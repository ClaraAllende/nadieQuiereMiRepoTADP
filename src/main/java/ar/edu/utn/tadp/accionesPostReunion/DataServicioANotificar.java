package ar.edu.utn.tadp.accionesPostReunion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import ar.edu.utn.tadp.excepcion.ProgramException;
import ar.edu.utn.tadp.organizables.Reunion;
import ar.edu.utn.tadp.recurso.Persona;

/**
 * Este es un data object que tiene la información necesaria para notificar por catering o transporte.
 * @author clari
 *
 */
public class DataServicioANotificar extends DataNotificacion {

	private String methodName;
	private Persona destinatario;
	private Reunion reunion;
	private String subject;
	private Collection<String> methodNamesForBody;
	
	public DataServicioANotificar(String methodName, Persona destinatario, Reunion reunion, Collection <String> mthsForBody, String subject){
		this.setMethodName(methodName);
		this.destinatario= destinatario;
		this.reunion=reunion;
		this.methodNamesForBody= mthsForBody;
		this.subject=subject;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getSubject() {
		return this.subject;
	}

	public Persona getDestinatario() {
		return this.destinatario;
	}

	public String getDestinatarioAsString(){
		return this.destinatario.toString();
	}
	public String getBody(Notificador notificador) {
		return this.generateBody();
	}

	public Collection<String> getMethodNamesForBody(){
		return this.methodNamesForBody;

			
		}

	public Reunion getReunion() {
		return this.reunion;
	}
	
	public String generateBody() {

		String body = new String();
		for (String methodName: this.getMethodNamesForBody()){
			Method m= this.getMethodWithoutParameters(this.getReunion(), methodName);
			body= body + this.invokeMethodAndReturnString(this.getReunion(), m);
		}
			return body;
		}

	private String invokeMethodAndReturnString(Reunion reunion, Method m) {
	    try{
	    	 return m.invoke(reunion).toString();
	    }
		catch  (IllegalAccessException e){
			throw new ProgramException ("Acceso inválido", e);
		}
		catch (InvocationTargetException e1){
			throw new ProgramException("Objeto receptor no válido", e1);
		}
}
	
}
