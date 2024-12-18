package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanDetalle extends JPanel {
	private JTextField tfCodigo, tfNombre, tfNota;
	private JLabel lblNombreAsign, lblCodigo, lblNota;
	private JButton btnAnterior, btnPrimero, btnUltimo, btnSiguiente, btnGuardar;
	
	

	public PanDetalle() {
		setLayout(null);
		
		addComponents();
		addListeners();
		
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
			}
		});
		
		
		
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
