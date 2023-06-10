package persona;


import contrataciones.Contratacion;
import contrataciones.iServicio;
import excepciones.ContratacionYaRegistradaException;
import excepciones.DomicilioNoEncontradoException;
import excepciones.DomicilioYaRegistradoException;
import excepciones.PersonaNoEncontradaException;
import modelo.Factura;
import modelo.MedioPago;
import modelo.Sistema;
import promociones.iPromocion;

public class SinContratacionEstado  implements IEstado{
	Persona p;

	public SinContratacionEstado(Persona p) {
		super();
		this.p = p;
	}

/*	@Override
	public void pagarFactura(Factura f, GregorianCalendar fecha, String metodoPago) {
		f.setPago(new Pago(f.totalModificadorMP(metodoPago), fecha, true));
	}*/
	@Override
	public void pagarFactura(Factura f, MedioPago metodoPago) {
		//no hace nada
	}
	
	@Override
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo, Factura f) throws DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException, PersonaNoEncontradaException {
		Sistema.getInstancia().crearContratacion(p.getDni(), dom, serv, promo);
	}

	@Override
	public void darDeBajaServicio(Contratacion c) {
		//no hace nada
	}
}
