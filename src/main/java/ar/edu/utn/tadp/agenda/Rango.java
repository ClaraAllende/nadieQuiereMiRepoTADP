package ar.edu.utn.tadp.agenda;

import org.joda.time.LocalDateTime;

public class Rango {
	private LocalDateTime start;
	private LocalDateTime end;
	
	public boolean includes(LocalDateTime aDate) {
		return (this.start.isBefore(aDate)) && (this.end.isAfter(aDate));
	}

	public boolean includes(Rango aRange) {
		return (this.includes(aRange.start)) && (this.includes(aRange.end));
	}

	public boolean overlaps(Rango unRango) {
		return this.includes(unRango.start) || this.includes(unRango.end);
	}

	public boolean equals(Rango unRango) {
		return start.equals(unRango.start) && end.equals(unRango.end);
	}

}
