package controlador;

import java.awt.event.ActionEvent;
import vista.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import excepciones.FacturaNoEncontradaException;
import excepciones.PersonaNoEncontradaException;
import excepciones.TipoDePersonaIncorrectoException;
import modelo.Factura;
import modelo.I_Sistema;
import modelo.Sistema;
import simulacion.ServicioTecnico;
import vista.IVista;
import vista.VistaNuevaContratacion;
import vista.VistaSistemaDeSeguridad;
import vista.VistaContrataciones;
import vista.AgregaDireccion;
import vista.VistaAgregaPersona;

public class Controlador implements ActionListener,WindowListener{

	private Sistema sistema;
	private VistaSistemaDeSeguridad vistaPrincipal;
	private VistaNuevaContratacion vistaNuevaContratacion;
	private VistaContrataciones vistaContrataciones;
	private VistaAgregaPersona vistaAgregarPersonas;
	private AgregaDireccion vistaAgregaDireccion;
	private VistaFacturasPersona vistaFacturasPersona;
	
	
	public Controlador(ServicioTecnico st) {
		super();
		this.vistaPrincipal =new VistaSistemaDeSeguridad(this,st);
		this.vistaPrincipal.setActionListener(this);
		this.sistema = Sistema.getInstancia();
		this.sistema.setControlador(this);
		
		//Ventanas secundarias
		this.vistaNuevaContratacion=new VistaNuevaContratacion(this);
		this.vistaAgregaDireccion=new AgregaDireccion(this);
		this.vistaAgregarPersonas=new VistaAgregaPersona(this);
		this.vistaContrataciones=new VistaContrataciones(this);
		this.vistaNuevaContratacion= new VistaNuevaContratacion(this);
		this.vistaFacturasPersona = new VistaFacturasPersona(this);
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
		    	abrirVentana();
		 
		}
		else if(comando.equalsIgnoreCase("Agregar Persona")){
			//llama a la vista de agregar persona
		    System.out.println("ejecutar");
		    vistaAgregarPersonas.setVisible(true);
		    vistaPrincipal.setVisible(false);
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
	
	//Metodos habilitadores de ventanas
	public void habilitaVentanaPrincipal() {
		this.vistaPrincipal.setVisible(true);
	}
	
	private void abrirVentana() {
	    // TODO Auto-generated method stub
	   String s = vistaPrincipal.getAccion();

	   if(s.equalsIgnoreCase( "Gestionar Contratacion"))
	       this.vistaNuevaContratacion.setVisible(true);
	   
	   else if(s.equalsIgnoreCase( "Agregar Domicilio")) 
	       this.vistaAgregaDireccion.setVisible(true);
	   
	   else if(s.equalsIgnoreCase( "Pagar Factura")) {
	       this.vistaFacturasPersona.setVisible(true);
	   }
	   else {
		   return;
	   }
	   
	   this.vistaPrincipal.setVisible(false);
	}
	
	public void windowClosing(WindowEvent e){
            int i=JOptionPane.showConfirmDialog(null, "¿Desea volver al menú principal?");
            if(i==0) {
            	vistaPrincipal.setVisible(true);
            	e.getWindow().setVisible(false);
            }
        }
	
	public void agregaTecnico() {

		sistema.darAltaTecnico(vistaPrincipal.getNombreTecnico());
		
	}
	
	
	
	public void iniciaSimulacion() {
	    
	}
	
	public void agregaPersona() {
	    try {
		//cerrar ventana despues de agregar persona
		sistema.crearPersona(
			vistaAgregarPersonas.getNombreApellido(), 
			vistaAgregarPersonas.getDNI(),
			vistaAgregarPersonas.getTipoFactura()
			);
	    } catch (TipoDePersonaIncorrectoException e) {
		//mostrar problema por pantalla
		
	    }
	}


	@Override
	public void windowOpened(WindowEvent e) {
	    // TODO Auto-generated method stub
	    
	}


	@Override
	public void windowClosed(WindowEvent e) {
	    // TODO Auto-generated method stub
	    vistaPrincipal.setVisible(true);
	}


	@Override
	public void windowIconified(WindowEvent e) {
	    // TODO Auto-generated method stub
	    
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
	    // TODO Auto-generated method stub
	    
	}


	@Override
	public void windowActivated(WindowEvent e) {
	    // TODO Auto-generated method stub
	    
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
	    // TODO Auto-generated method stub
	    
	}
	
//	public void agregaDomicilio() {
//	//Necesitamos saber cual es la persona primero
//	    sistema.asignarNuevoDomicilio(null,
//		    sistema.crearDomicilio(vistaDireccion.getCalle(),Integer.parseInt(vistaDireccion.getAltura())
//		    );
//	}
//	
	
}
