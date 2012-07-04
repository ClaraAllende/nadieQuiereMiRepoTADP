package ar.edu.utn.tadp.tratamiento;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.organizables.Reunion;
import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.recurso.roles.Rol;
import ar.edu.utn.tadp.requerimiento.Requerimiento;

public class TratarXObligatoriedadTest {
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
		unaEmpresa.addRecurso(leader);
		unaEmpresa.addRecurso(arquitecto1);
		unaEmpresa.addRecurso(gerente1);
		unaEmpresa.addRecurso(gerente2);
		unaEmpresa.addRecurso(programador1);
		unaEmpresa.addRecurso(programador2);
		unaEmpresa.addRecurso(programador3);
		unaEmpresa.addRecurso(sala);
		unaEmpresa.addRecurso(proyector1);
		unaEmpresa.addRecurso(proyector2);
	}

	/**
	 * Se quita un recurso que es opcional y la reunion no se cancela.
	 */
	@Test
	public void quitaRecursoOpcional() {
		// Deberia obtener Programador1
		final ArrayList<Propiedad> condicionesInvitado = new ArrayList<Propiedad>();
		condicionesInvitado.add(edificioCatalinas);
		condicionesInvitado.add(rolProgramador);
		final Requerimiento programador = new Requerimiento(condicionesInvitado);

		// Deberia obtener Proyector2. Se define que es opcional.
		final ArrayList<Propiedad> condicionesRecurso = new ArrayList<Propiedad>();
		condicionesRecurso.add(edificioCatalinas);
		condicionesRecurso.add(tipoProyector);
		final Requerimiento recurso = new Requerimiento(condicionesRecurso,
				false);

		final ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(programador);
		requerimientos.add(recurso);

		// Se crea la reunion.
		final Reunion reunion = unaEmpresa.createReunion(arquitecto1,
				requerimientos, Hours.SEVEN, DateTime.now().plusDays(2));

		// Recursos que deberian ser: anfitrion, invitado, proyector y la sala.
		Assert.assertEquals(4, reunion.getRecursos().size());
		Assert.assertTrue(reunion.getRecursos().contains(proyector2));

		// Se agrega un tratamiento
		reunion.addTratamientoPorObligatoriedad();

		// Se cancela un recurso opcional: Proyector2.
		unaEmpresa.cancelarParticipacion(proyector2, reunion);

		// La reunion no se cancelo.
		Assert.assertFalse(reunion.isCancelada());

		// Recursos que deberian quedar: anfitrion, invitado y la sala.
		Assert.assertEquals(3, reunion.getRecursos().size());
		Assert.assertTrue(reunion.getRecursos().contains(arquitecto1));
		Assert.assertTrue(reunion.getRecursos().contains(programador1));
		Assert.assertTrue(reunion.getRecursos().contains(sala));
		// Pero no esta el proyector2.
		Assert.assertFalse(reunion.getRecursos().contains(proyector2));
	}

	/**
	 * Se quita un recurso que es obligatorio y la reunion se cancela.
	 */
	@Test
	public void quitaRecursoObligatorio() {
		// Deberia obtener Programador1
		final ArrayList<Propiedad> condicionesInvitado = new ArrayList<Propiedad>();
		condicionesInvitado.add(edificioCatalinas);
		condicionesInvitado.add(rolProgramador);
		final Requerimiento programador = new Requerimiento(condicionesInvitado);

		// Deberia obtener Proyector2. Se define que es opcional.
		final ArrayList<Propiedad> condicionesRecurso = new ArrayList<Propiedad>();
		condicionesRecurso.add(edificioCatalinas);
		condicionesRecurso.add(tipoProyector);
		final Requerimiento recurso = new Requerimiento(condicionesRecurso,
				false);

		final ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(programador);
		requerimientos.add(recurso);

		// Se crea la reunion.
		final Reunion reunion = unaEmpresa.createReunion(arquitecto1,
				requerimientos, Hours.SEVEN, DateTime.now().plusDays(2));

		// Recursos que deberian ser: anfitrion, invitado, proyector y la sala.
		Assert.assertEquals(4, reunion.getRecursos().size());
		Assert.assertTrue(reunion.getRecursos().contains(proyector2));

		// Se agrega un tratamiento
		reunion.addTratamientoPorObligatoriedad();

		// Se cancela un recurso obligatorio: Programador1.
		unaEmpresa.cancelarParticipacion(programador1, reunion);

		// La reunion se cancelo.
		Assert.assertTrue(reunion.isCancelada());

		// No deberia tener recursos.
		Assert.assertEquals(0, reunion.getRecursos().size());
	}

	/**
	 * Se quita un recurso que es obligatorio y la reunion se cancela.
	 */
	@Test
	public void quitaAnfitrion() {
		// Deberia obtener Programador1
		final ArrayList<Propiedad> condicionesInvitado = new ArrayList<Propiedad>();
		condicionesInvitado.add(edificioCatalinas);
		condicionesInvitado.add(rolProgramador);
		final Requerimiento programador = new Requerimiento(condicionesInvitado);

		// Deberia obtener Proyector2. Se define que es opcional.
		final ArrayList<Propiedad> condicionesRecurso = new ArrayList<Propiedad>();
		condicionesRecurso.add(edificioCatalinas);
		condicionesRecurso.add(tipoProyector);
		final Requerimiento recurso = new Requerimiento(condicionesRecurso,
				false);

		final ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(programador);
		requerimientos.add(recurso);

		// Se crea la reunion.
		final Reunion reunion = unaEmpresa.createReunion(arquitecto1,
				requerimientos, Hours.SEVEN, DateTime.now().plusDays(2));

		// Recursos que deberian ser: anfitrion, invitado, proyector y la sala.
		Assert.assertEquals(4, reunion.getRecursos().size());
		Assert.assertTrue(reunion.getRecursos().contains(proyector2));

		// Se agrega un tratamiento
		reunion.addTratamientoPorObligatoriedad();

		// Se cancela un recurso obligatorio: Arquitecto1.
		unaEmpresa.cancelarParticipacion(arquitecto1, reunion);

		// La reunion se cancelo.
		Assert.assertTrue(reunion.isCancelada());

		// No deberia tener recursos.
		Assert.assertEquals(0, reunion.getRecursos().size());
	}

	/**
	 * Se quita un recurso que es obligatorio y la reunion se cancela.
	 */
	@Test
	public void quitaSala() {
		// Deberia obtener Programador1
		final ArrayList<Propiedad> condicionesInvitado = new ArrayList<Propiedad>();
		condicionesInvitado.add(edificioCatalinas);
		condicionesInvitado.add(rolProgramador);
		final Requerimiento programador = new Requerimiento(condicionesInvitado);

		// Deberia obtener Proyector2. Se define que es opcional.
		final ArrayList<Propiedad> condicionesRecurso = new ArrayList<Propiedad>();
		condicionesRecurso.add(edificioCatalinas);
		condicionesRecurso.add(tipoProyector);
		final Requerimiento recurso = new Requerimiento(condicionesRecurso,
				false);

		final ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(programador);
		requerimientos.add(recurso);

		// Se crea la reunion.
		final Reunion reunion = unaEmpresa.createReunion(arquitecto1,
				requerimientos, Hours.SEVEN, DateTime.now().plusDays(2));

		// Recursos que deberian ser: anfitrion, invitado, proyector y la sala.
		Assert.assertEquals(4, reunion.getRecursos().size());
		Assert.assertTrue(reunion.getRecursos().contains(proyector2));

		// Se agrega un tratamiento
		reunion.addTratamientoPorObligatoriedad();

		// Se cancela un recurso obligatorio: Sala.
		unaEmpresa.cancelarParticipacion(sala, reunion);

		// La reunion se cancelo.
		Assert.assertTrue(reunion.isCancelada());

		// No deberia tener recursos.
		Assert.assertEquals(0, reunion.getRecursos().size());
	}
}
