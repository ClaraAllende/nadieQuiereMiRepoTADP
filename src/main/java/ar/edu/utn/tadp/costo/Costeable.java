package ar.edu.utn.tadp.costo;

import java.math.BigDecimal;

/**
 * Interfaz para todos los objetos costeables.
 * 
 * @version 10-05-2012
 */
public interface Costeable {

	/**
	 * Obtiene el costo.
	 * 
	 * @return Costo.
	 */
	public BigDecimal getCosto();

}
