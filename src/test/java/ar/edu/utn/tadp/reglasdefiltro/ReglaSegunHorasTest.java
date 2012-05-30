package ar.edu.utn.tadp.reglasdefiltro;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.utn.tadp.recurso.Persona;

public class ReglaSegunHorasTest {

	private ReglaDeFiltrado reglaDeFiltrado;

	@Test
	public void testFiltrarSegunHoras() {
		Persona persona1 = mock(Persona.class);
		when(persona1.getCantidadDeHorasDeReunionEnLaUltimaSemana()).thenReturn(20l);
		
		Persona persona2 = mock(Persona.class);
		when(persona2.getCantidadDeHorasDeReunionEnLaUltimaSemana()).thenReturn(25l);
		
		Persona persona3 = mock(Persona.class);
		when(persona3.getCantidadDeHorasDeReunionEnLaUltimaSemana()).thenReturn(100l);
		
		reglaDeFiltrado = new ReglaSegunHoras();
		
		Collection<Persona> filtrada = reglaDeFiltrado.filtrar(Arrays.asList(persona1, persona2, persona3));
		Assert.assertEquals(1, filtrada.size());
		Assert.assertTrue(filtrada.contains(persona1));
	}
	
	@Test
	public void testFiltrarSegunHorasIguales() {
		Persona persona1 = mock(Persona.class);
		when(persona1.getCantidadDeHorasDeReunionEnLaUltimaSemana()).thenReturn(6l);
		
		Persona persona2 = mock(Persona.class);
		when(persona2.getCantidadDeHorasDeReunionEnLaUltimaSemana()).thenReturn(6l);
		
		Persona persona3 = mock(Persona.class);
		when(persona3.getCantidadDeHorasDeReunionEnLaUltimaSemana()).thenReturn(20l);
		
		reglaDeFiltrado = new ReglaSegunHoras();
		
		Collection<Persona> filtrada = reglaDeFiltrado.filtrar(Arrays.asList(persona1, persona2, persona3));
		Assert.assertEquals(2, filtrada.size());
		Assert.assertTrue(filtrada.contains(persona1));
		Assert.assertTrue(filtrada.contains(persona2));
	}
}
