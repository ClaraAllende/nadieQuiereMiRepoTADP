package ar.edu.utn.tadp.proyectos;

import java.math.BigDecimal;

import ar.edu.utn.tadp.costos.Costeable;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.reunion.Reunion;
/**
 * El composite de tareas :)
 * @author clari
 *
 */
public abstract class Tarea implements Costeable {

	public abstract BigDecimal dameTuCostoPara(Reunion unaReunion);

	public abstract BigDecimal tiempo();

	public Persona getEmpleadoResponsable(){
		//me gustaría que no haga nada.
		return new Persona(null);
	}
	
	public abstract boolean isComplete();
}