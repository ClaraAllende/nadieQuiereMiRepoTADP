package ar.edu.utn.tadp.reglasdefiltro;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ar.edu.utn.tadp.recurso.Recurso;

public class ReglaCompuesta implements ReglaDeFiltrado {

	private final List<ReglaDeFiltrado> listaDeReglas;

	public ReglaCompuesta(final List<ReglaDeFiltrado> listaDeReglas) {
		this.listaDeReglas = listaDeReglas;
	}

	@Override
	public Collection<Recurso> filtrar(final Collection<Recurso> recursos) {
		Collection<Recurso> recursosFiltradas = recursos;
		
		for (final ReglaDeFiltrado reglaDeFiltrado : listaDeReglas) {
			if(recursosFiltradas.size() == 1) {
				return recursosFiltradas;
			} else {
				recursosFiltradas = reglaDeFiltrado.filtrar(recursosFiltradas);
			}
		}
		
		
		return Arrays.asList(recursosFiltradas.iterator().next());
	}

}
