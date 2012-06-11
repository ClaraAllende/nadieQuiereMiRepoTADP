package ar.edu.utn.tadp.reglasdefiltro;

import java.util.Collection;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.reunion.Reunion;

public class ReglaSegunUbicacion implements ReglaDeFiltrado {

	private Reunion reunion;

	public ReglaSegunUbicacion(Reunion reunion) {
		this.reunion = reunion;
	}

	@Override
	public Collection<Persona> filtrar(Collection<Persona> personas) {
		return Collections2.filter(personas, new Predicate<Persona>() {

			@Override
			public boolean apply(Persona input) {
				return input.getUbicacion().equals(reunion.getUbicacion());
			}
			
		});
	}

}
