package vista;

import java.awt.event.ActionListener;

import controlador.Controlador;

public interface IVista {

	void setActionListener(ActionListener controlador);

	void addActionListener(ActionListener controlador);
	
}
