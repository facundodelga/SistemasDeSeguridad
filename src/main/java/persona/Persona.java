package persona;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import contrataciones.Contratacion;
import contrataciones.iServicio;
import excepciones.AccionNoAutorizadaException;
import excepciones.ContratacionYaRegistradaException;
import excepciones.DomicilioNoEncontradoException;
import excepciones.DomicilioYaRegistradoException;
import excepciones.PersonaNoEncontradaException;
import modelo.Factura;
import modelo.MedioPago;
import promociones.iPromocion;

public abstract class Persona implements Cloneable{
	private String nombre;
	private String dni;
	private ArrayList<Domicilio> domicilios;
	protected ArrayList<Contratacion> contrataciones;
	
	/**
	 * <b>PRE:</b>El parámetro nombre debe ser distinto de null y distinto de "". El parámetro dni debe ser distinto de null y distinto de "". 
	 * @param nombre parámetro de tipo String, es el nombre completo de la persona a registrar en el sistema
	 * @param dni parámetro de tipo String, es el dni de la persona a registrar en el sistema
	 */
	public Persona(String nombre, String dni) {
		super();
		assert nombre != null && !nombre.isBlank() : "El campo Nombre no puede estar vacio";
		assert dni != null && !dni.isBlank() : "El campo DNI no puede estar vacio";
		this.nombre = nombre;
		this.dni = dni;
		this.domicilios = new ArrayList<Domicilio>();
	}
	
	public Persona(String nombre, String dni, ArrayList<Domicilio> domicilios) {
		this.nombre = nombre;
		this.dni = dni;
		this.domicilios = domicilios;
	}

	
	//getters
	public String getNombre() {
		return nombre;
	}
	public String getDni() {
		return dni;
	}
	
	//domicilio
	public Iterator<Domicilio> getIterator(){
		return this.domicilios.iterator();
	}
	
	public Domicilio getDomicilio(int i) {
		return this.domicilios.get(i);
	}
	
	public boolean existeDomicilio(Domicilio dom) {
		return this.domicilios.contains(dom);
	}
	
	/**
	 * <b>PRE:</b>El parámetro domicilio debe ser distinto de null.
	 * Método que inserta un domicilio nuevo en la colección de domicilios de la persona. OPCIONAL! Lanza excepción cuando el domicilio ya esta registrado. 
	 * @param dom Parámetro de tipo Domicilio, es un nuevo domicilio de la persona instanciada
	 * @throws DomicilioYaRegistradoException 
	 */
	public void agregarDomicilio(Domicilio dom) throws DomicilioYaRegistradoException {
		assert dom != null : "El campo Domicilio debe estar instanciado";
		if(!this.existeDomicilio(dom))
			this.domicilios.add(dom);
		else 
			throw new DomicilioYaRegistradoException(dni,dom);
	}
	
	/**
	 * <b>PRE:</b>El parámetro domicilio debe ser distinto de null.
	 * Método que elimina un domicilio existente de la colección de domicilios de la persona. OPCIONAL! Lanza excepción cuando el domicilio no se encuentra en la lista. 
	 * @param dom Parámetro de tipo Domicilio, es un domicilio que pertenecia a la persona, pero que se desea retirar
	 * @throws DomicilioNoEncontradoException 
	 */
	public void eliminarDomicilio(Domicilio dom) throws DomicilioNoEncontradoException{
		assert dom != null : "El campo Domicilio debe estar instanciado";
		if(this.existeDomicilio(dom))
			this.domicilios.remove(dom);
		else throw new DomicilioNoEncontradoException(dni,dom);
	}	
	
	//CONTRATACIONES
	public ArrayList<Contratacion> getContrataciones() {
		return contrataciones;
	}
	
	/**
	 * Verifica si existe una contratación específica en la lista de contrataciones.
	 * @param con la contratación que se desea buscar.
	 * @return true si la contratación existe en la lista, false en caso contrario.
	 */
	public boolean existeContratacion(Contratacion con) {
		assert con != null : "El campo Contratacion debe estar instanciado";
		return contrataciones.contains(con);
	}
	
	/**
	 * <b>PRE:</b>El parámetro con debe ser distinto de null.
	 * Método que inserta una contratacion nueva en la colección de contrataciones de la factura. Lanza excepción cuando la contratacion ya esta registrado. 
	 * @param con Parámetro de tipo Contratacion, es una nueva contratacion de la factura instanciada
	 * @throws ContratacionYaRegistradaException, DomicilioYaRegistradoException 
	 */
	
	public void agregarContratacion(Contratacion con) throws ContratacionYaRegistradaException,ContratacionYaRegistradaException, DomicilioYaRegistradoException {
		assert con != null : "El campo Contratacion debe estar instanciado";
		if (!this.existeContratacion(con)) {
			if (this.existeDomicilio(con.getDomicilio())) {
				throw new DomicilioYaRegistradoException(con.getDni(), con.getDomicilio());
			}
			this.contrataciones.add(con);
		} else
			throw new ContratacionYaRegistradaException(con, this);
	}
	
	/**
	 * <b>PRE:</b>El parámetro con debe ser distinto de null.
	 * Método que elimina una contratacion existente de la colección de contrataciones de la persona. OPCIONAL! Lanza excepción cuando la contratacion no se encuentra en la lista. 
	 * @param dom Parámetro de tipo Domicilio, es una contratacion que pertenecia a la factura, pero que se desea retirar
	 */
	public void eliminarContratacion(Contratacion con) {
		assert con != null : "El campo Contratacion debe estar instanciado";
		if(this.existeContratacion(con)) {
			this.domicilios.remove(con.getDomicilio());
			this.contrataciones.remove(con);
		}
	}	
	
	private Contratacion buscarContratacion(Domicilio dom) {
		Contratacion con = null;
		for (Contratacion contratacion : contrataciones) {
			if(contratacion.getDomicilio()==dom)
				con=contratacion;
		}
		return con;
	}
	public void eliminarContratacion(Domicilio dom) {
		assert dom != null : "El campo domicilio debe estar instanciado";
		Contratacion con = this.buscarContratacion(dom);
		if(this.existeContratacion(con)) {
			this.domicilios.remove(con.getDomicilio());
			this.contrataciones.remove(con);
		}
	}	
	
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
	/**
	 * Compara si el objeto actual es igual a otro objeto.
	 * 
	 * @param obj El objeto con el cual se desea realizar la comparación.
	 * @return true si el objeto actual es igual a obj, false de lo contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return nombre + ", DNI: " + dni;
	}

	public abstract Factura crearFactura();

	public abstract Factura crearFactura(ArrayList<Contratacion> c);
	
	public abstract void pagarFactura(Factura f, MedioPago mp);
		
	public abstract void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo,Factura f) throws AccionNoAutorizadaException, DomicilioYaRegistradoException, DomicilioNoEncontradoException, ContratacionYaRegistradaException, PersonaNoEncontradaException;
	
	public abstract void darDeBajaServicio(Contratacion c) throws AccionNoAutorizadaException;

	/**
	 * Crea y devuelve una copia superficial de este objeto Persona.
	 * 
	 * @return una copia superficial de este objeto Persona.
	 * @throws CloneNotSupportedException si la clonación no es compatible o el objeto no es clonable.
	 */
	@Override
	public Object clone()throws CloneNotSupportedException{
		int i;
		try {
			Persona nObj=(Persona)super.clone();
			nObj.dni=this.dni;
			nObj.nombre=this.nombre;
			nObj.domicilios = new ArrayList<Domicilio>();// linea de correccion de clone, consultar
			for(i=0;i<this.domicilios.size();i++) {
				nObj.domicilios.add( (Domicilio) this.domicilios.get(i).clone());
			}
			return nObj;
		}
		catch(CloneNotSupportedException e) {
			throw new CloneNotSupportedException("No se pudo clonar Persona, FALLO="+e.toString());
		}
	}

	public ArrayList<Domicilio> getDomicilios() {
		return domicilios;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setDomicilios(ArrayList<Domicilio> domicilios) {
		this.domicilios = domicilios;
	}

	public void setContrataciones(ArrayList<Contratacion> contrataciones) {
		this.contrataciones = contrataciones;
	}

	public abstract void actualizar(Factura f1, Factura f2);


}
