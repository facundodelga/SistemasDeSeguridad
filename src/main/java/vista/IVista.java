package vista;

import controlador.Controlador;

public interface IVista {

	void setActionListener(Controlador controlador);
	String getNombre(); //en la vista tecnicos, va a devolver el del tecnico, en las otras devuelve el nombre del abonado
	String getCalle();
	String getAltura();
	String getDNI();
	String tipoPersona();
	String tipoServicio();
	String tipoPromo();
	
}
