package ar.edu.utn.tadp.reglasdefiltro;

import java.util.Collection;

import ar.edu.utn.tadp.recurso.Persona;

public interface ReglaDeFiltrado {

	Collection<Persona> filtrar(Collection<Persona> personas);
}
