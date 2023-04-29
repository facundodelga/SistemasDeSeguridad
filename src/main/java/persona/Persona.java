package persona;

import java.util.ArrayList;
import java.util.Iterator;

import excepciones.DomicilioNoEncontradoException;
import excepciones.DomicilioYaRegistradoException;
import modelo.Factura;

public abstract class Persona {
	private String nombre;
	private String dni;
	private ArrayList<Domicilio> domicilios;
	
	/**
	 * <b>PRE:</b>El parámetro nombre debe ser distinto de null y distinto de "". El parámetro dni debe ser distinto de null y distinto de "". 
	 * @param nombre parámetro de tipo String, es el nombre completo de la persona a registrar en el sistema
	 * @param dni parámetro de tipo String, es el dni de la persona a registrar en el sistema
	 */
	public Persona(String nombre, String dni) {
		super();
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
		if(!this.existeDomicilio(dom))
			this.domicilios.remove(dom);
		else throw new DomicilioNoEncontradoException(dni,dom);
	}	
	
	//bonificaciones
	/**
	 * <b>PRE:</b> el parámetro debe ser distinto de null
	 * Método abstracto de permite calcular una bonificación sobre el importe de una factura dependiendo del tipo de persona
	 * @param factura Parámetro de tipo Factura, es una factura asociada a la persona.
	 * @return total a pagar por una factura, dependiendo de el tipo de la instancia persona.
	 */
	public abstract double calcularBonificacion(Factura factura); //Preguntar por bonificacion de juridica
}
