package ar.edu.utn.tadp.recurso;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Interval;

import ar.edu.utn.tadp.agenda.Agenda;
import ar.edu.utn.tadp.propiedad.Propiedad;

/**
 * Representa a todos los recursos de la empresa, tanto humanos como no.
 * 
 * @version 18-05-2012
 */
public class Recurso {
	private Agenda agenda = new Agenda();
	private BigDecimal costoPorHora = new BigDecimal(0);
	// Propiedades de un Recurso serian TipoRecurso y Edificio
	private Set<Propiedad> propiedades = new HashSet<Propiedad>();

	public BigDecimal getCostoPorHora() {
		return costoPorHora;
	}

	public void setCostoPorHora(BigDecimal costoPorHora) {
		this.costoPorHora = costoPorHora;
	}

	public Set<Propiedad> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(Set<Propiedad> propiedades) {
		this.propiedades = propiedades;
	}

	public void addPropiedad(Propiedad propiedad) {
		this.propiedades.add(propiedad);
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public boolean tenesDisponibleAntesDe(Hours horas, DateTime vencimiento) {
		return this.agenda.tenesDisponibleAntesDe(horas, vencimiento);
	}

	public void ocupateDurante(Interval intervalo) {
		this.agenda.ocupateDurante(intervalo);
	}

	public boolean disponibleDurante(Interval intervalo) {
		return this.agenda.disponibleDurante(intervalo);
	}

	public Interval intervaloDisponibleDe(Duration standardDuration) {
		return this.agenda.intervaloDisponibleDe(standardDuration);
	}
}
