package ar.edu.utn.tadp.recurso;

import ar.edu.utn.tadp.criterio.Criterio;

public class Recurso implements Disponible {
	public boolean cumpleCondicion (Criterio unCriterio){
		//TODO: definir si las condiciones son un command o si hacemos double dispatch
		return false;
	}
}
