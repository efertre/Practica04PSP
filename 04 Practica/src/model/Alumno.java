package model;

import java.time.LocalDate;

public class Alumno {
	
	private Integer numero;
	private String usuario;
	private String contrasenia;
	private LocalDate fechaNac;
	private Double notaMedia;
	private byte[] img;
	
	
	
	public Alumno(Integer numero, String usuario, String contrasenia, LocalDate fechaNac, Double notaMedia, byte[] img) {
		super();
		this.numero = numero;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.fechaNac = fechaNac;
		this.notaMedia = notaMedia;
		this.img = img;
	}
	
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public LocalDate getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}
	public Double getNotaMedia() {
		return notaMedia;
	}
	public void setNotaMedia(Double notaMedia) {
		this.notaMedia = notaMedia;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	
	

}
