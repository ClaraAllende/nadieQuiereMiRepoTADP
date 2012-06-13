package ar.edu.utn.tadp.reunion.estategia;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Asociar otro criterio de seleccion para el criterio de seleccion orignal.
 * 
 * @version 13-06-2012
 */
public class CriterioAlternativoEstrategia implements
		TratarCancelacionEstrategia {

	@Override
	public boolean tratarCancelacion(final Reunion reunion,
			final Empresa empresa) {
		// TODO falta hacerlo.
		return false;
	}

}
