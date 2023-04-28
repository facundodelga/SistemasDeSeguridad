package contrataciones;

import promociones.iPromocion;

public interface iServicio {
	public void setTarifa(double tarifa);
	public double getTarifa();
	public double getTarifa(iPromocion promocion);
	public String descripcion();
//	double getDescDorada(iPromocion promocion);
//	double getDescPlatino(iPromocion promocion);
}
