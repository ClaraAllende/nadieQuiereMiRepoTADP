package ar.edu.utn.tadp.agenda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;

public class AgendaTest {

	private Interval intervalo1;
	private Interval intervalo2;
	private Interval intervalo3;
	private Interval intervalo4;
	private DateTime fecha1;
	private DateTime fecha2;
	private DateTime fecha3;
	private DateTime fecha4;

	private Agenda agendaTipada;
	private Evento proyeccion;
	private Evento reunion;

	@Before
	public void setUp() {

		fecha1 = new DateTime(2012, 10, 12, 8, 00, 00);
		fecha2 = new DateTime(2012, 10, 12, 12, 30, 00);
		fecha3 = new DateTime(2012, 10, 12, 15, 00, 00);
		fecha4 = new DateTime(2012, 10, 12, 21, 45, 00);

		intervalo1 = new Interval(fecha1, fecha2);
		intervalo2 = new Interval(fecha3, fecha4);
		intervalo3 = new Interval(Agenda.HOY, fecha1);
		intervalo4 = new Interval(fecha2, Agenda.FUTURO);

		agendaTipada = new Agenda();
		proyeccion = new Evento(intervalo1);
		proyeccion.setTipo(TipoEvento.REUNION);
		reunion = new Evento(intervalo2);
		reunion.setTipo(TipoEvento.VACACIONES);

	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ tests AgendaDetallada ++++++++++++++++++++++++++++++++
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@Test
	public void testAgendaOcupate() {
		agendaTipada.ocupate(proyeccion);
		assertTrue(agendaTipada.getHorariosOcupados().contains(intervalo1));
	}

	@Test
	public void testAgendaGetHorariosOcupados() {
		assertEquals(agendaTipada.getHorariosOcupados().size(), 0);
	}

	@Test
	public void testAgendaDisponibleDuranteIntervalo() {
		assertTrue(agendaTipada.disponibleDurante(intervalo2));
	}

	@Test
	public void testAgendaHorariosDisponibles() {
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(intervalo3);
		intervals.add(intervalo4);
		agendaTipada.ocupate(proyeccion);
		assertEquals(agendaTipada.horariosDisponibles(), intervals);
	}

	@Test
	public void testAgendaTenesDisponible() {
		agendaTipada.ocupate(proyeccion);
		Interval interval = agendaTipada.tenesDisponible(intervalo1
				.toDuration());
		assertTrue(interval.equals(intervalo3));
	}

	@Test
	public void testAgendaDesocupate() {
		// Ocupamos.
		agendaTipada.ocupate(proyeccion);
		assertTrue(agendaTipada.estasOcupadoDurante(proyeccion.getIntervalo()));
		// Desocupamos.
		Evento evento = new Evento(proyeccion.getIntervalo());
		evento.setTipo(TipoEvento.REUNION);
		agendaTipada.desocupate(evento);
		assertFalse(agendaTipada.estasOcupadoDurante(proyeccion.getIntervalo()));
	}

	@Test
	public void testAgendaTenesDisponibleAntesDe() {
		agendaTipada.ocupate(reunion);
		assertTrue(agendaTipada.tenesDisponibleAntesDe(Hours.ONE, fecha4));
	}

	@Test
	public void testAgendaHorasEn() {
		agendaTipada.ocupate(reunion);
		agendaTipada.ocupate(proyeccion);

		Hours resultado = agendaTipada.horasEn(TipoEvento.REUNION,
				new DateTime(2012, 10, 10, 0, 00, 00));
		assertEquals(Hours.FOUR, resultado);
		// deberían de ser 3 y media, pero redondea para arriba :)
		// para mas precisión, debemos usar la clase Minute
	}
}
