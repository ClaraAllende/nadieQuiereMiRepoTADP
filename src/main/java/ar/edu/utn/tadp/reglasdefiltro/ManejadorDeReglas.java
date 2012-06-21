package ar.edu.utn.tadp.reglasdefiltro;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ar.edu.utn.tadp.recurso.Recurso;

public class ManejadorDeReglas {

	private ReglaDeFiltrado reglaDeFiltrado;

	public ManejadorDeReglas(ReglaDeFiltrado...reglaDeFiltrados) {
		this.reglaDeFiltrado = new ReglaCompuesta(Arrays.asList(reglaDeFiltrados));
	}
	
	public Recurso filtra(List<Recurso> lista) {
		Collection<Recurso> listaFiltrada = this.reglaDeFiltrado.filtrar(lista);
	
		if(listaFiltrada.size() == 1) {
			return listaFiltrada.iterator().next();
		}
		
		throw new NoSeFiltroCompletamenteException("No se pudo filtrar correctamente");
	}

}
