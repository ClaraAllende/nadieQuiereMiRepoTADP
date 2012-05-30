package ar.edu.utn.tadp.reglasdefiltro;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.reunion.Reunion;

public class ReglaSegunUbicacionTest {

	private ReglaDeFiltrado reglaDeFiltrado;
	
	@Test
	public void testFiltrarSegunUbicacion() {
		Reunion reunion = mock(Reunion.class);
		when(reunion.getUbicacion()).thenReturn("Melmac");
		Persona persona1 = mock(Persona.class);
		when(persona1.getUbicacion()).thenReturn("Ciudad Gotica");
		Persona persona2 = mock(Persona.class);
		when(persona2.getUbicacion()).thenReturn("Cripton");
		Persona persona3 = mock(Persona.class);
		when(persona3.getUbicacion()).thenReturn("Melmac");
		
		reglaDeFiltrado = new ReglaSegunUbicacion(reunion);
		
		Collection<Persona> filtrada = reglaDeFiltrado.filtrar(Arrays.asList(persona1, persona2, persona3));
		Assert.assertEquals(1, filtrada.size());
		Assert.assertTrue(filtrada.contains(persona3));
	}
	
	@Test
	public void testFiltrarSegunUbicacionesIguales() {
		Reunion reunion = mock(Reunion.class);
		when(reunion.getUbicacion()).thenReturn("Cripton");
		Persona persona1 = mock(Persona.class);
		when(persona1.getUbicacion()).thenReturn("Ciudad Gotica");
		Persona persona2 = mock(Persona.class);
		when(persona2.getUbicacion()).thenReturn("Cripton");
		Persona persona3 = mock(Persona.class);
		when(persona3.getUbicacion()).thenReturn("Cripton");
		
		reglaDeFiltrado = new ReglaSegunUbicacion(reunion);
		
		Collection<Persona> filtrada = reglaDeFiltrado.filtrar(Arrays.asList(persona1, persona2, persona3));
		Assert.assertEquals(2, filtrada.size());
		Assert.assertTrue(filtrada.contains(persona2));
		Assert.assertTrue(filtrada.contains(persona3));
	}
}
