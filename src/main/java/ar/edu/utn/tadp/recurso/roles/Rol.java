package ar.edu.utn.tadp.recurso.roles;

import java.math.BigDecimal;
import java.util.ArrayList;

import ar.edu.utn.tadp.costos.CostoPorHora;
import ar.edu.utn.tadp.recurso.Recurso;

public abstract class Rol {
	public static final Rol ARQUITECTO = new RolSimple("arquitecto",
			new CostoPorHora(BigDecimal.valueOf(25.0)));
	public static final Rol DIS_GRAFICO = new RolSimple("graphic designer",
			new CostoPorHora(BigDecimal.valueOf(29.0)));
	public static final Rol DIS_SISTEMAS = new RolSimple("system designer",
			new CostoPorHora(BigDecimal.valueOf(30.0)));
	public static final Rol PROJECT_LEADER = new Lider("project leader",
			new CostoPorHora(BigDecimal.valueOf(35.0)));
	public static final Rol GERENTE = new RolSimple("gerente",
			new CostoPorHora(BigDecimal.valueOf(50.0)));
	public static final Rol PROGRAMADOR = new RolSimple("programador",
			new CostoPorHora(BigDecimal.valueOf(150.0)));

	private final CostoPorHora costoPorHora;
	protected String nombre;

	protected Rol(final String nombre, final CostoPorHora costoPorHora) {
		this.costoPorHora = costoPorHora;
		this.nombre = nombre;
	}

	public CostoPorHora getCostoPorHora() {
		return this.costoPorHora;
	}

	public abstract void necesitasRecurso(ArrayList<Recurso> asistentes);

	@Override
	public String toString() {
		return this.nombre;
	}
}
