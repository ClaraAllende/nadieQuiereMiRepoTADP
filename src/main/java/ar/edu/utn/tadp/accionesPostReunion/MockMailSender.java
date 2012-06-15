package ar.edu.utn.tadp.accionesPostReunion;

import com.google.common.collect.Iterables;

public class MockMailSender extends MailSender {

	@Override
	public void sendMail(Mail mail){
		this.mailsEnviados.add(mail);
	}

}
