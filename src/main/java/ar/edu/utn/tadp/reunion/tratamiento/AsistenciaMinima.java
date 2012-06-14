package ar.edu.utn.tadp.reunion.tratamiento;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Para no cancelar la reunion valida si hay menos de un porcentaje de
 * asistencia elegido por el usuario.
 * 
 * @version 13-06-2012
 */
public class AsistenciaMinima implements TratamientoCancelacion {

	// Porcentaje de asistencia minima.
	private final Integer porcentaje;

	public AsistenciaMinima(final Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	/**
	 * Trata de evitar la cancelacion replanificando la reunion, de este modo no
	 * hay que quitar el recurso que cancelo.
	 */
	@Override
	public boolean evitarCancelacion(final Recurso recurso,
			final Reunion reunion, final Empresa empresa) {
		// TODO falta hacerlo.

		return false;
	}

}
