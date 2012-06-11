package ar.edu.utn.tadp.proyectos;

import java.math.BigDecimal;

public class ComplejidadSimple implements Complejidad{

	private BigDecimal costoPorHora;
	
	public ComplejidadSimple(BigDecimal costo){
		this.costoPorHora=costo;
	}
	@Override
	public BigDecimal costo(Tarea tarea) {
		return this.costoPorHoraFijo().add( tarea.tiempo());
	}

	public BigDecimal costoPorHoraFijo(){
		return this.costoPorHora;
	}
	
}
