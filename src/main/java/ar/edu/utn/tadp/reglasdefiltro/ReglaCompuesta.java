package ar.edu.utn.tadp.reglasdefiltro;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ar.edu.utn.tadp.recurso.Persona;

public class ReglaCompuesta implements ReglaDeFiltrado {

	private final List<ReglaDeFiltrado> listaDeReglas;

	public ReglaCompuesta(final List<ReglaDeFiltrado> listaDeReglas) {
		this.listaDeReglas = listaDeReglas;
	}

	@Override
	public Collection<Persona> filtrar(final Collection<Persona> personas) {
		Collection<Persona> personasFiltradas = personas;
		
		for (final ReglaDeFiltrado reglaDeFiltrado : listaDeReglas) {
			if(personasFiltradas.size() == 1) {
				return personasFiltradas;
			} else {
				personasFiltradas = reglaDeFiltrado.filtrar(personasFiltradas);
			}
		}
		
		
		return Arrays.asList(personasFiltradas.iterator().next());
	}

}
