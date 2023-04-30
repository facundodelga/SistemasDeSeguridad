package contrataciones;

import java.util.Objects;

import promociones.iPromocion;

public class AlarmaComercio implements iServicio, Cloneable {
	private double tarifa=10000;
	
	public void setTarifa(double tarifa) {
		this.tarifa=tarifa;
	}

	public double getTarifa() {
		return this.tarifa;
	}
	
	public double getTarifa(iPromocion promocion) {
		//return this.tarifa;
		return this.tarifa+promocion.getPromoComercio(tarifa);
	}

	public String descripcion() {
		return "Alarma en comercio";
	}

	/**
	 * @param promocion
	 * @return
	 */
	public double getDescDorada(iPromocion promocion) {
		return promocion.getPromoComercio(this.tarifa);
	}

	/**
	 * @param promocion
	 * @return
	 */
	public double getDescPlatino(iPromocion promocion) {
		return promocion.getPromoComercio(this.tarifa);
	}

	/**
	 * Crea y devuelve una copia superficial de esta instancia de AlarmaComercio.
	 *
	 * @return una referencia a la copia clonada de esta instancia.
	 * @throws CloneNotSupportedException si la instancia de AlarmaComercio no es clonable.
	 */
	@Override
	public Object clone()throws CloneNotSupportedException{
		try {
			AlarmaComercio nObj=(AlarmaComercio)super.clone();
			return (Object)nObj;
		}
		catch(CloneNotSupportedException e) {
			throw new CloneNotSupportedException("No se pudo clonar AlarmaComercio, FALLO="+e.toString());
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(tarifa);
	}
	
	/**
	 * Compara esta instancia de AlarmaComercio con otro objeto para determinar si son iguales.
	 *
	 * @param obj el objeto con el que se desea comparar.
	 * @return true si el objeto es igual a esta instancia de AlarmaComercio, false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlarmaComercio other = (AlarmaComercio) obj;
		return Double.doubleToLongBits(tarifa) == Double.doubleToLongBits(other.tarifa);
	} 
}
