package ar.edu.utn.tadp.agenda;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;

public class AgendaTest {

	private Agenda agenda;
	private Interval intervalo1;
	private Interval intervalo2;
	private Interval intervalo3;
	private DateTime fecha1;
	private DateTime fecha2;
	private DateTime fecha3;
	private DateTime fecha4;
	private DateTime fecha5;

	@Before
	public void setUp() {
		agenda = new Agenda();
		
		fecha1 = new DateTime(2012,05,12,8,00,00);
		fecha2 = new DateTime(2012,05,12,9,30,00);
		fecha3 = new DateTime(2012,05,12,12,30,00);
		fecha4 = new DateTime(2012,05,12,15,00,00);
		fecha5 = new DateTime(2012,05,12,21,45,00);
		
		intervalo1 = new Interval(fecha1, fecha3);
		intervalo2 = new Interval(DateTime.now(),Agenda.FUTURO);
		intervalo3 = new Interval(fecha4, fecha5);
	}

	@Test
	public void testOcupateDurante() {
		agenda.ocupateDurante(intervalo1);	
		Assert.assertTrue(agenda.getHorariosOcupados().contains(intervalo1));
	}

	@Test
	public void testDisponibleDurante() {
		Assert.assertTrue(agenda.disponibleDurante(intervalo3));
	}

	@Test
	public void testHorariosDisponibles() {
		List<Interval> al = new ArrayList<Interval>();
		al.add(intervalo2);
		Assert.assertEquals(agenda.horariosDisponibles(), al);
	}

	@Test
	public void testIntervaloDisponibleDe() {
		Interval interval = agenda.intervaloDisponibleDe(intervalo1.toDuration());
		boolean condition = interval.equals(intervalo2);
		Assert.assertTrue(condition);
		
		//se puede refactorizar todo para que quede en una linea, pero se ve mejor así.
	}

}
