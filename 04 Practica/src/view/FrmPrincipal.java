package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CtrlResumen;
import model.Alumno;
import model.Asignatura;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenu mnVisualizar, mnValidar, mnAcercaDe;
	private JMenuItem mntmResumen, mntmDetalle, mntmEntrar, mntmSalir, mntmAcercaDe;
	private JMenuBar mnPrincipal;

	private Alumno alumno;
	

	public FrmPrincipal(Alumno alumno) throws SQLException {
		// Se pasa el alumno al constructor
		this.alumno = alumno;

		setTitle("Practica 04 - Psanvel & Efertre");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		addComponents();
		addListeners();
		actualizarBotones(alumno);
	}

	private void actualizarBotones(Alumno alumno) {

		mnVisualizar.setEnabled(alumno != null);

		mntmEntrar.setEnabled(alumno == null);

	}

	private void addListeners() {
		// Listener para el menú "Resumen"

		mntmResumen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarPanelResumen(); // Pasar el alumno que deseas visualizar
			}
		});

		// Listener para el menú "Detalle"
		mntmDetalle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarPanelDetalle();
			}
		});

		// Listener para el menú "Entrar"
		mntmEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PanLogin loginPanel = new PanLogin();
					setContentPane(loginPanel); // Usamos setContentPane para agregar el JPanel
					setLocationRelativeTo(null); // Este método lo coloca en el centro de la pantalla
					setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		// Listener para el menú "Salir"
		mntmSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Si no hay ningún alumno logueado y le da a salir 
				if(alumno == null)
				{
					System.exit(0);
				}
				alumno = null;
				actualizarBotones(alumno);
			}
		});
		
		mntmAcercaDe.addActionListener(e -> mostrarAcercaDe());
	}

	private void addComponents() {
		mnPrincipal = new JMenuBar();
		contentPane.add(mnPrincipal, BorderLayout.NORTH);

		// Menú Visualizar
		mnVisualizar = new JMenu("Visualizar");
		mnPrincipal.add(mnVisualizar);

		mntmDetalle = new JMenuItem("Detalle");
		mnVisualizar.add(mntmDetalle);

		mntmResumen = new JMenuItem("Resumen");
		mnVisualizar.add(mntmResumen);

		// Menú Validar
		mnValidar = new JMenu("Validar");
		mnPrincipal.add(mnValidar);

		mntmEntrar = new JMenuItem("Entrar");
		mnValidar.add(mntmEntrar);

		mntmSalir = new JMenuItem("Salir");
		mnValidar.add(mntmSalir);
		
		// Menú Acerca de
		mnAcercaDe = new JMenu("Acerca de");
        mntmAcercaDe = new JMenuItem("Información");
        mnAcercaDe.add(mntmAcercaDe);
		mnPrincipal.add(mnAcercaDe);
	}

	private void mostrarPanelResumen() {
		try {
			CtrlResumen ctrlResumen = new CtrlResumen();
			if (alumno != null) {
				// Obtener las asignaturas del alumno
				List<Asignatura> asignaturas = ctrlResumen.obtenerAsignaturasDeAlumno(alumno.getNumero());

				// Crear una instancia del Panel Resumen, pasando el alumno y las asignaturas
				PanResumen panelResumen = new PanResumen(alumno, asignaturas);

				// Limpiar el contentPane y cargar el nuevo panel
				contentPane.removeAll(); // Elimina todo el contenido actual
				contentPane.add(mnPrincipal, BorderLayout.NORTH); // Vuelve a agregar la barra de menú
				contentPane.add(panelResumen, BorderLayout.CENTER); // Agrega el panel de resumen

				// Actualizar la ventana
				contentPane.revalidate();
				contentPane.repaint();
			} else {
				// Mostrar un mensaje si el alumno no existe
				JOptionPane.showMessageDialog(this, "Alumno no encontrado.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void mostrarPanelDetalle() {
		// Crear una instancia del Panel Detalle
		PanDetalle panelDetalle = new PanDetalle(alumno);

		// Limpiar el contentPane y cargar el nuevo panel
		contentPane.removeAll(); // Elimina todo el contenido actual
		contentPane.add(mnPrincipal, BorderLayout.NORTH); // Vuelve a agregar la barra de menú
		contentPane.add(panelDetalle, BorderLayout.CENTER); // Agrega el panel de detalle

		// Actualizar la ventana
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	 private void mostrarAcercaDe() {
	        JOptionPane.showMessageDialog(this, "Aplicación de Gestión Académica\nVersión 1.0\nAutores: \nPaula Sánchez Vélez \nEmanuel Esteban Fernández Trejos", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
	    }
}
