package proyectoMensajeria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class jDialogCrearGrupo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static int IDUsuario;
	private JTextField textFieldNombre;
	private JTextField textFieldUsu1;
	private JTextField textFieldUsu2;
	private JTextField textFieldUsu3;
	
	/**
	 * Create the dialog.
	 */
	public jDialogCrearGrupo(int usu) {
		
		IDUsuario = usu;
		
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("CREAR GRUPO");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
			lblNewLabel.setBounds(10, 11, 414, 44);
			contentPanel.add(lblNewLabel);
		}
		
		JLabel lblNewLabel_1 = new JLabel("Debera introducir 3 usuarios m\u00EDnimo.\r\nIntroduzca sus nombres");
		lblNewLabel_1.setBounds(10, 143, 414, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Debera introducir un nombre de grupo");
		lblNewLabel_2.setBounds(10, 53, 414, 14);
		contentPanel.add(lblNewLabel_2);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(10, 78, 414, 20);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Se le asignar\u00E1 a usted como administrador principal");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(10, 341, 414, 14);
		contentPanel.add(lblNewLabel_3);
		
		textFieldUsu1 = new JTextField();
		textFieldUsu1.setColumns(10);
		textFieldUsu1.setBounds(10, 179, 414, 20);
		contentPanel.add(textFieldUsu1);
		
		textFieldUsu2 = new JTextField();
		textFieldUsu2.setColumns(10);
		textFieldUsu2.setBounds(10, 220, 414, 20);
		contentPanel.add(textFieldUsu2);
		
		textFieldUsu3 = new JTextField();
		textFieldUsu3.setColumns(10);
		textFieldUsu3.setBounds(10, 259, 414, 20);
		contentPanel.add(textFieldUsu3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear\r\n");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String n = textFieldNombre.getText();
						int usu1 = gestionUsuarios.buscarIDxNombre(textFieldUsu1.getText());
						int usu2 = gestionUsuarios.buscarIDxNombre(textFieldUsu2.getText());
						int usu3 = gestionUsuarios.buscarIDxNombre(textFieldUsu3.getText());
						
						if(usu1==0 || usu2==0 || usu3==0) {
							
						}else {
							gestionUsuarios.crearGrupo(n, IDUsuario, usu1, usu2, usu3);
							dispose();
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
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

