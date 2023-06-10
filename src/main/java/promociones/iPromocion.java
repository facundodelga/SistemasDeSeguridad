package promociones;

import java.io.Serializable;

public interface iPromocion extends Serializable {
	// double getValor(iServicio servicio);
	double getPromoVivienda(double valor);

	double getPromoComercio(double valor);

	String descripcion();

}
