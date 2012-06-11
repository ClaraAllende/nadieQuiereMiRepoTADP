package ar.edu.utn.tadp.recurso;


public class Estado {

	public static Estado EXCESIVAS_REUNIONES = new Estado(0);
	public static Estado NORMAL = new Estado(1);
	public static Estado POCAS_REUNIONES = new Estado(2);
	
	private int prioridad; 
	
	private Estado(int prioridad) {
		this.prioridad = prioridad;
	}
	
	public boolean esMasPrioritarioQue(Estado estado) {
		return this.prioridad > estado.prioridad;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Estado) {
			return this.prioridad == ((Estado) obj).prioridad;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.prioridad;
	}
}
