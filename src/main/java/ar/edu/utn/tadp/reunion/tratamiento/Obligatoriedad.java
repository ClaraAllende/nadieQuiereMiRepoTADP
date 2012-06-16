package ar.edu.utn.tadp.reunion.tratamiento;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Trata de evitar cancelar la reunion a menos que se dio de baja alguna de las
 * personas o recursos obligatorios.
 * 
 * @version 15-06-2012
 */
public class Obligatoriedad implements TratamientoCancelacion {

	@Override
	public boolean evitarCancelacion(final Recurso recurso,
			final Reunion reunion, final Empresa empresa) {
		if (reunion.isObligatorio(recurso)) {
			return false;
		} else {
			// Ya que es opcional lo quita y reporta que lo soluciono.
			reunion.quitar(recurso);
			return true;
		}
	}
}
