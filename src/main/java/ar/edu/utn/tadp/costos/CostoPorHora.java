package ar.edu.utn.tadp.costos;

import java.math.BigDecimal;

import ar.edu.utn.tadp.organizables.Organizable;

public class CostoPorHora extends Costo {

	public CostoPorHora(BigDecimal costoPorHora) {
		super(costoPorHora);
	}

	@Override
	public BigDecimal dameTuCostoPara(Organizable reunion) {
		return getCosto().multiply(BigDecimal.valueOf(reunion.getDuracion()));
	}

}
