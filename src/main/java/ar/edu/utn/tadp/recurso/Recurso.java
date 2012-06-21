package ar.edu.utn.tadp.recurso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Interval;

import ar.edu.utn.tadp.agenda.Agenda;
import ar.edu.utn.tadp.agenda.Evento;
import ar.edu.utn.tadp.agenda.TipoEvento;
import ar.edu.utn.tadp.costos.Costeable;
import ar.edu.utn.tadp.costos.Costo;
import ar.edu.utn.tadp.costos.CostoFijo;
import ar.edu.utn.tadp.costos.CostoPorPersona;
import ar.edu.utn.tadp.organizables.Organizable;
import ar.edu.utn.tadp.organizables.Reunion;
import ar.edu.utn.tadp.propiedad.Propiedad;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Representa a los recursos de la empresa.
 * 
 * @version 03-06-2012
 */
public class Recurso {
	// TODO Ver si se puede mejorar eso.
	public static final Recurso CATERING = new Recurso(new CostoFijo(
			BigDecimal.valueOf(400.00)));
	public static final Recurso TRANSPORTE = new Recurso(new CostoPorPersona(
			BigDecimal.valueOf(25.0)));

	// Propiedades de un Recurso serian TipoRecurso y Edificio
	protected String tipo;
	protected String edificio;

	private final Agenda agenda = new Agenda();

	private final Costeable costeable;

	public Recurso() {
		this.costeable = Costo.SIN_COSTO;
	}

	public Recurso(final Costeable costeable) {
		this.costeable = costeable;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public boolean tenesDisponibleAntesDe(final Hours horas,
			final DateTime vencimiento) {
		return this.agenda.tenesDisponibleAntesDe(horas, vencimiento);
	}

	public void ocupate(final Evento evento) {
		this.agenda.ocupate(evento);
	}

	public boolean disponibleDurante(final Interval intervalo) {
		return this.agenda.disponibleDurante(intervalo);
	}

	public Interval tenesDisponible(final Duration standardDuration) {
		return this.agenda.tenesDisponible(standardDuration);
	}

	public BigDecimal dameTuCostoPara(final Organizable reunion) {
		return this.costeable.dameTuCostoPara(reunion);
	}

	public void apuntateALaReunion(final ArrayList<Recurso> recursos) {
		recursos.add(this);
	}

	/**
	 * Devuelve los atributos en formato de <code>String</code>. Se usara en los
	 * test.
	 */
	@Override
	public String toString() {
		return "Recurso: " + tipo + " - " + edificio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(final String edificio) {
		this.edificio = edificio;
	}

	public Estado getEstado() {
		return Estado.POCAS_REUNIONES;
	}

	public String getUbicacion() {
		return this.edificio;
	}

	public int getHorasEnReunionesDeLaSemana() {
		// Los recursos siempre están disponibles
		return 0;
	}

	public Hours horasEn(List<TipoEvento> unosEventos, DateTime fechaLimite) {
		return this.agenda.horasEn(unosEventos, fechaLimite);
		// un recurso no lleva el tiempo ocupado...
	}

	public boolean tenesLaPropiedad(final Propiedad propiedad) {

		Predicate<? super Propiedad> mismaPropiedad = new Predicate<Propiedad>() {

			@Override
			public boolean apply(Propiedad unaPropiedad) {
				return propiedad.equals(unaPropiedad);
			}

		};
		return Iterables.any(RecursoIntrospector.getPropiedadesDe(this),
				mismaPropiedad);
	}

	/**
	 * Libera la agenda ya que se cancelo una reunion.
	 * 
	 * @param reunion
	 *            <code>Reunion</code> que se cancelo.
	 */
	public void cancelarReunion(Reunion reunion) {
		agenda.desocupate(new Evento(reunion.getHorario()));
	}
}
