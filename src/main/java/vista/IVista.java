package vista;

import java.awt.event.ActionListener;

import controlador.Controlador;

public interface IVista {

	void setActionListener(ActionListener controlador);
	String getNombre(); //en la vista tecnicos, va a devolver el del tecnico, en las otras devuelve el nombre del abonado
	String getCalle();
	String getAltura();
	String getDNI();
	String tipoPersona();
	String tipoServicio();
	String tipoPromo();
	
}
