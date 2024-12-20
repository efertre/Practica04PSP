package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controller.CtrlDetalle;
import model.Alumno;
import model.Asignatura;

public class PanDetalle extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField tfCodigo, tfNombre;
	private JLabel lblNombreAsign, lblCodigo, lblNota;
	private JButton btnAnterior, btnPrimero, btnUltimo, btnSiguiente, btnGuardar;
	private CtrlDetalle ctrlDetalle;
	private JSpinner spNota;

	public PanDetalle(Alumno alumno) {
		setLayout(null);
		
		try {
			ctrlDetalle = new CtrlDetalle(alumno); // Inicializamos el controlador
		} catch (SQLException e) {
			e.printStackTrace();
		}

		addComponents();
		addListeners();
		cargarAsignatura();
	}

	private void addComponents() {
		lblNombreAsign = new JLabel("Nombre:");
		lblNombreAsign.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNombreAsign.setBounds(174, 128, 100, 26);
		add(lblNombreAsign);

		lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblCodigo.setBounds(548, 22, 74, 26);
		add(lblCodigo);

		lblNota = new JLabel("Nota:");
		lblNota.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNota.setBounds(174, 230, 64, 23);
		add(lblNota);

		btnAnterior = new JButton("Anterior");
		btnAnterior.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnAnterior.setBounds(182, 361, 144, 49);
		add(btnAnterior);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnSiguiente.setBounds(455, 361, 144, 49);
		add(btnSiguiente);

		tfCodigo = new JTextField();
		tfCodigo.setEnabled(false);
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(618, 23, 100, 32);
		add(tfCodigo);
		tfCodigo.setColumns(10);

		tfNombre = new JTextField();
		tfNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfNombre.setEnabled(false);
		tfNombre.setEditable(false);
		tfNombre.setBounds(297, 122, 302, 49);
		add(tfNombre);
		tfNombre.setColumns(10);

		spNota = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.1)); // Rango: 0.0 a 10.0, incremento de 0.1
		spNota.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		spNota.setBounds(297, 219, 64, 49);
		add(spNota);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnGuardar.setBounds(383, 222, 117, 43);
		add(btnGuardar);

		btnPrimero = new JButton("Primero");
		btnPrimero.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnPrimero.setBounds(25, 361, 144, 49);
		add(btnPrimero);

		btnUltimo = new JButton("Ultimo");
		btnUltimo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnUltimo.setBounds(609, 361, 144, 49);
		add(btnUltimo);
	}

	private void addListeners() {
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retroceder();
			}
		});

		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avanzar();
			}
		});

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float nuevaNota = ((Double) spNota.getValue()).floatValue(); // Obtenemos el valor del JSpinner como
																					// float

					ctrlDetalle.modificarNota(nuevaNota);
				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}
		});

		btnPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irAlPrimero();
			}
		});

		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irAlUltimo();
			}
		});
	}

	// Método para cargar los datos de la asignatura en los JTextField

	private void cargarAsignatura() {
		try {
			Asignatura asignatura = ctrlDetalle.obtenerAsignaturaActual();
			tfCodigo.setText(String.valueOf(asignatura.getCodigo()));
			tfNombre.setText(asignatura.getNombre());
			spNota.setValue(asignatura.getNota()); // Establecemos el valor en el JSpinner
			actualizarBotones();

		} catch (SQLException e) {
			mostrarError("Error al cargar la asignatura: " + e.getMessage());
		}
	}

	private void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void actualizarBotones() {
		try {
			// Deshabilitar los botones "Primero" y "Anterior" si estamos en el primer
			// registro
			btnPrimero.setEnabled(!ctrlDetalle.esPrimero());
			btnAnterior.setEnabled(!ctrlDetalle.esPrimero());

			// Deshabilitar los botones "Último" y "Siguiente" si estamos en el último
			// registro
			btnUltimo.setEnabled(!ctrlDetalle.esUltimo());
			btnSiguiente.setEnabled(!ctrlDetalle.esUltimo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void irAlPrimero() {
		try {
			ctrlDetalle.irAlPrimero();
			cargarAsignatura();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void irAlUltimo() {
		try {
			ctrlDetalle.irAlUltimo();
			cargarAsignatura();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void retroceder() {
		try {
			if (ctrlDetalle.retroceder()) {
				cargarAsignatura();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void avanzar() {
		try {
			if (ctrlDetalle.avanzar()) {
				cargarAsignatura();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
