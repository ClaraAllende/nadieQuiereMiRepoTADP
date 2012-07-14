package dsl.manejadores

import ar.edu.utn.tadp.propiedad.Propiedad

class ConfiguradorDeRecursos {
	def recursos
	
	def ConfiguradorDeRecursos(values = ["proyector", "notebook"]) {
		recursos = values
	}
	
	def void execute(empresaDSL) {
		recursos.each { value ->
			empresaDSL.getClass().metaClass."$value" << { -> delegate.agregarRequerimiento(delegate.cantidad, [new Propiedad("tipo",value)]); delegate }
		}
	}
}
