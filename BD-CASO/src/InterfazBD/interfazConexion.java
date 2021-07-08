package InterfazBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class interfazConexion extends JFrame {
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfazConexion frame = new interfazConexion();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public interfazConexion() {
		setResizable(false);
		setTitle("Conectar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JButton btnNewButton = new JButton("Conectar a la Base de Datos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				final String url = "jdbc:mysql://localhost:3306/";
				final String dbName = "caso-cooperativa";
				final String username = "root";
				final String password = "2202001";
				try {
					con = (Connection) DriverManager.getConnection(url + dbName, username, password);
					if (!con.isClosed()) {
						Interfaz v = new Interfaz();
						v.setVisible(true);
						dispose();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "La base datos no esta conectada");
				} finally {
					try {
						if (con != null)
							con.close();
					} catch (SQLException e2) {
					}
				}
			}
		});
		btnNewButton.setBounds(104, 53, 197, 44);
		contentPane.add(btnNewButton);
	}
}
