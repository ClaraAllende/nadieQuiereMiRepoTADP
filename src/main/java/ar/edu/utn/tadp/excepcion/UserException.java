package ar.edu.utn.tadp.excepcion;


@SuppressWarnings("serial")
public class UserException extends RuntimeException {

	public UserException(String m, Throwable e){
		super(m, e);
	}

	public UserException() {
		super();
	}

	public UserException(String string) {
		super(string);
	}
}
