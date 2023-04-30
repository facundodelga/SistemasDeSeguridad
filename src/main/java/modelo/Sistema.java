package modelo;

import java.util.ArrayList;
import java.util.Scanner;

import contrataciones.AlarmaComercio;
import contrataciones.AlarmaVivienda;
import contrataciones.BotonAntipanico;
import contrataciones.Camara;

import contrataciones.Contratacion;
import contrataciones.MovilAcompañamiento;
import contrataciones.iContratable;
import contrataciones.iServicio;
import excepciones.ContratacionNoEncontradaException;
import excepciones.ContratacionYaRegistradaException;
import excepciones.DomicilioNoEncontradoException;
import excepciones.DomicilioNoPerteneceAPersona;
import excepciones.DomicilioYaRegistradoException;
import excepciones.FacturaNoEncontradaException;
import excepciones.PersonaNoEncontradaException;
import excepciones.TipoDeContratableIncorrectoException;
import excepciones.TipoDePersonaIncorrectoException;
import excepciones.TipoDePromocionIncorrectoException;
import excepciones.TipoDeServicioIncorrectoException;
import persona.Domicilio;
import persona.Persona;
import persona.PersonaFisica;
import persona.PersonaJuridica;
import promociones.PromoDorada;
import promociones.PromoPlatino;
import promociones.SinPromo;
import promociones.iPromocion;

public class Sistema {
	private static Sistema instancia  = null;
	private ArregloFacturas facturas;
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
	
	/**
	 * Crea una nueva factura asociada a la persona especificada.
	 * Agrega la factura a la lista de facturas.
	 * 
	 * @param p la persona asociada a la factura.
	 * @return el número de la factura creada.
	 */
	public int crearFactura(Persona p) {
	    Factura f = new Factura(p);
	    facturas.add(f);
	    return f.getNumFactura();
	}

	/**
	 * Crea una nueva factura asociada a una persona y una lista de contrataciones.
	 * Crea una nueva factura asociada a la persona y la lista de contrataciones especificadas.
	 * Agrega la factura a la lista de facturas.
	 * 
	 * @param p la persona asociada a la factura.
	 * @param c la lista de contrataciones asociadas a la factura.
	 */
	public void crearFactura(Persona p, ArrayList<Contratacion> c) {
	    Factura f = new Factura(p, c);
	    facturas.add(f);
	}
	
	/**
	 * Crea una nueva factura asociada a una persona y una contratación.
	 * Crea una nueva factura asociada a la persona y la contratación especificadas.
	 * Agrega la factura a la lista de facturas.
	 * 
	 * @param p la persona asociada a la factura.
	 * @param contr la contratación asociada a la factura.
	 * @throws ContratacionYaRegistradaException si la contratación ya está registrada en otra factura.
	 * @throws DomicilioYaRegistradoException si el domicilio de la contratación ya está registrado en otra factura.
	 */
	public void crearFactura(Persona p, Contratacion contr) throws ContratacionYaRegistradaException, DomicilioYaRegistradoException {
	    ArrayList<Contratacion> c = new ArrayList<Contratacion>();
	    c.add(contr);
	    this.crearFactura(p, c);
	}


	/**
	 * Elimina una factura por su identificador.
	 * Elimina la factura con el identificador especificado de la lista de facturas.
	 * 
	 * @param id el identificador de la factura a eliminar.
	 * @throws FacturaNoEncontradaException si no se encuentra la factura con el identificador especificado.
	 */
	public void eliminarFactura(int id) throws FacturaNoEncontradaException {
	    try {
	        this.facturas.borraPorId(id);
	    } catch (FacturaNoEncontradaException e) {
	        throw e;
	    }
	}

	/**
	 * Realiza el pago de una factura por su identificador y método de pago.
	 * Realiza el pago de la factura encontrada utilizando el identificador y método de pago especificados.
	 * 
	 * @param id el identificador de la factura a pagar.
	 * @param mp el método de pago utilizado para pagar la factura.
	 * @return el total pagado de la factura.
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
	
	public double pagarFactura(Factura f,String mp) throws FacturaNoEncontradaException {
		return f.totalModificadorMP(mp);
	}
	
	public Factura buscarFacturaPorPersona(String dni) throws PersonaNoEncontradaException, FacturaNoEncontradaException {
		Persona p=personas.buscaPorDni(dni);
		return facturas.buscaPorPersona(p);
	}

	public Factura buscarFacturaPorId(int id) throws FacturaNoEncontradaException {
		return facturas.buscaPorId(id);
	}
	
	
	//CONTRATACIONES
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

			contr=new Contratacion(dni,dom,serv,promo);			
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
	/**
	 * 
	 * @param c: refiere a la contratacion a la cual se le quiere agregar un Adicional
	 * @param a: refiere a la variable adicional la cua lse quiere adicionar a la Contratacion
	 */
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
				while(i<f.getContrataciones().size() && !d.equals(f.getContrataciones().get(i).getDomicilio())) {
					i++;
				}
					f.getContrataciones().get(i).agregarContratable(a);
			
			} catch (FacturaNoEncontradaException e) {
				throw new ContratacionNoEncontradaException();
			}
		}else {
			throw new DomicilioNoPerteneceAPersona(p1,d);
		}		
	}

	//PERSONA
	
	//creación de persona tipo Factory
	public Persona crearPersona(String nombre, String dni, String tipo) throws TipoDePersonaIncorrectoException {
		Persona nuevaP=null;
		if(tipo.equalsIgnoreCase("JURIDICA")) 
			nuevaP=new PersonaJuridica(nombre,dni);
		else
			if(tipo.equalsIgnoreCase("FISICA")) 
				nuevaP=new PersonaFisica(nombre,dni);
			else
				throw new TipoDePersonaIncorrectoException(tipo);
		
		this.personas.add(nuevaP);

		return nuevaP;
	}
	
	//DOMICILIO

	//creacion de domicilios
	
	public Domicilio crearDomicilio(String calle, int num){
		Domicilio dom = new Domicilio(calle,num);
		return dom;
	}
	
	public void asignarNuevoDomicilio(String dni, String calle, int num) throws DomicilioYaRegistradoException, PersonaNoEncontradaException {
		Domicilio dom = this.crearDomicilio(calle,num);
		this.personas.buscaPorDni(dni).agregarDomicilio(dom);
	}

	public void asignarNuevoDomicilio(String dni, Domicilio dom) throws DomicilioYaRegistradoException, PersonaNoEncontradaException {
		this.personas.buscaPorDni(dni).agregarDomicilio(dom);
	}
	
	
	//PROMOCIONES
	public iPromocion obtenerPromocion(String promo) throws TipoDePromocionIncorrectoException {
		iPromocion pr=null;
		if(promo.equalsIgnoreCase("DORADA"))
			pr = new PromoDorada();
		else
			if(promo.equalsIgnoreCase("PLATINO"))
				pr = new PromoPlatino();
			else
				if(promo.equalsIgnoreCase("SINPROMO"))
					pr = new SinPromo();
				else
					throw new TipoDePromocionIncorrectoException(promo);
		return pr;
	}

	//SERVICIOS
	public iServicio obtenerServicio(String serv) throws TipoDeServicioIncorrectoException {
		iServicio sr=null;
		if(serv.equalsIgnoreCase("VIVIENDA"))
			sr = new AlarmaVivienda();
		else
			if(serv.equalsIgnoreCase("COMERCIO"))
				sr = new AlarmaComercio();
			else	
				throw new TipoDeServicioIncorrectoException(serv);
		return sr;
	}

	public iContratable obtenerContratable(String cont) throws TipoDeContratableIncorrectoException {
		iContratable cr=null;
		if(cont.equalsIgnoreCase("BOTON"))
			cr = new BotonAntipanico();
		else
			if(cont.equalsIgnoreCase("CAMARA"))
				cr = new Camara();
			else
				if(cont.equalsIgnoreCase("MOVIL"))
					cr = new MovilAcompañamiento();
				else
					throw new TipoDeContratableIncorrectoException(cont);
		return cr;
		}
	
	// public void AgregaFactura(Factura f) {
	// 	if(existePersona(f))
	// 		facturas.add(f);
	// }



	// private boolean existePersona(Factura f) {
	// 	//this.facturas.buscaPorPersona(f);

	// 	return false;
	// }
	
	//CLONACIONES
	
	/**
	 * Realiza la clonación de una factura por su identificador.
	 * Realiza la clonación de la factura encontrada utilizando el identificador especificado.
	 * Muestra la representación de la factura clonada en forma de cadena.
	 * 
	 * @param id el identificador de la factura a buscar y clonar.
	 * @throws FacturaNoEncontradaException si no se encuentra la factura con el identificador especificado.
	 * @throws CloneNotSupportedException si la clonación de la factura no es compatible.
	 */
	public void clonaFacturaPorId(int id) {
		Object facturaClone;
		try {
			facturaClone=this.facturas.clonaFactura(id);
			System.out.println(facturaClone.toString()); 
		}
		catch(FacturaNoEncontradaException e) {
			System.out.println(e.toString());
		}
		catch(CloneNotSupportedException e) {
			System.out.println(e.toString());
		}
	}
	
	
	/**
	 * Realiza la clonación de una persona por su DNI.
	 * Busca una persona en la lista de personas utilizando el DNI especificado.
	 * Realiza la clonación de la persona encontrada y muestra su representación en forma de cadena.
	 * 
	 * @param dni el DNI de la persona a buscar y clonar.
	 * @throws PersonaNoEncontradaException si no se encuentra la persona con el DNI especificado.
	 * @throws CloneNotSupportedException si la clonación de la persona no es compatible.
	 */
	public void clonaPersonaPorDni(String dni) {
		Object personaClone;
		try {
			personaClone=this.personas.clonaPersona(dni);
			System.out.println(personaClone.toString());
		}
		catch(PersonaNoEncontradaException e) {
			System.out.println(e.toString());
		}
		catch(CloneNotSupportedException e) {
			System.out.println(e.toString());
		}
	}
	
}
