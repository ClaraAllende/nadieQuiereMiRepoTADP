package ar.edu.utn.tadp.accionesPostReunion;

import java.util.Collection;

import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Este es un data object que tiene la información necesaria para notificar por catering o transporte.
 * @author clari
 *
 */
public class DataServicioANotificar {

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
		return notificador.generateBody(this);
	}

	public Collection<String> getMethodNamesForBody(){
		return this.methodNamesForBody;

			
		}

	public Reunion getReunion() {
		return this.reunion;
	}
	
}
