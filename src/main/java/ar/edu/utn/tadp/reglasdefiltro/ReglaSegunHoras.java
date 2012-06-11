package ar.edu.utn.tadp.reglasdefiltro;

import java.util.Collection;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import ar.edu.utn.tadp.recurso.Persona;

public class ReglaSegunHoras implements ReglaDeFiltrado {

	@Override
	public Collection<Persona> filtrar(Collection<Persona> personas) {
		assert personas.size() != 0;
		
		final Persona persona = dameAlQueTengaMenosHoras(personas);
		
		return Collections2.filter(personas, new Predicate<Persona>() {

			@Override
			public boolean apply(Persona input) {
				return persona.getCantidadDeHorasDeReunionEnLaUltimaSemana().equals(input.getCantidadDeHorasDeReunionEnLaUltimaSemana());
			}
		});
	}

	private Persona dameAlQueTengaMenosHoras(Collection<Persona> personas) {
		assert personas.size() != 0;
		
		Persona personaConMenosHoras = null;
		
		for (Persona persona : personas) { 
			if(personaConMenosHoras == null || persona.getCantidadDeHorasDeReunionEnLaUltimaSemana() < personaConMenosHoras.getCantidadDeHorasDeReunionEnLaUltimaSemana()) {
				personaConMenosHoras = persona;
			}
		}
		
		return personaConMenosHoras;
	}

}
