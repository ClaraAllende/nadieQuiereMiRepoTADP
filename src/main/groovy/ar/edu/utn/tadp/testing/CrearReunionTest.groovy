package ar.edu.utn.tadp.testing

import org.junit.Before
import org.junit.Test


class CrearReunionTest {

	@Test
	void testSegundoCoso(){
		planificar.(reunion).con(un).projectMgr("Mobiliame")
		.con(un).liderTecnico("Mobiliame")
		.con(dos).diseniadorGrafico()
		.con(un).proyector()
		.con.(un).notebook()
		.cancelar({
			porcentajeDeAsistencia.esMenor(70)
		})
	}
	
	@Test
	void testSegundoCosoPrima(){
		planificar reunion con un projectMgr "Mobiliame"
		con un liderTecnico "Mobiliame"
		con dos diseniadorGrafico()
		con un proyector()
		con un notebook()
		cancelar si{
			porcentajeDeAsistencia.esMenor(70)
		}

	}
}
