package ar.edu.utn.tadp.accionesPostReunion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ar.edu.utn.tadp.excepcion.ProgramException;
import ar.edu.utn.tadp.organizables.Reunion;

public abstract class DataNotificacion {
	/**
	 * Método extraído para evitar manejar las excepciones chequeadas en el if de la funcion notifyService()
	 * @param unaReunion
	 * @param m
	 * @return
	 */
		public Boolean invokeBooleanMethod(Reunion unaReunion, Method m){
			try {
				return (Boolean) m.invoke(unaReunion);
			}
			catch  (IllegalAccessException e){
				throw new ProgramException ("Acceso inválido", e);
			}
			catch (InvocationTargetException e1){
				throw new ProgramException("Objeto receptor no válido", e1);
			}
		}

		/**
		 * Método extraído para que no quedara todo el manejo de las excpeciones chequeadas en la
		 * función principal
		 * @param unaReunion
		 * @param aMethodName
		 * @return El método (que retorna un boolean) que hay que usar como condicion para notificar.
		 * @throws NoSuchMethodException
		 * @throws SecurityException
		 */
		public Method getMethodWithoutParameters(Reunion unaReunion, String aMethodName) {
			try{
				return unaReunion.getClass().getMethod(aMethodName);
			}
			catch(SecurityException e){
					throw new ProgramException(e);
				}
			catch(NoSuchMethodException e1){
				throw new ProgramException("El método no está definido", e1);
			}
		
		}
	

}
