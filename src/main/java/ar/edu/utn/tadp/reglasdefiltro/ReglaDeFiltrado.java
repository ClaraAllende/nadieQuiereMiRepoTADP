package ar.edu.utn.tadp.reglasdefiltro;

import java.util.Collection;

import ar.edu.utn.tadp.recurso.Recurso;

public interface ReglaDeFiltrado {

	Collection<Recurso> filtrar(Collection<Recurso> personas);
}
