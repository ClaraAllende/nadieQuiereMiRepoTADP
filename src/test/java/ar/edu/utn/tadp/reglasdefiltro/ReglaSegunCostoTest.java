package ar.edu.utn.tadp.reglasdefiltro;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.reunion.Reunion;

public class ReglaSegunCostoTest {

	private ReglaDeFiltrado reglaDeFiltrado;
	
	@Test
	public void testFiltrarSegunCosto() {
		Reunion reunion = mock(Reunion.class);
		
		Persona persona1 = mock(Persona.class);
		when(persona1.dameTuCostoPara(Mockito.any(Reunion.class))).thenReturn(new BigDecimal(50.0));
		
		Persona persona2 = mock(Persona.class);
		when(persona2.dameTuCostoPara(Mockito.any(Reunion.class))).thenReturn(new BigDecimal(20.0));
		
		Persona persona3 = mock(Persona.class);
		when(persona3.dameTuCostoPara(Mockito.any(Reunion.class))).thenReturn(new BigDecimal(15.0));
		
		reglaDeFiltrado = new ReglaSegunCosto(reunion);
		
		Collection<Persona> filtrada = reglaDeFiltrado.filtrar(Arrays.asList(persona1, persona2, persona3));
		Assert.assertEquals(1, filtrada.size());
		Assert.assertTrue(filtrada.contains(persona3));
	}
	
	@Test
	public void testFiltrarSegunCostosIguales() {
		Reunion reunion = mock(Reunion.class);
		
		Persona persona1 = mock(Persona.class);
		when(persona1.dameTuCostoPara(Mockito.any(Reunion.class))).thenReturn(new BigDecimal(100.0));
		
		Persona persona2 = mock(Persona.class);
		when(persona2.dameTuCostoPara(Mockito.any(Reunion.class))).thenReturn(new BigDecimal(50.0));

		Persona persona3 = mock(Persona.class);
		when(persona3.dameTuCostoPara(Mockito.any(Reunion.class))).thenReturn(new BigDecimal(50.0));
		
		reglaDeFiltrado = new ReglaSegunCosto(reunion);
		
		Collection<Persona> filtrada = reglaDeFiltrado.filtrar(Arrays.asList(persona1, persona2, persona3));
		Assert.assertEquals(2, filtrada.size());
		Assert.assertTrue(filtrada.contains(persona2));
		Assert.assertTrue(filtrada.contains(persona3));
	}
}
