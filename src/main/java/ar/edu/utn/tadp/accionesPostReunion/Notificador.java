package ar.edu.utn.tadp.accionesPostReunion;

import java.lang.reflect.*;

import org.joda.time.Interval;

import ar.edu.utn.tadp.agenda.Agenda;
import ar.edu.utn.tadp.agenda.Evento;
import ar.edu.utn.tadp.agenda.TipoEvento;
import ar.edu.utn.tadp.excepcion.ProgramException;
import ar.edu.utn.tadp.organizables.Reunion;
import ar.edu.utn.tadp.recurso.Recurso;

public class Notificador {
	private MailSender mailSender;
	
	public Notificador(MailSender mailSender){
		this.mailSender= mailSender;
	}

	public  void notifyService(Reunion unaReunion, DataServicioANotificar data){
		Method m;
		m = this.getMethodWithoutParameters(unaReunion, data.getMethodName());
			if(invokeBooleanMethod(unaReunion, m)){
				this.mailSender.sendMail(new Mail(data.getSubject(), data.getDestinatarioAsString(), unaReunion.getOrganizador().toString(), data.getBody(this)));
						}
	}
	
	

	public String generateBody(DataServicioANotificar dataServicioANotificar) {
//eventualmente quizás estaría bueno tener un builder para hacer esto
		String body = new String();
		for (String methodName: dataServicioANotificar.getMethodNamesForBody()){
			Method m= this.getMethodWithoutParameters(dataServicioANotificar.getReunion(), methodName);
			body= body + this.invokeMethodAndReturnString(dataServicioANotificar.getReunion(), m);
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

	public void notifyPeople(Reunion unaReunion, DataNotificacionPersonas data){
		Method m = this.getMethodWithoutParameters(unaReunion, data.getMethodName());
		if (this.invokeBooleanMethod(unaReunion, m)){
			data.getAction(this);
		}
	}

		public void avisarEmpleados(Reunion reunion){
			for(Recurso recurso: reunion.getRecursos()){  
				if (recurso.getTipo().equals("humano")){
			this.mailSender.sendMail(new Mail("Convocatoria a reunion con catering",recurso.toString(), reunion.getOrganizador().getNombre(), "Para la próxima reunión contratamos catering, manejenloN :P"));
					}
				}				
			}
		
		public void marcarDiaOcupado (Reunion reunion){
			for(Recurso recurso: reunion.getRecursos()){
				if(recurso.getTipo().equals("humano")){
					Interval intervalo= new Interval(Agenda.HOY, Agenda.HOY);
					Evento ev= new Evento(intervalo);
					ev.setTipo(TipoEvento.DIACOMPLETO);
					recurso.ocupate(ev);
				}
			}
		}
/**
 * Método extraído para evitar manejar las excepciones chequeadas en el if de la funcion notifyService()
 * @param unaReunion
 * @param m
 * @return
 */
	public Boolean invokeBooleanMethod(Reunion unaReunion, Method m){
		try {
			return (Boolean) m.invoke(unaReunion);
		}
		catch  (IllegalAccessException e){
			throw new ProgramException ("Acceso inválido", e);
		}
		catch (InvocationTargetException e1){
			throw new ProgramException("Objeto receptor no válido", e1);
		}
	}

	/**
	 * Método extraído para que no quedara todo el manejo de las excpeciones chequeadas en la
	 * función principal
	 * @param unaReunion
	 * @param aMethodName
	 * @return El método (que retorna un boolean) que hay que usar como condicion para notificar.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public Method getMethodWithoutParameters(Reunion unaReunion, String aMethodName) {
		try{
			return unaReunion.getClass().getMethod(aMethodName);
		}
		catch(SecurityException e){
				throw new ProgramException(e);
			}
		catch(NoSuchMethodException e1){
			throw new ProgramException("El método no está definido", e1);
		}
	
	}
}
