package ar.edu.utn.tadp.reglasdefiltro;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;

public class ReglaSegunHorasTest {

	private ReglaDeFiltrado reglaDeFiltrado;

	@Test
	public void testFiltrarSegunHoras() {
		Persona persona1 = mock(Persona.class);
		when(persona1.getHorasEnReunionesDeLaSemana()).thenReturn(20);
		
		Persona persona2 = mock(Persona.class);
		when(persona2.getHorasEnReunionesDeLaSemana()).thenReturn(25);
		
		Persona persona3 = mock(Persona.class);
		when(persona3.getHorasEnReunionesDeLaSemana()).thenReturn(100);
		
		reglaDeFiltrado = new ReglaSegunHoras();
		
		List<Recurso> personas = new ArrayList<Recurso>();
		personas.add(persona1);
		personas.add(persona2);
		personas.add(persona3);
		Collection<Recurso> filtrada = reglaDeFiltrado.filtrar(personas);
		Assert.assertEquals(1, filtrada.size());
		Assert.assertTrue(filtrada.contains(persona1));
	}
	
	@Test
	public void testFiltrarSegunHorasIguales() {
		Persona persona1 = mock(Persona.class);
		when(persona1.getHorasEnReunionesDeLaSemana()).thenReturn(6);
		
		Persona persona2 = mock(Persona.class);
		when(persona2.getHorasEnReunionesDeLaSemana()).thenReturn(6);
		
		Persona persona3 = mock(Persona.class);
		when(persona3.getHorasEnReunionesDeLaSemana()).thenReturn(20);
		
		reglaDeFiltrado = new ReglaSegunHoras();
		
		List<Recurso> personas = new ArrayList<Recurso>();
		personas.add(persona1);
		personas.add(persona2);
		personas.add(persona3);
		Collection<Recurso> filtrada = reglaDeFiltrado.filtrar(personas);
		Assert.assertEquals(2, filtrada.size());
		Assert.assertTrue(filtrada.contains(persona1));
		Assert.assertTrue(filtrada.contains(persona2));
	}
}
