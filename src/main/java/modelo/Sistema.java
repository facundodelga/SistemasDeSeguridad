package modelo;

import java.util.ArrayList;

import contrataciones.Contratacion;
import contrataciones.iContratable;
import contrataciones.iServicio;
import excepciones.ContratacionNoEncontradaException;
import excepciones.ContratacionYaRegistradaException;
import excepciones.DomicilioNoEncontradoException;
import excepciones.DomicilioNoPerteneceAPersona;
import excepciones.DomicilioYaRegistradoException;
import excepciones.FacturaNoEncontradaException;
import excepciones.PersonaNoEncontradaException;
import persona.Domicilio;
import persona.Persona;
import promociones.iPromocion;

public class Sistema {
	private static Sistema instancia  = null;
	private ArregloFacturas facturas;
	//private ArrayList<Persona> personas;
	private ArregloPersonas personas;

	
	private Sistema() {
		super();
		this.facturas=new ArregloFacturas();
		this.personas=new ArregloPersonas();
	}
	
	public static Sistema getInstancia(){
		if(instancia==null){
			instancia = new Sistema();
		}
		return instancia;
	}

	
	//FACTURA
	public int crearFactura(Persona p){
		Factura f = new Factura(p);
		facturas.add(f);

		return f.getNumFactura();
	}

	public void crearFactura(Persona p,ArrayList<Contratacion> c){
		Factura f = new Factura(p,c);
		facturas.add(f);
	}

	public void crearFactura(Persona p, Contratacion contr){
		ArrayList<Contratacion>c=new ArrayList<Contratacion>();
		c.add(contr);
		this.crearFactura(p,c);
	}

	/**
 * 	
 * @param id
 * @throws FacturaNoEncontradaException
 */
	public void eliminarFactura(int id) throws FacturaNoEncontradaException {
		try{
			this.facturas.borraPorId(id);
		} catch (FacturaNoEncontradaException e) {
			throw e;
		}
	}
/**
 * 
 * @param id
 * @param mp
 * @return
 * @throws FacturaNoEncontradaException
 */
	public double pagarFactura(int id,String mp) throws FacturaNoEncontradaException {
	    Factura f;
		double total;
	    try{
			f = this.facturas.buscaPorId(id);
			total = f.totalModificadorMP(mp);
	    }catch(FacturaNoEncontradaException e) {
			throw e;
	    }
		return total;
	}
	
	//CONTRATACIONES
	/* 
	public void agregarContratacion(int id,Contratacion contratacion) throws FacturaNoEncontradaException,ContratacionYaRegistradaException, DomicilioYaRegistradoException {
	    Factura f;
		f = this.facturas.buscaPorId(id);
		f.agregarContratacion(contratacion);
	}
*/
	/**
	 * 
	 * @param dni
	 * @param dom
	 * @param serv
	 * @param promo
	 * @return
	 * @throws DomicilioYaRegistradoException
	 * @throws DomicilioNoEncontradoException
	 * @throws ContratacionYaRegistradaException
	 * @throws PersonaNoEncontradaException 
	 */
	public Contratacion crearContratacion(String dni, Domicilio dom, iServicio serv, iPromocion promo) throws DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException, PersonaNoEncontradaException {
		Contratacion contr=null;
		Persona p1;
		Factura f;
		try {
			p1=personas.buscaPorDni(dni);//la persona existe
			
			try {
				f=facturas.buscaPorPersona(p1);//la factura existe
				f.agregarContratacion(contr);

			} catch (FacturaNoEncontradaException e) {//si no se encuentra la factura, la creo
				this.crearFactura(p1,contr);
			}
		} catch (PersonaNoEncontradaException e) {
			throw e;
		}		
		return contr;
	}

	//ADICIONALES
	
	public void contratarAdicional(Contratacion c,iContratable a) {
		c.agregarContratable(a);
	}

	//puede que no sea del todo necesaria, o que sea preferible usar solo el metodo anterior
	public void contratarAdicional(String dni,Domicilio d,iContratable a) throws PersonaNoEncontradaException, DomicilioNoEncontradoException, ContratacionNoEncontradaException, DomicilioNoPerteneceAPersona {
		Persona p1,p2;
		Factura f;
		int i=0;
		p1 = personas.buscaPorDni(dni);//la persona existe
		p2 = personas.buscaPorDomicilio(d);
		if(p1.equals(p2)) {//el domicilio pertenece a la persona esperada
			try {
				f=facturas.buscaPorPersona(p1);
				while(i<this.facturas.size() && !f.getContrataciones().get(i).getDomicilio().equals(d))
				f.getContrataciones().get(i).agregarContratable(a);
			} catch (FacturaNoEncontradaException e) {
				throw new ContratacionNoEncontradaException();
			}
		}else {
			throw new DomicilioNoPerteneceAPersona(p1,d);
		}
		
		
	}
	
	
	// public void AgregaFactura(Factura f) {
	// 	if(existePersona(f))
	// 		facturas.add(f);
	// }



	// private boolean existePersona(Factura f) {
	// 	//this.facturas.buscaPorPersona(f);

	// 	return false;
	// }
}
