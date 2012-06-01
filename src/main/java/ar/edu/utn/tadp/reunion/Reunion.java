package ar.edu.utn.tadp.reunion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.Interval;

import ar.edu.utn.tadp.costos.Costeable;
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

	public long getDuracionDeReunion() {
		return horario.toDuration().getStandardHours();
	}

	public long getCantidadDePersonasQueNecesitanTransporte() {
		return 0;
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
		// TODO getUbicacion Hacer que devuelva la ubicacion de la sala, se debe
		// modificar para que la sala se setee primero antes de los candidatos
		return null;
	}
}
