package ar.edu.utn.tadp.agenda;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;

<<<<<<< HEAD
public class Agenda{
=======
public class Agenda implements Disponible {
>>>>>>> 94ff1662e8f9f70f38ca1e49badecf615bf65d6f
	/*
	 * Constantes 
	 */
 	public static DateTime FUTURO = new DateTime(java.lang.Long.MAX_VALUE);
	
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
		Interval intDisponible = new Interval(DateTime.now() , Agenda.FUTURO);
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
		throw new NoSuchIntervalException();
	}




}
