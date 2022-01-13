package proyectoMensajeria;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class eleccionUsuario extends JFrame {

	private JPanel contentPane;
	private ImageIcon imagen = new ImageIcon("/img/IMG.jpg");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			        UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
					eleccionUsuario frame = new eleccionUsuario();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public eleccionUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 396);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton jButtonNuevo = new JButton("Nuevo Usuario");
		jButtonNuevo.setForeground(Color.BLACK);
		jButtonNuevo.setBackground(Color.DARK_GRAY);
		jButtonNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jDialogNuevoUsu a = new jDialogNuevoUsu();
				
				a.setVisible(true);
				a.setLocationRelativeTo(null);
				
				dispose();
			}
		});
		jButtonNuevo.setBounds(23, 310, 150, 36);
		contentPane.add(jButtonNuevo);
		
		JButton jButtonExistente = new JButton("Usuario Existente");
		jButtonExistente.setBackground(Color.DARK_GRAY);
		jButtonExistente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jDialogExistenteUsu a = new jDialogExistenteUsu();
				a.setVisible(true);
				a.setLocationRelativeTo(null);
				
				dispose();
			}
		});
		jButtonExistente.setBounds(273, 310, 150, 36);
		contentPane.add(jButtonExistente);
		
		JLabel labelIMG = new JLabel("");
		labelIMG.setBounds(23, 31, 400, 225);
		labelIMG.setIcon(new ImageIcon(eleccionUsuario.class.getResource("/img/IMG.jpg")));
		contentPane.add(labelIMG);
		
	}
}


