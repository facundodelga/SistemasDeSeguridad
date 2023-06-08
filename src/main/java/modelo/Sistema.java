package modelo;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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
import excepciones.TipoDeContratableIncorrectoException;
import excepciones.TipoDePersonaIncorrectoException;
import excepciones.TipoDePromocionIncorrectoException;
import excepciones.TipoDeServicioIncorrectoException;
import factory.ContratableFactory;
import factory.MedioPagoFactory;
import factory.PersonaFactory;
import factory.PromocionFactory;
import factory.ServicioFactory;
import persona.Domicilio;
import persona.Persona;
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
		if(instancia == null){
			instancia = new Sistema();
		}
		return instancia;
	}
	
	//FACTURA
	
	/**
	 * <b>PRE:</b> Parámetro p distinto de null 
	 * Crea una nueva factura asociada a la persona especificada.
	 * Agrega la factura a la lista de facturas.
	 * 
	 * @param, parámetro de tipo Persona, p la persona asociada a la factura.
	 * @return el número de la factura creada.
	 */
	public int crearFactura(Persona p) {
		assert p != null : "El parámetro p no puede ser nulo";
		Factura f = p.crearFactura();
		facturas.add(f);
		/*Factura f = new Factura(p);
	    facturas.add(f);
	    */
		return f.getNumFactura();
	}

	/**
	 * <b>PRE:</b> Parámetro p distinto de null, parámetro c distinto de null 
	 * Crea una nueva factura asociada a una persona y una lista de contrataciones.
	 * Crea una nueva factura asociada a la persona y la lista de contrataciones especificadas.
	 * Agrega la factura a la lista de facturas.
	 * 
	 * @param p, parámetro de tipo Persona, la persona asociada a la factura.
	 * @param c, parámetro de tipo ArrayList<Contratacion>, la lista de contrataciones asociadas a la factura.
	 */
	public Factura crearFactura(Persona p, ArrayList<Contratacion> c) {
		assert p != null : "El parámetro p no puede ser nulo";
		Factura f = p.crearFactura(c);
		facturas.add(f);
		return f;
	}
	
	/**
	 * <b>PRE:</b> Parámetro p distinto de null, parámetro contr distinto de null 
	 * Crea una nueva factura asociada a una persona y una contratación.
	 * Crea una nueva factura asociada a la persona y la contratación especificadas.
	 * Agrega la factura a la lista de facturas.
	 * 
	 * @param p, parámetro de tipo Persona, la persona asociada a la factura.
	 * @param contr, parámetro de tipo Contratacion, la contratación asociada a la factura.
	 * @throws ContratacionYaRegistradaException si la contratación ya está registrada en otra factura.
	 * @throws DomicilioYaRegistradoException si el domicilio de la contratación ya está registrado en otra factura.
	 */
	public Factura crearFactura(Persona p, Contratacion contr) throws ContratacionYaRegistradaException, DomicilioYaRegistradoException {
		assert p != null : "El parámetro p no puede ser nulo";
		assert contr != null : "El parámetro contr no puede ser nulo";
		ArrayList<Contratacion> c = new ArrayList<Contratacion>();
	    c.add(contr);
	    Factura f = this.crearFactura(p, c);
	    return f;
	}


	/**
	 * <b>PRE:</b> Parámetro id positivo
	 * Elimina la factura con el identificador especificado de la lista de facturas.
	 * 
	 * @param id el identificador de la factura a eliminar.
	 * @throws FacturaNoEncontradaException si no se encuentra la factura con el identificador especificado.
	 */
	public void eliminarFactura(int id) throws FacturaNoEncontradaException {
		assert id >= 0: "El parámetro id debe ser distinto de null y positivo";
		try {
	        this.facturas.borraPorId(id);
	    } catch (FacturaNoEncontradaException e) {
	        throw e;
	    }
	}

	/**
	 * <b>PRE:</b> Parámetro id positivo, parámetro mp distinto de null y distinto de “”
	 * Realiza el pago de la factura encontrada utilizando el identificador y método de pago especificados.
	 * 
	 * @param id el identificador de la factura a pagar.
	 * @param mp el método de pago utilizado para pagar la factura.
	 * @return el total pagado de la factura.
	 * @throws FacturaNoEncontradaException
	 */
	/*public double pagarFactura(int id,String mp) throws FacturaNoEncontradaException {
		assert id >= 0 : "El parámetro id debe ser positivo";
		assert mp != null && !mp.isEmpty() : "El parámetro mp no puede ser nulo ni vacío";
	    Factura f;
		double total;
	    try{
			f = this.facturas.buscaPorId(id);
			MedioPago medio = MedioPagoFactory.getMedioPago(mp);
			total = f.calcularBonificacion(medio);
	    }catch(FacturaNoEncontradaException e) {
			throw e;
	    }
		return total;
	}
	*/
	/**
	* <b>PRE:</b> Parámetro f distinto de null, parámetro mp distinto de null y distinto de “”
	* Método que genera el valor de una factura luego de haber indicado el método de pago de la misma
	* @param f, parámetro de tipo Factura, es la factura que se desea pagar
	* @param mp, parámetro de tipo String, indica el tipo de medio de pago a utilizar (“EFECTIVO”,” TARJETA”,” CHEQUE”)
	* @return
	* @throws FacturaNoEncontradaException
	*/
	public void pagarFactura(Factura f,String mp, GregorianCalendar fecha) throws FacturaNoEncontradaException {
		assert f != null : "El parámetro f no puede ser nulo";
		assert mp != null && !mp.isBlank() : "El parámetro mp no puede ser nulo ni vacío";
		MedioPago medio = MedioPagoFactory.getMedioPago(mp, f);
		f.pagarFactura(medio);
		//f.pagarFactura(mp, fecha);
	}
	
	public Factura buscarFacturaPorPersona(String dni) throws PersonaNoEncontradaException, FacturaNoEncontradaException {
		assert dni != null && !dni.isBlank() : "El campo DNI no debe estar vacio";
		Persona p=personas.buscaPorDni(dni);
		return facturas.buscaPorPersona(p);
	}

	public Factura buscarFacturaPorId(int id) throws FacturaNoEncontradaException {
		assert id >= 0 : "El parámetro id debe ser positivo";
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
		assert dni != null && !dni.isBlank() : "El campo DNI no debe estar vacio";
		Contratacion contr=null;
		Persona p1;
		Factura f;
		try {
			p1=personas.buscaPorDni(dni);
			contr=new Contratacion(dni,dom,serv,promo);			
			try {
				f=facturas.buscaPorPersona(p1);
				f.agregarContratacion(contr);
			} catch (FacturaNoEncontradaException e) {
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
		assert c != null : "El campo Contratacion debe estar instanciado";
		assert a != null : "El campo iContratable debe estar instanciado";
		c.agregarContratable(a);
	}

	//puede que no sea del todo necesaria, o que sea preferible usar solo el metodo anterior
	public void contratarAdicional(String dni,Domicilio d,iContratable a) throws PersonaNoEncontradaException, DomicilioNoEncontradoException, ContratacionNoEncontradaException, DomicilioNoPerteneceAPersona {
		assert dni != null && !dni.isBlank() : "El campo dni debe estar vacio";
		assert d != null : "El campo Domicilio debe estar instanciado";
		assert a != null : "El campo iContratable debe estar instanciado";
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
	
	//creación de persona
	public Persona crearPersona(String nombre, String dni, String tipo) throws TipoDePersonaIncorrectoException {
		assert tipo != null && !tipo.isBlank() : "El campo tipo no debe estar vacio";
		Persona p=PersonaFactory.crearPersona(nombre,dni,tipo);
		this.personas.add(p);
		return p;
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
		assert promo != null && !promo.isBlank() : "El campo promo no debe estar vacio";
		return PromocionFactory.crearPromo(promo);
	}

	//SERVICIOS
	public iServicio obtenerServicio(String serv) throws TipoDeServicioIncorrectoException {
		assert serv != null && !serv.isBlank() : "El campo serv no debe estar vacio";
		return ServicioFactory.crearServicio(serv);
	}

	public iContratable obtenerContratable(String cont) throws TipoDeContratableIncorrectoException {
		assert cont != null && !cont.isBlank() : "El campo cont no debe estar vacio";
		return ContratableFactory.crearContratable(cont);
	}
	
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

	public Factura clonaFacturaPorId(int id) throws FacturaNoEncontradaException, CloneNotSupportedException {
		Factura facturaClone=null;
		facturaClone=(Factura) this.facturas.clonaFactura(id);
		return facturaClone;
	}

/*	
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
	*/
	
	/**
	 * Realiza la clonación de una persona por su DNI.
	 * Busca una persona en la lista de personas utilizando el DNI especificado.
	 * Realiza la clonación de la persona encontrada y muestra su representación en forma de cadena.
	 * 
	 * @param dni el DNI de la persona a buscar y clonar.
	 * @throws PersonaNoEncontradaException si no se encuentra la persona con el DNI especificado.
	 * @throws CloneNotSupportedException si la clonación de la persona no es compatible.
	 */
	  public Persona clonaPersonaPorDni(String dni) throws PersonaNoEncontradaException, CloneNotSupportedException {
			Persona personaClone=null;
			personaClone=(Persona) this.personas.clonaPersona(dni);
			return personaClone;
	  }	
	  /*
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
	*/
	  
	  public String detalleFactura(int id, String opcion) throws FacturaNoEncontradaException {
		  Factura f = facturas.buscaPorId(id);
		  MedioPago mp = MedioPagoFactory.getMedioPago(opcion, f);   
		  
		  return f.detalle(mp);
	    }

	    public String detalleFacturas() {
	        return detalleFacturas("");
	    }

	    public String detalleFacturas(String opcion) {
	        String res = "";

	        for (Factura factura : facturas) {
	            try {
	                res += "\n" + detalleFactura(factura.getNumFactura(), opcion) + "\n";
	            } catch (FacturaNoEncontradaException e) {
	            }
	        }

	        return res;
	    }


		public MedioPago getMedioPago(String metodoPago, Factura f) {
			MedioPago mp= MedioPagoFactory.getMedioPago(metodoPago, f);
			return mp;
		}
	  
}
