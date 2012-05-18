package ar.edu.utn.tadp.costos;

import java.math.BigDecimal;

import ar.edu.utn.tadp.reunion.Reunion;

public class CostoFijo extends Costo {

	public CostoFijo(BigDecimal costo) {
		super(costo);
	}

	@Override
	public BigDecimal dameTuCostoPara(Reunion reunion) {
		return this.getCosto();
	}
	
}
