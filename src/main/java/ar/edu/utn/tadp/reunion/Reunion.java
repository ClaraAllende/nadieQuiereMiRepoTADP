package ar.edu.utn.tadp.reunion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.utn.tadp.costos.Costeable;
import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.excepcion.UserException;
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
		// TODO Falta hacerlo. Aca deberia avisar a los recursos para que se
		// liberen.
		for (Recurso recurso : this.getRecursos()) {
			recurso.cancelarReunion(this);
		}
		this.setCancelada(true);
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

	public void setCancelada(final boolean cancelada) {
		this.cancelada = cancelada;
	}
}
