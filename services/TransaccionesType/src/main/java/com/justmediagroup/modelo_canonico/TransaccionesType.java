/**
 * 
 */
package com.justmediagroup.modelo_canonico;

import java.util.UUID;

/**
 * @author jesus
 *
 */
public class TransaccionesType {

	private UUID idAcceso;

	private String enlaceWeb;

	private int cantidadVisitas;

	private boolean isPuedeAcceder;

	private String usuarioId;

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
