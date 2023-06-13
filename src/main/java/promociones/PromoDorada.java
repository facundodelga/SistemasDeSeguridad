package promociones;

public class PromoDorada implements iPromocion{

    /**
     * @return
     */
    public double getPromoVivienda(double valor) {
        return -1500;
    }

    /**
     * @return
     */
    public double getPromoComercio(double valor) {
        return -2500;
    }

	@Override
	public String descripcion() {
		return "Dorada";
	}
}
