package ar.edu.utn.tadp.empresa;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.empresa.Empresa;

/**
 * Prueba la creacion de una reunion.
 * 
 * @version 11-05-2012
 */
public class CrearReunionTest {

	Empresa unaEmpresa;

	/**
	 * Crea la empresa y carga todos los recursos necesarios para test.
	 */
	@Before
	public void crearContexto() {
	}

	/**
	 * Se reserva una sala para todos los integrantes de un proyecto
	 * exitosamente.
	 */
	@Test
	public void creaReunionParaIntegrantesDeUnProyecto() {
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
