package persona;

import java.util.GregorianCalendar;

import contrataciones.Contratacion;
import contrataciones.iServicio;
import modelo.Factura;
<<<<<<< HEAD
import modelo.MedioPago;
=======
import modelo.Pago;
>>>>>>> 380f070f2f8e37269b6998cfe461d6daa440bcd5
import promociones.iPromocion;

public class SinContratacionEstado  implements IEstado{
	Persona p;

	public SinContratacionEstado(Persona p) {
		super();
		this.p = p;
	}

<<<<<<< HEAD
/*	@Override
	public void pagarFactura(Factura f, GregorianCalendar fecha, String metodoPago) {
		f.setPago(new Pago(f.totalModificadorMP(metodoPago), fecha, true));
	}*/
	@Override
	public void pagarFactura(Factura f, MedioPago metodoPago) {
		f.calcularBonificacion(metodoPago);	
		f.setPagoRealizado(true);
	}
	
=======
	@Override
	public void pagarFactura(Factura f, GregorianCalendar fecha, String metodoPago) {
		f.setPago(new Pago(f.totalModificadorMP(metodoPago), fecha, true));
	}

>>>>>>> 380f070f2f8e37269b6998cfe461d6daa440bcd5
	@Override
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo, Factura f) {
	}

	@Override
	public void darDeBajaServicio(Contratacion c, Factura f) {
	}
<<<<<<< HEAD

=======
>>>>>>> 380f070f2f8e37269b6998cfe461d6daa440bcd5
}
