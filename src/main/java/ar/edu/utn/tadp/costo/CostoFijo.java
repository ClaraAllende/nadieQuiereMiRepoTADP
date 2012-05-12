package ar.edu.utn.tadp.costo;

import java.math.BigDecimal;

import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Calcula costo fijo.
 * <p>
 * Implementa patron Singleton.
 * 
 * @version 11-05-2012
 */
public class CostoFijo implements Costo {

	// Instancia es unica para la clase.
	private static CostoFijo instancia;

	/**
	 * No tiene contructor publico.
	 */
	private CostoFijo() {
		super();
	}

	/**
	 * Forma unica de obtener instancia de la clase.
	 * 
	 * @return Instancia unica de <code>CostoFijo</code>
	 */
	public CostoFijo getInstancia() {
		// Lo hacemos "lazy"
		if (instancia == null) {
			instancia = new CostoFijo();
		}
		return instancia;
	}

	@Override
	public BigDecimal calcularCostoPor(Reunion reunion) {
		// TODO Auto-generated method stub
		return null;
	}
}
