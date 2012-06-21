package ar.edu.utn.tadp.empresa;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.organizables.Reunion;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.roles.Rol;

public class PersonaTest {

	private Persona persona;
	
	private Persona personaDelMismoProyecto;
	private Persona personaDeOtroProyecto;

	@Before 
	public void setUp() {
		persona = new Persona(Rol.PROGRAMADOR);
		persona.setProyecto("Tratar de conquistar al mundo");
		
		personaDelMismoProyecto = mock(Persona.class);
		when(personaDelMismoProyecto.getProyecto()).thenReturn("Tratar de conquistar al mundo");
		
		personaDeOtroProyecto = mock(Persona.class);
		when(personaDeOtroProyecto.getProyecto()).thenReturn("Salvar a ciudad gotica");
	}
	
	@Test
	public void testDameTuCostoParaReunionDelMismoProyecto() {
		Reunion reunionDelMismoProyecto = mock(Reunion.class);
		when(reunionDelMismoProyecto.getDuracion()).thenReturn(3l);
		when(reunionDelMismoProyecto.getOrganizador()).thenReturn(personaDelMismoProyecto);
		
		assertEquals(BigDecimal.valueOf(0.0), persona.dameTuCostoPara(reunionDelMismoProyecto));
	}
	
	@Test
	public void testDameTuCostoParaReunionDeDistintoProyecto() {
		Reunion reunionDelMismoProyecto = mock(Reunion.class);
		when(reunionDelMismoProyecto.getDuracion()).thenReturn(3l);
		when(reunionDelMismoProyecto.getOrganizador()).thenReturn(personaDeOtroProyecto);
		
		assertEquals(BigDecimal.valueOf(450.0), persona.dameTuCostoPara(reunionDelMismoProyecto));
	}

	@Test
	public void testEsDelMismoProyecto() {
		assertTrue(persona.esDelMismoProyecto(personaDelMismoProyecto));
		assertFalse(persona.esDelMismoProyecto(personaDeOtroProyecto));
	}

}
