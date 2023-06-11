package vista;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Choice;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controlador.Controlador;

import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class VistaSistemaDeSeguridad extends JFrame implements KeyListener, IVista, ActionListener   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ActionListener
	private ActionListener actionListener;
	
	//Componentes
	//Panel principal
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	//PESTAÑAS
	//Pestaña abonados
	private JPanel panelAbonados;
	    //Componentes Abonados
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblAccion;
	private JPanel panel_3;
	private JButton btnEjecuta;
	private JTextField textField_Accion;
	private JSplitPane splitPane;
	private JSplitPane splitPane_1;
	private JPanel panel_4;
	private JSplitPane splitPane_3;
	private JPanel panel_5;
	private JLabel lblPersonas;
	private JPanel panel_6;
	private JButton btnAgregarPersona;
	private JScrollPane scrollPane_PersonasAbonados;
	private JList list_PersonasAbonados;
	private JScrollPane scrollPane_AbonadosSistema;
	private JLabel lblSistema;
	private JTextArea textArea_OutputAbonados;
	//Pestaña tecnicos
	private JPanel panelTecnnicos;
		//Componentes de pestaña de tecnicos
	private JSplitPane splitPane_5;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JButton btnAgregaTecnico;
	private JButton btnIniciaSimulacion;
	private JScrollPane scrollPane_2;
	private JLabel lblSimulaciontecnicos;
	private JTextArea textArea_1;
	private JPanel panel_7;
	private JPanel panel_8;
	private JLabel lblNombreTecnico;
	private JTextField textField_NombreTecnico;
	private JSplitPane splitPane_4;
	private JScrollPane scrollPane_4;
    //Pestaña de facturas historicas
	private JPanel panelFacturasHistoricas;
	 	//Componentes pestaña de facturas historicas
	private JLabel lblFacturasHistoricas;
	private JTextArea textArea_FacturasHistoricas;
	private JPanel panel_12;
	private JButton btnBuscarFacturas;
	private JScrollPane scrollPane_Izquierda;
	private JLabel lblAbonantesHistoricas;
	private JList list_AbonantesHistoricas;
	//panel de siguiente mes
	private JPanel panelSiguienteMes;
	private JButton btnSiguienteMes;
	private JPanel panel;
	
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaSistemaDeSeguridad frame = new VistaSistemaDeSeguridad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaSistemaDeSeguridad() {
		setTitle("Sistema de Seguridad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 495);
		setLocationRelativeTo(null);
		this.iniciaVentana();
	}
	
	/* Inicia los componentes de la vista */
	
	private void iniciaVentana() {
		//Inicia Panel contenedor de todo
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		//Inicia el panel de las ventanas
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.contentPane.add(this.tabbedPane, BorderLayout.CENTER);
		
		
		
		
		//Componentes del panel de Abonados
		
		//Inicia panel de Abonados
		this.panelAbonados = new JPanel();
		this.tabbedPane.addTab("Abonados", null, this.panelAbonados, null);
		this.panelAbonados.setLayout(new BorderLayout(0, 0));
		
		this.splitPane = new JSplitPane();
		this.panelAbonados.add(this.splitPane);
		
		this.splitPane_1 = new JSplitPane();
		this.splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.splitPane.setLeftComponent(this.splitPane_1);
		
		this.panel_4 = new JPanel();
		this.splitPane_1.setRightComponent(this.panel_4);
		this.panel_4.setLayout(new BorderLayout(0, 0));
		
		this.splitPane_3 = new JSplitPane();
		this.panel_4.add(this.splitPane_3, BorderLayout.NORTH);
		
		this.panel_5 = new JPanel();
		this.splitPane_3.setLeftComponent(this.panel_5);
		
		this.lblPersonas = new JLabel("Personas:");
		this.panel_5.add(this.lblPersonas);
		
		this.panel_6 = new JPanel();
		this.splitPane_3.setRightComponent(this.panel_6);
		
		this.btnAgregarPersona = new JButton("Agregar Persona");
		this.panel_6.add(this.btnAgregarPersona);
		
		this.scrollPane_PersonasAbonados = new JScrollPane();
		this.panel_4.add(this.scrollPane_PersonasAbonados, BorderLayout.CENTER);
		
		this.list_PersonasAbonados = new JList();
		this.scrollPane_PersonasAbonados.setViewportView(this.list_PersonasAbonados);
		
		this.panel_1 = new JPanel();
		this.splitPane_1.setLeftComponent(this.panel_1);
		this.panel_1.setLayout(new BorderLayout(0, 0));
		
		this.panel_2 = new JPanel();
		this.panel_1.add(this.panel_2, BorderLayout.WEST);
		
		this.lblAccion = new JLabel("Accion:");
		this.panel_2.add(this.lblAccion);
		
		this.panel_3 = new JPanel();
		this.panel_1.add(this.panel_3, BorderLayout.EAST);
		
		this.btnEjecuta = new JButton("Ejecutar");
		this.btnEjecuta.setActionCommand("Ejecuta");
		this.panel_3.add(this.btnEjecuta);
		
		this.textField_Accion = new JTextField();
		this.textField_Accion.setColumns(10);
		this.panel_1.add(this.textField_Accion, BorderLayout.CENTER);
		
		this.scrollPane_AbonadosSistema = new JScrollPane();
		this.splitPane.setRightComponent(this.scrollPane_AbonadosSistema);
		
		this.lblSistema = new JLabel("Sistema");
		this.scrollPane_AbonadosSistema.setColumnHeaderView(this.lblSistema);
		
		this.textArea_OutputAbonados = new JTextArea();
		this.scrollPane_AbonadosSistema.setViewportView(this.textArea_OutputAbonados);
		
		
		
		//Componentes panel tecnicos
		
		//Inicia panel de Tecnicos
		this.panelTecnnicos = new JPanel();
		this.tabbedPane.addTab("Tecnicos", null, this.panelTecnnicos, null);
		this.panelTecnnicos.setLayout(new BorderLayout(0, 0));
		
		this.splitPane_5 = new JSplitPane();
		this.panelTecnnicos.add(this.splitPane_5, BorderLayout.CENTER);
		
		this.panel_9 = new JPanel();
		this.splitPane_5.setLeftComponent(this.panel_9);
		this.panel_9.setLayout(new BorderLayout(0, 0));
		
		this.panel_10 = new JPanel();
		this.panel_9.add(this.panel_10, BorderLayout.NORTH);
		
		this.btnAgregaTecnico = new JButton("Agrega tecnico");
		this.panel_10.add(this.btnAgregaTecnico);
		
		this.panel_11 = new JPanel();
		this.panel_9.add(this.panel_11, BorderLayout.CENTER);
		
		this.btnIniciaSimulacion = new JButton("Inicia simulacion");
		this.panel_11.add(this.btnIniciaSimulacion);
		
		this.scrollPane_2 = new JScrollPane();
		this.splitPane_5.setRightComponent(this.scrollPane_2);
		
		this.lblSimulaciontecnicos = new JLabel("     Simulacion tecnicos:");
		this.scrollPane_2.setColumnHeaderView(this.lblSimulaciontecnicos);
		
		this.textArea_1 = new JTextArea();
		this.scrollPane_2.setViewportView(this.textArea_1);
		
		this.panel_7 = new JPanel();
		this.panelTecnnicos.add(this.panel_7, BorderLayout.NORTH);
		this.panel_7.setLayout(new BorderLayout(0, 0));
		
		this.panel_8 = new JPanel();
		this.panel_7.add(this.panel_8, BorderLayout.WEST);
		
		this.lblNombreTecnico = new JLabel("Nombre:");
		this.panel_8.add(this.lblNombreTecnico);
		
		this.textField_NombreTecnico = new JTextField();
		this.panel_7.add(this.textField_NombreTecnico, BorderLayout.CENTER);
		this.textField_NombreTecnico.setColumns(10);
		
		
		
		//Panel Facturas Historicas
		
		//Inicia panel de Historial de Facturas
		this.panelFacturasHistoricas = new JPanel();
		this.tabbedPane.addTab("Facturas Historicas Abonado", null, this.panelFacturasHistoricas, null);
		this.panelFacturasHistoricas.setLayout(new BorderLayout(0, 0));
		
		this.splitPane_4 = new JSplitPane();
		this.panelFacturasHistoricas.add(this.splitPane_4, BorderLayout.CENTER);
		
		this.scrollPane_4 = new JScrollPane();
		this.splitPane_4.setRightComponent(this.scrollPane_4);
		
		this.lblFacturasHistoricas = new JLabel("Facturas historicas del abonante");
		this.scrollPane_4.setColumnHeaderView(this.lblFacturasHistoricas);
		
		this.textArea_FacturasHistoricas = new JTextArea();
		this.scrollPane_4.setViewportView(this.textArea_FacturasHistoricas);
		
		this.panel_12 = new JPanel();
		this.splitPane_4.setLeftComponent(this.panel_12);
		this.panel_12.setLayout(new BorderLayout(0, 0));
		
		this.btnBuscarFacturas = new JButton("Buscar Facturas");
		this.panel_12.add(this.btnBuscarFacturas, BorderLayout.SOUTH);
		
		this.scrollPane_Izquierda = new JScrollPane();
		this.panel_12.add(this.scrollPane_Izquierda, BorderLayout.CENTER);
		
		this.lblAbonantesHistoricas = new JLabel("Abonantes:");
		this.scrollPane_Izquierda.setColumnHeaderView(this.lblAbonantesHistoricas);
		
		this.list_AbonantesHistoricas = new JList();
		this.scrollPane_Izquierda.setViewportView(this.list_AbonantesHistoricas);
		
		
		//Panel de siguiente mes
		//Inicia panel
		this.panelSiguienteMes = new JPanel();
		this.contentPane.add(this.panelSiguienteMes, BorderLayout.SOUTH);
		this.panelSiguienteMes.setLayout(new GridLayout(0, 1, 0, 0));
		//Panel que rodea a el boton
		this.panel = new JPanel();
		this.panelSiguienteMes.add(this.panel);
		this.panel.setLayout(new BorderLayout(0, 0));
		
		this.btnSiguienteMes = new JButton("Siguiente Mes");
		this.panel.add(this.btnSiguienteMes, BorderLayout.EAST);
	}
	//Eventos
	
		//teclado
	public void keyReleased(KeyEvent e) {
		
		try {
			
		}
		catch(NumberFormatException e1) {
		}
	}
	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
	}
	//Setea controlador
	@Override
	public void setActionListener(Controlador controlador) {
		this.actionListener=controlador;
		
	}
	public void actionPerformed(ActionEvent e) {
	}
}