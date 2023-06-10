package contrataciones;

import promociones.iPromocion;

import java.io.Serializable;

public interface iServicio extends Serializable,Cloneable{
	public void setTarifa(double tarifa);
	public double getTarifa();
	public double getTarifa(iPromocion promocion);
	public String descripcion();
	Object clone() throws CloneNotSupportedException;
	
}
