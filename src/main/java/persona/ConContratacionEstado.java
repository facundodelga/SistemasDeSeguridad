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

	/**
	     * Realiza el pago de una factura utilizando un medio de pago.
	     *
	     * @param f           La factura a pagar.
	     * @param metodoPago  El medio de pago a utilizar.
	     */
	@Override
	public void pagarFactura(Factura f, MedioPago metodoPago) {
		f.calcularBonificacion(metodoPago);	
		f.setPagoRealizado(true);
	}
	/**
	     * Contrata un servicio en un domicilio con una promoción asociada y genera una factura.
	     *
	     * @param dom   El domicilio donde se contrata el servicio.
	     * @param serv  El servicio a contratar.
	     * @param promo La promoción asociada al servicio.
	     * @param f     La factura generada para la contratación.
	     * @throws DomicilioYaRegistradoException      Si el domicilio ya está registrado.
	     * @throws DomicilioNoEncontradoException      Si el domicilio no se encuentra.
	     * @throws ContratacionYaRegistradaException   Si la contratación ya está registrada.
	     * @throws PersonaNoEncontradaException        Si no se encuentra la persona asociada.
	     */
	@Override
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo, Factura f) throws DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException, PersonaNoEncontradaException {
		Sistema.getInstancia().crearContratacion(this.p, dom, serv, promo);
		}
	/**
	     * Da de baja un servicio de una contratación.
	     *
	     * @param c La contratación a dar de baja.
	     * @throws AccionNoAutorizadaException Si no se encuentra autorizado para realizar la acción.
	     */
	@Override
	public void darDeBajaServicio(Contratacion c) throws AccionNoAutorizadaException {
		this.p.darDeBajaServicio(c);
		if(this.p.getContrataciones().size()==0)
			this.p.setEstado(new SinContratacionEstado(this.p));
	}
}
