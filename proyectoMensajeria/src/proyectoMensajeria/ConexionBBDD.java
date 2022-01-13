package proyectoMensajeria;

import java.sql.*;
import java.time.LocalDate;// Import the LocalDateTime class
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import objetos.*;

public class ConexionBBDD {
    
    public static Connection conexion = null;
    
    
    /**
     * Metodo usado par conectar con la base de datos. 
     * @return Una conexion
     */
    public static Connection conectar(){
        
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mensajeria", "postgres", "postgres");
                    
        }catch(Exception e){
            e.printStackTrace();
        }
        return conexion;
    }
    
    /**
     * Inserción usuario Normal en la BBDD.
     * @param id
     * @param usu
     * @param contra
     */
    public void InsertarDatosNormal(int id, String usu, String contra) {
    	try{
        	Connection cn = conectar();	
        	
        	if (cn == null) {
                System.out.println("Conexión nula");
            } else {
            	
            	cn.setAutoCommit(false);
            	
            	PreparedStatement stmt = cn.prepareStatement("INSERT INTO usuarios (idusuario, nombre, contrasenya, fechacreacion) VALUES (?,?,?, now() )");
            	stmt.setInt(1, id);
            	stmt.setString(2, usu);
            	stmt.setString(3, contra);
            	
            	PreparedStatement stmt2 = cn.prepareStatement("INSERT INTO normales (idnormal) VALUES (?)");
            	stmt2.setInt(1, id);
            			
            	stmt.executeUpdate();
            	stmt2.executeUpdate();
            	
            	cn.setAutoCommit(true);
    			cn.close();
            }
    	}catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Inserción usuario admin en la BBDD.
     * @param id
     * @param usu
     * @param contra
     */
    public void InsertarDatosAdmin(int id, String usu, String contra) {
    	try{
        	Connection cn = conectar();	
        	
        	if (cn == null) {
                System.out.println("Conexión nula");
            } else {
            	
            	cn.setAutoCommit(false);
            	
            	PreparedStatement stmt = cn.prepareStatement("INSERT INTO usuarios (idusuario, nombre, contrasenya, fechacreacion) VALUES (?,?,?, now() )");
            	stmt.setInt(1, id);
            	stmt.setString(2, usu);
            	stmt.setString(3, contra);
            	
            	PreparedStatement stmt2 = cn.prepareStatement("INSERT INTO administradores (idadmin) VALUES (?)");
            	stmt2.setInt(1, id);
            	            	
            	PreparedStatement stmt3 = cn.prepareStatement("INSERT INTO normales (idnormal) VALUES (?)");
            	stmt3.setInt(1, id);
            	
            	stmt.executeUpdate();
            	stmt2.executeUpdate();
            	stmt3.executeUpdate();
            	
            	cn.setAutoCommit(true);
            }
    	}catch(Exception e){
            e.printStackTrace();
        }
    }    
    
    /**
     * Inserción de una conversacion en la BBDD.
     * @param idConver
     * @param idUsu1
     * @param idUsu2
     */
    public void InsertarConversacion(int idConver, int idUsu1, int idUsu2  ) {
    	try {
    		Connection cn = conectar();	
    		
    		if (cn == null) {
                System.out.println("Conexión nula");
            } else {
            	cn.setAutoCommit(false);
            	
            	PreparedStatement stmt1 = cn.prepareStatement("INSERT INTO chat (idchat) VALUES (?)");
            	stmt1.setInt(1, idConver);
            	
            	PreparedStatement stmt2 = cn.prepareStatement("INSERT INTO conversacion (idconver, idusu1, idusu2) VALUES (?, ? ,?)");
            	stmt2.setInt(1, idConver);
            	stmt2.setInt(2, idUsu1);
            	stmt2.setInt(3, idUsu2);
            	
            	stmt1.executeUpdate();
            	stmt2.executeUpdate();
            	
            	cn.setAutoCommit(true);
            }
			cn.close();
    	}catch(Exception e) {
    		e.printStackTrace();    		
    	}
    }
    
    /**
     * Inserción de un grupo en la BBDD
     * @param idConver
     * @param nombre
     * @param UsuAdmin
     * @param usu1
     * @param usu2
     * @param usu3
     */
    public void InsertarGrupo(int idConver, String nombre, int UsuAdmin, int usu1, int usu2, int usu3) {
    	try {
    		Connection cn = conectar();	
    		
    		if (cn == null) {
                System.out.println("Conexión nula");
            } else {
            	cn.setAutoCommit(false);
            	
            	//Necesario insertar en Chat
            	PreparedStatement stmtChat = cn.prepareStatement("INSERT INTO chat VALUES (?)");
            	stmtChat.setInt(1, idConver);
            	
            	
            	//Necesario insertar en Grupo
            	PreparedStatement stmtGrupo = cn.prepareStatement("INSERT INTO grupo VALUES (?, ?)");
            	stmtGrupo.setInt(1, idConver);
            	stmtGrupo.setString(2, nombre);
            	
            	//Necesario insertar en Administra
            	PreparedStatement stmtAdministra = cn.prepareStatement("INSERT INTO administra VALUES (?, ?)");
            	stmtAdministra.setInt(1, UsuAdmin);
            	stmtAdministra.setInt(2, idConver);
            	
            	//Necesario insesrtar en Pertenece 3 veces
            	PreparedStatement stmtPertenece1 = cn.prepareStatement("INSERT INTO pertenece VALUES (?, ?)");
            	stmtPertenece1.setInt(1, usu1);
            	stmtPertenece1.setInt(2, idConver);
            	
            	PreparedStatement stmtPertenece2 = cn.prepareStatement("INSERT INTO pertenece VALUES (?, ?)");
            	stmtPertenece2.setInt(1, usu2);
            	stmtPertenece2.setInt(2, idConver);
            	
            	PreparedStatement stmtPertenece3 = cn.prepareStatement("INSERT INTO pertenece VALUES (?, ?)");
            	stmtPertenece3.setInt(1, usu3);
            	stmtPertenece3.setInt(2, idConver);
            	
            	//Ejecutamos las updates
            	stmtChat.executeUpdate();
            	stmtGrupo.executeUpdate();
            	stmtAdministra.executeUpdate();
            	stmtPertenece1.executeUpdate();
            	stmtPertenece2.executeUpdate();
            	stmtPertenece3.executeUpdate();
            	
            	cn.setAutoCommit(true);
            }
			cn.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Inserción de un mensaje en la BBDD.
     * @param iDUsuario
     * @param idchat
     * @param txt
     */
	public void insertarMensaje(int iDUsuario, int idchat, String txt) {
		Connection cn = conectar();
	
		if(cn==null) {
			System.out.println("Conexion Nula");
		}else {
			try {
				
				PreparedStatement stmt = cn.prepareStatement("INSERT INTO mensaje VALUES (?, now(), now(), ?, ?)");
				stmt.setString(1, txt);
				stmt.setInt(2, iDUsuario);
				stmt.setInt(3, idchat);
				
				
				stmt.executeUpdate();
				
				cn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
    
	/**
	 * Sacar la información respecto de un usuario sobre los chats,
	 * @param idusu
	 */
    public void infochats(int idusu) {
    	Connection cn = conectar();	
    	
    	int array[] = new int[4];
    	
    	try {
    		
    		PreparedStatement selectCount = cn.prepareStatement("SELECT COUNT(*) FROM conversacion WHERE idusu1=(?) "
    				+ "UNION ALL "
    				+ "SELECT COUNT(*) FROM conversacion WHERE idusu2=(?)"
    				+ "UNION ALL "
    				+ "SELECT COUNT(*) FROM pertenece WHERE idnormal=(?) "
    				+ "UNION ALL "
    				+ "SELECT COUNT(*) FROM administra WHERE idadmin=(?) ");
    		
    		selectCount.setInt(1, idusu);
    		selectCount.setInt(2, idusu);
    		selectCount.setInt(3, idusu);
    		selectCount.setInt(4, idusu);

    		ResultSet rs = selectCount.executeQuery();
    		
    		int cont=0;
    		int aux1=0, aux2=0, aux3=0, aux4=0;
    		
    		while(rs.next()) {
    			if(cont==0) {
    				aux1=rs.getInt(1);
    				array[cont]=aux1;
    			}
    			if(cont==1) {
    				aux2=rs.getInt(1);
    				array[cont]=aux2;
    			}
    			if(cont==2) {
    				aux3=rs.getInt(1);
    				array[cont]=aux3;
    			}
    			if(cont==3) {
    				aux4=rs.getInt(1);
    				array[cont]=aux4;
    			}
    			
    			cont++;
    		}
    		
    		
    		System.out.println("______________________________________");
    		System.out.println("Conversacion creadas por el Usuario: " + array[0] + "\n" +  
    							"Conversaciones que han creado con el usuario: " + array[1] + "\n" +
    							"Conversaciones en total: " + (array[0] + array[1]) + "\n" +
    							"Grupos a los que pertenece como normal: " + array[2] + "\n" + 
    							"Grupos que administra el usuario: " + array[3] + ".");
    		System.out.println("______________________________________");
    		
    		//--------------------------------------------------------------------------------------------------------
    		
			cn.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    }
    
    /**
     * Selección de los chats conversaciones
     * @param idusu
     * @return
     */
    public ArrayList<Chat> seleccionarConver(int idusu) {
    	Connection cn = conectar();	    	
    	ArrayList<Chat> lista = new ArrayList<Chat>();
    	
    	if(cn==null) {
    		System.out.println("Conexion Nula");
    	}else {
    		try {
        		
    			PreparedStatement stmtConver = cn.prepareStatement("SELECT * FROM conversacion WHERE idusu1=(?) "
    											+ "UNION ALL "  
    											+ "SELECT * FROM conversacion WHERE idusu2=(?)");
    			
    			
    			stmtConver.setInt(1, idusu);
    			stmtConver.setInt(2, idusu);
    			
    			ResultSet rs = stmtConver.executeQuery();
    			
    			while(rs.next()) {

    				
    				int id = rs.getInt("idconver");
    				int usu1 = rs.getInt("idusu1");
    				int usu2 = rs.getInt("idusu2");
    				
    				Chat a = new Chat(id, usu1, usu2);
    				
    				lista.add(a);
    				
    			}
    			cn.close();			
    		}catch(Exception e) {
    			e.printStackTrace();
    		}

    		return lista;
    	}
    	return null;
    }
    
    /**
     * Selección de los chats grupos
     * @param idusu
     * @return
     */
    public ArrayList<Chat> seleccionarGrupo(int idusu) {
    	Connection cn = conectar();	   
    	ArrayList<Chat> lista = new ArrayList<Chat>();
    	
    	if(cn==null) {
    		System.out.println("Conexion Nula");
    	}else {
    		try {
        		PreparedStatement stmtGrupo = cn.prepareStatement("SELECT * FROM pertenece WHERE idnormal=(?)"
        				+ "UNION ALL "
        				+ "SELECT * FROM administra WHERE idadmin= (?)");
        		
        		stmtGrupo.setInt(1, idusu);
        		stmtGrupo.setInt(2, idusu);    			
    			
        		ResultSet rs = stmtGrupo.executeQuery();
        		
        		while(rs.next()) {
        			
        			int usu = rs.getInt("idnormal");
        			int gru = rs.getInt("idgrupo");
        			
        			
        			Chat a = new Chat(usu, gru);
        			
        			lista.add(a);
        		}
        		
        		cn.close();	
        		return lista;
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    		
    		return null;
    	}
    	return null;
    }
    
    /**
     * Selección de chats amigos
     * @param idusu
     * @return
     */
    public ArrayList<Chat> seleccionarAmigos(int idusu) {
    	Connection cn = conectar();	   
    	ArrayList<Chat> lista = new ArrayList<Chat>();
    	
    	if(cn==null) {
    		System.out.println("Conexion Nula");
    	}else {
    		try {
        		PreparedStatement ps = cn.prepareStatement("SELECT * FROM amistad WHERE idusu1 = ?");
        		ps.setInt(1, idusu);
        		
        		ResultSet rs = ps.executeQuery();
        		
        		while(rs.next()) {
        			int aux = rs.getInt(2);
        			
        			PreparedStatement stmt = cn.prepareStatement("SELECT * FROM conversacion WHERE idusu2 = ?");
        			stmt.setInt(1, aux);
        			
        			ResultSet rs2 = stmt.executeQuery();
        			
        			while(rs2.next()) {
        				int chat = rs2.getInt(1);
        				int usu1 = rs2.getInt(2);
        				int usu2 = rs2.getInt(3);
        				
        				Chat a = new Chat(chat, usu1, usu2);
        				
        				lista.add(a);
        			}
        			
        		}
        		
        		cn.close();	
        		return lista;
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    		
    		return null;
    	}
    	return null;
    } 
    
    /**
     * Selección de los mensajes de un chat
     * @param chat
     * @return
     */
    public ArrayList<Mensaje> seleccionarMensajes(int chat ) {
    	ArrayList<Mensaje> lista = new ArrayList<Mensaje>();
    	Connection cn = conectar();	
    	
    	if(cn == null) {
    		System.out.println("Conexion Nula");
    		return null;
    	}else {
    		try {
    			
    			PreparedStatement stmt = cn.prepareStatement("SELECT * FROM mensaje WHERE idchat = (?)");
    			stmt.setInt(1, chat);
    			
    			ResultSet rs = stmt.executeQuery();
    			
    			while(rs.next()) {
    				
    				String txt = rs.getString("texto");
    				Date fecha = rs.getDate("fecha");
    				Time hora = rs.getTime("hora");
    				int idusu = rs.getInt("idusu");
    				int idchat = rs.getInt("idchat");
    				
    				Mensaje a = new Mensaje(txt, fecha, hora, idusu, idchat);
    				
    				lista.add(a);
    			}
    		
    			
	    		cn.close();
	    		return lista;
    		
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
		}
    	
    	return null;
    }
    
    
    /**
     * Buscar un usuario dado el nombre
     * @param nusu
     * @return
     */
    public Usuario buscarUsuxNombre(String nusu) {
    	Connection cn = conectar();	
    	int idusu = 0;
    	String nombre = "";
    	String contra = "";
    	String fecha = "";
    	
    	try {
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				
				PreparedStatement stmt = cn.prepareStatement("SELECT * FROM usuarios WHERE nombre = (?) ");
				stmt.setString(1, nusu);
				
				ResultSet rs = stmt.executeQuery();
				
				//-------------------------------------------------------
				//Puede  haber problema si 2 usuarios tienen mismo nombre
				while(rs.next()) {
					idusu = rs.getInt("idusuario");
					nombre = rs.getString("nombre");
					contra = rs.getString("contrasenya");
					fecha = rs.getString("fechacreacion");
					
					Usuario usua = new Usuario(idusu, nombre, contra, fecha);	
					
					return usua;
				}
				
				cn.close();
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
    }
    
    /**
     * Buscar ID dado un nombre
     * @param idusu
     * @return
     */
	public String buscarNombrexID(int idusu) {
    	Connection cn = conectar();	
		
    	if(cn==null) {
    		System.out.println("Error en conexion");
    	}else {
    		try {
    			PreparedStatement stmt = cn.prepareStatement("SELECT nombre FROM usuarios WHERE idusuario = (?)");
    			stmt.setInt(1, idusu);
    			
    			ResultSet rs = stmt.executeQuery();
    			
    			String n = null;
    			
    			while(rs.next()) {
    				n = rs.getString("nombre");
    			}
    			
    			cn.close();
    			return n;
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
		return null;
	}
    
    /**
     * Buscar nombre del grupo
     * @param idgrupo
     * @return
     */
	public String buscarNombreGrupo(int idgrupo) {
    	Connection cn = conectar();	
    	String nombre = null;
    	
    	try {
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				
				PreparedStatement stmt = cn.prepareStatement("SELECT * FROM grupo WHERE idgrupo = (?) ");
				stmt.setInt(1, idgrupo);
				
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					nombre = rs.getString("nombre");
				}
				
				cn.close();	
				return nombre;
				
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	return null;
	}
	
	/**
	 * Comprobación de administrador de grupo
	 * @param idprincipal
	 * @param idgrupo
	 * @return
	 */
	public boolean comprobarAdminGrupo(int idprincipal, int idgrupo) {
    	Connection cn = conectar();	
		
    	if(cn==null) {
    		System.out.println("Error en conexion");
    	}else {
    		try {
    			
    			PreparedStatement stmt = cn.prepareStatement("SELECT * FROM administra WHERE idgrupo = (?)");
    			stmt.setInt(1, idgrupo);
    			
    			ResultSet rs = stmt.executeQuery();
    			
    			while(rs.next()) {
    				int usu = rs.getInt("idadmin");
    				
    				if(usu==idprincipal) {
    					cn.close();
    					return true;
    				}
    				
    			}
    			
    			cn.close();
    			return false;
    			
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
		
		return false;
	}
   
	/**
	 * Eliminar una conversación
	 * @param idchat
	 * @return
	 */
	public boolean borrarConver(int idchat) {
		Connection cn = conectar();	
		
		try {
			
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				
				PreparedStatement ps = cn.prepareStatement("DELETE FROM conversacion WHERE idconver = ?");
				ps.setInt(1, idchat);
				
				int aux = ps.executeUpdate();
				
				System.err.println("-_-_-_-_-_-_-_-_-_-_-");
				System.out.println(aux);
				System.err.println("-_-_-_-_-_-_-_-_-_-_-");
				
				if(aux==0) {
					cn.close();
					return false;
				}else {
					cn.close();
					return true;
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Eliminar participación de un grupo
	 * @param idchat
	 * @param idusu
	 * @return
	 */
	public boolean borrarParticipante(int idchat, int idusu) {
		Connection cn = conectar();	
		
		try {
			
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				
				PreparedStatement ps = cn.prepareStatement("DELETE FROM pertenece WHERE idgrupo = ? AND idnormal = ?");
				ps.setInt(1, idchat);
				ps.setInt(2, idusu);
				
				int aux = ps.executeUpdate();
				
				System.err.println("-_-_-_-_-_-_-_-_-_-_-");
				System.out.println(aux);
				System.err.println("-_-_-_-_-_-_-_-_-_-_-");
				
				if(aux==0) {
					cn.close();
					return false;
				}else {
					cn.close();
					return true;
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
    /**
     * Comprobar si usuario es administrador
     * @param idUsu
     * @return
     */
    public boolean comprobarAdmin(int idUsu) {
		boolean EsAdmin = false;
		int cont = 0;
		Connection cn = conectar();	
		
		try {
			
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				PreparedStatement stmt = cn.prepareStatement("SELECT * FROM administradores WHERE idadmin=(?)");
				
				stmt.setInt(1, idUsu);
			
				ResultSet rs = stmt.executeQuery();
				
				
				while(rs.next()) {
					cont++;
				}
			
				if(cont<=0) {
					EsAdmin =false;
				}else if(cont==1) {
					EsAdmin = true;
				}else {
					System.err.println("Hay mas de una consulta");
					EsAdmin = false;
				}
				cn.close();
			}
			
    	}catch(Exception e){
            e.printStackTrace();
        }
	
		return EsAdmin;
	}
    
    /**
     * Inserción amistad  
     * @param iDUsuario
     * @param amigo
     */
	public void insertarAmistad(int iDUsuario, int amigo) {
		Connection cn = conectar();	
		
		try {
			
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				PreparedStatement ps = cn.prepareStatement("INSERT INTO amistad VALUES (?, ?)");
				ps.setInt(1, iDUsuario);
				ps.setInt(2, amigo);
			
				ps.executeUpdate();
				cn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Comprobación chat es grupo
	 * @param idchat
	 * @return
	 */
	public boolean comprobarGrupo(int idchat) {
		Connection cn = conectar();	
		
		try {
			
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				PreparedStatement ps = cn.prepareStatement("SELECT COUNT(*) FROM grupo WHERE idgrupo = ?");
				
				ps.setInt(1, idchat);
				
				ResultSet rs = ps.executeQuery();
				
				int count = 0;
				while(rs.next()) {
					count = rs.getInt(1);
				}
				cn.close();
				
				if(count==1) { 
					return true; 
				}else {
					return false;
				}

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Añadir participante a grupo
	 * @param usu
	 * @param chat
	 */
	public void anyadirAGrupoNormal(int usu, int chat) {
		Connection cn = conectar();	
		
		try {
			
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				//Usuario y Grupo
				PreparedStatement ps = cn.prepareStatement("INSERT INTO pertenece VALUES(?, ?)");
				
				ps.setInt(1, usu);
				ps.setInt(2, chat);
				
				ps.executeUpdate();
				cn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Añadir administrador a grupo
	 * @param usu
	 * @param chat
	 */
	public void anyadirAGrupoAdmin(int usu, int chat) {
		Connection cn = conectar();	
		
		try {
			
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				//Usuario y Grupo
				PreparedStatement ps = cn.prepareStatement("INSERT INTO administra VALUES(?, ?)");
				
				ps.setInt(1, usu);
				ps.setInt(2, chat);
				
				ps.executeUpdate();
				cn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * Eliminar un chat grupo 
	 * @param idchat
	 */
	public void eliminarGrupo(int idchat) {
		Connection cn = conectar();	
		
		try {
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				cn.setAutoCommit(false);
				
				PreparedStatement ps = cn.prepareStatement("DELETE FROM administra WHERE idgrupo = ?");
				ps.setInt(1, idchat);
				
				PreparedStatement stmt = cn.prepareStatement("DELETE FROM chat WHERE idchat = ?");
				stmt.setInt(1, idchat);
				
				PreparedStatement stmt2 = cn.prepareStatement("DELETE FROM grupo WHERE idgrupo = ?");
				stmt2.setInt(1, idchat);
				
				ps.executeUpdate();
				stmt.executeUpdate();
				stmt2.executeUpdate();
				
				cn.setAutoCommit(true);
				cn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Vacíar un chat de mensajes
	 * @param idchat
	 */
	public void eliminarMensajes(int idchat) {
		Connection cn = conectar();	
		
		try {
			
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				//Usuario y Grupo
				PreparedStatement ps = cn.prepareStatement("DELETE FROM mensaje WHERE idchat = ?");
				ps.setInt(1, idchat);

				ps.executeUpdate();
				cn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Selección de id máximo usuario.
	 * @return
	 */
	public int selectMaxUsuario() {
		Connection cn = conectar();	
		
		try {
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				PreparedStatement ps = cn.prepareStatement("SELECT MAX(idusuario) FROM usuarios");
				
				ResultSet rs = ps.executeQuery();
				
				int count = 0;
				while(rs.next()) {
					count = rs.getInt(1);
					count = count+1;
					return count;
				}

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Selección de id máximo chat.
	 * @return
	 */
	public int selectMaxChat() {
		Connection cn = conectar();	
		
		try {
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				PreparedStatement ps = cn.prepareStatement("SELECT MAX(idchat) FROM chat");
				
				ResultSet rs = ps.executeQuery();
				
				int count = 0;
				while(rs.next()) {
					count = rs.getInt(1);
					count = count+1;
					cn.close();
					return count;
				}

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Metodo en desuso
	 * @return
	 */
	public ArrayList<Usuario> seleccionarUsuAdmins() {
		Connection cn = conectar();	
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		
		try {
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				PreparedStatement ps = cn.prepareStatement("SELECT idusuario, nombre FROM usuarios, administradores "
						+ "WHERE idusuario = idadmin;");
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					int id = rs.getInt(1);
					String n = rs.getString(2);
					
					Usuario a = new Usuario(id, n);
					
					lista.add(a);
				}
				
				cn.close();
				return lista;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo en desuso
	 * @return
	 */
	public ArrayList<Usuario> seleccionarUsuNormales() {
		Connection cn = conectar();	
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		
		try {
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				PreparedStatement ps = cn.prepareStatement("SELECT idusuario, nombre FROM usuarios, normales "
						+ "WHERE idusuario = idnormal;");
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					int id = rs.getInt(1);
					String n = rs.getString(2);
					
					Usuario a = new Usuario(id, n);
					
					lista.add(a);
				}
				
				cn.close();
				return lista;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Eliminar administrador de grupo
	 * @param iDUsuario
	 * @param idchat
	 */
	public void borrarAdministrador(int iDUsuario, int idchat) {
		Connection cn = conectar();	
		
		try {
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				PreparedStatement ps = cn.prepareStatement("DELETE FROM administra WHERE idadmin = ? and idgrupo = ? ");
				ps.setInt(1, iDUsuario);
				ps.setInt(2, idchat);
				
				ps.executeUpdate();
				
				cn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Eliminar un usuario
	 * @param usu
	 */
	public void borrarUsuario(int usu) {
		Connection cn = conectar();	
		
		/*	DELETE FROM pertenece WHERE idnormal=9;
			DELETE FROM usuarios WHERE idusuario=9;
		 *  DELETE FROM mensajes WHERE idusu=9;
		 * 
		 */
		
		try {
			if (cn == null) {
			    System.out.println("Conexión nula");
			} else {
				cn.setAutoCommit(false);
				
				PreparedStatement ps = cn.prepareStatement("DELETE FROM pertenece WHERE idnormal = ?");
				ps.setInt(1, usu);
				ps.executeUpdate();
				
				PreparedStatement ps2 = cn.prepareStatement("DELETE FROM mensaje WHERE idusu = ?");
				ps2.setInt(1, usu);
				ps2.executeUpdate();
				
				PreparedStatement ps3 = cn.prepareStatement("DELETE FROM usuarios WHERE idusuario = ?");
				ps3.setInt(1, usu);
				ps3.executeUpdate();
				
				
				cn.setAutoCommit(true);
				cn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}









   
