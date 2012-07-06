package ar.edu.utn.tadp.organizables;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.excepcion.UserException;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.requerimiento.Requerimiento;
import ar.edu.utn.tadp.tratamiento.AsistenciaMinima;
import ar.edu.utn.tadp.tratamiento.CriterioAlternativo;
import ar.edu.utn.tadp.tratamiento.Obligatoriedad;
import ar.edu.utn.tadp.tratamiento.Replanificacion;
import ar.edu.utn.tadp.tratamiento.TratamientoCancelacion;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Representa a una reunion que se acordo a realizar dentro de la empresa.
 * 
 * @version 21-06-2012
 */
public class Reunion implements Organizable {

	private Persona anfitrion;
	private List<Recurso> recursos;
	private Interval horario;
	private List<Requerimiento> requerimientos;
	private DateTime vencimiento;
	private boolean cancelada = false;
	private List<TratamientoCancelacion> tratamientos = new ArrayList<TratamientoCancelacion>();
	
		
	public Reunion(final Persona anfitrion, final ArrayList<Recurso> recursos,
			final Interval horario, final List<Requerimiento> requerimientos,
			final DateTime vencimiento) {
		this.anfitrion = anfitrion;
		this.recursos = recursos;
		this.horario = horario;
		this.requerimientos = requerimientos;
		this.vencimiento = vencimiento;
	}

	@Override
	public Persona getOrganizador() {
		return anfitrion;
	}

	public List<Recurso> getRecursos() {
		return recursos;
	}

	public Interval getHorario() {
		return horario;
	}

	private Recurso getSala() {
		for (final Recurso recurso : recursos) {
			if (recurso.getTipo().toLowerCase().equals("sala")) {
				return recurso;
			}
		}
		throw new UserException("Reunion no tiene una sala asignada!");
	}

	@Override
	public long getDuracion() {
		return horario.toDuration().getStandardHours();
	}

	public int getCantidadRequeridaDePersonas() {
		Predicate<Requerimiento> esDePersona = new Predicate<Requerimiento>() {
			@Override
			public boolean apply(Requerimiento req) {
				return !req.isRecurso();
			};
		};
		return Lists.newArrayList(
				Iterables.filter(this.getRequerimientos(), esDePersona)).size();
	}

	public long getCantidadDePersonasQueNecesitanTransporte() {
		int cantidadDePersonasQueNecesitanTransporte = 0;
		for (final Recurso recurso : recursos) {
			if (recurso.esPersona()
					&& (!recurso.getEdificio().equals(this.getUbicacion()))) {
				cantidadDePersonasQueNecesitanTransporte++;
			}
		}
		return cantidadDePersonasQueNecesitanTransporte;
	}

	public BigDecimal getCostoTotal() {
		BigDecimal result = BigDecimal.valueOf(0);

		for (final Recurso costeable : recursos) {
			result = result.add(costeable.dameTuCostoPara(this));
		}

		return result;
	}

	public boolean tieneCatering() {
		return recursos.contains(Recurso.CATERING);
	}

	@Override
	public String getUbicacion() {
		return this.getSala().getEdificio();
	}

	public int getCantidadDePersonas() {
		int cant = 0;
		for (final Recurso recurso : this.recursos) {
			if (recurso.esPersona()) {
				cant++;
			}
		}
		return cant;
	}

	public boolean requiereTransporte() {
		return this.recursos.contains(Recurso.TRANSPORTE);
	}

	@Override
	public long getCantidad() {
		return this.getCantidadDePersonasQueNecesitanTransporte();
	}

	public Persona getAnfitrion() {
		return anfitrion;
	}

	public List<Requerimiento> getRequerimientos() {
		return requerimientos;
	}

	public DateTime getVencimiento() {
		return vencimiento;
	}

	public boolean isCancelada() {
		return cancelada;
	}

	private void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}

	public List<TratamientoCancelacion> getTratamientos() {
		return tratamientos;
	}

	public void setTratamientos(List<TratamientoCancelacion> tratamientos) {
		this.tratamientos = tratamientos;
	}

	private void setAnfitrion(Persona anfitrion) {
		this.anfitrion = anfitrion;
	}

	private void setHorario(Interval horario) {
		this.horario = horario;
	}

	private void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

	private void setRequerimientos(List<Requerimiento> requerimientos) {
		this.requerimientos = requerimientos;
	}

	private void setVencimiento(DateTime vencimiento) {
		this.vencimiento = vencimiento;
	}

	/**
	 * Indica a la reunion que se debe cancelar.
	 */
	public void cancelar() {
		// Avisa a los recursos para que se liberen.
		for (Recurso recurso : this.getRecursos()) {
			recurso.cancelarReunion(this);
		}
		// Se quita los recursos.
		this.getRecursos().removeAll(this.getRecursos());
		cancelada = true;
	}

	/**
	 * Busca posibles tratamientos para evitar la cancelacion.
	 * 
	 * @param recurso
	 *            <code>Recurso</code> que cancelo su participacion.
	 * @param empresa
	 *            <code>Empresa</code> a la cual pertenece la reunion.
	 * @return <code>true</code> si fue exitoso, <code>false</code> si no.
	 * */
	public boolean tratarCancelacion(final Recurso recurso,
			final Empresa empresa) {
		for (final TratamientoCancelacion tratamiento : this.getTratamientos()) {
			if (tratamiento.evitarCancelacion(recurso, this, empresa)) {
				return true;
			}
		}
		// Si llega aca es por que no pudo solucionar la cancelacion.
		return false;
	}

	/**
	 * Remueve un recurso asignado a la reunion.
	 * 
	 * @param recurso
	 */
	public void quitar(Recurso recurso) {
		// Se quita de los recursos
		recursos.remove(recurso);
		try {
			getRequerimientoQueSatiface(recurso).setRecursoQueSatisface(null);
		} catch (UserException e) {
			// No pasa nada.
		}
		// Se le libera la agenda.
		recurso.cancelarReunion(this);
	}

	public Requerimiento getRequerimientoQueSatiface(Recurso recurso) {
		try {
			for (Requerimiento requerimiento : getRequerimientos()) {
				if (requerimiento.getRecursoQueSatisface().equals(recurso)) {
					return requerimiento;
				}
			}
		} catch (NullPointerException e) {
			// Se ve que el recurso cancelo su participacion.
		}
		// Si llega aca es por que es Anfitron, Sala, etc.. que son
		// obligatorios.
		throw new UserException(
				"No se encuentra RequerimientoQueSatiface el recurso: "
						+ recurso);
	}

	public boolean isObligatorio(Recurso recurso) {
		try {
			return getRequerimientoQueSatiface(recurso).isObligatorio();
		} catch (Exception e) {
			// Si llega aca es por que es Anfitron, Sala, etc.. que son
			// obligatorios.
			return true;
		}
	}

	/**
	 * Reemplaza un recurso por otro.
	 */
	public void reemplazarPorAlternativo(Requerimiento original,
			Recurso recurso, Requerimiento alternativo, Recurso reemplazo) {
		alternativo.setRecursoQueSatisface(reemplazo);
		requerimientos.add(alternativo);
		recursos.add(reemplazo);
		requerimientos.remove(original);
		quitar(recurso);
	}

	public void addTratamientoPorObligatoriedad() {
		this.tratamientos.add(new Obligatoriedad());
	}

	public void addTratamientoPorAsistenciaMinima(final Integer porcentaje) {
		this.tratamientos.add(new AsistenciaMinima(porcentaje));
	}

	public void addTratamientoPorReplanificacion() {
		this.tratamientos.add(new Replanificacion());
	}

	public void addTratamientoPorCriterioAlternativo() {
		this.tratamientos.add(new CriterioAlternativo());
	}

	/**
	 * Copia el estado de otra reunion.
	 * 
	 * @param reunion
	 *            <code>Reunion</code> cuyo estado se va a copiar.
	 */
	public void copiar(Reunion reunion) {
		this.setAnfitrion(reunion.getAnfitrion());
		this.setCancelada(reunion.isCancelada());
		this.setHorario(reunion.getHorario());
		this.setRecursos(reunion.getRecursos());
		this.setRequerimientos(reunion.getRequerimientos());
		this.setTratamientos(reunion.getTratamientos());
		this.setVencimiento(reunion.getVencimiento());
	}
	
}
