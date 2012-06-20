package ar.edu.utn.tadp.recurso;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.tadp.propiedad.Propiedad;

public class RecursoIntrospector {

	static public List<Propiedad> getPropiedadesDe(Recurso recurso){
		
		try{
			Field[] fields = recurso.getClass().getDeclaredFields();
		List<Propiedad> propiedades = new ArrayList<>();
		for (Field field : fields){
			field.setAccessible(true);
			if (field.get(recurso)!= null){
				propiedades.add(new Propiedad(field.getName(),field.get(recurso).toString()));
			}
		}
		
		return propiedades;
		}
		catch (Exception e){
			throw new RuntimeException("No se pudieron obtener las propiedades",e);
		}
	}
}
