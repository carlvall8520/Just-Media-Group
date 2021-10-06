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
import com.justmediagroup.ms.auditoriausuario.controller.dto.TransaccionDto;
import com.justmediagroup.ms.auditoriausuario.utils.AuditoriaConvert;

@Component
public class ActualizarTransaccionCommand implements ICommand {

	private static final Logger LOG = LoggerFactory.getLogger(ActualizarTransaccionCommand.class);

	@Value("${uri.usuarioes}")
	private String hostService;

	@Override
	public Object execute(IParam parametro) throws BusinessException {
		ResponseEntity<RespuestaType> respuesta = null;

		TransaccionDto transaccionDto = (TransaccionDto) parametro;

		try {
			LOG.info("INICIA COMANDO ACTUALIZAR TRANSACCION");
			// DECLARAMOS LOS COMANDOS PARA EL CONSUMO DEL SERVICIO
			HttpHeaders header = new HttpHeaders();
			header.set("Content-Type", "application/json");
			HttpEntity<TransaccionDto> request = new HttpEntity<>(transaccionDto, header);
			RestTemplate restTemplate = new RestTemplate();
		
			respuesta = restTemplate.exchange(hostService.concat(AuditoriaUsuarioConstans.ACTUALIZAR_TRANSACCION),
					HttpMethod.PUT, request, RespuestaType.class);

			LOG.info("SERVICIO RETORNA CODIGO HTTP: " + respuesta.getStatusCodeValue());
			LOG.info("BODY RETORNADO :" + AuditoriaConvert.convertirObjetoAString(respuesta.getBody()));

		} catch (HttpStatusCodeException e) {
			LOG.error("HTTP EXCEPCION COMMAND AL ACTUALIZAR LA TRANSACCION: {} {}", e.getMessage(), e.getStackTrace());
			LOG.error("ERROR AL CONSUMIR SERVICIO USUARIOES CODIGO ERROR: " + respuesta.getStatusCodeValue() + " "
					+ AuditoriaConvert.convertirObjetoAString(respuesta.getBody()));
			throw new BusinessException(
					String.format(AuditoriaUsuarioConstans.MENSAJE_ERROR_CONSULTA, "ACTUALIZAR TRANSACCION"),
					TipoError.FUENTE_DE_DATOS);
		}
		LOG.info("FINALIZA COMANDO ACTUALIZAR TRANSACCION");
		return respuesta.getBody();
	}

	@Override
	public void undo() {
		throw new UnsupportedOperationException("Operacion no soportada");
	}

}
