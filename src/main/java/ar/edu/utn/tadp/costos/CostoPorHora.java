package ar.edu.utn.tadp.costos;

import java.math.BigDecimal;

import ar.edu.utn.tadp.reunion.Reunion;

public class CostoPorHora extends Costo {

	public CostoPorHora(BigDecimal costoPorHora) {
		super(costoPorHora);
	}

	@Override
	public BigDecimal dameTuCostoPara(Reunion reunion) {
		return getCosto().multiply(BigDecimal.valueOf(reunion.getDuracionDeReunion()));
	}

}
