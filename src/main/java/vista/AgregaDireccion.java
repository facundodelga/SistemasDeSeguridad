package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class AgregaDireccion extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnSiguiente;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JLabel lblDireccionesAgregadas;
	private JTextArea textArea_DireccionesAgregadas;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblDirecccion;
	private JPanel panel_6;
	private JButton btnAgregarDireccion;
	private JPanel panel_4;
	private JPanel panel_7;
	private JPanel panel_8;
	private JLabel lblCalle;
	private JTextField textField_Calle;
	private JLabel lblAltura;
	private JTextField textField_Altura;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregaDireccion frame = new AgregaDireccion();
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
	public AgregaDireccion() {
		setTitle("Sistema de Seguridad-Direcciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 477);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.SOUTH);
		this.panel.setLayout(new BorderLayout(0, 0));
		
		this.btnSiguiente = new JButton("Siguiente");
		this.panel.add(this.btnSiguiente, BorderLayout.EAST);
		
		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1, BorderLayout.CENTER);
		this.panel_1.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane = new JScrollPane();
		this.panel_1.add(this.scrollPane, BorderLayout.CENTER);
		
		this.lblDireccionesAgregadas = new JLabel("Direciones agregadas");
		this.scrollPane.setColumnHeaderView(this.lblDireccionesAgregadas);
		
		this.textArea_DireccionesAgregadas = new JTextArea();
		this.scrollPane.setViewportView(this.textArea_DireccionesAgregadas);
		
		this.panel_2 = new JPanel();
		this.contentPane.add(this.panel_2, BorderLayout.WEST);
		this.panel_2.setLayout(new BorderLayout(0, 0));
		
		this.panel_3 = new JPanel();
		this.panel_2.add(this.panel_3, BorderLayout.NORTH);
		this.panel_3.setLayout(new BorderLayout(0, 0));
		
		this.lblDirecccion = new JLabel("Dirección:");
		this.panel_3.add(this.lblDirecccion);
		
		this.panel_6 = new JPanel();
		this.panel_2.add(this.panel_6, BorderLayout.SOUTH);
		this.panel_6.setLayout(new BorderLayout(0, 0));
		
		this.btnAgregarDireccion = new JButton("Agregar direccion");
		this.panel_6.add(this.btnAgregarDireccion, BorderLayout.EAST);
		
		this.panel_4 = new JPanel();
		this.panel_2.add(this.panel_4, BorderLayout.CENTER);
		this.panel_4.setLayout(new GridLayout(10, 2, 0, 0));
		
		this.panel_7 = new JPanel();
		this.panel_4.add(this.panel_7);
		
		this.lblCalle = new JLabel("Calle:");
		this.panel_7.add(this.lblCalle);
		
		this.textField_Calle = new JTextField();
		this.panel_7.add(this.textField_Calle);
		this.textField_Calle.setColumns(10);
		
		this.panel_8 = new JPanel();
		this.panel_4.add(this.panel_8);
		
		this.lblAltura = new JLabel("Altura");
		this.panel_8.add(this.lblAltura);
		
		this.textField_Altura = new JTextField();
		this.panel_8.add(this.textField_Altura);
		this.textField_Altura.setColumns(10);
	}

}
