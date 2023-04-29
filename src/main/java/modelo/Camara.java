package modelo;

import contrataciones.iContratable;

public class Camara implements iContratable {

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
}
