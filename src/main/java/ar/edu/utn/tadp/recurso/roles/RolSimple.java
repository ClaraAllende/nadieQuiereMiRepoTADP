package ar.edu.utn.tadp.recurso.roles;

import java.util.ArrayList;

import ar.edu.utn.tadp.costos.CostoPorHora;
import ar.edu.utn.tadp.recurso.Recurso;

public class RolSimple extends Rol {

	protected RolSimple(final String nombre, final CostoPorHora costoPorHora) {
		super(nombre, costoPorHora);
	}

	@Override
	public void necesitasRecurso(final ArrayList<Recurso> asistentes) {
		// TODO Queda asi? Sin nada?
	}
}
