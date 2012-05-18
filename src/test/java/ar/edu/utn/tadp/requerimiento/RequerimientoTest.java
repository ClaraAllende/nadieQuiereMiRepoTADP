package ar.edu.utn.tadp.requerimiento;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.Recurso;

public class RequerimientoTest {

	private Propiedad tipoProyector = new Propiedad("tipo", "Proyector");
	private Propiedad tipoNotebook = new Propiedad("Tipo", "NoteBook");
	private Propiedad edificioCatalinas = new Propiedad("edificio", "catalinas");
	private Propiedad edificioMadero = new Propiedad("edificio", "Madero");
	private Recurso proyector1;
	private Recurso proyector2;
	private Recurso proyector3;
	private Recurso notebook1;
	private Recurso notebook2;
	private Recurso notebook3;
	private ArrayList<Recurso> proyectores;
	private ArrayList<Recurso> notebooks;
	private Requerimiento requerimiento;

	@Before
	public void crearContexto() {
		proyector1 = new Recurso();
		proyector1.addPropiedad(tipoProyector);
		proyector1.addPropiedad(edificioCatalinas);
		proyector2 = new Recurso();
		proyector2.addPropiedad(tipoProyector);
		proyector2.addPropiedad(edificioCatalinas);
		proyector3 = new Recurso();
		proyector3.addPropiedad(tipoProyector);
		proyector3.addPropiedad(edificioMadero);
		notebook1 = new Recurso();
		notebook1.addPropiedad(tipoNotebook);
		notebook1.addPropiedad(edificioCatalinas);
		notebook2 = new Recurso();
		notebook2.addPropiedad(tipoNotebook);
		notebook2.addPropiedad(edificioMadero);
		notebook3 = new Recurso();
		notebook3.addPropiedad(tipoNotebook);
		notebook3.addPropiedad(edificioMadero);
		notebooks = new ArrayList<Recurso>();
		notebooks.add(notebook1);
		notebooks.add(notebook2);
		notebooks.add(notebook3);
		proyectores = new ArrayList<Recurso>();
		proyectores.add(proyector1);
		proyectores.add(proyector2);
		proyectores.add(proyector3);
	}

	@Test
	public void buscaTodosProyectores() {
		// Crea requerimiento.
		ArrayList<Propiedad> condiciones = new ArrayList<Propiedad>();
		condiciones.add(tipoProyector);
		requerimiento = new Requerimiento(condiciones);

		// Filtra.
		HashSet<Recurso> resultado = new HashSet<Recurso>(
				requerimiento.filtrarConjunto(proyectores));

		// Valida el resultado.
		Assert.assertTrue(resultado.containsAll(proyectores));
		resultado.removeAll(proyectores);
		Assert.assertTrue(resultado.isEmpty());
	}
}
