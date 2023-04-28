package persona;

public class DomicilioYaRegistradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String dni;
	Domicilio domicilio;
	
	public DomicilioYaRegistradoException(String dni, Domicilio domicilio) {
		super("El domicilio "+domicilio+" ya ha sido registrado para la persona de DNI: "+dni);
		this.dni = dni;
		this.domicilio = domicilio;
	}
	public String getDni() {
		return dni;
	}
	public Domicilio getDomicilio() {
		return domicilio;
	}
}
