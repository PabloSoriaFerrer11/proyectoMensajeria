package proyectoMensajeria;


import java.security.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import objetos.Chat;
import objetos.Mensaje;
import objetos.Usuario;

public class gestionUsuarios {

	static ConexionBBDD conect = new ConexionBBDD();
	static int pos;
	
	/**
	 * Valida si los datos son correctos para la creación de un usuario
	 * @param nombre
	 * @param contra
	 * @param contrarepe
	 * @param usuNormal
	 * @return
	 */
	public static boolean validarUsuario(String nombre, char[] contra, char[] contrarepe, boolean usuNormal) {
		
		int id = seleccionarIDUsuMax();
		
		//Segundo comprobamos que las contraseñas son iguales
		if(contra.length!=contrarepe.length) {
			JOptionPane.showMessageDialog(null, "La longitud de las contraseñas es diferente");
			return false;
		}
		
		for(int i=0; i<contra.length;i++) {
			if(contra[i]!=contrarepe[i]) {
				JOptionPane.showMessageDialog(null, "Las contraseñas son diferentes. Revisa mayusculas y minusculas");
				return false;
			}
		}
		
		
		//Seteamos la contraseña a un String para poder operar con ella
		String con = "";
		for(int i=0; i<contra.length;i++) {
			con = con + contra[i];
		}
		
	
		//Metodo para cifrar contraseña
		String cifrado = cifrarContra(con);
		
		
		System.out.println("....................................................");
		System.out.println("USU --> " + id + "|"+  nombre + "|"+ cifrado);
		System.out.println("....................................................");
		
		if (usuNormal)
			conect.InsertarDatosNormal(id, nombre, cifrado);
		else 
			conect.InsertarDatosAdmin(id, nombre, cifrado);
		
		return true;
	}
	
	/**
	 * Busca usuario en la BBDD
	 * @param n
	 * @param con
	 * @return
	 */
	public static boolean buscarUsuario(String n, char[] con) {
		
		//Se crea un objeto usuario para poder trabajar con el
		Usuario usua = new Usuario();
		
		//El usuario se busca por el nombre en la BBDD
		usua = conect.buscarUsuxNombre(n);
		
		if(usua==null) {
			
		}else {
			//Ahora cogeremos la contraseña
			String contraBBDD = usua.getContrasenya();
			
			
			String c = "";
			
			for(int i=0; i<con.length;i++)
				c = c + con[i];
			
			//Ciframos la contraseña que nos han dado
			String cifrada = cifrarContra(c);
			
			if(contraBBDD.equals(cifrada)) 
				return true;
			else 
				return false;
		}
		

		return false;
		
	}
	
	/**
	 * Busca el ID y devuelve el nombre. 
	 * @param id
	 * @return
	 */
	public static String buscarNombreXID(int id) {
		String n = "";
		
		n = conect.buscarNombrexID(id);
		
		return n; 
	}
	
	/**
	 * Busca el nombre y devuelve el ID.
	 * @param n
	 * @return
	 */
	public static int buscarIDxNombre(String n) {
		try {
			int id = 0;
			
			//Se crea un objeto usuario para poder trabajar con el
			Usuario usua = new Usuario();
					
			//El usuario se busca por el nombre en la BBDD
			usua = conect.buscarUsuxNombre(n);
			
			//Cogemos el ID
			id = usua.getIdusu();
			
			return id;
		}catch(NullPointerException ex) {
			JOptionPane.showMessageDialog(null, "Habrá introducido el nombre mal.");
		}
		return 0;
		
	}
	
	/**
	 * Crea un chat conversación
	 * @param id1
	 * @param id2
	 */
	public static void crearConver(int id1, int id2) {
		int conv =  conect.selectMaxChat();
		
		if(id1<=0 || id2<=0) {
			JOptionPane.showMessageDialog(null, "");
		}else {
			conect.InsertarConversacion(conv, id1, id2);
		}
		
		
	}
	
	/**
	 * Crea un chat grupo
	 * @param nombre
	 * @param admin
	 * @param Usu1
	 * @param Usu2
	 * @param Usu3
	 */
	public static void crearGrupo(String nombre,int admin, int Usu1, int Usu2, int Usu3) {
		int chat = conect.selectMaxChat();
		
		if(admin<=0 || Usu1<=0 || Usu2<=0 || Usu3<=0) {
			JOptionPane.showMessageDialog(null, "");
		}else {
			conect.InsertarGrupo(chat, nombre,admin, Usu1, Usu2, Usu3);
		}
	}
	
	public static boolean comprobarAdmin(int idUsu) {
		
		boolean esAdmin = conect.comprobarAdmin(idUsu);
		
		
		return esAdmin;
		
	}
	
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	
	public static ArrayList<Chat> recargarConver(int idusu) {
    	//Esto saca información
		//conect.infochats(idusu);
				
		ArrayList<Chat> lista = new ArrayList<Chat>();
		
    	//Selecciona las conversaciones
		lista = conect.seleccionarConver(idusu);

		return lista;
	}
	
	public static ArrayList<Chat> recargarGrupo(int idusu){
		
		ArrayList<Chat> lista = new ArrayList<Chat>();
		
		lista = conect.seleccionarGrupo(idusu);
		
		return lista;
	}
	
	public static ArrayList<Chat> recargarAmigos(int idusu){
		
		ArrayList<Chat> lista = new ArrayList<Chat>();
	
		lista = conect.seleccionarAmigos(idusu);
		
		return lista;
	}	

	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	
	//Este metodo se le llamara cada vez que apretemos un boton para seleccionar los mensajes del grupo
	public static void ensenyarConver(Chat c, JTextArea textPane, int idprincipal) {
		
		int id1 = c.getIdusu1();
		int id2 = c.getIdusu2();
		
		int idchat =  c.getIdchat();
		int OtroUsuario=0;
		
		if(idprincipal==id1) {
			OtroUsuario=id2;
		}else {
			OtroUsuario=id1;
		}
		
		String nombre =  conect.buscarNombrexID(OtroUsuario);
		
		textPane.setText("#################################################################################" + "\n" + 
						 "\t" + "Chat con: " + nombre + "\n" + "\t" +"Chat nº: " + idchat + "\n" + 	
						 "#################################################################################");
		

		
    	ArrayList<Mensaje> lista = new ArrayList<Mensaje>();
		
		lista = conect.seleccionarMensajes(idchat);
		
		if(lista.size()<=0 || lista==null) {
			textPane.setText(textPane.getText() + "\n" + 
			"--> Actualmente no hay menajes. Recarga la página o envía uno.");
		}else {
			for(int i = 0; i<lista.size();i++) {
				Mensaje a = lista.get(i);
				
				int usu = a.getIdusu();
				String n = conect.buscarNombrexID(usu);
				Time h = a.getHora();
				Date d = a.getFecha();
				
				String msg = a.getTxt();
				
				textPane.setText(textPane.getText() + "\n" + 
				"--> " + n + " | " + h + " | " + d +   "\n \t" + msg);
			}
		}
	}
	
	public static void ensenyarGrupos(Chat c, JTextArea textPane, int idprincipal) {
		int idgrupo = c.getIdchat();
		
		String nombre = conect.buscarNombreGrupo(idgrupo);
		
		
		
		boolean aux = conect.comprobarAdminGrupo(idprincipal, idgrupo);
		
		String esAdmin = null; 
		if(aux) {
			esAdmin = "Si";
		}else {
			esAdmin = "No";
		}
		
		
		//el usu 1 sera el mismo q el principal
		
		textPane.setText("#################################################################################" + "\n" + 
				 "\t" + "Nombre del grupo: " + nombre + "\n" + "\t" + "Nº Chat: " + idgrupo + "\n" + "\t" + "¿Administrador del grupo? " + esAdmin + "\n" +
				 "#################################################################################");
		
		ArrayList<Mensaje> lista = new ArrayList<Mensaje>();
		
		lista = conect.seleccionarMensajes(idgrupo);
		
		if(lista.size()<=0 || lista==null) {
			
		}else {
			for(int i = 0; i<lista.size();i++) {
				Mensaje a = lista.get(i);
				
				int usu = a.getIdusu();
				String n = conect.buscarNombrexID(usu);
				Time h = a.getHora();
				
				String msg = a.getTxt();
				
				textPane.setText(textPane.getText() + "\n" + 
				"--> " + n + "|" + h + "\n \t" + msg);
			}
		}
		
	}
	
	public static void enviarMensaje(int iDUsuario, int idchat) {
		
			String txt = JOptionPane.showInputDialog(null, "Que mensaje quiere enviar");
			
			if(txt!=null || txt!="") {
				conect.insertarMensaje(iDUsuario, idchat, txt);
			}
	}
	
	//--------------------------------------------------
	
	public static ArrayList<Chat> eliminarConver(int iDUsuario, int idchat) {
		ArrayList<Chat> lista = new ArrayList<Chat>();
		
		boolean aux = conect.borrarConver(idchat);
		
		if(aux) {
			lista = conect.seleccionarConver(iDUsuario);
		}else {
			lista = null;
		}
		return lista;
	}
	
	public static ArrayList<Chat> eliminarParticipacion(int iDUsuario, int idchat) {
		ArrayList<Chat> lista = new ArrayList<Chat>();
		
		//Comprobar si es admin?
		boolean aux = conect.borrarParticipante(idchat,iDUsuario);
		
		if(aux) {
			lista = conect.seleccionarGrupo(iDUsuario);
		}else {
			lista = null;
		}

		return lista;
	}

	//---------------------------------------------------
	
	public static String cifrarContra(String sinCifrar) {
		
		//Vamos a cifrar la contraseña
		String cifrado = "";
		
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sinCifrar.getBytes());
			byte[] digest = md.digest();
	
			for (byte b : digest) {
			 	  cifrado =  cifrado + Integer.toHexString(0xFF & b);
			}
				      			      
		}catch(NoSuchAlgorithmException e){
					System.out.print(e);
					
		}
		
		return cifrado;
	}

	public static boolean comprobarGrupo(int idchat) {
		boolean existeGrupo = false;
		
		existeGrupo = conect.comprobarGrupo(idchat);
		
		return existeGrupo;
	}
	
	public static void agregarAmigo(int iDUsuario, int amigo) {

		conect.insertarAmistad(iDUsuario, amigo);		
		
	}


	public static boolean mirarAdminGrupo(int iDUsuario, int idchat) {
		
		boolean aux = conect.comprobarAdminGrupo(iDUsuario, idchat);
		
		return aux;
	}

	public static void enviarMensajeSalir(int iDUsuario, int idchat) {
		String n = conect.buscarNombrexID(iDUsuario);
		
		String txt = "El  usuario " + n + " ha abandonado el chat."; 
		
		if(txt!=null || txt!="") {
			conect.insertarMensaje(iDUsuario, idchat, txt);
		}
		
	}

	public static void enviarMensajeEntrar(int iDUsuario, int idchat) {
		String n = conect.buscarNombrexID(iDUsuario);
		
		String txt = "El  usuario " + n + " ha entrado al chat."; 
		
		if(txt!=null || txt!="") {
			conect.insertarMensaje(iDUsuario, idchat, txt);
		}
		
	}
	
	public static void eliminarGrupo(int idchat) {
		
		conect.eliminarGrupo(idchat);
		
	}

	public static void vaciarMensajesGrupo(int IDUsuario, int idchat) {
		
		conect.eliminarMensajes(idchat);
		conect.insertarMensaje(IDUsuario, idchat, "Un administrador ha borrado el chat.");
	}

	public static void agregarAGrupoAdmin(int idchat, int idusu) {
		
		conect.anyadirAGrupoAdmin(idusu, idchat);
	}


	public static void agregarAGrupoNormal(int idchat, int idusu) {
		
		conect.anyadirAGrupoNormal(idusu, idchat);
		
	}

	public static int seleccionarIDUsuMax() {
		
		return conect.selectMaxUsuario();
	}
	
	public static int seleccionarIDChatMax() {
		
		return conect.selectMaxChat();
	}

	public static ArrayList<Usuario> seleccionarAdministradores() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		
		lista = conect.seleccionarUsuAdmins();
		
		return lista;
	}

	public static ArrayList<Usuario> seleccionarNormales() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		
		lista = conect.seleccionarUsuNormales();
		
		return lista;
	}
	
	public static void eliminarUsuario(int usu) {
		
		boolean esAdmin = conect.comprobarAdmin(usu);
		if(esAdmin) {
			JOptionPane.showMessageDialog(null, "No se puede eliminar un usuario administrador");
		}else {
			conect.borrarUsuario(usu);		
		}
		
	}

	public static void eliminarAdministrador(int iDUsuario, int idchat) {
		conect.borrarAdministrador(iDUsuario,idchat);
		
	}

}

