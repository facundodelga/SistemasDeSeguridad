package modelo;

import contrataciones.iContratable;

public class BotonAntipanico implements iContratable{

	private double tarifa=2000;
	
	public void setTarifa(double tarifa) {
		this.tarifa=tarifa;
	}

	public double getTarifa() {
		return this.tarifa;
	}

	public String descripcion() {
		return "Botón antipánico";
	}
	


}
