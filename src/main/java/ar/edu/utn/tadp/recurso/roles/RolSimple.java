package ar.edu.utn.tadp.recurso.roles;

import java.util.ArrayList;

import ar.edu.utn.tadp.costos.CostoPorHora;
import ar.edu.utn.tadp.recurso.Recurso;

public class RolSimple extends Rol {

	protected RolSimple(CostoPorHora costoPorHora) {
		super(costoPorHora);
	}

	@Override
	public void necesitasRecurso(ArrayList<Recurso> asistentes) {
	}

}
