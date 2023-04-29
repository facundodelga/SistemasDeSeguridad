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
	
	@Override
	public Object clone()throws CloneNotSupportedException{
		try {
			BotonAntipanico nObj=(BotonAntipanico)super.clone();
			return nObj;
		}
		catch(CloneNotSupportedException e) {
			throw new CloneNotSupportedException("No se pudo clonar BotonAntipanico, FALLO="+e.toString());
		}
	}

}
