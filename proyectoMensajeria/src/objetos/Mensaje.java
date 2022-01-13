package objetos;

import java.sql.Date;
import java.sql.Time;

public class Mensaje {
	private String txt;
	private Date fecha;
	private Time hora;
	private int idusu;
	private int idchat;
	
	/**
	 * Contructor de la clase mensaje
	 */
	public Mensaje() {
		this.txt = null;
		this.fecha = null;
		this.hora = null;
		this.idusu = 0;
		this.idchat = 0;
	}

	/**
	 * Contrucotr con parametros de la clase mensaje
	 * @param txt
	 * @param fecha
	 * @param hora
	 * @param idusu
	 * @param idchat
	 */
	public Mensaje(String txt, Date fecha, Time hora, int idusu, int idchat) {
		this.txt = txt;
		this.fecha = fecha;
		this.hora = hora;
		this.idusu = idusu;
		this.idchat = idchat;
	}



	public String getTxt() {
		return txt;
	}



	public void setTxt(String txt) {
		this.txt = txt;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public Time getHora() {
		return hora;
	}



	public void setHora(Time hora) {
		this.hora = hora;
	}



	public int getIdusu() {
		return idusu;
	}



	public void setIdusu(int idusu) {
		this.idusu = idusu;
	}



	public int getIdchat() {
		return idchat;
	}



	public void setIdchat(int idchat) {
		this.idchat = idchat;
	}
	
	
}
