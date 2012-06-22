package ar.edu.utn.tadp.tratamiento;

import java.util.Collection;

import ar.edu.utn.tadp.empresa.Empresa;
import ar.edu.utn.tadp.excepcion.UserException;
import ar.edu.utn.tadp.organizables.Reunion;
import ar.edu.utn.tadp.recurso.Recurso;
import ar.edu.utn.tadp.requerimiento.Requerimiento;

/**
 * Asociar otro criterio de seleccion para el criterio de seleccion orignal.
 * 
 * @version 13-06-2012
 */
public class CriterioAlternativo implements TratamientoCancelacion {

	@Override
	public boolean evitarCancelacion(final Recurso recurso,
			final Reunion reunion, final Empresa empresa) {
		Requerimiento requerimiento = reunion
				.getRequerimientoQueSatiface(recurso);
		if (requerimiento != null) {
			Requerimiento alternativa = requerimiento
					.getRequerimientoAlternativo();
			if (alternativa != null) {
				try {
					Collection<Recurso> candidatos = alternativa
							.buscaLosQueTeSatisfacen(empresa.gerRecursos());
					Recurso ganador = null;
					for (Recurso candidato : candidatos) {
						if (candidato.disponibleDurante(reunion.getHorario())) {
							ganador = candidato;
							break;
						}
					}
					if (ganador != null) {
						reunion.reemplazarPorAlternativo(requerimiento,
								recurso, alternativa, ganador);
						return true;
					} else {
						return false;
					}
				} catch (UserException e) {
					// No se encontro el candidato.
					return false;
				}
			} else {
				// No hay alternativa, hasta aca llegamos.
				return false;
			}
		} else {
			// Si llega aca es por que es Anfitron, Sala, etc.. que son
			// obligatorios. Esos no tienen criterio alternatico y no se puede
			// quitar.
			return false;
		}
	}
}
