package contrataciones;

import promociones.iPromocion;

public interface iServicio extends Cloneable{
	public void setTarifa(double tarifa);
	public double getTarifa();
	public double getTarifa(iPromocion promocion);
	public String descripcion();
	Object clone() throws CloneNotSupportedException;
	
}
