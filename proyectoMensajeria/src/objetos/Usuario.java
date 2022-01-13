package objetos;

public class Usuario{
	private int idusu;
	private String nombre;
	private String contrasenya;
	private String fecha;
	
	/**
	 * Contructor de la clase Usuario
	 */
	public Usuario() {
		this.idusu = 0;
		this.nombre = "";
		this.contrasenya = "";
		this.fecha = "";
	}

	public Usuario(int idusu, String nombre, String contrasenya) {
		this.idusu = idusu;
		this.nombre = nombre;
		this.contrasenya = contrasenya;
		this.fecha = "";
	}
	
	/**
	 * Contructor con parámetros de la clase Usuario
	 * @param idusu
	 * @param nombre
	 * @param contrasenya
	 * @param fecha
	 */
	public Usuario(int idusu, String nombre, String contrasenya, String fecha) {
		this.idusu = idusu;
		this.nombre = nombre;
		this.contrasenya = contrasenya;
		this.fecha = fecha;
	}
	
	public Usuario(int idusu, String nombre){
		this.idusu = idusu;
		this.nombre = nombre;
		this.contrasenya = "";
		this.fecha = "";
	}
	
	//---------------------------------
	
	public int getIdusu() {
		return idusu;
	}
	public void setIdusu(int idusu) {
		this.idusu = idusu;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
}
