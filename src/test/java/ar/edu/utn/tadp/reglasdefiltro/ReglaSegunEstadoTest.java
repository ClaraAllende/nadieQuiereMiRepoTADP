package ar.edu.utn.tadp.reglasdefiltro;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.utn.tadp.recurso.Estado;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;

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
		
		List<Recurso> personas = new ArrayList<Recurso>();
		personas.add(persona1);
		personas.add(persona2);
		personas.add(persona3);
		Collection<Recurso> filtrada = reglaDeFiltrado.filtrar(personas);
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
		
		List<Recurso> personas = new ArrayList<Recurso>();
		personas.add(persona1);
		personas.add(persona2);
		personas.add(persona3);
		Collection<Recurso> filtrada = reglaDeFiltrado.filtrar(personas);
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
