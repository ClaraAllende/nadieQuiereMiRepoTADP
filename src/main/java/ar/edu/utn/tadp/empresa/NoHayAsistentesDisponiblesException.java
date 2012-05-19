package ar.edu.utn.tadp.empresa;

import ar.edu.utn.tadp.excepcion.UserException;

public class NoHayAsistentesDisponiblesException extends UserException {

	public NoHayAsistentesDisponiblesException(String m, Throwable e) {
		super(m, e);
	}

	public NoHayAsistentesDisponiblesException() {
		super();
	}

}
