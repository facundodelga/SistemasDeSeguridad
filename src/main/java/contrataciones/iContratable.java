package contrataciones;

import java.io.Serializable;

public interface iContratable extends Serializable,Cloneable{
	public void setTarifa(double tarifa);
	public double getTarifa();
	public String descripcion();
	Object clone() throws CloneNotSupportedException;
}
