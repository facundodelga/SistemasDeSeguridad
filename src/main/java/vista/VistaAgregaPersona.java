package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;

public class VistaAgregaPersona extends JFrame implements IVista {


	private ActionListener controlador;
	
	
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblDatosPersona;
	private JPanel panel_1;
	private JPanel panel_Nombre;
	private JPanel panel_Direccion;
	private JLabel lblNombreApellido;
	private JPanel panel_7;
	private JPanel panel_10;
	private JTextField textField_DNI;
	private JTextField textField_TipoFactura;
	private JLabel lblDNI;
	private JLabel lblTipoFactura;
	private JPanel panel_13;
	private JButton btnAgregarPersona;
	private JTextField textField_NombreApellido;

	/**
	 * Create the frame.
	 */
	public VistaAgregaPersona(ActionListener controlador) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setActionListener(controlador);
		setBounds(100, 100, 500, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		iniciaComponentes(controlador);
		
	}
	public void iniciaComponentes(ActionListener controlador) {
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.NORTH);
		this.panel.setLayout(new BorderLayout(0, 0));
		
		this.lblDatosPersona = new JLabel("Datos de la persona:");
		this.lblDatosPersona.setHorizontalAlignment(SwingConstants.LEFT);
		this.panel.add(this.lblDatosPersona);
		
		this.panel_7 = new JPanel();
		this.contentPane.add(this.panel_7, BorderLayout.SOUTH);
		this.panel_7.setLayout(new BorderLayout(0, 0));
		
		this.panel_13 = new JPanel();
		this.panel_7.add(this.panel_13, BorderLayout.EAST);
		
		this.btnAgregarPersona = new JButton("Agregar persona");
		this.panel_13.add(this.btnAgregarPersona);
		
		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1, BorderLayout.CENTER);
		this.panel_1.setLayout(new GridLayout(5, 1, 0, 0));
		
		this.panel_Nombre = new JPanel();
		this.panel_1.add(this.panel_Nombre);
		this.panel_Nombre.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.lblNombreApellido = new JLabel("   Nombre/Apellido:");
		this.panel_Nombre.add(this.lblNombreApellido);
		
		this.textField_NombreApellido = new JTextField();
		this.textField_NombreApellido.setColumns(10);
		this.panel_Nombre.add(this.textField_NombreApellido);
		
		this.panel_Direccion = new JPanel();
		this.panel_1.add(this.panel_Direccion);
		this.panel_Direccion.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.lblDNI = new JLabel("   DNI:");
		this.panel_Direccion.add(this.lblDNI);
		
		this.textField_DNI = new JTextField();
		this.panel_Direccion.add(this.textField_DNI);
		this.textField_DNI.setColumns(10);
		
		this.panel_10 = new JPanel();
		this.panel_1.add(this.panel_10);
		this.panel_10.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.lblTipoFactura = new JLabel("   Tipo de facura:");
		this.panel_10.add(this.lblTipoFactura);
		
		this.textField_TipoFactura = new JTextField();
		this.panel_10.add(this.textField_TipoFactura);
		this.textField_TipoFactura.setColumns(10);
		
		addActionListener(this.controlador);
	}
	
	public void setActionListener(ActionListener controlador) {
		this.controlador=controlador;
	}
	
	
	public String getDNI() {
	    return textField_DNI.getText();
	}

	public String getTipoFactura() {
	    return textField_TipoFactura.getText();
	}

	public String getNombreApellido() {
	    return textField_NombreApellido.getText();
	}
	@Override
	public void addActionListener(ActionListener controlador) {
	   this.btnAgregarPersona.addActionListener(controlador);
	    
	}
}
