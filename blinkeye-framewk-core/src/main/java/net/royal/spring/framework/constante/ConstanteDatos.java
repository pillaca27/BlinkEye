package net.royal.spring.framework.constante;

public class ConstanteDatos {
	public enum SORT_ORDER {
		ASC, DESC
	}

	public enum TIPO_OPERACION {
		LIKE, EQ, NE, LE, LT
	}
	
	//TIPOS DE IMAGE
	public enum TIPO_IMAGEN
	{
	   LOGO,
	   FIRMA,
	   IMAGEN
	}
	
	public static final String CODIGO_IMAGEN_COMPANIA = "IMGCOM";
	public static final String CODIGO_IMAGEN_COMPANIA2 = "IMGCO2";
    public static final String CODIGO_IMAGEN_PRINCIPAL = "IMGPRI";
    public static final String CODIGO_IMAGEN_FORMATO = "IMGFOR";
	public static final String CODIGO_FIRMA_BOLETA = "FIRBOL";
    public static final String CODIGO_FIRMA_UTILIDADES = "FIRUTI";
    public static final String CODIGO_FIRMA_QUINTA = "FIRQUI";
    public static final String CODIGO_FIRMA_CTS = "FIRCTS";
    
    public static final String CORREO_TIPO_CONFIGURACION_CLASE = "CLASE";

	public static final String PARAMETRO_TIPO_CONFIGURACION = "MAILTIPOCO";
	public static final String PARAMETRO_MAIL_CUENTA = "MAILCUENTA";
	public static final String PARAMETRO_MAIL_CLAVE = "MAILCLAVE";
	public static final String PARAMETRO_MAIL_PERFIL = "MAILPERFIL";

	public static final String PARAMETRO_MAIL_ES_CORREO_PRUEBA = "MAILFLGPRU";
	public static final String PARAMETRO_MAIL_CUENTA_CORREO_PRUEBA = "MAILPRUEBA";
	public static final String PARAMETRO_MAIL_RUTA_TEMPORAL = "MAILRUTATM";
}
