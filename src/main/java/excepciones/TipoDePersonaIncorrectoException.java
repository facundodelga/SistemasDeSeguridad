package excepciones;

public class TipoDePersonaIncorrectoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipo;
	
	public TipoDePersonaIncorrectoException(String tipo) {
		super("El tipo de persona intruducido no coincide con los posibles ("+tipo.toUpperCase()+")");
		this.tipo=tipo.toUpperCase();
	}

	public String getTipo() {
		return tipo;
	}

}
