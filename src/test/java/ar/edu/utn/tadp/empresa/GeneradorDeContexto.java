package ar.edu.utn.tadp.empresa;

import java.util.ArrayList;

import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.recurso.roles.Rol;

public class GeneradorDeContexto {

	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Personas ++++++++++++++++++++++++++++++++++++++++++
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public Persona newProgramador(Propiedad proyecto, Propiedad sector, Propiedad edificio) {
		return this.newPersona(Rol.PROGRAMADOR, proyecto, sector, edificio);
	}

	public Persona newArquitecto(Propiedad proyecto, Propiedad sector, Propiedad edificio) {
		return this.newPersona(Rol.ARQUITECTO, proyecto, sector, edificio);
	}
	
	public Persona newGerente(Propiedad proyecto, Propiedad sector, Propiedad edificio) {
		return this.newPersona(Rol.GERENTE, proyecto, sector, edificio);
	}
	
	public Persona newProjectLeader(Propiedad proyecto, Propiedad sector, Propiedad edificio) {
		return this.newPersona(Rol.PROYECT_LEADER, proyecto, sector, edificio);
	}
	
	private Persona newPersona(Rol rol, Propiedad proyecto, Propiedad sector, Propiedad edificio) {
		Persona persona = new Persona(rol);
		persona.setProyecto(proyecto.getValor()).setEdificio(edificio.getValor());
		if (sector != null){
			persona.setSector(sector.getValor());
		}
		return persona;
	}

	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Recursos ++++++++++++++++++++++++++++++++++++++++++
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public Recurso newSala(Propiedad edificio) {
		return this.newRecurso(new Propiedad("tipo", "Sala"), edificio);
	}

	public Recurso newProyector(Propiedad edificio) {
		return this.newRecurso(new Propiedad("tipo", "Proyector"), edificio);
	}

	private Recurso newRecurso(Propiedad tipo, Propiedad edificio) {
		Recurso recurso = new Recurso();
		recurso.setTipo(tipo.getValor());
		recurso.setEdificio(edificio.getValor());
		return recurso;
	}

	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Empresa +++++++++++++++++++++++++++++++++++++++++++
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public Empresa newEmpresa(ArrayList<Recurso> recursos) {
		Empresa empresa = new Empresa();
		for (Recurso recurso : recursos) {
			empresa.addRecurso(recurso);
		}
		return empresa;
	}


}
