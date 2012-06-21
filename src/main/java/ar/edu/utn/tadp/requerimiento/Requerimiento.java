package ar.edu.utn.tadp.requerimiento;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
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
 * @version 21-06-2012
 */
public class Requerimiento {

	private final Collection<Propiedad> condiciones;
	private Collection<Recurso> meSatisfacen;
	private boolean obligatorio = true;
	private Requerimiento requerimientoAlternativo = null;
	private Recurso recursoQueSatisface;

	/**
	 * Crea un <code>Requerimiento</code> en base de las propiedades requeridas.
	 * 
	 * @param condiciones
	 * @see Propiedad
	 */
	public Requerimiento(final Collection<Propiedad> condiciones) {
		this(condiciones, true);
	}

	/**
	 * Crea un <code>Requerimiento</code> en base de las propiedades requeridas
	 * estableciendo si es o no obligatorio. Por defecto es obligatorio.
	 * 
	 * @param condiciones
	 * @param obligatorio
	 * @see Propiedad
	 */
	public Requerimiento(final Collection<Propiedad> condiciones,
			boolean obligatorio) {
		this.condiciones = condiciones;
		this.obligatorio = obligatorio;
	}

	/**
	 * Crea un <code>Requerimiento</code> a partir de un <code>Recurso</code>
	 * 
	 * @param recurso
	 * @see Recurso
	 */
	public Requerimiento(final Recurso recurso) {
		this(getPropiedades(recurso));
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

	public Collection<Recurso> buscaLosQueTeSatisfacen(
			final List<Recurso> recursos) {
		return meSatisfacen = this.filtrarConjunto(recursos);
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
	 * Obtiene las propiedades de un objeto analizando sus atributos.
	 * 
	 * @param objeto
	 *            Un objeto cuyas propiedades van a obtener.
	 * @return Set de <code>Propiedad</code> obtenidas.
	 * @see Propiedad
	 */
	public static Set<Propiedad> getPropiedades(final Object objeto) {
		final Set<Propiedad> propiedades = new HashSet<Propiedad>();
		final ArrayList<Field> lista = new ArrayList<Field>();
		for (final Field field : getAllFields(lista, objeto.getClass())) {
			try {
				field.setAccessible(true);
				final String key = field.getName();
				final String value = field.get(objeto).toString();
				propiedades.add(new Propiedad(key, value));
			} catch (final Exception e) {
				// Si hay un problema, pasa al siguiente atributo.
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
	private static List<Field> getAllFields(List<Field> fields,
			final Class<?> type) {
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

	public Collection<Propiedad> getCondiciones() {
		return condiciones;
	}

	public boolean isObligatorio() {
		return obligatorio;
	}

	public Requerimiento getRequerimientoAlternativo() {
		return requerimientoAlternativo;
	}

	public void setRequerimientoAlternativo(final Requerimiento requerimiento) {
		this.requerimientoAlternativo = requerimiento;
	}

	public Recurso getRecursoQueSatisface() {
		return recursoQueSatisface;
	}

	public void setRecursoQueSatisface(Recurso recurso) {
		this.recursoQueSatisface = recurso;
	}

	public boolean isRecurso() {
		Predicate<Propiedad> pidePersona = new Predicate<Propiedad>() {
			@Override
			public boolean apply(Propiedad cond) {
				// Es de recurso si tiene un "tipo" distinto de "humano"
				return cond.getTipo().toString().toLowerCase().equals("tipo")
						&& !cond.getValor().toString().toLowerCase()
								.equals("humano");
			};
		};

		try {
			Iterables.find(this.getCondiciones(), pidePersona);
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}
}
