package excepciones;

public class FacturaNoEncontradaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	
	public FacturaNoEncontradaException(int id) {
		super("La factura buscada no existe (id: "+id+")");
		this.id=id;
	}
	
	public FacturaNoEncontradaException() {
		super("La factura buscada no existe");
		this.id=-1;
	}
	
	public int getId() {
		return id;
	}

}
