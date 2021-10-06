package com.justmediagroup.es.usuario.repository.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tb_transaccionesweb")
public class TransaccionesWeb {

	@Id
	@Column(name = "idAcceso")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idAcceso;

	@Column(name = "enlaceWeb", length = 200)
	private String enlaceWeb;

	@Column(name = "cantidadVisitas")
	private int cantidadVisitas;

	@Column(name = "usuarioId")
	private String usuarioId;

	@Column(name = "isPuedeAcceder")
	private boolean isPuedeAcceder;

	/**
	 * @return the idAcceso
	 */
	public UUID getIdAcceso() {
		return idAcceso;
	}

	/**
	 * @param idAcceso the idAcceso to set
	 */
	public void setIdAcceso(UUID idAcceso) {
		this.idAcceso = idAcceso;
	}

	/**
	 * @return the enlaceWeb
	 */
	public String getEnlaceWeb() {
		return enlaceWeb;
	}

	/**
	 * @param enlaceWeb the enlaceWeb to set
	 */
	public void setEnlaceWeb(String enlaceWeb) {
		this.enlaceWeb = enlaceWeb;
	}

	/**
	 * @return the cantidadVisitas
	 */
	public int getCantidadVisitas() {
		return cantidadVisitas;
	}

	/**
	 * @param cantidadVisitas the cantidadVisitas to set
	 */
	public void setCantidadVisitas(int cantidadVisitas) {
		this.cantidadVisitas = cantidadVisitas;
	}

	/**
	 * @return the usuarioId
	 */
	public String getUsuarioId() {
		return usuarioId;
	}

	/**
	 * @param usuarioId the usuarioId to set
	 */
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	/**
	 * @return the isPuedeAcceder
	 */
	public boolean isPuedeAcceder() {
		return isPuedeAcceder;
	}

	/**
	 * @param isPuedeAcceder the isPuedeAcceder to set
	 */
	public void setPuedeAcceder(boolean isPuedeAcceder) {
		this.isPuedeAcceder = isPuedeAcceder;
	}

}
