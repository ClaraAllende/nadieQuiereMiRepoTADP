package ar.edu.utn.tadp.reunion.estategia;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Cuando el usuario cancela su participacion se pueden disparar una serie de
 * procesos automaticos que seran elegidos arbitrariamente al momento de crear
 * la reunion. Esta interfaz agrupa estos procesos.
 * 
 * @version 13-06-2012
 */
public interface TratarCancelacionEstrategia {

	public abstract boolean tratarCancelacion(Reunion reunion, Empresa empresa);

}
