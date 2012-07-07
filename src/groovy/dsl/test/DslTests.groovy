package dsl.test
import static org.junit.Assert.*

import ar.edu.utn.tadp.empresa.Empresa.*
import ar.edu.utn.tadp.empresa.GeneradorDeContexto
import ar.edu.utn.tadp.propiedad.Propiedad
import ar.edu.utn.tadp.requerimiento.Requerimiento


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
	def propProyectoZarlanga = new Propiedad("proyecto","Zarlanga Object Manager Abstract System")
	def propProyectoGC= new Propiedad("proyecto", "Automatic Losing Reference Counter Garbage Collector")
	def propDiseniadorGrafico = new Propiedad("rol", "graphic designer")
	def propNotebook = new Propiedad("tipo", "notebook")
	def propCanion = new Propiedad("tipo", "proyector")
    def propProgramador = new Propiedad("rol", "programador")
	def propMarketing = new Propiedad("Sector","Marketing")
	def propLiderTecnico = new Propiedad("rol", "Lider tecnico")
	

	
//++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++ definición de Recursos ++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++++++++++++++

	def empresaDSL
	def empresa
	def leader 	= new GeneradorDeContexto().newProjectLeader(propProyectoMobiliame, Propiedad.empty(), Propiedad.empty())
	def leader2 	= new GeneradorDeContexto().newProjectLeader(propProyectoZarlanga, Propiedad.empty(), Propiedad.empty())
	def leader3= new GeneradorDeContexto().newProjectLeader(propProyectoGC,Propiedad.empty(),Propiedad.empty())
	def host 	= new GeneradorDeContexto().newGerente(Propiedad.empty(), Propiedad.empty(), Propiedad.empty())
	def sala 	= new GeneradorDeContexto().newSala(Propiedad.empty())
	def sala2 	= new GeneradorDeContexto().newSala(Propiedad.empty())
	def sala3 	= new GeneradorDeContexto().newSala(Propiedad.empty())
	def canion 	= new GeneradorDeContexto().newProyector(Propiedad.empty())
	def notebook 	= new GeneradorDeContexto().newRecurso(new Propiedad("tipo", "notebook"), Propiedad.empty())
	def arquitecto 	= new GeneradorDeContexto().newArquitecto(propProyectoMobiliame, Propiedad.empty(), Propiedad.empty())
	def disGrafico	= new GeneradorDeContexto().newGraphicDesigner(propProyectoMobiliame, propDiseniadorGrafico, Propiedad.empty())
	def programador 	= new GeneradorDeContexto().newProgramador(propProyectoMobiliame, Propiedad.empty(), Propiedad.empty())
	def programador2 	= new GeneradorDeContexto().newProgramador(propProyectoMobiliame, Propiedad.empty(), Propiedad.empty())
	def programador3 	= new GeneradorDeContexto().newProgramador( propProyectoMobiliame,new Propiedad("sector", "Marketing"), Propiedad.empty())
	def arquitecto2 	= new GeneradorDeContexto().newArquitecto(Propiedad.empty(),new Propiedad("sector", "Marketing"),  Propiedad.empty())
	def arquitecto3 	= new GeneradorDeContexto().newArquitecto( Propiedad.empty(),new Propiedad("sector", "Marketing"), Propiedad.empty())
	def disGrafico2 	= new GeneradorDeContexto().newGraphicDesigner( Propiedad.empty(),new Propiedad("sector", "Marketing"), Propiedad.empty())
	def leader4 	= new GeneradorDeContexto().newProjectLeader( Propiedad.empty(), new Propiedad("sector", "Marketing"),Propiedad.empty())
	def gerente	= new GeneradorDeContexto().newGerente(propProyectoGC, Propiedad.empty(), Propiedad.empty())
	def gerente2= new GeneradorDeContexto().newGerente(propProyectoZarlanga, Propiedad.empty(), Propiedad.empty())
	def technicalLeader= new GeneradorDeContexto().newTechnicalLeader(propProyectoMobiliame, propLiderTecnico,Propiedad.empty())
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ definición requerimientos para requerimientos +++++
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	def reqPLDeMobiliame = new Requerimiento([propProjectLeader,propProyectoMobiliame])
	def reqProgramador = new Requerimiento([propProgramador])
	def reqDiseniadorGr  = new Requerimiento([propDiseniadorGrafico])
	def reqOtroDisGrafi	 = new Requerimiento([propDiseniadorGrafico])
	def reqTechnicalLeaderMobiliame = new Requerimiento([propLiderTecnico, propProyectoMobiliame])
	def reqProyector	 = new Requerimiento([propCanion])
	def reqNotebook		 = new Requerimiento([propNotebook])
	def reqProgramadorMobiliame= new Requerimiento([propProgramador,propProyectoMobiliame])
	def reqDiseniadorMobiliame= new Requerimiento([propDiseniadorGrafico,propProyectoMobiliame])
	def reqPLDeZarlanga = new Requerimiento([propProjectLeader,propProyectoZarlanga])
	def reqPLDeGC = new Requerimiento([propProjectLeader,propProyectoGC])
	def requerimientos3=[reqProgramadorMobiliame,reqProgramadorMobiliame,reqProgramadorMobiliame,reqDiseniadorMobiliame/*,reqLiderTecnico*/]
		
	@Test
	void testPrimeraReunion(){
		// fixture
		empresa = new GeneradorDeContexto()
		.newEmpresa([host, programador,leader, disGrafico2,leader2,leader3,leader4,arquitecto2,arquitecto3,gerente,gerente2,sala])
		empresaDSL = new EmpresaDSL(empresa).anfitrion(host)
		def reqDeMarketing = new Requerimiento([propMarketing])
		def reqDeGerente= new Requerimiento([new Propiedad("rol","Gerente")])
		def requerimientos1= [reqPLDeMobiliame, reqPLDeZarlanga, reqPLDeGC, reqDeMarketing,reqDeMarketing,reqDeMarketing,reqDeMarketing,reqDeMarketing, reqDeGerente,reqDeGerente]
		def reunionPosta = empresa.createReunion(host,requerimientos1, Hours.THREE, DateTime.now().plusDays(2));
	
		def reunion	// No hago nada con esto
		def reunionGenerada =
			empresaDSL	
				.con(1).projectLeader("Mobiliame")
				.con(1).projectLeader("Zarlanga Object Manager Abstract System")
				.con(1).projectLeader("Automatic Losing Reference Counter Garbage Collector")
				.con(5).sector("Marketing")
				.con(2).gerente()
				.planificar(reunion)
				.cancelar({porcentajeDeAsistenciaMenorA(70)})
				.getReunion()
				
				//en algún momento solucionaremos lo de estos asserts :P
				assertTrue(reunionPosta.getRecursos().containsAll(reunionGenerada.getRecursos()))
				assertTrue(reunionGenerada.getRecursos().containsAll(reunionPosta.getRecursos()))
				assertEquals(reunionPosta.getAnfitrion(), reunionGenerada.getAnfitrion())
				assertTrue(reunionPosta.getTratamientos().containsAll(reunionGenerada.getTratamientos()))
				assertTrue(reunionGenerada.getTratamientos().containsAll(reunionPosta.getTratamientos()))
	}
	
	@Test
    void testSegundaReunion(){
		empresa = new GeneradorDeContexto()
		.newEmpresa([host, programador,leader, disGrafico, canion, technicalLeader, notebook, sala])
		empresaDSL = new EmpresaDSL(empresa).anfitrion(host)
		def requerimientos2 = [reqPLDeMobiliame, reqDiseniadorGr, reqProyector, reqNotebook,reqTechnicalLeaderMobiliame	]
        // reunión en java
		
        def reunionPosta = empresa.createReunion(host,requerimientos2, Hours.THREE, DateTime.now().plusDays(2));

				
		// reunión en groovy		
		def reunion	// No hago nada con esto
		def reunionGenerada = 
			empresaDSL
				.con(1).projectLeader("Mobiliame")
    			.con(1).liderTecnico("Mobiliame")
		        .con(2).diseniadorGrafico()
		        .con(1).proyector()
		        .con(1).notebook() 
				.planificar(reunion)
		        .cancelar({ projectManagerCancela() })
				.cancelar({buscar(arquitecto,"Mobiliame")})
				.getReunion()
		       
			
			//Aserciones. Definitivamente hay que hacer algo con esto .
	        assertTrue(reunionPosta.getRecursos().containsAll(reunionGenerada.getRecursos()))
	        assertTrue(reunionGenerada.getRecursos().containsAll(reunionPosta.getRecursos()))
	        assertEquals(reunionPosta.getAnfitrion(), reunionGenerada.getAnfitrion())
	        assertTrue(reunionPosta.getTratamientos().containsAll(reunionGenerada.getTratamientos()))
	        assertTrue(reunionGenerada.getTratamientos().containsAll(reunionPosta.getTratamientos()))
    }
    
    @Test
    void testSegundaReunionSinParentesis(){
		empresa = new GeneradorDeContexto()
		.newEmpresa([host, programador,leader, disGrafico, canion, notebook, sala])
empresaDSL = new EmpresaDSL(empresa).anfitrion(host)
		def requerimientos2 = [reqPLDeMobiliame, reqDiseniadorGr, reqProyector, reqNotebook]
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
	void testTerceraReunion(){
		empresa = new GeneradorDeContexto()
		.newEmpresa([host, programador,leader, disGrafico, programador2,programador3, technicalLeader,sala])
empresaDSL = new EmpresaDSL(empresa).anfitrion(host)
		def reunionPosta = empresa.createReunion(host,requerimientos3, Hours.THREE, DateTime.now().plusDays(2));
			def reunion
			def reunionGenerada= 
				empresaDSL
				.con(1).projectLeader("Mobiliame")
					  			.con(1).liderTecnico()
								.con(3).programador("Mobiliame")
								.con(1).diseniadorGrafico("Mobiliame")
								.planificar(reunion)
								//.cancelar({fecha("30/7/2012))
								.cancelar({replanificar()})
								.getReunion()
								
					assertTrue(reunionPosta.getRecursos().containsAll(reunionGenerada.getRecursos()))
					assertTrue(reunionGenerada.getRecursos().containsAll(reunionPosta.getRecursos()))
					assertEquals(reunionPosta.getAnfitrion(), reunionGenerada.getAnfitrion())
					assertTrue(reunionPosta.getTratamientos().containsAll(reunionGenerada.getTratamientos()))
					assertTrue(reunionGenerada.getTratamientos().containsAll(reunionPosta.getTratamientos()))
					   
	}
	@Test
	void testHumilde(){
		empresa = new GeneradorDeContexto()
		.newEmpresa([host, programador,leader, disGrafico, canion, notebook, sala])
empresaDSL = new EmpresaDSL(empresa).anfitrion(host)
		
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
