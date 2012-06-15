package ar.edu.utn.tadp.agenda;

import org.joda.time.Interval;
import org.joda.time.ReadableDuration;
import org.joda.time.base.AbstractInstant;

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

	public TipoEvento getTipo() {
		return tipo;
	}

	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}

	public AbstractInstant getFechaInicio() {
		return this.intervalo.getStart();
	}

	public ReadableDuration getDuracion() {
		return this.getIntervalo().toDuration();
	}
	
}
