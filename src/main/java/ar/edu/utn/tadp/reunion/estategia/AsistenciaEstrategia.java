package ar.edu.utn.tadp.reunion.estategia;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.reunion.Reunion;

/**
 * Para no cancelar la reunion valida si hay menos de un porcentaje de
 * asistencia elegido por el usuario.
 * 
 * @version 13-06-2012
 */
public class AsistenciaEstrategia implements TratarCancelacionEstrategia {

	@Override
	public boolean tratarCancelacion(final Reunion reunion,
			final Empresa empresa) {
		// TODO falta hacerlo.
		return false;
	}

}
