package vista;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Factura;

import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VistaFacturasPersona extends JFrame implements IVista{

	//Controlador
	private ActionListener controlador;

	private JPanel contentPane;
	private JPanel panel;
	private JSplitPane splitPane;
	private JPanel panel_1;
	private JButton btnPagarFactura;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JLabel lblFacturas;
	private JList<Factura> list;
	private JPanel panel_3;
	private JScrollPane scrollPane_1;
	private JLabel lblDetalleFactura;
	private JTextArea textArea_Detalle;
	private JList list_Factura;
	private JPanel panel_4;
	private JLabel lblMetodoPago;
	private JComboBox comboBox_MetodoPago;


	/**
	 * Create the frame.
	 */
	public VistaFacturasPersona(ActionListener controlador) {
		setTitle("Sistema de seguridad-Pagar factura");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		this.setActionListener(controlador);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		iniciarComponentes(controlador);
		
		
	}
	public void iniciarComponentes(ActionListener controlador) {
		setContentPane(this.contentPane);
		
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(new BorderLayout(0, 0));
		
		this.splitPane = new JSplitPane();
		this.panel.add(this.splitPane, BorderLayout.CENTER);
		
		this.panel_2 = new JPanel();
		this.splitPane.setLeftComponent(this.panel_2);
		this.panel_2.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane = new JScrollPane();
		this.panel_2.add(this.scrollPane, BorderLayout.CENTER);
		
		this.lblFacturas = new JLabel("Facturas");
		this.scrollPane.setColumnHeaderView(this.lblFacturas);
		
		this.list = new JList<Factura>();
		this.scrollPane.setViewportView(this.list);
		
		this.list_Factura = new JList();
		this.scrollPane.setViewportView(this.list_Factura);
		
		this.panel_3 = new JPanel();
		this.splitPane.setRightComponent(this.panel_3);
		this.panel_3.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane_1 = new JScrollPane();
		this.panel_3.add(this.scrollPane_1, BorderLayout.CENTER);
		
		this.lblDetalleFactura = new JLabel("  Detalle de la factura: ");
		this.scrollPane_1.setColumnHeaderView(this.lblDetalleFactura);
		
		this.textArea_Detalle = new JTextArea();
		this.scrollPane_1.setViewportView(this.textArea_Detalle);
		
		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1, BorderLayout.SOUTH);
		this.panel_1.setLayout(new BorderLayout(0, 0));
		
		this.btnPagarFactura = new JButton("Pagar factura");
		this.panel_1.add(this.btnPagarFactura, BorderLayout.EAST);
		
		this.panel_4 = new JPanel();
		this.panel_1.add(this.panel_4, BorderLayout.WEST);
		this.panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.lblMetodoPago = new JLabel("Metodo de pago:");
		this.panel_4.add(this.lblMetodoPago);
		
		this.comboBox_MetodoPago = new JComboBox();
		this.comboBox_MetodoPago.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una opcion", "Efectivo", "Cheque", "Tarjeta"}));
		this.panel_4.add(this.comboBox_MetodoPago);
		
		this.addWindowListener((WindowListener) controlador);
	       
		
		addActionListener(this.controlador);
	
	}
	
	public void setActionListener(ActionListener controlador) {
		this.controlador=controlador;
	}
	@Override
	public void addActionListener(ActionListener controlador) {
	    this.btnPagarFactura.addActionListener(controlador);
	}
	
	
	
	
}