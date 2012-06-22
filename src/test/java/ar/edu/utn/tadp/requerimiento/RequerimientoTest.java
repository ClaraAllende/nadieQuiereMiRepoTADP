package ar.edu.utn.tadp.requerimiento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.recurso.roles.Rol;

/**
 * Corre los test de la clase <code>Requerimiento</code>
 * 
 * @see Requerimiento
 * @version 30-05-2012
 */
public class RequerimientoTest {

	private final Propiedad proyectoApollo = new Propiedad("proyecto", "Apollo");
	private final Propiedad proyectoMir = new Propiedad("Proyecto", "Mir");
	private final Propiedad proyectoShuttle = new Propiedad("Proyecto",
			"shuttle");
	private final Propiedad sectorDesarrollo = new Propiedad("Sector",
			"Desarrollo");
	private final Propiedad sectorGerencia = new Propiedad("sector", "gerencia");
	private final Propiedad rolProgramador = new Propiedad("rol", "programador");
	private final Propiedad rolArquitecto = new Propiedad("rol", "Arquitecto");
	private final Propiedad rolGerente = new Propiedad("Rol", "Gerente");
	private final Propiedad rolGraficDesigner = new Propiedad("Rol",
			"Grafic Designer");
	private final Propiedad rolSystemDesigner = new Propiedad("Rol",
			"System Designer");
	private final Propiedad rolProjectLeader = new Propiedad("Rol",
			"Project Leader");
	private Persona programador1;
	private Persona programador2;
	private Persona programador3;
	private Persona arquitecto;
	private Persona gerente1;
	private Persona gerente2;
	private Persona gerente3;
	private Persona leader;
	private Persona systemDesigner;
	private Persona graficDesigner;

	private final Propiedad tipoProyector = new Propiedad("tipo", "Proyector");
	private final Propiedad tipoNotebook = new Propiedad("Tipo", "NoteBook");
	private final Propiedad edificioCatalinas = new Propiedad("edificio",
			"catalinas");
	private final Propiedad edificioMadero = new Propiedad("edificio", "Madero");
	private final Propiedad edificioX = new Propiedad("edificio", "NoExiste");
	private Recurso proyector1;
	private Recurso proyector2;
	private Recurso proyector3;
	private Recurso notebook1;
	private Recurso notebook2;
	private Recurso notebook3;
	private ArrayList<Recurso> recursos;
	private Requerimiento requerimiento;

	@Before
	public void crearContexto() {
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
		arquitecto = new Persona(Rol.ARQUITECTO);
		arquitecto.setProyecto(proyectoApollo.getValor());
		arquitecto.setSector(sectorDesarrollo.getValor());
		arquitecto.setEdificio(edificioCatalinas.getValor());
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
		systemDesigner = new Persona(Rol.DIS_SISTEMAS);
		graficDesigner = new Persona(Rol.DIS_GRAFICO);

		proyector1 = new Recurso();
		proyector1.setTipo(tipoProyector.getValor());
		proyector1.setEdificio(edificioCatalinas.getValor());
		proyector2 = new Recurso();
		proyector2.setTipo(tipoProyector.getValor());
		proyector2.setEdificio(edificioCatalinas.getValor());
		proyector3 = new Recurso();
		proyector3.setTipo(tipoProyector.getValor());
		proyector3.setEdificio(edificioMadero.getValor());
		notebook1 = new Recurso();
		notebook1.setTipo(tipoNotebook.getValor());
		notebook1.setEdificio(edificioCatalinas.getValor());
		notebook2 = new Recurso();
		notebook2.setTipo(tipoNotebook.getValor());
		notebook2.setEdificio(edificioMadero.getValor());
		notebook3 = new Recurso();
		notebook3.setTipo(tipoNotebook.getValor());
		notebook3.setEdificio(edificioMadero.getValor());

		recursos = new ArrayList<Recurso>();
		recursos.add(notebook1);
		recursos.add(notebook2);
		recursos.add(notebook3);
		recursos.add(proyector1);
		recursos.add(proyector2);
		recursos.add(proyector3);
		recursos.add(programador1);
		recursos.add(programador2);
		recursos.add(programador3);
		recursos.add(leader);
		recursos.add(arquitecto);
		recursos.add(gerente1);
		recursos.add(gerente2);
		recursos.add(gerente3);
		recursos.add(graficDesigner);
		recursos.add(systemDesigner);

	}

	@Test
	public void validaUnRecurso() {
		// Crea requerimiento.
		final ArrayList<Propiedad> condiciones = new ArrayList<Propiedad>();
		condiciones.add(tipoProyector);
		condiciones.add(edificioCatalinas);
		requerimiento = new Requerimiento(condiciones);
		assertTrue(requerimiento.cumpleCondiciones(proyector1));
	}

	@Test
	public void buscaPorEdificio() {
		ArrayList<Propiedad> condiciones;
		// Recursos de Catalinas.
		condiciones = new ArrayList<Propiedad>();
		condiciones.add(edificioCatalinas);
		requerimiento = new Requerimiento(condiciones);
		assertEquals(5, requerimiento.filtrarConjunto(recursos).size());

		// Recursos de Madero.
		condiciones = new ArrayList<Propiedad>();
		condiciones.add(edificioMadero);
		requerimiento = new Requerimiento(condiciones);
		assertEquals(9, requerimiento.filtrarConjunto(recursos).size());
	}

	@Test
	public void buscaPorRol() {
		ArrayList<Propiedad> condiciones;
		// Requerimiento de Programadores.
		condiciones = new ArrayList<Propiedad>();
		condiciones.add(rolProgramador);
		requerimiento = new Requerimiento(condiciones);
		assertEquals(3, requerimiento.filtrarConjunto(recursos).size());

		// Requerimiento de un Arquitecto.
		condiciones = new ArrayList<Propiedad>();
		condiciones.add(rolArquitecto);
		requerimiento = new Requerimiento(condiciones);
		assertEquals(1, requerimiento.filtrarConjunto(recursos).size());

		// Requerimiento de un Poject Leader.
		condiciones = new ArrayList<Propiedad>();
		condiciones.add(rolProjectLeader);
		requerimiento = new Requerimiento(condiciones);
		assertEquals(1, requerimiento.filtrarConjunto(recursos).size());

		// Requerimiento de un Gerente.
		condiciones = new ArrayList<Propiedad>();
		condiciones.add(rolGerente);
		requerimiento = new Requerimiento(condiciones);
		assertEquals(3, requerimiento.filtrarConjunto(recursos).size());

		// Requerimiento de un Disen~ador de Sistema.
		condiciones = new ArrayList<Propiedad>();
		condiciones.add(rolSystemDesigner);
		requerimiento = new Requerimiento(condiciones);
		assertEquals(1, requerimiento.filtrarConjunto(recursos).size());

		// Requerimiento de un Disen~ador Grafico.
		condiciones = new ArrayList<Propiedad>();
		condiciones.add(rolGraficDesigner);
		requerimiento = new Requerimiento(condiciones);
		assertEquals(1, requerimiento.filtrarConjunto(recursos).size());
	}

	@Test
	public void buscaTodosProyectores() {
		// Crea requerimiento.
		final ArrayList<Propiedad> condiciones = new ArrayList<Propiedad>();
		condiciones.add(tipoProyector);
		requerimiento = new Requerimiento(condiciones);

		// Filtra.
		final HashSet<Recurso> resultado = new HashSet<Recurso>(
				requerimiento.filtrarConjunto(recursos));

		// Existen en total 3 proyectores.
		assertEquals(3, resultado.size());
	}

	@Test
	public void buscaTodosProyectoresDeMadero() {
		// Crea requerimiento.
		final ArrayList<Propiedad> condiciones = new ArrayList<Propiedad>();
		condiciones.add(tipoProyector);
		condiciones.add(edificioMadero);
		requerimiento = new Requerimiento(condiciones);

		// Filtra.
		final HashSet<Recurso> resultado = new HashSet<Recurso>(
				requerimiento.filtrarConjunto(recursos));

		// Valida el resultado. Solo hay 1 proyector en el edificio Madero.
		assertEquals(1, resultado.size());
	}

	@Test
	public void buscaLoQueNoExiste() {
		// Crea requerimiento.
		final ArrayList<Propiedad> condiciones = new ArrayList<Propiedad>();
		condiciones.add(edificioX);
		requerimiento = new Requerimiento(condiciones);

		// Filtra.
		final HashSet<Recurso> resultado = new HashSet<Recurso>(
				requerimiento.filtrarConjunto(recursos));

		// Valida el resultado.
		assertTrue(resultado.isEmpty());
	}

	@Test
	public void isRecurso() {
		final ArrayList<Propiedad> condiciones = new ArrayList<Propiedad>();

		// Crea requerimiento de un Proyector.
		condiciones.add(edificioX);
		condiciones.add(tipoProyector);
		requerimiento = new Requerimiento(condiciones);

		assertTrue(requerimiento.isRecurso());

		// Crea requerimiento de un Arquitecto.
		condiciones.removeAll(condiciones);
		condiciones.add(rolArquitecto);
		condiciones.add(edificioX);
		requerimiento = new Requerimiento(condiciones);

		assertFalse(requerimiento.isRecurso());
	}
}
