package com.justmediagroup.ms.auditoriausuario.controller.contract;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.justmediagroup.logica_comun.utilitarios.RespuestaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
public interface IAuditoriaUsuarioController {

	@Operation(method = "verificarTransaccionDeUsuario", operationId = "AuditoriaUsuarioMS", description = "Capacidad que se encarga de verificar las transacciones del usuario", tags = "AuditoriaUsuarioMS", summary = "verificarTrasaccionDeUsuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "404", description = "Not Fount", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@GetMapping(value = "/api/ms/auditoriausuario/link/v1/{enlace}", produces = "application/json; charset=utf-8")
	public ResponseEntity<Object> verificarTransaccionUsuario(@PathVariable(name = "enlace") String enlace,
			@RequestHeader(value = "usuarioId", required = true) String usuarioId,
			@RequestHeader(value = "sistema", required = true) String sistema,
			@RequestHeader(value = "versionSistemaOperativo", required = true) String versionSistemaOperativo,
			@RequestHeader(value = "versionDelNavegador", required = true) String versionDelNavegador,
			@RequestHeader(value = "canal", required = true) String canal);

	@Operation(method = "loguear usuario", operationId = "loguear usuario", description = "Capacidad que se encarga de validar el usuario para el logeo", tags = "AuditoriaUsuarioMS", summary = "loguear usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "404", description = "Not Fount", content = @Content(schema = @Schema(implementation = RespuestaType.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = RespuestaType.class))) })
	@GetMapping(value = "/api/ms/auditoriausuario/login/v1/{correo}", produces = "application/json; charset=utf-8")
	public ResponseEntity<Object> logearUsuario(@PathVariable(name = "correo") String correo,
			@RequestHeader(name = "password", required = true) String password,
			@RequestHeader(value = "sistema", required = true) String sistema,
			@RequestHeader(value = "versionSistemaOperativo", required = true) String versionSistemaOperativo,
			@RequestHeader(value = "versionDelNavegador", required = true) String versionDelNavegador,
			@RequestHeader(value = "canal", required = true) String canal);

}
