package persona;

import contrataciones.Contratacion;
import contrataciones.iServicio;
import excepciones.AccionNoAutorizadaException;
import modelo.Factura;
import modelo.MedioPago;
import promociones.iPromocion;

public class MorosoEstado implements IEstado {
	PersonaFisica p;

	public MorosoEstado(PersonaFisica  p) {
		super();
		this.p = p;
	}
	/**
	     * Realiza el pago de una factura utilizando un medio de pago.
	     * Además de calcular la bonificación, se incrementa el total bonificado en un 30% adicional.
	     *
	     * @param f           La factura a pagar.
	     * @param metodoPago  El medio de pago a utilizar.
	     */
	@Override
	public void pagarFactura(Factura f, MedioPago metodoPago) {
		f.calcularBonificacion(metodoPago);
		f.setTotalBonificado(f.getTotalBonificado()*1.3);
		f.setPagoRealizado(true);
	}
	 /**
	     * Intenta contratar un servicio en un domicilio con una promoción asociada y generar una factura.
	     * No está autorizado para realizar esta acción y lanzará una excepción.
	     *
	     * @param dom   El domicilio donde se intenta contratar el servicio.
	     * @param serv  El servicio que se intenta contratar.
	     * @param promo La promoción asociada al servicio.
	     * @param f     La factura generada para la contratación (no se llega a generar en este caso).
	     * @throws AccionNoAutorizadaException Si no está autorizado para realizar la acción.
	     */
	@Override
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo, Factura f) throws AccionNoAutorizadaException {
		throw new AccionNoAutorizadaException("Persona morosa",p);
	}
	 /**
	     * Intenta dar de baja un servicio de una contratación.
	     * No está autorizado para realizar esta acción y lanzará una excepción.
	     *
	     * @param c La contratación a dar de baja.
	     * @throws AccionNoAutorizadaException Si no está autorizado para realizar la acción.
	     */
	@Override
	public void darDeBajaServicio(Contratacion c) throws AccionNoAutorizadaException {
		throw new AccionNoAutorizadaException("Persona morosa",p);
	}

}
