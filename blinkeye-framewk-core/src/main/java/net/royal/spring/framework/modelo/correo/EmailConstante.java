package net.royal.spring.framework.modelo.correo;

public class EmailConstante {
	public static final String APLICACION_CODIGO = "SY";

	public static final String SEPARADOR_CORREOS = ";";

	public static final String CORREO_TIPO_CONFIGURACION_BDFUNCION = "BDFUNCION";
	public static final String CORREO_TIPO_CONFIGURACION_CLASE = "CLASE";

	public static final String PARAMETRO_TIPO_CONFIGURACION = "MAILTIPOCO";
	public static final String PARAMETRO_MAIL_CUENTA = "MAILCUENTA";
	public static final String PARAMETRO_MAIL_CLAVE = "MAILCLAVE";
	public static final String PARAMETRO_MAIL_PERFIL = "MAILPERFIL";

	public static final String PARAMETRO_MAIL_ES_CORREO_PRUEBA = "MAILFLGPRU";
	public static final String PARAMETRO_MAIL_CUENTA_CORREO_PRUEBA = "MAILPRUEBA";
	public static final String PARAMETRO_MAIL_RUTA_TEMPORAL = "MAILRUTATM";

	public enum tipo_configuracion {
		ARCHIVO, PARAMETROS, FUNCIONES
	};

	

	public static final String CORREO_TIPO_CONFIGURACION = "CORREO.TIPO_CONFIGURACION";
	public static final String CORREO_TIPO_CONFIGURACION_ARCHIVO = "ARCHIVO";
	public static final String CORREO_TIPO_CONFIGURACION_BDPARAMETROS = "BDPARAMETROS";

}
