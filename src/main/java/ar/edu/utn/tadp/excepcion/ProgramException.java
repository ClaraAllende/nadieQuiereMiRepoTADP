package ar.edu.utn.tadp.excepcion;

@SuppressWarnings("serial")
public class ProgramException extends RuntimeException {

	public ProgramException(Throwable e){
		super(e);
	}
	
	public ProgramException(String message, Throwable e){
		super(message, e);
	}
}
