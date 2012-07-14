package dsl

import org.joda.time.DateTime

import ar.edu.utn.tadp.empresa.Empresa.*
import ar.edu.utn.tadp.propiedad.Propiedad
import ar.edu.utn.tadp.requerimiento.Requerimiento
import dsl.manejadores.ManejadorDeRecursos;
import dsl.manejadores.ManejadorDeRequerimientos;
import dsl.manejadores.Manejadores

class EmpresaDSL {
	
	def cantidad
	def requerimientos = new ArrayList()
	def empresa 
	def host
	def reunion
	def duracion
	
	def EmpresaDSL(unaEmpresa, manejadores = [new Manejadores(), new ManejadorDeRecursos(), new ManejadorDeRequerimientos()]){
		empresa = unaEmpresa
		initialize(manejadores)
	}
	
	def anfitrion(anfitrion){
		host = anfitrion
		this
	}
	
	def deDuracion(duracion) {
		this.duracion = duracion
		this
	}
	
	def planificarReunion(){
		reunion = empresa.createReunion(host, requerimientos, duracion, DateTime.now().plusDays(2))
		this
	}
	
	def con(cuantos){
		cantidad = cuantos
		this
	}

	def cancelar(block){
		block
		this
	}
	
	def porcentajeDeAsistenciaMenorA(numero){
		reunion.addTratamientoPorAsistenciaMinima(numero)
	}
	
	def projectManagerCancela(){
		reunion.addTratamientoPorObligatoriedad()
	}
	
	def buscar(rol, proyecto){
		con(rol,proyecto)
	}
	
	def replanificar(){
		reunion.agregarTratamientoPorReplanificacion()
	}
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inicializacion de Manejadores ++++++++++++++++++++++++++++++++++++++++++
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	def initialize(manejadores) {
		manejadores.each { manejador -> manejador.execute(this) }
	}
	
	def agregarRequerimiento(cant, propiedades){
		cant.with {requerimientos << new Requerimiento(propiedades)}
	}
	
}
