/**
 * 
 */
package com.justmediagroup.es.usuario.controller.impl;

import java.util.Base64;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.justmediagroup.es.usuario.controller.contract.IUsuarioController;
import com.justmediagroup.es.usuario.service.contract.IUsuarioSvc;
import com.justmediagroup.es.usuario.utils.EntityServiceValidator;
import com.justmediagroup.logica_comun.constantes.MensajeDelSistema;
import com.justmediagroup.logica_comun.exception.BusinessException;
import com.justmediagroup.logica_comun.utilitarios.DataValidator;
import com.justmediagroup.logica_comun.utilitarios.RespuestaType;
import com.justmediagroup.modelo_canonico.IntentosLoginType;
import com.justmediagroup.modelo_canonico.TransaccionesType;
import com.justmediagroup.modelo_canonico.UsuarioType;
import com.justmediagroup.modelo_canonico.ValoresConfigurablesType;

/**
 * @author jesus
 *
 */
@RestController
public class UsuarioControllerImpl implements IUsuarioController {

	private static final Logger LOG = LoggerFactory.getLogger(UsuarioControllerImpl.class);

	private static final String separador = "=========================================================="
			+ "=======================================================================================";

	@Autowired
	IUsuarioSvc usuarioService;

	@Override
	public ResponseEntity<Object> crearUsuario(@Valid UsuarioType usuario) {
		LOG.info(separador);
		ResponseEntity<Object> respuesta;
		try {
			LOG.info("INICIA CAPACIDAD DE CREAR USUARIO");
			UsuarioType usuarioType;
			usuarioType = usuarioService.crearUsuario(usuario);
			respuesta = EntityServiceValidator.validarResultadoBYCreated(usuarioType);
		} catch (BusinessException ex) {
			LOG.info("ERROR DE NEGOCIO", ex.getMessage());
			respuesta = DataValidator.validarResultado(ex);
		} catch (Exception e) {
			LOG.info("EXCEPCION " + e.getMessage());
			respuesta = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CAPACIDAD DE CREAR USUARIO");
		LOG.info(separador);
		return respuesta;
	}

	@Override
	public ResponseEntity<Object> modificarUsuario(@Valid UsuarioType usuario) {
		LOG.info(separador);
		ResponseEntity<Object> respuesta;
		try {
			LOG.info("INICIA CAPACIDAD DE MODIFICAR USUARIO");
			UsuarioType usuarioType;
			usuarioType = usuarioService.modificarUsuario(usuario);
			respuesta = EntityServiceValidator.validarResultadoByUpdate(usuarioType);
		} catch (BusinessException ex) {
			LOG.info("ERROR DE NEGOCIO" + ex.getMessage());
			respuesta = DataValidator.validarResultado(ex);
		} catch (Exception e) {
			LOG.info("EXCEPCION " + e.getMessage());
			respuesta = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CAPACIDAD DE MODIFICAR USUARIO");
		LOG.info(separador);
		return respuesta;
	}

	@Override
	public ResponseEntity<Object> consultarTransaccionesPorUsuario(String usuarioId, String enlace) {
		LOG.info(separador);
		ResponseEntity<Object> respuesta;
		try {
			LOG.info("INICIA CAPACIDAD DE CONSULTAR TRANSACCIONES POR USUARIO : " + usuarioId + " " + enlace);
			TransaccionesType transaccion;
			byte[] bytesDecodificados = Base64.getDecoder().decode(enlace);
			String cadenaDecodificada = new String(bytesDecodificados);
			transaccion = usuarioService.consultarTransaccionPorUsuario(usuarioId, cadenaDecodificada);
			respuesta = EntityServiceValidator.validarResultado(transaccion);
		} catch (BusinessException e) {
			LOG.info("ERROR DE NEGOCIO: " + e.getMessage());
			respuesta = DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("EXCEPCION: " + e.getMessage());
			respuesta = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CAPACIDAD DE CONSULTAR TRANSACCIONES POR USUARIO");
		LOG.info(separador);
		return respuesta;
	}

	@Override
	public ResponseEntity<Object> guardarTransaccionDeUsuario(@Valid TransaccionesType transaccion) {
		LOG.info(separador);
		ResponseEntity<Object> respuesta;
		try {
			LOG.info("INICIA CAPACIDAD DE GUARDAR TRANSACCION DE USUARIO");
			TransaccionesType transaccionType;
			transaccionType = usuarioService.crearTransaccion(transaccion);
			respuesta = EntityServiceValidator.validarResultadoByCreated(transaccionType);
		} catch (BusinessException e) {
			LOG.info("ERROR DE NEGOCIO: " + e.getMessage());
			respuesta = DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("EXCEPCION: " + e.getMessage());
			respuesta = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CAPACIDAD DE GUARDAR TRANSACCION DE USUARIO");
		LOG.info(separador);
		return respuesta;
	}

	@Override
	public ResponseEntity<Object> modificarTransaccionDeUsuario(@Valid TransaccionesType transaccion) {
		LOG.info(separador);
		ResponseEntity<Object> respuesta;
		try {
			LOG.info("INICIA CAPACIDAD DE MODIFICAR TRANSACCION DE USUARIO");
			TransaccionesType transaccionType;
			transaccionType = usuarioService.modificarTransaccion(transaccion);
			respuesta = EntityServiceValidator.validarResultadoByUpdate(transaccionType);
		} catch (BusinessException e) {
			LOG.info("ERROR DE NEGOCIO: " + e.getMessage());
			respuesta = DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("EXCEPCION: " + e.getMessage());
			respuesta = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CAPACIDAD DE MODIFCAR TRANSACCION DE USUARIO");
		LOG.info(separador);
		return respuesta;
	}

	@Override
	public ResponseEntity<Object> consultarValoresConfigurablesPorNombre(String nombre) {
		LOG.info(separador);
		ResponseEntity<Object> respuesta;
		try {
			LOG.info("INICIA CAPACIDAD DE CONSULTAR VALORES CONFIGURABLES");
			ValoresConfigurablesType valoresConfigurablesType;
			valoresConfigurablesType = usuarioService.consultarValorConfigurable(nombre);
			respuesta = EntityServiceValidator.validarResultado(valoresConfigurablesType);
		} catch (BusinessException e) {
			LOG.info("ERROR DE NEGOCIO: " + e.getMessage());
			respuesta = DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("EXCEPCION: " + e.getMessage());
			respuesta = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CAPACIDAD DE CONSULTAR VALORES CONFIGURABLES");
		LOG.info(separador);
		return respuesta;
	}

	@Override
	public ResponseEntity<Object> crearValorConfigurable(ValoresConfigurablesType valorConfigurable) {
		LOG.info(separador);
		ResponseEntity<Object> respuesta;
		try {
			LOG.info("INICIA CAPACIDAD DE CREAR VALORES CONFIGURABLES");
			ValoresConfigurablesType valoresConfigurablesType;
			valoresConfigurablesType = usuarioService.crearValorConfigurable(valorConfigurable);
			respuesta = EntityServiceValidator.validarResultadoByCreated(valoresConfigurablesType);
		} catch (BusinessException e) {
			LOG.info("ERROR DE NEGOCIO: " + e.getMessage());
			respuesta = DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("EXCEPCION: " + e.getMessage());
			respuesta = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CAPACIDAD DE CREAR VALORES CONFIGURABLES");
		LOG.info(separador);
		return respuesta;
	}

	@Override
	public ResponseEntity<Object> consultarIntentosLoginPorCorreo(String correo) {
		LOG.info(separador);
		ResponseEntity<Object> respuesta;
		try {
			LOG.info("INICIA CAPACIDAD DE CONSULTAR INTENTOS LOGIN -> {}", correo);
			List<IntentosLoginType> usuarioType;
			usuarioType = usuarioService.consultarIntentosLoginPorCorreo(correo);
			respuesta = EntityServiceValidator.validarResultado(usuarioType);
		} catch (BusinessException ex) {
			LOG.error("EXCEPCION DE NEGOCIO {}", ex.fillInStackTrace());
			respuesta = DataValidator.validarResultado(ex);
		} catch (Exception e) {
			LOG.error("EXCEPCION EN CAPACIDAD CONSULTAR USUARIO POR EMAIL {}", e.getMessage());
			respuesta = new ResponseEntity<Object>(
					new RespuestaType().codigoRespuesta("500").descripcion("ERROR INTERNO"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CAPACIDAD DE CONSULTAR USUARIO POR EMAIL");
		LOG.info(separador);
		return respuesta;
	}

	@Override
	public ResponseEntity<Object> modificarIntentosLoginUsuario(@Valid IntentosLoginType intentosLoginType) {
		LOG.info(separador);
		ResponseEntity<Object> respuesta;
		try {
			LOG.info("INICIA CAPACIDAD DE MODIFICAR INTENTOS LOGIN");
			IntentosLoginType intentosLogin;
			intentosLogin = usuarioService.modificarIntentosLoginUsuario(intentosLoginType);
			respuesta = EntityServiceValidator.validarResultadoByUpdate(intentosLogin);
		} catch (BusinessException e) {
			LOG.info("ERROR DE NEGOCIO: " + e.getMessage());
			respuesta = DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("EXCEPCION: " + e.getMessage());
			respuesta = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CAPACIDAD DE MODIFCAR TRANSACCION DE USUARIO");
		LOG.info(separador);
		return respuesta;
	}

	@Override
	public ResponseEntity<Object> guardarIntentosLoginDeUsuario(@Valid IntentosLoginType intentosLoginType) {
		LOG.info(separador);
		ResponseEntity<Object> respuesta;
		try {
			LOG.info("INICIA CAPACIDAD DE CREAR INTENTOS LOGIN");
			IntentosLoginType intentos;
			intentos = usuarioService.guardarIntentosLoginDeUsuario(intentosLoginType);
			respuesta = EntityServiceValidator.validarResultadoByCreated(intentos);
		} catch (BusinessException e) {
			LOG.info("ERROR DE NEGOCIO: " + e.getMessage());
			respuesta = DataValidator.validarResultado(e);
		} catch (Exception e) {
			LOG.error("EXCEPCION: " + e.getMessage());
			respuesta = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CAPACIDAD DE CREAR INTENTOS LOGIN");
		LOG.info(separador);
		return respuesta;
	}

	@Override
	public ResponseEntity<Object> consultarUsuarioPorEmail(String correo, String contrasenia) {
		LOG.info(separador);
		ResponseEntity<Object> respuesta;
		try {
			LOG.info("INICIA CAPACIDAD DE CONSULTAR USUARIO POR EMAIL -> {}", correo);
			UsuarioType usuarioType;
			usuarioType = usuarioService.consultarUsuarioPorEmail(correo, contrasenia);
			respuesta = EntityServiceValidator.validarResultado(usuarioType);
		} catch (BusinessException ex) {
			LOG.error("EXCEPCION DE NEGOCIO {}", ex.fillInStackTrace());
			respuesta = DataValidator.validarResultado(ex);
		} catch (Exception e) {
			LOG.error("EXCEPCION EN CAPACIDAD CONSULTAR USUARIO POR EMAIL {}", e.getMessage());
			respuesta = new ResponseEntity<Object>(
					new RespuestaType().codigoRespuesta("500").descripcion("ERROR INTERNO"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CAPACIDAD DE CONSULTAR USUARIO POR EMAIL");
		LOG.info(separador);
		return respuesta;
	}

}
