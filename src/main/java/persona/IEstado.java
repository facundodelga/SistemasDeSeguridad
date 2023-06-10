package persona;

import contrataciones.Contratacion;
import contrataciones.iServicio;
import excepciones.AccionNoAutorizadaException;
import excepciones.ContratacionYaRegistradaException;
import excepciones.DomicilioNoEncontradoException;
import excepciones.DomicilioYaRegistradoException;
import excepciones.PersonaNoEncontradaException;
import modelo.Factura;
import modelo.MedioPago;
import promociones.iPromocion;

import java.io.Serializable;

public interface IEstado extends Serializable {
	
	void pagarFactura(Factura f, MedioPago metodoPago);
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo,Factura f) throws AccionNoAutorizadaException, DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException, PersonaNoEncontradaException;
	public void darDeBajaServicio(Contratacion c) throws AccionNoAutorizadaException;
}
