package ar.edu.utn.tadp.requerimiento;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Hours;

import ar.edu.utn.tadp.excepcion.UserException;
import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Recurso;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Representa un objeto que sabe filtrar a los recursos. Y tiene cargadas las
 * condiciones.
 * 
 * @version 30-05-2012
 */
public class Requerimiento {

	private Collection<Propiedad> condiciones;
	private Collection<Recurso> meSatisfacen;

	public Requerimiento(final Collection<Propiedad> condiciones) {
		this.condiciones = condiciones;
	}

	/**
	 * @param recursos
	 *            una coleccion de <code>Recurso</code>
	 * @return una colección de Iterables con los elementos que cumplen con el
	 *         criterio
	 */
	public Collection<Recurso> filtrarConjunto(
			final Collection<Recurso> recursos) {
		final Requerimiento self = this;
		final Predicate<Recurso> p = new Predicate<Recurso>() {
			@Override
			public boolean apply(final Recurso unRecurso) {
				return self.cumpleCondiciones(unRecurso);
			}
		};
		return Sets.newHashSet(Iterables.filter(recursos, p));
	}

	/**
	 * Valida las condiciones requeridas con las propiedades de un recurso.
	 * 
	 * @param unRecurso
	 *            <code>Recurso</code> a validar.
	 * @return verdadero si todas las condiciones estan incluidas entre las
	 *         propiedades del recurso, que tendra otras mas.
	 * @see Recurso
	 * @see Propiedad
	 */
	public boolean cumpleCondiciones(final Recurso unRecurso) {
		final Recurso recursoActual = unRecurso;
		final Predicate<Propiedad> p = new Predicate<Propiedad>() {
			@Override
			public boolean apply(final Propiedad propiedad) {
				// XXX lo hago a mano por que con Iterables no funciona.
				for (final Propiedad elemento : getPropiedades(recursoActual)) {
					if (propiedad.equals(elemento)) {
						return true;
					}
				}
				return false;
				// return Iterables.contains(getPropiedades(recursoActual),
				// propiedad);
			}
		};
		return Iterables.all(this.condiciones, p);
	}

	public void agregarCondiciones(final Set<Propiedad> unasCondiciones) {
		this.condiciones = unasCondiciones;
	}

	public void buscaLosQueTeSatisfacen(final List<Recurso> recursos) {
		meSatisfacen = this.filtrarConjunto(recursos);

	}

	public ArrayList<Recurso> teSatisfacenDurante(final Hours horas,
			final DateTime vencimiento) {
		final ArrayList<Recurso> recursos = new ArrayList<Recurso>();
		for (final Recurso recurso : meSatisfacen) {
			if (recurso.tenesDisponibleAntesDe(horas, vencimiento))
				recursos.add(recurso);
		}
		if (recursos.isEmpty())
			throw new UserException(
					"No hay recurso que satisfaga este requerimiento");
		return recursos;
	}

	/**
	 * Obtiene las propiedades de un <code>Recurso</code> analizando sus
	 * atributos.
	 * 
	 * @param unRecurso
	 *            <code>Recurso</code> cuyas propiedades van a obtener.
	 * @return Set de <code>Propiedad</code> obtenidas.
	 * @see Propiedad
	 */
	private Set<Propiedad> getPropiedades(final Recurso unRecurso) {
		final Set<Propiedad> propiedades = new HashSet<Propiedad>();
		// final Field[] fields = unRecurso.getClass().getDeclaredFields();
		final ArrayList<Field> fields = new ArrayList<Field>();
		for (final Field field : this
				.getAllFields(fields, unRecurso.getClass())) {
			try {
				final String key = field.getName();
				final String value = field.get(unRecurso).toString();
				propiedades.add(new Propiedad(key, value));
			} catch (final Exception e) {
				// Si hay algun problema pasa al siguiente atributo.
				continue;
			}
		}
		return propiedades;
	}

	/**
	 * Metodo recursivo que obtiene todos los <code>Field</code> de una clase
	 * dada, incluyendo los heredados.
	 * 
	 * @param fields
	 *            <code>List</code> para llenar.
	 * @param type
	 *            <code>Class</code> de la cual se obtendran los atributos.
	 * @return Lista de <code>Field</code> ontenidos.
	 * @see Field
	 */
	public List<Field> getAllFields(List<Field> fields, final Class<?> type) {
		// Obtiene atributos propios.
		for (final Field field : type.getDeclaredFields()) {
			fields.add(field);
		}
		if (type.getSuperclass() != null) {
			// Obtiene atributos del padre.
			fields = getAllFields(fields, type.getSuperclass());
		}
		return fields;
	}
}
