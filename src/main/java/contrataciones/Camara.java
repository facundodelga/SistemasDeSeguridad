package contrataciones;

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

}
