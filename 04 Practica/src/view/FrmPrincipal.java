package view;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenu mnVisualizar,  mnValidar;
	private JMenuItem mntmResumen, mntmDetalle, mntmEntrar, mntmSalir;
	private JMenuBar mnPrincipal;

	

	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		addComponents();
		addListeners();
	}



	private void addListeners() {
        // Listener para el menú "Resumen"
        mntmResumen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelResumen();
            }

			
        });
    }



	private void addComponents() {
		mnPrincipal = new JMenuBar();
		contentPane.add(mnPrincipal, BorderLayout.NORTH);
		
		mnVisualizar = new JMenu("Visualizar");
		mnPrincipal.add(mnVisualizar);
		
		mntmDetalle = new JMenuItem("Detalle");
		mnVisualizar.add(mntmDetalle);
		
		mntmResumen = new JMenuItem("Resumen");
		mnVisualizar.add(mntmResumen);
		
		mnValidar = new JMenu("Validar");
		mnPrincipal.add(mnValidar);
		
		mntmEntrar = new JMenuItem("Entrar");
		mnValidar.add(mntmEntrar);
		
		mntmSalir = new JMenuItem("Salir");
		mnValidar.add(mntmSalir);
		
	}

	   private void mostrarPanelResumen() {
	        // Crear una instancia del Panel Resumen
	        PanResumen panelResumen = new PanResumen();

	        // Limpiar el contentPane y cargar el nuevo panel
	        contentPane.removeAll(); // Elimina todo el contenido actual
	        contentPane.add(mnPrincipal, BorderLayout.NORTH); // Vuelve a agregar la barra de menú
	        contentPane.add(panelResumen, BorderLayout.CENTER); // Agrega el panel de resumen

	        // Actualizar la ventana
	        contentPane.revalidate();
	        contentPane.repaint();
	    }

}
