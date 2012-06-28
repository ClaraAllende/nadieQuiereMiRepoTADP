package ar.edu.utn.tadp.proyectos;

import java.math.BigDecimal;

public class ComplejidadMedia implements Complejidad {

	@Override
	public BigDecimal costo(Tarea tarea) {
		BigDecimal costoEmpleado=  costoEmpleado(tarea);
		return costoEmpleado.add(costoEmpleado.multiply(costoEmpleado));
	}

	protected BigDecimal costoEmpleado(Tarea tarea) {
		return tarea.getEmpleadoResponsable().getRol().getCostoPorHora().getCosto();
	}

}
