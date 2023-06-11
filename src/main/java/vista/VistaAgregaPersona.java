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
import java.awt.FlowLayout;

public class VistaAgregaPersona extends JFrame {

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
	private JTextField textField_5;
	private JLabel lblDNI;
	private JLabel lblTipoFactura;
	private JPanel panel_13;
	private JButton btnAgregarPersona;
	private JTextField textField;

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
		setBounds(100, 100, 500, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
		
		this.textField = new JTextField();
		this.textField.setColumns(10);
		this.panel_Nombre.add(this.textField);
		
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
		
		this.textField_5 = new JTextField();
		this.panel_10.add(this.textField_5);
		this.textField_5.setColumns(10);
	}
}
