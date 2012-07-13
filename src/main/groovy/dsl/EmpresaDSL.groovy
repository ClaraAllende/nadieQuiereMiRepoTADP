package dsl

import groovy.lang.MetaClass;

import org.joda.time.DateTime
import org.joda.time.Hours

import ar.edu.utn.tadp.empresa.Empresa.*
import ar.edu.utn.tadp.propiedad.Propiedad
import ar.edu.utn.tadp.requerimiento.Requerimiento

class EmpresaDSL {
	
	def cantidad
	def requerimientos = new ArrayList()
	def empresa 
	def host
	def reunion
	def duracion
	
	def EmpresaDSL(unaEmpresa){
		empresa = unaEmpresa
		initialize()
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
	//++ Personas ++++++++++++++++++++++++++++++++++++++++++
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	def sector(sector){
		agregarRequerimiento(cantidad, [new Propiedad("Sector", sector)])
		this
	}
	
	def initialize() {
		def map = [
			gerente:"Gerente",
			programador:"programador",
			liderTecnico:"Lider Tecnico",
			projectLeader:"project leader",
			diseniadorGrafico:"graphic designer"]
		
		map.each { key, value ->
			this.getClass().metaClass."$key" << { -> agregarRequerimiento(cantidad, [new Propiedad("rol",value)]); this }
			this.getClass().metaClass."$key" << { String proyecto -> agregarRequerimiento(cantidad, [new Propiedad("proyecto",proyecto), new Propiedad("rol",value)]); this } 
		}
		
		def recursos = ["proyector", "notebook"]
		
		recursos.each { value -> 
			this.getClass().metaClass."$value" << { -> agregarRequerimiento(cantidad, [new Propiedad("tipo",value)]); this }
		} 
	}
	
	def agregarRequerimiento(cant, propiedades){
		cant.with {requerimientos << new Requerimiento(propiedades)}
	}
	
}
