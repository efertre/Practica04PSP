package model;

public class Asignatura {

	private String nombre;
	private Integer codigo;
	private Double nota;
	private Integer alumnNumero;
	
	
	public Asignatura(String nombre, Integer codigo, Double nota, Integer alumnNumero) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.nota = nota;
		this.alumnNumero = alumnNumero;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public Integer getAlumnNumero() {
		return alumnNumero;
	}
	public void setAlumnNumero(Integer alumnNumero) {
		this.alumnNumero = alumnNumero;
	}
	
	
	
}
