package ar.edu.utn.tadp.criterio;
import java.util.Collection;
import java.util.Set;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import ar.edu.utn.tadp.propiedad.Propiedad;
import ar.edu.utn.tadp.recurso.*;

/** 
 * Representa un objeto que sabe filtrar a los recursos.
 *  @author clari
 *
 */
public  class Seleccionador {
	private Iterable<Propiedad> condiciones;
	/**
	 * @param 
	 * una coleccion de
	 * @return 
	 * una colección de Iterables con los elementos que cumplen con el criterio
	 * 
	 */
	public Iterable <Recurso> filtrarConjunto(Collection <Recurso> recursos){
		//si, le puse self :P y la tengo que declarar final porque sino se queja ¬¬
		final Seleccionador self=this; 
	    Predicate<Recurso>p= new Predicate<Recurso>() {
	    	public boolean apply(Recurso unRecurso){
	    		return self.cumpleCondicion(unRecurso);
	    	}
	    };
	    	return	Iterables.filter(recursos, p);
	}
	
	/**
	 * @return verdadero si alguna de las condiciones está incluida
	 * en las propiedades del recurso (al menos una)
	 * @author clari
	 */
	public boolean cumpleCondicion(Recurso unRecurso){
		boolean cumple= false;
		for (Propiedad propiedad: this.condiciones){
			cumple= Iterables.contains(unRecurso.getPropiedades() , propiedad);
		}
		return cumple;
	}
	
	public void agregarCondiciones (Set<Propiedad> unasCondiciones){
		this.condiciones= unasCondiciones;
	}
	
}
