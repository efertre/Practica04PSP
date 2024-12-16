package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	

	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JMenuBar mnPrincipal = new JMenuBar();
		contentPane.add(mnPrincipal, BorderLayout.NORTH);
		
		JMenu mnVisualizar = new JMenu("Visualizar");
		mnPrincipal.add(mnVisualizar);
		
		JMenuItem mntmDetalle = new JMenuItem("Detalle");
		mnVisualizar.add(mntmDetalle);
		
		JMenuItem mntmResumen = new JMenuItem("Resumen");
		mnVisualizar.add(mntmResumen);
		
		JMenu mnValidar = new JMenu("Validar");
		mnPrincipal.add(mnValidar);
		
		JMenuItem mntmEntrar = new JMenuItem("Entrar");
		mnValidar.add(mntmEntrar);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnValidar.add(mntmSalir);
	}

}
