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

	public  void notifyService( DataServicioANotificar data){
		Method m;
		m = data.getMethodWithoutParameters(data.getReunion(), data.getMethodName());
			if(data.invokeBooleanMethod(data.getReunion(), m)){
				this.mailSender.sendMail(new Mail(data.getSubject(), data.getDestinatarioAsString(), data.getReunion().getOrganizador().toString(), data.getBody(this)));
						}
	}


	public void notifyPeople(DataNotificacionPersonas data){
		Method m = data.getMethodWithoutParameters(data.getReunion(), data.getMethodName());
		if (data.invokeBooleanMethod(data.getReunion(), m)){
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
		}

