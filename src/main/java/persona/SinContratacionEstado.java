package persona;

import java.util.GregorianCalendar;

import contrataciones.Contratacion;
import contrataciones.iServicio;
import modelo.Factura;
import modelo.Pago;
import promociones.iPromocion;

public class SinContratacionEstado  implements IEstado{
	Persona p;

	public SinContratacionEstado(Persona p) {
		super();
		this.p = p;
	}

	@Override
	public void pagarFactura(Factura f, GregorianCalendar fecha, String metodoPago) {
		f.setPago(new Pago(f.totalModificadorMP(metodoPago), fecha, true));
	}

	@Override
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo, Factura f) {
	}

	@Override
	public void darDeBajaServicio(Contratacion c, Factura f) {
	}
}
