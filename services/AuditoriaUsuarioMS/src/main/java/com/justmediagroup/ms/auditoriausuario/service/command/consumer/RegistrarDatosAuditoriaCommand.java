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
import com.justmediagroup.logica_comun.utilitarios.RespuestaType;
import com.justmediagroup.ms.auditoriausuario.constans.AuditoriaUsuarioConstans;
import com.justmediagroup.ms.auditoriausuario.controller.dto.DatosAuditoriaDto;
import com.justmediagroup.ms.auditoriausuario.utils.AuditoriaConvert;

@Component
public class RegistrarDatosAuditoriaCommand implements ICommand {

	private static final Logger LOG = LoggerFactory.getLogger(ActualizarUsuarioCommand.class);

	@Value("${uri.elascticservicegatewayus}")
	private String hostService;

	@Override
	public Object execute(IParam parametro) throws BusinessException {
		LOG.info(AuditoriaUsuarioConstans.separador);
		ResponseEntity<RespuestaType> response = null;

		try {
			LOG.info("INICIA COMANDO ENVIAR DATOS AUDITORIA A SERVICIO DE UTILIDAD");
			HttpHeaders header = new HttpHeaders();
			header.set("Content-Type", "application/json");
			DatosAuditoriaDto datos = (DatosAuditoriaDto) parametro;
			HttpEntity<DatosAuditoriaDto> request = new HttpEntity<>(datos, header);
			RestTemplate restTemplate = new RestTemplate();

			response = restTemplate.exchange(
					hostService.concat(AuditoriaUsuarioConstans.GUARDAR_DATOS_INSTANCIA_ELASTIC), HttpMethod.POST,
					request, RespuestaType.class);

			LOG.info("SERVICIO RETORNA CODIGO HTTP: " + response.getStatusCodeValue());
			LOG.info("BODY RETORNADO :" + AuditoriaConvert.convertirObjetoAString(response.getBody()));

		} catch (HttpStatusCodeException e) {
			LOG.error("HTTP EXCEPCION COMMANDO  ENVIAR DATOS AUDITORIA : {} {}", e.getMessage(), e.getStackTrace());
			LOG.error("ERROR AL CONSUMIR SERVICIO DE UTILIDAD CODIGO ERROR: " + response.getStatusCodeValue() + " "
					+ AuditoriaConvert.convertirObjetoAString(response.getBody()));
		}
		LOG.info("FINALIZA COMANDO ENVIAR DATOS AUDITORIA");
		return response.getBody();
	}

	@Override
	public void undo() {
		throw new UnsupportedOperationException("Operacion no soportada");
	}

}
