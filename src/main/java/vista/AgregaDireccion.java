package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;

public class AgregaDireccion extends JFrame implements IVista{
	
	//Controlador
	Controlador controlador;
	
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JLabel lblDireccionesAgregadas;
	private JTextArea textArea_DireccionesAgregadas;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblDirecccion;
	private JPanel panel_6;
	private JPanel panel_4;
	private JPanel panel_7;
	private JPanel panel_8;
	private JLabel lblCalle;
	private JTextField textField_Calle;
	private JLabel lblAltura;
	private JTextField textField_Altura;
	private JButton btnAgregarDireccion;

	

	/**
	 * Create the frame.
	 */
	public AgregaDireccion(Controlador controlador) {
		this.controlador = controlador;
		
		setTitle("Sistema de Seguridad-Direcciones");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setActionListener(controlador);
		setBounds(100, 100, 650, 477);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.SOUTH);
		this.panel.setLayout(new BorderLayout(0, 0));
		
		btnAgregarDireccion = new JButton("Agregar direccion");
		btnAgregarDireccion.setActionCommand("Confirmar Domicilio");
		panel.add(btnAgregarDireccion, BorderLayout.NORTH);
		
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
		
		this.addWindowListener(controlador);
		this.addActionListener(controlador);
	}
	
	public void setActionListener(ActionListener controlador) {
//		this.controlador=controlador;
	}
	
	public String getCalle() {
	    return textField_Calle.getText();
	}

	public String getAltura() {
	    return textField_Altura.getText();
	}

	@Override
	public void addActionListener(ActionListener controlador) {
		this.btnAgregarDireccion.addActionListener(controlador);
	}

	public void vaciarTextArea() {
		this.textArea_DireccionesAgregadas.setText("");
	}
	
	public void actualizarTextArea(String dom) {
		this.textArea_DireccionesAgregadas.append(dom+"\n");
	}
	
	
}
