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
		this.cargarDatos();	
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

		//System.out.println(comando);
		if (comando.equalsIgnoreCase("Siguiente Mes")) {
			this.sistema.adelantarMes();
			refreshFacturas();
			this.vistaPrincipal.vaciarTextFields();
			this.sistema.reiniciarSimulacion();

		} else if (comando.equalsIgnoreCase("Ejecucion")) {
			persona = vistaPrincipal.getPersona();
			//System.out.println(persona);
			abrirVentanaAccion();

		} else if (comando.equalsIgnoreCase("Agregar Persona")) {
			this.habilitaVentanaAgregaPersonas();
			this.inhabilitaVentanaPrincipal();

		} else if (comando.equalsIgnoreCase("Confirmar Persona")) {
			agregarPersona();

		} else if (comando.equalsIgnoreCase("Confirmar Domicilio")) {
			agregarDomicilio();
		
		} else if (comando.equalsIgnoreCase("Agrega tecnico")) {
			agregaTecnico();
		} else if (comando.equalsIgnoreCase("Agrega adicional")) {
			vistaNuevaContratacion.agregarAdicional();
		} else if (comando.equalsIgnoreCase("Reset adicionales")) {
			vistaNuevaContratacion.resetAdicionales();
		} else if (comando.equalsIgnoreCase("Inicia simulacion")) {
			sistema.iniciaSimulacion();
		
		} else if (comando.equalsIgnoreCase("Buscar Facturas")) {
			buscarFacturas();
		
		} else if (comando.equalsIgnoreCase("Pagar factura")) {
			pagarFacturas();
		
		} else if (comando.equalsIgnoreCase("Mostrar detalle")) {
			detalleFactura();

		}else if(comando.equalsIgnoreCase("Confirmar Contratacion")) {
			agregarContratacion();
		
		}else if(comando.equalsIgnoreCase("Nueva Contratacion")) {
			this.habilitaVentanaNuevaContratacion();
			this.inhabilitaVentanaContrataciones();
		
		}else if(comando.equalsIgnoreCase("Eliminar Contratacion")) {
			eliminarContratacion();
		}
	

	}

	private void abrirVentanaAccion() {
		// TODO Auto-generated method stub
		String s = vistaPrincipal.getAccion();
		if(persona!=null) {
			if (s.equalsIgnoreCase("Ver Contrataciones"))
				this.habilitaVentanaContrataciones();

			else if (s.equalsIgnoreCase("Agregar Domicilio")) {
				this.getDomiciliosActuales();
				this.habilitaVentanaAgregaDireccion();
			}else if (s.equalsIgnoreCase("Mostrar Factura")) {
				// prep para la ventana
				this.getFacturasImpagas();
				this.habilitaVentanaFacturasPersona();
			}
			this.inhabilitaVentanaPrincipal();
		} else
			this.informarVistaPrincipal("Debe seleccionar una persona de la lista");
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

	//RECARGA DE LAS LISTAS
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
				//System.out.println("refrescando, listaModel");
				listaPersonas.clear();

				for (Persona persona : personas) {
		 	    	listaPersonas.addElement(persona);
				}
			//	System.out.println(listaPersonas);
			}
		};

		SwingUtilities.invokeLater(updateGUIAsincrono);
	}

	public DefaultListModel<Persona> getListaPersonas() {
		System.out.println(listaPersonas);
		return listaPersonas;
	}

	public void refreshDomicilios() {
		Runnable updateGUIAsincrono = new Runnable() {
			public void run() {
				ArrayList<Domicilio> domicilios = persona.getDomicilios();
				//System.out.println("refrescando Domicilios, ComboBoxModel");
				listaDomicilios.removeAllElements();

				for (Domicilio domicilio : domicilios) {
					listaDomicilios.addElement(domicilio);
				}
			}
		};
		SwingUtilities.invokeLater(updateGUIAsincrono);
	}
	/**
	 * @return the listaDomicilios
	 */
	public DefaultComboBoxModel<Domicilio> getListaDomicilios() {
		return listaDomicilios;
	}
	
	public void refreshFacturas() {
		Runnable updateGUIAsincrono = new Runnable() {
			public void run() {
				ArrayList<Factura> facturas = sistema.getFacturas();
				//System.out.println("refrescando facturas, listaModel");
				listaFacturas.clear();

				for (Factura factura : facturas) {
		 	    	listaFacturas.addElement(factura);
				}
			}
		};

		SwingUtilities.invokeLater(updateGUIAsincrono);
	}
	
	public DefaultListModel<Factura> getListaFacturas() {
		System.out.println(listaFacturas);
		return listaFacturas;
	}
	
	public void refreshContrataciones() {
		/*
		 * Teniamos un bug, que a veces al abrir la ventana el JList no se pintaba, solo
		 * despues de agregar una persona nueva. Googleando, parece que hay que updatear
		 * el model con un runnable y usando SwingUtilities.invokeLater para que no haya
		 * problemas
		 */
		Runnable updateGUIAsincrono = new Runnable() {
			public void run() {
				if(persona!=null) {
					ArrayList<Contratacion> contrataciones = persona.getContrataciones();
					//System.out.println("refrescando contrataciones, listaModel");
					listaContrataciones.clear();
	
					for (Contratacion contratacion : contrataciones) {
			 	    	listaContrataciones.addElement(contratacion);
					}
				}
			}
		};
		SwingUtilities.invokeLater(updateGUIAsincrono);
	}
	
	public DefaultListModel<Contratacion> getListaContrataciones() {
		return listaContrataciones;
	}

	// apartado facturas historicas
	private void buscarFacturas() {
			persona = this.vistaPrincipal.getPersonaHistorica();
			ArrayList<Factura> facturas = sistema.getFacturas();
			String fHistoricas="";
			int idAnt=-1;
			for (Factura f : facturas) {
				if(f!=null && f.getPersona()==persona && f.getNumFactura()!=idAnt) {
					fHistoricas += f.detalle()+"\n";
					idAnt=f.getNumFactura();
					//fHistoricas += f.toString()+"\n";
				}
		}
		this.vistaPrincipal.setFacturasHistoricas(fHistoricas);
	}

	//VENTANA AGREGARDOMICILIO

	public void agregarDomicilio() {
		// Necesitamos saber cual es la persona primero
		try {
			Domicilio d = sistema.crearDomicilio(vistaAgregaDireccion.getCalle(),
					Integer.parseInt(vistaAgregaDireccion.getAltura()));
			sistema.asignarNuevoDomicilio(this.persona, d);
		} catch (NumberFormatException | DomicilioYaRegistradoException | PersonaNoEncontradaException e) {
			informarVistaPrincipal(e.getMessage());
		} finally {
			inhabilitaVentanaAgregaDireccion();
			habilitaVentanaPrincipal();
		}
		refreshDomicilios();
		
	}
	
	private void getDomiciliosActuales() {
		persona = this.vistaPrincipal.getPersona();
		ArrayList<Domicilio> domicilios = persona.getDomicilios();
		this.listaDomicilios.removeAllElements();
		for (Domicilio d : domicilios) {
			if(d!=null) {
				this.listaDomicilios.addElement(d);
				System.out.println(d.toString());
			}
		}
		this.vistaAgregaDireccion.actualizarTextArea(getDomiciliosText());
	}
	
	public String getDomiciliosText() {
		String res="";
		persona = this.vistaPrincipal.getPersona();
		if(persona!=null) {
			ArrayList<Domicilio> domicilios = persona.getDomicilios();
			for (Domicilio domicilio : domicilios) {
				if(domicilio!=null)
					res += domicilio.toString()+"\n";
			}
		}
		return res;
	}

	// VENTANA FACTURASPERSONA
	// deberia mostrar solo las facturas sin pagar
	private void pagarFacturas() {
		try {
			String mp = this.vistaFacturasPersona.getMetodoPago();
			Factura f = this.vistaFacturasPersona.getFactura();
				sistema.pagarFactura(persona.getDni(), 
						f.getNumFactura(),
						mp);
				/*System.out.println(persona.getDni()+"\n"+ 
						f.getNumFactura()+"\n"+
						mp+"\n"+
						f.isPagoRealizado());*/
				//System.out.println("Factura abonada\n" + this.vistaFacturasPersona.getFactura());
				this.vistaFacturasPersona.mostrarDetalle(this.vistaFacturasPersona.getFactura().detalle(mp));
		} catch (FacturaNoEncontradaException | PersonaNoEncontradaException e) {
			this.informarVistaPrincipal(e.getMessage());
			this.inhabilitaVentanaFacturasPersona();
			this.habilitaVentanaPrincipal();
		}
	}
	private void detalleFactura() {
		this.vistaFacturasPersona.mostrarDetalle(this.vistaFacturasPersona.getFactura().detalle());
	}

	public void getFacturasImpagas() {
		//try {
			persona = this.vistaPrincipal.getPersona();
			ArrayList<Factura> facturas = sistema.getFacturas();//sistema.buscarFacturaPorPersonaDNI(persona.getDni());
			this.listaFacturas.clear();
			for (Factura f : facturas) {
				if (f != null && f.getPersona()==persona && !f.isPagoRealizado() && !listaFacturas.contains(f))
					this.listaFacturas.addElement(f);
			}
		/*} catch (PersonaNoEncontradaException | FacturaNoEncontradaException e) {
			this.informarVistaPrincipal(e.getMessage());
		}*/
	}
	
	//VENTANA NUEVACONTRATACION
	private void agregarContratacion() {
		
		//puede que tenga que hacer conversiones
		Domicilio d = this.vistaNuevaContratacion.getDireccion();
		iPromocion p;
			try {
				//getPromo
				p = sistema.obtenerPromocion(this.vistaNuevaContratacion.getPromo());
				//getServicio
				iServicio s = sistema.obtenerServicio(this.vistaNuevaContratacion.getServicio());
				//Crear contratacion
				Contratacion c = sistema.crearContratacion(persona, d, s, p);
				//Agregar adicionales
				for (int i = 0; i < vistaNuevaContratacion.getCantBotones(); i++) {
					iContratable a = sistema.obtenerContratable("BOTON");
					sistema.contratarAdicional(c, a);
				}
				for (int i = 0; i < vistaNuevaContratacion.getCantCamaras(); i++) {
					iContratable a = sistema.obtenerContratable("CAMARA");
					sistema.contratarAdicional(c, a);
				}
				for (int i = 0; i < vistaNuevaContratacion.getCantMoviles(); i++) {
					iContratable a = sistema.obtenerContratable("MOVIL");
					sistema.contratarAdicional(c, a);
				}
				refreshContrataciones();
			} catch (DomicilioYaRegistradoException | DomicilioNoEncontradoException | ContratacionYaRegistradaException
					| PersonaNoEncontradaException | TipoDePromocionIncorrectoException | TipoDeServicioIncorrectoException | TipoDeContratableIncorrectoException e) {
				this.informarVistaPrincipal(e.getMessage());
				this.inhabilitaVentanaNuevaContratacion();
				this.habilitaVentanaPrincipal();
			}
	}
	
	//VENTANA CONTRATACIONES
	private void eliminarContratacion() {
		try {
			if(this.vistaContrataciones.getContratacion()!=null) {
				sistema.eliminarContratacion(persona, this.vistaContrataciones.getContratacion().getDomicilio());
				refreshContrataciones();
			}
		} catch (PersonaNoEncontradaException | DomicilioNoEncontradoException | FacturaNoEncontradaException e) {
			this.informarVistaPrincipal(e.getMessage());
		}
		
	}	
	
	//EVENTOS VENTANA

	public void windowClosing(WindowEvent e) {
		if (e.getWindow() == this.vistaPrincipal) {
			int i = JOptionPane.showConfirmDialog(null, "¿Desea finalizar la aplicación?");
			if (i == 0) {
			    	sistema.pararSimulacion();
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
		sistema.darAltaTecnico(vistaPrincipal.getNombreTecnico());
	}


	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {
//		vistaPrincipal.setVisible(true);
	}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {	}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {	}

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
		refreshContrataciones();
		this.vistaContrataciones.setVisible(true);
	}
	public void habilitaVentanaNuevaContratacion() {
		refreshDomicilios();
		this.vistaNuevaContratacion.setTituloLabel(persona.getNombre());
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
		this.vistaAgregaDireccion.vaciarTextArea();
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

	public Persona getPersona() {
		return persona;
	}
	public String getNombre() {
		String res="";
		if(persona!=null)
			res = persona.getNombre();
		return res;
	}

	
}
