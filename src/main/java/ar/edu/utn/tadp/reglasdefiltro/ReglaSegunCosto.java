package ar.edu.utn.tadp.reglasdefiltro;

import java.util.Collection;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.reunion.Reunion;

public class ReglaSegunCosto implements ReglaDeFiltrado {

	private Reunion reunion;
	
	public ReglaSegunCosto(Reunion reunion) {
		this.reunion = reunion;
	}
	
	@Override
	public Collection<Persona> filtrar(Collection<Persona> personas) {
		assert personas.size() != 0;
		final Persona personaConMenorCosto = getPersonaConMenorCost(personas);
		
		return Collections2.filter(personas, new Predicate<Persona>() {

			@Override
			public boolean apply(Persona input) {
				return personaConMenorCosto.dameTuCostoPara(reunion).equals(input.dameTuCostoPara(reunion));
			}
		});
	}

	private Persona getPersonaConMenorCost(Collection<Persona> personas) {
		assert personas.size() != 0;
		Persona personaConMenorCosto = null;
		
		for (Persona persona : personas) {
			if(personaConMenorCosto == null || persona.dameTuCostoPara(reunion).compareTo(personaConMenorCosto.dameTuCostoPara(reunion)) == -1) {
				personaConMenorCosto = persona;
			}
		}
		
		return personaConMenorCosto;
	}

}
