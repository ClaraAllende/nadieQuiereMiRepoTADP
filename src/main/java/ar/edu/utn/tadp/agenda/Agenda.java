package ar.edu.utn.tadp.agenda;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Interval;

import ar.edu.utn.tadp.excepcion.UserException;

/**
 * Representa a la agenda que contiene todos los horarios que estan ocupados.
 * 
 * @version 14-06-2012
 */
public class Agenda {

	/*
	 * Constantes
	 */
	public static DateTime FUTURO = new DateTime(java.lang.Long.MAX_VALUE);
	public static DateTime HOY = new DateTime(DateTime.now().getYear(),
			DateTime.now().getMonthOfYear(), DateTime.now().getDayOfMonth(), 0,
			0);

	private final List<Interval> horariosOcupados = new ArrayList<Interval>();

	public List<Interval> getHorariosOcupados() {
		return horariosOcupados;
	}

	public void ocupateDurante(Interval unIntervalo) {
		this.getHorariosOcupados().add(unIntervalo);
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

	public boolean disponibleDurante(Interval unIntervalo) {
		for (Interval intervalo : this.getHorariosOcupados()) {
			if (intervalo.overlaps(unIntervalo))
				return false;
		}
		return true;
	}

	public List<Interval> horariosDisponibles() {
		List<Interval> disponibles = new ArrayList<Interval>();
		Interval intDisponible = new Interval(Agenda.HOY, Agenda.FUTURO);
		disponibles.add(intDisponible);

		if (this.horariosOcupados.isEmpty())
			return disponibles;

		for (Interval intOcupado : this.horariosOcupados) {
			Interval intAuxiliar = new Interval(intDisponible.getStart(),
					intOcupado.getStart());
			disponibles.remove(intDisponible);
			disponibles.add(intAuxiliar);
			intDisponible = intDisponible.withStart(intOcupado.getEnd());
			disponibles.add(intDisponible);
		}
		return disponibles;
	}

	public Interval intervaloDisponibleDe(Duration unaDuracion) {
		for (Interval intervalo : this.horariosDisponibles()) {
			if (intervalo.toDuration().isLongerThan(unaDuracion)) {
				return intervalo;
			}
		}
		throw new UserException(
				"no hay un intervalo disponible de la duracion pedida");
	}

	public boolean tenesDisponibleAntesDe(Hours horas, DateTime vencimiento) {
		Interval interval = this.intervaloDisponibleDe(horas
				.toStandardDuration());
		interval = interval.withEnd(interval.getStart().plus(
				horas.toStandardDuration()));
		return interval.isBefore(vencimiento);
	}

	public boolean estasOcupadoDurante(Interval intervalo) {
		return horariosOcupados.contains(intervalo);
	}
}
