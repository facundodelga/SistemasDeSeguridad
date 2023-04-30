package contrataciones;

public interface iContratable extends Cloneable{
	public void setTarifa(double tarifa);
	public double getTarifa();
	public String descripcion();
	Object clone() throws CloneNotSupportedException;
}
