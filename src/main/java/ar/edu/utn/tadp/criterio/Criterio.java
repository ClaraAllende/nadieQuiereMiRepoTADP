package ar.edu.utn.tadp.criterio;
import java.util.Collection;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import ar.edu.utn.tadp.recurso.Persona;
import ar.edu.utn.tadp.recurso.Recurso;

/** 
 * Representa un command que sabe filtrar a las personas.
 *  @author clari
 *
 */
public  class Criterio {
	/**
	 * @param 
	 * una coleccion de recursos
	 * @return 
	 * una colección de Iterables con los elementos que cumplen con el criterio
	 * 
	 */
	public Iterable <Recurso> filtrarConjunto(Collection <Recurso> recursos){
		//si, le puse self :P y la tengo que declarar final porque sino se queja ¬¬
		final Criterio self=this; 
	    Predicate<Recurso>p= new Predicate<Recurso>() {
	    	public boolean apply(Recurso unRecurso){
	    		return self.cumpleCondicion(unRecurso);
	    	}
	    };
	    	return	Iterables.filter(recursos, p);
	}
	
	public boolean cumpleCondicion(Recurso unRecurso){
		//unRecurso.estaDisponible();
		return false;
	}
	
	public boolean cumpleCondicion(Persona unaPersona){
		//double dispatch? 
		//contains() o elementEquals() de guava
		//unaPersona.cumpleCondicion(this);
		return false;
		}
}
