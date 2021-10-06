package com.justmediagroup.ms.auditoriausuario.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.justmediagroup.logica_comun.exception.BusinessException;
import com.justmediagroup.logica_comun.utilitarios.DataValidator;
import com.justmediagroup.logica_comun.utilitarios.RespuestaType;
import com.justmediagroup.ms.auditoriausuario.constans.AuditoriaUsuarioConstans;
import com.justmediagroup.ms.auditoriausuario.controller.contract.IAuditoriaUsuarioController;
import com.justmediagroup.ms.auditoriausuario.controller.dto.TransaccionDto;
import com.justmediagroup.ms.auditoriausuario.controller.dto.UsuarioDto;
import com.justmediagroup.ms.auditoriausuario.service.contract.IAuditoriaUsuarioSvc;
import com.justmediagroup.ms.auditoriausuario.utils.AuditoriaUsuarioValidator;

@RestController
public class AuditoriaUsuarioControllerImpl implements IAuditoriaUsuarioController {

	private static Logger LOG = LoggerFactory.getLogger(AuditoriaUsuarioControllerImpl.class);

	@Autowired
	IAuditoriaUsuarioSvc auditoriaUsuarioService;

	@Override
	public ResponseEntity<Object> verificarTransaccionUsuario(String enlace, String usuarioId, String sistema,
			String versionSistemaOperativo, String versionDelNavegador, String canal) {
		LOG.info(AuditoriaUsuarioConstans.separador);
		LOG.info("INICIA CAPACIDAD DE LOGUEAR USUARIO");
		LOG.info("ENLACE  : " + enlace);
		LOG.info("USUARIO  : " + usuarioId);
		LOG.info("SISTEMA : " + sistema);
		LOG.info("VERSION SISTEMA OPERATIVO : " + versionSistemaOperativo);
		LOG.info("VERSION DEL NAVEGADOR : " + versionDelNavegador);
		LOG.info("CANAL : " + canal);
		LOG.info(AuditoriaUsuarioConstans.separador);
		LOG.info(AuditoriaUsuarioConstans.INICIA_CAPACIDAD_LOGUEAR_USUARIO);
		ResponseEntity<Object> respuesta = null;
		try {
			LOG.info("ENLACE  : " + usuarioId);
			LOG.info("SISTEMA : " + sistema);
			LOG.info("VERSION SISTEMA OPERATIVO : " + versionSistemaOperativo);
			LOG.info("VERSION DEL NAVEGADOR : " + versionDelNavegador);
			LOG.info("CANAL : " + canal);

			TransaccionDto transaccion;
			transaccion = (TransaccionDto) auditoriaUsuarioService.verificarTransaccionUsuario(enlace, usuarioId,
					sistema, versionSistemaOperativo, versionDelNavegador, canal);

			respuesta = AuditoriaUsuarioValidator.validarResultado(transaccion);

		} catch (BusinessException ex) {
			LOG.error("EXCEPCION DE NEGOCIO {}", ex.fillInStackTrace());
			respuesta = DataValidator.validarResultado(ex);
		} catch (Exception e) {
			LOG.error("EXCEPCION EN CAPACIDAD DE LOGUEAR USUARIO {}", e.getMessage());
			respuesta = new ResponseEntity<Object>(
					new RespuestaType().codigoRespuesta("500").descripcion("ERROR INTERNO"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info(AuditoriaUsuarioConstans.FINALIZA_CAPACIDAD_LOGUEAR_USUARIO);
		LOG.info(AuditoriaUsuarioConstans.separador);
		return respuesta;
	}

	@Override
	public ResponseEntity<Object> logearUsuario(String correo, String password, String sistema,
			String versionSistemaOperativo, String versionDelNavegador, String canal) {
		LOG.info(AuditoriaUsuarioConstans.separador);
		LOG.info(AuditoriaUsuarioConstans.INICIA_CAPACIDAD_LOGUEAR_USUARIO);
		ResponseEntity<Object> respuesta;
		try {
			LOG.info("CORREO  : " + correo);
			LOG.info("SISTEMA : " + sistema);
			LOG.info("VERSION SISTEMA OPERATIVO : " + versionSistemaOperativo);
			LOG.info("VERSION DEL NAVEGADOR : " + versionDelNavegador);
			LOG.info("CANAL : " + canal);

			UsuarioDto usuario;
			usuario = auditoriaUsuarioService.logearUsuario(correo, password, sistema, versionSistemaOperativo,
					versionDelNavegador, canal);

			respuesta = AuditoriaUsuarioValidator.validarResultado(usuario);

		} catch (BusinessException ex) {
			LOG.error("EXCEPCION DE NEGOCIO {}", ex.fillInStackTrace());
			respuesta = DataValidator.validarResultado(ex);
		} catch (Exception e) {
			LOG.error("EXCEPCION EN CAPACIDAD DE LOGUEAR USUARIO {}", e.getMessage());
			respuesta = new ResponseEntity<Object>(
					new RespuestaType().codigoRespuesta("500").descripcion("ERROR INTERNO"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info(AuditoriaUsuarioConstans.FINALIZA_CAPACIDAD_LOGUEAR_USUARIO);
		LOG.info(AuditoriaUsuarioConstans.separador);
		return respuesta;
	}

}
