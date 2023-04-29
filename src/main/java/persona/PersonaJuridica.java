package persona;

import modelo.Factura;

public class PersonaJuridica extends Persona{

	public PersonaJuridica(String nombre, String dni) {
		super(nombre, dni);
	}
	
	@Override
	public double calcularBonificacion(Factura factura) {
		
		return factura.calcularBonificacionJuridica();
	}
	@Override
	public Object clone()throws CloneNotSupportedException{
		throw new CloneNotSupportedException("PersonaJuridica no puede clonarse");
	}
}
