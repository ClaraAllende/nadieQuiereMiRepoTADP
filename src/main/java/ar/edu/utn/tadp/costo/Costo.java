package ar.edu.utn.tadp.costo;

import java.math.BigDecimal;

import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Interfaz para las estrategias del caclulo de costo.
 * <p>
 * Implementa patron Strategy.
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
