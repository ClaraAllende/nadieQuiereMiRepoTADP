package ar.utn.edu.tadp.agenda;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.agenda.Rango;

public class RangoTest {

	private Rango rango;
	private Period periodo;
	private Object periodo2;

	@Before
	public void setUp() {
		rango = new Rango(new DateTime(2012,05,01,20,20), DateTime.now());
		periodo = new Period (rango.getStart(),rango.getEnd());
		periodo2 = new Period (rango.getStart(),rango.getEnd());
	}
	@Test
	public void test() {
		Assert.assertTrue(periodo.equals(periodo2));
	}

}
