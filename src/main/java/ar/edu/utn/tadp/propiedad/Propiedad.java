package ar.edu.utn.tadp.propiedad;

/**
 * Representa las propiedades de los <code>Recurso</code>.
 * <p>
 * Contiene Tipo->Valor. Ejemplo: "Edificio"->"Catalinas",
 * "Nombre"->"Pepe Argento"
 * 
 * @version 18-05-2012
 */
public class Propiedad {
	String tipo = "";
	String valor = "";

	public Propiedad(String tipo, String valor) {
		this.tipo = tipo;
		this.valor = valor;
	}

	/**
	 * Redefine el metodo teniendo en cuenta que los <code>String</code> no son
	 * inmutables y podrian generar errores de comparacion.
	 * <p>
	 * Y de yapa no es Case Sensitive! ;-)
	 * 
	 * @param unObjeto
	 *            Otra <code>Propiedad</code> a comparar.
	 * @return <code>true</code> si la otra <code>Propiedad</code> es igual.
	 */
	@Override
	public boolean equals(Object unObjeto) {
		Propiedad otraPropiedad = (Propiedad) unObjeto;
<<<<<<< HEAD
=======
		if (!(unObjeto instanceof Propiedad)) {
			return false;
		}
>>>>>>> 94ff1662e8f9f70f38ca1e49badecf615bf65d6f
		return this.getTipo().toLowerCase()
				.equals(otraPropiedad.getTipo().toLowerCase())
				&& this.getValor().toLowerCase()
						.equals(otraPropiedad.getValor().toLowerCase());
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
