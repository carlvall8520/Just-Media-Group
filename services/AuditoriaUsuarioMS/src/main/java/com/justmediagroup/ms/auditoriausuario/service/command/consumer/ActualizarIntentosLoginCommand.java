package com.justmediagroup.ms.auditoriausuario.service.command.consumer;

import java.util.List;

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
import com.justmediagroup.modelo_canonico.Estado;
import com.justmediagroup.ms.auditoriausuario.constans.AuditoriaUsuarioConstans;
import com.justmediagroup.ms.auditoriausuario.controller.dto.IntentosLoginDto;
import com.justmediagroup.ms.auditoriausuario.utils.AuditoriaConvert;

@Component
public class ActualizarIntentosLoginCommand implements ICommand {

	private static final Logger LOG = LoggerFactory.getLogger(ActualizarUsuarioCommand.class);

	@Value("${uri.usuarioes}")
	private String hostService;

	@Override
	public Object execute(IParam parametro) throws BusinessException {
		LOG.info(AuditoriaUsuarioConstans.separador);

		ResponseEntity<RespuestaType> response = null;

		try {
			LOG.info("INICIA COMANDO ACTUALIZAR INTENTOS LOGIN");
			HttpHeaders header = new HttpHeaders();
			header.set("Content-Type", "application/json");

			IntentosLoginDto listadoDeIntentos = (IntentosLoginDto) parametro;

			RestTemplate restTemplate = new RestTemplate();

			List<IntentosLoginDto> listaIntentosLogin = listadoDeIntentos.getIntentosLogin();

			for (int i = 0; i < listaIntentosLogin.size(); i++) {

				IntentosLoginDto intentosLogin = new IntentosLoginDto();
				intentosLogin.setCorreoUsuario(listaIntentosLogin.get(i).getCorreoUsuario());
				intentosLogin.setFechaCreacion(listaIntentosLogin.get(i).getFechaCreacion());
				intentosLogin.setId(listaIntentosLogin.get(i).getId());
				// CAMBIAMOS EL ESTADO DEL INTENTO
				intentosLogin.setEstado(Estado.INACTIVO);

				HttpEntity<IntentosLoginDto> request = new HttpEntity<>(intentosLogin, header);
				response = restTemplate.exchange(hostService.concat(AuditoriaUsuarioConstans.ACTUALIZAR_INTENTOS_LOGIN),
						HttpMethod.PUT, request, RespuestaType.class);

				LOG.info("SERVICIO RETORNA CODIGO HTTP: " + response.getStatusCode());
				LOG.info("BODY RETORNADO :" + AuditoriaConvert.convertirObjetoAString(response.getBody()));
			}

		} catch (HttpStatusCodeException e) {
			LOG.error("HTTP EXCEPCION COMMANDO ACTUALIZAR INTENTOS LOGIN: {} {}", e.getMessage(), e.getStackTrace());
			LOG.error("ERROR AL CONSUMIR SERVICIO ACTUALIZAR INTENTOS LOGIN: " + response.getStatusCodeValue() + " "
					+ AuditoriaConvert.convertirObjetoAString(response.getBody()));
			throw new BusinessException(
					String.format(AuditoriaUsuarioConstans.MENSAJE_ERROR_MODIFICAR, "ACTUALIZAR INTENTOS LOGIN"),
					TipoError.FUENTE_DE_DATOS);
		}
		LOG.info("FINALIZA COMANDO ACTUALIZAR USUARIO");
		return response.getBody();
	}

	@Override
	public void undo() {
		throw new UnsupportedOperationException("Operacion no soportada");
	}

}
