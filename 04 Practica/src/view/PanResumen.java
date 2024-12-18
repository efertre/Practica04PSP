package view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class PanResumen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanResumen() {
		setLayout(null);
		
		table = new JTable();
		table.setBounds(561, 10, 216, 417);
		add(table);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(466, 406, 85, 21);
		add(btnCalcular);
		
		
		
		 // Cargar la imagen desde un archivo
        ImageIcon imagen = new ImageIcon("media/usuario_no_encontrado.jpg");
        Image imagenRedimensionada = imagen.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon imagenFinal = new ImageIcon(imagenRedimensionada);

        // Crear un JLabel y asignarle la imagen
        JLabel lblImagen = new JLabel(imagenFinal);
		lblImagen.setBounds(431, 10, 120, 120);
		add(lblImagen);
        
	}
}
