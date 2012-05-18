package ar.edu.utn.tadp.empresa;
import ar.edu.utn.tadp.requerimiento.*;
import ar.edu.utn.tadp.reunion.*;
import ar.edu.utn.tadp.recurso.*;
import ar.edu.utn.tadp.excepcion.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa a una Empresa. Contiene todos los recursos.
 */
public class Empresa {

	private List<Recurso> recursos = new ArrayList<Recurso>();

	public Reunion createReunion(Persona anfitrion, List<Requerimiento> criterios) {
		try {
			// TODO Auto-generated method stub
		} catch (UserException e) {
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
