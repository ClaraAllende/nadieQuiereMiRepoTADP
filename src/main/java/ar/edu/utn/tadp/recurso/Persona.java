package ar.edu.utn.tadp.recurso;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.joda.time.Interval;

import ar.edu.utn.tadp.recurso.roles.Rol;
import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Representa a los recursos humanos dentro de la empresa.
 * 
 * @version 31-05-2012
 */
public class Persona extends Recurso {
	// Propiedades de una Persona serian: proyecto rol sector nombre
	protected String nombre;
	protected String sector;
	protected String proyecto;
	protected final Rol rol;

	private Estado estado;

	public Persona(final Rol rol) {
		this.rol = rol;
	}

	@Override
	public BigDecimal dameTuCostoPara(final Reunion reunion) {
		return this.rol.getCostoPorHora().dameTuCostoPara(reunion);
	}

	@Override
	public void apuntateALaReunion(final ArrayList<Recurso> recursos) {
		super.apuntateALaReunion(recursos);
		this.rol.necesitasRecurso(recursos);
	}

	public boolean estasOcupadoDurante(final Interval intervalo) {
		return this.getAgenda().estasOcupadoDurante(intervalo);
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(final Estado estado) {
		this.estado = estado;
	}

	public Long getCantidadDeHorasDeReunionEnLaUltimaSemana() {
		// TODO getCantidadDeHorasDeReunionEnLaUltimaSemana hacer que devuelva
		// en base al calendario de horas ocupadas en reuniones
		return null;
	}

	public String getUbicacion() {
		// XXX ubicacion seria el edificio.
		return this.edificio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(final String sector) {
		this.sector = sector;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(final String proyecto) {
		this.proyecto = proyecto;
	}
}
