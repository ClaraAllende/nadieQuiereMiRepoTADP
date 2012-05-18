package ar.edu.utn.tadp.recurso.roles;

import java.math.BigDecimal;

import ar.edu.utn.tadp.costos.CostoPorHora;

public class Rol {

	public static final Rol DISENIADOR = new Rol(new CostoPorHora(BigDecimal.valueOf(25.0)));
	public static final Rol LIDER = new Rol(new CostoPorHora(BigDecimal.valueOf(35.0)));
	public static final Rol GERENTE = new Rol(new CostoPorHora(BigDecimal.valueOf(50.0)));
	public static final Rol DESARROLLADOR = new Rol(new CostoPorHora(BigDecimal.valueOf(150.0)));
	
	private CostoPorHora costoPorHora;

	public Rol(CostoPorHora costoPorHora) {
		this.costoPorHora = costoPorHora;
	}

	public CostoPorHora getCostoPorHora() {
		return this.costoPorHora;
	}

}
