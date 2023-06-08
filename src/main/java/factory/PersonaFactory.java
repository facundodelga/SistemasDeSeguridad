package factory;

import excepciones.TipoDePersonaIncorrectoException;
import persona.Persona;
import persona.PersonaFisica;
import persona.PersonaJuridica;

public class PersonaFactory {

	public static Persona crearPersona(String nombre, String dni, String tipo) throws TipoDePersonaIncorrectoException {
		Persona nuevaP=null;
		if(tipo.equalsIgnoreCase("JURIDICA")) 
			nuevaP=new PersonaJuridica(nombre,dni);
		else
			if(tipo.equalsIgnoreCase("FISICA")) 
				nuevaP=new PersonaFisica(nombre,dni);
			else
				throw new TipoDePersonaIncorrectoException(tipo);
		return nuevaP;
	}

}
