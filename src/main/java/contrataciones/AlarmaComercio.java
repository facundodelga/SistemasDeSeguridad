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
