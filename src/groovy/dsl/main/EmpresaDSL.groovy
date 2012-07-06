package dsl.main

import javax.management.relation.Role;

import junit.extensions.RepeatedTest;
import ar.edu.utn.tadp.empresa.Empresa.*
import ar.edu.utn.tadp.empresa.GeneradorDeContexto
import ar.edu.utn.tadp.propiedad.Propiedad
import ar.edu.utn.tadp.recurso.Persona
import ar.edu.utn.tadp.recurso.Recurso
import ar.edu.utn.tadp.requerimiento.Requerimiento
import ar.edu.utn.tadp.recurso.roles.Rol

import org.joda.time.DateTime
import org.joda.time.Hours

import com.google.common.collect.Lists

class EmpresaDSL {
	
	def cantidad
	def requerimientos = new ArrayList()
	def empresa 
	def host
	def reunion
	
	def EmpresaDSL(unaEmpresa){
		empresa = unaEmpresa
	}
	
	def anfitrion(anfitrion){
		host = anfitrion
		this
	}
	
	def planificar(sarlanga){
		reunion = empresa.createReunion(host, requerimientos, Hours.THREE, DateTime.now().plusDays(2))
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
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Personas ++++++++++++++++++++++++++++++++++++++++++
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	def programador(){
		agregarRequerimiento(cantidad, [new Propiedad("rol","programador")])
		this
	}
	
	def programador(proyecto){
		agregarRequerimiento(cantidad, [new Propiedad("proyecto",proyecto), new Propiedad("rol","programador")])
		this
	}

	def liderTecnico(proyecto){
		agregarRequerimiento(cantidad, [new Propiedad("proyecto",proyecto), new Propiedad("rol","Lider Tecnico")])
		this
	}
	
	def projectLeader(proyecto){
		agregarRequerimiento(cantidad, [new Propiedad("proyecto",proyecto), new Propiedad("rol","project leader")])
		this
	} 
	
	def diseniadorGrafico(){
		agregarRequerimiento(cantidad, [new Propiedad("rol","graphic designer")])
		this
	}

	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ otros recursos ++++++++++++++++++++++++++++++++++++
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	def proyector(){
			agregarRequerimiento(cantidad, [new Propiedad("tipo","proyector")])
		this
	}

	def notebook(){
			agregarRequerimiento(cantidad, [new Propiedad("tipo","notebook")])
		this
	}
	
	def agregarRequerimiento(cant, propiedades){
		cant.with {requerimientos << new Requerimiento(propiedades)}
	}
	
}
