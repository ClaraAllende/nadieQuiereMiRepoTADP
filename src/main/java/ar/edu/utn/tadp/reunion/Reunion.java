package ar.edu.utn.tadp.reunion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import ar.edu.utn.tadp.costos.Costeable;

public class Reunion {

	public long getCantidadDePersonasQueNecesitanTransporte() {
		return 0;
	}

	public long getDuracionDeReunion() {
		return 0;
	}
	
	public BigDecimal getCostoTotal() {
		BigDecimal result = BigDecimal.valueOf(0);
		
		for (Costeable costeable : dameListDeLosCosteables())
		{
			result.add(costeable.dameTuCostoPara(this));
		}
		
		return result;
	}

	private Collection<Costeable> dameListDeLosCosteables() {
		return new ArrayList<Costeable>();
	}
}
