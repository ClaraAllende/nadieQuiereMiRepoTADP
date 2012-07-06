package dsl

import static org.junit.Assert.*
import ar.edu.utn.tadp.empresa.Empresa
import ar.edu.utn.tadp.empresa.GeneradorDeContexto
import ar.edu.utn.tadp.propiedad.Propiedad
import ar.edu.utn.tadp.requerimiento.Requerimiento
import dsl.main.EmpresaDSL
import org.joda.time.DateTime
import org.joda.time.Hours
import org.junit.Test
import org.junit.Before

import com.google.common.collect.Lists

class DslTests {
	
	def empresaDSL
	def empresa
	def programador = new GeneradorDeContexto().newProgramador(new Propiedad("proyecto", "Mobiliame"), Propiedad.empty(), Propiedad.empty())
	def host = new GeneradorDeContexto().newGerente(Propiedad.empty(), Propiedad.empty(), Propiedad.empty())
	def sala = new GeneradorDeContexto().newSala(Propiedad.empty())
	
	@Before
	void setUp(){
		empresa = new GeneradorDeContexto().newEmpresa(Lists.newArrayList(host, programador, sala))
		empresaDSL = new EmpresaDSL(empresa)
	}
	
	@Test
	void testSegundoCoso(){
		empresaDSL.planificar(reunion).con(1, {projectMgr("Mobiliame")})
		.con(1, {liderTecnico("Mobiliame")})
		.con(2,{diseniadorGrafico()})
		.con(1,{proyector()})
		.con(1,{notebook()})
		.cancelar({
			porcentajeDeAsistencia.esMenor(70)
		})
	}
	
	@Test
	void testSegundoCosoPrima(){
		empresaDSL planificar reunion con un projectMgr "Mobiliame"
		con un liderTecnico "Mobiliame"
		con dos diseniadorGrafico()
		con un proyector()
		con un notebook()
		cancelar si{
			porcentajeDeAsistencia.esMenor(70)
		}
	}
	
	@Test
	void testHumilde(){
		//reunion En java. Se pueden hacer un par de inlines, pero pierde expresividad.
		def propProgramador = new Propiedad("rol","Programador")
		def propProyectoMobiliame = new Propiedad("proyecto","Mobiliame")
		def requerimiento = new Requerimiento(Lists.newArrayList(propProgramador,propProyectoMobiliame))
		def requerimientos = Lists.newArrayList(requerimiento)
		def reunionPosta = empresa.createReunion(host,requerimientos, Hours.THREE, DateTime.now().plusDays(2));
		
		//reunion con el dsl en groovy. La batata de la vida :D
		def reunion //no se hace nada con esto, pero me lo pide o se rompe.
		def reunionGenerada = empresaDSL.anfitrion(host).con(1).programador("Mobiliame")planificar(reunion)

		assertEquals(reunionPosta, reunionGenerada)
		
	}
}