package factory;

import contrataciones.AlarmaComercio;
import contrataciones.AlarmaVivienda;
import contrataciones.iServicio;
import excepciones.TipoDeServicioIncorrectoException;

public class ServicioFactory {

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
