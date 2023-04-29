package contrataciones;
import clonable.Clonable;

public interface iContratable extends Clonable{
	public void setTarifa(double tarifa);
	public double getTarifa();
	public String descripcion();
}
