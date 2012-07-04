package dsl.test;

import static org.junit.Assert.*

import ar.edu.utn.tadp.empresa.Empresa
import org.junit.Test
import org.junit.Before


class DslTests {

	def planificar
	
	@Before
	def setUp(){
		planificar = new Empresa()
		
	}
	
	@Test
    def testSegundoCoso(){
        planificar.(reunion).con(1).projectMgr("Mobiliame") 
        .con(1).liderTecnico("Mobiliame") 
        .con(2).diseniadorGrafico()
        .con(1).proyector()
        .con.(1).notebook() 
        .cancelar({
            porcentajeDeAsistencia.esMenor(70)
        })
    }
    
    @Test
    def testSegundoCosoPrima(){
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
