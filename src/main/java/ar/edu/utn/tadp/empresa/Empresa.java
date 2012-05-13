package ar.edu.utn.tadp.empresa;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.tadp.criterio.Requisito;
import ar.edu.utn.tadp.excepcion.ExcepcionParaUsuario;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Representa a una Empresa. Contiene todos los recursos.
 */
public class Empresa {

	private List<Recurso> recursos = new ArrayList<Recurso>();

	public Reunion createReunion(Persona anfitrion, List<Requisito> criterios) {
		try {
			// TODO Auto-generated method stub
		} catch (ExcepcionParaUsuario e) {
			// TODO: handle exception
		}
		return null;
	}

	public void addRecurso(Recurso recurso) {
		this.recursos.add(recurso);
	}

	public void removeRecurso(Recurso recurso) {
		this.recursos.remove(recurso);
	}

	public void removeAllRecurso() {
		this.recursos.removeAll(this.recursos);
	}
}
