package ar.edu.utn.tadp.empresa;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.costos.Costeable;
import ar.edu.utn.tadp.costos.Costo;
import ar.edu.utn.tadp.costos.CostoPorHora;
import ar.edu.utn.tadp.reunion.Reunion;

public class CostoTest {

	private Reunion reunion;

	@Before 
	public void setUp() {
		reunion = mock(Reunion.class);
		when(reunion.getCantidadDePersonasQueNecesitanTransporte()).thenReturn(7l);
		when(reunion.getDuracionDeReunion()).thenReturn(4l);
	}
	
	@Test
	public void testCostoDeTransporte() {
		Costeable costeable = Costo.TRANSPORTE;
		
		assertEquals(BigDecimal.valueOf(175.0), costeable.dameTuCostoPara(reunion));
	}

	@Test
	public void testCostoDeCatering() { 
		Costeable costeable = Costo.CATERING;
		
		assertEquals(BigDecimal.valueOf(400.0), costeable.dameTuCostoPara(reunion));
	}
	
	@Test
	public void testCostoPorHora() { 
		Costeable costeable = new CostoPorHora(BigDecimal.valueOf(30.0));
		
		assertEquals(BigDecimal.valueOf(120.0), costeable.dameTuCostoPara(reunion));
		
		
	}
}
