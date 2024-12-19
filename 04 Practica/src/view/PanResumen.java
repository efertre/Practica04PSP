package view;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.CtrlAlumno;
import model.Alumno;
import model.Asignatura;

public class PanResumen extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private Alumno alumno; // El alumno que se está visualizando

    /**
     * Constructor del panel.
     */
    public PanResumen(Alumno alumno, List<Asignatura> asignaturas) {
        this.alumno = alumno; // Alumno pasado como parámetro
        setLayout(null);

        // Mostrar imagen del alumno
        ImageIcon imagen = new ImageIcon(convertirBytesAImagen(alumno.getImg()));
        Image imagenRedimensionada = imagen.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon imagenFinal = new ImageIcon(imagenRedimensionada);

        JLabel lblImagen = new JLabel(imagenFinal);
        lblImagen.setBounds(431, 10, 120, 120);
        add(lblImagen);

        // Mostrar información del alumno
        JLabel lblUsuario = new JLabel("Usuario: " + alumno.getUsuario());
        lblUsuario.setBounds(10, 10, 200, 20);
        add(lblUsuario);

        JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento: " + alumno.getFechaNac());
        lblFechaNacimiento.setBounds(10, 40, 200, 20);
        add(lblFechaNacimiento);

        JLabel lblNotaMedia = new JLabel("Nota Media: " + alumno.getNotaMedia());
        lblNotaMedia.setBounds(10, 70, 200, 20);
        add(lblNotaMedia);

        // Mostrar las asignaturas en una tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Nota");

        for (Asignatura asignatura : asignaturas) {
            model.addRow(new Object[] { asignatura.getNombre(), asignatura.getNota() });
        }

        table = new JTable(model);
        table.setBounds(561, 10, 216, 417);
        add(table);

        // Botón para calcular la media
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(466, 406, 85, 21);
        btnCalcular.addActionListener(e -> calcularNotaMedia(asignaturas, lblNotaMedia));
        add(btnCalcular);
    }

    // Método para convertir el array de bytes a una imagen
    private Image convertirBytesAImagen(byte[] bytes) {
        try {
            InputStream in = new ByteArrayInputStream(bytes);
            return ImageIO.read(in);  // Convierte el byte[] a un objeto Image
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;  // Retorna null si ocurre un error
    }

 // Método para calcular la nota media
    private void calcularNotaMedia(List<Asignatura> asignaturas, JLabel lblNotaMedia) {
        double sumaNotas = 0;
        for (Asignatura asignatura : asignaturas) {
            sumaNotas += asignatura.getNota();
        }
        double mediaCalculada = sumaNotas / asignaturas.size();

        // Mostrar la media calculada
        lblNotaMedia.setText("Nota Media: " + mediaCalculada);

        // Verificar si la media calculada es diferente a la almacenada en el alumno
        if (mediaCalculada != alumno.getNotaMedia()) {
            // Si es diferente, actualizar la nota media del alumno
            alumno.setNotaMedia(mediaCalculada);
            JOptionPane.showMessageDialog(this, "La nota media ha sido actualizada.");

            // Actualizar la nota media en la base de datos
            try {
                CtrlAlumno ctrlAlumno = new CtrlAlumno();  // Instancia el controlador
                ctrlAlumno.actualizarNotaMedia(alumno);  // Actualiza la base de datos

              
            } catch (SQLException e) {
                // Manejo de excepción si ocurre un error al intentar actualizar
                JOptionPane.showMessageDialog(this, "Error al actualizar la base de datos: " + e.getMessage());
            }
        }
    }

}
