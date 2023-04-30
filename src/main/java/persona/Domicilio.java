package persona;

import java.util.Objects;

public class Domicilio implements Cloneable{
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
	
	@Override
	public int hashCode() {
		return Objects.hash(calle, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Domicilio other = (Domicilio) obj;
		return Objects.equals(calle, other.calle) && numero == other.numero;
	}

	@Override
	public Object clone() throws CloneNotSupportedException{
		try {
			Domicilio nObj=(Domicilio)super.clone();
			nObj.calle=this.calle;
			return nObj;
		}
		catch(CloneNotSupportedException e) {
			throw new CloneNotSupportedException("No se pudo clonar Domicilio, FALLO="+e.toString());
		}
	}

	@Override
	public String toString() {
		return calle + " " + numero;
	}
	
}
