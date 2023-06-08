package persona;

<<<<<<< HEAD
=======
import java.util.GregorianCalendar;

>>>>>>> 380f070f2f8e37269b6998cfe461d6daa440bcd5
import contrataciones.Contratacion;
import contrataciones.iServicio;
import excepciones.AccionNoAutorizadaException;
import modelo.Factura;
<<<<<<< HEAD
import modelo.MedioPago;
=======
>>>>>>> 380f070f2f8e37269b6998cfe461d6daa440bcd5
import promociones.iPromocion;

public interface IEstado {
	
<<<<<<< HEAD
	void pagarFactura(Factura f, MedioPago metodoPago);
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo,Factura f) throws AccionNoAutorizadaException;
	public void darDeBajaServicio(Contratacion c,Factura f) throws AccionNoAutorizadaException;
	
	/*
	void pagarFactura(Factura f, GregorianCalendar fecha, String metodoPago);
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo,Factura f) throws AccionNoAutorizadaException;
	public void darDeBajaServicio(Contratacion c,Factura f) throws AccionNoAutorizadaException;
	*/
=======
	void pagarFactura(Factura f, GregorianCalendar fecha, String metodoPago);
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo,Factura f) throws AccionNoAutorizadaException;
	public void darDeBajaServicio(Contratacion c,Factura f) throws AccionNoAutorizadaException;
>>>>>>> 380f070f2f8e37269b6998cfe461d6daa440bcd5
}
