package contrataciones;

import java.util.Objects;

public class Camara implements iContratable,Cloneable {

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
		Camara other = (Camara) obj;
		return Double.doubleToLongBits(tarifa) == Double.doubleToLongBits(other.tarifa);
	}
}
