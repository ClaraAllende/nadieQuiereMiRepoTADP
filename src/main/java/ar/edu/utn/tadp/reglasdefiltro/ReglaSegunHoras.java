package ar.edu.utn.tadp.reglasdefiltro;

import java.util.Collection;

import ar.edu.utn.tadp.recurso.Recurso;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class ReglaSegunHoras implements ReglaDeFiltrado {

	@Override
	public Collection<Recurso> filtrar(Collection<Recurso> personas) {
		assert personas.size() != 0;
		
		final Recurso persona = dameAlQueTengaMenosHoras(personas);
		
		return Collections2.filter(personas, new Predicate<Recurso>() {

			@Override
			public boolean apply(Recurso otraRecurso) {
				return persona.getHorasEnReunionesDeLaSemana() == otraRecurso.getHorasEnReunionesDeLaSemana();
			}
		});
	}

	private Recurso dameAlQueTengaMenosHoras(Collection<Recurso> recursos) {
		assert recursos.size() != 0;
		
		Recurso recursoConMenosHoras = null;
		
		for (Recurso persona : recursos) { 
			if(recursoConMenosHoras == null || persona.getHorasEnReunionesDeLaSemana() < recursoConMenosHoras.getHorasEnReunionesDeLaSemana()) {
				recursoConMenosHoras = persona;
			}
		}
		
		return recursoConMenosHoras;
	}

}
