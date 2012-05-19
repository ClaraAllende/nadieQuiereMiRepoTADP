package ar.edu.utn.tadp.empresa;
import ar.edu.utn.tadp.requerimiento.*;
import ar.edu.utn.tadp.reunion.*;
import ar.edu.utn.tadp.recurso.*;
import ar.edu.utn.tadp.excepcion.*;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Interval;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Representa a una Empresa. Contiene todos los recursos.
 */
public class Empresa {

	private List<Recurso> recursos = new ArrayList<Recurso>();

	public Reunion createReunion(Persona anfitrion, List<Requerimiento> criterios, Hours horas, DateTime vencimiento) {
		ArrayList<ArrayList<Recurso>> candidatos = new ArrayList<ArrayList<Recurso>>();
		
		for( Requerimiento requerimiento : criterios) {
			try {
				requerimiento.buscaLosQueTeSatisfacen(recursos);
			} 
			catch (UserException e) {
				// TODO: handle exception
			}
		}
		for( Requerimiento requerimiento : criterios){
			try{
				candidatos.add(requerimiento.teSatisfacenDurante(horas,vencimiento)); 
			}
			catch(UserException e) {
				// TODO: handle exception
			}
		}
		ArrayList<Recurso> asistentes = new ArrayList<Recurso>(); 
		asistentes = this.seleccionarCandidatos(candidatos);
		//inicializo un intervalo vacio
		Interval intervalo = new Interval(0, 0);
		
		for (Recurso recurso : asistentes){
//			en intervalo guardo el primer intervalo que cumpla con la disponibilidad de horas del recurso elegido
			intervalo = recurso.getAgenda().intervaloDisponibleDe(horas.toStandardDuration());
//			le pongo quiero que el final del intervalo sea las ghoras despues del comienzo.
			intervalo = intervalo.withEnd(intervalo.getStart().plus(horas.toStandardDuration()));
			
			boolean flag = true;
//			recorro nuevamente la lista de recursos
			for (Recurso rec : asistentes){
//			le pregunto a todos los recursos si tienen disponible el intervalo dado
				flag = rec.getAgenda().disponibleDurante(intervalo);
			}
			if (flag = true) {
//				si lo tienen, ocupo a todos los recursos.
				recurso.getAgenda().ocupateDurante(intervalo);
			}
		}
//		por ultimo devuelvo una reunion inicializada feliz y contenta :)
		return new Reunion(anfitrion, asistentes, intervalo);
	}

	private ArrayList<Recurso> seleccionarCandidatos(ArrayList<ArrayList<Recurso>> candidatos) {
		ArrayList<Recurso> asistentes = new ArrayList<Recurso>();
		for (ArrayList<Recurso> recursos : candidatos){
			asistentes.add(recursos.get(0));
		}
		return asistentes;
	}

	public void addRecurso(Recurso recurso) {
		this.recursos.add(recurso);
	}

	public void removeRecurso(Recurso recurso) {
		this.recursos.remove(recurso);
	}

	public void removeAllRecurso() {
		this.recursos.removeAll(this.recursos);
	}
}
