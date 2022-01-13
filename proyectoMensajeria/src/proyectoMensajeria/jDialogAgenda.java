package proyectoMensajeria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import objetos.Chat;
import objetos.Usuario;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class jDialogAgenda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableAdmin;
	private static ArrayList<Usuario> listaAdmin;
	private static ArrayList<Usuario> listaNormal;
	private JTable tableNormal;

	/**
	 * Create the dialog.
	 */
	public jDialogAgenda() {
		
		DefaultTableModel dtm = new DefaultTableModel();
		
		dtm.setColumnIdentifiers(new String[] {"ID", "NOMBRE"});
		

		
		listaAdmin = gestionUsuarios.seleccionarAdministradores();
		
		for(int i=0; i<listaAdmin.size(); i++){
			Object [] fila = new Object[2];
			
			Usuario a = listaAdmin.get(i);
			int id = a.getIdusu();
			String n = a.getNombre();
			
			fila[0] = id;
			fila[1] = n;
			dtm.addRow ( fila );
		}
		
		DefaultTableModel dtm2 = new DefaultTableModel();
		
		dtm2.setColumnIdentifiers(new String[] {"ID", "NOMBRE"});		
		
		listaNormal = gestionUsuarios.seleccionarNormales();	
		
		for(int i=0; i<listaNormal.size(); i++){
			Object [] fila2 = new Object[2];
			
			Usuario a = listaNormal.get(i);
			int usu = a.getIdusu();
			String nn = a.getNombre();
			
			fila2[0] = usu;
			fila2[1] = nn;
			dtm2.addRow ( fila2 );
		}
		//
		
		setBounds(100, 100, 650, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tableAdmin = new JTable(dtm);
		tableAdmin.setEnabled(false);
		tableAdmin.setBounds(23, 67, 278, 250);
		contentPanel.add(tableAdmin);
		
		JLabel jLabelAdmin = new JLabel("-Administadores-");
		jLabelAdmin.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 18));
		jLabelAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelAdmin.setBounds(69, 42, 172, 14);
		contentPanel.add(jLabelAdmin);
		
		JLabel lblnormales = new JLabel("-Normales-");
		lblnormales.setHorizontalAlignment(SwingConstants.CENTER);
		lblnormales.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 18));
		lblnormales.setBounds(378, 42, 172, 14);
		contentPanel.add(lblnormales);
		
		tableNormal = new JTable(dtm2);
		tableNormal.setEnabled(false);
		tableNormal.setBounds(335, 67, 289, 250);
		contentPanel.add(tableNormal);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
