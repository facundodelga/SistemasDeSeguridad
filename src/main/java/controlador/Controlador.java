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

import excepciones.DomicilioYaRegistradoException;
import excepciones.FacturaNoEncontradaException;
import excepciones.PersonaNoEncontradaException;
import excepciones.PersonaYaExisteException;
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

public class Controlador implements ActionListener, WindowListener {

	private Sistema sistema;
	private VistaSistemaDeSeguridad vistaPrincipal;
	private VistaNuevaContratacion vistaNuevaContratacion;
	private VistaContrataciones vistaContrataciones;
	private VistaAgregaPersona vistaAgregarPersonas;
	private AgregaDireccion vistaAgregaDireccion;
	private VistaFacturasPersona vistaFacturasPersona;
	private DefaultListModel<Persona> listaPersonas;
	private DefaultListModel<Factura> listaFacturas;
	private Persona persona;
	private ServicioTecnico st;
	

	public Controlador(ServicioTecnico st) {
		super();
		this.sistema = Sistema.getInstancia();
		this.cargarDatos();
		this.st = st;
		//iniciaSimulacion();
		this.listaPersonas = new DefaultListModel<Persona>();
		this.listaFacturas = new DefaultListModel<Factura>();
		this.st = st;
		this.sistema.setServicioTecnico(st);
		this.vistaPrincipal = new VistaSistemaDeSeguridad(this, st);
		this.vistaPrincipal.setActionListener(this);

		this.sistema.setControlador(this);

		// Ventanas secundarias
		this.vistaNuevaContratacion = new VistaNuevaContratacion(this);
		this.vistaAgregaDireccion = new AgregaDireccion(this);
		this.vistaAgregarPersonas = new VistaAgregaPersona(this);
		this.vistaContrataciones = new VistaContrataciones(this);
		this.vistaNuevaContratacion = new VistaNuevaContratacion(this);
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
		String comando = e.getActionCommand();

		System.out.println(comando);

		if (comando.equalsIgnoreCase("Siguiente Mes")) {
			// Llama a la funcion siguiente mes del sistema
			this.sistema.adelantarMes();
			this.vistaPrincipal.vaciarTextFields();
			// setea todos los textField en vacio menos el de simulacion de tecnicos
//			this.vista.
		} else if (comando.equalsIgnoreCase("Ejecutar")) {
			// Debe tomar lo que se ingreso por el textField_Accion y ejecutar la accion que
			// se requiera
			persona = vistaPrincipal.getPersona();
			System.out.println(persona);
			abrirVentanaAccion();

		} else if (comando.equalsIgnoreCase("Agregar Persona")) {
			// llama a la vista de agregar persona
			this.habilitaVentanaAgregaPersonas();
			this.inhabilitaVentanaPrincipal();
			
		} else if (comando.equalsIgnoreCase("Confirmar Persona")) {
			agregarPersona();
			
		} else if (comando.equalsIgnoreCase("Agrega tecnico")) {
			agregaTecnico();
			
		} else if (comando.equalsIgnoreCase("Inicia simulacion")) {
			sistema.iniciaSimulacion();

		} else if (comando.equalsIgnoreCase("Buscar Facturas")) {
			buscarFacturas();
		} else if (comando.equalsIgnoreCase("Pagar Factura")) {
			pagarFacturas();
			
		}else if(comando.equalsIgnoreCase("Confirmar Domicilio")) {
			agregarDomicilio();
		}
		

	}


	private void abrirVentanaAccion() {
		// TODO Auto-generated method stub
		String s = vistaPrincipal.getAccion();
		if(persona!=null) {
			if (s.equalsIgnoreCase("Gestionar Contratacion"))
				this.habilitaVentanaNuevaContratacion();
			else if (s.equalsIgnoreCase("Agregar Domicilio"))
				this.habilitaVentanaAgregaDireccion();
			else if (s.equalsIgnoreCase("Mostrar Factura")) {
				//prep para la ventana
				this.getFacturasImpagas();
				this.habilitaVentanaFacturasPersona();
			}
			this.inhabilitaVentanaPrincipal();
		}else
			this.informarVistaPrincipal("Debe seleccionar una persona de la lista");
	}
	
	//VISTAPRINCIPAL
	//mensajes
	void informarVistaPrincipal(String msg) {
		this.vistaPrincipal.informar(msg);
	}
	//apartado abonados
	public void agregarPersona() {
		try {
			sistema.crearPersona(this.vistaAgregarPersonas.getNombreApellido(), this.vistaAgregarPersonas.getDNI(),
					this.vistaAgregarPersonas.getTipoFactura());
			refreshPersonas();
		} catch (TipoDePersonaIncorrectoException e) {
			informarVistaPrincipal(e.getMessage());
		} catch (PersonaYaExisteException e) {
			informarVistaPrincipal(e.getMessage());
		}finally {
			inhabilitaVentanaAgregaPersonas();
			habilitaVentanaPrincipal();
			
		}
	}
	
	public void refreshPersonas() {
		ArrayList<Persona> personas = sistema.getPersonas();
//	    System.out.println(personas);
//		System.out.println("refrescando, listaModel");
		this.listaPersonas.clear();

		for (Persona persona : personas) {
//	    	System.out.println(persona);
			this.listaPersonas.addElement(persona);
		}
		System.out.println(listaPersonas);
		// this.vistaPrincipal.setListaPersonas(this.listaPersonas);
		// this.vistaPrincipal.setListaPersonas(personas);
	}

	public DefaultListModel<Persona> getListaPersonas() {
		System.out.println(listaPersonas);
		return listaPersonas;
	}
	
	//apartado facturas historicas
	private void buscarFacturas() {
		try {
			persona=this.vistaPrincipal.getPersona();
			ArrayList<Factura> facturas = sistema.buscarFacturaPorPersonaDNI(persona.getDni());
			this.listaFacturas.clear();
			for (Factura f : facturas) {
				this.listaFacturas.addElement(f);
				//cuando se creen las primeras facturas, probarlo
			}	
		} catch (PersonaNoEncontradaException | FacturaNoEncontradaException e) {
			this.informarVistaPrincipal(e.getMessage());
		}
	}
	
	public DefaultListModel<Factura> getListaFacturas() {
		System.out.println(listaFacturas);
		return listaFacturas;
	}




	
	//VENTANA AGREGARDOMICILIO
	public void agregarDomicilio() {
	//Necesitamos saber cual es la persona primero
	    try {
			sistema.asignarNuevoDomicilio(
					this.persona,
					sistema.crearDomicilio(vistaAgregaDireccion.getCalle(),Integer.parseInt(vistaAgregaDireccion.getAltura())));
		} catch (NumberFormatException | DomicilioYaRegistradoException | PersonaNoEncontradaException e) {
			informarVistaPrincipal(e.getMessage());
		}finally {
			inhabilitaVentanaAgregaDireccion();
			habilitaVentanaPrincipal();
		}
	}

	//VENTANA FACTURASPERSONA	
	//deberia mostrar solo las facturas sin pagar
	private void pagarFacturas() {

		System.out.println("Intento pagar\n");
		try {
			sistema.pagarFactura(
					persona.getDni(), 
					this.vistaFacturasPersona.getFactura().getNumFactura(), 
					this.vistaFacturasPersona.getMetodoPago()
					);
			System.out.println("Factura abonada\n"+this.vistaFacturasPersona.getFactura());
		} catch (FacturaNoEncontradaException | PersonaNoEncontradaException e) {
			this.informarVistaPrincipal(e.getMessage());
		}finally {
			this.inhabilitaVentanaFacturasPersona();
			this.habilitaVentanaPrincipal();
		}
	}
	
	public void getFacturasImpagas() {
		try {
			persona=this.vistaPrincipal.getPersona();
			ArrayList<Factura> facturas = sistema.buscarFacturaPorPersonaDNI(persona.getDni());
			this.listaFacturas.clear();
			for (Factura f : facturas) {
				if(f!=null && !f.isPagoRealizado() && !listaFacturas.contains(f))
					this.listaFacturas.addElement(f);
				//cuando se creen las primeras facturas, probarlo, solo facturas impagas
			}	
		} catch (PersonaNoEncontradaException | FacturaNoEncontradaException e) {
			this.informarVistaPrincipal(e.getMessage());
		}
	}
	
	//EVENTOS VENTANA
	public void windowClosing(WindowEvent e) {
		if (e.getWindow() == this.vistaPrincipal) {
			int i = JOptionPane.showConfirmDialog(null, "¿Desea finalizar la aplicación?");
			if (i == 0) {
				vistaPrincipal.setVisible(false);
				System.exit(0);
			}
		} else {
			int i = JOptionPane.showConfirmDialog(null, "¿Desea volver al menú principal?");
			if (i == 0) {
				vistaPrincipal.setVisible(true);
				e.getWindow().setVisible(false);
			}
		}
	}

	public void agregaTecnico() {
		//sistema.setServicioTecnico(st);//error this thread is not owner
		sistema.darAltaTecnico(vistaPrincipal.getNombreTecnico());

	}
	//falta
	public void iniciaSimulacion() {
		sistema.setServicioTecnico(st);
		//st iniciado en el constructor del controlador, no funciona
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
			p.abrirOutput("SistemasDeSeguridadDatos.bin");
			p.escribir(StaticsUtil.SistemaASistemaDTO(sistema));
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
			p.abrirInput("SistemasDeSeguridadDatos.bin");
			StaticsUtil.setEstaticosDelSistema((SistemaDTO) p.leer());
		} catch (IOException | ClassNotFoundException e) {
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
	// Metodos habilitadores de ventanas
		public void habilitaVentanaPrincipal() {
			this.vistaPrincipal.setVisible(true);
		}
		public void habilitaVentanaAgregaPersonas() {
			this.vistaAgregarPersonas.setVisible(true);
		}
		public void habilitaVentanaAgregaDireccion() {
			this.vistaAgregaDireccion.setVisible(true);
		}
		public void habilitaVentanaNuevaContratacion() {
			this.vistaNuevaContratacion.setVisible(true);
		}
		public void habilitaVentanaFacturasPersona() {
			this.vistaFacturasPersona.setVisible(true);
		}
		//Metodos inhabilitadores de ventanas
		public void inhabilitaVentanaPrincipal() {
			this.vistaPrincipal.setVisible(false);
		}
		public void inhabilitaVentanaAgregaPersonas() {
			this.vistaAgregarPersonas.setVisible(false);
		}
		public void inhabilitaVentanaAgregaDireccion() {
			this.vistaAgregaDireccion.setVisible(false);
		}
		public void inhabilitaVentanaNuevaContratacion() {
			this.vistaNuevaContratacion.setVisible(false);
		}
		public void inhabilitaVentanaFacturasPersona() {
			this.vistaFacturasPersona.setVisible(false);
		}
}
