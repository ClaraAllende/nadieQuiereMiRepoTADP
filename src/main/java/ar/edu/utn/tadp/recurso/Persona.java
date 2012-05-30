package ar.edu.utn.tadp.recurso;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.joda.time.Interval;

import ar.edu.utn.tadp.recurso.roles.Rol;
import ar.edu.utn.tadp.reunion.Reunion;

public class Persona extends Recurso {

	private Rol rol;
	private Estado estado;
	private String ubicacion;

	public Persona(Rol rol) {
		this.rol = rol;
	}

	@Override
	public BigDecimal dameTuCostoPara(Reunion reunion) {
		return this.rol.getCostoPorHora().dameTuCostoPara(reunion);
	}

	@Override
	public void apuntateALaReunion(ArrayList<Recurso> recursos) {
		super.apuntateALaReunion(recursos);
		this.rol.necesitasRecurso(recursos);
	}

	// Propiedades de una Persona serian: proyecto rol sector nombre empresa

	public boolean estasOcupadoDurante(Interval intervalo) {
		return this.getAgenda().estasOcupadoDurante(intervalo);
	}

	public Estado getEstado() {
		return this.estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Long getCantidadDeHorasDeReunionEnLaUltimaSemana() {
		// TODO getCantidadDeHorasDeReunionEnLaUltimaSemana hacer que devuelva en base al calendario de horas ocupadas en reuniones
		return null;
	}

	public String getUbicacion() {
		// TODO fijarse si ubicacion debe ser un string o no
		return this.ubicacion;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}
