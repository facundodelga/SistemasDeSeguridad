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

    /**
     * @param servicio
     * @return
     */
  /*  public double getdesc(iServicio servicio) {
        return servicio.getDescPlatino(this);
    }*/
    /**
     * @param servicio
     * @return
     */
   /* public double getValor(iServicio servicio) {
        return servicio.getTarifa();
    }*/


}
