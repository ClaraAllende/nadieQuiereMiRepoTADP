package ar.edu.utn.tadp.accionesPostReunion;


public class Mail {

	private String subject;
	private String destinatario;
	private String cuerpo;
	private String remitente;

	public Mail (String subject, String destinatario, String remitente,  String cuerpo){
		this.subject= subject;
		this.destinatario = destinatario;
		this.remitente=remitente;
		this.cuerpo= cuerpo;
	}

	public String getBody() {
		return this.cuerpo;
	}
}
