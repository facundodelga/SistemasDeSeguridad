package factory;

import excepciones.TipoDePromocionIncorrectoException;
import promociones.PromoDorada;
import promociones.PromoPlatino;
import promociones.SinPromo;
import promociones.iPromocion;

public class PromocionFactory {
    /**
     * Crea una nueva instancia de iPromocion según el tipo de promoción especificado.
     *
     * @param promo el tipo de promoción ("DORADA", "PLATINO" o "SINPROMO")
     * @return una nueva instancia de iPromocion correspondiente al tipo especificado
     * @throws TipoDePromocionIncorrectoException si se especifica un tipo de promoción incorrecto
     */
	public static iPromocion crearPromo(String promo) throws TipoDePromocionIncorrectoException {
		iPromocion pr=null;
		if(promo.equalsIgnoreCase("DORADA"))
			pr = new PromoDorada();
		else
			if(promo.equalsIgnoreCase("PLATINO"))
				pr = new PromoPlatino();
			else
				if(promo.equalsIgnoreCase("SINPROMO"))
					pr = new SinPromo();
				else
					throw new TipoDePromocionIncorrectoException(promo);
		return pr;
	}

}
