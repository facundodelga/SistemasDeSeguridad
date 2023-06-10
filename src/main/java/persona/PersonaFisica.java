package persona;

import java.util.ArrayList;

import contrataciones.Contratacion;
import contrataciones.iServicio;
import excepciones.AccionNoAutorizadaException;
import excepciones.ContratacionYaRegistradaException;
import excepciones.DomicilioNoEncontradoException;
import excepciones.DomicilioYaRegistradoException;
import excepciones.PersonaNoEncontradaException;
import modelo.*;
import promociones.iPromocion;

public class PersonaFisica extends Persona{
	IEstado estado;

	public PersonaFisica(String nombre, String dni) {
		super(nombre, dni);
		this.estado = new SinContratacionEstado(this);
	}

	@Override
	public Factura crearFactura() {
		return new FacturaFisica(this, Sistema.getInstancia().getMes());
	}

	@Override
	public Factura crearFactura(ArrayList<Contratacion> c) {
		return new FacturaFisica(this,c, Sistema.getInstancia().getMes());
	}
	
	public void pagarFactura(Factura f, MedioPago mp) {
		this.estado.pagarFactura(f,mp);
	}
	
	@Override
	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo, Factura f)throws AccionNoAutorizadaException, DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException, PersonaNoEncontradaException {
		this.estado.contratarServicio(dom, serv, promo, f);
	}

	@Override
	public void darDeBajaServicio(Contratacion c) throws AccionNoAutorizadaException {
		this.estado.darDeBajaServicio(c);
	}

	@Override
	public void actualizar(Factura f1, Factura f2) {
		//f1= factura del mes anterior
		//f2=factura de dos meses atras
		if(f1 == null ) 
			this.setEstado(new SinContratacionEstado(this));
		else 
			if(f2 == null || f2.isPagoRealizado()) 
				this.setEstado(new ConContratacionEstado(this));
			else 
				if(!f1.isPagoRealizado() && !f2.isPagoRealizado())
					this.setEstado(new MorosoEstado(this));				
	}


	public PersonaFisica(String nombre, String dni, ArrayList<Domicilio> domicilios, IEstado estado) {
		super(nombre, dni, domicilios, estado);
		this.estado = estado;
	}

	/**
	 * Crea y devuelve un clon de la instancia actual de PersonaFisica.
	 *
	 * @return El clon de la instancia actual de PersonaFisica.
	 * @throws CloneNotSupportedException Si se produce un error durante el proceso de clonación.
	 */
	@Override
	public Object clone()throws CloneNotSupportedException{
		try {
			PersonaFisica nObj=(PersonaFisica)super.clone();
			nObj.estado=this.estado;
			return nObj;
		}
		catch(CloneNotSupportedException e) {
			throw new CloneNotSupportedException("No se pudo clonar PersonaFisica, FALLO="+e.toString());
		}
	}
	
	public IEstado getEstado() {
		return estado;
	}

	public void setEstado(IEstado estado) {
		this.estado = estado;
	}

}
