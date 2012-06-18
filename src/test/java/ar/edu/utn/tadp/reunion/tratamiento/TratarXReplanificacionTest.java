package ar.edu.utn.tadp.reunion.tratamiento;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.recurso.roles.Rol;
import ar.edu.utn.tadp.requerimiento.Requerimiento;
import ar.edu.utn.tadp.reunion.Reunion;

public class TratarXReplanificacionTest {
	private final Propiedad proyectoApollo = new Propiedad("proyecto", "Apollo");
	private final Propiedad proyectoMir = new Propiedad("Proyecto", "Mir");
	private final Propiedad proyectoShuttle = new Propiedad("Proyecto",
			"shuttle");
	private final Propiedad sectorDesarrollo = new Propiedad("Sector",
			"Desarrollo");
	private final Propiedad sectorGerencia = new Propiedad("sector", "gerencia");
	private final Propiedad rolProgramador = new Propiedad("rol", "programador");
	private final Propiedad edificioCatalinas = new Propiedad("edificio",
			"catalinas");
	private final Propiedad edificioMadero = new Propiedad("edificio", "Madero");
	private final Propiedad tipoSala = new Propiedad("tipo", "Sala");
	private final Propiedad tipoProyector = new Propiedad("tipo", "Proyector");

	private Persona programador1;
	private Persona programador2;
	private Persona programador3;
	private Persona arquitecto1;
	private Persona gerente1;
	private Persona gerente2;
	private Persona gerente3;
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
		// Personas
		programador1 = new Persona(Rol.PROGRAMADOR);
		programador1.setProyecto(proyectoApollo.getValor());
		programador1.setSector(sectorDesarrollo.getValor());
		programador1.setEdificio(edificioCatalinas.getValor());
		programador2 = new Persona(Rol.PROGRAMADOR);
		programador2.setProyecto(proyectoApollo.getValor());
		programador2.setSector(sectorDesarrollo.getValor());
		programador2.setEdificio(edificioMadero.getValor());
		programador3 = new Persona(Rol.PROGRAMADOR);
		programador3.setProyecto(proyectoMir.getValor());
		programador3.setSector(sectorDesarrollo.getValor());
		programador3.setEdificio(edificioMadero.getValor());
		arquitecto1 = new Persona(Rol.ARQUITECTO);
		arquitecto1.setProyecto(proyectoApollo.getValor());
		arquitecto1.setSector(sectorDesarrollo.getValor());
		arquitecto1.setEdificio(edificioCatalinas.getValor());
		gerente1 = new Persona(Rol.GERENTE);
		gerente1.setProyecto(proyectoApollo.getValor());
		gerente1.setSector(sectorGerencia.getValor());
		gerente1.setEdificio(edificioMadero.getValor());
		gerente2 = new Persona(Rol.GERENTE);
		gerente2.setProyecto(proyectoMir.getValor());
		gerente2.setSector(sectorGerencia.getValor());
		gerente2.setEdificio(edificioMadero.getValor());
		gerente3 = new Persona(Rol.GERENTE);
		gerente3.setProyecto(proyectoShuttle.getValor());
		gerente3.setSector(sectorGerencia.getValor());
		gerente3.setEdificio(edificioMadero.getValor());
		leader = new Persona(Rol.PROYECT_LEADER);
		leader.setProyecto(proyectoShuttle.getValor());
		leader.setEdificio(edificioMadero.getValor());
		// Recursos
		sala = new Recurso();
		sala.setTipo(tipoSala.getValor());
		sala.setEdificio(edificioMadero.getValor());
		proyector1 = new Recurso();
		proyector1.setTipo(tipoProyector.getValor());
		proyector1.setEdificio(edificioMadero.getValor());
		proyector2 = new Recurso();
		proyector2.setTipo(tipoProyector.getValor());
		proyector2.setEdificio(edificioCatalinas.getValor());
		// Empresa
		unaEmpresa = new Empresa();
		// unaEmpresa.addRecurso(leader);
		unaEmpresa.addRecurso(arquitecto1);
		// unaEmpresa.addRecurso(gerente1);
		// unaEmpresa.addRecurso(gerente2);
		unaEmpresa.addRecurso(programador1);
		unaEmpresa.addRecurso(programador2);
		unaEmpresa.addRecurso(programador3);
		unaEmpresa.addRecurso(sala);
		// unaEmpresa.addRecurso(proyector1);
		unaEmpresa.addRecurso(proyector2);
	}

	@Test
	public void puedeReplanificar() {
		// Deberia obtener un Programador1
		final ArrayList<Propiedad> condicionesInvitado1 = new ArrayList<Propiedad>();
		condicionesInvitado1.add(proyectoApollo);
		condicionesInvitado1.add(edificioCatalinas);
		condicionesInvitado1.add(rolProgramador);
		final Requerimiento reqProgramador1 = new Requerimiento(
				condicionesInvitado1);

		// Deberia obtener un Programador2
		final ArrayList<Propiedad> condicionesInvitado2 = new ArrayList<Propiedad>();
		condicionesInvitado2.add(proyectoApollo);
		condicionesInvitado2.add(edificioMadero);
		condicionesInvitado2.add(rolProgramador);
		final Requerimiento reqProgramador2 = new Requerimiento(
				condicionesInvitado2);

		// Deberia obtener un Programador3
		final ArrayList<Propiedad> condicionesInvitado3 = new ArrayList<Propiedad>();
		condicionesInvitado3.add(proyectoMir);
		condicionesInvitado3.add(edificioMadero);
		condicionesInvitado3.add(rolProgramador);
		final Requerimiento reqProgramador3 = new Requerimiento(
				condicionesInvitado3);

		// Deberia obtener Proyector2. Se define que es opcional.
		final ArrayList<Propiedad> condicionesRecurso = new ArrayList<Propiedad>();
		condicionesRecurso.add(edificioCatalinas);
		condicionesRecurso.add(tipoProyector);
		final Requerimiento reqProyector2 = new Requerimiento(
				condicionesRecurso, false);

		final ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(reqProgramador1);
		requerimientos.add(reqProgramador2);
		requerimientos.add(reqProgramador3);
		requerimientos.add(reqProyector2);

		/**************** Hasta aca la preparacion ***********/

		// Se crea la reunion.
		final Reunion reunion = unaEmpresa.createReunion(arquitecto1,
				requerimientos, Hours.hours(2), DateTime.now().plusDays(1));
		// Se agrega el tratamiento.
		reunion.addTratamientoPorReplanificacion();

		final Interval horarioOriginal = reunion.getHorario();
		// Valida que la reunion no este cancelada y tenga 3 recursos (2
		// participantes y la sala).
		Assert.assertFalse(reunion.isCancelada());
		Assert.assertEquals(6, reunion.getRecursos().size());
		// Valida que todos los recursos esten ocupados.
		Assert.assertTrue(programador1.estasOcupadoDurante(reunion.getHorario()));
		Assert.assertTrue(programador2.estasOcupadoDurante(reunion.getHorario()));
		Assert.assertTrue(programador3.estasOcupadoDurante(reunion.getHorario()));
		Assert.assertTrue(arquitecto1.estasOcupadoDurante(reunion.getHorario()));
		Assert.assertFalse(sala.disponibleDurante(reunion.getHorario()));

		// Se va un asistente.
		unaEmpresa.cancelarParticipacion(programador1, reunion);

		// Valida que la reunion nueva no este cancelada
		Assert.assertFalse(reunion.isCancelada());

		// Valida que los recursos esten desocupados para la reunion original.
		Assert.assertFalse(programador1.estasOcupadoDurante(horarioOriginal));
		Assert.assertFalse(arquitecto1.estasOcupadoDurante(horarioOriginal));
		Assert.assertTrue(sala.disponibleDurante(horarioOriginal));

		// Participantes solo esten ocupados para la nueva reunion.
		Assert.assertTrue(programador1.estasOcupadoDurante(reunion.getHorario()));
		Assert.assertTrue(arquitecto1.estasOcupadoDurante(reunion.getHorario()));
	}

	@Test
	public void noPuedeReplanificar() {
		// Deberia obtener un Programador1
		final ArrayList<Propiedad> condicionesInvitado1 = new ArrayList<Propiedad>();
		condicionesInvitado1.add(proyectoApollo);
		condicionesInvitado1.add(edificioCatalinas);
		condicionesInvitado1.add(rolProgramador);
		final Requerimiento reqProgramador1 = new Requerimiento(
				condicionesInvitado1);

		// Deberia obtener un Programador2
		final ArrayList<Propiedad> condicionesInvitado2 = new ArrayList<Propiedad>();
		condicionesInvitado2.add(proyectoApollo);
		condicionesInvitado2.add(edificioMadero);
		condicionesInvitado2.add(rolProgramador);
		final Requerimiento reqProgramador2 = new Requerimiento(
				condicionesInvitado2);

		// Deberia obtener un Programador3
		final ArrayList<Propiedad> condicionesInvitado3 = new ArrayList<Propiedad>();
		condicionesInvitado3.add(proyectoMir);
		condicionesInvitado3.add(edificioMadero);
		condicionesInvitado3.add(rolProgramador);
		final Requerimiento reqProgramador3 = new Requerimiento(
				condicionesInvitado3);

		// Deberia obtener Proyector2. Se define que es opcional.
		final ArrayList<Propiedad> condicionesRecurso = new ArrayList<Propiedad>();
		condicionesRecurso.add(edificioCatalinas);
		condicionesRecurso.add(tipoProyector);
		final Requerimiento reqProyector2 = new Requerimiento(
				condicionesRecurso, false);

		final ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(reqProgramador1);
		requerimientos.add(reqProgramador2);
		requerimientos.add(reqProgramador3);
		requerimientos.add(reqProyector2);

		/**************** Hasta aca la preparacion ***********/

		// Se crea la reunion.
		final Reunion reunion = unaEmpresa.createReunion(arquitecto1,
				requerimientos, Hours.hours(22), DateTime.now().plusDays(1));
		// Se agrega el tratamiento.
		reunion.addTratamientoPorReplanificacion();

		final Interval horarioOriginal = reunion.getHorario();
		// Valida que la reunion no este cancelada y tenga 3 recursos (2
		// participantes y la sala).
		Assert.assertFalse(reunion.isCancelada());
		Assert.assertEquals(6, reunion.getRecursos().size());
		// Valida que todos los recursos esten ocupados.
		Assert.assertTrue(programador1.estasOcupadoDurante(reunion.getHorario()));
		Assert.assertTrue(programador2.estasOcupadoDurante(reunion.getHorario()));
		Assert.assertTrue(programador3.estasOcupadoDurante(reunion.getHorario()));
		Assert.assertTrue(arquitecto1.estasOcupadoDurante(reunion.getHorario()));
		Assert.assertFalse(sala.disponibleDurante(reunion.getHorario()));

		// Se va un asistente.
		unaEmpresa.cancelarParticipacion(programador1, reunion);

		// Valida que la reunion nueva este cancelada
		Assert.assertTrue(reunion.isCancelada());

		// Valida que no tenga recursos
		Assert.assertEquals(0, reunion.getRecursos().size());

		// Valida que los recursos esten desocupados para la reunion original.
		Assert.assertFalse(programador1.estasOcupadoDurante(horarioOriginal));
		Assert.assertFalse(arquitecto1.estasOcupadoDurante(horarioOriginal));
		Assert.assertTrue(sala.disponibleDurante(horarioOriginal));
	}
}