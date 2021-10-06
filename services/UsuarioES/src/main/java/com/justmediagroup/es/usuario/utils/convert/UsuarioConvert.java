/**
 * 
 */
package com.justmediagroup.es.usuario.utils.convert;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.justmediagroup.es.usuario.repository.model.Usuario;
import com.justmediagroup.modelo_canonico.UsuarioType;

/**
 * @author jesus
 *
 */
public class UsuarioConvert {

	private static Logger LOGGER = LoggerFactory.getLogger(UsuarioConvert.class);

	private static ObjectMapper mapperJson = new ObjectMapper();

	UsuarioConvert() {
	}

	public static UsuarioType convertModelToType(Usuario usuario) {
		LOGGER.info("USUARIO MODEL TO TYPE");
		try {
			mapperJson.configure(SerializationFeature.INDENT_OUTPUT, true);
			LOGGER.info(mapperJson.writeValueAsString(usuario));
		} catch (JsonProcessingException e) {
			LOGGER.error(e.toString());
		}
		return new ModelMapper().map(usuario, UsuarioType.class);
	}

	public static Usuario convertTypeToModel(UsuarioType usuarioType) {
		LOGGER.info("USUARIO TYPE TO MODEL");
		try {
			mapperJson.configure(SerializationFeature.INDENT_OUTPUT, true);
			LOGGER.info(mapperJson.writeValueAsString(usuarioType));
		} catch (JsonProcessingException e) {
			LOGGER.error(e.toString());
		}
		return new ModelMapper().map(usuarioType, Usuario.class);
	}

}
