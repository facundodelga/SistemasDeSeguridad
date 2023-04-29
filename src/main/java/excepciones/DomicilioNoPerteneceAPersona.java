package excepciones;

import persona.Domicilio;
import persona.Persona;

public class DomicilioNoPerteneceAPersona extends Exception {
	private Persona p;
	private Domicilio d;
	
	public DomicilioNoPerteneceAPersona(Persona p, Domicilio d) {
		super("El domicilio no le pertenece a la persona indicada");
		this.p=p;
		this.d=d;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Persona getP() {
		return p;
	}

	public Domicilio getD() {
		return d;
	}
	
	

}
