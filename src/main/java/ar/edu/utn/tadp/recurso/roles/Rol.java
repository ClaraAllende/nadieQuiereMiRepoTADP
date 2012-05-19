package ar.edu.utn.tadp.recurso.roles;

import java.math.BigDecimal;

import ar.edu.utn.tadp.costos.CostoPorHora;

public class Rol {
	public static final Rol ARQUITECTO = new Rol(new CostoPorHora(
			BigDecimal.valueOf(25.0)));
	public static final Rol DIS_GRAFICO = new Rol(new CostoPorHora(
			BigDecimal.valueOf(29.0)));
	public static final Rol DIS_SISTEMAS = new Rol(new CostoPorHora(
			BigDecimal.valueOf(30.0)));
	public static final Rol PROYECT_LIDER = new Rol(new CostoPorHora(
			BigDecimal.valueOf(35.0)));
	public static final Rol GERENTE = new Rol(new CostoPorHora(
			BigDecimal.valueOf(50.0)));
	public static final Rol PROGRAMADOR = new Rol(new CostoPorHora(
			BigDecimal.valueOf(150.0)));

	private CostoPorHora costoPorHora;

	public Rol(CostoPorHora costoPorHora) {
		this.costoPorHora = costoPorHora;
	}

	public CostoPorHora getCostoPorHora() {
		return this.costoPorHora;
	}
}
