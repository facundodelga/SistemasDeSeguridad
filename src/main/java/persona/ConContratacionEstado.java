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
	 * Realiza el pago de una factura utilizando un medio de pago especificado.
	 * Calcula la bonificación correspondiente a la factura y marca la factura como pagada.
	 * 
	 * @param f la factura a pagar
	 * @param metodoPago el medio de pago utilizado
	 */
	@Override
	public void pagarFactura(Factura f, MedioPago metodoPago) {
		f.calcularBonificacion(metodoPago);	
		f.setPagoRealizado(true);
	}

	/**
	 * Contrata un servicio para un domicilio específico, aplicando una promoción y generando una factura.
	 * 
	 * @param dom el domicilio en el que se contratará el servicio
	 * @param serv el servicio a contratar
	 * @param promo la promoción a aplicar (puede ser null si no se aplica ninguna promoción)
	 * @param f la factura generada para la contratación
	 * @throws DomicilioYaRegistradoException si el domicilio ya está registrado en el sistema
	 * @throws DomicilioNoEncontradoException si el domicilio no se encuentra en el sistema
	 * @throws ContratacionYaRegistradaException si ya existe una contratación para el domicilio y servicio especificados
	 * @throws PersonaNoEncontradaException si no se encuentra la persona asociada al domicilio
	 */
	@Override
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo, Factura f) throws DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException, PersonaNoEncontradaException {
		Sistema.getInstancia().crearContratacion(this.p, dom, serv, promo);
		}


	/**
	 * Da de baja un servicio contratado, actualizando el estado de la persona si es necesario.
	 * 
	 * @param c la contratación del servicio a dar de baja
	 * @throws AccionNoAutorizadaException si no se tiene autorización para realizar la acción
	 */
	@Override
	public void darDeBajaServicio(Contratacion c) throws AccionNoAutorizadaException {
		this.p.darDeBajaServicio(c);
		if(this.p.getContrataciones().size()==0)
			this.p.setEstado(new SinContratacionEstado(this.p));
	}
}
