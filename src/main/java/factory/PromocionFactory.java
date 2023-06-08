package factory;

import excepciones.TipoDePromocionIncorrectoException;
import promociones.PromoDorada;
import promociones.PromoPlatino;
import promociones.SinPromo;
import promociones.iPromocion;

public class PromocionFactory {

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
