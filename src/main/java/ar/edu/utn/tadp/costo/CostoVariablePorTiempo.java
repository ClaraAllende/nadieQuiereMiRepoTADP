package ar.edu.utn.tadp.costo;

import java.math.BigDecimal;

import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Calcula costo variable por tiempo de la reunion.
 * <p>
 * Implementa patron Singleton.
 * 
 * @version 11-05-2012
 */
public class CostoVariablePorTiempo implements Costo {

	// Instancia es unica para la clase.
	private static CostoVariablePorTiempo instancia;

	/**
	 * No tiene contructor publico.
	 */
	private CostoVariablePorTiempo() {
		super();
	}

	/**
	 * Forma unica de obtener instancia de la clase.
	 * 
	 * @return Instancia unica de <code>CostoVariablePorTiempo</code>
	 */
	public CostoVariablePorTiempo getInstancia() {
		// Lo hacemos "lazy"
		if (instancia == null) {
			instancia = new CostoVariablePorTiempo();
		}
		return instancia;
	}

	@Override
	public BigDecimal calcularCostoPor(Reunion reunion) {
		// TODO Auto-generated method stub
		return null;
	}
}
