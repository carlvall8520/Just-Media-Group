package com.justmediagroup.appweb.constans;

public class AppWebConstans {

	public static final String LOGEAR_USUARIO_PORTAL = "/api/ms/auditoriausuario/login/v1/{correo}";
	
	public static final String CONSULTAR_TRANSACCION = "/api/ms/auditoriausuario/link/v1/{enlace}";

	public static final String separador = "==========================================================="
			+ "=======================================================================================";

	// VALORES UTILIZADOS EN LOS PARAMETROS DE CONSUMO AL MICROSERVICIO DE AUDITORIA
	public static final String CORREO = "correo";
	public static final String PASSWORD = "password";
	public static final String SISTEMA = "sistema";
	public static final String VERSION_OS = "versionSistemaOperativo";
	public static final String VERSION_NAVEGADOR = "versionDelNavegador";
	public static final String CANAL = "canal";
	public static final String ENLACE = "enlace";
	public static final String USUARIO_ID = "usuarioId";

}
