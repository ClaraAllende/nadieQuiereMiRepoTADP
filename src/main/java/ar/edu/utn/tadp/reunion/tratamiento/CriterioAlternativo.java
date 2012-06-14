package ar.edu.utn.tadp.reunion.tratamiento;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Asociar otro criterio de seleccion para el criterio de seleccion orignal.
 * 
 * @version 13-06-2012
 */
public class CriterioAlternativo implements TratamientoCancelacion {

	@Override
	public boolean evitarCancelacion(final Recurso recurso, final Reunion reunion,
			final Empresa empresa) {
		// TODO falta hacerlo.
		return false;
	}

}
