package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Factura;

public class VistaFacturasPersona extends JFrame implements IVista{

	//Controlador
	private Controlador controlador;

	private JPanel contentPane;
	private JPanel panel;
	private JSplitPane splitPane;
	private JPanel panel_1;
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
	private JPanel panel_5;
	private JButton btnMostrarDetalle;
	private JButton btnPagarFactura;


	/**
	 * Create the frame.
	 */
	public VistaFacturasPersona(Controlador controlador) {
		setTitle("Sistema de seguridad-Pagar factura");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		this.setActionListener(controlador);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.controlador = controlador;
		iniciarComponentes(controlador);
		
		
	}
	public void iniciarComponentes(Controlador controlador) {
		this.controlador=controlador;
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
		
		this.list_Factura = new JList<Factura>(this.controlador.getListaFacturas());
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
		
		this.panel_4 = new JPanel();
		this.panel_1.add(this.panel_4, BorderLayout.WEST);
		this.panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.lblMetodoPago = new JLabel("Metodo de pago:");
		this.panel_4.add(this.lblMetodoPago);
		
		this.comboBox_MetodoPago = new JComboBox();
		this.comboBox_MetodoPago.setModel(new DefaultComboBoxModel(new String[] {"Efectivo", "Cheque", "Tarjeta"}));
		this.panel_4.add(this.comboBox_MetodoPago);
		
		panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.EAST);
		
		btnMostrarDetalle = new JButton("Mostrar detalle");
		panel_5.add(btnMostrarDetalle);
		
		btnPagarFactura = new JButton("Pagar factura");
		panel_5.add(btnPagarFactura);
		
		this.addWindowListener(controlador);
		this.addActionListener(controlador);
	     
	
	}
	
	public void setActionListener(ActionListener controlador) {
		//this.controlador=controlador;
	}
	@Override
	public void addActionListener(ActionListener controlador) {
	    this.btnMostrarDetalle.addActionListener(controlador);
	    this.btnPagarFactura.addActionListener(controlador);
	}
	
	public Factura getFactura() {
		return (Factura) this.list_Factura.getSelectedValue();
	}
	
	public String getMetodoPago() {
		return (String) this.comboBox_MetodoPago.getSelectedItem();
	}
	
	public void mostrarDetalle(String detalle) {
		this.textArea_Detalle.setText(detalle+"\n");
	}
	
	
	
}