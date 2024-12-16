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
	private JMenu mnVisualizar,  mnValidar;
	private JMenuItem mntmResumen, mntmDetalle, mntmEntrar, mntmSalir;
	private JMenuBar mnPrincipal;

	

	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		addComponents();
		addListeners();
	}



	private void addListeners() {
		// TODO Auto-generated method stub
		
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

}
