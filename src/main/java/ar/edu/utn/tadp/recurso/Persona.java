package ar.edu.utn.tadp.recurso;

import ar.edu.utn.tadp.criterio.Criterio;

public class Persona extends Recurso {
	@Override
	public boolean cumpleCondicion(Criterio unCriterio){
		return false;
	}
}
