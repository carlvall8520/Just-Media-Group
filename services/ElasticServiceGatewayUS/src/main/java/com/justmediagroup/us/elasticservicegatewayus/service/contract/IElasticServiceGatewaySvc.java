package com.justmediagroup.us.elasticservicegatewayus.service.contract;

import javax.validation.Valid;


import com.justmediagroup.us.elasticservicegatewayus.repository.model.DatosAuditoriaUsuario;

public interface IElasticServiceGatewaySvc {

	DatosAuditoriaUsuario crearDatosAuditoriaUsuario(@Valid DatosAuditoriaUsuario datosAuditoria);

}
