package ar.edu.utn.tadp.agenda;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class Rango {
	private DateTime start;
	private DateTime end;
	
	public Rango(DateTime inicio, DateTime fin) {
		this.setStart(inicio);
		this.setEnd(fin);
	}
	
	
	/*
	 * accesors
	 */
	
	public DateTime getStart() {
		return start;
	}
	
	public void setStart(DateTime start) {
		this.start = start;
	}
	
	public DateTime getEnd() {
		return end;
	}
	
	public void setEnd(DateTime end) {
		this.end = end;
	}
	
	
	/*
	 * Methods
	 */
	
	public boolean includes(DateTime aDate) {
		return (this.getStart().isBefore(aDate)) && (this.getEnd().isAfter(aDate));
	}

	public boolean includes(Rango aRange) {
		return (this.includes(aRange.getStart())) && (this.includes(aRange.getEnd()));
	}

	public boolean overlaps(Rango unRango) {
		return this.includes(unRango.getStart()) || this.includes(unRango.getEnd());
	}

	public boolean equals(Rango unRango) {
		return getStart().equals(unRango.getStart()) && getEnd().equals(unRango.getEnd());
	}
	public Period size(){
		return new Period (this.getStart(), this.getEnd());
		
	}

}
