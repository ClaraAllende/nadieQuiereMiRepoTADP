package ar.edu.utn.tadp.reglasdefiltro;

import java.util.Collection;

import ar.edu.utn.tadp.recurso.Recurso;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class ReglaSegunEstado implements ReglaDeFiltrado {

	@Override
	public Collection<Recurso> filtrar(Collection<Recurso> personas) {
		final Recurso recursoConMasPrioridad = getRecursoConMasPrioridad(personas);
		
		return Collections2.filter(personas, new Predicate<Recurso>() {

			@Override
			public boolean apply(Recurso input) {
				return recursoConMasPrioridad.getEstado().equals(input.getEstado());
			}
		});
		
	}

	private Recurso getRecursoConMasPrioridad(Collection<Recurso> recursos) {
		Recurso recursoConMasPrioridad = null;
		
		for (Recurso recurso : recursos) {
			if(recursoConMasPrioridad == null || recurso.getEstado().esMasPrioritarioQue(recursoConMasPrioridad.getEstado())) {
				recursoConMasPrioridad = recurso; 
			} 
		}
		
		return recursoConMasPrioridad;
	}

}
