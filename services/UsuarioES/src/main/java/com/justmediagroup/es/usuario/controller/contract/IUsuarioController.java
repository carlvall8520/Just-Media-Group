package com.justmediagroup.es.usuario.controller.contract;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.justmediagroup.logica_comun.utilitarios.RespuestaType;
import com.justmediagroup.modelo_canonico.IntentosLoginType;
import com.justmediagroup.modelo_canonico.TransaccionesType;
import com.justmediagroup.modelo_canonico.UsuarioType;
import com.justmediagroup.modelo_canonico.ValoresConfigurablesType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * @author jesus
 *
 */

@Validated

public interface IUsuarioController {

	// CAPACIDADES PARA LA GESTION DE USUARIOS

	@Operation(method = "consultarUsuarioPorEmail", operationId = "consultarUsuarioPorEmail", description = "Capacidad que se encarga de consultar el usuario por email", tags = "UsuarioEntityServiceV1", summary = "consultar usuario por email")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "404", description = "Not Fount", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@GetMapping(value = "/api/es/usuario/v1/{correo}", produces = "application/json; charset=utf-8")
	public ResponseEntity<Object> consultarUsuarioPorEmail(@PathVariable(name = "correo") String correo,
			@RequestHeader(value = "password", required = true) String contrasenia);

	@Operation(method = "crearUsuario", operationId = "crearUsuario", description = "Capacidad que se encarga de crear el usuario", tags = "UsuarioEntityServiceV1", summary = "crearUsuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@PostMapping(value = "/api/es/usuario/v1", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "UsuarioType", required = true, content = @Content(schema = @Schema(implementation = UsuarioType.class)))
	public ResponseEntity<Object> crearUsuario(
			@Valid @org.springframework.web.bind.annotation.RequestBody(required = true) UsuarioType usuario);

	@Operation(method = "modificarUsuario", operationId = "modificarUsuario", description = "Capacidad que se encarga de crear el usuario", tags = "UsuarioEntityServiceV1", summary = "modificarUsuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))), })
	@PutMapping(value = "/api/es/usuario/v1", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "UsuarioType", required = true, content = @Content(schema = @Schema(implementation = UsuarioType.class)))
	public ResponseEntity<Object> modificarUsuario(
			@Valid @org.springframework.web.bind.annotation.RequestBody(required = true) UsuarioType usuario);

	// CAPACIDADES PARA LA GESTION DE TRANSACCIONES
	@Operation(method = "consultarTransaccionPorUsuarioIdyEnlace", operationId = "consultarTransaccionPorUsuarioIdyEnlace", description = "consultar Transaccion Por UsuarioId y Enlace", tags = "TransaccionesEntitySeviceV1", summary = "consultarTransaccionPorUsuarioIdyEnlace")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TransaccionesType.class))),

			@ApiResponse(responseCode = "404", description = "Not Fount", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

	})
	@GetMapping(value = "/api/es/transaccion/usuario/v1/{usuarioId}/{enlace}", produces = "application/json; charset=utf-8")
	public ResponseEntity<Object> consultarTransaccionesPorUsuario(@PathVariable("usuarioId") String usuarioId,
			@PathVariable("enlace") String enlace);

	@Operation(method = "guardarTransaccionDeUsuario", operationId = "guardarTransaccionDeUsuario", description = "guardar transaccion de Usuario", tags = "TransaccionesEntitySeviceV1", summary = "guardarTransaccionDeUsuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))), })
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "TransaccionesType", required = true, content = @Content(schema = @Schema(implementation = TransaccionesType.class)))
	@PostMapping(value = "/api/es/transaccion/v1", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public ResponseEntity<Object> guardarTransaccionDeUsuario(
			@Valid @org.springframework.web.bind.annotation.RequestBody TransaccionesType transaccion);

	@Operation(method = "modificarTransaccionDeUsuario", operationId = "modificarTransaccionDeUsuario", description = "modificarTransaccionDeUsuario", tags = "TransaccionesEntitySeviceV1", summary = "modificarTransaccionDeUsuario")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "404", description = "Not Fount", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "TransaccionesType", required = true, content = @Content(schema = @Schema(implementation = TransaccionesType.class)))
	@PutMapping(value = "/api/es/transaccion/v1", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public ResponseEntity<Object> modificarTransaccionDeUsuario(
			@Valid @org.springframework.web.bind.annotation.RequestBody TransaccionesType transaccion);

	// CAPACIDADES QUE SE ENCARGAN DE MANIPULAR LOS VALORES CONFIGURABLES
	@Operation(method = "consultarValoresConfigureablesPorNombre", operationId = "consultarValoresConfigureablesPorNombre", description = "consultarValoresConfigureablesPorNombre", tags = "ValoresConfigurableESV1", summary = "consultarValoresConfigureablesPorNombre")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ValoresConfigurablesType.class))),

			@ApiResponse(responseCode = "404", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class)))

	})
	@GetMapping(value = "/api/es/valorconfigurable/v1/{nombre}", produces = "application/json; charset=utf-8")
	@Parameters({ @Parameter(name = "nombre", example = "maximoDeConsultas") })
	public ResponseEntity<Object> consultarValoresConfigurablesPorNombre(@PathVariable(name = "nombre") String nombre);

	@Operation(method = "crearValorConfigurable", operationId = "CrearValorConfigurable", description = "crearValorConfigurable", tags = "ValoresConfigurableESV1")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@PostMapping(value = "/api/es/valorconfigurable/v1", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Valor Configurable", content = @Content(schema = @Schema(implementation = ValoresConfigurablesType.class)))
	public ResponseEntity<Object> crearValorConfigurable(
			@org.springframework.web.bind.annotation.RequestBody ValoresConfigurablesType valorConfigurable);

	@Operation(method = "consultarIntentosLoginPorCorreo", operationId = "consultarIntentosLoginPorCorreo", description = "consultarIntentosLoginPorCorreo", tags = "IntentosLoginEntitySeviceV1", summary = "consultarIntentosLoginPorCorreo")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ValoresConfigurablesType.class))),

			@ApiResponse(responseCode = "404", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class)))

	})
	@GetMapping(value = "/api/es/intentoslogin/v1/{correo}", produces = "application/json; charset=utf-8")
	@Parameters({ @Parameter(name = "nombre", example = "correo") })
	public ResponseEntity<Object> consultarIntentosLoginPorCorreo(@PathVariable(name = "correo") String correo);

	@Operation(method = "guardarIntentosLogin", operationId = "guardarIntentosLogin", description = "guardar intentos login", tags = "IntentosLoginEntitySeviceV1", summary = "guardarIntentosLogin")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))), })
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Intentos Login", required = true, content = @Content(schema = @Schema(implementation = IntentosLoginType.class)))
	@PostMapping(value = "/api/es/intentoslogin/v1", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public ResponseEntity<Object> guardarIntentosLoginDeUsuario(
			@Valid @org.springframework.web.bind.annotation.RequestBody IntentosLoginType intentosLoginType);

	@Operation(method = "modificarIntentosLogin", operationId = "modificarIntentosLogin", description = "modificarIntentosLogin", tags = "IntentosLoginEntitySeviceV1", summary = "modificarIntentosLogin")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "404", description = "Not Fount", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Intentos Login", required = true, content = @Content(schema = @Schema(implementation = IntentosLoginType.class)))
	@PutMapping(value = "/api/es/intentoslogin/v1", produces = "application/json; charset=utf-8", consumes = "application/json; charset=utf-8")
	public ResponseEntity<Object> modificarIntentosLoginUsuario(
			@Valid @org.springframework.web.bind.annotation.RequestBody IntentosLoginType intentosLoginType);

}
