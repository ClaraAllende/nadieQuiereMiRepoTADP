package ar.edu.utn.tadp.agenda;

import org.joda.time.Interval;

public class Evento {

	
	private Interval intervalo;
	private TipoEvento tipo;
	
	
	public Evento(Interval unIntervalo) {
		intervalo = unIntervalo;
	}

	public Interval getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Interval intervalo) {
		this.intervalo = intervalo;
	}

	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}

	
	
}
