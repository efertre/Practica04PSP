package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controller.CtrlDetalle;
import model.Asignatura;
import java.sql.SQLException;

public class PanDetalle extends JPanel {
    private static final long serialVersionUID = 1L;
	private JTextField tfCodigo, tfNombre, tfNota;
    private JLabel lblNombreAsign, lblCodigo, lblNota;
    private JButton btnAnterior, btnPrimero, btnUltimo, btnSiguiente, btnGuardar;
    private CtrlDetalle ctrlDetalle;

    public PanDetalle() {
        setLayout(null);
        try {
            ctrlDetalle = new CtrlDetalle();  // Inicializamos el controlador
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
        lblNombreAsign.setBounds(140, 129, 100, 26);
        add(lblNombreAsign);

        lblCodigo = new JLabel("Codigo:");
        lblCodigo.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblCodigo.setBounds(608, 24, 74, 26);
        add(lblCodigo);

        lblNota = new JLabel("Nota:");
        lblNota.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
        lblNota.setBounds(145, 250, 64, 23);
        add(lblNota);

        btnAnterior = new JButton("Anterior");
        btnAnterior.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        btnAnterior.setBounds(179, 424, 144, 49);
        add(btnAnterior);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        btnSiguiente.setBounds(475, 424, 144, 49);
        add(btnSiguiente);

        tfCodigo = new JTextField();
        tfCodigo.setEnabled(false);
        tfCodigo.setEditable(false);
        tfCodigo.setBounds(694, 23, 100, 32);
        add(tfCodigo);
        tfCodigo.setColumns(10);

        tfNombre = new JTextField();
        tfNombre.setEnabled(false);
        tfNombre.setEditable(false);
        tfNombre.setBounds(274, 122, 302, 49);
        add(tfNombre);
        tfNombre.setColumns(10);

        tfNota = new JTextField();
        tfNota.setColumns(10);
        tfNota.setBounds(273, 236, 302, 49);
        add(tfNota);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        btnGuardar.setBounds(599, 242, 100, 43);
        add(btnGuardar);

        btnPrimero = new JButton("Primero");
        btnPrimero.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        btnPrimero.setBounds(23, 424, 144, 49);
        add(btnPrimero);

        btnUltimo = new JButton("Ultimo");
        btnUltimo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        btnUltimo.setBounds(633, 424, 144, 49);
        add(btnUltimo);
    }

    private void addListeners() {
        btnAnterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ctrlDetalle.retroceder()) {
                        cargarAsignatura();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ctrlDetalle.avanzar()) {
                        cargarAsignatura();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    float nuevaNota = Float.parseFloat(tfNota.getText());
                    ctrlDetalle.modificarNota(nuevaNota);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (NumberFormatException ex) {
                    // Mostrar un mensaje de error si el formato de la nota no es válido
                    ex.printStackTrace();
                }
            }
        });

        btnPrimero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ctrlDetalle.irAlPrimero();
                    cargarAsignatura();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnUltimo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ctrlDetalle.irAlUltimo();
                    cargarAsignatura();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // Método para cargar los datos de la asignatura en los JTextField
    private void cargarAsignatura() {
        try {
            Asignatura asignatura = ctrlDetalle.obtenerAsignaturaActual();
            tfCodigo.setText(String.valueOf(asignatura.getCodigo()));
            tfNombre.setText(asignatura.getNombre());
            tfNota.setText(String.valueOf(asignatura.getNota()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
