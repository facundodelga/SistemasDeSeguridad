package excepciones;

public class TipoDeContratableIncorrectoException extends Exception {
	private String contratable;
	
	public TipoDeContratableIncorrectoException(String cont) {
		super("El tipo de contratable ingresado no existe ("+cont.toUpperCase()+")");
		this.contratable=cont.toUpperCase();
	}

}
