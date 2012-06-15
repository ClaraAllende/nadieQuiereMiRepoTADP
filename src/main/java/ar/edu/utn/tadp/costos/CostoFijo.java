package ar.edu.utn.tadp.costos;

import java.math.BigDecimal;

import ar.edu.utn.tadp.organizables.Organizable;

public class CostoFijo extends Costo {

	public CostoFijo(BigDecimal costo) {
		super(costo);
	}

	@Override
	public BigDecimal dameTuCostoPara(Organizable reunion) {
		return this.getCosto();
	}
	
}
