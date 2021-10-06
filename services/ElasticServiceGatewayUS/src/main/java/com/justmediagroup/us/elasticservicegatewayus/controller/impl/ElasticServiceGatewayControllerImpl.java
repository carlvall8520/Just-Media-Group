package com.justmediagroup.us.elasticservicegatewayus.controller.impl;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.justmediagroup.logica_comun.constantes.MensajeDelSistema;
import com.justmediagroup.logica_comun.utilitarios.RespuestaType;
import com.justmediagroup.us.elasticservicegatewayus.controller.contract.IElasticServiceGatewayController;
import com.justmediagroup.us.elasticservicegatewayus.repository.model.DatosAuditoriaUsuario;
import com.justmediagroup.us.elasticservicegatewayus.service.contract.IElasticServiceGatewaySvc;
import com.justmediagroup.us.elasticservicegatewayus.utils.UtilityServiceValidator;

@RestController
public class ElasticServiceGatewayControllerImpl implements IElasticServiceGatewayController {

	private static final String separador = "=========================================================="
			+ "=============";

	private static final Logger LOG = LoggerFactory.getLogger(ElasticServiceGatewayControllerImpl.class);

	@Autowired
	IElasticServiceGatewaySvc service;

	@Override
	public ResponseEntity<Object> crearDatosAuditoriaUsuario(@Valid DatosAuditoriaUsuario datosAuditoria) {
		LOG.info(separador);
		ResponseEntity<Object> respuesta;
		try {
			LOG.info("INICIA CAPACIDAD DE CREAR DATOS AUDITORIA ELASTIC SERVICE");
			DatosAuditoriaUsuario type;
			type = service.crearDatosAuditoriaUsuario(datosAuditoria);
			respuesta = UtilityServiceValidator.validarResultado(type);
		} catch (Exception e) {
			LOG.info("EXCEPCION " + e.getMessage());
			respuesta = new ResponseEntity<>(
					new RespuestaType().codigoRespuesta("500").descripcion(MensajeDelSistema.ERROR_INTERNO),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("FINALIZA CAPACIDAD DE CREAR DATOS AUDITORIA ELASTIC SERVICE");
		LOG.info(separador);
		return respuesta;
	}

}
