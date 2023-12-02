package net.royal.spring.framework.core;

public class UConstante {
	public static final String APLICACION_CODIGO_APLICACION = "APLICACION.CODIGO_APLICACION";
	public static final String APLICACION_IMAGEN_LOGIN_PRINCIPAL = "APLICACION.IMAGEN_LOGIN_PRINCIPAL";
	public static final String APLICACION_IMAGEN_LOGIN_FONDO = "APLICACION.IMAGEN_LOGIN_FONDO";
	public static final String APLICACION_IMAGEN_LOGIN_BANDA1 = "APLICACION.IMAGEN_LOGIN_BANDA1";
	public static final String APLICACION_VER_LISTA_COMPANIAS = "APLICACION.VER_LISTA_COMPANIAS";
	public static final String APLICACION_VER_LISTA_CLIENTES = "APLICACION.VER_LISTA_CLIENTES";
	public static final String APLICACION_VER_LISTA_PERIODOS = "APLICACION.VER_LISTA_PERIODOS";
	public static final String APLICACION_NAVEGACION_LOGIN = "APLICACION.NAVEGACION_LOGIN";
	public static final String APLICACION_NAVEGACION_BIENVENIDA = "APLICACION.NAVEGACION_BIENVENIDA";
	public static final String APLICACION_NAVEGACION_ERROR = "APLICACION.NAVEGACION_ERROR";
	public static final String APLICACION_NAVEGACION_VISTAEXPIRADA = "APLICACION.NAVEGACION_VISTAEXPIRADA";

	/**
	 * Indica cual es la clave publica para identificar cual es la clave para ir a
	 * la pagina para mostrar los errores del sistema
	 */
	public static final String NAVIGATIONCASE_VISTAEXPIRADA = "pantallaVistaExpirada";

	public static final String SESION_NAVIGATIONCASE_ACTUAL = "sessionVistaActual";

	/**
	 * indica el nombre generico del archivo de configuracion del sistema actual
	 */
	public static final String ARCHIVOPROPIEDADES_NOMBRE = "propiedadesAplicacionWeb.properties";

	/**
	 * Archivo de propiedades donde almacena los variables publicas
	 */
	public static final String ARCHIVOPROPIEDADES_SESSION = "keyPropiedadesAplicacionWeb";

	/**
	 * indica el tipo de validacion que se hara al momento ed validar el login
	 */
	public static final String CONSTANTE_VALIDACIONLOGIN_CODIGOUSUARIO = "CODIGOUSUARIO";

	/**
	 * indica el tipo de validacion que se hara al momento ed validar el login
	 */
	public static final String CONSTANTE_VALIDACIONLOGIN_DOCUMENTOPERSONA = "DOCUMENTOPERSONA";

	/**
	 * indica el tipo de aplicacion que se esta ejecutando, WEB
	 */
	public static final String CONSTANTE_TIPOAPLICACION_WEB = "WEB";

	/**
	 * indica el tipo de aplicacion que se esta ejecutando, WIN
	 */
	public static final String CONSTANTE_TIPOAPLICACION_WIN = "WIN";

	/**
	 * indica el tipo de aplicacion que se esta ejecutando, WEB SERVICES
	 */
	public static final String CONSTANTE_TIPOAPLICACION_WS = "WS";

	/**
	 * constante que indica si es SI
	 */
	public static final String CONSTANTE_OPCION_SI = "S";

	/**
	 * constante que indica si es NO
	 */
	public static final String CONSTANTE_OPCION_NO = "N";

	/**
	 * Devuelve el nombre del bean en la session de Faces
	 */
	public static final String SESION_BEAN_BINDINGCONTROLLER = "cBindingController";

	/**
	 * Devuelve el nombre del bean en la session de Faces
	 */
	public static final String SESION_BEAN_COMUNSESSIONCONTROLLER = "cComunSession";

	/**
	 * Devuelve el nombre del bean en la session de Faces
	 */
	public static final String SESION_BEAN_GLOBALSESSIONCONTROLLER = "cComunGlobal";

	/**
	 * Devuelve el listado de navegacion de donde se encuentra la aplicacion
	 */
	public static final String SESION_FORMULARIO_LISTA_PARAMETROS = "formularioListaParametros";

	/**
	 * Devuelve el listado de navegacion de donde se encuentra la aplicacion
	 */
	public static final String SESION_FORMULARIO_ACCION_SOLICITADA = "formularioAccionSolicitada";

	/**
	 * Devuelve el listado de navegacion de donde se encuentra la aplicacion
	 */
	public static final String SESION_FORMULARIO_OBJETO_ENVIADO = "formularioObjetoEnviado";

	/**
	 * Devuelve el listado de modelos encontrados por usuario
	 */
	public static final String SESION_MENU_MODELO_POR_USUARIO = "menuPorUsuario";

	/**
	 * Devuelve el listado de navegacion de donde se encuentra la aplicacion
	 */
	public static final String SESION_MENU_NAVEGACION = "keyCurrentMenuNavegacion";

	/**
	 * Devuelve el listado de navegacion de donde se encuentra la aplicacion
	 */
	public static final String SESION_MENU_PANTALLA_TITULO_ACCION = "keyCurrentMenuPantallaTituloAccion";

	/**
	 * objeto del tipo usuario actual que tiene todos los atributos del usuario que
	 * se encuentra en el sistema
	 */
	public static final String SESION_EXCEPCIONACTUAL = "excepcionactual";

	/**
	 * objeto del tipo usuario actual que tiene todos los atributos del usuario que
	 * se encuentra en el sistema
	 */
	public static final String SESION_USUARIOACTUALDOMINIO = "usuario";

	/**
	 * objeto del tipo usuario actual que tiene todos los atributos del usuario que
	 * se encuentra en el sistema
	 */
	public static final String SESION_USUARIOREMOTODOMINIO = "usuario.remoto";

	/**
	 * Devuelve el tipo de aplicacion actual de la session
	 */
	public static final String SESION_TIPOAPLICACION = "keyCurrentTipoApplication";

	/**
	 * Devuelve el codigo de aplicacion actual de la session
	 */
	public static final String SESION_CODIGOAPLICACION = "keyCurrentCodApplication";

	/**
	 * indica el nombre generico del archivo que contiene la llave o semilla para
	 * los metodos de desencriptacion
	 */
	public static final String SESION_NOMBREARCHIVOLLAVESEGURIDAD = "ayuda.help";

	/**
	 * mostrar el mensaje de error
	 */
	public static final String PROPIEDAD_MOSTRARMENSAJE = "system.err.showmessage";

	/**
	 * mostrar el detalle de error
	 */
	public static final String PROPIEDAD_MOSTRARSTACKTRACE = "system.err.showstackTrace";

	/**
	 * Indica cual es la clave publica para identificar el controller que maneja el
	 * login
	 */
	public static final String NAVIGATIONCASE_LOGIN = "pantallaLogin";

	/**
	 * Indica cual es la clave publica para identificar cual es la clave para ir a
	 * la primera pagina de la aplicacion o de bienvenida
	 */
	public static final String NAVIGATIONCASE_BIENVENIDA = "pantallaBienvenida";

	/**
	 * Indica cual es la clave publica para identificar cual es la clave para ir a
	 * la pagina para mostrar los errores del sistema
	 */
	public static final String NAVIGATIONCASE_ERROR = "pantallaError";

	/**
	 * Indica cual es el modo de autentificacion que tendra el modulo de seguridad
	 */
	public static final String PROPIEDAD_MODOAUTENTIFICACION = "user.authentication";

	/**
	 * Indica cual es el modo de autentificacion que tendra el modulo de seguridad
	 */
	public static final String PROPIEDAD_VALIDARCLAVEBLANCO = "user.validarClaveBlanco";

	/**
	 * constante para indicar que la autentificacion debe ser por Active Directory
	 */
	public static final String CONSTANTE_AUTENTIFICACION_ACTIVEDIRECTORY = "A";

	/**
	 * constante para indicar que la autentificacion debe ser por Base de datos
	 */
	public static final String CONSTANTE_AUTENTIFICACION_USERDATABASE = "B";

	/**
	 * constante para indicar que la autentificacion sera con un sistema externo el
	 * SAA
	 */
	public static final String CONSTANTE_AUTENTIFICACION_SAA = "S";

	/**
	 * constante para indicar cual es el valor del estado Activo
	 */
	public static final String CONSTANTE_ESTADO_ACTIVO = "A";

	/**
	 * constante para indicar cual es el valor del estado Inactivo
	 */
	public static final String CONSTANTE_ESTADO_INACTIVO = "I";

	public static final String CONSTANTE_WORKFLOW_ACCION_TIPO_EXTERNO_GUARDAR = "workflow_externo_guardar";
	public static final String CONSTANTE_WORKFLOW_ACCION_TIPO_EXTERNO_SALIR = "workflow_externo_salir";
	public static final String CONSTANTE_WORKFLOW_ACCION_TIPO_EXTERNO_OTRO = "workflow_externo_otro";
	public static final String CONSTANTE_WORKFLOW_ACCION_TIPO_INSERTAR = "workflow_insertar";
	public static final String CONSTANTE_WORKFLOW_ACCION_TIPO_ACTUALIZAR = "workflow_actualizar";
	public static final String CONSTANTE_WORKFLOW_ACCION = "workflow_accion";
	public static final String CONSTANTE_WORKFLOW_ELEMENTO_ENTRADA = "workflow_elemento_entrada";
	public static final String CONSTANTE_WORKFLOW_ELEMENTO_SALIDA = "workflow_elemento_salida";

	/**
	 * constante para indicar la llave con la que se pasa parametros para el nombre
	 * del tipo de busqueda de una ventana emergente
	 */
	public static final String CONSTANTE_KEY_NOMBRETIPOBUSQUEDA = "busqueda_nombreTipoBusqueda";

	/**
	 * constante para indicar la llave con la que se pasa parametros para el nombre
	 * del tipo de busqueda de una ventana emergente
	 */
	public static final String CONSTANTE_KEY_NOMBRETIPOBUSQUEDA_TAG = "busqueda_nombreTipoBusqueda_tag";

	/**
	 * constante para indicar la llave para el mensaje al validar usuario
	 */
	public static final String CONSTANTE_KEY_ERR_USUARIO = "seguridad_msg_usuario";

	/**
	 * constante para indicar la llave para el mensaje al validar usuario
	 */
	public static final String CONSTANTE_KEY_ERR_USUARIO_VACIO = "seguridad_msg_usuario";

	/**
	 * constante para indicar la llave para el mensaje al validar clave
	 */
	public static final String CONSTANTE_KEY_ERR_CLAVE = "seguridad_msg_clave";

	/**
	 * constante para indicar la llave para el mensaje al validar usuario
	 */
	public static final String CONSTANTE_KEY_ERR_CLAVE_VACIO = "seguridad_msg_usuario";

	/**
	 * constante para indicar la llave para el mensaje al validar permisos
	 * componentes
	 */
	public static final String CONSTANTE_KEY_ERR_COMPONENETE = "seguridad_msg_componentes";

	/**
	 * constante para indicar la llave para el mensaje al validar persona
	 */
	public static final String CONSTANTE_KEY_ERR_PERSONA = "seguridad_msg_persona";

	public static final String CONSTANTE_MENSAJE_MES = "MES_ERROR";
	public static final String CONSTANTE_MENSAJE_DIA = "DIA_ERROR";

	// public static final String HSEQ = "HS";

	public static final String FORMATO_FECHAHORA_TRAMAUNIVERSAL = "yyyyMMddHHmmss";

	public static final String FORMATO_FECHA = "dd/MM/yyyy";
	public static final String FORMATO_FECHA_HORA24 = "dd/MM/yyyy HH:mm:ss";

	/**
	 * indica cual es la ruta en la que se almacena la informacion de las imagenes
	 * de la aplicacion y de los reportes (logo de compaia de cabezera, y logo en
	 * reporte)
	 */
	public static final String RUTA_IMAGENES_COMPANIA = "comun/recursos/imagenes/compania";

	/**
	 * indica cual es la ruta en la que se almacena la informacion de las imagenes
	 * de la aplicacion y de los reportes (logo del cliente, y logo en reporte)
	 */
	public static final String RUTA_IMAGENES_CLIENTE = "comun/recursos/imagenes/cliente";

	/**
	 * indica cual es la ruta en la que se almacena la informacion de las imagenes
	 * de la aplicacion y de los reportes (logo del cliente, y logo en reporte)
	 */
	// public static final String RUTA_SEGURIDADCONCETO_AYUDAS =
	// "comun/recursos/ayudas";

	/**
	 * indica cual es la ruta en la que se almacena la informacion de las imagenes
	 * de la aplicacion y de los reportes (logo del cliente, y logo en reporte)
	 */
	public static final String RUTA_ARCHIVOS_TEMPORALES = "temporal";

	public static final String CONSTANTE_VALIADCION_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static final String CONSTANTE_UNIDAD_PIXELES = "px";

	/**
	 * indica cual es la ruta en la que se almacena el icono de pdf para asignarlo a
	 * los documentos de tipo pdf.
	 */
	public static final String CONSTANTE_RUTA_ICONO_PDF = "/comun/recursos/imagenes/tipoarchivo/pdf.png";

	/**
	 * indica cual es la ruta en la que se almacena el icono de word para asignarlo
	 * a los documentos de tipo word.
	 */
	public static final String CONSTANTE_RUTA_ICONO_WORD = "/comun/recursos/imagenes/tipoarchivo/word.png";

	/**
	 * indica cual es la ruta en la que se almacena el icono de excel para asignarlo
	 * a los documentos de tipo excel.
	 */
	public static final String CONSTANTE_RUTA_ICONO_EXCEL = "/comun/recursos/imagenes/tipoarchivo/excel.png";

	public static final String CONSTANTE_RUTA_ICONO_POWERPOINT = "/comun/recursos/imagenes/tipoarchivo/powerpoint.png";

	/**
	 * indica cual es la ruta en la que se almacena el icono de txt para asignarlo a
	 * los documentos de tipo txt.
	 */
	public static final String CONSTANTE_RUTA_ICONO_TXT = "/comun/recursos/imagenes/tipoarchivo/txt.png";

	public static final String CONSTANTE_RUTA_ICONO_AVI = "/comun/recursos/imagenes/tipoarchivo/avi.png";

	public static final String CONSTANTE_RUTA_ICONO_JPG = "/comun/recursos/imagenes/tipoarchivo/jpg.png";

	public static final String CONSTANTE_RUTA_ICONO_MOV = "/comun/recursos/imagenes/tipoarchivo/mov.png";

	public static final String CONSTANTE_RUTA_ICONO_MP3 = "/comun/recursos/imagenes/tipoarchivo/mp3.png";

	public static final String CONSTANTE_RUTA_ICONO_MP4 = "/comun/recursos/imagenes/tipoarchivo/mp4.png";

	public static final String CONSTANTE_RUTA_ICONO_PNG = "/comun/recursos/imagenes/tipoarchivo/png.png";

	public static final String CONSTANTE_RUTA_ICONO_ZIP = "/comun/recursos/imagenes/tipoarchivo/zip.png";

	public static final String CONSTANTE_RUTA_ICONO_RAR = "/comun/recursos/imagenes/tipoarchivo/rar.png";

	/**
	 * indica la extension a validar para los documentos pdf.
	 */
	public static final String CONSTANTE_NOMBRE_EXTENSION_PDF = "pdf";

	/**
	 * indica la extension a validar para los documentos word.
	 */
	public static final String CONSTANTE_NOMBRE_EXTENSION_WORD = "doc";
	public static final String CONSTANTE_NOMBRE_EXTENSION_WORDX = "docx";
	/**
	 * indica la extension a validar para los documentos excel.
	 */
	public static final String CONSTANTE_NOMBRE_EXTENSION_EXCEL = "xls";
	public static final String CONSTANTE_NOMBRE_EXTENSION_EXCELX = "xlsx";

	public static final String CONSTANTE_NOMBRE_EXTENSION_PPT = "ppt";
	public static final String CONSTANTE_NOMBRE_EXTENSION_PPTX = "pptx";

	/**
	 * indica la extension a validar para los documentos txt.
	 */
	public static final String CONSTANTE_NOMBRE_EXTENSION_TXT = "txt";

	public static final String CONSTANTE_NOMBRE_EXTENSION_AVI = "avi";

	public static final String CONSTANTE_NOMBRE_EXTENSION_JPG = "jpg";

	public static final String CONSTANTE_NOMBRE_EXTENSION_MOV = "mov";

	public static final String CONSTANTE_NOMBRE_EXTENSION_MP3 = "mp3";

	public static final String CONSTANTE_NOMBRE_EXTENSION_MP4 = "mp4";

	public static final String CONSTANTE_NOMBRE_EXTENSION_PNG = "png";

	public static final String CONSTANTE_NOMBRE_EXTENSION_ZIP = "zip";
	public static final String CONSTANTE_NOMBRE_EXTENSION_RAR = "rar";

	/**
	 * constante para indicar la ruta de los documentos firmados, en caso no se
	 * encuentre en ParametrosMast, para la Firma Digital Web
	 */
	public static final String CONSTANTE_KEY_PARAM_URL_FIRMA_DIGITAL_1 = "URLFIRWEB1";
	public static final String CONSTANTE_KEY_PARAM_URL_FIRMA_DIGITAL_2 = "URLFIRWEB2";
	public static final String CONSTANTE_KEY_PARAM_RUTA_BASE = "URLFIRWEB3";

	public static final String CONSTANTE_KEY_URL_FIRMA_DIGITAL_DEFAULT_1 = "http://localhost:8080/frontend-erp-almacen/temporal/firmadigital/";
	public static final String CONSTANTE_KEY_URL_FIRMA_DIGITAL_DEFAULT_2 = "http://localhost:8080/frontend-erp-almacen/";
	public static final String CONSTANTE_KEY_URL_FIRMA_DIGITAL_RUTA_BASE = "/temporal/firmadigital/";

	/**
	 * constante para indicar el nivel de seguridad por defecto en cada elemento de
	 * un proceso.
	 */
	public static final String CONSTANTE_KEY_NIVEL_SEGURIDAD_TODOS = "TODOS";

	/**
	 * constante para indicar el tipo de seguridad por defecto en cada rol de un
	 * proceso.
	 */
	public static final String CONSTANTE_KEY_TIPO_SEGURIDAD_USUARIO = "USUARIO";

}
