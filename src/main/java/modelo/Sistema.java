package modelo;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

import contrataciones.Contratacion;
import contrataciones.iContratable;
import contrataciones.iServicio;
import controlador.Controlador;
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
import simulacion.ServicioTecnico;
import simulacion.Tecnico;

public class Sistema implements Serializable, I_Sistema {
	
	//Controlador
	private Controlador controlador;
	//Del sistema
	private static Sistema instancia  = null;
	private ArregloFacturas facturas;
	private ArregloPersonas personas;
	private int mes;
	private ArrayList<Tecnico> tecnicos;
    private ServicioTecnico servicioTecnico;
	
	
	public Sistema(ArregloFacturas facturas, ArregloPersonas personas, ArrayList<Tecnico> tecnicos, ServicioTecnico servicioTecnico) {
        this.facturas = facturas;
        this.personas = personas;
        this.tecnicos = tecnicos;
        this.servicioTecnico = servicioTecnico;
    }
	private Sistema() {
		super();
		this.facturas=new ArregloFacturas();
		this.personas=new ArregloPersonas();
		this.mes = 0;
	}
	
	
	public static Sistema getInstancia(){
		if(instancia == null){
			instancia = new Sistema();
		}
		return instancia;
	}
	
	//Setea controlador
	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
	}

	//MES ACTUAL
	public void adelantarMes() {
		if(this.mes<12)
			this.mes++;
		else 
			this.mes=1;
		this.chequearEstados();
		this.crearNuevasFacturas();
	}

	public void atrasarMes() {
		if(this.mes!=1)
			this.mes--;
		else
			this.mes=12;
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
	public Factura crearFactura(Persona p) {
		assert p != null : "El parámetro p no puede ser nulo";
		Factura f = p.crearFactura();
		facturas.add(f);
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
	 * @throws PersonaNoEncontradaException 
	 */	
	public boolean pagarFactura(String dni, int id, String mp) throws FacturaNoEncontradaException, PersonaNoEncontradaException {
		assert id >= 0 : "El parámetro id debe ser positivo";
		Factura f;
		Persona p;
		f = this.facturas.buscaPorId(id);
		p = this.personas.buscaPorDni(dni); 
		MedioPago medio = MedioPagoFactory.getMedioPago(mp, f);		
		p.pagarFactura(f, medio);
		return f.isPagoRealizado();
	}
		
	public ArrayList<Factura> buscarFacturaPorPersonaDNI(String dni) throws PersonaNoEncontradaException, FacturaNoEncontradaException {
		assert dni != null && !dni.isBlank() : "El campo DNI no debe estar vacio";
		Persona p=personas.buscaPorDni(dni);
		return facturas.buscaPorPersona(p);
	}
	
	
	public Factura buscarFacturaPorId(int id) throws FacturaNoEncontradaException {
		assert id >= 0 : "El parámetro id debe ser positivo";
		return facturas.buscaPorId(id);
	}

	private void crearNuevasFacturas() {
		Factura f = null;
		for (Persona p : personas) {
			f = this.crearFactura(p);
			this.facturas.add(f);
		}
	}
	
	public String historicoFactura(Persona p) throws PersonaNoEncontradaException, FacturaNoEncontradaException {
		String res="";
		ArrayList<Factura> facs = this.buscarFacturaPorPersonaDNI(p.getDni());
		for (Factura f : facs) {
			res += f.detalle();
		}		
		return res;
	}
	
	  public String detalleFactura(int id, String medio) throws FacturaNoEncontradaException {
		  Factura f = facturas.buscaPorId(id);		  
		  return f.detalle(medio);
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

	
	//PERSONA
	
	//creación de persona
	public Persona crearPersona(String nombre, String dni, String tipo) throws TipoDePersonaIncorrectoException {
		assert tipo != null && !tipo.isBlank() : "El campo tipo no debe estar vacio";
		Persona p=PersonaFactory.crearPersona(nombre,dni,tipo);
		this.personas.add(p);
		return p;
	}
	
	private void chequearEstados() {
		ArrayList<Factura> facs;
		Factura f1=null,f2=null;
		
		for (Persona p : personas) {
			try {
				facs = this.facturas.buscaPorPersona(p);
			} catch (FacturaNoEncontradaException e) {
				facs = null;
			}
			if(facs!=null) {
				for (Factura factura : facs) {
					if(factura.getMes()==(this.mes-1))
						f1 = factura;
					else
						if(factura.getMes()==(this.mes-2))
							f2 = factura;
				}
			}
			p.actualizar(f1,f2);
		}	
	}
	
	//DOMICILIO

	//creacion de domicilios
	
	public Domicilio crearDomicilio(String calle, int num){
		Domicilio dom = new Domicilio(calle,num);
		return dom;
	}
	public void asignarNuevoDomicilio(Persona p, Domicilio d) throws DomicilioYaRegistradoException, PersonaNoEncontradaException {
		this.personas.buscaPorDni(p.getDni()).agregarDomicilio(d);
	}
	
	/*
	public void asignarNuevoDomicilio(String dni, Domicilio dom) throws DomicilioYaRegistradoException, PersonaNoEncontradaException {
		this.personas.buscaPorDni(dni).agregarDomicilio(dom);
	}
	*/
	//SERVICIO TECNICO
	public void darAltaTecnico(String nombre) {
        assert nombre != null : "El campo nombre no debe estar vacio";
        Tecnico t = new Tecnico(nombre, servicioTecnico);
        this.tecnicos.add(t);
    }
	
	//PROMOCIONES
	public iPromocion obtenerPromocion(String promo) throws TipoDePromocionIncorrectoException {
		assert promo != null && !promo.isBlank() : "El campo promo no debe estar vacio";
		return PromocionFactory.crearPromo(promo);
	}

	//CONTRATACIONES - SERVICIOS

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
/*
 	public void crearContratacion(String dni, Domicilio dom, iServicio serv, iPromocion promo) throws DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException, PersonaNoEncontradaException {
		assert dni != null && !dni.isBlank() : "El campo DNI no debe estar vacio";
		Contratacion contr=null;
		Persona p = this.personas.buscaPorDni(dni);
		contr=new Contratacion(dni,dom,serv,promo);			
		p.agregarContratacion(contr);
	}
*/
	public void crearContratacion(Persona p, Domicilio dom, iServicio serv, iPromocion promo) throws DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException, PersonaNoEncontradaException {
		Contratacion contr=null;
		contr=new Contratacion(p.getDni(),dom,serv,promo);			
		p.agregarContratacion(contr);
	}
	
	public void eliminarContratacion(Persona p, Domicilio dom) throws PersonaNoEncontradaException, DomicilioNoEncontradoException, FacturaNoEncontradaException {
		p.eliminarContratacion(dom);
	}
	/*
	public void eliminarContratacion(String dni, Domicilio dom) throws PersonaNoEncontradaException, DomicilioNoEncontradoException, FacturaNoEncontradaException {
		Persona p = this.personas.buscaPorDni(dni);
		p.eliminarContratacion(dom);
	}
	 */
	public iServicio obtenerServicio(String serv) throws TipoDeServicioIncorrectoException {
		assert serv != null && !serv.isBlank() : "El campo serv no debe estar vacio";
		return ServicioFactory.crearServicio(serv);
	}

	//ADICIONALES - CONTRATABLES
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

	public void contratarAdicional(String dni,Domicilio d,iContratable a) throws PersonaNoEncontradaException, DomicilioNoEncontradoException, ContratacionNoEncontradaException, DomicilioNoPerteneceAPersona {
		assert dni != null && !dni.isBlank() : "El campo dni debe estar vacio";
		assert d != null : "El campo Domicilio debe estar instanciado";
		assert a != null : "El campo iContratable debe estar instanciado";
		Persona p1,p2;
		
		p1 = personas.buscaPorDni(dni);//la persona existe
		p2 = personas.buscaPorDomicilio(d);

		if(p1.equals(p2)) {//el domicilio pertenece a la persona esperada
			ArrayList<Contratacion> cont= p1.getContrataciones();
			for (Contratacion c : cont) {
				if(c.getDomicilio()==d)
					c.agregarContratable(a);
			}
		}else {
			throw new DomicilioNoPerteneceAPersona(p1,d);
		}		
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
	  

		public MedioPago getMedioPago(String metodoPago, Factura f) {
			MedioPago mp= MedioPagoFactory.getMedioPago(metodoPago, f);
			return mp;
		}


		public int getMes() {
			return mes;
		}


		public void setMes(int mes) {
			this.mes = mes;
		}
		
		public ArregloFacturas getFacturas() {
			return facturas;
		}


		public ArregloPersonas getPersonas() {
			return personas;
		}

	public ArrayList<Tecnico> getTecnicos() {
		return tecnicos;
	}

	public void setTecnicos(ArrayList<Tecnico> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public ServicioTecnico getServicioTecnico() {
		return servicioTecnico;
	}

	public void setServicioTecnico(ServicioTecnico servicioTecnico) {
		this.servicioTecnico = servicioTecnico;
	}


	public void setFacturas(ArregloFacturas facturas) {
		this.facturas = facturas;
	}

	public void setPersonas(ArregloPersonas personas) {
		this.personas = personas;
	}
}
