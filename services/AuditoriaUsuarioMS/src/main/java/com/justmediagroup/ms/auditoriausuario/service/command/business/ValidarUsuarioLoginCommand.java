package com.justmediagroup.ms.auditoriausuario.service.command.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.justmediagroup.logica_comun.exception.BusinessException;
import com.justmediagroup.logica_comun.exception.TipoError;
import com.justmediagroup.logica_comun.patrones.command.ICommand;
import com.justmediagroup.logica_comun.patrones.command.IParam;
import com.justmediagroup.modelo_canonico.Estado;
import com.justmediagroup.ms.auditoriausuario.constans.AuditoriaUsuarioConstans;
import com.justmediagroup.ms.auditoriausuario.controller.dto.ValidarUsuarioDto;

@Component
public class ValidarUsuarioLoginCommand implements ICommand {

	private static final Logger LOG = LoggerFactory.getLogger(ValidarUsuarioLoginCommand.class);

	@Override
	public Object execute(IParam parametro) throws BusinessException {
		LOG.info("INICIA COMANDO VALIDAR USUARIO LOGIN");
		ValidarUsuarioDto validarUsuarioDto = (ValidarUsuarioDto) parametro;

		// VALIDAMOS EL ESTADO DEL USUARIO SI SE ENCUENTRA EN ESTADO INACTIVO ENTONCES
		// ESTA BLOQUEADO
		if (validarUsuarioDto.getUsuario().getEstadoUsuario() != Estado.ACTIVO) {
			throw new BusinessException(AuditoriaUsuarioConstans.MENSAJE_USUARIO_SE_ENCUENTRA_BLOQUEADO,
					TipoError.SOLICITUD_INVALIDA);
		}
		// SI LA CANTIDAD DE INTENTOS LOGIN ES IGUAL AL PARAMETRIZADO
		LOG.info("FINALIZA COMANDO VALIDAR USUARIO LOGIN");
		return null;
	}

	@Override
	public void undo() {
		new UnsupportedOperationException("Operacion no soportada");
	}

}
