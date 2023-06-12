package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import contrataciones.Contratacion;
import contrataciones.iContratable;
import contrataciones.iServicio;
import excepciones.ContratacionYaRegistradaException;
import excepciones.DomicilioNoEncontradoException;
import excepciones.DomicilioYaRegistradoException;
import excepciones.FacturaNoEncontradaException;
import excepciones.PersonaNoEncontradaException;
import excepciones.PersonaYaExisteException;
import excepciones.TipoDeContratableIncorrectoException;
import excepciones.TipoDePersonaIncorrectoException;
import excepciones.TipoDePromocionIncorrectoException;
import excepciones.TipoDeServicioIncorrectoException;
import modelo.Factura;
import modelo.Sistema;
import persistencia.PersistenciaBin;
import persistencia.SistemaDTO;
import persistencia.StaticsUtil;
import persona.Domicilio;
import persona.Persona;
import simulacion.ServicioTecnico;
import vista.AgregaDireccion;
import vista.VistaAgregaPersona;
import vista.VistaContrataciones;
import vista.VistaFacturasPersona;
import vista.VistaNuevaContratacion;
import vista.VistaSistemaDeSeguridad;
import persistencia.*;
import persona.Domicilio;
import persona.Persona;
import promociones.iPromocion;

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
	private DefaultComboBoxModel<Domicilio> listaDomicilios;
	private DefaultListModel<Contratacion> listaContrataciones;
	private Persona persona;

	public Controlador() {
		super();
		this.sistema = Sistema.getInstancia();
		//this.cargarDatos();	
		//iniciaSimulacion();
		this.listaPersonas = new DefaultListModel<Persona>();
		this.listaFacturas = new DefaultListModel<Factura>();
		this.listaDomicilios = new DefaultComboBoxModel<Domicilio>();
		this.listaContrataciones = new DefaultListModel<Contratacion>();

		this.vistaPrincipal = new VistaSistemaDeSeguridad(this,sistema.getServicioTecnico());

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


		refreshPersonas();
		vistaPrincipal.addWindowListener(this);
//		vistaPrincipal.addActionListener(this); //Estaba de mas, los eventos se ejecutaban dos veces
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		System.out.println(comando);

		if (comando.equalsIgnoreCase("Siguiente Mes")) {
			// Llama a la funcion siguiente mes del sistema
			this.sistema.adelantarMes();
			this.vistaPrincipal.vaciarTextFields();
			this.sistema.reiniciarSimulacion();
			// setea todos los textField en vacio menos el de simulacion de tecnicos
//			this.vista.
		} else if (comando.equalsIgnoreCase("Ejecucion")) {
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
		} else if (comando.equalsIgnoreCase("Confirmar Domicilio")) {
			agregarDomicilio();
		} else if (comando.equalsIgnoreCase("Agrega tecnico")) {
			agregaTecnico();

		} else if (comando.equalsIgnoreCase("Inicia simulacion")) {

			sistema.iniciaSimulacion();
		} else if (comando.equalsIgnoreCase("Buscar Facturas")) {
			buscarFacturas();
		} else if (comando.equalsIgnoreCase("Pagar Factura")) {
			pagarFacturas();

		} else if (comando.equalsIgnoreCase("Confirmar Domicilio")) {
			agregarDomicilio();
		
		}else if(comando.equalsIgnoreCase("Confirmar Contratación")) {
			agregarContratacion();
		
		}else if(comando.equalsIgnoreCase("Nueva Contratación")) {
			this.habilitaVentanaContrataciones();
			this.inhabilitaVentanaPrincipal();
		
		}else if(comando.equalsIgnoreCase("Eliminar Contratación")) {
			eliminarContratacion();
		}
	

	}

	private void abrirVentanaAccion() {
		// TODO Auto-generated method stub
		String s = vistaPrincipal.getAccion();
		if (persona != null) {
			if (s.equalsIgnoreCase("Crear Contratación"))
				this.habilitaVentanaNuevaContratacion();

		if(persona!=null) {
			if (s.equalsIgnoreCase("Gestionar Contratacion"))
				//this.habilitaVentanaNuevaContratacion();
				this.habilitaVentanaContrataciones();

			else if (s.equalsIgnoreCase("Agregar Domicilio"))
				this.habilitaVentanaAgregaDireccion();
			else if (s.equalsIgnoreCase("Mostrar Factura")) {
				// prep para la ventana
				this.getFacturasImpagas();
				this.habilitaVentanaFacturasPersona();
			}
			this.inhabilitaVentanaPrincipal();
		} else
			this.informarVistaPrincipal("Debe seleccionar una persona de la lista");
		}
	}

	// VISTAPRINCIPAL
	// mensajes
	void informarVistaPrincipal(String msg) {
		this.vistaPrincipal.informar(msg);
	}

	// apartado abonados
	public void agregarPersona() {
		try {
			sistema.crearPersona(this.vistaAgregarPersonas.getNombreApellido(), this.vistaAgregarPersonas.getDNI(),
					this.vistaAgregarPersonas.getTipoFactura());
			refreshPersonas();
		} catch (TipoDePersonaIncorrectoException e) {
			informarVistaPrincipal(e.getMessage());
		} catch (PersonaYaExisteException e) {
			informarVistaPrincipal(e.getMessage());
		} finally {
			inhabilitaVentanaAgregaPersonas();
			habilitaVentanaPrincipal();

		}
	}

	public void refreshPersonas() {

		/*
		 * Teniamos un bug, que a veces al abrir la ventana el JList no se pintaba, solo
		 * despues de agregar una persona nueva. Googleando, parece que hay que updatear
		 * el model con un runnable y usando SwingUtilities.invokeLater para que no haya
		 * problemas
		 */
		Runnable updateGUIAsincrono = new Runnable() {
			public void run() {
				ArrayList<Persona> personas = sistema.getPersonas();
//		 	    System.out.println(personas);
				System.out.println("refrescando, listaModel");

				listaPersonas.clear();

				for (Persona persona : personas) {
//		 	    	System.out.println(persona);
					listaPersonas.addElement(persona);
				}
				System.out.println(listaPersonas);
				// this.vistaPrincipal.setListaPersonas(this.listaPersonas);
				// this.vistaPrincipal.setListaPersonas(personas);
			}
		};

		SwingUtilities.invokeLater(updateGUIAsincrono);

	}

	public void refreshDomicilios() {
		assert persona != null;

		Runnable updateGUIAsincrono = new Runnable() {
			public void run() {
				ArrayList<Domicilio> domicilios = persona.getDomicilios();
//		 	    System.out.println(personas);
//				System.out.println("refrescando, listaModel");

				listaDomicilios.removeAllElements();

				for (Domicilio domicilio : domicilios) {
//		 	    	System.out.println(persona);
					listaDomicilios.addElement(domicilio);
				}
				System.out.println(listaPersonas);
				// this.vistaPrincipal.setListaPersonas(this.listaPersonas);
				// this.vistaPrincipal.setListaPersonas(personas);
			}
		};

		SwingUtilities.invokeLater(updateGUIAsincrono);

	}

	public DefaultListModel<Persona> getListaPersonas() {
		System.out.println(listaPersonas);
		return listaPersonas;
	}

	/**
	 * @return the listaDomicilios
	 */
	public DefaultComboBoxModel<Domicilio> getListaDomicilios() {
		return listaDomicilios;
	}
	
	public DefaultListModel<Contratacion> getListaContrataciones() {
		return listaContrataciones;
	}

	// apartado facturas historicas
	private void buscarFacturas() {
		try {
			persona = this.vistaPrincipal.getPersona();
			ArrayList<Factura> facturas = sistema.buscarFacturaPorPersonaDNI(persona.getDni());
			this.listaFacturas.clear();
			for (Factura f : facturas) {
				this.listaFacturas.addElement(f);
				// cuando se creen las primeras facturas, probarlo
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
		// Necesitamos saber cual es la persona primero
		try {
			sistema.asignarNuevoDomicilio(this.persona, sistema.crearDomicilio(vistaAgregaDireccion.getCalle(),
					Integer.parseInt(vistaAgregaDireccion.getAltura())));
		} catch (NumberFormatException | DomicilioYaRegistradoException | PersonaNoEncontradaException e) {
			informarVistaPrincipal(e.getMessage());
		} finally {
			inhabilitaVentanaAgregaDireccion();
			habilitaVentanaPrincipal();
		}
	}

	// VENTANA FACTURASPERSONA
	// deberia mostrar solo las facturas sin pagar
	private void pagarFacturas() {

		System.out.println("Intento pagar\n");
		try {
			sistema.pagarFactura(persona.getDni(), this.vistaFacturasPersona.getFactura().getNumFactura(),
					this.vistaFacturasPersona.getMetodoPago());
			System.out.println("Factura abonada\n" + this.vistaFacturasPersona.getFactura());
		} catch (FacturaNoEncontradaException | PersonaNoEncontradaException e) {
			this.informarVistaPrincipal(e.getMessage());
			this.inhabilitaVentanaFacturasPersona();
			this.habilitaVentanaPrincipal();
		}
	}

	public void getFacturasImpagas() {
		try {
			persona = this.vistaPrincipal.getPersona();
			ArrayList<Factura> facturas = sistema.buscarFacturaPorPersonaDNI(persona.getDni());
			this.listaFacturas.clear();
			for (Factura f : facturas) {
				if (f != null && !f.isPagoRealizado() && !listaFacturas.contains(f))
					this.listaFacturas.addElement(f);
				// cuando se creen las primeras facturas, probarlo, solo facturas impagas
			}
		} catch (PersonaNoEncontradaException | FacturaNoEncontradaException e) {
			this.informarVistaPrincipal(e.getMessage());
		}
	}
	
	//VENTANA NUEVACONTRATACION
	private void agregarContratacion() {
		
		//puede que tenga que hacer conversiones
		Domicilio d = this.vistaNuevaContratacion.getDireccion();
		iPromocion p;
			try {
				p = sistema.obtenerPromocion(this.vistaNuevaContratacion.getPromo());
				iServicio s = sistema.obtenerServicio(this.vistaNuevaContratacion.getServicio());
				Contratacion c = sistema.crearContratacion(persona, d, s, p);
				iContratable a = sistema.obtenerContratable(this.vistaNuevaContratacion.getAdicional());
				sistema.contratarAdicional(c, a);
				/*	ArrayList<iContratable> adicionales = this.vistaNuevaContratacion.getAdicionales();
				for (iContratable a : adicionales) {
					sistema.contratarAdicional(c, a);
				}
			*/
			
			} catch (DomicilioYaRegistradoException | DomicilioNoEncontradoException | ContratacionYaRegistradaException
					| PersonaNoEncontradaException | TipoDePromocionIncorrectoException | TipoDeServicioIncorrectoException | TipoDeContratableIncorrectoException e) {
				this.informarVistaPrincipal(e.getMessage());
			}
	}
	
	//VENTANA CONTRATACIONES
	private void eliminarContratacion() {
		try {
			sistema.eliminarContratacion(persona, this.vistaContrataciones.getContratacion().getDomicilio());
		} catch (PersonaNoEncontradaException | DomicilioNoEncontradoException | FacturaNoEncontradaException e) {
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
		// sistema.setServicioTecnico(st);//error this thread is not owner
		sistema.darAltaTecnico(vistaPrincipal.getNombreTecnico());

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
		    	//sistema.pararSimulacion();
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
	public void habilitaVentanaContrataciones() {
		this.vistaContrataciones.setVisible(true);
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
	public void inhabilitaVentanaContrataciones() {
		this.vistaContrataciones.setVisible(false);
	}
	public void inhabilitaVentanaNuevaContratacion() {
		this.vistaNuevaContratacion.setVisible(false);
	}
	public void inhabilitaVentanaFacturasPersona() {
		this.vistaFacturasPersona.setVisible(false);
	}

}
