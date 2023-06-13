package factory;

import excepciones.TipoDePersonaIncorrectoException;
import persona.Persona;
import persona.PersonaFisica;
import persona.PersonaJuridica;

public class PersonaFactory {
    /**
     * Crea una nueva instancia de Persona según el nombre, DNI y tipo especificados.
     *
     * @param nombre el nombre de la persona
     * @param dni    el DNI de la persona
     * @param tipo   el tipo de persona ("JURIDICA" o "FISICA")
     * @return una nueva instancia de Persona correspondiente al tipo especificado
     * @throws TipoDePersonaIncorrectoException si se especifica un tipo de persona incorrecto
     */
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
