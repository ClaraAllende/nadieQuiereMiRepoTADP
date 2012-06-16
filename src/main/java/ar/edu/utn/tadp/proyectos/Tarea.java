package ar.edu.utn.tadp.proyectos;

import java.math.BigDecimal;

import ar.edu.utn.tadp.costos.Costeable;
import ar.edu.utn.tadp.organizables.Organizable;
import ar.edu.utn.tadp.recurso.Persona;
/**
 * El composite de tareas :)
 * @author clari
 *
 */
public abstract class Tarea implements Costeable {

	public abstract BigDecimal dameTuCostoPara(Organizable unaReunion);

	public abstract BigDecimal tiempo();

	public Persona getEmpleadoResponsable(){
		//me gustaría que no haga nada.
		return new Persona(null);
	}
	
	public abstract boolean isComplete();
}