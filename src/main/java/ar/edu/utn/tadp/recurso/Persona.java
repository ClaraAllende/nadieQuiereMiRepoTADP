package ar.edu.utn.tadp.recurso;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.joda.time.Interval;

import ar.edu.utn.tadp.recurso.roles.Rol;
import ar.edu.utn.tadp.reunion.Reunion;

public class Persona extends Recurso {
	// XXX Tienen que ser publicos para que funcione Reflection
	public String nombre;
	public String sector;
	public String proyecto;
	public final Rol rol;

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

	// Propiedades de una Persona serian: proyecto rol sector nombre empresa

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
		// TODO fijarse si ubicacion debe ser un string o no
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
