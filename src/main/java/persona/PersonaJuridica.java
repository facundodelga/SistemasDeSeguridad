package persona;

import java.util.ArrayList;

import contrataciones.Contratacion;
import modelo.Factura;
import modelo.FacturaJuridica;

public class PersonaJuridica extends Persona{

	public PersonaJuridica(String nombre, String dni) {
		super(nombre, dni);
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
	
	@Override
	public Factura crearFactura() {
		return new FacturaJuridica(this);
	}

	@Override
	public Factura crearFactura(ArrayList<Contratacion> c) {
		return new FacturaJuridica(this,c);
	}
}
