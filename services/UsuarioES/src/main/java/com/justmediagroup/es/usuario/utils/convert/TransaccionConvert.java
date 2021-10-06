package com.justmediagroup.es.usuario.utils.convert;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.justmediagroup.es.usuario.repository.model.TransaccionesWeb;
import com.justmediagroup.modelo_canonico.TransaccionesType;

public class TransaccionConvert {

	private static Logger LOGGER = LoggerFactory.getLogger(TransaccionConvert.class);

	private static ObjectMapper mapperJson = new ObjectMapper();

	TransaccionConvert() {
		
	}

	public static TransaccionesType modelToType(TransaccionesWeb transaccion) {
		LOGGER.info("TRANSACCION -> MODEL TO TYPE");
		try {
			mapperJson.configure(SerializationFeature.INDENT_OUTPUT, true);
			LOGGER.info(mapperJson.writeValueAsString(transaccion));
		} catch (JsonProcessingException e) {
			LOGGER.info(e.getMessage());
		}
		return new ModelMapper().map(transaccion, TransaccionesType.class);
	}

	public static TransaccionesWeb typeToModel(TransaccionesType transaccion) {
		LOGGER.info("TRANSACCION -> TYPE TO MODEL");
		try {
			mapperJson.configure(SerializationFeature.INDENT_OUTPUT, true);
			LOGGER.info(mapperJson.writeValueAsString(transaccion));
		} catch (JsonProcessingException e) {
			LOGGER.info(e.getMessage());
		}
		return new ModelMapper().map(transaccion, TransaccionesWeb.class);
	}

}
