package modelo;

import java.io.Serializable;

public interface MedioPago extends Serializable {
	double calcularTotal();
	String descripcion();
}
