package com.justmediagroup.ms.auditoriausuario.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.justmediagroup.logica_comun.constantes.MensajeDelSistema;
import com.justmediagroup.logica_comun.utilitarios.RespuestaType;
import com.justmediagroup.ms.auditoriausuario.controller.dto.TransaccionDto;
import com.justmediagroup.ms.auditoriausuario.controller.dto.UsuarioDto;

public class AuditoriaUsuarioValidator {

	public static final ResponseEntity<Object> validarResultado(UsuarioDto resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("404").descripcion(MensajeDelSistema.RECURSO_NO_ENCONTRADO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(resultado);
	}

	public static final ResponseEntity<Object> validarResultado(TransaccionDto
			resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("404").descripcion(MensajeDelSistema.RECURSO_NO_ENCONTRADO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(resultado);
	}	
	
}
