package ar.edu.utn.tadp.accionesPostReunion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import ar.edu.utn.tadp.excepcion.ProgramException;
import ar.edu.utn.tadp.reunion.Reunion;

public class DataNotificacionPersonas {

	private String methodName;
	private String action;
	private Reunion reunion;
	
	public DataNotificacionPersonas(String methodName, String action, Reunion reunion){
		this.methodName=methodName;
		this.action=action;
		this.reunion= reunion;
	}
	public void getAction(Notificador notificador) {
		Method m = notificador.getMethodWithoutParameters(reunion, this.methodName);
		if (notificador.invokeBooleanMethod(reunion, m)){
		Method action= getMethodWithVarArgs(reunion, this.action, notificador);
		this.invokeMethod(notificador, action,reunion);
		}
	}
	
	private Method getMethodWithVarArgs(Reunion reunion, String action, Notificador notificador) {
	 try{
		 return notificador.getClass().getMethod(action, Reunion.class);
	 }
	 catch(NoSuchMethodException e){
		 throw new ProgramException("Acceso inválido", e);
	 }
	}
	
	private void invokeMethod(Notificador notificador, Method m, Reunion reunion) {
	    try{
	    	  m.invoke(notificador,  reunion);
	    }
		catch  (IllegalAccessException e){
			throw new ProgramException ("Acceso inválido", e);
		}
		catch (InvocationTargetException e1){
			throw new ProgramException("Objeto receptor no válido", e1);
		}
}
	public String getMethodName(){
		return this.methodName;
	}

}
