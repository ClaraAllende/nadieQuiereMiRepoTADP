package ar.edu.utn.tadp.recurso;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import ar.edu.utn.tadp.agenda.Agenda;
import ar.edu.utn.tadp.propiedad.Propiedad;

public class Recurso {
	private Agenda agenda;

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

}
