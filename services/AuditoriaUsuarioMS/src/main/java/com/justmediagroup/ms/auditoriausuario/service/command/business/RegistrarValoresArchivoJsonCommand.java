package com.justmediagroup.ms.auditoriausuario.service.command.business;

import java.io.File;
import java.io.FileWriter;
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
import com.justmediagroup.modelo_canonico.DatosAuditoriaUsuarioType;
import com.justmediagroup.ms.auditoriausuario.constans.AuditoriaUsuarioConstans;
import com.justmediagroup.ms.auditoriausuario.controller.dto.DatosAuditoriaDto;
import com.justmediagroup.ms.auditoriausuario.controller.dto.GenericStringParam;

@Component
public class RegistrarValoresArchivoJsonCommand implements ICommand {

	private static final Logger LOG = LoggerFactory.getLogger(RegistrarValoresArchivoJsonCommand.class);

	private ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("null")
	@Override
	public Object execute(IParam parametro) throws BusinessException {

		GenericStringParam stringParam = (GenericStringParam) parametro;
		String json;
		try {
			LOG.info("INICIA COMANDO REGISTRAR VALORES ARCHIVO JSON");
			// OBTENEMOS LOS DATOS DEL MAP PARA ARMAR EL MODELO CANONICO A REGISTRAR EN
			// ARCHIVO JSON
			DatosAuditoriaDto datosAuditoria = new DatosAuditoriaDto();
			datosAuditoria.setEnlace(stringParam.getValues().get(AuditoriaUsuarioConstans.ENLACE));
			datosAuditoria.setUsuarioId(stringParam.getValues().get(AuditoriaUsuarioConstans.USUARIO_ID));
			datosAuditoria.setSistema(stringParam.getValues().get(AuditoriaUsuarioConstans.VERSION_OS));
			datosAuditoria.setVersionSistemaOperativo(stringParam.getValues().get(AuditoriaUsuarioConstans.VERSION_OS));
			datosAuditoria
					.setVersionDelNavegador(stringParam.getValues().get(AuditoriaUsuarioConstans.VERSION_NAVEGADOR));
			datosAuditoria.setCanal(stringParam.getValues().get(AuditoriaUsuarioConstans.CANAL));
			datosAuditoria.setCorreo(stringParam.getValues().get(AuditoriaUsuarioConstans.CORREO));
			datosAuditoria.setPassword(stringParam.getValues().get(AuditoriaUsuarioConstans.PASSWORD));

			File listadoDatosAuditoria;

			// BUSCAMOS LOS ARCHIVOS EN LA RUTA PARAMETRIZADA
			listadoDatosAuditoria = new File(
					stringParam.getValues().get(AuditoriaUsuarioConstans.RUTA_ARCHIVO_REGISTRO_AUDITORIA));

			// LECTURAMOS EL CONTENIDO DEL ARCHIVO JSON
			List<DatosAuditoriaUsuarioType> listaDeDatosAlmacenadosEnJson = mapper.readValue(listadoDatosAuditoria,
					new TypeReference<List<DatosAuditoriaUsuarioType>>() {
					});

			// CONFIGURAMOS EL MAPPER
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

			// VALIDAMOS QUE EL ARCHIVO TENGA CONTENIDO EN EL CASO DE QUE EL
			// ARCHIVO YA CUENTE CON DATOS
			if (!(listaDeDatosAlmacenadosEnJson == null) || !(listaDeDatosAlmacenadosEnJson.isEmpty())) {
				json = "";
				listaDeDatosAlmacenadosEnJson.add(datosAuditoria);
				json = mapper.writeValueAsString(listaDeDatosAlmacenadosEnJson);
				FileWriter file = new FileWriter(
						stringParam.getValues().get(AuditoriaUsuarioConstans.RUTA_ARCHIVO_REGISTRO_AUDITORIA));
				file.write(json);
				file.flush();
				file.close();
			}

		} catch (Exception e) {
			LOG.error("EXCEPCION EN COMANDO {} ", e.getMessage());
		}

		LOG.info(" FINALIZA COMANDO REGISTRAR VALORES ARCHIVO JSON COMMAND");
		return null;
	}

	@Override
	public void undo() {
		throw new UnsupportedOperationException("Operacion no soportada");
	}

}
