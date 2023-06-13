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
import excepciones.PersonaYaExisteException;
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
import simulacion.ClienteThread;
import simulacion.ServicioTecnico;
import simulacion.Tecnico;

public class Sistema implements Serializable, I_Sistema {
	
	/**
     * 
     */
    private static final long serialVersionUID = 7533724918767447614L;
	//Controlador
	private Controlador controlador;
	//Del sistema
	private static Sistema instancia  = null;
	private ArregloFacturas facturas;
	private ArregloPersonas personas;
	private int mes;
	private ArrayList<Tecnico> tecnicos;
        private ServicioTecnico servicioTecnico;
        private ArrayList<ClienteThread> clientesHilo;
	
	
	public Sistema(ArregloFacturas facturas, ArregloPersonas personas, ArrayList<Tecnico> tecnicos,ArrayList<ClienteThread> clientesHilo, ServicioTecnico servicioTecnico) {
            this.facturas = facturas;
            this.personas = personas;
            this.tecnicos = tecnicos;
            this.servicioTecnico = servicioTecnico;
            this.clientesHilo = clientesHilo;
        }
	
	private Sistema() {
		super();
		this.facturas=new ArregloFacturas();
		this.personas=new ArregloPersonas();
		this.servicioTecnico = new ServicioTecnico();
		this.tecnicos = new ArrayList<>();
		this.clientesHilo = new ArrayList<>();
		this.mes = 0;
	}
	
	
	public static Sistema getInstancia(){
		if(instancia == null){
			instancia = new Sistema();
		}
		return instancia;
	}
	
//	Setea controlador
	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
	}

	//MES ACTUAL
	/**
	 * Adelanta el mes actual y realiza las acciones correspondientes.
	 * Si el mes actual es menor a 12, se incrementa en 1.
	 * Si el mes actual es igual a 12, se reinicia a 1.
	 * Luego, se realiza la verificación de estados y se crean nuevas facturas.
	 */
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
		Factura f=null;
		Persona p;
		//f = this.facturas.buscaPorId(id);
		for (Factura fac : facturas) {
			if(fac!=null && fac.getNumFactura()==id)
				f=fac;
		}
		p = this.personas.buscaPorDni(dni); 
		MedioPago medio = MedioPagoFactory.getMedioPago(mp, f);		
		p.pagarFactura(f, medio);
		return f.isPagoRealizado();
	}
	/**
	 * Busca las facturas correspondientes a una persona mediante su número de DNI.
	 *
	 * @param dni El número de DNI de la persona.
	 * @return Una lista de facturas asociadas a la persona.
	 * @throws PersonaNoEncontradaException Si no se encuentra la persona con el DNI especificado.
	 * @throws FacturaNoEncontradaException Si no se encuentran facturas asociadas a la persona.
	 */
	public ArrayList<Factura> buscarFacturaPorPersonaDNI(String dni) throws PersonaNoEncontradaException, FacturaNoEncontradaException {
		assert dni != null && !dni.isBlank() : "El campo DNI no debe estar vacio";
		Persona p=personas.buscaPorDni(dni);
		return facturas.buscaPorPersona(p);
	}
	
	/**
	 * Busca una factura por su identificador.
	 *
	 * @param id El identificador de la factura.
	 * @return La factura con el identificador especificado.
	 * @throws FacturaNoEncontradaException Si no se encuentra la factura con el identificador especificado.
	 */
	public Factura buscarFacturaPorId(int id) throws FacturaNoEncontradaException {
		assert id >= 0 : "El parámetro id debe ser positivo";
		return facturas.buscaPorId(id);
	}
	
	/**
	 * Crea nuevas facturas para todas las personas registradas en el sistema.
	 * Cada persona tendrá una factura asociada.
	 * Las nuevas facturas se agregan a la lista de facturas del sistema.
	 */
	private void crearNuevasFacturas() {
		Factura f = null;
		for (Persona p : personas) {
			f = this.crearFactura(p);
			this.facturas.add(f);
		}
	}
	
	/**
	 * Genera el historial de facturas de una persona.
	 *
	 * @param p La persona para la cual se desea obtener el historial de facturas.
	 * @return Una cadena que representa el historial de facturas de la persona.
	 * @throws PersonaNoEncontradaException Si no se encuentra la persona en el sistema.
	 * @throws FacturaNoEncontradaException Si no se encuentran facturas asociadas a la persona.
	 */
	public String historicoFactura(Persona p) throws PersonaNoEncontradaException, FacturaNoEncontradaException {
		String res="";
		ArrayList<Factura> facs = this.buscarFacturaPorPersonaDNI(p.getDni());
		for (Factura f : facs) {
			res += f.detalle();
		}		
		return res;
	}
	/**
	 * Obtiene el detalle de una factura específica.
	 *
	 * @param id    El identificador de la factura.
	 * @param medio El método de pago utilizado en la factura.
	 * @return Una cadena que representa el detalle de la factura.
	 * @throws FacturaNoEncontradaException Si no se encuentra la factura con el identificador especificado.
	 */
	  public String detalleFactura(int id, String medio) throws FacturaNoEncontradaException {
		  Factura f = facturas.buscaPorId(id);		  
		  return f.detalle(medio);
	    }

	    public String detalleFacturas() {
	        return detalleFacturas("");
	    }
	    /**
	     * Obtiene el detalle de todas las facturas del sistema con una opción de filtrado adicional.
	     *
	     * @param opcion La opción de filtrado adicional.
	     * @return Una cadena que representa el detalle de las facturas según la opción de filtrado.
	     */
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
	    /**
	     * Crea una nueva persona y la agrega al sistema.
	     *
	     * @param nombre El nombre de la persona.
	     * @param dni    El DNI de la persona.
	     * @param tipo   El tipo de persona (FISICA o JURIDICA).
	     * @return La persona creada.
	     * @throws TipoDePersonaIncorrectoException Si el tipo de persona no es válido.
	     * @throws PersonaYaExisteException        Si ya existe una persona con el mismo DNI en el sistema.
	     */
	public Persona crearPersona(String nombre, String dni, String tipo) throws TipoDePersonaIncorrectoException, PersonaYaExisteException {
		assert tipo != null && !tipo.isBlank() : "El campo tipo no debe estar vacio";
		
		if (personas.existePersona(dni)) {
			throw new PersonaYaExisteException("La persona ya existe, con DNI: " + dni);
		} 

		
		Persona p=PersonaFactory.crearPersona(nombre,dni,tipo);
		this.personas.add(p);
		
		this.darAltaClienteThread(nombre);
		
		return p;
	}
	/**
	 * Realiza la verificación de los estados de las facturas de las personas en el sistema.
	 * Actualiza el estado de cada persona en base a las facturas anteriores.
	 */
	private void chequearEstados() {
		ArrayList<Factura> facs;
		Factura f1=null,f2=null;
		
		for (Persona p : personas) {
			/*try {
				facs = this.facturas.buscaPorPersona(p);
			} catch (FacturaNoEncontradaException e) {
				facs = null;
			}*/
			facs =this.facturas;
			if(facs!=null) {
				for (Factura factura : facs) {
					if(factura!=null && factura.getPersona() == p)
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
	/**
	 * Crea un nuevo objeto Domicilio con la calle y número especificados.
	 *
	 * @param calle La calle del domicilio.
	 * @param num   El número del domicilio.
	 * @return El objeto Domicilio creado.
	 */
	public Domicilio crearDomicilio(String calle, int num){
		Domicilio dom = new Domicilio(calle,num);
		return dom;
	}
	/**
	 * Asigna un nuevo domicilio a una persona específica.
	 *
	 * @param p La persona a la que se le asignará el nuevo domicilio.
	 * @param d El nuevo domicilio a asignar.
	 * @throws DomicilioYaRegistradoException    Si el domicilio ya está registrado para la persona.
	 * @throws PersonaNoEncontradaException      Si la persona no se encuentra en el sistema.
	 */
	public void asignarNuevoDomicilio(Persona p, Domicilio d) throws DomicilioYaRegistradoException, PersonaNoEncontradaException {
		this.personas.buscaPorDni(p.getDni()).agregarDomicilio(d);
	}
	
	/*
	public void asignarNuevoDomicilio(String dni, Domicilio dom) throws DomicilioYaRegistradoException, PersonaNoEncontradaException {
		this.personas.buscaPorDni(dni).agregarDomicilio(dom);
	}
	*/
	//SERVICIO TECNICO
	/**
	 * Da de alta a un nuevo técnico con el nombre especificado.
	 *
	 * @param nombre El nombre del técnico.
	 */
	public void darAltaTecnico(String nombre) {
        assert nombre != null : "El campo nombre no debe estar vacio";
        Tecnico t = new Tecnico(nombre, this.servicioTecnico);
        this.tecnicos.add(t);
    }
	/**
	 * Da de alta a un nuevo cliente en un hilo de ejecución separado.
	 *
	 * @param nombre El nombre del cliente.
	 */
	private void darAltaClienteThread(String nombre) {
	    ClienteThread cliente = new ClienteThread(nombre,this.servicioTecnico);
	    this.clientesHilo.add(cliente);
	}
	/**
	 * Reinicia la simulación, restableciendo los datos del servicio técnico, los clientes y los técnicos.
	 * Inicia la simulación nuevamente.
	 */
	public void reiniciarSimulacion() {
	    this.servicioTecnico.setPedidos(new ArrayList<String>());
	    this.servicioTecnico.setTecnicosDisponibles(0);

	    ArrayList<ClienteThread> auxClientes = new ArrayList<ClienteThread>(); 
            
	    for (ClienteThread clienteThread : this.clientesHilo) {
		clienteThread = new ClienteThread(clienteThread.getNombre(),this.servicioTecnico);
		auxClientes.add(clienteThread);
	    }
	    
	    this.clientesHilo.clear();
	    
	    ArrayList<Tecnico> auxTecnicos = new ArrayList<Tecnico>(); 
	    
	    for (Tecnico tecnico : this.tecnicos) {
		tecnico = new Tecnico(tecnico.getNombre(),this.servicioTecnico);
		auxTecnicos.add(tecnico);

	    }
	    
	    this.tecnicos.clear();
            
            this.clientesHilo = auxClientes;
            this.tecnicos = auxTecnicos;
            
            System.out.println("CANTIDAD DE TECNICOS DISPONIBLES " + this.tecnicos.size());
            System.out.println("CANTIDAD DE CLIENTES " + this.clientesHilo.size());
            this.servicioTecnico.setTecnicosDisponibles(this.tecnicos.size());
            iniciaSimulacion();
	   
	}
	/**
	 * Inicia la simulación, iniciando los hilos de ejecución para los técnicos y clientes.
	 * Cada técnico y cliente se ejecuta en un hilo separado.
	 */
	public void iniciaSimulacion() {
		for (Tecnico t : this.tecnicos) {
		    
		    	    t.start();
	           }
		    
		    for (ClienteThread clienteThread : this.clientesHilo) {
			
			    clienteThread.start();
		    }
	    
	}
	
	/**
	 * Detiene la simulación, estableciendo el estado activo de todos los técnicos y clientes en falso.
	 * Los hilos de ejecución se detendrán cuando lleguen al siguiente punto de verificación.
	 */
	public void pararSimulacion() {
	    for (Tecnico t : this.tecnicos) {
            	t.setActivo(false);
            }
	    
	    for (ClienteThread clienteThread : clientesHilo) {
		clienteThread.setActivo(false);
	    }
	}
	
	
	//PROMOCIONES
	/**
	 * Obtiene una instancia de la interfaz `iPromocion` según el tipo de promoción especificado.
	 *
	 * @param promo el tipo de promoción
	 * @return una instancia de la promoción correspondiente
	 * @throws TipoDePromocionIncorrectoException si el tipo de promoción especificado es incorrecto
	 */
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
	/**
	 * Crea una nueva contratación para una persona en un domicilio, con un servicio y una promoción especificados.
	 *
	 * @param p la persona que realiza la contratación
	 * @param dom el domicilio donde se realizará la contratación
	 * @param serv el tipo de servicio a contratar
	 * @param promo la promoción a aplicar en la contratación
	 * @return la nueva contratación creada
	 * @throws DomicilioYaRegistradoException si el domicilio ya está registrado en la persona
	 * @throws DomicilioNoEncontradoException si no se encuentra el domicilio especificado
	 * @throws ContratacionYaRegistradaException si ya existe una contratación registrada para el domicilio y la persona
	 * @throws PersonaNoEncontradaException si no se encuentra la persona especificada
	 */
	public Contratacion crearContratacion(Persona p, Domicilio dom, iServicio serv, iPromocion promo) throws DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException, PersonaNoEncontradaException {
		Contratacion contr=null;
		contr=new Contratacion(p.getDni(),dom,serv,promo);			
		p.agregarContratacion(contr);
		return contr;
	}
	
	/**
	 * Elimina una contratación de una persona en un domicilio.
	 *
	 * @param p la persona que tiene la contratación
	 * @param dom el domicilio de la contratación a eliminar
	 * @throws PersonaNoEncontradaException si no se encuentra la persona especificada
	 * @throws DomicilioNoEncontradoException si no se encuentra el domicilio especificado
	 * @throws FacturaNoEncontradaException si no se encuentra la factura asociada a la contratación
	 */
	public void eliminarContratacion(Persona p, Domicilio dom) throws PersonaNoEncontradaException, DomicilioNoEncontradoException, FacturaNoEncontradaException {
		p.eliminarContratacion(dom);
	}
	/*
	public void eliminarContratacion(String dni, Domicilio dom) throws PersonaNoEncontradaException, DomicilioNoEncontradoException, FacturaNoEncontradaException {
		Persona p = this.personas.buscaPorDni(dni);
		p.eliminarContratacion(dom);
	}
	 */
	/**
	 * Obtiene una instancia de la interfaz `iServicio` según el tipo de servicio especificado.
	 *
	 * @param serv el tipo de servicio
	 * @return una instancia del servicio correspondiente
	 * @throws TipoDeServicioIncorrectoException si el tipo de servicio especificado es incorrecto
	 */
	public iServicio obtenerServicio(String serv) throws TipoDeServicioIncorrectoException {
		assert serv != null && !serv.isBlank() : "El campo serv no debe estar vacio";
		return ServicioFactory.crearServicio(serv);
	}

	//ADICIONALES - CONTRATABLES
	/**
	 * Agrega un servicio adicional a una contratación existente.
	 *
	 * @param c la contratación a la que se agregará el servicio adicional
	 * @param a el servicio adicional a contratar
	 */
	public void contratarAdicional(Contratacion c,iContratable a) {
		assert c != null : "El campo Contratacion debe estar instanciado";
		assert a != null : "El campo iContratable debe estar instanciado";
		c.agregarContratable(a);
	}
	
	/**
	 * Agrega un servicio adicional a una contratación existente de una persona en un domicilio específico.
	 *
	 * @param dni el DNI de la persona
	 * @param d el domicilio de la contratación
	 * @param a el servicio adicional a contratar
	 * @throws PersonaNoEncontradaException si no se encuentra la persona especificada
	 * @throws DomicilioNoEncontradoException si no se encuentra el domicilio especificado
	 * @throws ContratacionNoEncontradaException si no se encuentra la contratación correspondiente al domicilio y la persona
	 * @throws DomicilioNoPerteneceAPersona si el domicilio no pertenece a la persona especificada
	 */
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
	/**
	 * Obtiene el objeto iContratable correspondiente al tipo de contratable especificado.
	 *
	 * @param cont el tipo de contratable
	 * @return el objeto iContratable correspondiente al tipo de contratable especificado
	 * @throws TipoDeContratableIncorrectoException si el tipo de contratable especificado es incorrecto
	 */
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
	  
	  /**
	   * Obtiene el objeto MedioPago correspondiente al método de pago especificado para una factura.
	   *
	   * @param metodoPago el método de pago a utilizar
	   * @param f la factura para la cual se obtendrá el medio de pago
	   * @return el objeto MedioPago correspondiente al método de pago y factura especificados
	   */
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
	public ArrayList<ClienteThread> getClientesHilo() {
	    return clientesHilo;
	}
	public void setClientesHilo(ArrayList<ClienteThread> clientesHilo) {
	    this.clientesHilo = clientesHilo;
	}
	
	
	
}
