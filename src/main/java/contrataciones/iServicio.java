package contrataciones;

import promociones.iPromocion;
import clonable.Clonable;

public interface iServicio extends Clonable{
	public void setTarifa(double tarifa);
	public double getTarifa();
	public double getTarifa(iPromocion promocion);
	public String descripcion();
//	double getDescDorada(iPromocion promocion);
//	double getDescPlatino(iPromocion promocion);
}
