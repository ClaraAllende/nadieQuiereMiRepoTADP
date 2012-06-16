package ar.edu.utn.tadp.accionesPostReunion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.agenda.Agenda;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.recurso.roles.Rol;
import ar.edu.utn.tadp.reunion.Reunion;

public class NotificadorTest {
	private final Reunion reunion = mock(Reunion.class);
	private final Persona host = mock(Persona.class);
	private final MailSender mailSender = new MockMailSender();
	private final Notificador notificador = new Notificador(mailSender);
	private final Collection<String> mets = new ArrayList<String>();
	private Interval hoy;
	private Persona dest;

	@Before
	public void setUp() {
		when(reunion.getAnfitrion()).thenReturn(host);
		hoy = new Interval(new DateTime(2012, 6, 15, 0, 0, 0, 0), new DateTime(
				2012, 6, 16, 0, 0, 0, 0));
		dest = mock(Persona.class);
	}

	@Test
	public void testNotificarCatering() {
		mets.add("getHorario");
		mets.add("getCantidadDePersonas");
		when(reunion.tieneCatering()).thenReturn(true);
		when(reunion.getHorario()).thenReturn(hoy);
		when(reunion.getCantidadDePersonas()).thenReturn(5);
		DataServicioANotificar dataCatering = new DataServicioANotificar(
				"tieneCatering", dest, reunion, mets,
				"Solicitud de servicio de catering");
		notificador.notifyService(reunion, dataCatering);
		assertEquals((hoy.toString() + "5"), mailSender.ultimoMailEnviado()
				.getBody());
	}

	@Test
	public void testNotificarTransporte() {
		mets.add("getHorario");
		mets.add("getCantidadDePersonasQueNecesitanTransporte");
		mets.add("getUbicacion");
		when(reunion.requiereTransporte()).thenReturn(true);
		when(reunion.getCantidadDePersonasQueNecesitanTransporte()).thenReturn(
				(long) 6);
		when(reunion.getUbicacion()).thenReturn("Catalinas");
		when(reunion.getHorario()).thenReturn(hoy);
		DataServicioANotificar dataTransporte = new DataServicioANotificar(
				"requiereTransporte", dest, reunion, mets,
				"Solicitud de servicio de transporte");
		notificador.notifyService(reunion, dataTransporte);
		assertEquals((hoy.toString() + "6" + "Catalinas"), mailSender
				.ultimoMailEnviado().getBody());

	}

	@Test
	public void testMandarMailALosEmpleadosPorqueHayCatering() {
		List<Recurso> recs = new ArrayList<Recurso>();
		Persona p1 = new Persona(Rol.DIS_SISTEMAS);
		Persona p2 = new Persona(Rol.PROGRAMADOR);
		p1.setTipo("humano");
		p2.setTipo("humano");
		recs.add(p1);
		recs.add(p2);
		when(reunion.tieneCatering()).thenReturn(true);
		when(reunion.getRecursos()).thenReturn(recs);
		DataNotificacionPersonas data = new DataNotificacionPersonas(
				"tieneCatering", "avisarEmpleados", reunion);
		notificador.notifyPeople(reunion, data);
		assertEquals(2, mailSender.mailsEnviados.size());
	}

	@Test
	public void testLosEmpleadosMarcanElDiaComoOcupadoPorqueHayTransporte() {
		List<Recurso> recs = new ArrayList<Recurso>();
		Persona p1 = new Persona(Rol.DIS_SISTEMAS);
		Persona p2 = new Persona(Rol.PROGRAMADOR);
		p1.setTipo("humano");
		p2.setTipo("humano");
		recs.add(p1);
		recs.add(p2);
		when(reunion.requiereTransporte()).thenReturn(true);
		when(reunion.getRecursos()).thenReturn(recs);
		DataNotificacionPersonas data = new DataNotificacionPersonas(
				"requiereTransporte", "marcarDiaOcupado", reunion);
		notificador.notifyPeople(reunion, data);
		p1.getAgenda();
		assertTrue(p1.estasOcupadoDurante(new Interval(Agenda.HOY, Agenda.HOY)));
		assertTrue(p2.estasOcupadoDurante(new Interval(Agenda.HOY, Agenda.HOY)));
	}
}
