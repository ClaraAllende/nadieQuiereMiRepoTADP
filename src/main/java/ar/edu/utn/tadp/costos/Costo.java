package ar.edu.utn.tadp.costos;

import java.math.BigDecimal;

public abstract class Costo implements Costeable {

	public static final Costo SIN_COSTO = new CostoFijo(BigDecimal.valueOf(0));
	
	private BigDecimal costo;
	
	public Costo(BigDecimal costo) {
		this.costo = costo;
	}
	
	public BigDecimal getCosto() { 
		//le cambié la visibilidad para usarlo desde las tareas.
		return costo;
	}

	

}
