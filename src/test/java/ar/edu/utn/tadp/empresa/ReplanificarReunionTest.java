package ar.edu.utn.tadp.empresa;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.recurso.roles.Rol;
import ar.edu.utn.tadp.requerimiento.Requerimiento;
import ar.edu.utn.tadp.reunion.Reunion;

public class ReplanificarReunionTest {
	private final Propiedad proyectoApollo = new Propiedad("proyecto", "Apollo");
	private final Propiedad proyectoMir = new Propiedad("Proyecto", "Mir");
	private final Propiedad sectorDesarrollo = new Propiedad("Sector",
			"Desarrollo");
	private final Propiedad edificioCatalinas = new Propiedad("edificio",
			"catalinas");
	private final Propiedad edificioMadero = new Propiedad("edificio", "Madero");
	private final Propiedad tipoSala = new Propiedad("tipo", "Sala");
	private final Propiedad tipoProyector = new Propiedad("tipo", "Proyector");
	private final Propiedad rolProgramador = new Propiedad("rol", "programador");
	private Persona programador1;
	private Persona programador2;
	private Persona programador3;
	private Persona arquitecto1;
	private Recurso sala;
	private Recurso proyector1;
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
		// Empresa
		unaEmpresa = new Empresa();
		unaEmpresa.addRecurso(programador1);
		unaEmpresa.addRecurso(programador2);
		unaEmpresa.addRecurso(programador3);
		unaEmpresa.addRecurso(arquitecto1);
		unaEmpresa.addRecurso(sala);
		unaEmpresa.addRecurso(proyector1);
	}

	@Test
	public void replanificarUnaReunion() {
		final ArrayList<Propiedad> condiciones = new ArrayList<Propiedad>();
		condiciones.add(proyectoApollo);
		condiciones.add(rolProgramador);
		condiciones.add(edificioCatalinas);
		final Requerimiento requerimiento = new Requerimiento(condiciones);

		final ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(requerimiento);

		final Reunion reunion = unaEmpresa.createReunion(arquitecto1,
				requerimientos, Hours.hours(2), DateTime.now().plusDays(1));

		final Interval horarioOriginal = reunion.getHorario();
		// Valida que la reunion no este cancelada y tenga 3 recursos (2
		// participantes y la sala).
		Assert.assertFalse(reunion.isCancelada());
		Assert.assertEquals(3, reunion.getRecursos().size());
		// Valida que todos los recursos esten ocupados.
		Assert.assertTrue(programador1.estasOcupadoDurante(reunion.getHorario()));
		Assert.assertTrue(arquitecto1.estasOcupadoDurante(reunion.getHorario()));
		Assert.assertFalse(sala.disponibleDurante(reunion.getHorario()));

		unaEmpresa.replanificarReunion(reunion);
		// Valida que la reunion nueva no este cancelada y tenga los mismos
		// recursos.
		Assert.assertFalse(reunion.isCancelada());

		// TODO ver que pasa aca?
		// Assert.assertEquals(3, reunion.getRecursos().size());

		// Valida que los recursos esten desocupados para la reunion original.
		Assert.assertFalse(programador1.estasOcupadoDurante(horarioOriginal));
		Assert.assertFalse(arquitecto1.estasOcupadoDurante(horarioOriginal));
		Assert.assertTrue(sala.disponibleDurante(horarioOriginal));

		// Participantes solo esten ocupados para la nueva reunion.
		Assert.assertTrue(programador1.estasOcupadoDurante(reunion.getHorario()));
		Assert.assertTrue(arquitecto1.estasOcupadoDurante(reunion.getHorario()));
		Assert.assertFalse(sala.disponibleDurante(reunion.getHorario()));
	}
}
