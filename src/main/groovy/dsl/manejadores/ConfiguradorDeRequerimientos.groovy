package dsl.manejadores

import ar.edu.utn.tadp.propiedad.Propiedad

class ConfiguradorDeRequerimientos {
	def otroRequerimiento
	
	def ConfiguradorDeRequerimientos(values = ["sector"]) {
		otroRequerimiento = values
	}
	
	def void execute(empresaDSL) {
		otroRequerimiento.each { value ->
			empresaDSL.getClass().metaClass."$value" << { String requerimiento -> delegate.agregarRequerimiento(delegate.cantidad, [new Propiedad(value,requerimiento)]); delegate}
		}
	}
}
