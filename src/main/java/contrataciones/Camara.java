package contrataciones;

import java.util.Objects;

public class Camara implements iContratable,Cloneable {

	private double tarifa=3000;

	public void setTarifa(double tarifa) {
		this.tarifa=tarifa;
	}

	public double getTarifa() {
		return this.tarifa;
	}

	public String descripcion() {
		return "Cámara de seguridad";
	}
	
	/**
	 * Crea y devuelve una copia superficial de esta instancia de Camara.
	 *
	 * @return una referencia a la copia clonada de esta instancia.
	 * @throws CloneNotSupportedException si la instancia de Camara no es clonable.
	 */
	@Override
	public Object clone()throws CloneNotSupportedException{
		try {
			Camara nObj=(Camara)super.clone();
			return nObj;
		}
		catch(CloneNotSupportedException e) {
			throw new CloneNotSupportedException("No se pudo clonar Camara, FALLO="+e.toString());
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(tarifa);
	}
	/**
	 * Compara esta instancia de Camara con otro objeto para determinar si son iguales.
	 *
	 * @param obj el objeto con el que se desea comparar.
	 * @return true si el objeto es igual a esta instancia de Camara, false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Camara other = (Camara) obj;
		return Double.doubleToLongBits(tarifa) == Double.doubleToLongBits(other.tarifa);
	}
}
