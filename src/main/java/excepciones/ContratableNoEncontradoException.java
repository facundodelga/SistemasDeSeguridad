package excepciones;

import contrataciones.iContratable;

public class ContratableNoEncontradoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private iContratable cont;
	
	public ContratableNoEncontradoException(int id, iContratable cont) {
		super(cont+ " no fue encontrado en el registro de la contratación de id: "+id);
		this.id=id;
		this.cont=cont;
	}

	public int getId() {
		return id;
	}

	public iContratable getCont() {
		return cont;
	}

	
}
