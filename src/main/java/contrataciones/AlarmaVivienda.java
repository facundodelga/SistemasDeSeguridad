package contrataciones;

import java.util.Objects;

import promociones.iPromocion;

public class AlarmaVivienda implements iServicio, Cloneable {
	private double tarifa=8500;
	
	public void setTarifa(double tarifa) {
		assert tarifa > 0 : "Tarifa debe ser mayor que 0";
		this.tarifa=tarifa;
	}

	public double getTarifa() {
		return this.tarifa;
	}
	
	public double getTarifa(iPromocion promocion) {
		assert promocion != null : "La promocion debe estar inicializada en getTarifa(iPromocion)";
		return this.tarifa + promocion.getPromoVivienda(tarifa);
	}

	public String descripcion() {
		return "Alarma en vivienda";
	}

	/**
	 * @param promocion
	 * @return
	 */
	public double getDescDorada(iPromocion promocion) {
		assert promocion != null : "La promocion debe estar inicializada";
		return promocion.getPromoVivienda(this.tarifa);
	}

	/**
	 * @param promocion
	 * @return
	 */
	public double getDescPlatino(iPromocion promocion) {
		assert promocion != null : "La promocion debe estar inicializada";
		return promocion.getPromoVivienda(this.tarifa);
	}
	
	/**
	 * Crea y devuelve una copia superficial de esta instancia de AlarmaVivienda.
	 *
	 * @return una referencia a la copia clonada de esta instancia.
	 * @throws CloneNotSupportedException si la instancia de AlarmaVivienda no es clonable.
	 */
	@Override
	public Object clone()throws CloneNotSupportedException{
		try {
			AlarmaVivienda nObj=(AlarmaVivienda)super.clone();
			return nObj;
		}
		catch(CloneNotSupportedException e) {
			throw new CloneNotSupportedException("No se pudo clonar AlarmaVivienda, FALLO="+e.toString());
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(tarifa);
	}
	
	/**
	 * Compara esta instancia de AlarmaVivienda con otro objeto para determinar si son iguales.
	 *
	 * @param obj el objeto con el que se desea comparar.
	 * @return true si el objeto es igual a esta instancia de AlarmaVivienda, false en caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlarmaVivienda other = (AlarmaVivienda) obj;
		return Double.doubleToLongBits(tarifa) == Double.doubleToLongBits(other.tarifa);
	}
}
