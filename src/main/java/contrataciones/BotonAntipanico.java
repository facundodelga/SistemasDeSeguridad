package contrataciones;

import java.util.Objects;

public class BotonAntipanico implements iContratable,Cloneable{

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
		BotonAntipanico other = (BotonAntipanico) obj;
		return Double.doubleToLongBits(tarifa) == Double.doubleToLongBits(other.tarifa);
	}
	
	

}
