package ar.edu.utn.tadp.costos;

import java.math.BigDecimal;

public abstract class Costo implements Costeable {

	public static final Costo CATERING = new CostoFijo(BigDecimal.valueOf(400.0));
	public static final Costo TRANSPORTE = new CostoPorPersona(BigDecimal.valueOf(25.0));
	
	private BigDecimal costo;
	
	public Costo(BigDecimal costo) {
		this.costo = costo;
	}
	
	protected BigDecimal getCosto() { 
		return costo;
	}

	

}
