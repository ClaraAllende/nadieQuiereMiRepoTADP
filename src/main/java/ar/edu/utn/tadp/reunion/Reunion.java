package ar.edu.utn.tadp.reunion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.Interval;

import ar.edu.utn.tadp.costos.Costeable;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;

public class Reunion {

	private Persona host;
	private List<Recurso> recursos;
	private Interval horario;

	public Reunion(Persona anfitrion, ArrayList<Recurso> recursos,
			Interval horario) {
		this.host = anfitrion;
		this.recursos = recursos;
		this.horario = horario;
	}

	public long getCantidadDePersonasQueNecesitanTransporte() {
		return 0;
	}

	public long getDuracionDeReunion() {
		return horario.toDuration().getStandardHours();
	}

	public BigDecimal getCostoTotal() {
		BigDecimal result = BigDecimal.valueOf(0);

		for (Costeable costeable : recursos) {
			result = result.add(costeable.dameTuCostoPara(this));
		}

		return result;
	}

	public boolean tieneCatering() {
		return recursos.contains(Recurso.CATERING);
	}

	public String getUbicacion() {
		//TODO getUbicacion Hacer que devuelva la ubicacion de la sala, se debe modificar para que la sala se setee primero antes de los candidatos 
		return null;
	}
}
