package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Choice;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class vista extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelAbonados;
	private JPanel panelTecnnicos;
	private JPanel panelFacturasHistoricas;
	private JButton btnAgregaTecnico;
	private JLabel lblNombreTecnico;
	private JTextField textField_NombreTecnico;
	private JButton btnIniciaSimulacion;
	private JTextArea textLogTecnicos;
	private JLabel lblLogTecnicos;
	private JComboBox comboBox_Accion;
	private JLabel lblAccion;
	private JTextArea textArea_Estado;
	private JLabel lblEstado;
	private JLabel lblNombreYApellido;
	private JTextField textField_DNI;
	private JLabel lblDNI;
	private JTextField textField_NombreYApellido;
	private JLabel lblFactura;
	private JComboBox comboBox_Factura;
	private JButton btnEjecuta;
	private JLabel lblDireccion;
	private JTextField textField_Direccion;
	private JButton btnAgregaDireccion;
	private JTextArea textFacturas;
	private JLabel lblFacturas;
	private JLabel lblDatosAbonante;
	private JLabel lblNombreYApellidoHistoricas;
	private JTextField textField_NombreYApellidoHistoricas;
	private JTextField textField_DNIHistoricas;
	private JLabel lblDNIHistoricas;
	private JLabel lblDireccionHistoricas;
	private JTextField textField_DireccionHistoricas;
	private JButton btnAgregaDireccionHistoricas;
	private JButton btnBuscaFacturas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vista frame = new vista();
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
	public vista() {
		setTitle("Sistema de Seguridad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 676, 480);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.contentPane.add(this.tabbedPane, BorderLayout.CENTER);
		
		this.panelAbonados = new JPanel();
		this.tabbedPane.addTab("Abonados", null, this.panelAbonados, null);
		this.panelAbonados.setLayout(null);
		
		this.comboBox_Accion = new JComboBox();
		this.comboBox_Accion.setBounds(97, 23, 227, 21);
		this.comboBox_Accion.setModel(new DefaultComboBoxModel(new String[] {"...", "Pagar factura", "Contratar nuevo servicio", "Baja de un servicio"}));
		this.panelAbonados.add(this.comboBox_Accion);
		
		this.lblAccion = new JLabel("Acción:");
		this.lblAccion.setBounds(6, 26, 87, 14);
		this.panelAbonados.add(this.lblAccion);
		
		this.textArea_Estado = new JTextArea();
		this.textArea_Estado.setBounds(328, 23, 317, 369);
		this.panelAbonados.add(this.textArea_Estado);
		
		this.lblEstado = new JLabel("Estado:");
		this.lblEstado.setBounds(328, 6, 184, 14);
		this.panelAbonados.add(this.lblEstado);
		
		this.lblNombreYApellido = new JLabel("Nombre/Apellido:");
		this.lblNombreYApellido.setBounds(6, 95, 87, 14);
		this.panelAbonados.add(this.lblNombreYApellido);
		
		this.textField_DNI = new JTextField();
		this.textField_DNI.setBounds(97, 126, 227, 20);
		this.panelAbonados.add(this.textField_DNI);
		this.textField_DNI.setColumns(10);
		
		this.lblDNI = new JLabel("DNI:");
		this.lblDNI.setBounds(6, 129, 87, 14);
		this.panelAbonados.add(this.lblDNI);
		
		this.textField_NombreYApellido = new JTextField();
		this.textField_NombreYApellido.setBounds(97, 92, 227, 20);
		this.panelAbonados.add(this.textField_NombreYApellido);
		this.textField_NombreYApellido.setColumns(10);
		
		this.lblFactura = new JLabel("Factura:");
		this.lblFactura.setBounds(6, 61, 87, 14);
		this.panelAbonados.add(this.lblFactura);
		
		this.comboBox_Factura = new JComboBox();
		this.comboBox_Factura.setBounds(97, 58, 227, 20);
		this.comboBox_Factura.setModel(new DefaultComboBoxModel(new String[] {"...", "Fisica", "Juridica"}));
		this.panelAbonados.add(this.comboBox_Factura);
		
		this.btnEjecuta = new JButton("Ejecutar");
		this.btnEjecuta.setBounds(207, 369, 117, 23);
		this.panelAbonados.add(this.btnEjecuta);
		
		this.lblDireccion = new JLabel("Dirección/es:");
		this.lblDireccion.setBounds(6, 163, 87, 14);
		this.panelAbonados.add(this.lblDireccion);
		
		this.textField_Direccion = new JTextField();
		this.textField_Direccion.setBounds(97, 160, 227, 21);
		this.panelAbonados.add(this.textField_Direccion);
		this.textField_Direccion.setColumns(10);
		
		this.btnAgregaDireccion = new JButton("Agregar dirección");
		this.btnAgregaDireccion.setBounds(207, 195, 117, 23);
		this.panelAbonados.add(this.btnAgregaDireccion);
		
		this.panelTecnnicos = new JPanel();
		this.tabbedPane.addTab("Tecnicos", null, this.panelTecnnicos, null);
		this.panelTecnnicos.setLayout(null);
		
		this.btnAgregaTecnico = new JButton("Agregar tecnico");
		this.btnAgregaTecnico.setBounds(155, 113, 109, 23);
		this.panelTecnnicos.add(this.btnAgregaTecnico);
		
		this.lblNombreTecnico = new JLabel("Nombre:");
		this.lblNombreTecnico.setBounds(10, 48, 67, 14);
		this.panelTecnnicos.add(this.lblNombreTecnico);
		
		this.textField_NombreTecnico = new JTextField();
		this.textField_NombreTecnico.setBounds(79, 45, 185, 20);
		this.panelTecnnicos.add(this.textField_NombreTecnico);
		this.textField_NombreTecnico.setColumns(10);
		
		this.btnIniciaSimulacion = new JButton("Inicia simulacion");
		this.btnIniciaSimulacion.setBounds(155, 371, 109, 23);
		this.panelTecnnicos.add(this.btnIniciaSimulacion);
		
		this.textLogTecnicos = new JTextArea();
		this.textLogTecnicos.setBounds(274, 27, 361, 367);
		this.panelTecnnicos.add(this.textLogTecnicos);
		
		this.lblLogTecnicos = new JLabel("  Log Tecnicos");
		this.lblLogTecnicos.setBounds(274, 11, 67, 14);
		this.panelTecnnicos.add(this.lblLogTecnicos);
		
		this.panelFacturasHistoricas = new JPanel();
		this.tabbedPane.addTab("Facturas Historicas Abonado", null, this.panelFacturasHistoricas, null);
		this.panelFacturasHistoricas.setLayout(null);
		
		this.textFacturas = new JTextArea();
		this.textFacturas.setBounds(332, 23, 303, 369);
		this.panelFacturasHistoricas.add(this.textFacturas);
		
		this.lblFacturas = new JLabel("Facturas:");
		this.lblFacturas.setBounds(332, 6, 169, 14);
		this.panelFacturasHistoricas.add(this.lblFacturas);
		
		this.lblDatosAbonante = new JLabel("Datos abonante:");
		this.lblDatosAbonante.setBounds(6, 6, 224, 14);
		this.panelFacturasHistoricas.add(this.lblDatosAbonante);
		
		this.lblNombreYApellidoHistoricas = new JLabel("Nombre/Apellido:");
		this.lblNombreYApellidoHistoricas.setBounds(6, 40, 94, 14);
		this.panelFacturasHistoricas.add(this.lblNombreYApellidoHistoricas);
		
		this.textField_NombreYApellidoHistoricas = new JTextField();
		this.textField_NombreYApellidoHistoricas.setBounds(104, 34, 224, 20);
		this.textField_NombreYApellidoHistoricas.setColumns(10);
		this.panelFacturasHistoricas.add(this.textField_NombreYApellidoHistoricas);
		
		this.textField_DNIHistoricas = new JTextField();
		this.textField_DNIHistoricas.setBounds(104, 68, 224, 21);
		this.textField_DNIHistoricas.setColumns(10);
		this.panelFacturasHistoricas.add(this.textField_DNIHistoricas);
		
		this.lblDNIHistoricas = new JLabel("DNI:");
		this.lblDNIHistoricas.setBounds(6, 71, 94, 14);
		this.panelFacturasHistoricas.add(this.lblDNIHistoricas);
		
		this.lblDireccionHistoricas = new JLabel("Dirección/es:");
		this.lblDireccionHistoricas.setBounds(6, 107, 94, 14);
		this.panelFacturasHistoricas.add(this.lblDireccionHistoricas);
		
		this.textField_DireccionHistoricas = new JTextField();
		this.textField_DireccionHistoricas.setBounds(104, 103, 224, 22);
		this.textField_DireccionHistoricas.setColumns(10);
		this.panelFacturasHistoricas.add(this.textField_DireccionHistoricas);
		
		this.btnAgregaDireccionHistoricas = new JButton("Agregar dirección");
		this.btnAgregaDireccionHistoricas.setBounds(211, 139, 117, 23);
		this.btnAgregaDireccionHistoricas.addActionListener(this);
		this.panelFacturasHistoricas.add(this.btnAgregaDireccionHistoricas);
		
		this.btnBuscaFacturas = new JButton("Buscar Facturas");
		this.btnBuscaFacturas.setBounds(211, 369, 117, 23);
		this.panelFacturasHistoricas.add(this.btnBuscaFacturas);
	}
	public void actionPerformed(ActionEvent e) {
	}
}
