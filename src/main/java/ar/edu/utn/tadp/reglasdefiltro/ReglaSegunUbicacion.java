package ar.edu.utn.tadp.reglasdefiltro;

import java.util.Collection;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.reunion.Reunion;

public class ReglaSegunUbicacion implements ReglaDeFiltrado {

	private Reunion reunion;

	public ReglaSegunUbicacion(Reunion reunion) {
		this.reunion = reunion;
	}

	@Override
	public Collection<Recurso> filtrar(Collection<Recurso> recursos) {
		return Collections2.filter(recursos, new Predicate<Recurso>() {

			@Override
			public boolean apply(Recurso input) {
				return input.getUbicacion().equals(reunion.getUbicacion());
			}
			
		});
	}

}
