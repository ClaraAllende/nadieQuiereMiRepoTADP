package ar.edu.utn.tadp.recurso;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.utn.tadp.agenda.TipoEvento;
import ar.edu.utn.tadp.organizables.Organizable;
import ar.edu.utn.tadp.recurso.roles.Rol;

/**
 * Representa a los recursos humanos dentro de la empresa.
 * 
 * @version 03-06-2012
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
		this.tipo = "Humano";
		this.estado = Estado.POCAS_REUNIONES;
	}

	public boolean esDelMismoProyecto(final Persona persona) {
		return this.proyecto.equals(persona.getProyecto());
	}

	@Override
	public BigDecimal dameTuCostoPara(final Organizable reunion) {
		return !this.esDelMismoProyecto(reunion.getOrganizador()) ? this.rol
				.getCostoPorHora().dameTuCostoPara(reunion) : BigDecimal
				.valueOf(0.0);
	}

	@Override
	public void apuntateALaReunion(final ArrayList<Recurso> recursos) {
		super.apuntateALaReunion(recursos);
		this.rol.necesitasRecurso(recursos);
	}

	public boolean estasOcupadoDurante(final Interval intervalo) {
		return this.getAgenda().estasOcupadoDurante(intervalo);
	}

	@Override
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(final Estado estado) {
		this.estado = estado;
	}

	@Override
	public int getHorasEnReunionesDeLaSemana() {
		DateTime unaSemanaAtras = new DateTime().minusWeeks(1);
		return this.getAgenda().horasEn(TipoEvento.REUNION, unaSemanaAtras)
				.getHours();
	}

	@Override
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

	public Persona setSector(final String sector) {
		this.sector = sector;
		return this;
	}

	public Rol getRol() {
		return this.rol;
	}

	public String getProyecto() {
		return proyecto;
	}

	public Persona setProyecto(final String proyecto) {
		this.proyecto = proyecto;
		return this;
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ++ para testing ++++++++++++++++++++++++++++++++++++++
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/**
	 * Devuelve los atributos en formato de <code>String</code>. Se usara en los
	 * test.
	 */
	@Override
	public String toString() {
		return "Persona: " + tipo + " - " + edificio + " - " + nombre + " - "
				+ sector + " - " + proyecto + " - " + rol;
	}
}
