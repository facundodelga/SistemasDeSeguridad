package contrataciones;

import java.util.Objects;

import promociones.iPromocion;

public class AlarmaVivienda implements iServicio, Cloneable {
	private double tarifa=8500;
	
	public void setTarifa(double tarifa) {
		this.tarifa=tarifa;
	}

	public double getTarifa() {
		return this.tarifa;
	}
	
	public double getTarifa(iPromocion promocion) {
		//return this.tarifa;
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
		return promocion.getPromoVivienda(this.tarifa);
	}

	/**
	 * @param promocion
	 * @return
	 */
	public double getDescPlatino(iPromocion promocion) {
		return promocion.getPromoVivienda(this.tarifa);
	}

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
