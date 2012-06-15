package ar.edu.utn.tadp.costos;

import java.math.BigDecimal;

import ar.edu.utn.tadp.organizables.Organizable;

public class CostoPorPersona extends Costo {

	public CostoPorPersona(BigDecimal costo) {
		super(costo);
	}

	@Override
	public BigDecimal dameTuCostoPara(Organizable ubicable) {
		return getCosto().multiply(BigDecimal.valueOf(ubicable.getCantidad()));
	}

}
