package ar.edu.utn.tadp.tratamiento;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.excepcion.UserException;
import ar.edu.utn.tadp.organizables.Reunion;
import ar.edu.utn.tadp.recurso.Recurso;

/**
 * Trata de replani?car la reunion con todos sus requerimientos para mas
 * adelante.
 * 
 * @version 15-06-2012
 */
public class Replanificacion implements TratamientoCancelacion {

	@Override
	public boolean evitarCancelacion(final Recurso recurso,
			final Reunion reunion, final Empresa empresa) {
		// Este tratamiento no hace la remocion de recurso.
		try {
			empresa.replanificarReunion(reunion);
			return true;
		} catch (final UserException e) {
			// Si tira excepcion es por que no se puede replanificar.
			return false;
		}
	}
}
