package com.justmediagroup.es.usuario.repository.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.justmediagroup.modelo_canonico.EstadoValorConfigurable;

@Entity(name = "tb_valores_configurables")
public class ValoresConfigurables {

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "valor_defecto")
	private String valorDefecto;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
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

}
