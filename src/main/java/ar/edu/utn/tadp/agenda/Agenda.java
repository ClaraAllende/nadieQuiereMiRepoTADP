package ar.edu.utn.tadp.agenda;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Interval;

import ar.edu.utn.tadp.excepcion.UserException;

public class Agenda{

	/*
	 * Constantes 
	 */
 	public static DateTime FUTURO = new DateTime(java.lang.Long.MAX_VALUE);
 	public static DateTime HOY = new DateTime(DateTime.now().getYear(), DateTime.now().getMonthOfYear(),DateTime.now().getDayOfMonth(),0,0);
	
	private List<Interval> horariosOcupados = new ArrayList<Interval>();
	
	public List<Interval> getHorariosOcupados() {
		return horariosOcupados;
	}
	
	public void ocupateDurante(Interval unIntervalo) {
		this.getHorariosOcupados().add(unIntervalo);
	}
	
	public boolean disponibleDurante (Interval unIntervalo) {
		for (Interval intervalo : this.getHorariosOcupados()) {
			if (intervalo.overlaps(unIntervalo)) return false;
		}
		return true;
	}
	
	public List<Interval> horariosDisponibles(){
		List<Interval> disponibles = new ArrayList<Interval>();
		Interval intDisponible = new Interval(Agenda.HOY , Agenda.FUTURO);
		disponibles.add(intDisponible);
		
		if (this.horariosOcupados.isEmpty()) return disponibles;
		
		for(Interval intOcupado : this.horariosOcupados){
			Interval intAuxiliar = new Interval(intDisponible.getStart(), intOcupado.getStart());
			disponibles.remove(intDisponible);
			disponibles.add(intAuxiliar);
			intDisponible = intDisponible.withStart(intOcupado.getEnd());
			disponibles.add(intDisponible);
		}
		return disponibles;
		//TODO TESTEAR!!! Esta medio oscuro esto, pero me parece que tiene sentido
		/* Si no tiene horarios ocupados, devuelve el intervalo desde el instante YA
		 * hasta el maximo posible de las fechas (anio 29mil y pico).
		 * Si no, empieza un algoritmo raro... 
		 * a un intervalo nuevo int2 le pongo el inicio de i y el inicio del horarioOcupado.
		 * lo guardo en la coleccion de disponibles.
		 * saco i de la coleccion y le cambio el inicio por el final del 
		 * intervalo ocupado. Finalmente vuelvo a agregar a i.  
		 */
	}
	
	public Interval intervaloDisponibleDe(Duration unaDuracion){
		for (Interval intervalo : this.horariosDisponibles()){
			if(intervalo.toDuration().isLongerThan(unaDuracion)) {
				return intervalo;
			}
		}
		throw new UserException("no hay un intervalo disponible de la duracion pedida");
	}

	public boolean tenesDisponibleAntesDe(Hours horas, DateTime vencimiento) {
		Interval interval = this.intervaloDisponibleDe(horas.toStandardDuration());
		interval = interval.withEnd(interval.getStart().plus(horas.toStandardDuration()));
		return interval.isBefore(vencimiento);
	}




}
