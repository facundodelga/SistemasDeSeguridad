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
	 * Realiza el pago de una factura, aplicando una bonificación y actualizando el estado de pago.
	 * Además, incrementa el monto bonificado en un 30% adicional.
	 * 
	 * @param f la factura a pagar
	 * @param metodoPago el medio de pago utilizado
	 */
	@Override
	public void pagarFactura(Factura f, MedioPago metodoPago) {
		f.calcularBonificacion(metodoPago);
		f.setTotalBonificado(f.getTotalBonificado()*1.3);
		f.setPagoRealizado(true);
	}
	
	/**
	 * Intenta contratar un servicio para un domicilio específico, aplicando una promoción y generando una factura.
	 * Sin embargo, lanza una excepción `AccionNoAutorizadaException` si la persona tiene deuda pendiente.
	 * 
	 * @param dom el domicilio donde se desea contratar el servicio
	 * @param serv el servicio a contratar
	 * @param promo la promoción a aplicar
	 * @param f la factura generada para la contratación
	 * @throws AccionNoAutorizadaException si la persona tiene deuda pendiente
	 */
	@Override
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo, Factura f) throws AccionNoAutorizadaException {
		throw new AccionNoAutorizadaException("Persona morosa",p);
	}

	/**
	 * Intenta dar de baja un servicio específico, pero lanza una excepción `AccionNoAutorizadaException`
	 * si la persona tiene deuda pendiente.
	 *
	 * @param c la contratación del servicio a dar de baja
	 * @throws AccionNoAutorizadaException si la persona tiene deuda pendiente
	 */
	@Override
	public void darDeBajaServicio(Contratacion c) throws AccionNoAutorizadaException {
		throw new AccionNoAutorizadaException("Persona morosa",p);
	}

}
