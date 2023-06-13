package factory;

import modelo.Cheque;
import modelo.Efectivo;
import modelo.Factura;
import modelo.MedioPago;
import modelo.Tarjeta;

public class MedioPagoFactory {
    /**
     * Obtiene una instancia del medio de pago según el método de pago especificado.
     *
     * @param metodo  el método de pago
     * @param factura la factura asociada al medio de pago
     * @return una instancia del medio de pago correspondiente al método especificado
     */
	public static MedioPago getMedioPago(String metodo, Factura factura) {
		MedioPago mp = null;
		if(metodo.equalsIgnoreCase("CHEQUE"))
			mp = new Cheque(factura);
		else
			if(metodo.equalsIgnoreCase("EFECTIVO"))
				mp = new Efectivo(factura);
			else
				if(metodo.equalsIgnoreCase("TARJETA"))
					mp = new Tarjeta(factura);

		return mp;
	}

}
