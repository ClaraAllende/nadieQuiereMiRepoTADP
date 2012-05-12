package ar.edu.utn.tadp.criterio;
import java.util.Collection;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import ar.edu.utn.tadp.recurso.Recurso;

public class Criterio {
	public Iterable <Recurso> filtrarConjunto(Collection <Recurso> recursos){
		final Criterio actual=this; 
	    Predicate<Recurso>p= new Predicate<Recurso>() {
	    	public boolean apply(Recurso unRecurso){
	    		return unRecurso.cumpleCondicion(actual);
	    	}
	    };
	    	return	Iterables.filter(recursos, p);
	}
}
