package ar.edu.utn.tadp.tratamiento;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.organizables.Reunion;
import ar.edu.utn.tadp.recurso.Recurso;

/**
 * Para no cancelar la reunion valida si hay menos de un porcentaje de
 * asistencia elegido por el usuario.
 * 
 * @version 22-06-2012
 */
public class AsistenciaMinima implements TratamientoCancelacion {

	// Porcentaje de asistencia minima.
	private final Integer porcentaje;

	public AsistenciaMinima(final Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	/**
	 * Verifica que se cumpla la asistencia minima al cancelar.
	 */
	@Override
	public boolean evitarCancelacion(final Recurso recurso,
			final Reunion reunion, final Empresa empresa) {
		if (recurso.esPersona()) {
			Integer division = new Integer(
			// Personas que estan menos la personas que se va.
					((reunion.getCantidadDePersonas() - 1) * 100)
					// Requerimientos + el anfitrion.
							/ (reunion.getCantidadRequeridaDePersonas() + 1));
			boolean cumpleAsistencia = division > porcentaje;
			// Si se cumple la asistencia, quita el recurso.
			if (cumpleAsistencia) {
				reunion.quitar(recurso);
			}
			return cumpleAsistencia;
		} else {
			// Si no es persona no hace falta evaluar, lo quita directamente.
			reunion.quitar(recurso);
			return true;
		}
	}
}
