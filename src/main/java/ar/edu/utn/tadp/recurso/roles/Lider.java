package ar.edu.utn.tadp.recurso.roles;

import java.util.ArrayList;

import ar.edu.utn.tadp.costos.CostoPorHora;
import ar.edu.utn.tadp.recurso.Recurso;

public class Lider extends Rol {

	protected Lider(CostoPorHora costoPorHora) {
		super(costoPorHora);
	}

	@Override
	public void necesitasRecurso(ArrayList<Recurso> recursos) {
		recursos.add(Recurso.CATERING);

	}

}
