package persona;

import modelo.*;

public class PersonaFisica extends Persona{

	public PersonaFisica(String nombre, String dni) {
		super(nombre, dni);
	}

	@Override
	public double calcularBonificacion(Factura factura) {
		return factura.calcularBonificacionFisica();
	}

}
