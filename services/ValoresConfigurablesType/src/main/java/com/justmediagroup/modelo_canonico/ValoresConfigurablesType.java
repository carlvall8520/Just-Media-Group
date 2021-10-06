package com.justmediagroup.modelo_canonico;

import java.util.UUID;

public class ValoresConfigurablesType {

	private UUID id;

	private String nombre;

	private String valorDefecto;

	private String descripcion;

	private EstadoValorConfigurable estado;

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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the valorDefecto
	 */
	public String getValorDefecto() {
		return valorDefecto;
	}

	/**
	 * @param valorDefecto the valorDefecto to set
	 */
	public void setValorDefecto(String valorDefecto) {
		this.valorDefecto = valorDefecto;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the estado
	 */
	public EstadoValorConfigurable getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoValorConfigurable estado) {
		this.estado = estado;
	}

}
