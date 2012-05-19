package ar.edu.utn.tadp.recurso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Interval;

import ar.edu.utn.tadp.agenda.Agenda;
import ar.edu.utn.tadp.costos.Costeable;
import ar.edu.utn.tadp.costos.Costo;
import ar.edu.utn.tadp.costos.CostoFijo;
import ar.edu.utn.tadp.costos.CostoPorPersona;
import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Representa a todos los recursos de la empresa, tanto humanos como no.
 * 
 * @version 18-05-2012
 */
public class Recurso implements Costeable {
	public static final Recurso CATERING = new Recurso(new CostoFijo(BigDecimal.valueOf(400.00)));
	public static final Recurso TRANSPORTE = new Recurso(new CostoPorPersona(BigDecimal.valueOf(25.0)));
	
	private Agenda agenda = new Agenda();
	// Propiedades de un Recurso serian TipoRecurso y Edificio
	private Set<Propiedad> propiedades = new HashSet<Propiedad>();

	private Costeable costeable;

	public Recurso() {
		this.costeable = Costo.SIN_COSTO;
	}
	
	public Recurso(Costeable costeable) {
		this.costeable = costeable;
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

	@Override
	public BigDecimal dameTuCostoPara(Reunion reunion) {
		return this.costeable.dameTuCostoPara(reunion);
	}

	public void apuntateALaReunion(ArrayList<Recurso> recursos) {
		recursos.add(this);
	}
}
