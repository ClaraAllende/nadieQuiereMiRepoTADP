package ar.edu.utn.tadp.reglasdefiltro;

import java.util.Collection;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import ar.edu.utn.tadp.recurso.Persona;

public class ReglaSegunEstado implements ReglaDeFiltrado {

	@Override
	public Collection<Persona> filtrar(Collection<Persona> personas) {
		final Persona personaConMasPrioridad = getPersonaConMasPrioridad(personas);
		
		return Collections2.filter(personas, new Predicate<Persona>() {

			@Override
			public boolean apply(Persona input) {
				return personaConMasPrioridad.getEstado().equals(input.getEstado());
			}
		});
		
	}

	private Persona getPersonaConMasPrioridad(Collection<Persona> personas) {
		Persona personaConMasPrioridad = null;
		
		for (Persona persona : personas) {
			if(personaConMasPrioridad == null || persona.getEstado().esMasPrioritarioQue(personaConMasPrioridad.getEstado())) {
				personaConMasPrioridad = persona; 
			} 
		}
		
		return personaConMasPrioridad;
	}

}
