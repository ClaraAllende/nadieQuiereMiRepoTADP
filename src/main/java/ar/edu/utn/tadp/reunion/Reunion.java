package ar.edu.utn.tadp.reunion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.Interval;

import ar.edu.utn.tadp.costos.Costeable;
import ar.edu.utn.tadp.excepcion.UserException;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;

/**
 * Representa a una reunion que se acordo a realizar dentro de la empresa.
 * 
 * @version 01-06-2012
 */
public class Reunion {
	private final Persona anfitrion;
	private final List<Recurso> recursos;
	private final Interval horario;

	public Reunion(final Persona anfitrion, final ArrayList<Recurso> recursos,
			final Interval horario) {
		this.anfitrion = anfitrion;
		this.recursos = recursos;
		this.horario = horario;
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
			if (recurso.getTipo().equals("sala")) {
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
			if (recurso.getTipo().equals("humano")
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
}
