package ar.edu.utn.tadp.organizables;

import org.joda.time.Hours;

import ar.edu.utn.tadp.recurso.Persona;

public class OrganizableSimple implements Organizable {

	private Persona organizador;
	private Hours horas;

	public OrganizableSimple(Persona anfitrion, Hours horas) {
		this.organizador = anfitrion;
		this.horas = horas;
	}

	@Override
	public long getCantidad() {
		return 0;
	}

	@Override
	public long getDuracion() {
		return this.horas.getHours();
	}

	@Override
	public Persona getOrganizador() {
		return this.organizador;
	}

	@Override
	public String getUbicacion() {
		return this.organizador.getUbicacion();
	}

}
