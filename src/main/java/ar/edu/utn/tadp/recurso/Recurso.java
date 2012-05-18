package ar.edu.utn.tadp.recurso;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.Duration;
import org.joda.time.Interval;

import ar.edu.utn.tadp.propiedad.Propiedad;

/**
 * Representa un recurso de la empresa
 * 
 * @version 17-05-2012
 */
public class Recurso implements Disponible {

	private BigDecimal costoPorHora = new BigDecimal(0);
	// Propiedades de un Recurso serian TipoRecurso y Edificio
	private Set<Propiedad> propiedades = new HashSet<Propiedad>();

	// TODO Falta implementar horariosOcupados

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

	@Override
	public void ocupateDurante(Interval unIntervalo) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean disponibleDurante(Interval unIntervalo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Interval> horariosDisponibles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interval intervaloDisponibleDe(Duration unaDuracion) {
		// TODO Auto-generated method stub
		return null;
	}
}
