package factory;

import contrataciones.AlarmaComercio;
import contrataciones.AlarmaVivienda;
import contrataciones.iServicio;
import excepciones.TipoDeServicioIncorrectoException;

public class ServicioFactory {
    /**
     * Crea una nueva instancia de iServicio según el tipo de servicio especificado.
     *
     * @param serv el tipo de servicio ("VIVIENDA" o "COMERCIO")
     * @return una nueva instancia de iServicio correspondiente al tipo especificado
     * @throws TipoDeServicioIncorrectoException si se especifica un tipo de servicio incorrecto
     */
	public static iServicio crearServicio(String serv) throws TipoDeServicioIncorrectoException {
		iServicio sr=null;
		if(serv.equalsIgnoreCase("VIVIENDA"))
			sr = new AlarmaVivienda();
		else
			if(serv.equalsIgnoreCase("COMERCIO"))
				sr = new AlarmaComercio();
			else	
				throw new TipoDeServicioIncorrectoException(serv);
		return sr;
	}

}
