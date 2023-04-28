package modelo;

import contrataciones.iContratable;

public class MovilAcompañamiento implements iContratable{

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
}
