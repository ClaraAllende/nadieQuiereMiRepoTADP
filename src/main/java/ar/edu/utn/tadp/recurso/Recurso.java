package ar.edu.utn.tadp.recurso;

<<<<<<< HEAD
import ar.edu.utn.tadp.agenda.*;
import ar.edu.utn.tadp.propiedad.*;
import java.util.List;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import com.google.common.collect.Iterables;


public class Recurso  {
	private Agenda agenda;

=======
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import ar.edu.utn.tadp.agenda.Agenda;
import ar.edu.utn.tadp.propiedad.Propiedad;

/**
 * Representa a todos los recursos de la empresa, tanto humanos como no.
 * 
 * @version 18-05-2012
 */
public class Recurso {
	private Agenda agenda;
>>>>>>> 94ff1662e8f9f70f38ca1e49badecf615bf65d6f
	private BigDecimal costoPorHora = new BigDecimal(0);
	// Propiedades de un Recurso serian TipoRecurso y Edificio
	private Set<Propiedad> propiedades = new HashSet<Propiedad>();

	public BigDecimal getCostoPorHora() {
		return costoPorHora;
	}

<<<<<<< HEAD

=======
>>>>>>> 94ff1662e8f9f70f38ca1e49badecf615bf65d6f
	public void setCostoPorHora(BigDecimal costoPorHora) {
		this.costoPorHora = costoPorHora;
	}

	public Set<Propiedad> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(Set<Propiedad> propiedades) {
		this.propiedades = propiedades;
<<<<<<< HEAD
	}

=======
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
>>>>>>> 94ff1662e8f9f70f38ca1e49badecf615bf65d6f
}
