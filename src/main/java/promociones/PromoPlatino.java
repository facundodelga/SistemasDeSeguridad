package promociones;

public class PromoPlatino implements iPromocion{

    /**
     * @return
     */
    public double getPromoVivienda(double valor) {
        return -(valor * 0.3);
    }

    /**
     * @return
     */
    public double getPromoComercio(double valor) {
        return -(valor * 0.35);
    }
    
    @Override
	public String descripcion() {
		return "Platino";
	}

}
