package excepciones;

public class TipoDePromocionIncorrectoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String promo;
	
	public TipoDePromocionIncorrectoException(String promo) {
		super("El tipo de promocion ingresado no existe ("+promo.toUpperCase()+")");
		this.promo=promo.toUpperCase();
	}

	public String getPromo() {
		return promo;
	}
	

}
