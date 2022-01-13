package proyectoMensajeria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class jDialogNuevoUsu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldRepe;
	//---------------
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	


	/**
	 * Create the dialog.
	 */
	public jDialogNuevoUsu() {
		setBounds(100, 100, 465, 340);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel jLabelTit = new JLabel("-Nuevo Usuario-");
		jLabelTit.setFont(new Font("Tahoma", Font.BOLD, 14));
		jLabelTit.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelTit.setBounds(10, 11, 235, 38);
		contentPanel.add(jLabelTit);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(264, 70, 175, 30);
		contentPanel.add(textFieldNombre);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(20, 71, 141, 29);
		contentPanel.add(lblNombre);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(264, 111, 175, 30);
		contentPanel.add(passwordField);
		
		passwordFieldRepe = new JPasswordField();
		passwordFieldRepe.setBounds(264, 150, 175, 30);
		contentPanel.add(passwordFieldRepe);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setBounds(21, 115, 113, 22);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Repita Contrase\u00F1a");
		lblNewLabel_1_1.setBounds(21, 154, 113, 22);
		contentPanel.add(lblNewLabel_1_1);
		
		JRadioButton jButtonNormal = new JRadioButton("Usuario Normal");
		jButtonNormal.setSelected(true);
		buttonGroup.add(jButtonNormal);
		jButtonNormal.setBounds(20, 200, 143, 23);
		contentPanel.add(jButtonNormal);
		
		JRadioButton jButtonAdmin = new JRadioButton("Usuario Administrador");
		buttonGroup.add(jButtonAdmin);
		jButtonAdmin.setBounds(212, 200, 175, 23);
		contentPanel.add(jButtonAdmin);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						boolean usuNormal = true;
						
						if (jButtonNormal.isSelected())
							usuNormal = true;
						else
							usuNormal = false;
						
						int IdUsu = gestionUsuarios.seleccionarIDUsuMax();
						
						boolean continuar = gestionUsuarios.validarUsuario(textFieldNombre.getText(), passwordField.getPassword(), passwordFieldRepe.getPassword(), usuNormal);

						
						if(continuar) {
							dispose();
							jDialogPrincipal prin = new jDialogPrincipal(IdUsu);
							prin.setVisible(true);
							prin.setLocationRelativeTo(null);							
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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