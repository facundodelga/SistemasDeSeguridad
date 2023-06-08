package modelo;

public class MedioPagoFactory {
	
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
