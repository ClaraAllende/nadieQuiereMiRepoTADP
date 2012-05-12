package ar.edu.utn.tadp.costo;

import java.math.BigDecimal;

import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Calcula costo variable por cantidad de personas que tiene que viajar al lugar
 * de la reunion.
 * <p>
 * Implementa patron Singleton.
 * 
 * @version 11-05-2012
 */
public class CostoVariablePorViajantes implements Costo {

	// Instancia es unica para la clase.
	private static CostoVariablePorViajantes instancia;

	/**
	 * No tiene contructor publico.
	 */
	private CostoVariablePorViajantes() {
		super();
	}

	/**
	 * Forma unica de obtener instancia de la clase.
	 * 
	 * @return Instancia unica de <code>CostoVariablePorViajantes</code>
	 */
	public CostoVariablePorViajantes getInstancia() {
		// Lo hacemos "lazy"
		if (instancia == null) {
			instancia = new CostoVariablePorViajantes();
		}
		return instancia;
	}

	@Override
	public BigDecimal calcularCostoPor(Reunion reunion) {
		// TODO Auto-generated method stub
		return null;
	}
}
