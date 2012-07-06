package dsl

import ar.edu.utn.tadp.requerimiento.Requerimiento
import ar.edu.utn.tadp.propiedad.Propiedad

class EmpresaDSL {
	def cantidad
	def requerimientos = new ArrayList()
	def empresa
	def host
	
	def EmpresaDSL(unaEmpresa){
		empresa = unaEmpresa
	}
	
	def anfitrion(anfitrion){
		host = anfitrion
		this
	}
	
	def planificar(reunion){
		empresa.createReunion(host, requerimientos, Hours.THREE, DateTime.now().plusDays(2))
	}
	
	def con(cuantos, unBloque){
		cantidad = cuantos
		cantidad.times ({unBloque()})
		this
	}

	
	def programador(proyecto){
		requerimientos << new Requerimiento(Lists.newArrayList(new Propiedad("proyecto",proyecto), new Propiedad("rol","Programador")))
		this
	}
}