package dsl.manejadores

import ar.edu.utn.tadp.propiedad.Propiedad

class ManejadorDeRoles {

	def map = [
			gerente:"Gerente",
			programador:"programador",
			liderTecnico:"Lider Tecnico",
			projectLeader:"project leader",
			diseniadorGrafico:"graphic designer"]
	
	def ManejadorDeRoles() {
	}
	
	def void execute(empresaDSL) {
		map.each { key, value ->
			empresaDSL.getClass().metaClass."$key" << { -> delegate.agregarRequerimiento(delegate.cantidad, [new Propiedad("rol",value)]); delegate }
			empresaDSL.getClass().metaClass."$key" << { String proyecto -> delegate.agregarRequerimiento(delegate.cantidad, [new Propiedad("proyecto",proyecto), new Propiedad("rol",value)]); delegate }
		}
	}
}

class ManejadorDeRecursos {
	def recursos = ["proyector", "notebook"]
	
	def void execute(empresaDSL) {
		recursos.each { value ->
			empresaDSL.getClass().metaClass."$value" << { -> delegate.agregarRequerimiento(delegate.cantidad, [new Propiedad("tipo",value)]); delegate }
		}
	}
}

class ManejadorDeRequerimientos {
	def otroRequerimiento = [ "sector" ]
	
	def void execute(empresaDSL) {
		otroRequerimiento.each { value ->
			empresaDSL.getClass().metaClass."$value" << { String requerimiento -> delegate.agregarRequerimiento(delegate.cantidad, [new Propiedad(value,requerimiento)]); delegate}
		}
	}
}
