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
