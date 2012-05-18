package ar.edu.utn.tadp.criterio;

import java.util.HashSet;
import java.util.Set;

import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Recurso;

/**
 * Especifica las propiedades requeridas de un recurso para una reunion.
 */
public class Requerimiento {
	private int cantidad = 1;
	private Set<Propiedad> propiedades = new HashSet<Propiedad>();

	/**
	 * Verifica si un <code>Recurso</code> cumple con el
	 * <code>Requerimiento</code>
	 * 
	 * @param unRecurso
	 *            Un <code>Recurso</code> a verificar.
	 * @return <code>true</code> en caso de que el <code>Recurso</code> cumpla
	 *         con el <code>Requerimiento</code>, y <code>false</code> en caso
	 *         de que no.
	 */
	public boolean cumple(Recurso unRecurso) {
		return unRecurso.getPropiedades().containsAll(this.getPropiedades());
	}

	/******* Getters y Setters **********/
	public Set<Propiedad> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(Set<Propiedad> propiedades) {
		this.propiedades = propiedades;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
