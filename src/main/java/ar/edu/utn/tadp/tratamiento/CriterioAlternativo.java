package ar.edu.utn.tadp.tratamiento;

import java.util.Collection;

import org.joda.time.Interval;

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
		try {
			Requerimiento requerimiento = reunion
					.getRequerimientoQueSatiface(recurso);
			reemplazarPorAlternativo(requerimiento, recurso, reunion, empresa);
			return true;
		} catch (Exception e) {
			// No se pudo evitar la cancelacion.
			return false;
		}
	}

	private void reemplazarPorAlternativo(Requerimiento requerimiento,
			final Recurso recurso, final Reunion reunion, final Empresa empresa) {
		try {
			Requerimiento requerimientoAlternativo = requerimiento
					.getRequerimientoAlternativo();
			Collection<Recurso> candidatos = requerimiento
					.getRequerimientoAlternativo().buscaLosQueTeSatisfacen(
							empresa.gerRecursos());
			Recurso reemplazo = getRecursoDisponible(reunion.getHorario(),
					candidatos);
			reunion.reemplazarPorAlternativo(requerimiento, recurso,
					requerimientoAlternativo, reemplazo);
		} catch (NullPointerException e) {
			// Si llega aca es por que es Anfitron, Sala, etc.. sin alternativa.
			throw new UserException("No hay reemplazo para el recurso: "
					+ recurso);
		} catch (UserException e) {
			// No hay alternativa, hasta aca llegamos.
			throw new UserException("No hay reemplazo para el recurso: "
					+ recurso);
		}
	}

	private Recurso getRecursoDisponible(final Interval intervalo,
			Collection<Recurso> candidatos) {
		for (Recurso candidato : candidatos)
			if (candidato.disponibleDurante(intervalo))
				return candidato;
		throw new UserException("No hay recurso disponible para el horario: "
				+ intervalo);
	}
}
