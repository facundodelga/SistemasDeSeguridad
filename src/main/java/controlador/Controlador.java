package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import excepciones.FacturaNoEncontradaException;
import excepciones.PersonaNoEncontradaException;
import modelo.Factura;
import modelo.I_Sistema;
import modelo.Sistema;
import vista.IVista;
import vista.VistaSistemaDeSeguridad;


public class Controlador implements ActionListener{

	private Sistema sistema;
	private IVista vista;
	public Controlador() {
		this.vista = new VistaSistemaDeSeguridad();
		this.vista.setActionListener(this);
		this.sistema = Sistema.getInstancia();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		if(comando.equalsIgnoreCase("Siguiente Mes")){
			//Llama a la funcion siguiente mes del sistema
			this.sistema.adelantarMes();
			//setea todos los textField en vacio menos el de simulacion de tecnicos
//			this.vista.
		}
		else if(comando.equalsIgnoreCase("Ejecutar")){
			//Debe tomar lo que se ingreso por el textField_Accion y ejecutar la accion que se requiera
		}
		else if(comando.equalsIgnoreCase("Agregar Persona")){
			//llama a la vista de agregar persona
			
			//toma los valores ingresados por los text field
			
			//ingresa la peersona al sistema con el metodo correspondiente
		}
		else if(comando.equalsIgnoreCase("Agrega tecnico")){
			//llama a la funcion agrega tecnico
			agregaTecnico();
		}
		else if(comando.equalsIgnoreCase("Inicia simulacion")){
			//llama a la funcion Inicia simulacion;
		}
		else if(comando.equalsIgnoreCase("Buscar Facturas")){} 
	
	}
	
	public void agregaTecnico() {
		//toma el valor del textField_AgregaTecnico
		
		//llama a la funcion en sistema agregaTecnico
	}
}
