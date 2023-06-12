package persona;

import java.util.ArrayList;

import contrataciones.Contratacion;
import contrataciones.iServicio;
import excepciones.AccionNoAutorizadaException;
import excepciones.ContratacionYaRegistradaException;
import excepciones.DomicilioNoEncontradoException;
import excepciones.DomicilioYaRegistradoException;
import excepciones.PersonaNoEncontradaException;
import modelo.Factura;
import modelo.FacturaFisica;
import modelo.FacturaJuridica;
import modelo.MedioPago;
import modelo.Sistema;
import promociones.iPromocion;

public class PersonaJuridica extends Persona{

	public PersonaJuridica(String nombre, String dni) {
		super(nombre, dni);
	}

	public PersonaJuridica(String nombre, String dni, ArrayList<Domicilio> domicilios) {
		super(nombre, dni, domicilios);
	}

	@Override
	public Factura crearFactura() {
		Factura f = null;
		if(this.getContrataciones()!=null)
			f = new FacturaJuridica(this, Sistema.getInstancia().getMes());
		return f;
	}

	@Override
	public void pagarFactura(Factura f, MedioPago mp) {
		f.calcularBonificacion(mp);	
		f.setPagoRealizado(true);
	}

	@Override
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo, Factura f)throws AccionNoAutorizadaException, DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException, PersonaNoEncontradaException {
		Sistema.getInstancia().crearContratacion(this, dom, serv, promo);
	}

	@Override
	public void darDeBajaServicio(Contratacion c) throws AccionNoAutorizadaException {
		this.eliminarContratacion(c.getDomicilio());
	}
	

	/**
	 * Lanza una excepción de tipo CloneNotSupportedException indicando que la clonación de una PersonaJuridica no está permitida.
	 *
	 * @throws CloneNotSupportedException Si se intenta clonar una PersonaJuridica.
	 */
	@Override
	public Object clone()throws CloneNotSupportedException{
		throw new CloneNotSupportedException("PersonaJuridica no puede clonarse");
	}

	@Override
	public void actualizar(Factura f1, Factura f2) {
		//no realiza acciones porque no tiene estado
	}
}
