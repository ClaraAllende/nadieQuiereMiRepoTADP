package ar.edu.utn.tadp.reunion.tratamiento;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Trata de evitar cancelar la reunion a menos que se dio de baja alguna de las
 * personas o recursos obligatorios.
 * 
 * @version 13-06-2012
 */
public class Obligatoriedad implements TratamientoCancelacion {

	@Override
	public boolean evitarCancelacion(final Recurso recurso, final Reunion reunion,
			final Empresa empresa) {
		// TODO falta hacerlo.
		return false;
	}

}
