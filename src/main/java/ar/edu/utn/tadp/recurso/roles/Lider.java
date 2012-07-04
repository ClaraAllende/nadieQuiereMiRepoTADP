package ar.edu.utn.tadp.recurso.roles;

import java.util.ArrayList;

import ar.edu.utn.tadp.costos.CostoPorHora;
import ar.edu.utn.tadp.recurso.Recurso;

public class Lider extends Rol {

	protected Lider(final String nombre, final CostoPorHora costoPorHora) {
		super(nombre, costoPorHora);
	}

	@Override
	public void necesitasRecurso(final ArrayList<Recurso> recursos) {
		recursos.add(Recurso.CATERING);
	}
}
