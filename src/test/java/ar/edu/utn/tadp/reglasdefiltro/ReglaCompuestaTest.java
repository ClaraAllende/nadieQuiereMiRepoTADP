package ar.edu.utn.tadp.reglasdefiltro;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import ar.edu.utn.tadp.organizables.Reunion;
import ar.edu.utn.tadp.recurso.Estado;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;

public class ReglaCompuestaTest {

	private ReglaDeFiltrado reglaDeFiltrado;
	
	@Test
	public void testFiltrarSegunUbicacion() {
		Reunion reunion = mock(Reunion.class);
		
		when(reunion.getUbicacion()).thenReturn("Melmac");
		
		Persona persona1 = mock(Persona.class);
		when(persona1.getUbicacion()).thenReturn("Ciudad Gotica");
		when(persona1.dameTuCostoPara(Mockito.any(Reunion.class))).thenReturn(new BigDecimal(50.0));
		when(persona1.getHorasEnReunionesDeLaSemana()).thenReturn(20);
		when(persona1.getEstado()).thenReturn(Estado.POCAS_REUNIONES);

		Persona persona2 = mock(Persona.class);
		when(persona2.getUbicacion()).thenReturn("Cripton");
		when(persona2.dameTuCostoPara(Mockito.any(Reunion.class))).thenReturn(new BigDecimal(20.0));
		when(persona2.getHorasEnReunionesDeLaSemana()).thenReturn(20);
		when(persona2.getEstado()).thenReturn(Estado.NORMAL);

		Persona persona3 = mock(Persona.class);
		when(persona3.getUbicacion()).thenReturn("Melmac");
		when(persona3.dameTuCostoPara(Mockito.any(Reunion.class))).thenReturn(new BigDecimal(50.0));
		when(persona3.getHorasEnReunionesDeLaSemana()).thenReturn(20);
		when(persona3.getEstado()).thenReturn(Estado.EXCESIVAS_REUNIONES);

		List<ReglaDeFiltrado> listaDeReglas = Arrays.asList(new ReglaSegunEstado(), 
															new ReglaSegunHoras(),
															new ReglaSegunCosto(reunion),
															new ReglaSegunUbicacion(reunion));
		
		reglaDeFiltrado = new ReglaCompuesta(listaDeReglas);
		
		List<Recurso> personas = new ArrayList<Recurso>();
		personas.add(persona1);
		personas.add(persona2);
		personas.add(persona3);
		Collection<Recurso> filtrada = reglaDeFiltrado.filtrar(personas);
		Assert.assertEquals(1, filtrada.size());
		Assert.assertTrue(filtrada.contains(persona1));
	}
	
	@Test
	public void testFiltrarSegunReglasIguales() {
		Reunion reunion = mock(Reunion.class);
		
		when(reunion.getUbicacion()).thenReturn("Melmac");
		
		Persona persona1 = mock(Persona.class);
		when(persona1.getUbicacion()).thenReturn("Ciudad Gotica");
		when(persona1.dameTuCostoPara(Mockito.any(Reunion.class))).thenReturn(new BigDecimal(20.0));
		when(persona1.getHorasEnReunionesDeLaSemana()).thenReturn(20);
		when(persona1.getEstado()).thenReturn(Estado.EXCESIVAS_REUNIONES);

		Persona persona2 = mock(Persona.class);
		when(persona2.getUbicacion()).thenReturn("Melmac");
		when(persona2.dameTuCostoPara(Mockito.any(Reunion.class))).thenReturn(new BigDecimal(50.0));
		when(persona1.getHorasEnReunionesDeLaSemana()).thenReturn(20);
		when(persona2.getEstado()).thenReturn(Estado.NORMAL);

		Persona persona3 = mock(Persona.class);
		when(persona3.getUbicacion()).thenReturn("Melmac");
		when(persona3.dameTuCostoPara(Mockito.any(Reunion.class))).thenReturn(new BigDecimal(50.0));
		when(persona1.getHorasEnReunionesDeLaSemana()).thenReturn(20);
		when(persona3.getEstado()).thenReturn(Estado.NORMAL);
		
		List<ReglaDeFiltrado> listaDeReglas = Arrays.asList(new ReglaSegunEstado(), 
				new ReglaSegunHoras(),
				new ReglaSegunCosto(reunion),
				new ReglaSegunUbicacion(reunion));
		
		reglaDeFiltrado = new ReglaCompuesta(listaDeReglas);
		
		List<Recurso> personas = new ArrayList<Recurso>();
		personas.add(persona1);
		personas.add(persona2);
		personas.add(persona3);
		Collection<Recurso> filtrada = reglaDeFiltrado.filtrar(personas);
		Assert.assertEquals(1, filtrada.size());
		Assert.assertTrue(filtrada.contains(persona2));
	}
}
