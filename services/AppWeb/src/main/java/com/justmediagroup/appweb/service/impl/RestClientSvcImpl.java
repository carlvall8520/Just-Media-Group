package com.justmediagroup.appweb.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.justmediagroup.appweb.constans.AppWebConstans;
import com.justmediagroup.appweb.dto.GenericStringParam;
import com.justmediagroup.appweb.service.contract.IRestClientSvc;

import com.justmediagroup.logica_comun.patrones.command.IParam;
import com.justmediagroup.modelo_canonico.TransaccionesType;
import com.justmediagroup.modelo_canonico.UsuarioType;

@Service
public class RestClientSvcImpl implements IRestClientSvc {

	private static final Logger LOG = LoggerFactory.getLogger(RestClientSvcImpl.class);

	@Value("${uri.auditoriaservicems}")
	private String hostService;

	@Override
	public UsuarioType logearUsuarioService(IParam parametro) {
		LOG.info(AppWebConstans.separador);

		ResponseEntity<UsuarioType> response = null;

		GenericStringParam genericStringParam = (GenericStringParam) parametro;

		try {
			LOG.info("INICIA CONSUMO DE SERVICIO LOGUEAR USUARIO");
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders header = new HttpHeaders();
			header.set("Content-Type", "application/json");
			header.set(AppWebConstans.PASSWORD,
					genericStringParam.getValues().get(AppWebConstans.PASSWORD));
			header.set(AppWebConstans.SISTEMA,
					genericStringParam.getValues().get(AppWebConstans.SISTEMA));
			header.set(AppWebConstans.VERSION_OS, 
					genericStringParam.getValues().get(AppWebConstans.VERSION_OS));
			header.set(AppWebConstans.VERSION_NAVEGADOR,
					genericStringParam.getValues().get(AppWebConstans.VERSION_NAVEGADOR));
			header.set(AppWebConstans.CANAL, genericStringParam.getValues().get(AppWebConstans.CANAL));

			HttpEntity<Object> request = new HttpEntity<>(header);
			/*
			 * Realizamos la peticion HTTP GET al MICROSERVICIO PARA CONSULTAR EL USUARIO
			 */
			response = restTemplate.exchange(hostService.concat(AppWebConstans.LOGEAR_USUARIO_PORTAL), HttpMethod.GET,
					request, UsuarioType.class, genericStringParam.getValues().get("correo"));

			LOG.info("SERVICIO RETORNA CODIGO HTTP: " + response.getStatusCodeValue());

		} catch (HttpStatusCodeException e) {
			LOG.error("HTTP EXCEPCION AL LOGUEAR USUARIO: {} {}", e.getMessage(), e.getStackTrace());
		}
		LOG.info("FINALIZA CONSUMO DE SERVICIO LOGUEAR USUARIO");
		LOG.info(AppWebConstans.separador);
		return response.getBody();
	}

	@Override
	public TransaccionesType consultarTransaccion(IParam parametro) {
		LOG.info(AppWebConstans.separador);

		ResponseEntity<TransaccionesType> response = null;

		GenericStringParam genericStringParam = (GenericStringParam) parametro;

		try {
			LOG.info("INICIA CONSUMO DE SERVICIO CONSULTAR TRANSACCION");
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders header = new HttpHeaders();
			header.set("Content-Type", "application/json");
			header.set(AppWebConstans.ENLACE,
					genericStringParam.getValues().get(AppWebConstans.ENLACE));
			header.set(AppWebConstans.USUARIO_ID,
					genericStringParam.getValues().get(AppWebConstans.SISTEMA));
			header.set(AppWebConstans.VERSION_OS, 
					genericStringParam.getValues().get(AppWebConstans.VERSION_OS));
			header.set(AppWebConstans.VERSION_NAVEGADOR,
					genericStringParam.getValues().get(AppWebConstans.VERSION_NAVEGADOR));
			header.set(AppWebConstans.CANAL, genericStringParam.getValues().get(AppWebConstans.CANAL));
			
			HttpEntity<Object> request = new HttpEntity<>(header);
			/*
			 * Realizamos la peticion HTTP GET al MICROSERVICIO PARA CONSULTAR EL USUARIO
			 */
			response = restTemplate.exchange(hostService.concat(AppWebConstans.CONSULTAR_TRANSACCION), HttpMethod.GET,
					request, TransaccionesType.class, genericStringParam.getValues().get("correo"),
					genericStringParam.getValues().get("enlace"));

			LOG.info("SERVICIO RETORNA CODIGO HTTP: " + response.getStatusCodeValue());

		} catch (HttpStatusCodeException e) {
			LOG.error("HTTP EXCEPCION COMANDO CONSULTAR TRANSACCION: {} {}", e.getMessage(), e.getStackTrace());
		}
		LOG.info("FINALIZA CONSUMO DE SERVICIO CONSULTAR TRANSACCION");
		LOG.info(AppWebConstans.separador);
		return response.getBody();
	}
}
