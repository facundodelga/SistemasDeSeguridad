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
	PersonaFisica p;

	public SinContratacionEstado(PersonaFisica p) {
		super();
		this.p = p;
	}

	@Override
	public void pagarFactura(Factura f, MedioPago metodoPago) {
		//no hace nada
	}
	
	/**
	 * Contrata un nuevo servicio para la persona jurídica.
	 *
	 * @param dom   el domicilio donde se contratará el servicio
	 * @param serv  el servicio a contratar
	 * @param promo la promoción a aplicar al servicio
	 * @param f     la factura a la cual se asociará la contratación
	 * @throws DomicilioYaRegistradoException      si el domicilio ya está registrado
	 * @throws DomicilioNoEncontradoException      si el domicilio no se encuentra registrado
	 * @throws ContratacionYaRegistradaException   si la contratación ya está registrada
	 * @throws PersonaNoEncontradaException        si no se encuentra la persona asociada a la contratación
	 */
	@Override
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo, Factura f) throws DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException, PersonaNoEncontradaException {
		Sistema.getInstancia().crearContratacion(this.p, dom, serv, promo);
	}

	@Override
	public void darDeBajaServicio(Contratacion c) {
		//no hace nada
	}
}
