package com.justmediagroup.es.usuario.repository.model;

import java.io.Serializable;
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

/**
 * @author jesus
 *
 */

@Entity(name = "tb_usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idUsuario")
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID id;

	@Column(name = "nombreCompleto", nullable = false, length = 200)
	String nombreCompleto;

	@Column(name = "correo", nullable = false, length = 200)
	String correo;

	@Column(name = "password", nullable = false)
	String password;

	@Column(name = "estadoUsuario")
	@Enumerated(EnumType.STRING)
	Estado estadoUsuario;

	@Column(name = "fechaCreacion", nullable = false)
	Date fechaCreacion;

	@Column(name = "fechaModificacion", nullable = false)
	Date fechaModificacion;

	@Column(name = "intentosLogin", nullable = false)
	int intentosLogin;

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
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the estadoUsuario
	 */
	public Estado getEstadoUsuario() {
		return estadoUsuario;
	}

	/**
	 * @param estadoUsuario the estadoUsuario to set
	 */
	public void setEstadoUsuario(Estado estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
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
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return the intentosLogin
	 */
	public int getIntentosLogin() {
		return intentosLogin;
	}

	/**
	 * @param intentosLogin the intentosLogin to set
	 */
	public void setIntentosLogin(int intentosLogin) {
		this.intentosLogin = intentosLogin;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
