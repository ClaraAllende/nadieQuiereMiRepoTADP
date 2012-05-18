package ar.edu.utn.tadp.costos;

import java.math.BigDecimal;

import ar.edu.utn.tadp.reunion.Reunion;

public class CostoPorPersona extends Costo {

	public CostoPorPersona(BigDecimal costo) {
		super(costo);
	}

	@Override
	public BigDecimal dameTuCostoPara(Reunion reunion) {
		return getCosto().multiply(BigDecimal.valueOf(reunion.getCantidadDePersonasQueNecesitanTransporte()));
	}

}
