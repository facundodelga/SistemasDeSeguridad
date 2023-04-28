package contrataciones;

import promociones.iPromocion;

public class AlarmaComercio implements iServicio {
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
}
