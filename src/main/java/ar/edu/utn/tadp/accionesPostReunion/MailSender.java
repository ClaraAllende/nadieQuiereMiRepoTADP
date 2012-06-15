package ar.edu.utn.tadp.accionesPostReunion;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.Iterables;

public class MailSender {
	
	protected Collection<Mail> mailsEnviados= new ArrayList<Mail>();

	public void sendMail(Mail mail) {
		this.mailsEnviados.add(mail);
		System.out.println(mail);
	}
	
	public Mail ultimoMailEnviado(){
		return Iterables.getLast(this.mailsEnviados);
	}
}
