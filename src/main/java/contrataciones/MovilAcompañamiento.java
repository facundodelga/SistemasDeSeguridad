package contrataciones;

import java.util.Objects;

public class MovilAcompañamiento implements iContratable,Cloneable{

	private double tarifa=7500;
	
	public void setTarifa(double tarifa) {
		this.tarifa=tarifa;
	}

	public double getTarifa() {
		return this.tarifa;
	}

	public String descripcion() {
		return "Movil de acompañamiento"; //Un móvil de la empresa acompaña al cliente en los horarios estimulados de entrada y salida.
	}
	/**
	 * Crea y devuelve una copia superficial de esta instancia de MovilAcompañamiento.
	 *
	 * @return una referencia a la copia clonada de esta instancia.
	 * @throws CloneNotSupportedException si la instancia de MovilAcompañamiento no es clonable.
	 */
	@Override
	public Object clone()throws CloneNotSupportedException{
		try {
			MovilAcompañamiento nObj=(MovilAcompañamiento)super.clone();
			return nObj;
		}
		catch(CloneNotSupportedException e) {
			throw new CloneNotSupportedException("No se pudo clonar MovilAcompañamiento, FALLO="+e.toString());
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(tarifa);
	}
	
	/**
	 * Compara esta instancia de MovilAcompañamiento con otro objeto para determinar si son iguales.
	 *
	 * @param obj el objeto con el que se desea comparar.
	 * @return true si el objeto es igual a esta instancia de MovilAcompañamiento, false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovilAcompañamiento other = (MovilAcompañamiento) obj;
		return Double.doubleToLongBits(tarifa) == Double.doubleToLongBits(other.tarifa);
	}
	
	
}
