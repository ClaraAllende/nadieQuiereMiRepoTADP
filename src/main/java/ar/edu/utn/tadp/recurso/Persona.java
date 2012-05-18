package ar.edu.utn.tadp.recurso;

<<<<<<< HEAD
public class Persona extends Recurso {
=======
import java.math.BigDecimal;

import ar.edu.utn.tadp.costos.Costeable;
import ar.edu.utn.tadp.recurso.roles.Rol;
import ar.edu.utn.tadp.reunion.Reunion;

public class Persona extends Recurso implements Costeable {

	private Rol rol;

	@Override
	public BigDecimal dameTuCostoPara(Reunion reunion) {
		return this.rol.getCostoPorHora().dameTuCostoPara(reunion);
	}
>>>>>>> 94ff1662e8f9f70f38ca1e49badecf615bf65d6f
	// Propiedades de una Persona serian: proyecto rol sector nombre empresa

	// TODO alguna diferencia de Recurso? Viajar a otro edificio?
}
