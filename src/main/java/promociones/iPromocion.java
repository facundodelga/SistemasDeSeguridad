package promociones;

import java.io.Serializable;

public interface iPromocion extends Serializable {

	double getPromoVivienda(double valor);

	double getPromoComercio(double valor);

	String descripcion();

}
