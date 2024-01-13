package com.amegdev.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "personas")
public class Persona implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_persona;
	
	@Column(nullable = false, length = 50)
	private String nombres;
	
	@Column(nullable = false, length = 50)
	private String apellidos;
	
	@Column(nullable = false, length = 1)
	private String sexo;
	
	@Column(nullable = false, length = 50)
	private String pais;
	
	@Column(nullable = true, length = 150)
	private String direccion;
	
	@Column(nullable = true)
	private byte[] foto;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "persona")
	private Usuario usuario;
	
	@Transient
	private boolean esSeguido;

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isEsSeguido() {
		return esSeguido;
	}

	public void setEsSeguido(boolean esSeguido) {
		this.esSeguido = esSeguido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_persona);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(id_persona, other.id_persona);
	}

	private static final long serialVersionUID = 1L;
}
