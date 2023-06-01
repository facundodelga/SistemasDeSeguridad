package excepciones;

import persona.Persona;

public class AccionNoAutorizadaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3156137971331640334L;
	Persona p;
	
	public AccionNoAutorizadaException(String message,Persona p) {
		super(message);
		this.p=p;
	}

}
