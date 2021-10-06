package com.justmediagroup.es.usuario.controller.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.justmediagroup.es.usuario.repository.contract.ITransaccionesWebRepository;
import com.justmediagroup.es.usuario.repository.contract.IUsuarioRepository;
import com.justmediagroup.es.usuario.repository.contract.IValoresConfigurablesRepository;
import com.justmediagroup.es.usuario.repository.model.TransaccionesWeb;
import com.justmediagroup.es.usuario.repository.model.Usuario;
import com.justmediagroup.es.usuario.repository.model.ValoresConfigurables;
import com.justmediagroup.es.usuario.utils.convert.TransaccionConvert;
import com.justmediagroup.es.usuario.utils.convert.UsuarioConvert;
import com.justmediagroup.modelo_canonico.Estado;
import com.justmediagroup.modelo_canonico.EstadoValorConfigurable;
import com.justmediagroup.modelo_canonico.TransaccionesType;
import com.justmediagroup.modelo_canonico.UsuarioType;
import com.justmediagroup.modelo_canonico.ValoresConfigurablesType;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UsuarioControllerImplTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ITransaccionesWebRepository transaccionRepository;

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private IValoresConfigurablesRepository valoresConfigurablesRepository;

	private File datosUsuarioJson;

	private File datosTransaccionJson;

	private File datosValoresConfigurablesJson;

	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		this.datosUsuarioJson = new File("src/test/resources/usuario.json");
		this.datosTransaccionJson = new File("src/test/resources/transacciones.json");
		this.datosValoresConfigurablesJson = new File("src/test/resources/valorconfigurable.json");
	}

	@AfterEach
	void tearDown() throws Exception {
		this.transaccionRepository.deleteAll();
		this.usuarioRepository.deleteAll();
		this.valoresConfigurablesRepository.deleteAll();
		datosUsuarioJson = null;
		datosTransaccionJson = null;
		datosValoresConfigurablesJson = null;
		this.mockMvc = null;
	}

	@Test
	void testConsultarUsuarioPorEmail() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setId(null);
		usuario.setCorreo("carlvall8520@gmail.com");
		usuario.setNombreCompleto("CARLOS");
		usuario.setEstadoUsuario(Estado.ACTIVO);
		usuario.setFechaCreacion(new Date());
		usuario.setFechaModificacion(new Date());
		usuario.setIntentosLogin(0);
		usuario.setPassword("12345");
		
		usuarioRepository.save(usuario);
		
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/api/es/usuario/v1/{correo}","carlvall8520@gmail.com")	
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testConsultarUsuarioPorEmailNotFount() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setId(null);
		usuario.setCorreo("carlvall8520@gmail.com");
		usuario.setNombreCompleto("CARLOS");
		usuario.setEstadoUsuario(Estado.ACTIVO);
		usuario.setFechaCreacion(new Date());
		usuario.setFechaModificacion(new Date());
		usuario.setIntentosLogin(0);
		usuario.setPassword("12345");
		
		usuarioRepository.save(usuario);
		
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/api/es/usuario/v1/{correo}","8520@gmail.com")	
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	void testCrearUsuario() throws JsonProcessingException, Exception {
		UsuarioType  usuarioType = objectMapper.readValue(datosUsuarioJson, new TypeReference<List <UsuarioType>>() {}).get(0);
		this.mockMvc.perform(MockMvcRequestBuilders
				.post("/api/es/usuario/v1")
				.content(objectMapper.writeValueAsString(usuarioType))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testModificarUsuario() throws JsonProcessingException, Exception {
		UsuarioType usuarioType = objectMapper.readValue(datosUsuarioJson, new TypeReference<List<UsuarioType>>() {}).get(0);
		Usuario usuario = usuarioRepository.save(UsuarioConvert.convertTypeToModel(usuarioType));
		usuario.setCorreo("xxxxxxxxxxxxxxxxxxx@gmail.com");
		this.mockMvc.perform(MockMvcRequestBuilders
				.put("/api/es/usuario/v1")
				.content(objectMapper.writeValueAsString(usuario))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	      		.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testModificarUsuarioNotFount() throws JsonProcessingException, Exception {
		UsuarioType usuarioType = objectMapper.readValue(datosUsuarioJson, new TypeReference<List<UsuarioType>>() {}).get(0);
		Usuario usuario = usuarioRepository.save(UsuarioConvert.convertTypeToModel(usuarioType));
		usuario.setId(UUID.randomUUID());
		usuario.setCorreo("xxxxxxxxxxxxxxxxxxx@gmail.com");
		this.mockMvc.perform(MockMvcRequestBuilders
				.put("/api/es/usuario/v1")
				.content(objectMapper.writeValueAsString(usuario))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	      		.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testConsultarTransaccionesPorUsuario() throws JsonProcessingException, Exception {
		TransaccionesWeb transaccion = new TransaccionesWeb();
		transaccion.setCantidadVisitas(1);
		transaccion.setEnlaceWeb("1234");
		transaccion.setIdAcceso(null);
		transaccion.setUsuarioId("1234");
		transaccionRepository.save(transaccion);
		
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/api/es/transaccion/usuario/v1/{usuarioId}/{enlace}","1234","MTIzNA==")	
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGuardarTransaccionDeUsuario() throws JsonProcessingException, Exception {
		TransaccionesType  transaccionesType = objectMapper.readValue(datosTransaccionJson, new TypeReference<List <TransaccionesType>>() {}).get(0);
		this.mockMvc.perform(MockMvcRequestBuilders
				.post("/api/es/transaccion/v1")
				.content(objectMapper.writeValueAsString(transaccionesType))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testModificarTransaccionDeUsuario() throws JsonProcessingException, Exception {
		TransaccionesType transaccionesType = objectMapper.readValue(datosTransaccionJson, new TypeReference<List<TransaccionesType>>() {}).get(0);
		TransaccionesWeb transaccion = transaccionRepository.save(TransaccionConvert.typeToModel(transaccionesType));
		transaccion.setCantidadVisitas(3);
		this.mockMvc.perform(MockMvcRequestBuilders
				.put("/api/es/transaccion/v1")
				.content(objectMapper.writeValueAsString(transaccion))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	      		.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	void testModificarTransaccionDeUsuarioNotFount() throws JsonProcessingException, Exception {
		TransaccionesType transaccionesType = objectMapper.readValue(datosTransaccionJson, new TypeReference<List<TransaccionesType>>() {}).get(0);
		TransaccionesWeb transaccion = transaccionRepository.save(TransaccionConvert.typeToModel(transaccionesType));
		transaccion.setCantidadVisitas(3);
		transaccion.setIdAcceso(UUID.randomUUID());
		this.mockMvc.perform(MockMvcRequestBuilders
				.put("/api/es/transaccion/v1")
				.content(objectMapper.writeValueAsString(transaccion))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	      		.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testConsultarValoresConfigurablesPorNombre() throws Exception {
		ValoresConfigurables valoresConfigurablesType = new ValoresConfigurables();
		valoresConfigurablesType.setId(null);
		valoresConfigurablesType.setNombre("variableConfigurable");
		valoresConfigurablesType.setValorDefecto("1");
		valoresConfigurablesType.setDescripcion("VARIABLE CONFIGURABLE");
		valoresConfigurablesType.setEstado(EstadoValorConfigurable.ACTIVO);
		valoresConfigurablesRepository.save(valoresConfigurablesType);
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/api/es/valorconfigurable/v1/{nombre}","variableConfigurable")	
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void testConsultarValoresConfigurablesPorNombreNotFount() throws Exception {
		ValoresConfigurables valoresConfigurablesType = new ValoresConfigurables();
		valoresConfigurablesType.setId(null);
		valoresConfigurablesType.setNombre("Variab");
		valoresConfigurablesType.setValorDefecto("1");
		valoresConfigurablesType.setDescripcion("VARIABLE CONFIGURABLE");
		valoresConfigurablesType.setEstado(EstadoValorConfigurable.ACTIVO);
		valoresConfigurablesRepository.save(valoresConfigurablesType);
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/api/es/valorconfigurable/v1/{nombre}","variableNoConfigurable")	
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testCrearValorConfigurable() throws JsonProcessingException, Exception {
		ValoresConfigurablesType valorConfigurable = objectMapper.readValue(datosValoresConfigurablesJson, new TypeReference<List <ValoresConfigurablesType>>() {}).get(0);
		valorConfigurable.setId(null); 
		this.mockMvc.perform(MockMvcRequestBuilders
				.post("/api/es/valorconfigurable/v1")
				.content(objectMapper.writeValueAsString(valorConfigurable))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

}
