package ar.edu.utn.tadp.recurso.roles;

import java.math.BigDecimal;
import java.util.ArrayList;

import ar.edu.utn.tadp.costos.CostoPorHora;
import ar.edu.utn.tadp.recurso.Recurso;

public abstract class Rol {
	public static final Rol ARQUITECTO = new RolSimple(new CostoPorHora(
			BigDecimal.valueOf(25.0)));
	public static final Rol DIS_GRAFICO = new RolSimple(new CostoPorHora(
			BigDecimal.valueOf(29.0)));
	public static final Rol DIS_SISTEMAS = new RolSimple(new CostoPorHora(
			BigDecimal.valueOf(30.0)));
	public static final Rol PROYECT_LIDER = new Lider(new CostoPorHora(
			BigDecimal.valueOf(35.0)));
	public static final Rol GERENTE = new RolSimple(new CostoPorHora(
			BigDecimal.valueOf(50.0)));
	public static final Rol PROGRAMADOR = new RolSimple(new CostoPorHora(
			BigDecimal.valueOf(150.0)));

	private CostoPorHora costoPorHora;

	protected Rol(CostoPorHora costoPorHora) {
		this.costoPorHora = costoPorHora;
	}

	public CostoPorHora getCostoPorHora() {
		return this.costoPorHora;
	}

	public abstract void necesitasRecurso(ArrayList<Recurso> asistentes);
}
