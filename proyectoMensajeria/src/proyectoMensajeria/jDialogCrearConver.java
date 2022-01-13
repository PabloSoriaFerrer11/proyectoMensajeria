package proyectoMensajeria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class jDialogCrearConver extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static int IDUsuario;
	private JTextField textFieldNombre;
	
	/**
	 * Create the dialog.
	 */
	public jDialogCrearConver(int usu) {
		
		IDUsuario = usu;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel Titulo = new JLabel("CREAR CONVERSACION");
		Titulo.setFont(new Font("Arial", Font.BOLD, 14));
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setBounds(10, 11, 414, 37);
		contentPanel.add(Titulo);
		{
			JLabel lblNewLabel_1 = new JLabel("\u00BFCual es el nombre del usuario?");
			lblNewLabel_1.setBounds(10, 74, 244, 24);
			contentPanel.add(lblNewLabel_1);
		}
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(10, 109, 414, 20);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton crearButton = new JButton("Crear");
				crearButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int id2 = gestionUsuarios.buscarIDxNombre(textFieldNombre.getText());
						if(id2==0) {
							
						}else {
							gestionUsuarios.crearConver(IDUsuario, id2);
							dispose();
						}
						
					}
				});
				crearButton.setActionCommand("OK");
				buttonPane.add(crearButton);
				getRootPane().setDefaultButton(crearButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

