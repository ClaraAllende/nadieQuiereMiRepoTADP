package ar.edu.utn.tadp.empresa;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Interval;

import ar.edu.utn.tadp.agenda.Evento;
import ar.edu.utn.tadp.agenda.TipoEvento;
import ar.edu.utn.tadp.excepcion.UserException;
import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.requerimiento.Requerimiento;
import ar.edu.utn.tadp.reunion.Reunion;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

/**
 * Representa a una Empresa. Contiene todos los recursos.
 * 
 * @version 03-06-2012
 */
public class Empresa {

	private final List<Recurso> recursos = new ArrayList<Recurso>();

	/**
	 * Crea una <code>Reunion</code> en base de los requerimientos.
	 * 
	 * @param anfitrion
	 * @param requerimientos
	 * @param horas
	 * @param vencimiento
	 * @return
	 * @see Reunion
	 * @see Requerimiento
	 */
	public Reunion createReunion(final Persona anfitrion,
			List<Requerimiento> requerimientos, final Hours horas, final DateTime vencimiento) {
		ArrayList<ArrayList<Recurso>> candidatos = new ArrayList<ArrayList<Recurso>>();

		// Si no hay requerimiento de sala, se agrega.
		requerimientos = this.agregarIndispensables(anfitrion, requerimientos);

		this.satisfaceRequerimientos(requerimientos);
		candidatos = seleccionarCandidatos(requerimientos, horas, vencimiento);

		ArrayList<Recurso> asistentes = new ArrayList<Recurso>();
		asistentes = this.seleccionarCandidatos(candidatos);

		/*
		 * Se supone que ocuparAsistente no puede fallar, por eso no va con
		 * try/catch si falla estamos al horno, porque estan dadas las
		 * condiciones para que no falle nunca.
		 */

		Interval intervalo = ocuparAsistentes(horas, asistentes);

		return new Reunion(anfitrion, asistentes, intervalo);
	}

	public void removeRecurso(final Recurso recurso) {
		this.recursos.remove(recurso);
	}

	public void removeAllRecurso() {
		this.recursos.removeAll(this.recursos);
	}

	/*
	 * Metodos privados, auxiliares de generarReunion
	 */

	private boolean todosDisponiblesDurante(
			final ArrayList<Recurso> asistentes, final Interval intervalo) {
		Predicate<? super Recurso> predicate = new Predicate<Recurso>() {

			@Override
			public boolean apply(final Recurso recurso) {
				return recurso.getAgenda().disponibleDurante(intervalo);
			}
		};
		return Iterators.all(asistentes.iterator(), predicate);
	}

	private Interval ocuparAsistentes(final Hours horas, final ArrayList<Recurso> asistentes) {
		Interval intervalo = new Interval(0, 0);

		for (Recurso recurso : asistentes) {
			intervalo = recurso.tenesDisponible(horas
					.toStandardDuration());
			intervalo = intervalo.withEnd(intervalo.getStart().plus(
					horas.toStandardDuration()));
			if (todosDisponiblesDurante(asistentes, intervalo))
				break;
		}
		Evento reunion = new Evento(intervalo);
		reunion.setTipo(TipoEvento.REUNION);
		for (Recurso recurso : asistentes) {
			recurso.ocupate(reunion);
		}
		return intervalo;
	}

	public void addRecurso(final Recurso recurso) {
		this.recursos.add(recurso);
	}

	private ArrayList<ArrayList<Recurso>> seleccionarCandidatos(
			final List<Requerimiento> criterios, final Hours horas,
			final DateTime vencimiento) {
		ArrayList<ArrayList<Recurso>> candidatos = new ArrayList<ArrayList<Recurso>>();

		for (Requerimiento requerimiento : criterios) {
			candidatos.add(requerimiento
					.teSatisfacenDurante(horas, vencimiento));
		}
		return candidatos;
	}

	private void satisfaceRequerimientos(
			final List<Requerimiento> requerimientos) {
		for (Requerimiento requerimiento : requerimientos) {
			requerimiento.buscaLosQueTeSatisfacen(recursos);
		}
	}

	private ArrayList<Recurso> seleccionarCandidatos(
			final ArrayList<ArrayList<Recurso>> candidatos) {

		ArrayList<Recurso> asistentes = new ArrayList<Recurso>();
		for (ArrayList<Recurso> recursos : candidatos) {
			Recurso recurso = recursos.get(0);
			recurso.apuntateALaReunion(asistentes);
		}
		if (asistentes.isEmpty())
			throw new UserException("No hay candidatos disponibles");
		return asistentes;
	}

	/**
	 * Agrega al anfitrion y otros requerimientos indispensables. Ejemplo: sala.
	 * 
	 * @param anfitrion
	 * @param requerimientos
	 * @return
	 */
	private List<Requerimiento> agregarIndispensables(final Persona anfitrion,
			final List<Requerimiento> requerimientos) {
		// Se agregan todas las propiedades de afintrion como un requerimiento.
		requerimientos.add(new Requerimiento(anfitrion));
		// Si no hay un requerimiento de sala, tambien se agrega.
		if (!tieneRequerimientoSala(requerimientos)) {
			ArrayList<Propiedad> condiciones = new ArrayList<Propiedad>();
			condiciones.add(new Propiedad("tipo", "sala"));
			requerimientos.add(new Requerimiento(condiciones));
		}
		// XXX podemos pedir aca el catering para gerente y project leader?
		return requerimientos;
	}

	/**
	 * Se fija si la sala ya fue pedida.
	 * 
	 * @param requerimientos
	 * @return
	 */
	private boolean tieneRequerimientoSala(
			final List<Requerimiento> requerimientos) {
		for (final Requerimiento requerimiento : requerimientos) {
			for (final Propiedad propiedad : requerimiento.getCondiciones()) {
				if (propiedad.getTipo().equals("sala")) {
					return true;
				}
			}
		}
		return false;
	}
}
