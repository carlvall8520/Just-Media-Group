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
import com.justmediagroup.ms.auditoriausuario.controller.dto.UsuarioDto;
import com.justmediagroup.ms.auditoriausuario.utils.AuditoriaConvert;

@Component
public class ActualizarUsuarioCommand implements ICommand {

	private static final Logger LOG = LoggerFactory.getLogger(ActualizarUsuarioCommand.class);

	@Value("${uri.usuarioes}")
	private String hostService;

	@Override
	public Object execute(IParam parametro) throws BusinessException {
		LOG.info(AuditoriaUsuarioConstans.separador);
		ResponseEntity<RespuestaType> response = null;

		try {
			LOG.info("INICIA COMANDO  ACTUALIZAR USUARIO");
			HttpHeaders header = new HttpHeaders();
			header.set("Content-Type", "application/json");
			UsuarioDto usuario = (UsuarioDto) parametro;
			HttpEntity<UsuarioDto> request = new HttpEntity<>(usuario, header);
			RestTemplate restTemplate = new RestTemplate();

			response = restTemplate.exchange(hostService.concat(AuditoriaUsuarioConstans.ACTUALIZAR_USUARIO),
					HttpMethod.PUT, request, RespuestaType.class);

			LOG.info("SERVICIO RETORNA CODIGO HTTP: " + response.getStatusCodeValue());
			LOG.info("BODY RETORNADO :" + AuditoriaConvert.convertirObjetoAString(response.getBody()));

		} catch (HttpStatusCodeException e) {
			LOG.error("HTTP EXCEPCION COMMANDO ACTUALIZAR USUARIO: {} {}", e.getMessage(), e.getStackTrace());
			LOG.error("ERROR AL CONSUMIR SERVICIO USUARIOES CODIGO ERROR: " + response.getStatusCodeValue() + " "
					+ AuditoriaConvert.convertirObjetoAString(response.getBody()));
			throw new BusinessException(
					String.format(AuditoriaUsuarioConstans.MENSAJE_ERROR_MODIFICAR, "ACTUALIZAR USUARIO"),
					TipoError.FUENTE_DE_DATOS);
		}
		LOG.info("FINALIZA COMANDO ACTUALIZAR USUARIO");
		return response.getBody();
	}

	@Override
	public void undo() {
		throw new UnsupportedOperationException("OPERACION NO SOPORTADA");
	}

}
