package ar.edu.utn.tadp.agenda;

import java.util.List;
import org.joda.time.Duration;
import org.joda.time.Interval;

	 
/**
* Interfaz para los objetos que pueden estar ocupados.
*/

	 public interface Disponible {

/**
 * Interfaz para los objetos que pueden estar ocupados.
 */

//  Ocuparse durante un intervalo

  public void ocupateDurante(Interval unIntervalo);

//  Saber si está disponible o no durante un intervalo

  public boolean disponibleDurante (Interval unIntervalo);

//  conocer sus intervalos disponibles

  public List<Interval> horariosDisponibles();  

//  saber si tiene un intervalo disponible de una duración dada, si no la tiene tira exception

  public Interval intervaloDisponibleDe(Duration unaDuracion);

	


}
