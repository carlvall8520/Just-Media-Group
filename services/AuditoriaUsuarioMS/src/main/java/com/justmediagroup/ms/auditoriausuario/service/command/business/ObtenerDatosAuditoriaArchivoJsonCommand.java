package com.justmediagroup.ms.auditoriausuario.service.command.business;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.justmediagroup.logica_comun.exception.BusinessException;
import com.justmediagroup.logica_comun.patrones.command.ICommand;
import com.justmediagroup.logica_comun.patrones.command.IParam;
import com.justmediagroup.ms.auditoriausuario.constans.AuditoriaUsuarioConstans;
import com.justmediagroup.ms.auditoriausuario.controller.dto.DatosAuditoriaDto;
import com.justmediagroup.ms.auditoriausuario.controller.dto.GenericStringParam;

@Component
public class ObtenerDatosAuditoriaArchivoJsonCommand implements ICommand {

	private static final Logger LOGGER = LoggerFactory.getLogger(ObtenerDatosAuditoriaArchivoJsonCommand.class);

	private ObjectMapper mapper = new ObjectMapper();

	private String json = "";

	@Override
	public Object execute(IParam parametro) throws BusinessException {

		GenericStringParam stringParam = (GenericStringParam) parametro;
		File listadoDatosAuditoriaJson;

		try {

			LOGGER.info("INICA COMANDO OBTENER DATOS AUDITORIA ARCHIVO JSON");
			// BUSCAMOS LOS ARCHIVOS EN LA RUTA PARAMETRIZADA
			listadoDatosAuditoriaJson = new File(
					stringParam.getValues().get(AuditoriaUsuarioConstans.RUTA_ARCHIVO_REGISTRO_AUDITORIA));

			// LECTURAMOS EL CONTENIDO DEL ARCHIVO JSON
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			List<DatosAuditoriaDto> listaDeDatosAlmacenadosEnJson = mapper.readValue(listadoDatosAuditoriaJson,
					new TypeReference<List<DatosAuditoriaDto>>() {
					});

			// IMPRIMIMOS EL ARCHIVO POR CONSOLA
			LOGGER.info(mapper.writeValueAsString(listaDeDatosAlmacenadosEnJson));

			// VACIAMOS EL ARCHIVO PARA NO CREAR REDUNDANCIA DE DATOS
			// AL ENVIAR A INSTANCIA DE ELASTIC
			// DESPUES DE EJECUTAR EL JOBS
			json = "[]";
			FileWriter file = new FileWriter(
					stringParam.getValues().get(AuditoriaUsuarioConstans.RUTA_ARCHIVO_REGISTRO_AUDITORIA));
			file.write(json);
			file.flush();
			file.close();

			return listaDeDatosAlmacenadosEnJson;

		} catch (Exception e) {
			LOGGER.info("ERROR EN EL COMANDO OBTENER DATOS AUDITORIA ARCHIVO JSON");
		}
		LOGGER.info("INICA COMANDO OBTENER DATOS AUDITORIA ARCHIVO JSON");
		return new ArrayList<>();
	}

	@Override
	public void undo() {

	}

}
