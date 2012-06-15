package ar.edu.utn.tadp.reglasdefiltro;

import java.util.Collection;

import ar.edu.utn.tadp.organizables.Organizable;
import ar.edu.utn.tadp.recurso.Recurso;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class ReglaSegunUbicacion implements ReglaDeFiltrado {

	private Organizable ubicable;

	public ReglaSegunUbicacion(Organizable reunion) {
		this.ubicable = reunion;
	}

	@Override
	public Collection<Recurso> filtrar(Collection<Recurso> recursos) {
		return Collections2.filter(recursos, new Predicate<Recurso>() {

			@Override
			public boolean apply(Recurso input) {
				return input.getUbicacion().equals(ubicable.getUbicacion());
			}
			
		});
	}

}
