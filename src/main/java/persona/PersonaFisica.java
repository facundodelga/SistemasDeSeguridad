package persona;

import java.util.ArrayList;

import contrataciones.Contratacion;
import modelo.*;

public class PersonaFisica extends Persona{
	IEstado estado;

	public PersonaFisica(String nombre, String dni) {
		super(nombre, dni);
	}

	@Override
	public Factura crearFactura() {
		return new FacturaFisica(this, Sistema.getInstancia().getMes());
	}

	@Override
	public Factura crearFactura(ArrayList<Contratacion> c) {
		return new FacturaFisica(this,c, Sistema.getInstancia().getMes());
	}
	/**
	 * Crea y devuelve un clon de la instancia actual de PersonaFisica.
	 *
	 * @return El clon de la instancia actual de PersonaFisica.
	 * @throws CloneNotSupportedException Si se produce un error durante el proceso de clonación.
	 */
	@Override
	public Object clone()throws CloneNotSupportedException{
		try {
			PersonaFisica nObj=(PersonaFisica)super.clone();
			return nObj;
		}
		catch(CloneNotSupportedException e) {
			throw new CloneNotSupportedException("No se pudo clonar PersonaFisica, FALLO="+e.toString());
		}
	}
}
