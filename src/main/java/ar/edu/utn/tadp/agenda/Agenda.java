package ar.edu.utn.tadp.agenda;

import java.util.List;

public class Agenda {

	private List<Rango> horariosOcupados;
	
	
	public boolean disponibleDurante (Rango unRango) {
		//puede mejorarse con Iterables.any(horariosOcupados, Predicate)
		for (Rango horario : this.horariosOcupados) {
				if(horario.overlaps(unRango)) return false;
		}
		return true;
	}
}
