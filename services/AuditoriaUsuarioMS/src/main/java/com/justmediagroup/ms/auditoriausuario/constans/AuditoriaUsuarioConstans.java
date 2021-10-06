package com.justmediagroup.ms.auditoriausuario.constans;

public class AuditoriaUsuarioConstans {

	public static final String separador = "==========================================================="
			+ "=======================================================================================";

	// URIS DE LAS CAPACIDADES DE SERVICIOS A ORQUESTAR

	public static final String CONSULTAR_USUARIO_POR_EMAIL = "/api/es/usuario/v1/{correo}";
	public static final String CONSULTAR_VALOR_CONFIGURABLE_POR_NOMBRE = "/api/es/valorconfigurable/v1/{nombre}";
	public static final String ACTUALIZAR_USUARIO = "/api/es/usuario/v1";
	public static final String CONSULTAR_TRANSACCION_POR_USUARIOID_ENLACE = "/api/es/transaccion/usuario/v1/{usuarioId}/{enlace}";
	public static final String ACTUALIZAR_TRANSACCION = "/api/es/transaccion/v1";
	public static final String CREAR_INTENTOS_LOGIN = "/api/es/intentoslogin/v1";
	public static final String ACTUALIZAR_INTENTOS_LOGIN = "/api/es/intentoslogin/v1";
	public static final String CONSULTAR_INTENTOS_LOGIN_POR_CORREO = "/api/es/intentoslogin/v1/{correo}";
	public static final String GUARDAR_DATOS_INSTANCIA_ELASTIC = "/api/us/auditoria/v1";

	// MENSAJES DE RESPUESTA
	public static final String MENSAJE_CLAVE_INCORRECTA = "Tu usuario o clave está incorrecta";
	public static final String MENSAJE_USUARIO_BLOQUEADO = "Usuario se encuentra bloqueado por maximo de intentos de login";
	public static final String MENSAJE_USUARIO_SE_ENCUENTRA_BLOQUEADO = "Usuario se encuentra bloqueado";

	public static final String MENSAJE_ERROR_CONSULTA = "A OCURRIDO UN ERROR AL CONSULTAR INFORMACIÓN DE %s";
	public static final String MENSAJE_ERROR_CREAR = "A OCURRIDO UN ERROR AL CREAR %s";
	public static final String MENSAJE_ERROR_MODIFICAR = "A OCURRIDO UN ERROR AL MODIFICAR %s";
	public static final String INICIA_CAPACIDAD_LOGUEAR_USUARIO = " ***************** INICIA CAPACIDAD DE LOGEAR USUARIO MS "
			+ "*****************";
	public static final String FINALIZA_CAPACIDAD_LOGUEAR_USUARIO = " ***************** INICIA CAPACIDAD DE LOGEAR USUARIO MS "
			+ "*****************";
	public static final String INICIA_PROCESO_ENVIAR_DATOS_AUDITORIA = "INICIA PROCESO ENVIAR DATOS AUDITORIA JOBS MS - AUDITORIA USUARIO";
	public static final String FINALIZA_PROCESO_ENVIAR_DATOS_AUDITORIA = "FINALIZA PROCESO ENVIAR DATOS AUDITORIA JOBS MS - AUDITORIA USUARIO";

	public static final int MINIMO_DE_INTENTOS_LOGIN_A_VALIDAR = 1;

	// NOMBRE DE VALORES CONFIGURABLES A UTILIZAR
	public static final String VALOR_CONFIGURABLE = "valorConfigurable";
	public static final String MAXIMO_INTENTOS_LOGIN = "maximoIntentosLogin";
	public static final String VALOR_PERMITIDO_DE_ACCESO = "valorPermitidoDeAcceso";
	public static final String RUTA_ARCHIVO_REGISTRO_AUDITORIA = "rutaArchivoJson";

	// CLAVES UTILIZADAS EN LOS MAPS
	public static final String CORREO = "correo";
	public static final String PASSWORD = "password";
	public static final String SISTEMA = "sistema";
	public static final String VERSION_OS = "versionSistemaOperativo";
	public static final String VERSION_NAVEGADOR = "versionDelNavegador";
	public static final String CANAL = "canal";
	public static final String ENLACE = "enlace";
	public static final String USUARIO_ID = "usuarioId";
}
