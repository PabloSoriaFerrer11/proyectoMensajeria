package proyectoMensajeria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objetos.Chat;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;



public class jDialogPrincipal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static int IDUsuario;
	static ArrayList<Chat> listaConver;
	static ArrayList<Chat> listaGrupos;
	static ArrayList<Chat> listaAmigos;
	static int pos = 0;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * Create the dialog.
	 */
	public jDialogPrincipal(int idUSU) {

		IDUsuario = idUSU;
		
		setBounds(100, 100, 840, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 420, 824, 58);
		panel.setBackground(Color.DARK_GRAY);
		contentPanel.add(panel);
		
		JButton jButtonCrear = new JButton("Crear...");
		jButtonCrear.setBounds(103, 11, 89, 36);
		jButtonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Conversacion", "Grupo" };
				int Selec = JOptionPane.showOptionDialog(null, "Selecione una opcion", "Crear...",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[0]);
			
				if(Selec == -1) {
				
				}else if(Selec == 0){
					jDialogCrearConver cconv= new jDialogCrearConver(IDUsuario);
					cconv.setLocationRelativeTo(null);
					cconv.setVisible(true);
				}else{
					boolean esAdmin = gestionUsuarios.comprobarAdmin(IDUsuario);
					if(esAdmin) {
						jDialogCrearGrupo cgrup = new jDialogCrearGrupo(IDUsuario);
						cgrup.setLocationRelativeTo(null);
						cgrup.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "No tienes permisos");
					}
				}
			}
		});
		panel.setLayout(null);
		panel.add(jButtonCrear);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(715, 0, 109, 478);
		panel_1_1.setBackground(Color.GRAY);
		contentPanel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JRadioButton jButtonConversaciones = new JRadioButton("Conversaciones");
		jButtonConversaciones.setHorizontalAlignment(SwingConstants.CENTER);
		jButtonConversaciones.setVerticalAlignment(SwingConstants.BOTTOM);
		jButtonConversaciones.setBackground(Color.GRAY);
		buttonGroup.add(jButtonConversaciones);
		jButtonConversaciones.setSelected(true);
		jButtonConversaciones.setBounds(0, 82, 109, 23);
		panel_1_1.add(jButtonConversaciones);
		
		JRadioButton jButtonGrupos = new JRadioButton("Grupos\r\n");
		jButtonGrupos.setHorizontalAlignment(SwingConstants.CENTER);
		jButtonGrupos.setBackground(Color.GRAY);
		buttonGroup.add(jButtonGrupos);
		jButtonGrupos.setBounds(0, 121, 109, 23);
		panel_1_1.add(jButtonGrupos);
		
		JRadioButton jButtonAmigos = new JRadioButton("Amigos");
		buttonGroup.add(jButtonAmigos);
		jButtonAmigos.setBackground(Color.GRAY);
		jButtonAmigos.setHorizontalAlignment(SwingConstants.CENTER);
		jButtonAmigos.setBounds(0, 161, 109, 23);
		panel_1_1.add(jButtonAmigos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 57, 642, 365);
		contentPanel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("Terminar Chat");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jButtonConversaciones.isSelected()) {
					try {
						Chat a = listaConver.get(pos);
						int idchat = a.getIdchat();
						
						listaConver.remove(pos);
						
						listaConver = gestionUsuarios.eliminarConver(IDUsuario, idchat);
						
						if(listaConver==null) {
							listaConver = gestionUsuarios.recargarConver(IDUsuario);
						}else {
							gestionUsuarios.enviarMensajeSalir(IDUsuario, idchat);
							pos=0;
							Chat b = listaConver.get(pos);
							
							gestionUsuarios.ensenyarConver(b, textArea, IDUsuario);
						}
						
						
					}catch(IndexOutOfBoundsException ex) {
						System.out.println("No tiene chats");
					}
				}else if(jButtonGrupos.isSelected()){
					try {
						Chat a = listaGrupos.get(pos);
						int idchat = a.getIdchat();
						
						listaGrupos.remove(pos);
						
						gestionUsuarios.enviarMensajeSalir(IDUsuario, idchat);
						
						listaGrupos = gestionUsuarios.eliminarParticipacion(IDUsuario, idchat);
						
						if(listaGrupos==null) {
							listaGrupos = gestionUsuarios.recargarGrupo(IDUsuario);
						}else {
							gestionUsuarios.enviarMensajeSalir(IDUsuario, idchat);
							pos=0;
							Chat b = listaGrupos.get(pos);
							
							gestionUsuarios.ensenyarConver(b, textArea, IDUsuario);
						}

					}catch(IndexOutOfBoundsException ex) {
						System.out.println("No tiene chats");

					}
				}
			}
		});
		btnNewButton.setBounds(0, 250, 109, 23);
		panel_1_1.add(btnNewButton);
		
		JButton jButtonAmistad = new JButton("Marcar Fav");
		jButtonAmistad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jButtonConversaciones.isSelected()) {
					Chat a = listaConver.get(pos);
					
					int amigo = a.getIdusu2();
					
					if(amigo==IDUsuario){
						amigo = a.getIdusu1();
					}
					
					gestionUsuarios.agregarAmigo(IDUsuario, amigo);
				}else {
					JOptionPane.showMessageDialog(null, "No puede hacer esto aqui");
				}
			}
		});
		jButtonAmistad.setVisible(true);
		jButtonAmistad.setBounds(0, 294, 109, 23);
		panel_1_1.add(jButtonAmistad);

		
		JButton jButtonRecargar = new JButton("Recargar");
		jButtonRecargar.setBounds(592, 11, 89, 36);
		jButtonRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pos=0;
					if(jButtonConversaciones.isSelected()) {
						//Cogerá las conversaciones del usuario
						listaConver = gestionUsuarios.recargarConver(IDUsuario);
						
						Chat a = listaConver.get(pos);
						
						gestionUsuarios.ensenyarConver(a, textArea, IDUsuario);
					}else if(jButtonGrupos.isSelected()){
						listaGrupos = gestionUsuarios.recargarGrupo(IDUsuario);
						
						Chat a = listaGrupos.get(pos);
						
						gestionUsuarios.ensenyarGrupos(a, textArea, IDUsuario);
					}else {
						listaAmigos = gestionUsuarios.recargarAmigos(IDUsuario);
						
						Chat a = listaAmigos.get(pos);
						
						gestionUsuarios.ensenyarConver(a, textArea, IDUsuario);
					}
					
				} catch ( java.lang.IndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "NO tiene conversaciones");
				}
			}

			
		});
		panel.add(jButtonRecargar);
		

		
		JButton jButtonEnviar = new JButton("Enviar Un Mensaje\r\n");
		jButtonEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jButtonConversaciones.isSelected()) {
					Chat a = listaConver.get(pos);
					int idchat = a.getIdchat();
					gestionUsuarios.enviarMensaje(IDUsuario, idchat);
					
					listaConver = gestionUsuarios.recargarConver(IDUsuario);
					
					Chat b = listaConver.get(pos);
					
					gestionUsuarios.ensenyarConver(b, textArea, IDUsuario);
					
				}else if(jButtonGrupos.isSelected()){
					Chat a = listaGrupos.get(pos);
					int idchat = a.getIdchat();
					gestionUsuarios.enviarMensaje(IDUsuario, idchat);
					
					listaGrupos = gestionUsuarios.recargarGrupo(IDUsuario);
					
					Chat b = listaGrupos.get(pos);
					
					gestionUsuarios.ensenyarGrupos(b, textArea, IDUsuario);
				}else {
					Chat a = listaAmigos.get(pos);
					int idchat = a.getIdchat();
					gestionUsuarios.enviarMensaje(IDUsuario, idchat);
					
					listaAmigos = gestionUsuarios.recargarAmigos(IDUsuario);
					
					Chat b = listaAmigos.get(pos);
					
					gestionUsuarios.ensenyarConver(b, textArea, IDUsuario);
				}
				
				
			}
		});
		jButtonEnviar.setBounds(300, 11, 184, 36);
		panel.add(jButtonEnviar);
		
		JButton btnNewButton_1 = new JButton("Opciones ...");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean esAdmin = gestionUsuarios.comprobarAdmin(IDUsuario);
				if(esAdmin) {
					jDialogOpciones opc = new jDialogOpciones(IDUsuario);
					opc.setLocationRelativeTo(null);
					opc.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "No tienes permisos");
				}
			}
		});
		btnNewButton_1.setBounds(725, 11, 89, 36);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 75, 478);
		panel_1.setBackground(Color.GRAY);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton jButtonAgenda = new JButton("Agenda");
		jButtonAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jDialogAgenda ag = new jDialogAgenda();
				ag.setLocationRelativeTo(null);
				ag.setVisible(true);
			}
		});
		jButtonAgenda.setBounds(0, 187, 75, 23);
		panel_1.add(jButtonAgenda);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 824, 58);
		panel_2.setBackground(Color.GRAY);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		//--------------------------------------------------------------------------------
		try {
			listaConver = gestionUsuarios.recargarConver(IDUsuario);
			
			Chat a = listaConver.get(pos);
			
			gestionUsuarios.ensenyarConver(a, textArea, IDUsuario);
		
		
			listaGrupos = gestionUsuarios.recargarGrupo(IDUsuario);
			
			
			
			listaAmigos = gestionUsuarios.recargarAmigos(IDUsuario);
			
		
			
		}catch(IndexOutOfBoundsException e) {
			System.out.println("No tiene conversaciones");
		}
		//--------------------------------------------------------------------------------
		
		JButton jButtonPrimero = new JButton("<<|--");
		jButtonPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(jButtonConversaciones.isSelected()) {
						pos = 0;
						Chat a = listaConver.get(pos);
						gestionUsuarios.ensenyarConver(a, textArea, IDUsuario);
					}else if(jButtonGrupos.isSelected()){
						pos = 0;
						Chat a = listaGrupos.get(pos);
						gestionUsuarios.ensenyarGrupos(a, textArea, IDUsuario);
					}else {
						pos = 0;
						Chat a = listaAmigos.get(pos);
						gestionUsuarios.ensenyarConver(a, textArea, IDUsuario);
					}

				} catch ( java.lang.NullPointerException e1) {
					System.out.print("Recarga primero");
				}
			}
		});
		
		jButtonPrimero.setBounds(91, 24, 89, 23);
		panel_2.add(jButtonPrimero);
		
		JButton jButtonUltimo = new JButton("--|>>");
		jButtonUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(jButtonConversaciones.isSelected()) {
						pos = listaConver.size() -1;
						Chat a = listaConver.get(pos);
						gestionUsuarios.ensenyarConver(a, textArea, IDUsuario);
					}else if(jButtonGrupos.isSelected()){
						pos = listaGrupos.size() -1;
						Chat a = listaGrupos.get(pos);
						gestionUsuarios.ensenyarGrupos(a, textArea, IDUsuario);
					}else {
						pos = listaAmigos.size() -1;
						Chat a = listaAmigos.get(pos);
						gestionUsuarios.ensenyarConver(a, textArea, IDUsuario);
					}
					
				} catch ( java.lang.NullPointerException e1) {
					System.out.print("Recarga primero");
				}
					
			}
		});
		jButtonUltimo.setBounds(619, 24, 89, 23);
		panel_2.add(jButtonUltimo);
		
		JButton jButtonAnterior = new JButton("<");
		jButtonAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(jButtonConversaciones.isSelected()) {
						pos--;
						if(pos<=-1) {
							JOptionPane.showMessageDialog(null, "Esta al principio del todo");
							pos++;
						}
						Chat a = listaConver.get(pos);
						gestionUsuarios.ensenyarConver(a, textArea, IDUsuario);
					}else if(jButtonGrupos.isSelected()){
						pos--;
						if(pos<=-1) {
							JOptionPane.showMessageDialog(null, "Esta al principio del todo");
							pos++;
						}
						Chat a = listaGrupos.get(pos);
						gestionUsuarios.ensenyarGrupos(a, textArea, IDUsuario);
					}else {
						pos--;
						if(pos<=-1) {
							JOptionPane.showMessageDialog(null, "Esta al principio del todo");
							pos++;
						}
						Chat a = listaAmigos.get(pos);
						gestionUsuarios.ensenyarConver(a, textArea, IDUsuario);
					}
					
				} catch ( java.lang.NullPointerException e1) {
					System.out.print("Recarga primero");
				}
			}
		});
		jButtonAnterior.setBounds(197, 24, 89, 23);
		panel_2.add(jButtonAnterior);
		
		JButton jButtonSiguiente = new JButton(">");
		jButtonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(jButtonConversaciones.isSelected()){
						pos++;
						if(pos>=listaConver.size()){
							pos--;
							JOptionPane.showMessageDialog(null, "Esta al final del todo");
						}
						Chat a = listaConver.get(pos);
						gestionUsuarios.ensenyarConver(a, textArea, IDUsuario);
					}else if(jButtonGrupos.isSelected()){
						pos++;
						if(pos>=listaGrupos.size()){
							pos--;
							JOptionPane.showMessageDialog(null, "Esta al final del todo");
						}
						Chat a = listaGrupos.get(pos);
						gestionUsuarios.ensenyarGrupos(a, textArea, IDUsuario);
					}else {
						pos++;
						if(pos>=listaAmigos.size()){
							pos--;
							JOptionPane.showMessageDialog(null, "Esta al final del todo");
						}
						Chat a = listaAmigos.get(pos);
						gestionUsuarios.ensenyarConver(a, textArea, IDUsuario);
					}
					
				} catch ( java.lang.NullPointerException e1) {
					System.out.print("Recarga primero");
				}
			}
		});
		jButtonSiguiente.setBounds(520, 24, 89, 23);
		panel_2.add(jButtonSiguiente);
		
		JButton jButtonElegir = new JButton("Elegir...\r\n");
		jButtonElegir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux = JOptionPane.showInputDialog(null, "Introduzca el id del chat al que ir.");
				
				int chatABuscar = Integer.parseInt(aux);
				
				if(jButtonConversaciones.isSelected()) {
					for(int i=0; i<listaConver.size();i++) {
						Chat chat = listaConver.get(i);
						
						if(chat.getIdchat()==chatABuscar) {
							pos=i;
							gestionUsuarios.ensenyarConver(chat, textArea, IDUsuario);
						}
						
					}
				}else if(jButtonGrupos.isSelected()){
					for(int i=0; i<listaGrupos.size();i++) {
						Chat chat = listaGrupos.get(i);
						
						if(chat.getIdchat()==chatABuscar) {
							pos=i;
							gestionUsuarios.ensenyarConver(chat, textArea, IDUsuario);
						}
						
					}
				}else {
					for(int i=0; i<listaAmigos.size();i++) {
						Chat chat = listaAmigos.get(i);
						
						if(chat.getIdchat()==chatABuscar) {
							pos=i;
							gestionUsuarios.ensenyarConver(chat, textArea, IDUsuario);
						}
						
					}
				}
				
				
			}
		});
		jButtonElegir.setBounds(351, 24, 89, 23);
		panel_2.add(jButtonElegir);
		

		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
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