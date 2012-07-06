package dsl.test;

import static org.junit.Assert.*

import ar.edu.utn.tadp.empresa.Empresa.*
import ar.edu.utn.tadp.empresa.GeneradorDeContexto
import ar.edu.utn.tadp.propiedad.Propiedad
import ar.edu.utn.tadp.requerimiento.Requerimiento
import org.mockito.Mockito.*


import org.joda.time.DateTime
import org.joda.time.Hours
import org.junit.Test
import org.junit.Before

import com.google.common.collect.Lists
import dsl.main.EmpresaDSL


class DslTests {
//++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++ definición de propiedades +++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++++++++++++++	
	
	def propProjectLeader = new Propiedad("rol","project leader")
	def propProyectoMobiliame = new Propiedad("proyecto","Mobiliame")
	def propDiseniadorGrafico = new Propiedad("rol", "graphic designer")
	def propNotebook = new Propiedad("tipo", "notebook")
	def propCanion = new Propiedad("tipo", "proyector")

	
//++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++ definición de Recursos ++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++++++++++++++

	def empresaDSL
	def empresa
	def leader 	= new GeneradorDeContexto().newProjectLeader(propProyectoMobiliame, Propiedad.empty(), Propiedad.empty())
	def host 	= new GeneradorDeContexto().newGerente(Propiedad.empty(), Propiedad.empty(), Propiedad.empty())
	def sala 	= new GeneradorDeContexto().newSala(Propiedad.empty())
	def sala2 	= new GeneradorDeContexto().newSala(Propiedad.empty())
	def sala3 	= new GeneradorDeContexto().newSala(Propiedad.empty())
	def canion 	= new GeneradorDeContexto().newProyector(Propiedad.empty())
	def notebook 	= new GeneradorDeContexto().newRecurso(new Propiedad("tipo", "notebook"), Propiedad.empty())
	def arquitecto 	= new GeneradorDeContexto().newArquitecto(new Propiedad("proyecto", "ACE"), Propiedad.empty(), Propiedad.empty())
	def disGrafico	= new GeneradorDeContexto().newGraphicDesigner(propDiseniadorGrafico, Propiedad.empty(), Propiedad.empty())
	def programador 	= new GeneradorDeContexto().newProgramador(propProyectoMobiliame, Propiedad.empty(), Propiedad.empty())
	def programador2 	= new GeneradorDeContexto().newProgramador(new Propiedad("proyecto", "Notes"), Propiedad.empty(), Propiedad.empty())
	def otroDisGrafico	= new GeneradorDeContexto().newGraphicDesigner(propDiseniadorGrafico, Propiedad.empty(), Propiedad.empty())
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ definición requerimientos para requerimientos +++++
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	def reqPLDeMobiliame = new Requerimiento([propProjectLeader,propProyectoMobiliame])
	def reqDiseniadorGr  = new Requerimiento([propDiseniadorGrafico])
	def reqProyector	 = new Requerimiento([propCanion])
	def reqNotebook		 = new Requerimiento([propNotebook])
	def requerimientos2 = [reqPLDeMobiliame, reqDiseniadorGr, reqProyector, reqNotebook]

	@Before
	void setUp(){
		
		empresa = new GeneradorDeContexto()
				.newEmpresa([host, programador,leader, disGrafico, canion, notebook, sala])
		empresaDSL = new EmpresaDSL(empresa).anfitrion(host)
	}
	
	@Test
    void testSegundoCoso(){
        // reunión en java
		
        def reunionPosta = empresa.createReunion(host,requerimientos2, Hours.THREE, DateTime.now().plusDays(2));

				
		// reunión en groovy		
		def reunion	// No hago nada con esto
		def reunionGenerada = 
			empresaDSL
				.con(1).projectLeader("Mobiliame")
//  			.con(1).liderTecnico("Mobiliame")
		        .con(2).diseniadorGrafico()
		        .con(1).proyector()
		        .con(1).notebook() 
				.planificar(reunion)
		        .cancelar({ porcentajeDeAsistenciaMenorA(70) })
				.getReunion()
		       
			
			//Aserciones. Definitivamente hay que hacer algo con esto .
	        assertTrue(reunionPosta.getRecursos().containsAll(reunionGenerada.getRecursos()))
	        assertTrue(reunionGenerada.getRecursos().containsAll(reunionPosta.getRecursos()))
	        assertEquals(reunionPosta.getAnfitrion(), reunionGenerada.getAnfitrion())
	        assertTrue(reunionPosta.getTratamientos().containsAll(reunionGenerada.getTratamientos()))
	        assertTrue(reunionGenerada.getTratamientos().containsAll(reunionPosta.getTratamientos()))
    }
    
    @Test
    void testSegundoCosoPrima(){
    	def reunionPosta = empresa.createReunion(host,requerimientos2, Hours.THREE, DateTime.now().plusDays(2));
		
		
    	def reunion		//No hago nada con esto.
		
		def reunionGenerada = 
		empresaDSL
//	        con 1 liderTecnico "Mobiliame"
			.con 1 projectLeader "Mobiliame" con 1 diseniadorGrafico() con 1 proyector() con 1 notebook() planificar reunion cancelar {porcentajeDeAsistenciaMenorA(70)}
			.getReunion()
			
			//Aserciones. Definitivamente hay que hacer algo con esto .
			assertTrue(reunionPosta.getRecursos().containsAll(reunionGenerada.getRecursos()))
			assertTrue(reunionGenerada.getRecursos().containsAll(reunionPosta.getRecursos()))
			assertEquals(reunionPosta.getAnfitrion(), reunionGenerada.getAnfitrion())
			assertTrue(reunionPosta.getTratamientos().containsAll(reunionGenerada.getTratamientos()))
			assertTrue(reunionGenerada.getTratamientos().containsAll(reunionPosta.getTratamientos()))
    }
	
	@Test
	void testHumilde(){
		
		//reunion En java. Se pueden hacer un par de inlines, pero pierde expresividad.
		def propProgramador = new Propiedad("rol","Programador")
		def requerimiento = new Requerimiento([propProgramador,propProyectoMobiliame])
		def requerimientos = [requerimiento]
		def reunionPosta = empresa.createReunion(host,requerimientos, Hours.THREE, DateTime.now().plusDays(2));
		
		//reunion con el dsl en groovy. La batata de la vida :D
		def reunion 	//no se hace nada con esto, pero me lo pide o se rompe. 
		
		def reunionGenerada = empresaDSL.con 1 programador "Mobiliame" anfitrion host planificar reunion getReunion()
		

		/*
		 * Es una paja, pero como el equals entre objetos compara por identidad, solo me queda
		 * esto o redefinir equals para la clase que quiero. 
		 * Y no quiero redefinir equals :D
		 * 
		 * OBS: No es EXACTAMENTE la misma reunión... como la reunión tiene efecto en los recursos,
		 * se genera una reunión nueva de las mismas características (cumple los mismos requerimientos)
		 * pero un rato después (para ser precisos, inmediatamente después :D)
		 * 
		 * Cuando comparamos por igual sólo comparamos aquéllo que no tiene que ver con el horario.
		 */
		assertTrue(reunionPosta.getRecursos().containsAll(reunionGenerada.getRecursos()))
		assertTrue(reunionGenerada.getRecursos().containsAll(reunionPosta.getRecursos()))
		assertEquals(reunionPosta.getAnfitrion(), reunionGenerada.getAnfitrion())
		assertTrue(reunionGenerada.getTratamientos().containsAll(reunionPosta.getTratamientos()))
		assertTrue(reunionPosta.getTratamientos().containsAll(reunionGenerada.getTratamientos()))
		
		}
	}
