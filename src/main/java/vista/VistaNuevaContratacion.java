package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import contrataciones.Contratacion;
import contrataciones.iServicio;
import controlador.Controlador;
import persona.Domicilio;
import promociones.iPromocion;
import javax.swing.JTextArea;

public class VistaNuevaContratacion extends JFrame implements IVista {

	// controlador
	private Controlador controlador;

	// variables privadas
	private int cantBotones = 0;
	private int cantCamaras = 0;
	private int cantMoviles = 0;

	// componentes
	private JPanel contentPane;
	private JPanel panel;
	private JPanel contratacionesPanel;
	private JPanel tituloContratacionesPanel;
	private JLabel tituloLabel;
	private JPanel domicilioPanel;
	private JLabel lblNewLabel_1;
	private JPanel panel_1;
	private JPanel servicioPanel;
	private JLabel lblNewLabel_2;
	private JPanel panel_2;
	private JComboBox comboBox_1;
	private JPanel adicionalPanel;
	private JLabel lblNewLabel_3;
	private JPanel panel_3;
	private JComboBox adicionalCombo;
	private JPanel listaAdicionalesPanel;
	private JLabel lblNewLabel_4;
	private JPanel panel_4;
	private JPanel promoPanel;
	private JLabel lblNewLabel_5;
	private JPanel panel_5;
	private JComboBox comboBox_4;
	private JPanel confirmarPanel;
	private JPanel panel_6;
	private JComboBox<Domicilio> comboDomicilio;
	private JPanel panel_7;
	private JButton botonAgregarAdicional;
	private JButton botonResetAdicionales;
	private JButton botonConfirmar;
	private JTextArea adicionalesTextArea;

	/**
	 * Create the frame.
	 */
	public VistaNuevaContratacion(Controlador controlador) {
		this.controlador = controlador;
		setTitle("Nueva Contratación");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setActionListener(controlador);
		setBounds(100, 100, 609, 400);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		contratacionesPanel = new JPanel();
		panel.add(contratacionesPanel);
		contratacionesPanel.setLayout(new GridLayout(0, 2, 0, 0));

		domicilioPanel = new JPanel();
		domicilioPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0),
				new Color(0, 64, 128), new Color(0, 64, 128)));
		contratacionesPanel.add(domicilioPanel);
		domicilioPanel.setLayout(new GridLayout(0, 1, 0, 0));

		lblNewLabel_1 = new JLabel("Domicilio");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		domicilioPanel.add(lblNewLabel_1);

		panel_1 = new JPanel();
		domicilioPanel.add(panel_1);

		comboDomicilio = new JComboBox<>();
		comboDomicilio.setModel(this.controlador.getListaDomicilios());
		panel_1.add(comboDomicilio);

		servicioPanel = new JPanel();
		servicioPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0),
				new Color(0, 64, 128), new Color(0, 64, 128)));
		contratacionesPanel.add(servicioPanel);
		servicioPanel.setLayout(new GridLayout(2, 1, 0, 0));

		lblNewLabel_2 = new JLabel("Servicio");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		servicioPanel.add(lblNewLabel_2);

		panel_2 = new JPanel();
		servicioPanel.add(panel_2);

		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Servicio Vivienda", "Servicio Comercio" }));
		panel_2.add(comboBox_1);

		adicionalPanel = new JPanel();
		adicionalPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0),
				new Color(0, 64, 128), new Color(0, 64, 128)));
		contratacionesPanel.add(adicionalPanel);
		adicionalPanel.setLayout(new GridLayout(3, 1, 0, 0));

		lblNewLabel_3 = new JLabel("Adicionales");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		adicionalPanel.add(lblNewLabel_3);

		panel_3 = new JPanel();
		adicionalPanel.add(panel_3);

		adicionalCombo = new JComboBox();
		adicionalCombo.setModel(
				new DefaultComboBoxModel(new String[] { "Botón Antipánico", "Cámara", "Móvil Acompañamiento" }));
		panel_3.add(adicionalCombo);

		panel_7 = new JPanel();
		adicionalPanel.add(panel_7);

		botonAgregarAdicional = new JButton("Agregar");
		botonAgregarAdicional.setActionCommand("Agrega adicional");
		panel_7.add(botonAgregarAdicional);

		botonResetAdicionales = new JButton("Eliminar todos");
		botonResetAdicionales.setActionCommand("Reset adicionales");
		panel_7.add(botonResetAdicionales);

		listaAdicionalesPanel = new JPanel();
		listaAdicionalesPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0),
				new Color(0, 64, 128), new Color(0, 64, 128)));
		contratacionesPanel.add(listaAdicionalesPanel);
		listaAdicionalesPanel.setLayout(new BorderLayout(0, 0));

		lblNewLabel_4 = new JLabel("Adicionales Agregados");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		listaAdicionalesPanel.add(lblNewLabel_4, BorderLayout.NORTH);

		panel_4 = new JPanel();
		listaAdicionalesPanel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		adicionalesTextArea = new JTextArea();
		adicionalesTextArea.setEditable(false);
		panel_4.add(adicionalesTextArea, BorderLayout.CENTER);

		promoPanel = new JPanel();
		promoPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0),
				new Color(0, 64, 128), new Color(0, 64, 128)));
		contratacionesPanel.add(promoPanel);
		promoPanel.setLayout(new GridLayout(2, 1, 0, 0));

		lblNewLabel_5 = new JLabel("Promoción");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		promoPanel.add(lblNewLabel_5);

		panel_5 = new JPanel();
		promoPanel.add(panel_5);

		comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] { "Sin Promo", "Promo Dorada", "Promo Platino" }));
		panel_5.add(comboBox_4);

		confirmarPanel = new JPanel();
		confirmarPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0),
				new Color(0, 64, 128), new Color(0, 64, 128)));
		contratacionesPanel.add(confirmarPanel);
		GridBagLayout gbl_confirmarPanel = new GridBagLayout();
		gbl_confirmarPanel.columnWidths = new int[] { 291, 0 };
		gbl_confirmarPanel.rowHeights = new int[] { 92, 0 };
		gbl_confirmarPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_confirmarPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		confirmarPanel.setLayout(gbl_confirmarPanel);

		panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.weighty = 1.0;
		gbc_panel_6.weightx = 1.0;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		confirmarPanel.add(panel_6, gbc_panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));

		botonConfirmar = new JButton("Confirmar Contratación");
		botonConfirmar.setActionCommand("Confirmar contratacion");
		botonConfirmar.setAlignmentX(1.0f);
		panel_6.add(botonConfirmar);

		tituloContratacionesPanel = new JPanel();
		tituloContratacionesPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0),
				new Color(0, 64, 128), new Color(0, 64, 128)));
		panel.add(tituloContratacionesPanel, BorderLayout.NORTH);
		tituloContratacionesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		tituloLabel = new JLabel("");
		tituloContratacionesPanel.add(tituloLabel);

		this.addWindowListener(controlador);
		this.addActionListener(controlador);
	}

	public void setActionListener(ActionListener controlador) {
//		this.controlador=controlador;
	}

	@Override
	public void addActionListener(ActionListener controlador) {
		// TODO Auto-generated method stub
		this.botonAgregarAdicional.addActionListener(controlador);
		this.botonConfirmar.addActionListener(controlador);
		this.botonResetAdicionales.addActionListener(controlador);
	}

	public void refreshAdicionales() {
		String res = "";

		if (cantBotones == 1) {
			res += "- Botón Antipánico\n";
		} else if (cantBotones > 1) {
			res += "- Botón Antipánico x" + cantBotones + "\n";
		}

		if (cantCamaras == 1) {
			res += "- Cámara de Vigilancia\n";
		} else if (cantCamaras > 1) {
			res += "- Cámara de Vigilancia x" + cantCamaras + "\n";
		}

		if (cantMoviles == 1) {
			res += "- Móvil de acompañamiento\n";
		} else if (cantMoviles > 1) {
			res += "- Móvil de acompañamiento x" + cantMoviles + "\n";
		}

		adicionalesTextArea.setText(res);
	}

	public void resetAdicionales() {
		cantBotones = cantCamaras = cantMoviles = 0;

		refreshAdicionales();
	}

	public void agregarAdicional() {
		switch ((String) adicionalCombo.getSelectedItem()) {
		case "Botón Antipánico":
			cantBotones++;
			break;
		case "Cámara":
			cantCamaras++;
			break;
		case "Móvil Acompañamiento":
			cantMoviles++;
			break;
		}
		refreshAdicionales();
	}
	
	

	/**
	 * @return the cantBotones
	 */
	public int getCantBotones() {
		return cantBotones;
	}

	/**
	 * @return the cantCamaras
	 */
	public int getCantCamaras() {
		return cantCamaras;
	}

	/**
	 * @return the cantMoviles
	 */
	public int getCantMoviles() {
		return cantMoviles;
	}

	public String getPromo() {
		switch ((String) this.comboBox_4.getSelectedItem()) {
		case "Sin Promo":
			return "SINPROMO";
		case "Promo Dorada":
			return "DORADA";
		case "Promo Platino":
			return "PLATINO";
		}
		return "";
	}

	public String getServicio() {
		switch ((String) this.comboBox_1.getSelectedItem()) {
		case "Servicio Vivienda":
			return "VIVIENDA";
		case "Servicio Comercio":
			return "COMERCIO";
		}

		return "";

	}
	
	

	public Domicilio getDireccion() {
		return (Domicilio) this.comboDomicilio.getSelectedItem();
	}

	public void setTituloLabel(String titulo) {
		tituloLabel.setText(titulo);
	}

}
