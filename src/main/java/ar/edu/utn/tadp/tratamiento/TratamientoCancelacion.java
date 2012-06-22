package ar.edu.utn.tadp.tratamiento;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.organizables.Reunion;
import ar.edu.utn.tadp.recurso.Recurso;

/**
 * Cuando el usuario cancela su participacion se pueden disparar una serie de
 * procesos automaticos que seran elegidos arbitrariamente al momento de crear
 * la reunion. Esta interfaz agrupa estos procesos.
 * 
 * @version 13-06-2012
 */
public interface TratamientoCancelacion {

	/**
	 * Trata de evitar la cancelacion. Segun el metodo, en caso de exito podria
	 * remover el recurso.
	 * 
	 * @param recurso
	 * @param reunion
	 * @param empresa
	 * @return <code>true</code> si se pudo evitar la cancelacion,
	 *         <code>false</code> si no.
	 */
	public abstract boolean evitarCancelacion(Recurso recurso, Reunion reunion,
			Empresa empresa);
}
