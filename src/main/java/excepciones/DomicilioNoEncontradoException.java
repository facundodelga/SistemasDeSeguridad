package excepciones;

import persona.Domicilio;

public class DomicilioNoEncontradoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String dni;
	Domicilio domicilio;
	
	public DomicilioNoEncontradoException(String dni, Domicilio domicilio) {
		super("El domicilio "+domicilio+" no ha sido encontrado en el registro de domicilios de "+dni);
		this.dni = dni;
		this.domicilio = domicilio;
	}
	
	public DomicilioNoEncontradoException(Domicilio domicilio) {
		super("El domicilio "+domicilio+" no ha sido encontrado");
		this.domicilio = domicilio;
		this.dni=null;
	}
	
	public String getDni() {
		return dni;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
}
