package com.justmediagroup.es.usuario.service.contract;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.justmediagroup.logica_comun.exception.BusinessException;
import com.justmediagroup.modelo_canonico.IntentosLoginType;
import com.justmediagroup.modelo_canonico.TransaccionesType;
import com.justmediagroup.modelo_canonico.UsuarioType;
import com.justmediagroup.modelo_canonico.ValoresConfigurablesType;

/**
 * @author jesus
 *
 */
public interface IUsuarioSvc {

	public UsuarioType consultarUsuarioPorEmail(String correo, String password) throws BusinessException;

	public UsuarioType crearUsuario(UsuarioType usuarioType) throws BusinessException, JsonProcessingException;

	public UsuarioType modificarUsuario(UsuarioType usuarioType) throws BusinessException;

	public TransaccionesType consultarTransaccionPorUsuario(String usuarioId, String enlace) throws BusinessException;

	public TransaccionesType crearTransaccion(TransaccionesType transaccion) throws BusinessException;

	public TransaccionesType modificarTransaccion(TransaccionesType transaccion) throws BusinessException;

	public ValoresConfigurablesType consultarValorConfigurable(String nombre) throws BusinessException;

	public ValoresConfigurablesType crearValorConfigurable(ValoresConfigurablesType valorConfigurable)
			throws BusinessException;

	public List<IntentosLoginType> consultarIntentosLoginPorCorreo(String correo) throws BusinessException;

	public IntentosLoginType guardarIntentosLoginDeUsuario(@Valid IntentosLoginType intentosLoginType)
			throws BusinessException;

	public IntentosLoginType modificarIntentosLoginUsuario(@Valid IntentosLoginType intentosLoginType)
			throws BusinessException;

}
