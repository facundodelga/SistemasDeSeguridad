package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import contrataciones.Contratacion;
import controlador.Controlador;

public class VistaContrataciones extends JFrame implements IVista{
	
	//Controlador
	private Controlador controlador;
	
	private JPanel contentPane;
	private JPanel panel;
	private JPanel contratacionesPanel;
	private JPanel tituloContratacionesPanel;
	private JLabel lblNewLabel;
	private JList contratacionesJList;
	private JPanel botonesPanel;
	private JButton botonAgregar;
	private JButton botonAgregar_1;


	/**
	 * Create the frame.
	 */
	public VistaContrataciones(Controlador controlador) {
		setTitle("Contrataciones");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setActionListener(controlador);
		setBounds(100, 100, 609, 351);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		contratacionesPanel = new JPanel();
		panel.add(contratacionesPanel);
		contratacionesPanel.setLayout(new BorderLayout(0, 0));
		
		contratacionesJList = new JList(this.controlador.getListaContrataciones());


		contratacionesJList.setVisibleRowCount(20);
		contratacionesPanel.add(contratacionesJList);
		
		tituloContratacionesPanel = new JPanel();
		panel.add(tituloContratacionesPanel, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("Arias, Iñaki Gabriel");
		tituloContratacionesPanel.add(lblNewLabel);
		
		botonesPanel = new JPanel();
		panel.add(botonesPanel, BorderLayout.SOUTH);
		
		botonAgregar = new JButton("Nueva Contratación");
		botonesPanel.add(botonAgregar);
		
		botonAgregar_1 = new JButton("Eliminar Contratación");
		botonesPanel.add(botonAgregar_1);
		
		this.addWindowListener(controlador);
	}
	
	public void setActionListener(ActionListener controlador) {
//		this.controlador=controlador;
	}

	@Override
	public void addActionListener(ActionListener controlador) {
	    this.botonAgregar.addActionListener(controlador);
	    this.botonAgregar_1.addActionListener(controlador);   
	}
	
	public Contratacion getContratacion() {
		return (Contratacion) this.contratacionesJList.getSelectedValue();
	}
}
