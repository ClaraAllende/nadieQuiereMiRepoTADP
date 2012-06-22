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

public class TratarXCriterioAlternativoTest {
	private final Propiedad proyectoApollo = new Propiedad("proyecto", "Apollo");
	private final Propiedad proyectoMir = new Propiedad("Proyecto", "Mir");
	private final Propiedad sectorDesarrollo = new Propiedad("Sector",
			"Desarrollo");
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
		unaEmpresa.addRecurso(arquitecto1);
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
	public void buscaAlternativaMientrasHaya() {

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

		reqProgramador1.setRequerimientoAlternativo(reqProgramador2);

		// Deberia obtener Proyector2. Se define que es opcional.
		final ArrayList<Propiedad> condicionesRecurso = new ArrayList<Propiedad>();
		condicionesRecurso.add(edificioCatalinas);
		condicionesRecurso.add(tipoProyector);
		final Requerimiento reqProyector2 = new Requerimiento(
				condicionesRecurso, false);

		final ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(reqProgramador1);
		requerimientos.add(reqProyector2);

		// Se crea la reunion.
		final Reunion reunion = unaEmpresa.createReunion(arquitecto1,
				requerimientos, Hours.SEVEN, DateTime.now().plusDays(2));

		// Recursos que deberian ser: anfitrion, 1 invitado, proyector y la
		// sala.
		Assert.assertEquals(4, reunion.getRecursos().size());
		Assert.assertTrue(reunion.getRecursos().contains(arquitecto1));
		Assert.assertTrue(reunion.getRecursos().contains(programador1));
		Assert.assertTrue(reunion.getRecursos().contains(proyector2));
		Assert.assertTrue(reunion.getRecursos().contains(sala));

		// Se agrega un tratamiento
		reunion.addTratamientoPorCriterioAlternativo();

		// Se cancela una asistencia.
		unaEmpresa.cancelarParticipacion(programador1, reunion);

		// La reunion no se cancelo.
		Assert.assertFalse(reunion.isCancelada());

		// Recursos que deberian quedar: anfitrion, 2 invitados, proyector y la
		// sala.
		Assert.assertEquals(4, reunion.getRecursos().size());
		Assert.assertTrue(reunion.getRecursos().contains(arquitecto1));
		Assert.assertTrue(reunion.getRecursos().contains(programador2));
		Assert.assertTrue(reunion.getRecursos().contains(proyector2));
		Assert.assertTrue(reunion.getRecursos().contains(sala));
		// Pero no esta mas el programador1.
		Assert.assertFalse(reunion.getRecursos().contains(programador1));

		// Se cancela una asistencia. Que no tiene alternativas.
		unaEmpresa.cancelarParticipacion(programador2, reunion);

		// La reunion se cancela.
		Assert.assertTrue(reunion.isCancelada());

		// No deberia quedar ningun recurso
		Assert.assertEquals(0, reunion.getRecursos().size());
	}
}
