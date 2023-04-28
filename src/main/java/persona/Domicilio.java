package persona;

public class Domicilio {
	private String calle;
	private int numero;
	
	/**
	 * <b>PRE:</b> El parámetro calle no puede ser null ni vacio. El parámetro numero no puede ser null ni menor a 0.
	 * @param calle parámetro de tipo String, indica el nombre de la calle del domicilio instanciado
	 * @param numero parámetro de tipo int, indica la altura de la calle del domicilio instanciado
	 */
	public Domicilio(String calle, int numero) {
		super();
		this.calle = calle;
		this.numero = numero;
	}

	public String getCalle() {
		return calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	
}
