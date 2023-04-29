package excepciones;

public class PersonaNoEncontradaException extends Exception {
	String dni;
	
	public PersonaNoEncontradaException(String dni) {
		super("La persona no existe (dni: "+dni+")");
		this.dni=dni;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getDni() {
		return dni;
	}
}
