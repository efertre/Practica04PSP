package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import controller.CtrlAlumno;
import model.Alumno;

public class PanLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;

	public PanLogin() {
		setLayout(new GridBagLayout());

		// Definición de la cuadrícula
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); // Espaciado

		// Etiqueta de usuario
		JLabel lblUsuario = new JLabel("Usuario:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(lblUsuario, gbc);

		// Campo de texto para el nombre de usuario
		txtUsuario = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(txtUsuario, gbc);

		// Etiqueta de contraseña
		JLabel lblContrasenia = new JLabel("Contraseña:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(lblContrasenia, gbc);

		// Campo de texto para la contraseña
		txtContrasenia = new JPasswordField(20);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(txtContrasenia, gbc);

		// Botón de login
		JButton btnLogin = new JButton("Iniciar sesión");
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(btnLogin, gbc);

		// Acción del botón de login
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Intentar iniciar sesión y obtener el alumno
				Alumno alumno = iniciarSesion();

				// Si el login es exitoso, cierra la ventana de login y abre la ventana principal
				if (alumno != null) {
					// Cerrar la ventana de login
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(PanLogin.this);
					frame.dispose();

					// Abrir la ventana principal con el alumno autenticado
					FrmPrincipal frmPrincipal = null;
					try {
						frmPrincipal = new FrmPrincipal(alumno);  // Pasa el alumno al formulario principal
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					frmPrincipal.setLocationRelativeTo(null);
					frmPrincipal.setVisible(true);
				}
			}
		});
	}

	public Alumno iniciarSesion() {
		String usuario = txtUsuario.getText();
		String contrasenia = new String(txtContrasenia.getPassword());

		try {
			CtrlAlumno ctrlAlumno = new CtrlAlumno();
				Alumno alumno = ctrlAlumno.obtenerAlumnoPorUsuario(usuario, contrasenia);

			if (alumno != null) {
				return alumno;
			} else {
				JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al autenticar: " + e.getMessage());
		}
		return null;
	}
}
