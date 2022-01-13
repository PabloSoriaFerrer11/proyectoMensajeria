package proyectoMensajeria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSeparator;

public class jDialogOpciones extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static int IDUsuario;
	/**
	 * Create the dialog.
	 */
	
	public jDialogOpciones(int usu) {
		IDUsuario = usu;
		
		setBounds(100, 100, 540, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton jButtonAnyadirAGrupo = new JButton("A\u00F1adir Normal a Grupo");
			jButtonAnyadirAGrupo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String aux = JOptionPane.showInputDialog(null, "Introduzca el ID del grupo.");
						
						int idchat = Integer.parseInt(aux);
						
						if(idchat>=1) {
							//Se comprueba q el id pertenece a un grupo
							boolean existe = gestionUsuarios.comprobarGrupo(idchat);
							if(existe) {
								
								boolean esAdmin = gestionUsuarios.mirarAdminGrupo(IDUsuario, idchat);
								
								if(esAdmin) {
									
									aux = JOptionPane.showInputDialog(null, "Introduzca el Nombre del usuario a añadir.");	
									
									int idusu = gestionUsuarios.buscarIDxNombre(aux);
									
									if(idusu==0) {
										
									}else {
										gestionUsuarios.agregarAGrupoNormal(idchat, idusu);
										
										gestionUsuarios.enviarMensajeEntrar(idusu, idchat);
									}
									
								}else {
									JOptionPane.showMessageDialog(null, "No eres admin de este grupo");
								}
								
							}
							
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
				}
			});
			jButtonAnyadirAGrupo.setBounds(10, 21, 170, 35);
			contentPanel.add(jButtonAnyadirAGrupo);
		}
		
		JButton jButtonEliminar = new JButton("Eliminar Grupo");
		jButtonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux = JOptionPane.showInputDialog(null, "Introduzca el ID del grupo a borrar");
				
				int idchat = Integer.parseInt(aux);
				
				if(idchat>=1) {
					boolean esAdmin = gestionUsuarios.mirarAdminGrupo(IDUsuario, idchat);
					
					if(esAdmin) {
						gestionUsuarios.eliminarGrupo(idchat);
					}
				}
			}
		});
		jButtonEliminar.setBounds(10, 125, 170, 35);
		contentPanel.add(jButtonEliminar);
		
		JButton jButtonLimpiarGrupo = new JButton("Limpiar Grupo");
		jButtonLimpiarGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux = JOptionPane.showInputDialog(null, "Introduzca el ID del grupo a vacíar");
				
				int idchat = Integer.parseInt(aux);
				if(idchat>=1) {
					boolean esAdmin = gestionUsuarios.mirarAdminGrupo(IDUsuario, idchat);
					
					if(esAdmin){
						gestionUsuarios.vaciarMensajesGrupo(IDUsuario, idchat);
					}
				}
			}
		});
		jButtonLimpiarGrupo.setBounds(10, 225, 170, 35);
		contentPanel.add(jButtonLimpiarGrupo);
		{
			JButton btnAadirAdminA = new JButton("A\u00F1adir Admin a Grupo");
			btnAadirAdminA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String aux = JOptionPane.showInputDialog(null, "Introduzca el ID del grupo.");
						
						int idchat = Integer.parseInt(aux);
						
						if(idchat>=1) {
							//Se comprueba q el id pertenece a un grupo
							boolean existe = gestionUsuarios.comprobarGrupo(idchat);
							if(existe) {
								
								boolean esAdmin = gestionUsuarios.mirarAdminGrupo(IDUsuario, idchat);
								
								if(esAdmin) {
									
									aux = JOptionPane.showInputDialog(null, "Introduzca el Nombre del usuario a añadir.");	
									
									int idusu = gestionUsuarios.buscarIDxNombre(aux);
									
									if(idusu==0) {
										
									}else {
										gestionUsuarios.agregarAGrupoAdmin(idchat, idusu);
										
										gestionUsuarios.enviarMensajeEntrar(idusu, idchat);
									}
									
								}else {
									JOptionPane.showMessageDialog(null, "No eres admin de este grupo");
								}
								
							}
							
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			btnAadirAdminA.setBounds(324, 21, 170, 35);
			contentPanel.add(btnAadirAdminA);
		}
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 101, 485, 2);
		contentPanel.add(separator);
		
		JButton jButtonEliminarAdmin = new JButton("Dejar de Administrar");
		jButtonEliminarAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String aux = JOptionPane.showInputDialog(null, "Introduzca el ID del grupo.");
					
					int idchat = Integer.parseInt(aux);
					
					if(idchat>=1) {
						//Se comprueba q el id pertenece a un grupo
						boolean existe = gestionUsuarios.comprobarGrupo(idchat);
						if(existe) {
							
							boolean esAdmin = gestionUsuarios.mirarAdminGrupo(IDUsuario, idchat);
							
							if(esAdmin) {
								
								gestionUsuarios.eliminarAdministrador(IDUsuario,idchat);
								
							}else {
								JOptionPane.showMessageDialog(null, "No eres admin de este grupo");
							}
							
						}
						
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
		});
		jButtonEliminarAdmin.setBounds(324, 125, 170, 35);
		contentPanel.add(jButtonEliminarAdmin);
		
		JButton jButtonEliminarUsuario = new JButton("Eliminar Un Usuario");
		jButtonEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String aux = JOptionPane.showInputDialog(null, "Introduzca el nombre del usurio.");
					
					int idusu = gestionUsuarios.buscarIDxNombre(aux);
					
					Object[] options = { "Si", "No" };
					int Selec = JOptionPane.showOptionDialog(null, "Se va a eliminar el usuario " + aux, "Elimnar Usu",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[0]);
				
					if(Selec == -1) {
					
					}else if(Selec == 0){
						gestionUsuarios.eliminarUsuario(usu);
					}else{
						
					}
						
							
		
							
					
							
							
						
						
				
				} catch (Exception e1) {
					e1.printStackTrace();
				}				
			}
		});
		jButtonEliminarUsuario.setBounds(324, 225, 170, 35);
		contentPanel.add(jButtonEliminarUsuario);
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
