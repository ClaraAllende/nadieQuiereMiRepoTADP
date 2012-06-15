package ar.edu.utn.tadp.agenda;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.joda.time.ReadableDuration;

import ar.edu.utn.tadp.excepcion.UserException;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class Agenda {

	/*
	 * Constantes
	 */
	public static DateTime FUTURO = new DateTime(java.lang.Long.MAX_VALUE);
	public static DateTime HOY = new DateTime(DateTime.now().getYear(),
			DateTime.now().getMonthOfYear(), DateTime.now().getDayOfMonth(), 0,
			0);

	private final List<Evento> eventos = new ArrayList<Evento>();

	public void ocupate(Evento evento) {
		this.eventos.add(evento);
	}

	public boolean disponibleDurante(Interval unIntervalo) {
		for (Interval intervalo : this.getHorariosOcupados()) {
			if (intervalo.overlaps(unIntervalo))
				return false;
		}
		return true;
	}

	public List<Interval> getHorariosOcupados() {
		Function<Evento, Interval> function = new Function<Evento, Interval>() {

			@Override
			public Interval apply(Evento evento) {
				return evento.getIntervalo();
			}
		};
		return Lists.transform(this.eventos, function);
	}

	public List<Interval> horariosDisponibles() {
		List<Interval> disponibles = new ArrayList<Interval>();
		Interval intDisponible = new Interval(Agenda.HOY, Agenda.FUTURO);
		disponibles.add(intDisponible);

		if (this.getHorariosOcupados().isEmpty())
			return disponibles;

		for (Interval intOcupado : this.getHorariosOcupados()) {
			Interval intAuxiliar = new Interval(intDisponible.getStart(),
					intOcupado.getStart());
			disponibles.remove(intDisponible);
			disponibles.add(intAuxiliar);
			intDisponible = intDisponible.withStart(intOcupado.getEnd());
			disponibles.add(intDisponible);
		}
		return disponibles;
	}

	public Interval tenesDisponible(Duration unaDuracion) {
		for (Interval intervalo : this.horariosDisponibles()) {
			if (intervalo.toDuration().isLongerThan(unaDuracion)) {
				return intervalo;
			}
		}
		throw new UserException(
				"no hay un intervalo disponible de la duracion pedida");
	}

	public boolean tenesDisponibleAntesDe(Hours horas, DateTime vencimiento) {
		Interval interval = this.tenesDisponible(horas.toStandardDuration());
		interval = interval.withEnd(interval.getStart().plus(
				horas.toStandardDuration()));
		return interval.isBefore(vencimiento);
	}

	public boolean estasOcupadoDurante(Interval intervalo) {
		return getHorariosOcupados().contains(intervalo);
	}

	public Hours horasEn(TipoEvento reunion, DateTime fechaLimite) {
		Iterable<Evento> todasLasReuniones = eventosDeTipo(reunion,
				this.eventos);
		Iterable<Evento> reunionesDeLaSemana = eventosAntesDe(fechaLimite,
				todasLasReuniones);
		ArrayList<Evento> arrayList = Lists.newArrayList(reunionesDeLaSemana);
		return cantidadDeHoras(arrayList);
	}

	private Hours cantidadDeHoras(Iterable<Evento> reunionesDeLaSemana) {
		Duration duracion = new Duration(0);
		for (Evento evento : reunionesDeLaSemana) {
			ReadableDuration unaDuracion = evento.getDuracion();
			Duration d2 = duracion.plus(unaDuracion);
			duracion = d2;
		}
		return duracion.toStandardHours();
	}

	private Iterable<Evento> eventosAntesDe(final DateTime fechaLimite,
			Iterable<Evento> eventos) {
		Predicate<Evento> entreLimiteYHoy = new Predicate<Evento>() {

			@Override
			public boolean apply(Evento evento) {
				return fechaLimite.isBefore(evento.getFechaInicio());
			}
		};
		return Iterables.filter(eventos, entreLimiteYHoy);
	}

	private Iterable<Evento> eventosDeTipo(final TipoEvento tipoEvento,
			Iterable<Evento> eventos) {
		Predicate<Evento> predicate = new Predicate<Evento>() {

			@Override
			public boolean apply(Evento evento) {
				return evento.getTipo().equals(tipoEvento);
			}

		};
		return Iterables.filter(eventos, predicate);
	}

	/**
	 * Quita un horario de los horarios ocupados.
	 * 
	 * @param horario
	 *            <code>Interval</code> de tiempo que se libera.
	 * @see Interval
	 */
	public void desocupateDurante(Interval horario) {
		this.getHorariosOcupados().remove(horario);
	}
}
