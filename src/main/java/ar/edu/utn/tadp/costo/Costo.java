package ar.edu.utn.tadp.costo;

import java.math.BigDecimal;

import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Interfaz para las estrategias del cálculo de costo.
 * <p>
 * Implementa patrón Strategy.
 * 
 * @version 11-05-2012
 */
public interface Costo {

	/**
	 * Obtiene el costo.
	 * 
	 * @return Costo.
	 */
	public BigDecimal calcularCostoPor(Reunion reunion);

}
