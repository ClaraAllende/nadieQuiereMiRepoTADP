package ar.edu.utn.tadp.empresa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.agenda.Agenda;
import ar.edu.utn.tadp.excepcion.UserException;
import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.recurso.roles.Rol;
import ar.edu.utn.tadp.requerimiento.Requerimiento;
import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Prueba la creacion de una reunion.
 * 
 * @version 11-05-2012
 */
public class CrearReunionTest {

	private Propiedad proyectoApollo = new Propiedad("proyecto", "Apollo");
	private Propiedad proyectoMir = new Propiedad("Proyecto", "Mir");
	private Propiedad proyectoShuttle = new Propiedad("Proyecto", "shuttle");
	private Propiedad sectorDesarrollo = new Propiedad("Sector", "Desarrollo");
	private Propiedad sectorGerencia = new Propiedad("sector", "gerencia");
	private Propiedad rolProgramador = new Propiedad("rol", "programador");
	private Propiedad rolArquitecto = new Propiedad("rol", "Arquitecto");
	private Propiedad rolGerente = new Propiedad("Rol", "Gerente");
	private Propiedad edificioCatalinas = new Propiedad("edificio", "catalinas");
	private Propiedad edificioMadero = new Propiedad("edificio", "Madero");
	private Propiedad tipoSala = new Propiedad("tipo", "Sala");
	private Propiedad propiedadProjectLeader = new Propiedad("Rol",
			"Project Leader");
	private Persona programador1;
	private Persona programador2;
	private Persona programador3;
	private Persona arquitecto1;
	private Persona gerente1;
	private Persona gerente2;
	private Persona lider;
	private Recurso sala;
	private Empresa unaEmpresa;

	private Persona gerente3;

	/**
	 * Crea la empresa y carga todos los recursos necesarios para test.
	 */
	@Before
	public void crearContexto() {

		programador1 = new Persona(Rol.PROGRAMADOR);
		programador1.addPropiedad(rolProgramador);
		programador1.addPropiedad(proyectoApollo);
		programador1.addPropiedad(sectorDesarrollo);
		programador1.addPropiedad(edificioCatalinas);
		programador2 = new Persona(Rol.PROGRAMADOR);
		programador2.addPropiedad(rolProgramador);
		programador2.addPropiedad(proyectoApollo);
		programador2.addPropiedad(sectorDesarrollo);
		programador2.addPropiedad(edificioMadero);
		programador3 = new Persona(Rol.PROGRAMADOR);
		programador3.addPropiedad(rolProgramador);
		programador3.addPropiedad(proyectoMir);
		programador3.addPropiedad(sectorDesarrollo);
		programador3.addPropiedad(edificioMadero);
		arquitecto1 = new Persona(Rol.ARQUITECTO);
		arquitecto1.addPropiedad(rolArquitecto);
		arquitecto1.addPropiedad(proyectoApollo);
		arquitecto1.addPropiedad(sectorDesarrollo);
		arquitecto1.addPropiedad(edificioCatalinas);
		gerente1 = new Persona(Rol.GERENTE);
		gerente1.addPropiedad(rolGerente);
		gerente1.addPropiedad(proyectoApollo);
		gerente1.addPropiedad(sectorGerencia);
		gerente1.addPropiedad(edificioMadero);
		gerente2 = new Persona(Rol.GERENTE);
		gerente2.addPropiedad(rolGerente);
		gerente2.addPropiedad(proyectoMir);
		gerente2.addPropiedad(sectorGerencia);
		gerente2.addPropiedad(edificioMadero);
		gerente3 = new Persona(Rol.GERENTE);
		gerente3.addPropiedad(rolGerente);
		gerente3.addPropiedad(proyectoShuttle);
		gerente3.addPropiedad(sectorGerencia);
		gerente3.addPropiedad(edificioMadero);
		lider = new Persona(Rol.PROYECT_LIDER);
		lider.addPropiedad(propiedadProjectLeader);
		lider.addPropiedad(proyectoShuttle);
		lider.addPropiedad(edificioCatalinas);
		lider.addPropiedad(edificioMadero);

		sala = new Recurso();
		sala.addPropiedad(tipoSala);
		sala.addPropiedad(edificioMadero);
		unaEmpresa = new Empresa();
		unaEmpresa.addRecurso(lider);
		unaEmpresa.addRecurso(arquitecto1);
		unaEmpresa.addRecurso(gerente1);
		unaEmpresa.addRecurso(gerente2);
		unaEmpresa.addRecurso(programador1);
		unaEmpresa.addRecurso(programador2);
		unaEmpresa.addRecurso(programador3);
		unaEmpresa.addRecurso(sala);
	}

	/**
	 * Se reserva una sala para todos los integrantes de un proyecto
	 * exitosamente.
	 */
	@Test
	public void creaReunionParaIntegrantesDeUnProyecto() {
		ArrayList<Propiedad> condiciones = new ArrayList<Propiedad>();
		condiciones.add(proyectoApollo);
		Requerimiento requerimiento = new Requerimiento(condiciones);

		ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(requerimiento);

		Reunion reunion = unaEmpresa.createReunion(gerente1, requerimientos,
				Hours.THREE, DateTime.now().plusDays(2));

		Assert.assertNotNull(reunion);
	}

	/**
	 * Se reserva una sala para 3 gerentes cualquiera para dentro de 2 dias,
	 * pero falla porque estan todos ocupados.
	 */
	@Test(expected = UserException.class)
	public void fallaCreandoRenionCon3GerentesOcupadosDentroDe2Dias() {

		// Intervalo de ocupacion 5 dias.
		Interval ocupado3Dias = new Interval(Agenda.HOY, Agenda.HOY.plusDays(5));

		gerente1.ocupateDurante(ocupado3Dias);
		gerente2.ocupateDurante(ocupado3Dias);
		gerente3.ocupateDurante(ocupado3Dias);

		ArrayList<Propiedad> pripiedadesSala = new ArrayList<Propiedad>();
		pripiedadesSala.add(tipoSala);
		Requerimiento reqSala = new Requerimiento(pripiedadesSala);

		ArrayList<Propiedad> pripiedadesGerente = new ArrayList<Propiedad>();
		pripiedadesGerente.add(rolGerente);
		Requerimiento reqGerente1 = new Requerimiento(pripiedadesGerente);
		Requerimiento reqGerente2 = new Requerimiento(pripiedadesGerente);
		Requerimiento reqGerente3 = new Requerimiento(pripiedadesGerente);

		ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(reqSala);
		requerimientos.add(reqGerente1);
		requerimientos.add(reqGerente2);
		requerimientos.add(reqGerente3);

		// Esto tira una excepcion.
		unaEmpresa.createReunion(gerente1, requerimientos, Hours.THREE,
				DateTime.now().plusDays(2));
	}

	/**
	 * Se reserva una sala para 3 programadores y un project leader, la sala se
	 * reserva y se agrega el catering automaticamente.
	 */
	@Test
	public void creaReunionCon3ProgramadoresYProjectLeader() {

		Collection<Propiedad> propiedadesSala = new ArrayList<Propiedad>();
		propiedadesSala.add(tipoSala);

		Requerimiento sala = new Requerimiento(propiedadesSala);
		Collection<Propiedad> propiedadesProjectLeader = new ArrayList<Propiedad>();

		propiedadesProjectLeader.add(propiedadProjectLeader);
		lider.addPropiedad(propiedadProjectLeader);
		Requerimiento requerimientoProjectLeader = new Requerimiento(
				propiedadesProjectLeader);

		ArrayList<Propiedad> propiedadesProgramador = new ArrayList<Propiedad>();
		propiedadesProgramador.add(rolProgramador);
		Requerimiento reqProgramador1 = new Requerimiento(
				propiedadesProgramador);
		Requerimiento reqProgramador2 = new Requerimiento(
				propiedadesProgramador);
		Requerimiento reqProgramador3 = new Requerimiento(
				propiedadesProgramador);

		List<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(requerimientoProjectLeader);
		requerimientos.add(reqProgramador1);
		requerimientos.add(reqProgramador2);
		requerimientos.add(reqProgramador3);
		requerimientos.add(sala);
		Reunion reunion = unaEmpresa.createReunion(lider, requerimientos,
				Hours.THREE, DateTime.now().plusDays(2));

		Assert.assertNotNull(reunion);
		Assert.assertTrue(reunion.tieneCatering());
	}

	/**
	 * Considerando un proyecto X, se crean 2 reuniones (una con personas de
	 * distintos proyectos, y otra solo con persona de un proyecto X) y luego se
	 * verifica que el costo sea el correcto.
	 */
	@Test
	public void crea2ReunionesConLosMismosRolesPeroDistintoCosto() {
		ArrayList<Propiedad> condiciones1 = new ArrayList<Propiedad>();
		condiciones1.add(proyectoApollo);
		condiciones1.add(edificioMadero);
		condiciones1.add(rolProgramador);
		Requerimiento programadorApollo = new Requerimiento(condiciones1);

		ArrayList<Requerimiento> requerimientos1 = new ArrayList<Requerimiento>();
		requerimientos1.add(programadorApollo);

		// Anfintrion es de Apollo y el invitado tambien
		Reunion reunionBarata = unaEmpresa.createReunion(gerente1,
				requerimientos1, Hours.SEVEN, DateTime.now().plusDays(2));

		ArrayList<Propiedad> condiciones2 = new ArrayList<Propiedad>();
		condiciones2.add(proyectoMir);
		condiciones2.add(edificioMadero);
		condiciones2.add(rolProgramador);
		Requerimiento programadorMir = new Requerimiento(condiciones2);

		ArrayList<Requerimiento> requerimientos2 = new ArrayList<Requerimiento>();
		requerimientos2.add(programadorMir);

		// Anfintrion es de Apollo y el invitado de Mir
		Reunion reunionCara = unaEmpresa.createReunion(gerente1,
				requerimientos2, Hours.SEVEN, DateTime.now().plusDays(2));

		float costoReunionBarata = reunionBarata.getCostoTotal().floatValue();
		float costoReunionCara = reunionCara.getCostoTotal().floatValue();

		Assert.assertTrue(costoReunionBarata < costoReunionCara);
	}

	/**
	 * Se crea una reunion que incluya a un arquitecto del proyecto X y se
	 * verifica que su tiempo este ocupado en ese rango.
	 */
	@Test
	public void creaReunionYValidaQueLaGenteQuedeOcupada() {

		Interval intervalo = new Interval(Agenda.HOY, Agenda.HOY.plusHours(2));
		ArrayList<Propiedad> propiedadesArq = new ArrayList<Propiedad>();
		propiedadesArq.add(rolArquitecto);
		propiedadesArq.add(proyectoApollo);
		Requerimiento reqArquitecto = new Requerimiento(propiedadesArq);
		ArrayList<Requerimiento> requerimientos = new ArrayList<Requerimiento>();
		requerimientos.add(reqArquitecto);
		unaEmpresa
				.createReunion(gerente1, requerimientos, intervalo.toDuration()
						.toStandardHours(), intervalo.getEnd().plusDays(2));

		Assert.assertTrue(arquitecto1.estasOcupadoDurante(intervalo));
	}
}
