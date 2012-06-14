package ar.edu.utn.tadp.agenda;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Interval;

import ar.edu.utn.tadp.excepcion.UserException;

import com.google.common.base.Function;
import com.google.common.collect.*;

public class Agenda {

	/*
	 * Constantes 
	 */
 	public static DateTime FUTURO = new DateTime(java.lang.Long.MAX_VALUE);
 	public static DateTime HOY = new DateTime(DateTime.now().getYear(), DateTime.now().getMonthOfYear(),DateTime.now().getDayOfMonth(),0,0);
	
	private List<Evento> eventos = new ArrayList<>();
	
	public void ocupate(Evento evento){
		this.eventos.add(evento);
	}

	
	public boolean disponibleDurante (Interval unIntervalo) {
		for (Interval intervalo : this.getHorariosOcupados()) {
			if (intervalo.overlaps(unIntervalo)) return false;
		}
		return true;
	}

	
	public List<Interval> getHorariosOcupados() {
		Function<Evento, Interval> function = new Function<Evento, Interval>() {

			@Override
			public Interval apply(Evento evento) {
				return evento.getIntervalo();
			}
		};
		return Lists.transform(this.eventos, function );
	}
	
	public List<Interval> horariosDisponibles(){
		List<Interval> disponibles = new ArrayList<Interval>();
		Interval intDisponible = new Interval(Agenda.HOY , Agenda.FUTURO);
		disponibles.add(intDisponible);
		
		if (this.getHorariosOcupados().isEmpty()) return disponibles;
		
		for(Interval intOcupado : this.getHorariosOcupados()){
			Interval intAuxiliar = new Interval(intDisponible.getStart(), intOcupado.getStart());
			disponibles.remove(intDisponible);
			disponibles.add(intAuxiliar);
			intDisponible = intDisponible.withStart(intOcupado.getEnd());
			disponibles.add(intDisponible);
		}
		return disponibles;
	}
	
	public Interval tenesDisponible(Duration unaDuracion){
		for (Interval intervalo : this.horariosDisponibles()){
			if(intervalo.toDuration().isLongerThan(unaDuracion)) {
				return intervalo;
			}
		}
		throw new UserException("no hay un intervalo disponible de la duracion pedida");
	}

	public boolean tenesDisponibleAntesDe(Hours horas, DateTime vencimiento) {
		Interval interval = this.tenesDisponible(horas.toStandardDuration());
		interval = interval.withEnd(interval.getStart().plus(horas.toStandardDuration()));
		return interval.isBefore(vencimiento);
	}

	public boolean estasOcupadoDurante(Interval intervalo) {
		return getHorariosOcupados().contains(intervalo);
	}




}
