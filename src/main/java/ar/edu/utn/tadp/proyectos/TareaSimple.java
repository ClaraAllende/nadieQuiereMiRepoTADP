package ar.edu.utn.tadp.proyectos;

import java.math.BigDecimal;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import ar.edu.utn.tadp.organizables.Organizable;
import ar.edu.utn.tadp.recurso.Persona;

public class TareaSimple extends Tarea {
	
	private LocalDate inicio;
	private LocalDate fin; 
	private Persona empleadoResponsable;
	private Complejidad complejidad;
	
	public TareaSimple (LocalDate inicio, LocalDate fin, Persona empleado, Complejidad complejidad){
		this.inicio=inicio;
		this.fin=fin;
		this.empleadoResponsable= empleado;
		this.complejidad=complejidad;
	}

	@Override
	public BigDecimal dameTuCostoPara(Organizable unaReunion) {
		return this.complejidad.costo(this);
	}

	@Override
	public BigDecimal tiempo() {
		Days date= Days.daysBetween(this.inicio, this.fin);
		return new BigDecimal(date.getDays());
	}
	
	@Override
	public boolean isComplete(){
	return this.fin.isAfter(LocalDate.now());	
	}
	public  Persona getEmpleadoResponsable(){
		return this.empleadoResponsable;
	}
	

}
