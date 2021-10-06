package com.justmediagroup.es.usuario.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.justmediagroup.logica_comun.constantes.MensajeDelSistema;
import com.justmediagroup.logica_comun.utilitarios.RespuestaType;
import com.justmediagroup.modelo_canonico.IntentosLoginType;
import com.justmediagroup.modelo_canonico.TransaccionesType;
import com.justmediagroup.modelo_canonico.UsuarioType;
import com.justmediagroup.modelo_canonico.ValoresConfigurablesType;

/**
 * @author jesus
 *
 */

public class EntityServiceValidator {

	/* FUNCIONES DE VALIDACION PARA LA ENTIDAD USUARIO */

	public static final ResponseEntity<Object> validarResultado(UsuarioType resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("404").descripcion(MensajeDelSistema.RECURSO_NO_ENCONTRADO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(resultado);
	}

	public static final ResponseEntity<Object> validarResultadoByUpdate(UsuarioType resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("404").descripcion(MensajeDelSistema.RECURSO_NO_ENCONTRADO),
					HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(new RespuestaType().codigoRespuesta("200")
				.descripcion(String.format("RECURSO CON ID [%s] actualizado", resultado.getId().toString())));
	}

	public static final ResponseEntity<Object> validarResultadoBYCreated(UsuarioType resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RespuestaType().codigoRespuesta("201")
				.descripcion(String.format("RECURSO CREADO CON ID [%s]", resultado.getId())), HttpStatus.CREATED);
	}

	/* FUNCIONES DE VALIDACION PARA LA ENTIDAD TRANSACCIONES */
	public static final ResponseEntity<Object> validarResultadoByCreated(TransaccionesType resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RespuestaType().codigoRespuesta("201")
				.descripcion(String.format("RECURSO CREADO CON ID [%s]", resultado.getIdAcceso())), HttpStatus.CREATED);
	}

	public static final ResponseEntity<Object> validarResultadoByUpdate(TransaccionesType resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("404").descripcion(MensajeDelSistema.RECURSO_NO_ENCONTRADO),
					HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(new RespuestaType().codigoRespuesta("200")
				.descripcion(String.format("RECURSO CON ID [%s] ACTUALIZADO", resultado.getIdAcceso())));
	}

	public static final ResponseEntity<Object> validarResultado(TransaccionesType resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("404").descripcion(MensajeDelSistema.RECURSO_NO_ENCONTRADO),
					HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(resultado);
	}

	/* FUNCIONES DE VALIDACION PARA LA ENTIDAD VALORES CONFIGURABLES */
	public static final ResponseEntity<Object> validarResultado(ValoresConfigurablesType resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("404").descripcion(MensajeDelSistema.RECURSO_NO_ENCONTRADO),
					HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(resultado);
	}

	public static final ResponseEntity<Object> validarResultadoByCreated(ValoresConfigurablesType resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RespuestaType().codigoRespuesta("201")
				.descripcion(String.format("RECURSO CREADO CON ID [%s]", resultado.getId())), HttpStatus.CREATED);
	}

	/* FUNCIONES DE VALIDACION PARA LA ENTIDAD INTENTOS LOGIN */
	public static ResponseEntity<Object> validarResultado(List<IntentosLoginType> listado) {
		if (listado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("204").descripcion(MensajeDelSistema.INFO_204),
					HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(listado);
	}

	public static final ResponseEntity<Object> validarResultadoByUpdate(IntentosLoginType resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("404").descripcion(MensajeDelSistema.RECURSO_NO_ENCONTRADO),
					HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(new RespuestaType().codigoRespuesta("200")
				.descripcion(String.format("RECURSO CON ID [%s] actualizado", resultado.getId().toString())));
	}

	public static final ResponseEntity<Object> validarResultadoByCreated(IntentosLoginType resultado) {
		if (resultado == null) {
			return new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RespuestaType().codigoRespuesta("201")
				.descripcion(String.format("RECURSO CREADO CON ID [%s]", resultado.getId())), HttpStatus.CREATED);
	}

}
