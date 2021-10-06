package com.justmediagroup.ms.auditoriausuario.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.justmediagroup.ms.auditoriausuario.constans.AuditoriaUsuarioConstans;
import com.justmediagroup.ms.auditoriausuario.controller.dto.DatosAuditoriaDto;
import com.justmediagroup.ms.auditoriausuario.controller.dto.GenericStringParam;
import com.justmediagroup.ms.auditoriausuario.controller.dto.ValorConfigurableDto;
import com.justmediagroup.ms.auditoriausuario.service.command.business.ObtenerDatosAuditoriaArchivoJsonCommand;
import com.justmediagroup.ms.auditoriausuario.service.command.consumer.ConsultarValorConfigurableCommand;
import com.justmediagroup.ms.auditoriausuario.service.command.consumer.RegistrarDatosAuditoriaCommand;

@Component
public class EnviarDatosAuditoriaJob {

	private static final Logger LOG = LoggerFactory.getLogger(EnviarDatosAuditoriaJob.class);
	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyyy HH:mm:ss");

	@Autowired
	ConsultarValorConfigurableCommand consultarValorConfigurableCommand;

	@Autowired
	ObtenerDatosAuditoriaArchivoJsonCommand obtenerDatosAuditoriaArchivoJsonCommand;

	@Autowired
	RegistrarDatosAuditoriaCommand registrarDatosAuditoriaCommand;

	@SuppressWarnings("unchecked")
	@Scheduled(fixedRate = 100000000)
	public void executeJobEnviarDatosAuditoria() {
		LOG.info(AuditoriaUsuarioConstans.separador);
		LOG.info(AuditoriaUsuarioConstans.INICIA_PROCESO_ENVIAR_DATOS_AUDITORIA);
		LOG.info("FECHA DE INICIO DEL PROCESO: {} ", formatoFecha.format(new Date()));

		// CONSULTAMOS RUTA DE UBICACION DEL ARCHIVO JSON
		ValorConfigurableDto rutaUbicacionArchivoJson = null;
		try {

			// VARIABLE PRIMITIVA QUE VAMOS A UTILIZAR A LO LARGO DE LA IMPLEMENTACION
			GenericStringParam stringParam = new GenericStringParam();

			stringParam.addValue(AuditoriaUsuarioConstans.VALOR_CONFIGURABLE,
					AuditoriaUsuarioConstans.RUTA_ARCHIVO_REGISTRO_AUDITORIA);

			// 1.- CONSULTAMOS VALORES CONFIGURABLES (RUTA DE ARCHIVO JSON)
			rutaUbicacionArchivoJson = (ValorConfigurableDto) consultarValorConfigurableCommand.execute(stringParam);

			stringParam.addValue(AuditoriaUsuarioConstans.RUTA_ARCHIVO_REGISTRO_AUDITORIA,
					rutaUbicacionArchivoJson.getValorDefecto());

			// 2.- OBTENEMOS DATOS DE AUDITORIA DEL ARCHIVO JSON
			List<DatosAuditoriaDto> listado = (List<DatosAuditoriaDto>) 
					obtenerDatosAuditoriaArchivoJsonCommand
					.execute(stringParam);

			// 3.- VALIDAMOS QUE EXISTAN DATOS EN EL LISTADO
			if (!listado.isEmpty() || listado != null) {
				// 3.- ENVIAMOS A SERVICIO DE UTILIDAD QUE SE CONECTA A INSTANCIA DE ELASTIC
				for (DatosAuditoriaDto objetoDatosAudit : listado) {
					registrarDatosAuditoriaCommand.execute(objetoDatosAudit);
				}
			}

		} catch (Exception e) {
			LOG.info("EXCEPTION : " + e.getMessage());
			e.printStackTrace();
		}
		LOG.info("FECHA DE FIN DEL PROCESO: {} ", formatoFecha.format(new Date()));
		LOG.info(AuditoriaUsuarioConstans.FINALIZA_PROCESO_ENVIAR_DATOS_AUDITORIA);
		LOG.info(AuditoriaUsuarioConstans.separador);
	}
}
