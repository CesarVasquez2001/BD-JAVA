package InterfazBD;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class InterfazDistrito extends JFrame {

	private JPanel contentPane;
	private JTextField dist_cod;
	private JTextField dist_nom;
	private JTextField dist_reg;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JButton btnInactivar;
	private JButton btnReactivar;
	private JButton btnActualizar;
	private JButton btnSalir;
	private JLabel cod;
	private JLabel nom;
	private JLabel est;
	Connection con = null;
	final String url = "jdbc:mysql://localhost:3306/";
	final String dbName = "cooperativa";
	final String username = "root";
	final String password = "2202001";
	private JTable Tabla;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazDistrito frame = new InterfazDistrito();
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
	public InterfazDistrito() {
		setResizable(false);
		setTitle("Interfaz distrito");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 683, 755);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Distrito");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(25, 22, 223, 37);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(SystemColor.activeCaptionBorder);
		panel.setToolTipText("");
		panel.setForeground(Color.WHITE);
		panel.setBounds(25, 87, 628, 229);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 32, 608, 187);
		panel.add(panel_1);
		panel_1.setLayout(null);

		cod = new JLabel("Codigo:");
		cod.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cod.setBounds(29, 29, 112, 25);
		panel_1.add(cod);

		nom = new JLabel("Nombre:");
		nom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nom.setBounds(29, 78, 112, 25);
		panel_1.add(nom);

		est = new JLabel("Estado Registro:");
		est.setFont(new Font("Tahoma", Font.PLAIN, 20));
		est.setBounds(29, 126, 166, 25);
		panel_1.add(est);

		dist_cod = new JTextField();
		dist_cod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dist_cod.transferFocus();
			}
		});
		dist_cod.setBounds(202, 29, 134, 25);
		panel_1.add(dist_cod);
		dist_cod.setColumns(10);

		dist_nom = new JTextField();
		dist_nom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dist_nom.transferFocus();
			}
		});
		dist_nom.setColumns(10);
		dist_nom.setBounds(202, 78, 372, 25);
		panel_1.add(dist_nom);

		dist_reg = new JTextField();
		dist_reg.setColumns(10);
		dist_reg.setBounds(202, 126, 39, 25);
		dist_reg.setEditable(false);
		panel_1.add(dist_reg);

		JLabel lblNewLabel_1 = new JLabel("Registro de Distritos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(10, 10, 210, 26);
		panel.add(lblNewLabel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setToolTipText("");
		panel_2.setForeground(Color.WHITE);
		panel_2.setBorder(null);
		panel_2.setBackground(SystemColor.activeCaptionBorder);
		panel_2.setBounds(25, 340, 628, 229);
		contentPane.add(panel_2);

		JLabel lblNewLabel_1_1 = new JLabel("Tabla de Distritos");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(10, 10, 188, 26);
		panel_2.add(lblNewLabel_1_1);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(30, 46, 569, 158);
		panel_2.add(scrollPane);

		Tabla = new JTable();
		Tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(Tabla);
		Tabla.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Nombre", "Estado" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		Tabla.setFocusable(false);
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				desbloquear();
				dist_reg.setEditable(false);
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnReactivar.setEnabled(false);
				btnInactivar.setEnabled(false);
			}
		});
		btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdicionar.setBounds(25, 611, 112, 28);
		contentPane.add(btnAdicionar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = Tabla.getSelectedRow();
				if (fila == -1) {
					JOptionPane.showMessageDialog(null, "Distrito no seleccionado");
				} else {
					int cod = Integer.parseInt((String) Tabla.getValueAt(fila, 0).toString());
					String nom = (String) Tabla.getValueAt(fila, 1);
					String reg = (String) Tabla.getValueAt(fila, 2);
					if (reg.equals("I")) {
						JOptionPane.showMessageDialog(null, "Distrito Inactivo");
					} else {
						limpiar();
						desbloquear();
						dist_cod.setEditable(false);
						dist_reg.setEditable(false);
						dist_cod.setText("" + cod);
						dist_nom.setText(nom);
						dist_reg.setText(reg);
						btnEliminar.setEnabled(false);
						btnAdicionar.setEnabled(false);
						btnReactivar.setEnabled(false);
						btnInactivar.setEnabled(false);
					}

				}
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnModificar.setBounds(203, 611, 112, 28);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = Tabla.getSelectedRow();
				if (fila == -1) {
					JOptionPane.showMessageDialog(null, "Distrito no seleccionado");
				} else {
					int cod = Integer.parseInt((String) Tabla.getValueAt(fila, 0).toString());
					String nom = (String) Tabla.getValueAt(fila, 1);
					String reg = (String) Tabla.getValueAt(fila, 2);
					if (reg.equals("I")) {
						JOptionPane.showMessageDialog(null, "Distrito Inactivo");
					} else {
						limpiar();
						desbloquear();
						dist_cod.setEditable(false);
						dist_nom.setEditable(false);
						dist_reg.setEditable(false);
						dist_cod.setText("" + cod);
						dist_nom.setText(nom);
						dist_reg.setText(reg);
						btnModificar.setEnabled(false);
						btnAdicionar.setEnabled(false);
						btnReactivar.setEnabled(false);
						btnInactivar.setEnabled(false);
					}

				}
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminar.setBounds(371, 611, 112, 28);
		contentPane.add(btnEliminar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				bloquear();
				btnAdicionar.setEnabled(true);
				btnModificar.setEnabled(true);
				btnEliminar.setEnabled(true);
				btnReactivar.setEnabled(true);
				btnInactivar.setEnabled(true);
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(541, 611, 112, 28);
		contentPane.add(btnCancelar);

		btnInactivar = new JButton("Inactivar");
		btnInactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = Tabla.getSelectedRow();
				if (fila == -1) {
					JOptionPane.showMessageDialog(null, "Distrito no seleccionado");
				} else {
					limpiar();
					desbloquear();
					dist_cod.setEditable(false);
					dist_nom.setEditable(false);
					dist_reg.setEditable(false);
					int cod = Integer.parseInt((String) Tabla.getValueAt(fila, 0).toString());
					String nom = (String) Tabla.getValueAt(fila, 1);
					String reg = (String) Tabla.getValueAt(fila, 2);
					dist_cod.setText("" + cod);
					dist_nom.setText(nom);
					dist_reg.setText(reg);
					btnModificar.setEnabled(false);
					btnAdicionar.setEnabled(false);
					btnEliminar.setEnabled(false);
					btnReactivar.setEnabled(false);
				}
			}
		});
		btnInactivar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnInactivar.setBounds(25, 668, 112, 28);
		contentPane.add(btnInactivar);

		btnReactivar = new JButton("Reactivar");
		btnReactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = Tabla.getSelectedRow();
				if (fila == -1) {
					JOptionPane.showMessageDialog(null, "Distrito no seleccionado");
				} else {
					limpiar();
					desbloquear();
					dist_cod.setEditable(false);
					dist_nom.setEditable(false);
					dist_reg.setEditable(false);
					int cod = Integer.parseInt((String) Tabla.getValueAt(fila, 0).toString());
					String nom = (String) Tabla.getValueAt(fila, 1);
					String reg = (String) Tabla.getValueAt(fila, 2);
					dist_cod.setText("" + cod);
					dist_nom.setText(nom);
					dist_reg.setText(reg);
					btnModificar.setEnabled(false);
					btnAdicionar.setEnabled(false);
					btnEliminar.setEnabled(false);
					btnInactivar.setEnabled(false);
				}
			}
		});
		btnReactivar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReactivar.setBounds(203, 668, 112, 28);
		contentPane.add(btnReactivar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String c, n, er;
					c = dist_cod.getText();
					n = dist_nom.getText();
					er = dist_reg.getText();
					con = (Connection) DriverManager.getConnection(url + dbName, username, password);
					if (btnAdicionar.isEnabled()) {
						desbloquear();
						PreparedStatement stmt = con
								.prepareStatement("INSERT INTO distrito (DistCod,DistNom,DistEstReg) VALUES (?,?,?) ");
						stmt.setString(1, c);
						stmt.setString(2, n);
						stmt.setString(3, er);
						stmt.executeUpdate();
						stmt.close();
						JOptionPane.showMessageDialog(null, "Registro Agregado");
						btnModificar.setEnabled(true);
						btnEliminar.setEnabled(true);
						btnInactivar.setEnabled(true);
						btnReactivar.setEnabled(true);
						limpiarTabla();
					}
					if (btnModificar.isEnabled()) {
						desbloquear();
						PreparedStatement stmt = con
								.prepareStatement("UPDATE distrito SET DistNom = ? WHERE DistCod = ?");
						stmt.setString(1, n);
						stmt.setString(2, c);
						stmt.executeUpdate();
						stmt.close();
						JOptionPane.showMessageDialog(null, "Registro Modificado");
						btnAdicionar.setEnabled(true);
						btnEliminar.setEnabled(true);
						btnInactivar.setEnabled(true);
						btnReactivar.setEnabled(true);
						limpiarTabla();
					}
					if (btnEliminar.isEnabled()) {
						PreparedStatement stmt = con.prepareStatement(
								"DELETE FROM distrito WHERE DistCod = ? and DistNom=? and DistEstReg=?");
						stmt.setString(1, c);
						stmt.setString(2, n);
						stmt.setString(3, er);
						stmt.executeUpdate();
						stmt.close();
						JOptionPane.showMessageDialog(null, "Registro Eliminado");
						btnAdicionar.setEnabled(true);
						btnModificar.setEnabled(true);
						btnInactivar.setEnabled(true);
						btnReactivar.setEnabled(true);
						limpiarTabla();
					}
					if (btnInactivar.isEnabled()) {
						PreparedStatement stmt = con
								.prepareStatement("UPDATE distrito SET DistEstReg = ? WHERE DistCod = ? and DistNom=?");
						stmt.setString(1, "I");
						stmt.setString(2, c);
						stmt.setString(3, n);
						stmt.executeUpdate();
						stmt.close();
						JOptionPane.showMessageDialog(null, "Registro Inactivado");
						btnAdicionar.setEnabled(true);
						btnEliminar.setEnabled(true);
						btnModificar.setEnabled(true);
						btnReactivar.setEnabled(true);
						limpiarTabla();
					}
					if (btnReactivar.isEnabled()) {
						PreparedStatement stmt = con
								.prepareStatement("UPDATE distrito SET DistEstReg = ? WHERE DistCod = ? and DistNom=?");
						stmt.setString(1, "A");
						stmt.setString(2, c);
						stmt.setString(3, n);
						stmt.executeUpdate();
						stmt.close();
						JOptionPane.showMessageDialog(null, "Registro Activado");
						btnAdicionar.setEnabled(true);
						btnEliminar.setEnabled(true);
						btnModificar.setEnabled(true);
						btnInactivar.setEnabled(true);
						limpiarTabla();
					}

				} catch (Exception error) {
				}
				listar();
				limpiar();
				bloquear();

			}
		});
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnActualizar.setBounds(371, 668, 112, 28);
		contentPane.add(btnActualizar);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				interfazTablasReferenciales n = new interfazTablasReferenciales();
				n.setVisible(true);
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSalir.setBounds(541, 668, 112, 28);
		contentPane.add(btnSalir);
		setLocationRelativeTo(null);
		limpiar();
		listar();
		bloquear();
	}

	void limpiar() {
		dist_cod.setText("");
		dist_nom.setText("");
		dist_reg.setText("A");
	}

	void bloquear() {
		dist_cod.setEditable(false);
		dist_nom.setEditable(false);
		dist_reg.setEditable(false);
		btnActualizar.setEnabled(false);
		btnCancelar.setEnabled(false);
	}

	void desbloquear() {
		dist_cod.setEditable(true);
		dist_nom.setEditable(true);
		dist_reg.setEditable(true);
		btnActualizar.setEnabled(true);
		btnCancelar.setEnabled(true);
	}

	void listar() {
		try {
			modelo = (DefaultTableModel) Tabla.getModel();
			con = (Connection) DriverManager.getConnection(url + dbName, username, password);
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM distrito");
			ResultSet rs = stmt.executeQuery();
			Object[] datos = new Object[3];
			while (rs.next()) {
				datos[0] = rs.getInt("DistCod");
				datos[1] = rs.getString("DistNom");
				datos[2] = rs.getString("DistEstReg");
				modelo.addRow(datos);
			}
			Tabla.setModel(modelo);
			rs.close();
			stmt.close();
		} catch (Exception e) {
		}
	}

	void limpiarTabla() {
		modelo = (DefaultTableModel) Tabla.getModel();
		for (int i = 0; i <= Tabla.getRowCount(); i++) {
			modelo.removeRow(i);
			i--;
		}
	}

	public void close() {
		WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}
}
