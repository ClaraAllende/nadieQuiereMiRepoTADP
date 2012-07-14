package dsl.manejadores

import ar.edu.utn.tadp.propiedad.Propiedad

class ConfiguradorDeRoles {

	def roles
	
	def ConfiguradorDeRoles(values=[gerente:"Gerente",programador:"programador",liderTecnico:"Lider Tecnico",projectLeader:"project leader",diseniadorGrafico:"graphic designer"]) {
		roles = values
	}
	
	def void execute(empresaDSL) {
		roles.each { key, value ->
			empresaDSL.getClass().metaClass."$key" << { -> delegate.agregarRequerimiento(delegate.cantidad, [new Propiedad("rol",value)]); delegate }
			empresaDSL.getClass().metaClass."$key" << { String proyecto -> delegate.agregarRequerimiento(delegate.cantidad, [new Propiedad("proyecto",proyecto), new Propiedad("rol",value)]); delegate }
		}
	}
}
