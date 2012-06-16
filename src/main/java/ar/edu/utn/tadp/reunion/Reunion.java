package ar.edu.utn.tadp.reunion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.utn.tadp.costos.Costeable;
import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.excepcion.UserException;
import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.requerimiento.Requerimiento;
import ar.edu.utn.tadp.reunion.tratamiento.AsistenciaMinima;
import ar.edu.utn.tadp.reunion.tratamiento.CriterioAlternativo;
import ar.edu.utn.tadp.reunion.tratamiento.Obligatoriedad;
import ar.edu.utn.tadp.reunion.tratamiento.Replanificacion;
import ar.edu.utn.tadp.reunion.tratamiento.TratamientoCancelacion;

/**
 * Representa a una reunion que se acordo a realizar dentro de la empresa.
 * 
 * @version 14-06-2012
 */
public class Reunion {
	private final Persona anfitrion;
	private final List<Recurso> recursos;
	private final Interval horario;
	private final List<Requerimiento> requerimientos;
	private final DateTime vencimiento;
	private boolean cancelada = false;
	private final List<TratamientoCancelacion> tratamientos = new ArrayList<TratamientoCancelacion>();

	public Reunion(final Persona anfitrion, final ArrayList<Recurso> recursos,
			final Interval horario, final List<Requerimiento> requerimientos,
			final DateTime vencimiento) {
		this.anfitrion = anfitrion;
		this.recursos = recursos;
		this.horario = horario;
		this.requerimientos = requerimientos;
		this.vencimiento = vencimiento;
	}

	public Persona getAnfitrion() {
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

	public long getDuracionDeReunion() {
		return horario.toDuration().getStandardHours();
	}

	public int getCantidadRequeridaDePersonas() {
		int cant = getRequerimientos().size() + 1; // total de req + anfintrion
		for (final Requerimiento requerimiento : this.requerimientos) {
			for (Propiedad condicion : requerimiento.getCondiciones()) {
				// XXX mejorar eso. Pregunta si req que no es persona.
				if (condicion.getTipo().toLowerCase().equals("tipo")
						&& (!condicion.getValor().toLowerCase()
								.equals("humano"))) {
					cant--;
				}
			}
		}
		return cant;
	}

	public long getCantidadDePersonasQueNecesitanTransporte() {
		int cantidadDePersonasQueNecesitanTransporte = 0;
		for (final Recurso recurso : recursos) {
			if (recurso.getTipo().toLowerCase().equals("humano")
					&& (!recurso.getEdificio().equals(this.getUbicacion()))) {
				cantidadDePersonasQueNecesitanTransporte++;
			}
		}
		return cantidadDePersonasQueNecesitanTransporte;
	}

	public boolean requiereTransporte() {
		return this.recursos.contains(Recurso.TRANSPORTE);
	}

	public int getCantidadDePersonas() {

		int cant = 0;

		for (final Recurso recurso : this.recursos) {

			if (recurso.getTipo().toLowerCase().equals("humano")) {

				cant++;

			}

		}

		return cant;

	}

	public BigDecimal getCostoTotal() {
		BigDecimal result = BigDecimal.valueOf(0);

		for (final Costeable costeable : recursos) {
			result = result.add(costeable.dameTuCostoPara(this));
		}

		return result;
	}

	public boolean tieneCatering() {
		return recursos.contains(Recurso.CATERING);
	}

	public String getUbicacion() {
		return this.getSala().getEdificio();
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
		for (final TratamientoCancelacion tratamiento : this.tratamientos) {
			if (tratamiento.evitarCancelacion(recurso, this, empresa)) {
				return true;
			}
		}
		// Si llega aca es por que no pudo solucionar la cancelacion.
		return false;
	}

	public void addTratamientoPorReplanificacion() {
		this.tratamientos.add(new Replanificacion());
	}

	public void addTratamientoPorAsistenciaMinima(final Integer porcentaje) {
		this.tratamientos.add(new AsistenciaMinima(porcentaje));
	}

	public void addTratamientoPorObligatoriedad() {
		this.tratamientos.add(new Obligatoriedad());
	}

	public void addTratamientoPorCriterioAlternativo() {
		this.tratamientos.add(new CriterioAlternativo());
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

	public List<Requerimiento> getRequerimientos() {
		return requerimientos;
	}

	public DateTime getVencimiento() {
		return vencimiento;
	}

	public boolean isCancelada() {
		return cancelada;
	}

	/**
	 * Remueve un recurso asignado a la reunion.
	 * 
	 * @param recurso
	 */
	public void quitar(Recurso recurso) {
		// Se quita de los recursos
		recursos.remove(recurso);
		Requerimiento requerimiento = getRequerimientoQueSatiface(recurso);
		if (requerimiento != null) {
			requerimiento.setRecursoQueSatisface(null);
		}
		// Se le libera la agenda.
		recurso.cancelarReunion(this);
	}

	public boolean isObligatorio(Recurso recurso) {
		Requerimiento requerimiento = getRequerimientoQueSatiface(recurso);
		if (requerimiento != null) {
			return requerimiento.isObligatorio();
		} else {
			// Si llega aca es por que es Anfitron, Sala, etc.. que son
			// obligatorios.
			return true;
		}
	}

	private Requerimiento getRequerimientoQueSatiface(Recurso recurso) {
		for (Requerimiento requerimiento : requerimientos) {
			if (requerimiento.getRecursoQueSatisface().equals(recurso)) {
				return requerimiento;
			}
		}
		// Si llega aca es por que es Anfitron, Sala, etc.. que son
		// obligatorios.
		return null;
	}
}
