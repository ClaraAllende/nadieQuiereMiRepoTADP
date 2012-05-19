package ar.edu.utn.tadp.empresa;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.costos.CostoPorHora;
import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Persona;
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
	private Propiedad sectorDesarrollo = new Propiedad("Sector", "Desarrollo");
	private Propiedad sectorGerencia = new Propiedad("sector", "gerencia");
	private Propiedad rolProgramador = new Propiedad("rol", "programador");
	private Propiedad rolArquitecto = new Propiedad("rol", "Arquitecto");
	private Propiedad rolGerente = new Propiedad("Rol", "Gerente");
	private Propiedad edificioCatalinas = new Propiedad("edificio", "catalinas");
	private Propiedad edificioMadero = new Propiedad("edificio", "Madero");
	private Rol rolDeProgramador;
	private Rol rolDeArquitecto;
	private Rol rolDeGerente;
	private Persona programador1;
	private Persona programador2;
	private Persona programador3;
	private Persona arquitecto1;
	private Persona gerente1;
	private Persona gerente2;
	private Empresa unaEmpresa;
	private CostoPorHora costoProgramador;
	private CostoPorHora costoArquitecto;
	private CostoPorHora costoGerente;

	/**
	 * Crea la empresa y carga todos los recursos necesarios para test.
	 */
	@Before
	public void crearContexto() {
		costoProgramador = new CostoPorHora(new BigDecimal(10));
		costoArquitecto = new CostoPorHora(new BigDecimal(20));
		costoGerente = new CostoPorHora(new BigDecimal(50));
		rolDeProgramador = new Rol(costoProgramador);
		rolDeArquitecto = new Rol(costoArquitecto);
		rolDeGerente = new Rol(costoGerente);

		programador1 = new Persona(rolDeProgramador);
		programador1.addPropiedad(rolProgramador);
		programador1.addPropiedad(proyectoApollo);
		programador1.addPropiedad(sectorDesarrollo);
		programador1.addPropiedad(edificioCatalinas);
		programador2 = new Persona(rolDeProgramador);
		programador2.addPropiedad(rolProgramador);
		programador2.addPropiedad(proyectoApollo);
		programador2.addPropiedad(sectorDesarrollo);
		programador2.addPropiedad(edificioMadero);
		programador3 = new Persona(rolDeProgramador);
		programador3.addPropiedad(rolProgramador);
		programador3.addPropiedad(proyectoMir);
		programador3.addPropiedad(sectorDesarrollo);
		programador3.addPropiedad(edificioMadero);
		arquitecto1 = new Persona(rolDeArquitecto);
		arquitecto1.addPropiedad(rolArquitecto);
		arquitecto1.addPropiedad(proyectoApollo);
		arquitecto1.addPropiedad(sectorDesarrollo);
		arquitecto1.addPropiedad(edificioCatalinas);
		gerente1 = new Persona(rolDeGerente);
		gerente1.addPropiedad(rolGerente);
		gerente1.addPropiedad(proyectoApollo);
		gerente1.addPropiedad(sectorGerencia);
		gerente1.addPropiedad(edificioMadero);
		gerente2 = new Persona(rolDeGerente);
		gerente2.addPropiedad(rolGerente);
		gerente2.addPropiedad(proyectoMir);
		gerente2.addPropiedad(sectorGerencia);
		gerente2.addPropiedad(edificioMadero);
		unaEmpresa = new Empresa();
		unaEmpresa.addRecurso(arquitecto1);
		unaEmpresa.addRecurso(gerente1);
		unaEmpresa.addRecurso(gerente2);
		unaEmpresa.addRecurso(programador1);
		unaEmpresa.addRecurso(programador2);
		unaEmpresa.addRecurso(programador3);
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
				Hours.THREE, DateTime.now());

		Assert.assertNotNull(reunion);
	}

	/**
	 * Se reserva una sala para 3 gerentes cualquiera para dentro de 2 dias,
	 * pero falla porque estan todos ocupados.
	 */
	@Test
	public void fallaCreandoRenionCon3GerentesOcupadosDentroDe2Dias() {
		// TODO
	}

	/**
	 * Se reserva una sala para 3 programadores y un project leader, la sala se
	 * reserva y se agrega el catering automaticamente.
	 */
	public void creaReunionCon3ProgramadoresYProjectLeader() {
		// TODO
	}

	/**
	 * Considerando un proyecto X, se crean 2 reuniones (una con personas de
	 * distintos proyectos, y otra solo con persona de un proyecto X) y luego se
	 * verifica que el costo sea el correcto.
	 */
	public void crea2ReunionesConLosMismosRolesPeroAnfitrionDistinto() {
		// TODO
	}

	/**
	 * Se crea una reunion que incluya a un arquitecto del proyecto X y se
	 * verifica que su tiempo este ocupado en ese rango.
	 */
	public void creaReunionYValidaQueLaGenteQuedeOcupada() {
		// TODO
	}
}