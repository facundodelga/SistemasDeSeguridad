package excepciones;

public class ContratacionNoEncontradaException extends Exception {

	public ContratacionNoEncontradaException() {
		super("La contratación no ha sido encontrada en los registros");
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
