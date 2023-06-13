package factory;

import contrataciones.BotonAntipanico;
import contrataciones.Camara;
import contrataciones.MovilAcompañamiento;
import contrataciones.iContratable;
import excepciones.TipoDeContratableIncorrectoException;

public class ContratableFactory {
    /**
     * Crea una instancia de un objeto contratable según el tipo especificado.
     *
     * @param cont el tipo de contratable a crear
     * @return una instancia del contratable creado
     * @throws TipoDeContratableIncorrectoException si se especifica un tipo de contratable incorrecto
     */
	public static iContratable crearContratable(String cont) throws TipoDeContratableIncorrectoException {
		iContratable cr=null;
		if(cont.equalsIgnoreCase("BOTON"))
			cr = new BotonAntipanico();
		else
			if(cont.equalsIgnoreCase("CAMARA"))
				cr = new Camara();
			else
				if(cont.equalsIgnoreCase("MOVIL"))
					cr = new MovilAcompañamiento();
				else
					throw new TipoDeContratableIncorrectoException(cont);
		return cr;
	}

}
