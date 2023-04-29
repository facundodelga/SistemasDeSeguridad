package excepciones;

import contrataciones.Contratacion;
import persona.Persona;

public class ContratacionYaRegistradaException extends Exception {
	private Contratacion con;
	private Persona per;
	
	public ContratacionYaRegistradaException(Contratacion con, Persona persona) {
		super("La contratacion ya estaba registrada");
		this.con=con;
		this.per=persona;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Contratacion getCon() {
		return con;
	}

	public Persona getPer() {
		return per;
	}

}
