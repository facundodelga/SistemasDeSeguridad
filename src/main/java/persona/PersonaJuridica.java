package persona;

import java.util.ArrayList;

import contrataciones.Contratacion;
import modelo.Factura;
import modelo.FacturaJuridica;
import modelo.Sistema;

public class PersonaJuridica extends Persona{

	public PersonaJuridica(String nombre, String dni) {
		super(nombre, dni);
	}
	
	@Override
	public Factura crearFactura() {
		return new FacturaJuridica(this, Sistema.getInstancia().getMes());
	}

	@Override
	public Factura crearFactura(ArrayList<Contratacion> c) {
		return new FacturaJuridica(this,c, Sistema.getInstancia().getMes());
	}
	/**
	 * Lanza una excepción de tipo CloneNotSupportedException indicando que la clonación de una PersonaJuridica no está permitida.
	 *
	 * @throws CloneNotSupportedException Si se intenta clonar una PersonaJuridica.
	 */
	@Override
	public Object clone()throws CloneNotSupportedException{
		throw new CloneNotSupportedException("PersonaJuridica no puede clonarse");
	}
}
