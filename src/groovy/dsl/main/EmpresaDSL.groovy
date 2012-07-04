package dsl.main

import ar.edu.utn.tadp.empresa.Empresa
import ar.edu.utn.tadp.empresa.GeneradorDeContexto
import ar.edu.utn.tadp.propiedad.Propiedad
import ar.edu.utn.tadp.recurso.Persona
import ar.edu.utn.tadp.requerimiento.Requerimiento

import org.joda.time.DateTime
import org.joda.time.Hours

import com.google.common.collect.Lists

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
	
	def con(cuantos){
		cantidad = cuantos
		this
	}
	
	def programador(proyecto){
		//TODO agregar la lógica para que tome la cantidad del con()
		requerimientos << new Requerimiento(Lists.newArrayList(new Propiedad("proyecto",proyecto), new Propiedad("rol","Programador")))
		this
	}
	
	
	
}
