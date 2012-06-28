package ar.edu.utn.tadp.propiedad;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Contiene los test para <code>Propiedad</code>
 * 
 * @version 18-05-2012
 */
public class PropiedadTest {

	private final String tipoMinuscula = "tipo";
	private final String tipoMayuscula = "TipO";
	private final String valorMinuscula = "valor";
	private final String valorMayuscula = "VaLoR";

	@Test
	public void esIgualASiMisma() {
		Propiedad unaPropiedad = new Propiedad(tipoMayuscula, valorMayuscula);
		Assert.assertTrue(unaPropiedad.equals(unaPropiedad));
	}

	@Test
	public void esIgualAOtra() {
		Propiedad unaPropiedad = new Propiedad(tipoMayuscula, valorMinuscula);
		Propiedad otraPropiedad = new Propiedad(tipoMinuscula, valorMayuscula);
		Assert.assertTrue(unaPropiedad.equals(otraPropiedad));
	}

	@Test
	public void esDistintaDeNull() {
		Propiedad unaPropiedad = new Propiedad(tipoMayuscula, valorMinuscula);
		Assert.assertFalse(unaPropiedad.equals(null));
	}

	@Test
	public void esDistintaDeOtras() {
		Propiedad unaPropiedad = new Propiedad(tipoMayuscula, valorMinuscula);
		Propiedad otraPropiedad1 = new Propiedad(tipoMinuscula + 1,
				valorMayuscula);
		Propiedad otraPropiedad2 = new Propiedad(tipoMinuscula,
				valorMayuscula + 2);
		Propiedad otraPropiedad3 = new Propiedad(tipoMinuscula + 3,
				valorMayuscula + 3);
		Assert.assertFalse(unaPropiedad.equals(otraPropiedad1));
		Assert.assertFalse(unaPropiedad.equals(otraPropiedad2));
		Assert.assertFalse(unaPropiedad.equals(otraPropiedad3));
	}
}
