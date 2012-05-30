package ar.edu.utn.tadp.reglasdefiltro;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.utn.tadp.recurso.Estado;
import ar.edu.utn.tadp.recurso.Persona;

public class ReglaSegunEstadoTest {
	ReglaDeFiltrado reglaDeFiltrado;
	
	@Test
	public void testFiltrarSegunMultiplesEstados() {
		Persona persona1 = mock(Persona.class);
		when(persona1.getEstado()).thenReturn(Estado.POCAS_REUNIONES);
		
		Persona persona2 = mock(Persona.class);
		when(persona2.getEstado()).thenReturn(Estado.NORMAL);
		
		Persona persona3 = mock(Persona.class);
		when(persona3.getEstado()).thenReturn(Estado.EXCESIVAS_REUNIONES);
		
		reglaDeFiltrado = new ReglaSegunEstado();
		
		Collection<Persona> filtrada = reglaDeFiltrado.filtrar(Arrays.asList(persona1, persona2, persona3));
		Assert.assertEquals(1, filtrada.size());
		Assert.assertTrue(filtrada.contains(persona1));
	}
	
	@Test
	public void testFiltrarSegunMultiplesEstadosExcesivasReuniones() {
		Persona persona1 = mock(Persona.class);
		when(persona1.getEstado()).thenReturn(Estado.EXCESIVAS_REUNIONES);
		
		Persona persona2 = mock(Persona.class);
		when(persona2.getEstado()).thenReturn(Estado.NORMAL);
		
		Persona persona3 = mock(Persona.class);
		when(persona3.getEstado()).thenReturn(Estado.EXCESIVAS_REUNIONES);
		
		reglaDeFiltrado = new ReglaSegunEstado();
		
		Collection<Persona> filtrada = reglaDeFiltrado.filtrar(Arrays.asList(persona1, persona2, persona3));
		Assert.assertEquals(1, filtrada.size());
		Assert.assertTrue(filtrada.contains(persona2));
	}

	@Test
	public void testFiltrarSegunMultiplesEstadosNormal() {
		Persona persona1 = mock(Persona.class);
		when(persona1.getEstado()).thenReturn(Estado.NORMAL);
		
		Persona persona2 = mock(Persona.class);
		when(persona2.getEstado()).thenReturn(Estado.NORMAL);
		
		Persona persona3 = mock(Persona.class);
		when(persona3.getEstado()).thenReturn(Estado.EXCESIVAS_REUNIONES);
		
		reglaDeFiltrado = new ReglaSegunEstado();
		
		Collection<Persona> filtrada = reglaDeFiltrado.filtrar(Arrays.asList(persona1, persona2, persona3));
		Assert.assertEquals(2, filtrada.size());
		Assert.assertTrue(filtrada.contains(persona1));
		Assert.assertTrue(filtrada.contains(persona2));
	}
}
