package persona;

import contrataciones.Contratacion;
import contrataciones.iServicio;
import excepciones.AccionNoAutorizadaException;
import modelo.Factura;
import modelo.MedioPago;
import promociones.iPromocion;

public interface IEstado{
	
	void pagarFactura(Factura f, MedioPago metodoPago);
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo,Factura f) throws AccionNoAutorizadaException;
	public void darDeBajaServicio(Contratacion c,Factura f) throws AccionNoAutorizadaException;
}
