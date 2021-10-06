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
import com.justmediagroup.logica_comun.exception.TipoError;
import com.justmediagroup.logica_comun.patrones.command.ICommand;
import com.justmediagroup.logica_comun.patrones.command.IParam;
import com.justmediagroup.logica_comun.utilitarios.RespuestaType;
import com.justmediagroup.ms.auditoriausuario.constans.AuditoriaUsuarioConstans;
import com.justmediagroup.ms.auditoriausuario.controller.dto.IntentosLoginDto;
import com.justmediagroup.ms.auditoriausuario.utils.AuditoriaConvert;

/**
 * @author jesus
 *
 */

@Component
public class CrearIntentosLoginCommand implements ICommand {
	private static final Logger LOG = LoggerFactory.getLogger(ActualizarUsuarioCommand.class);

	@Value("${uri.usuarioes}")
	private String hostService;

	@Override
	public Object execute(IParam parametro) throws BusinessException {
		LOG.info(AuditoriaUsuarioConstans.separador);
		ResponseEntity<RespuestaType> response = null;

		try {
			LOG.info("INICIA COMANDO CREAR INTENTOS LOGIN");
			HttpHeaders header = new HttpHeaders();
			header.set("Content-Type", "application/json");
			IntentosLoginDto intentos = (IntentosLoginDto) parametro;
			HttpEntity<IntentosLoginDto> request = new HttpEntity<>(intentos, header);
			RestTemplate restTemplate = new RestTemplate();

			response = restTemplate.exchange(hostService.concat(AuditoriaUsuarioConstans.CREAR_INTENTOS_LOGIN),
					HttpMethod.POST, request, RespuestaType.class);

			LOG.info("SERVICIO RETORNA CODIGO HTTP: " + response.getStatusCodeValue());
			LOG.info("BODY RETORNADO :" + AuditoriaConvert.convertirObjetoAString(response.getBody()));

		} catch (HttpStatusCodeException e) {
			LOG.error("HTTP EXCEPCION COMMANDO CREAR INTENTOS LOGIN: {} {}", e.getMessage(), e.getStackTrace());
			LOG.error("ERROR AL CONSUMIR SERVICIO CREAR INTENTOS LOGIN CODIGO ERROR: " + response.getStatusCodeValue()
					+ " " + AuditoriaConvert.convertirObjetoAString(response.getBody()));
			throw new BusinessException(
					String.format(AuditoriaUsuarioConstans.MENSAJE_ERROR_CREAR, "CREAR INTENTOS LOGIN"),
					TipoError.FUENTE_DE_DATOS);
		}
		LOG.info("FINALIZA COMANDO CREAR INTENTOS LOGIN");
		return response.getBody();
	}

	@Override
	public void undo() {
		throw new UnsupportedOperationException("OPERACION NO SOPORTADA");
	}
}
