package ar.edu.utn.tadp.criterio;
import java.util.Collection;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import ar.edu.utn.tadp.recurso.Recurso;

public abstract class Criterio {
	public abstract boolean cumpleCondicion(Recurso unRecurso);
	
	public Iterable <Recurso> filtrarConjunto(Collection <Recurso> recursos){
	//template method
		final Criterio actual=this; 
	    Predicate<Recurso>p= new Predicate<Recurso>() {
	    	public boolean apply(Recurso unRecurso){
	    		return actual.cumpleCondicion(unRecurso);
	    	}
	    };
	    	return	Iterables.filter(recursos, p);
	}
}
