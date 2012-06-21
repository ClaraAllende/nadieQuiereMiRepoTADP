package ar.edu.utn.tadp.organizables;

import java.util.ArrayList;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.agenda.Agenda;
import ar.edu.utn.tadp.organizables.Reunion;
import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.recurso.roles.Rol;
import ar.edu.utn.tadp.requerimiento.Requerimiento;

public class ReunionTest {
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
	private Recurso proyector2;

	/**
	 * Crea los recursos necesarios para test.
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
		proyector2.setEdificio(edificioMadero.getValor());

	}

	@Test
	public void getCantidadRequeridaDePersonas() {
		// Se crean los 7 recursos
		ArrayList<Recurso> recursos = new ArrayList<Recurso>();
		recursos.add(arquitecto1);
		recursos.add(programador1);
		recursos.add(programador2);
		recursos.add(programador3);
		recursos.add(sala);
		recursos.add(proyector1);
		recursos.add(proyector2);

		// Requerimientos son 3 programadores y 2 proyectores.
		ArrayList<Propiedad> condiciones;
		condiciones = new ArrayList<Propiedad>();
		condiciones.add(rolProgramador);
		final Requerimiento requerimientoPtogramador = new Requerimiento(
				condiciones);
		condiciones = new ArrayList<Propiedad>();
		condiciones.add(tipoProyector);
		final Requerimiento requerimientoProyector = new Requerimiento(
				condiciones);
		final ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(requerimientoPtogramador);
		requerimientos.add(requerimientoPtogramador);
		requerimientos.add(requerimientoPtogramador);
		requerimientos.add(requerimientoProyector);
		requerimientos.add(requerimientoProyector);

		final Reunion reunion = new Reunion(arquitecto1, recursos,
				new Interval(Agenda.HOY, Agenda.HOY), requerimientos, DateTime
						.now().plusDays(1));

		Assert.assertEquals(7, reunion.getRecursos().size());
		Assert.assertEquals(5, reunion.getRequerimientos().size());
		// 3 programadores.
		Assert.assertEquals(3, reunion.getCantidadRequeridaDePersonas());
	}
}
