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
import modelo.Sistema;
import promociones.iPromocion;

public class ConContratacionEstado implements IEstado {
	PersonaFisica  p;

	public ConContratacionEstado(PersonaFisica  p) {
		super();
		this.p = p;
	}


	@Override
	public void pagarFactura(Factura f, MedioPago metodoPago) {
		f.calcularBonificacion(metodoPago);	
		f.setPagoRealizado(true);
	}

	@Override
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo, Factura f) throws DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException, PersonaNoEncontradaException {
		Sistema.getInstancia().crearContratacion(this.p, dom, serv, promo);
		}

	@Override
	public void darDeBajaServicio(Contratacion c) throws AccionNoAutorizadaException {
		this.p.darDeBajaServicio(c);
		if(this.p.getContrataciones().size()==0)
			this.p.setEstado(new SinContratacionEstado(this.p));
	}
}
