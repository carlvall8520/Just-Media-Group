package com.justmediagroup.ms.auditoriausuario.controller.dto;

/**
 * @author jesus
 *
 */
public class ResultadoValidacionUsuarioDTO {

	private UsuarioDto usuario;

	private boolean isResetIntentosLogin;

	public boolean isBloquearUsuario;

	private boolean isConcederAcceso;

	private String observacion;

	private boolean isAumentarIntentos;

	/**
	 * @return the isAumentarIntentos
	 */
	public boolean isAumentarIntentos() {
		return isAumentarIntentos;
	}

	/**
	 * @param isAumentarIntentos the isAumentarIntentos to set
	 */
	public void setAumentarIntentos(boolean isAumentarIntentos) {
		this.isAumentarIntentos = isAumentarIntentos;
	}

	/**
	 * @return the isBloquearUsuario
	 */
	public boolean isBloquearUsuario() {
		return isBloquearUsuario;
	}

	/**
	 * @param isBloquearUsuario the isBloquearUsuario to set
	 */
	public void setBloquearUsuario(boolean isBloquearUsuario) {
		this.isBloquearUsuario = isBloquearUsuario;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the isConcederAcceso
	 */
	public boolean isConcederAcceso() {
		return isConcederAcceso;
	}

	/**
	 * @param isConcederAcceso the isConcederAcceso to set
	 */
	public void setConcederAcceso(boolean isConcederAcceso) {
		this.isConcederAcceso = isConcederAcceso;
	}

	/**
	 * @return the usuario
	 */
	public UsuarioDto getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the isResetIntentosLogin
	 */
	public boolean isResetIntentosLogin() {
		return isResetIntentosLogin;
	}

	/**
	 * @param isResetIntentosLogin the isResetIntentosLogin to set
	 */
	public void setResetIntentosLogin(boolean isResetIntentosLogin) {
		this.isResetIntentosLogin = isResetIntentosLogin;
	}

}
