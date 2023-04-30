package excepciones;

public class TipoDeServicioIncorrectoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String servicio;
	
	public TipoDeServicioIncorrectoException(String serv) {
		super("El tipo de servicio ingresado no existe ("+serv+")");
		this.servicio=serv;
	}

	public String getServicio() {
		return servicio;
	}

}
