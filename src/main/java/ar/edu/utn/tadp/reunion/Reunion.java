package ar.edu.utn.tadp.reunion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.joda.time.Interval;

import ar.edu.utn.tadp.costos.Costeable;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;

public class Reunion {

	private Persona host;
	private List<Recurso> asistentes;
	private Interval horario;

	public Reunion(Persona anfitrion, ArrayList<Recurso> asistentes, Interval horario) {
		this.host = anfitrion;
		this.asistentes = asistentes;
		this.horario = horario;
		// TODO Auto-generated constructor stub
	}

	public long getCantidadDePersonasQueNecesitanTransporte() {
		return 0;
	}

	public long getDuracionDeReunion() {
		return 0;
	}
	
	public BigDecimal getCostoTotal() {
		BigDecimal result = BigDecimal.valueOf(0);
		
		for (Costeable costeable : dameListDeLosCosteables())
		{
			result.add(costeable.dameTuCostoPara(this));
		}
		
		return result;
	}

	private Collection<Costeable> dameListDeLosCosteables() {
		return new ArrayList<Costeable>();
	}
}
