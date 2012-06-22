package ar.edu.utn.tadp.accionesPostReunion;

public class MockMailSender extends MailSender {

	@Override
	public void sendMail(Mail mail) {
		this.mailsEnviados.add(mail);
	}

}
