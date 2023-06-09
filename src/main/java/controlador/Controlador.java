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
	}
	/**
	 * Ejecuta las acciones correspondientes cuando se produce un evento de acción en las vistas del programa.
	 *
	 * @param e El evento de acción generado.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equalsIgnoreCase("Siguiente Mes")) {
			this.sistema.adelantarMes();
			refreshFacturas();
			this.vistaPrincipal.vaciarTextFields();
			this.sistema.reiniciarSimulacion();

		} else if (comando.equalsIgnoreCase("Ejecucion")) {
			persona = vistaPrincipal.getPersona();
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
	/**
	 * Abre la ventana correspondiente en función de la acción seleccionada en la vista principal.
	 * Realiza diferentes comprobaciones y habilita/inhabilita ventanas según las condiciones.
	 */
	private void abrirVentanaAccion() {
		String s = vistaPrincipal.getAccion();
		if(persona!=null) {
			if (s.equalsIgnoreCase("Ver Contrataciones"))
				this.habilitaVentanaContrataciones();

			else if (s.equalsIgnoreCase("Agregar Domicilio")) {
				this.getDomiciliosActuales();
				this.habilitaVentanaAgregaDireccion();
			}else if (s.equalsIgnoreCase("Mostrar Factura")) {
				this.getFacturasImpagas();
				this.habilitaVentanaFacturasPersona();
			}
			this.inhabilitaVentanaPrincipal();
		} else
			this.informarVistaPrincipal("Debe seleccionar una persona de la lista");
		}
	

	// VISTAPRINCIPAL
	void informarVistaPrincipal(String msg) {
		this.vistaPrincipal.informar(msg);
	}

	// apartado abonados
	/**
	 * Agrega una persona al sistema utilizando los datos proporcionados por la vista de agregar personas.
	 * Realiza diferentes validaciones y captura las excepciones correspondientes.
	 * Refresca la lista de personas después de agregar una nueva persona.
	 * Habilita/inhabilita ventanas según el resultado de la operación.
	 */
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
	/**
	 * Actualiza la lista de personas en la vista principal.
	 * Utiliza un Runnable y SwingUtilities.invokeLater para asegurar la actualización en el hilo de eventos de Swing.
	 * Obtiene la lista de personas del sistema y la agrega al modelo de lista utilizado por la vista.
	 */
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
		return listaPersonas;
	}
	/**
	 * Actualiza la lista de domicilios en la vista de agregación de domicilios.
	 * Utiliza un Runnable y SwingUtilities.invokeLater para asegurar la actualización en el hilo de eventos de Swing.
	 * Obtiene la lista de domicilios de la persona seleccionada y la agrega al modelo de lista utilizado por el ComboBox en la vista.
	 */
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
	/**
	 * Actualiza la lista de facturas en la vista principal.
	 * Utiliza un Runnable y SwingUtilities.invokeLater para asegurar la actualización en el hilo de eventos de Swing.
	 * Obtiene la lista de facturas del sistema y la agrega al modelo de lista utilizado por la vista principal.
	 */
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
		//System.out.println(listaFacturas);
		return listaFacturas;
	}
	/**
	 * Actualiza la lista de contrataciones en la vista principal.
	 * Utiliza un Runnable y SwingUtilities.invokeLater para asegurar la actualización en el hilo de eventos de Swing.
	 * Obtiene la lista de contrataciones de la persona seleccionada y la agrega al modelo de lista utilizado por la vista principal.
	 * Si no hay ninguna persona seleccionada, la lista de contrataciones se vacía.
	 */
	public void refreshContrataciones() {

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
	/**
	 * Realiza la búsqueda de facturas históricas para una persona seleccionada.
	 * Obtiene la persona seleccionada de la vista principal y la asigna a la variable local "persona".
	 * Obtiene todas las facturas del sistema mediante el método "getFacturas".
	 * Recorre la lista de facturas y verifica si pertenecen a la persona seleccionada y si el número de factura es diferente al anterior.
	 * Si cumple las condiciones, se agrega el detalle de la factura al string "fHistoricas" y se actualiza el valor de "idAnt" con el número de factura actual.
	 * Finalmente, se asigna el string "fHistoricas" a la vista principal para mostrar las facturas históricas encontradas.
	 */
	private void buscarFacturas() {
			persona = this.vistaPrincipal.getPersonaHistorica();
			ArrayList<Factura> facturas = sistema.getFacturas();
			String fHistoricas="";
			int idAnt=-1;
			for (Factura f : facturas) {
				if(f!=null && f.getPersona()==persona && f.getNumFactura()!=idAnt) {
					fHistoricas += f.detalle()+"\n";
					idAnt=f.getNumFactura();
					
				}
		}
		this.vistaPrincipal.setFacturasHistoricas(fHistoricas);
	}

	//VENTANA AGREGARDOMICILIO
	/**
	 * Agrega un nuevo domicilio a la persona seleccionada.
	 * Primero se obtiene la calle y la altura del domicilio desde la vista "vistaAgregaDireccion".
	 * A continuación, se crea un nuevo objeto "Domicilio" utilizando los datos obtenidos.
	 * Se intenta asignar el nuevo domicilio a la persona seleccionada utilizando el método "asignarNuevoDomicilio" del sistema.
	 * Si ocurre alguna excepción, se muestra el mensaje de error en la vista principal utilizando el método "informarVistaPrincipal".
	 * Finalmente, se inhabilita la ventana "vistaAgregaDireccion", se habilita la ventana principal y se actualiza la lista de domicilios en la vista.
	 */
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
	/**
	 * Obtiene los domicilios actuales de la persona seleccionada y los muestra en la vista "vistaAgregaDireccion".
	 * Primero se obtiene la persona seleccionada desde la vista principal y se almacena en la variable "persona".
	 * A continuación, se obtiene la lista de domicilios de la persona.
	 * Se limpia el modelo de la lista de domicilios en la vista "vistaAgregaDireccion".
	 * Se recorre la lista de domicilios y se agregan al modelo de la lista en la vista.
	 * Además, se imprime cada domicilio en la consola.
	 * Por último, se actualiza el área de texto en la vista "vistaAgregaDireccion" con los domicilios obtenidos.
	 */
	private void getDomiciliosActuales() {
		persona = this.vistaPrincipal.getPersona();
		ArrayList<Domicilio> domicilios = persona.getDomicilios();
		this.listaDomicilios.removeAllElements();
		for (Domicilio d : domicilios) {
			if(d!=null) {
				this.listaDomicilios.addElement(d);
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
	/**
	 * Realiza el pago de una factura seleccionada utilizando el método de pago especificado.
	 * Primero se obtiene el método de pago seleccionado desde la vista "vistaFacturasPersona" y se almacena en la variable "mp".
	 * A continuación, se obtiene la factura seleccionada desde la vista "vistaFacturasPersona" y se almacena en la variable "f".
	 * Se intenta realizar el pago de la factura utilizando el método "pagarFactura" del sistema, pasando el DNI de la persona, el número de factura y el método de pago.
	 * Si se produce alguna excepción (FacturaNoEncontradaException o PersonaNoEncontradaException), se muestra el mensaje de error en la vista principal y se restablecen las vistas "vistaFacturasPersona" y "vistaPrincipal".
	 * En caso de que el pago se realice correctamente, se muestra el detalle de la factura actualizado en la vista "vistaFacturasPersona".
	 */
	private void pagarFacturas() {
		try {
			String mp = this.vistaFacturasPersona.getMetodoPago();
			Factura f = this.vistaFacturasPersona.getFactura();
				sistema.pagarFactura(persona.getDni(), 
						f.getNumFactura(),
						mp);
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
	/**
	 * Obtiene y muestra las facturas impagas de la persona seleccionada en la vista principal.
	 * Primero se obtiene la persona seleccionada desde la vista "vistaPrincipal" y se almacena en la variable "persona".
	 * A continuación, se obtienen todas las facturas del sistema utilizando el método "getFacturas" del sistema y se almacenan en la variable "facturas".
	 * Se limpia el modelo de lista de facturas "listaFacturas".
	 * Se itera sobre las facturas obtenidas y se agregan al modelo de lista "listaFacturas" todas aquellas facturas que pertenecen a la persona seleccionada, no han sido pagadas y no están ya en la lista.
	 */
	public void getFacturasImpagas() {
			persona = this.vistaPrincipal.getPersona();
			ArrayList<Factura> facturas = sistema.getFacturas();
			this.listaFacturas.clear();
			for (Factura f : facturas) {
				if (f != null && f.getPersona()==persona && !f.isPagoRealizado() && !listaFacturas.contains(f))
					this.listaFacturas.addElement(f);
			}

	}
	
	//VENTANA NUEVACONTRATACION
	/**
	 * Agrega una nueva contratación para la persona seleccionada.
	 * Obtiene los datos de la nueva contratación desde la vista de nueva contratación.
	 * Realiza las conversiones necesarias y utiliza los métodos del sistema para crear la contratación,
	 * agregar los adicionales correspondientes y actualizar la lista de contrataciones.
	 * En caso de que ocurra alguna excepción, muestra un mensaje de error y habilita la ventana principal.
	 */
	private void agregarContratacion() {
		
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
	/**
	 * Elimina la contratación seleccionada de la persona actual.
	 * Utiliza los métodos del sistema para eliminar la contratación y actualiza la lista de contrataciones.
	 * En caso de que ocurra alguna excepción, muestra un mensaje de error.
	 */
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
	/**
	 * Método que se ejecuta al cerrar una ventana.
	 *
	 * @param e El evento de ventana.
	 */
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

	/**
	 * Agrega un nuevo técnico al sistema.
	 */
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
	/**
	 * Guarda los datos del sistema en un archivo binario.
	 */
	public void guardarDatos() {
		PersistenciaBin p = new PersistenciaBin();

		try {
			p.abrirOutput("SistemasDeSeguridadDatos.bin");
			p.escribir(StaticsUtil.SistemaASistemaDTO(sistema));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			p.cerrarOutput();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Carga los datos del sistema desde un archivo binario.
	 */
	public void cargarDatos() {
		PersistenciaBin p = new PersistenciaBin();
		try {
			p.abrirInput("SistemasDeSeguridadDatos.bin");
			StaticsUtil.setEstaticosDelSistema((SistemaDTO) p.leer());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			p.cerrarInput();
		} catch (IOException e) {
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
