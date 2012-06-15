package ar.edu.utn.tadp.reunion.tratamiento;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.excepcion.UserException;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Trata de replaniﬁcar la reunion con todos sus requerimientos para mas
 * adelante.
 * 
 * @version 13-06-2012
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
			return false;
		}
	}
}
