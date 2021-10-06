package com.justmediagroup.ms.auditoriausuario.service.command.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.justmediagroup.logica_comun.exception.BusinessException;
import com.justmediagroup.logica_comun.patrones.command.ICommand;
import com.justmediagroup.logica_comun.patrones.command.IParam;
import com.justmediagroup.ms.auditoriausuario.constans.AuditoriaUsuarioConstans;
import com.justmediagroup.ms.auditoriausuario.controller.dto.GenericStringParam;
import com.justmediagroup.ms.auditoriausuario.controller.dto.TransaccionDto;
import com.justmediagroup.ms.auditoriausuario.utils.AuditoriaConvert;

@Component
public class ConsultarTransaccionPorUsuarioIdyEnlace implements ICommand {

	private static Logger LOG = LoggerFactory.getLogger(ConsultarTransaccionPorUsuarioIdyEnlace.class);

	@Value("${uri.usuarioes}")
	private String hostService;

	@Override
	public Object execute(IParam parametro) throws BusinessException {
		LOG.info(AuditoriaUsuarioConstans.separador);
		ResponseEntity<TransaccionDto> response = null;

		GenericStringParam stringParam = (GenericStringParam) parametro;

		try {
			LOG.info("INICIA COMANDO CONSULTAR TRANSACCION POR USUARIO");
			HttpHeaders header = new HttpHeaders();
			header.set("Content-Type", "application/json");
			HttpEntity<Object> request = new HttpEntity<>(header);
			RestTemplate restTemplate = new RestTemplate();
			/*
			 * REALIZAMOS LA PETICION HTTP GET AL SERVICIO USUARIOES A LA CAPACIDAD DE
			 * CONSULTAR TRANSACCIONES POR USUARIO ID Y ENLACE
			 */
			response = restTemplate.exchange(
					hostService.concat(AuditoriaUsuarioConstans.CONSULTAR_TRANSACCION_POR_USUARIOID_ENLACE),
					HttpMethod.GET, request, TransaccionDto.class, stringParam.getValues().get("usuarioId"),
					stringParam.getValues().get("enlace"));

			LOG.info("SERVICIO RETORNA CODIGO HTTP: " + response.getStatusCodeValue());
			LOG.info("BODY RETORNADO :" + AuditoriaConvert.convertirObjetoAString(response.getBody()));

		} catch (HttpStatusCodeException e) {
			LOG.error("HTTP EXCEPCION COMANDO CONSULTAR TRANSACCION POR USUARIO: {} {}", e.getMessage(),
					e.getStackTrace());
			LOG.error(
					"ERROR AL CONSUMIR SERVICIO USUARIOES CODIGO ERROR: " + response.getStatusCodeValue() + " "
					+ AuditoriaConvert.convertirObjetoAString(response.getBody()));
		}
		LOG.info("FINALIZA COMANDO CONSULTAR TRANSACCION POR USUARIO");
		LOG.info(AuditoriaUsuarioConstans.separador);
		return response.getBody();
	}

	@Override
	public void undo() {
		throw new UnsupportedOperationException("Operacion no soportada");
	}

}
