package persona;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Objects;

import contrataciones.Contratacion;
import contrataciones.iServicio;
import excepciones.AccionNoAutorizadaException;
import excepciones.DomicilioNoEncontradoException;
import excepciones.DomicilioYaRegistradoException;
import modelo.Factura;
import modelo.MedioPago;
import promociones.iPromocion;

public abstract class Persona implements Cloneable{
	private String nombre;
	private String dni;
	private ArrayList<Domicilio> domicilios;
	private IEstado estado;
	
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
		if(!this.existeDomicilio(dom))
			this.domicilios.remove(dom);
		else throw new DomicilioNoEncontradoException(dni,dom);
	}	
	
	//bonificaciones
	
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
		public void pagarFactura(Factura f, MedioPago mp) {
		this.estado.pagarFactura(f,mp);
	}
		
	/*
	public void pagarFactura(Factura f, String mp, GregorianCalendar fecha) {
		this.estado.pagarFactura(f, fecha, mp);
	}*/

	public void contratarServicio(Domicilio dom, iServicio serv, iPromocion promo,Factura f) throws AccionNoAutorizadaException{
		
	}
	public void darDeBajaServicio(Contratacion c,Factura f) throws AccionNoAutorizadaException{
		
	}
	
	
}
