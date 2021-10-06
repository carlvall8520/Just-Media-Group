package com.justmediagroup.es.usuario.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justmediagroup.es.usuario.repository.contract.IIntentosLoginRepository;
import com.justmediagroup.es.usuario.repository.contract.ITransaccionesWebRepository;
import com.justmediagroup.es.usuario.repository.contract.IUsuarioRepository;
import com.justmediagroup.es.usuario.repository.contract.IValoresConfigurablesRepository;
import com.justmediagroup.es.usuario.repository.model.IntentosLogin;
import com.justmediagroup.es.usuario.repository.model.TransaccionesWeb;
import com.justmediagroup.es.usuario.repository.model.Usuario;
import com.justmediagroup.es.usuario.repository.model.ValoresConfigurables;
import com.justmediagroup.es.usuario.service.contract.IUsuarioSvc;
import com.justmediagroup.es.usuario.utils.convert.IntentosLoginConvert;
import com.justmediagroup.es.usuario.utils.convert.TransaccionConvert;
import com.justmediagroup.es.usuario.utils.convert.UsuarioConvert;
import com.justmediagroup.es.usuario.utils.convert.ValorConfigurableConvert;
import com.justmediagroup.logica_comun.exception.BusinessException;
import com.justmediagroup.logica_comun.exception.TipoError;
import com.justmediagroup.modelo_canonico.IntentosLoginType;
import com.justmediagroup.modelo_canonico.TransaccionesType;
import com.justmediagroup.modelo_canonico.UsuarioType;
import com.justmediagroup.modelo_canonico.ValoresConfigurablesType;

/**
 * @author jesus
 *
 */

@Service
public class UsuarioSvcImpl implements IUsuarioSvc {

	@Autowired
	IUsuarioRepository usuarioRepository;

	@Autowired
	ObjectMapper mapperJson;

	@Autowired
	ITransaccionesWebRepository transaccionWebRepository;

	@Autowired
	IValoresConfigurablesRepository valoresConfigurablesRepository;

	@Autowired
	IIntentosLoginRepository intentosLoginRepository;

	@Override
	public UsuarioType crearUsuario(UsuarioType usuarioType) throws BusinessException {
		if (usuarioType == null) {
			throw new BusinessException("EL RECURSO NO PUEDE SER NULO", TipoError.SOLICITUD_INVALIDA);
		}
		Usuario usuario;
		usuario = UsuarioConvert.convertTypeToModel(usuarioType);
		usuario.setId(null);
		return UsuarioConvert.convertModelToType(usuarioRepository.save(usuario));
	}

	@Override
	public UsuarioType modificarUsuario(UsuarioType usuarioType) throws BusinessException {
		if (!usuarioRepository.existsById(usuarioType.getId())) {
			throw new BusinessException(
					String.format("USUARIO CON ID [%s] no encontrado", usuarioType.getId().toString()),
					TipoError.NO_ENCONTRADO);
		}
		Usuario usuario;
		usuario = usuarioRepository.save(UsuarioConvert.convertTypeToModel(usuarioType));
		return UsuarioConvert.convertModelToType(usuario);
	}

	@Override
	public TransaccionesType consultarTransaccionPorUsuario(String usuarioId, String enlace) throws BusinessException {
		TransaccionesWeb transaccion;
		transaccion = transaccionWebRepository.consultarTransaccionPorUsuarioId(usuarioId, enlace);
		if (transaccion == null) {
			throw new BusinessException("RECURSO NO ENCONTRADO", TipoError.NO_ENCONTRADO);
		}
		return TransaccionConvert.modelToType(transaccion);
	}

	@Override
	public TransaccionesType crearTransaccion(TransaccionesType transaccion) throws BusinessException {
		if (transaccion == null) {
			throw new BusinessException("EL RECURSO NO PUEDE SER NULO", TipoError.SOLICITUD_INVALIDA);
		}
		transaccion.setIdAcceso(null);
		TransaccionesWeb transaccionWeb;
		transaccionWeb = transaccionWebRepository.save(TransaccionConvert.typeToModel(transaccion));
		return TransaccionConvert.modelToType(transaccionWeb);
	}

	@Override
	public TransaccionesType modificarTransaccion(TransaccionesType transaccion) throws BusinessException {
		if (!transaccionWebRepository.existsById(transaccion.getIdAcceso())) {
			throw new BusinessException(String.format("RECURSO CON ID [%S] NO ENCONTRADO", transaccion.getIdAcceso()),
					TipoError.NO_ENCONTRADO);
		}
		TransaccionesWeb transaccionWeb;
		transaccionWeb = transaccionWebRepository.save(TransaccionConvert.typeToModel(transaccion));
		return TransaccionConvert.modelToType(transaccionWeb);
	}

	@Override
	public ValoresConfigurablesType consultarValorConfigurable(String nombre) throws BusinessException {
		ValoresConfigurables valoresConfigurables;
		valoresConfigurables = valoresConfigurablesRepository.consultarValorConfigurablePorNombre(nombre);
		if (valoresConfigurables == null) {
			throw new BusinessException("RECURSO NO ENCONTRADO", TipoError.NO_ENCONTRADO);
		}
		return ValorConfigurableConvert.convertModelToType(valoresConfigurables);
	}

	@Override
	public ValoresConfigurablesType crearValorConfigurable(ValoresConfigurablesType valorConfigurable)
			throws BusinessException {
		if (valorConfigurable == null) {
			throw new BusinessException("EL RECURSO NO PUEDE SER NULO", TipoError.SOLICITUD_INVALIDA);
		}
		valorConfigurable.setId(null);
		ValoresConfigurables valorConfig;
		valorConfig = valoresConfigurablesRepository
				.save(ValorConfigurableConvert.convertTypeToModel(valorConfigurable));
		return ValorConfigurableConvert.convertModelToType(valorConfig);

	}

	@Override
	public List<IntentosLoginType> consultarIntentosLoginPorCorreo(String correo) {
		List<IntentosLogin> intentosLogin = new ArrayList<>();
		intentosLogin = intentosLoginRepository.consultarIntentosLoginPorCorreo(correo);
		return IntentosLoginConvert.convertlistTypeToModelx(intentosLogin);
	}

	@Override
	public IntentosLoginType guardarIntentosLoginDeUsuario(@Valid IntentosLoginType intentosLoginType)
			throws BusinessException {
		if (intentosLoginType == null) {
			throw new BusinessException("EL RECURSO NO PUEDE SER NULO", TipoError.SOLICITUD_INVALIDA);
		}
		intentosLoginType.setId(null);
		IntentosLogin intentosLogin;
		intentosLogin = intentosLoginRepository.save(IntentosLoginConvert.convertTypeToModel(intentosLoginType));
		return IntentosLoginConvert.convertModelToType(intentosLogin);
	}

	@Override
	public IntentosLoginType modificarIntentosLoginUsuario(@Valid IntentosLoginType intentosLoginType)
			throws BusinessException {
		if (!intentosLoginRepository.existsById(intentosLoginType.getId())) {
			throw new BusinessException(String.format("RECURSO CON ID [%S] NO ENCONTRADO", intentosLoginType.getId()),
					TipoError.NO_ENCONTRADO);
		}
		IntentosLogin intentosLogin;
		intentosLogin = intentosLoginRepository.save(IntentosLoginConvert.convertTypeToModel(intentosLoginType));
		return IntentosLoginConvert.convertModelToType(intentosLogin);
	}

	@Override
	public UsuarioType consultarUsuarioPorEmail(String correo, String password) throws BusinessException {
		Usuario usuario;
		usuario = usuarioRepository.consultarUsuarioPorCorreo(correo, password);
		if (usuario == null) {
			throw new BusinessException("EL RECURSO NO FUE ENCONTRADO", TipoError.NO_ENCONTRADO);
		}
		return UsuarioConvert.convertModelToType(usuario);
	}

}
