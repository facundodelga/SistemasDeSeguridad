package controlador;

import java.awt.event.ActionEvent;
import vista.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

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
import persistencia.*;
import persona.Persona;

public class Controlador implements ActionListener,WindowListener{

	private Sistema sistema;
	private VistaSistemaDeSeguridad vistaPrincipal;
	private VistaNuevaContratacion vistaNuevaContratacion;
	private VistaContrataciones vistaContrataciones;
	private VistaAgregaPersona vistaAgregarPersonas;
	private AgregaDireccion vistaAgregaDireccion;
	private VistaFacturasPersona vistaFacturasPersona;
	private DefaultListModel<Persona> listaPersonas;
	
	
	public Controlador(ServicioTecnico st) {
		super();
		this.listaPersonas = new DefaultListModel<Persona>();
		
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
		
		
		
		vistaPrincipal.addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	                guardarDatos();
	                super.windowClosing(e);
	            }
	        });
		
		cargarDatos();
		
		
		
		refreshPersonas();
		
		vistaPrincipal.addWindowListener(this);
		vistaPrincipal.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		
		System.out.println(comando);
		
		if(comando.equalsIgnoreCase("Siguiente Mes")){
			//Llama a la funcion siguiente mes del sistema
			this.sistema.adelantarMes();
			//setea todos los textField en vacio menos el de simulacion de tecnicos
//			this.vista.
		}
		else if(comando.equalsIgnoreCase("Ejecutar")){
			//Debe tomar lo que se ingreso por el textField_Accion y ejecutar la accion que se requiera
		    	abrirVentanaAccion();
		 
		}
		else if(comando.equalsIgnoreCase("Agregar Persona")){
			//llama a la vista de agregar persona
		    vistaAgregarPersonas.setVisible(true);
		    vistaPrincipal.setVisible(false);
			//toma los valores ingresados por los text field
			
			//ingresa la peersona al sistema con el metodo correspondiente
		}
		else if(comando.equalsIgnoreCase("Confirmar Persona")){
			agregarPersona();
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
	
	public void refreshPersonas() {
	    ArrayList<Persona> personas = sistema.getPersonas();
//	    System.out.println(personas);
	    System.out.println("refrescando, listaModel");
	    for (Persona persona : personas) {
//	    	System.out.println(persona);
	    	this.listaPersonas.addElement(persona);
	    }
	    System.out.println(listaPersonas);
	    //this.vistaPrincipal.setListaPersonas(this.listaPersonas);
	   // this.vistaPrincipal.setListaPersonas(personas);
	}
	
	
	public DefaultListModel<Persona> getListaPersonas() {
	    return listaPersonas;
	}
	
	


	//Metodos habilitadores de ventanas
	public void habilitaVentanaPrincipal() {
		this.vistaPrincipal.setVisible(true);
	}
	
	private void abrirVentanaAccion() {
	    // TODO Auto-generated method stub
	   String s = vistaPrincipal.getAccion();

	   if(s.equalsIgnoreCase( "Gestionar Contratacion"))
	       this.vistaNuevaContratacion.setVisible(true);
	   
	   else if(s.equalsIgnoreCase( "Agregar Domicilio")) 
	       this.vistaAgregaDireccion.setVisible(true);
	   
	   else if(s.equalsIgnoreCase( "Mostrar Factura")) {
	       this.vistaFacturasPersona.setVisible(true);
	   }
	   
	   this.vistaPrincipal.setVisible(false);
	}
	
	public void agregarPersona() {
	    try {
	    	sistema.crearPersona(this.vistaAgregarPersonas.getNombreApellido(),this.vistaAgregarPersonas.getDNI(),this.vistaAgregarPersonas.getTipoFactura());
	    	refreshPersonas();
	    } catch (TipoDePersonaIncorrectoException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
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
	
	public void guardarDatos() {
	    PersistenciaBin p = new PersistenciaBin();
	    
	    try {
		p.abrirOutput("Guadado.bin");
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    
	    try {
		p.cerrarOutput();
		
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	   
	}
	
	public void cargarDatos() {
	    PersistenciaBin p = new PersistenciaBin();
	    
	    try {
		p.abrirInput("Guadado.bin");
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    
	    try {
		p.cerrarInput();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    
	    
	}
	
//	public void agregaDomicilio() {
//	//Necesitamos saber cual es la persona primero
//	    sistema.asignarNuevoDomicilio(null,
//		    sistema.crearDomicilio(vistaDireccion.getCalle(),Integer.parseInt(vistaDireccion.getAltura())
//		    );
//	}
//	
	
}
