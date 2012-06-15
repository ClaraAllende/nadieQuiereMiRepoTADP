package ar.edu.utn.tadp.reglasdefiltro;

import java.util.Collection;

import ar.edu.utn.tadp.organizables.Organizable;
import ar.edu.utn.tadp.recurso.Recurso;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class ReglaSegunCosto implements ReglaDeFiltrado {

	private Organizable ubicable;
	
	public ReglaSegunCosto(Organizable ubicable) {
		this.ubicable = ubicable;
	}
	
	@Override
	public Collection<Recurso> filtrar(Collection<Recurso> recursos) {
		assert recursos.size() != 0;
		final Recurso personaConMenorCosto = getPersonaConMenorCost(recursos);
		
		return Collections2.filter(recursos, new Predicate<Recurso>() {

			@Override
			public boolean apply(Recurso input) {
				return personaConMenorCosto.dameTuCostoPara(ubicable).equals(input.dameTuCostoPara(ubicable));
			}
		});
	}

	private Recurso getPersonaConMenorCost(Collection<Recurso> recursos) {
		assert recursos.size() != 0;
		Recurso recursoConMenorCosto = null;
		
		for (Recurso persona : recursos) {
			if(recursoConMenorCosto == null || persona.dameTuCostoPara(ubicable).compareTo(recursoConMenorCosto.dameTuCostoPara(ubicable)) == -1) {
				recursoConMenorCosto = persona;
			}
		}
		
		return recursoConMenorCosto;
	}

}
