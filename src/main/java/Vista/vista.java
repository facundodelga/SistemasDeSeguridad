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

public class vista extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelAbonados;
	private JPanel panelTecnnicos;
	private JPanel panelFacturasHistoricas;
	private JButton btnAgregaTecnico;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton btnNewButton;
	private JTextArea textArea;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox;
	private JLabel lblNewLabel_2;
	private JTextArea textArea_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField textField_1;
	private JLabel lblNewLabel_5;
	private JTextField textField_2;
	private JLabel lblNewLabel_6;
	private JComboBox comboBox_1;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_7;
	private JTextArea textArea_2;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 721, 483);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.contentPane.add(this.tabbedPane, BorderLayout.CENTER);
		
		this.panelAbonados = new JPanel();
		this.tabbedPane.addTab("Abonados", null, this.panelAbonados, null);
		this.panelAbonados.setLayout(null);
		
		this.comboBox = new JComboBox();
		this.comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Pagar factura", "Contratar nuevo servicio", "Baja de un servicio"}));
		this.comboBox.setBounds(131, 28, 117, 22);
		this.panelAbonados.add(this.comboBox);
		
		this.lblNewLabel_2 = new JLabel("Acción:");
		this.lblNewLabel_2.setBounds(34, 32, 46, 14);
		this.panelAbonados.add(this.lblNewLabel_2);
		
		this.textArea_1 = new JTextArea();
		this.textArea_1.setBounds(363, 27, 317, 368);
		this.panelAbonados.add(this.textArea_1);
		
		this.lblNewLabel_3 = new JLabel("Estado:");
		this.lblNewLabel_3.setBounds(363, 11, 66, 14);
		this.panelAbonados.add(this.lblNewLabel_3);
		
		this.lblNewLabel_4 = new JLabel("Nombre/Apellido:");
		this.lblNewLabel_4.setBounds(34, 98, 87, 14);
		this.panelAbonados.add(this.lblNewLabel_4);
		
		this.textField_1 = new JTextField();
		this.textField_1.setBounds(131, 127, 117, 22);
		this.panelAbonados.add(this.textField_1);
		this.textField_1.setColumns(10);
		
		this.lblNewLabel_5 = new JLabel("DNI:");
		this.lblNewLabel_5.setBounds(34, 131, 46, 14);
		this.panelAbonados.add(this.lblNewLabel_5);
		
		this.textField_2 = new JTextField();
		this.textField_2.setBounds(131, 94, 117, 22);
		this.panelAbonados.add(this.textField_2);
		this.textField_2.setColumns(10);
		
		this.lblNewLabel_6 = new JLabel("Factura");
		this.lblNewLabel_6.setBounds(34, 63, 46, 14);
		this.panelAbonados.add(this.lblNewLabel_6);
		
		this.comboBox_1 = new JComboBox();
		this.comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "Fisica", "Juridica"}));
		this.comboBox_1.setBounds(131, 59, 117, 22);
		this.panelAbonados.add(this.comboBox_1);
		
		this.btnNewButton_1 = new JButton("Ejecutar accion");
		this.btnNewButton_1.setBounds(248, 372, 105, 23);
		this.panelAbonados.add(this.btnNewButton_1);
		
		this.lblNewLabel_7 = new JLabel("Direccion/es:");
		this.lblNewLabel_7.setBounds(34, 156, 73, 14);
		this.panelAbonados.add(this.lblNewLabel_7);
		
		this.textArea_2 = new JTextArea();
		this.textArea_2.setEnabled(false);
		this.textArea_2.setBounds(131, 160, 219, 59);
		this.panelAbonados.add(this.textArea_2);
		
		this.panelTecnnicos = new JPanel();
		this.tabbedPane.addTab("Tecnicos", null, this.panelTecnnicos, null);
		this.panelTecnnicos.setLayout(null);
		
		this.btnAgregaTecnico = new JButton("Agregar tecnico");
		this.btnAgregaTecnico.setBounds(216, 80, 109, 23);
		this.panelTecnnicos.add(this.btnAgregaTecnico);
		
		this.lblNewLabel = new JLabel("Nombre");
		this.lblNewLabel.setBounds(23, 84, 46, 14);
		this.panelTecnnicos.add(this.lblNewLabel);
		
		this.textField = new JTextField();
		this.textField.setBounds(79, 81, 86, 20);
		this.panelTecnnicos.add(this.textField);
		this.textField.setColumns(10);
		
		this.btnNewButton = new JButton("Inicia simulacion");
		this.btnNewButton.setBounds(216, 114, 109, 23);
		this.panelTecnnicos.add(this.btnNewButton);
		
		this.textArea = new JTextArea();
		this.textArea.setBounds(367, 40, 313, 355);
		this.panelTecnnicos.add(this.textArea);
		
		this.lblNewLabel_1 = new JLabel("  Log Tecnicos");
		this.lblNewLabel_1.setBounds(367, 15, 79, 14);
		this.panelTecnnicos.add(this.lblNewLabel_1);
		
		this.panelFacturasHistoricas = new JPanel();
		this.tabbedPane.addTab("Facturas Historicas Abonado", null, this.panelFacturasHistoricas, null);
		this.panelFacturasHistoricas.setLayout(null);
	}
}
