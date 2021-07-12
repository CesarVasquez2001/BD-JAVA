package InterfazBD;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class interfazTablasReferenciales extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfazTablasReferenciales frame = new interfazTablasReferenciales();
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
	public interfazTablasReferenciales() {
		setResizable(false);
		setTitle("Tablas Referenciales");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 585, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Departamentos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				InterfazDepartamento v = new InterfazDepartamento();
				v.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(73, 82, 158, 51);
		contentPane.add(btnNewButton);

		JButton btnProvincia = new JButton("Provincia");
		btnProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				InterfazProvincia v = new InterfazProvincia();
				v.setVisible(true);
				dispose();
			}
		});
		btnProvincia.setBounds(327, 82, 158, 51);
		contentPane.add(btnProvincia);

		JButton btnDistrito = new JButton("Distrito");
		btnDistrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				InterfazDistrito v = new InterfazDistrito();
				v.setVisible(true);
				dispose();
			}
		});
		btnDistrito.setBounds(73, 188, 158, 51);
		contentPane.add(btnDistrito);

		JButton btnNewButton_1_1 = new JButton("Moneda");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				InterfazMoneda v = new InterfazMoneda();
				v.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(327, 188, 158, 51);
		contentPane.add(btnNewButton_1_1);

		JButton btnTipoProductos = new JButton("Tipo productos");
		btnTipoProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				InterfazTipoProducto v = new InterfazTipoProducto();
				v.setVisible(true);
				dispose();
			}
		});
		btnTipoProductos.setBounds(73, 296, 158, 51);
		contentPane.add(btnTipoProductos);

		JButton btnNewButton_1_1_1 = new JButton("Tipo de servicio");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				InterfazTipoServicio v = new InterfazTipoServicio();
				v.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_1_1.setBounds(327, 402, 158, 51);
		contentPane.add(btnNewButton_1_1_1);

		JButton btnNewButton_1_2 = new JButton("Tareas");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				InterfazTarea v = new InterfazTarea();
				v.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_2.setBounds(327, 296, 158, 51);
		contentPane.add(btnNewButton_1_2);

		JButton btnNewButton_2_1 = new JButton("Estados");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				InterfazEstado v = new InterfazEstado();
				v.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2_1.setBounds(73, 402, 158, 51);
		contentPane.add(btnNewButton_2_1);

		JButton btnNewButton_1_1_1_1 = new JButton("Salir");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1_1_1_1.setBounds(200, 490, 158, 51);
		contentPane.add(btnNewButton_1_1_1_1);
		setLocationRelativeTo(null);
	}

	public void close() {
		WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
	}

}
