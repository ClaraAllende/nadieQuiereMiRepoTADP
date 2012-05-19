package ar.edu.utn.tadp.recurso;

import java.math.BigDecimal;

import org.joda.time.Interval;

import ar.edu.utn.tadp.costos.Costeable;
import ar.edu.utn.tadp.recurso.roles.Rol;
import ar.edu.utn.tadp.reunion.Reunion;

public class Persona extends Recurso implements Costeable {

	private Rol rol;

	public Persona(Rol rol) {
		this.rol = rol;
	}

	@Override
	public BigDecimal dameTuCostoPara(Reunion reunion) {
		return this.rol.getCostoPorHora().dameTuCostoPara(reunion);
	}
	// Propiedades de una Persona serian: proyecto rol sector nombre empresa

	public boolean estasOcupadoDurante(Interval intervalo) {
		return this.getAgenda().estasOcupadoDurante(intervalo);
	}

	// TODO alguna diferencia de Recurso? Viajar a otro edificio?
}
