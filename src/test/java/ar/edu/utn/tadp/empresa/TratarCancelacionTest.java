package ar.edu.utn.tadp.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.organizables.Reunion;
import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.recurso.roles.Rol;
import ar.edu.utn.tadp.requerimiento.Requerimiento;

public class TratarCancelacionTest {
	private final Propiedad proyectoApollo = new Propiedad("proyecto", "Apollo");
	private final Propiedad proyectoMir = new Propiedad("Proyecto", "Mir");
	private final Propiedad sectorDesarrollo = new Propiedad("Sector",
			"Desarrollo");
	private final Propiedad rolProgramador = new Propiedad("rol", "programador");
	private final Propiedad rolLeader = new Propiedad("rol", "project leader");
	private final Propiedad edificioCatalinas = new Propiedad("edificio",
			"catalinas");
	private final Propiedad edificioMadero = new Propiedad("edificio", "Madero");
	private final Propiedad tipoSala = new Propiedad("tipo", "Sala");
	private final Propiedad tipoProyector = new Propiedad("tipo", "Proyector");

	private Persona programador1;
	private Persona programador2;
	private Persona programador3;
	private Persona arquitecto1;

	private Persona leader;
	private Recurso sala;
	private Recurso proyector1;
	private Recurso proyector2;
	private Empresa unaEmpresa;

	/**
	 * Crea la empresa y carga todos los recursos necesarios para test.
	 */
	@Before
	public void crearContexto() {
		// Personas --------------------------------
		programador1 = new Persona(Rol.PROGRAMADOR);
		programador1.setProyecto(proyectoApollo.getValor());
		programador1.setSector(sectorDesarrollo.getValor());
		programador1.setEdificio(edificioCatalinas.getValor());
		programador1.setNombre("Programador1");
		programador2 = new Persona(Rol.PROGRAMADOR);
		programador2.setProyecto(proyectoApollo.getValor());
		programador2.setSector(sectorDesarrollo.getValor());
		programador2.setEdificio(edificioMadero.getValor());
		programador2.setNombre("Programador2");
		programador3 = new Persona(Rol.PROGRAMADOR);
		programador3.setProyecto(proyectoMir.getValor());
		programador3.setSector(sectorDesarrollo.getValor());
		programador3.setEdificio(edificioMadero.getValor());
		programador3.setNombre("Programador3");
		arquitecto1 = new Persona(Rol.ARQUITECTO);
		arquitecto1.setProyecto(proyectoApollo.getValor());
		arquitecto1.setSector(sectorDesarrollo.getValor());
		arquitecto1.setEdificio(edificioCatalinas.getValor());
		arquitecto1.setNombre("Arquitecto1");
		leader = new Persona(Rol.PROYECT_LEADER);
		leader.setProyecto(proyectoApollo.getValor());
		leader.setEdificio(edificioCatalinas.getValor());
		leader.setNombre("NaNaNaLeader!");
		// Recursos -------------------------------
		sala = new Recurso();
		sala.setTipo(tipoSala.getValor());
		sala.setEdificio(edificioMadero.getValor());
		proyector1 = new Recurso();
		proyector1.setTipo(tipoProyector.getValor());
		proyector1.setEdificio(edificioMadero.getValor());
		proyector2 = new Recurso();
		proyector2.setTipo(tipoProyector.getValor());
		proyector2.setEdificio(edificioCatalinas.getValor());
		// Empresa -------------------------------
		unaEmpresa = new Empresa();
		unaEmpresa.addRecurso(leader);
		unaEmpresa.addRecurso(arquitecto1);
		unaEmpresa.addRecurso(programador1);
		unaEmpresa.addRecurso(programador2);
		unaEmpresa.addRecurso(programador3);
		unaEmpresa.addRecurso(sala);
		unaEmpresa.addRecurso(proyector1);
		unaEmpresa.addRecurso(proyector2);
	}

	@Test
	public void primeroObligatoriedadLuegoAsistenciaNoCancela() {

		// Se definen los requerimiento de asistente y recursos para la reunion.
		final ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		// La asistencia de Programador1 es opcional
		requerimientos.add(getReqProgramador1_Opcional());
		requerimientos.add(getReqProgramador2());
		requerimientos.add(getReqProgramador3());
		requerimientos.add(getReqProyector2_Opcional());

		// Se crea la reunion.
		final Reunion reunion = unaEmpresa.createReunion(arquitecto1,
				requerimientos, Hours.hours(2), DateTime.now().plusDays(1));
		assertFalse(reunion.isCancelada());
		final Interval horarioOriginal = reunion.getHorario();

		// Se agregan los tratamientos.
		reunion.addTratamientoPorObligatoriedad(); // Se soluciona aca!
		reunion.addTratamientoPorReplanificacion(); // Anda, pero aca no llega.
		reunion.addTratamientoPorCriterioAlternativo(); // Esta va a fallar
		reunion.addTratamientoPorAsistenciaMinima(99); // Esta va a fallar

		// Valida que en un principio no esta cancelada (por las dudas...)
		assertFalse(reunion.isCancelada());

		// Se va un asistente opcional.
		unaEmpresa.cancelarParticipacion(programador1, reunion);

		// Valida que la reunion no se cancela
		assertFalse(reunion.isCancelada());

		// NO Se aplico la replanificacion (chequeamos por las dudas).
		assertEquals(horarioOriginal, reunion.getHorario());
	}

	/**
	 * Deberia obtener un Proyector2. Se define que es opcional.
	 */
	private Requerimiento getReqProyector2_Opcional() {
		final ArrayList<Propiedad> condicionesRecurso = new ArrayList<Propiedad>();
		condicionesRecurso.add(edificioCatalinas);
		condicionesRecurso.add(tipoProyector);
		final Requerimiento reqProyector2 = new Requerimiento(
				condicionesRecurso, false);
		return reqProyector2;
	}

	/**
	 * Deberia obtener un Programador3
	 */
	private Requerimiento getReqProgramador3() {
		final ArrayList<Propiedad> condicionesInvitado3 = new ArrayList<Propiedad>();
		condicionesInvitado3.add(proyectoMir);
		condicionesInvitado3.add(edificioMadero);
		condicionesInvitado3.add(rolProgramador);
		final Requerimiento reqProgramador3 = new Requerimiento(
				condicionesInvitado3);
		return reqProgramador3;
	}

	/**
	 * Deberia obtener un Programador2.
	 */
	private Requerimiento getReqProgramador2() {
		final ArrayList<Propiedad> condicionesInvitado2 = new ArrayList<Propiedad>();
		condicionesInvitado2.add(proyectoApollo);
		condicionesInvitado2.add(edificioMadero);
		condicionesInvitado2.add(rolProgramador);
		final Requerimiento reqProgramador2 = new Requerimiento(
				condicionesInvitado2);
		return reqProgramador2;
	}

	/**
	 * Deberia obtener un Programador1. Se define que es opcional.
	 */
	private Requerimiento getReqProgramador1_Opcional() {
		final ArrayList<Propiedad> condicionesInvitado1 = new ArrayList<Propiedad>();
		condicionesInvitado1.add(proyectoApollo);
		condicionesInvitado1.add(edificioCatalinas);
		condicionesInvitado1.add(rolProgramador);
		final Requerimiento reqProgramador1 = new Requerimiento(
				condicionesInvitado1, false);
		return reqProgramador1;
	}

	@Test
	public void primeroAsistenciaLuegoObligatoriedadTampocoCancela() {
		final Requerimiento reqProgramador1 = getReqProgramador1_Opcional();

		final Requerimiento reqProgramador2 = getReqProgramador2();

		final Requerimiento reqProgramador3 = getReqProgramador3();

		final Requerimiento reqProyector2 = getReqProyector2_Opcional();

		final ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(reqProgramador1);
		requerimientos.add(reqProgramador2);
		requerimientos.add(reqProgramador3);
		requerimientos.add(reqProyector2);

		/**************** Hasta aca la preparacion ***********/

		// Se crea la reunion.
		final Reunion reunion = unaEmpresa.createReunion(arquitecto1,
				requerimientos, Hours.hours(20), DateTime.now().plusDays(1));
		assertFalse(reunion.isCancelada());
		final Interval horarioOriginal = reunion.getHorario();

		// Se agregan los tratamientos.
		reunion.addTratamientoPorCriterioAlternativo(); // Esta va a fallar
		reunion.addTratamientoPorAsistenciaMinima(99); // Esta va a fallar
		reunion.addTratamientoPorObligatoriedad(); // Aca se soluciona!
		reunion.addTratamientoPorReplanificacion(); // Aca no llega

		// Se va un asistente.
		unaEmpresa.cancelarParticipacion(programador1, reunion);

		// Valida que la reunion se cancela, Mentira! Asi no tendria logica.
		// assertTrue(reunion.isCancelada());
		// TODO Putear el TP que esta mal redactado. Esto anda!
		assertFalse(reunion.isCancelada());

		// NO Se aplico la replanificacion.
		assertEquals(horarioOriginal, reunion.getHorario());
	}

	@Test
	public void primeroAsistenciaLuegoObligatoriedadYLuegoPorReplanificacionNoCancela() {
		// Deberia obtener un Programador1.
		final ArrayList<Propiedad> condicionesInvitado1 = new ArrayList<Propiedad>();
		condicionesInvitado1.add(proyectoApollo);
		condicionesInvitado1.add(edificioCatalinas);
		condicionesInvitado1.add(rolProgramador);
		final Requerimiento reqProgramador1 = new Requerimiento(
				condicionesInvitado1, true);

		final Requerimiento reqProgramador2 = getReqProgramador2();

		final Requerimiento reqProgramador3 = getReqProgramador3();

		final Requerimiento reqProyector2 = getReqProyector2_Opcional();

		final ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(reqProgramador1);
		requerimientos.add(reqProgramador2);
		requerimientos.add(reqProgramador3);
		requerimientos.add(reqProyector2);

		/**************** Hasta aca la preparacion ***********/

		// Se crea la reunion.
		final Reunion reunion = unaEmpresa.createReunion(arquitecto1,
				requerimientos, Hours.hours(2), DateTime.now().plusDays(1));
		assertFalse(reunion.isCancelada());
		final Interval horarioOriginal = reunion.getHorario();

		// Se agregan los tratamientos.
		reunion.addTratamientoPorCriterioAlternativo(); // Esta va a fallar
		reunion.addTratamientoPorAsistenciaMinima(99); // Esta va a fallar
		reunion.addTratamientoPorObligatoriedad(); // Esta tambien va a fallar
		reunion.addTratamientoPorReplanificacion(); // Aca se soluciona!

		// Se va un asistente.
		unaEmpresa.cancelarParticipacion(programador1, reunion);

		// Valida que la reunion no se cancela
		assertFalse(reunion.isCancelada());
		// Se aplico la replanificacion.
		assertNotSame(horarioOriginal, reunion.getHorario());
	}

	@Test
	public void primeroAsistenciaLuegoObligatoriedadYLuegoPorReplanificacionYCancela() {
		// Deberia obtener un Programador1.
		final ArrayList<Propiedad> condicionesInvitado1 = new ArrayList<Propiedad>();
		condicionesInvitado1.add(proyectoApollo);
		condicionesInvitado1.add(edificioCatalinas);
		condicionesInvitado1.add(rolProgramador);
		final Requerimiento reqProgramador1 = new Requerimiento(
				condicionesInvitado1, true);

		final Requerimiento reqProgramador2 = getReqProgramador2();

		final Requerimiento reqProgramador3 = getReqProgramador3();

		final Requerimiento reqProyector2 = getReqProyector2_Opcional();

		final ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(reqProgramador1);
		requerimientos.add(reqProgramador2);
		requerimientos.add(reqProgramador3);
		requerimientos.add(reqProyector2);

		/**************** Hasta aca la preparacion ***********/

		// Se crea la reunion.
		final Reunion reunion = unaEmpresa.createReunion(arquitecto1,
				requerimientos, Hours.hours(22), DateTime.now().plusDays(1));
		assertFalse(reunion.isCancelada());

		// Se agregan los tratamientos.
		reunion.addTratamientoPorCriterioAlternativo(); // Esta va a fallar
		reunion.addTratamientoPorAsistenciaMinima(99); // Esta va a fallar
		reunion.addTratamientoPorObligatoriedad(); // Esta tambien va a fallar
		reunion.addTratamientoPorReplanificacion(); // Esta tambien va a fallar

		// Se va un asistente.
		unaEmpresa.cancelarParticipacion(programador1, reunion);

		// Valida que la reunion se cancela
		assertTrue(reunion.isCancelada());
	}

	@Test
	public void primeroAsistenciaLuegoObligatoriedadLuegoPorReplanificacionYLuegoPorCriterioAlternativo() {
		// Deberia obtener un Leader.
		final ArrayList<Propiedad> condicionesInvitado1 = new ArrayList<Propiedad>();
		condicionesInvitado1.add(proyectoApollo);
		condicionesInvitado1.add(edificioCatalinas);
		condicionesInvitado1.add(rolLeader);
		final Requerimiento reqLeader = new Requerimiento(condicionesInvitado1,
				true);
		// Alternativa al Leader -> Programador1
		final ArrayList<Propiedad> condicionesAlternativa = new ArrayList<Propiedad>();
		condicionesAlternativa.add(proyectoApollo);
		condicionesAlternativa.add(edificioCatalinas);
		condicionesAlternativa.add(rolProgramador);
		final Requerimiento reqProgramador1 = new Requerimiento(
				condicionesAlternativa, true);

		// Se agrega la alternativa.
		reqLeader.setRequerimientoAlternativo(reqProgramador1);

		final Requerimiento reqProgramador2 = getReqProgramador2();

		final Requerimiento reqProgramador3 = getReqProgramador3();

		final Requerimiento reqProyector2 = getReqProyector2_Opcional();

		final ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(reqLeader);
		requerimientos.add(reqProgramador2);
		requerimientos.add(reqProgramador3);
		requerimientos.add(reqProyector2);

		/**************** Hasta aca la preparacion ***********/

		// Se crea la reunion.
		final Reunion reunion = unaEmpresa.createReunion(arquitecto1,
				requerimientos, Hours.hours(22), DateTime.now().plusDays(1));
		assertFalse(reunion.isCancelada());

		// Se agregan los tratamientos.
		reunion.addTratamientoPorAsistenciaMinima(99); // Esta va a fallar
		reunion.addTratamientoPorObligatoriedad(); // Esta tambien va a fallar
		reunion.addTratamientoPorReplanificacion(); // Esta tambien va a fallar
		reunion.addTratamientoPorCriterioAlternativo(); // Aca se resuelve!

		// Se va un asistente.
		unaEmpresa.cancelarParticipacion(leader, reunion);

		// Valida que la reunion no se cancela
		assertFalse(reunion.isCancelada());
		// NO Se aplico la replanificacion.
		assertEquals(7, reunion.getRecursos().size());
	}
}