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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;

public class VistaAgregaPersona extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblDatosPersona;
	private JPanel panel_1;
	private JPanel panel_calle;
	private JPanel panel_Nombre;
	private JPanel panel_DNI;
	private JPanel panel_Direccion;
	private JPanel panel_2;
	private JLabel lblNombreApellido;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JTextField textField_NombreApellido;
	private JTextField textField_DNI;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblDNI;
	private JLabel lblDireccion;
	private JLabel lblCalle;
	private JLabel lblAltura;
	private JLabel lblTipoFactura;
	private JPanel panel_12;
	private JPanel panel_13;
	private JButton btnAgregarDireccion;
	private JButton btnAgregarPersona;
	private JScrollPane scrollPane;
	private JLabel lblDireccionesAgregadas;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAgregaPersona frame = new VistaAgregaPersona();
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
	public VistaAgregaPersona() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 351);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.NORTH);
		
		this.lblDatosPersona = new JLabel("Datos de la persona:");
		this.lblDatosPersona.setHorizontalAlignment(SwingConstants.LEFT);
		this.panel.add(this.lblDatosPersona);
		
		this.panel_7 = new JPanel();
		this.contentPane.add(this.panel_7, BorderLayout.SOUTH);
		this.panel_7.setLayout(new BorderLayout(0, 0));
		
		this.panel_12 = new JPanel();
		this.panel_7.add(this.panel_12, BorderLayout.WEST);
		
		this.btnAgregarDireccion = new JButton("Agregar direccion");
		this.panel_12.add(this.btnAgregarDireccion);
		
		this.panel_13 = new JPanel();
		this.panel_7.add(this.panel_13, BorderLayout.EAST);
		
		this.btnAgregarPersona = new JButton("Agregar persona");
		this.panel_13.add(this.btnAgregarPersona);
		
		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1, BorderLayout.WEST);
		this.panel_1.setLayout(new GridLayout(6, 2, 0, 0));
		
		this.panel_Nombre = new JPanel();
		this.panel_1.add(this.panel_Nombre);
		
		this.lblNombreApellido = new JLabel("Nombre/Apellido");
		this.panel_Nombre.add(this.lblNombreApellido);
		
		this.panel_DNI = new JPanel();
		this.panel_1.add(this.panel_DNI);
		
		this.textField_NombreApellido = new JTextField();
		this.panel_DNI.add(this.textField_NombreApellido);
		this.textField_NombreApellido.setColumns(10);
		
		this.panel_Direccion = new JPanel();
		this.panel_1.add(this.panel_Direccion);
		
		this.lblDNI = new JLabel("DNI");
		this.panel_Direccion.add(this.lblDNI);
		
		this.panel_calle = new JPanel();
		this.panel_1.add(this.panel_calle);
		
		this.textField_DNI = new JTextField();
		this.panel_calle.add(this.textField_DNI);
		this.textField_DNI.setColumns(10);
		
		this.panel_4 = new JPanel();
		this.panel_1.add(this.panel_4);
		
		this.lblDireccion = new JLabel("Direccion:");
		this.panel_4.add(this.lblDireccion);
		
		this.panel_3 = new JPanel();
		this.panel_1.add(this.panel_3);
		
		this.panel_5 = new JPanel();
		this.panel_1.add(this.panel_5);
		
		this.lblCalle = new JLabel("Calle:");
		this.lblCalle.setVerticalAlignment(SwingConstants.TOP);
		this.lblCalle.setHorizontalAlignment(SwingConstants.LEFT);
		this.panel_5.add(this.lblCalle);
		
		this.panel_6 = new JPanel();
		this.panel_1.add(this.panel_6);
		
		this.textField_3 = new JTextField();
		this.panel_6.add(this.textField_3);
		this.textField_3.setColumns(10);
		
		this.panel_8 = new JPanel();
		this.panel_1.add(this.panel_8);
		
		this.lblAltura = new JLabel("Altura:");
		this.panel_8.add(this.lblAltura);
		
		this.panel_9 = new JPanel();
		this.panel_1.add(this.panel_9);
		
		this.textField_4 = new JTextField();
		this.panel_9.add(this.textField_4);
		this.textField_4.setColumns(10);
		
		this.panel_10 = new JPanel();
		this.panel_1.add(this.panel_10);
		
		this.lblTipoFactura = new JLabel("Tipo de facura");
		this.panel_10.add(this.lblTipoFactura);
		
		this.panel_11 = new JPanel();
		this.panel_1.add(this.panel_11);
		
		this.textField_5 = new JTextField();
		this.panel_11.add(this.textField_5);
		this.textField_5.setColumns(10);
		
		this.panel_2 = new JPanel();
		this.contentPane.add(this.panel_2, BorderLayout.CENTER);
		this.panel_2.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane = new JScrollPane();
		this.panel_2.add(this.scrollPane, BorderLayout.CENTER);
		
		this.lblDireccionesAgregadas = new JLabel("Direcciones agregadas:");
		this.scrollPane.setColumnHeaderView(this.lblDireccionesAgregadas);
		
		this.textArea = new JTextArea();
		this.scrollPane.setViewportView(this.textArea);
	}
}
