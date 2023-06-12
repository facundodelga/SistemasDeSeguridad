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
import javax.swing.JTabbedPane;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.AbstractListModel;

public class VistaContrataciones extends JFrame implements IVista{
	
	//Controlador
	private ActionListener controlador;
	
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
	public VistaContrataciones(ActionListener controlador) {
		setTitle("Contrataciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		contratacionesJList = new JList();
		contratacionesJList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Detalle Contratacion 1", "Detalle Contratacion 2", "Detalle Contratacion 3"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
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
	}
	
	public void setActionListener(ActionListener controlador) {
		this.controlador=controlador;
	}

	@Override
	public void addActionListener(ActionListener controlador) {
	    this.botonAgregar.addActionListener(controlador);
	    this.botonAgregar_1.addActionListener(controlador);
	    
	}
}
