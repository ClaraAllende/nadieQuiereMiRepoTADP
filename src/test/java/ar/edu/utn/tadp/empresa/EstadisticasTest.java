package ar.edu.utn.tadp.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.agenda.Evento;
import ar.edu.utn.tadp.agenda.TipoEvento;
import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.RecursoIntrospector;
import ar.edu.utn.tadp.recurso.roles.Rol;

import com.google.common.collect.Lists;

public class EstadisticasTest {

	private Empresa empresa = new Empresa();
	private List<TipoEvento> unosEventos;
	private Persona arquitecto1;
	private Persona arquitecto2;
	private Evento evento;
	private Propiedad prop1;
	private Propiedad prop2;
	
	@Before
	public void setUp(){
		unosEventos = Lists.newArrayList(TipoEvento.REUNION);
		arquitecto1 = new Persona(Rol.ARQUITECTO);
		arquitecto1.setSector("Sector7G");
		arquitecto2 = new Persona(Rol.ARQUITECTO);
		arquitecto2.setSector("Sector7G");
		evento = new Evento(new Interval(new DateTime(2012,07,21,8,00), Hours.TWO));
		evento.setTipo(TipoEvento.REUNION);
		arquitecto1.ocupate(evento);
		arquitecto2.ocupate(evento);
		empresa.addRecurso(arquitecto1);
		empresa.addRecurso(arquitecto2);
		prop1 = new Propiedad("rol","arquitecto");
		prop2 = new Propiedad("sector", "Sector7G");
		
	}

	@Test
	public void testeaHorasPorSector(){
		DateTime fecha = new DateTime(2012,07,10,8,00);
		assertEquals(Hours.FOUR, empresa.horasEn(unosEventos, fecha, new Propiedad("sector", "Sector7G") ));
	}

	@Test
	public void testeaHorasPorRol(){
		DateTime fecha = new DateTime(2012,07,10,8,00);
		assertEquals(Hours.FOUR, empresa.horasEn(unosEventos, fecha, new Propiedad("rol", "arquitecto") ));
	}
	
	@Test
	public void recursoIntrospectorTest(){
		assertTrue(RecursoIntrospector.getPropiedadesDe(arquitecto1).contains(prop1));
		assertTrue(RecursoIntrospector.getPropiedadesDe(arquitecto1).contains(prop2));
	}
}
