package ar.edu.utn.tadp.proyectos;

import java.math.BigDecimal;

public class ComplejidadComplicada extends ComplejidadMedia {
	
	private BigDecimal costoExtra;

	public ComplejidadComplicada(BigDecimal costoExtra){
		this.costoExtra=costoExtra;
	}

	@Override
	public BigDecimal costo(Tarea tarea) {
		if (tarea.tiempo().intValue()<5){
			return super. costoEmpleado(tarea);
		}
		return super.costoEmpleado(tarea).add(this. costoExtra().multiply(tarea.tiempo()));
	}
	
	public BigDecimal costoExtra(){
		return this.costoExtra;
	}

}
