package proyectoMensajeria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class jDialogExistenteUsu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JPasswordField passwordField;

	/**
	 * Create the dialog.
	 */
	public jDialogExistenteUsu() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel jLabelNombre = new JLabel("Introduzca Nombre Usuario:");
		jLabelNombre.setBounds(10, 37, 169, 34);
		contentPanel.add(jLabelNombre);
		
		JLabel jLabelContra = new JLabel("Introduzca Contrase\u00F1a:");
		jLabelContra.setBounds(10, 133, 145, 14);
		contentPanel.add(jLabelContra);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(187, 44, 237, 20);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(187, 130, 237, 20);
		contentPanel.add(passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton ButtonEntrar = new JButton("Entrar");
				ButtonEntrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean encontrado = gestionUsuarios.buscarUsuario(textFieldNombre.getText(),passwordField.getPassword());
						
						int idUsu = gestionUsuarios.buscarIDxNombre(textFieldNombre.getText());
						
						if(idUsu==0) {
							
						}else {
							if(encontrado) {
								jDialogPrincipal a = new jDialogPrincipal(idUsu);
								a.setVisible(true);
								a.setLocationRelativeTo(null);
								
								dispose();
							}else
								JOptionPane.showMessageDialog(null, "No encontrado");
						}
						
					}
				});
				ButtonEntrar.setActionCommand("OK");
				buttonPane.add(ButtonEntrar);
				getRootPane().setDefaultButton(ButtonEntrar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						eleccionUsuario ele = new eleccionUsuario();
						ele.setVisible(true);
						ele.setLocationRelativeTo(null);
						
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

