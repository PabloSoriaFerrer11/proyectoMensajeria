package objetos;

public class Chat {
	private int idchat;
	private int idusu1;
	private int idusu2;
	private String nombre;
	
	/**
	 * Contructor de la clase Chat
	 */
	public Chat() {
		this.idchat = 0;
		this.idusu1 = 0;
		this.idusu2 = 0;
		this.nombre = "";
	}
	
	/**
	 * Contructor con parámetros de la clase Chat
	 * @param idchat
	 * @param idusu1
	 * @param idusu2
	 */
	public Chat(int idchat, int idusu1, int idusu2) {
		this.idchat = idchat;
		this.idusu1 = idusu1;
		this.idusu2 = idusu2;
	}

	public Chat(int idchat, String nombre) {
		this.idchat = idchat;
		this.nombre = nombre;
	}
	
	public Chat(int idusu, int idgrupo) {
		this.idusu1=idusu;
		this.idchat=idgrupo;
	}
	
	//_-----------------------------------------
	
	public int getIdchat() {
		return idchat;
	}

	public void setIdchat(int idchat) {
		this.idchat = idchat;
	}

	public int getIdusu1() {
		return idusu1;
	}

	public void setIdusu1(int idusu1) {
		this.idusu1 = idusu1;
	}

	public int getIdusu2() {
		return idusu2;
	}

	public void setIdusu2(int idusu2) {
		this.idusu2 = idusu2;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
