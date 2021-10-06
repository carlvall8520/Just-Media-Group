package com.justmediagroup.es.usuario.repository.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.justmediagroup.modelo_canonico.Estado;

@Entity(name = "intentos_login")
public class IntentosLogin {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private Estado estado;

	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaCreacion;

	@Column(name = "correoUsuario", nullable = false)
	private String correoUsuario;

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the correoUsuario
	 */
	public String getCorreoUsuario() {
		return correoUsuario;
	}

	/**
	 * @param correoUsuario the correoUsuario to set
	 */
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}

}
