package ar.edu.utn.tadp.proyectos;

import java.math.BigDecimal;

import java.util.Collection;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import ar.edu.utn.tadp.reunion.Reunion;

public class TareaCompuesta extends Tarea {
	
	private Collection<Tarea> tareas;
	
	
	public TareaCompuesta(Collection<Tarea> tareas){
		this.tareas=tareas;
	}

	/**
	 * El costo de una tarea compuesta es la suma de los costos de sus subtareas (que pueden ser simples o compuestas.
	 */
	@Override
	public BigDecimal dameTuCostoPara(Reunion unaReunion) {
		BigDecimal costoTotal = new BigDecimal(0);
		for(Tarea tarea:this.tareas){
		costoTotal= costoTotal.add(tarea.dameTuCostoPara(unaReunion));
	}
		return costoTotal;
	}

	/**
	 * El tiempo de una tarea compuesta es la suma de los tiempos de las subtareas
	 */
	@Override
	public BigDecimal tiempo() {
		BigDecimal tiempoTotal= new BigDecimal(0);
		for (Tarea tarea: this.tareas){
			tiempoTotal= tiempoTotal.add(tarea.tiempo());
		}
		return tiempoTotal;
	}

	@Override
	public boolean isComplete(){
		Predicate<Tarea> p= new Predicate<Tarea>(){
			public boolean apply(Tarea tarea){
				return tarea.isComplete();
			}
		};
		return Iterables.all(this.tareas, p);
	}
}
