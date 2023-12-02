package net.royal.spring.comun.boot;

public class SpringComunConstanteBoot {
	public static final String NOMBRE = "framework.comun";
	
	public static final Integer TOKEN_MINUTES = 600;
	public static final String TOKEN_JWTKEY = "4L3j4nDr0";
	
	public static final String PARAMETRO_SEGURIDAD_COMPANIA = "COMPANIASOCIO";
	public static final String PARAMETRO_SEGURIDAD_CLASIFICACION = "AGEVCLASIFIC";
	public static final String PARAMETRO_SEGURIDAD_SUCURSAL = "SUCURSAL";
	public static final String PARAMETRO_SEGURIDAD_UNIDAD_NEGOCIO = "UNIDAD_NEGOCIO";
	
	// TIPO DE RECURSOS - CUADERNOS DE CAMPO
	public static final Integer TIPO_RECURSO_COMBUSTIBLE = 1;
	public static final Integer TIPO_RECURSO_IMPLEMENTOS = 2;
	public static final Integer TIPO_RECURSO_SEMILLAS = 3;
	
	// GRUPO DE RECURSOS - CUADERNOS DE CAMPO
	public static final Integer GRUPO_ARTICULO = 1;
	public static final Integer GRUPO_MANO_OBRA = 2;
	public static final Integer GRUPO_MAQUINARIA = 3;
	public static final Integer GRUPO_SERVICIO = 4;
	public static final Integer GRUPO_OTROS = 5;
	
	
	//VALIDACIONES TIPO CAMBIO
	public static final String VAL_RESTRICCION_UNICA_TIPOCAMBIO = "El registro ya existe en el maestro.";
	public static final String VAL_COMPRA_NULO_TIPOCAMBIO = "Ingrese un valor de compra.";
	public static final String VAL_VENTA_NULO_TIPOCAMBIO = "Ingrese un valor de venta.";
	public static final String VAL_MONEDA_IGUAL_TIPOCAMBIO = "La moneda no puede ser igual a la moneda de cambio.";
	
	//VALIDACIONES PARAMETROS
	public static final String VAL_PARAM_RESTRICCION_UNICA_PARAMETRO = "El registro ya existe en el maestro.";
	public static final String VAL_PARAM_RESTRICCION_APLICACION_CODIGO = "El c\u00F3digo aplicaci\u00F3n no debe estar vac\u00EDo.";
	public static final String VAL_PARAM_RESTRICCION_COMPANIA_CODIGO = "La compa\u00F1\u00EDa no debe estar vac\u00EDa";
	public static final String VAL_PARAM_RESTRICCION_TABLA_CODIGO = "El c\u00F3digo del par\u00E1metro no debe estar vac\u00EDo.";
	public static final String VAL_PARAM_RESTRICCION_TABLA_DESCRIPCION = "La descripci\u00F3n del par\u00E1metro no debe estar vac\u00EDo.";
	public static final String VAL_PARAM_RESTRICCION_ESTADO = "El estado no debe estar vac\u00EDo.";
	public static final String VAL_PARAM_RESTRICCION_TIPO = "El tipo de dato no debe estar vac\u00EDo.";
	public static final String VAL_PARAM_RESTRICCION_TIPO_TEXTO = "El campo Texto no debe estar vac\u00EDo.";
	public static final String VAL_PARAM_RESTRICCION_TIPO_NUMERO = "El campo N\u00FAmero no debe estar vac\u00EDo.";
	public static final String VAL_PARAM_RESTRICCION_TIPO_FECHA = "El campo Fecha no debe estar vac\u00EDo.";
	
	
	
	//VALIDACIONES MISCELANEOS HEADER
	public static final String VAL_MISC_RESTRICCION_UNICA = "El registro ya existe en el maestro.";
	public static final String VAL_MISC_RESTRICCION_APLICACION_CODIGO = "El c\u00F3digo aplicaci\u00F3n no debe estar vac\u00EDo.";
	public static final String VAL_MISC_RESTRICCION_COMPANIA_CODIGO = "La compa\u00F1\u00EDa no debe estar vac\u00EDa";
	public static final String VAL_MISC_RESTRICCION_TABLA_CODIGO = "El c\u00F3digo de la tabla no debe estar vac\u00EDo.";
	public static final String VAL_MISC_RESTRICCION_ESTADO = "El estado no debe estar vac\u00EDo.";
	public static final String VAL_MISC_D_RESTRICCION_CODIGO_PERIODO = "El c\u00F3digo del periodo es requerido.";
	public static final String VAL_MISC_D_RESTRICCION_REPORTE = "El reporte es requerido.";
	public static final String VAL_MISC_D_RESTRICCION_TIPO_RECURSO = "El tipo es requerido.";
	
	//VALIDACIONES MISCELANEOS DETALLE		
	public static final String VAL_MISC_D_RESTRICCION_CODIGO_ELEMENTO = "El c\u00F3digo del elemento no debe estar vac\u00EDo.";
	public static final String VAL_MISC_D_RESTRICCION_DESCRIPCION = "La descripci\u00F3n no debe estar vac\u00EDo.";
	public static final String VAL_MISC_D_RESTRICCION_ESTADO = "El estado no debe estar vac\u00EDo.";
	
	
	//VALIDACIONES SY REPORTE
	public static final String VAL_RE_RESTRICCION_UNICA = "El registro ya existe.";
	public static final String VAL_RE_RESTRICCION_APLICACION_CODIGO = "El c\u00F3digo aplicaci\u00F3n no debe estar vac\u00EDo.";
	public static final String VAL_RE_RESTRICCION_COMPANIA_CODIGO = "La compa\u00F1\u00EDa no debe estar vac\u00EDa";
	public static final String VAL_RE_RESTRICCION_TABLA_CODIGO = "El c\u00F3digo del reporte no debe estar vac\u00EDo.";
	public static final String VAL_RE_RESTRICCION_NOMBRE = "El nombre no debe estar vac\u00EDo.";
	public static final String VAL_RE_RESTRICCION_TIPO_REPORTE = "El tipo de reporte no debe estar vac\u00EDo.";
	public static final String VAL_RE_RESTRICCION_ESTADO = "El estado no debe estar vac\u00EDo.";
	
	//VALIDACIONES SY REPORTE ARCHIVO
	public static final String VAL_RE_AR_RESTRICCION_UNICA = "El registro ya existe.";
	public static final String VAL_RE_AR_RESTRICCION_COMPANIA_CODIGO = "La compa\u00F1\u00EDa no debe estar vac\u00EDa";
	public static final String VAL_RE_AR_RESTRICCION_PERIODO = "El periodo no debe estar vac\u00EDa";
	public static final String VAL_RE_AR_RESTRICCION_VERSION = "La versi\u00F3n no debe estar vac\u00EDa";
	public static final String VAL_RE_AR_RESTRICCION_ARCHIVO = "El archivo est\u00E1 vac\u00EDo";
	
	//VALIDACIONES COMMUNITYSUB
	public static final String VAL_COMMU_RESTRICCION_CODIGO = "El c\u00F3digo community no debe estar vac\u00EDo.";
	public static final String VAL_COMMU_RESTRICCION_DESCRIPCION = "La descripci\u00F3n local no debe estar vac\u00EDo.";
	public static final String VAL_COMMU_RESTRICCION_CLASIFICACION = "La clasificaci\u00F3n no debe estar vac\u00EDo.";
	public static final String VAL_COMMU_RESTRICCION_ESTADO = "El estado no debe estar vac\u00EDo.";
	public static final String VAL_COMMU_UNICA_COMMUNITY = "El c\u00F3digo community ya existe";
	public static final String VAL_COMMU_UNICA_COMMUNITYSUB = "El registro de la subclase ya existe en el maestro.";
	public static final String VAL_COMMUSUB_RESTRICCION_CODIGO = "El c\u00F3digo sub clase no debe estar vac\u00EDo.";
	public static final String VAL_COMMUSUB_RESTRICCION_DESCRIPCION = "La descripci\u00F3n sub clase no debe estar vac\u00EDo.";
	public static final String VAL_COMMUSUB_RESTRICCION_UNIDAD = "La unidad no debe estar vac\u00EDo.";
	
	//VALIDACIONES LINEA-FAMILIA
	public static final String VAL_LINEA_VACIO_CODIGO = "La linea no debe estar vac\u00EDo.";
	public static final String VAL_LINEA_VACIO_DESCRIPCION = "La descripci\u00F3n local no debe estar vac\u00EDo.";
	public static final String VAL_LINEA_VACIO_DESCRIPCION_INGLES = "La descripci\u00F3n ingles no debe estar vac\u00EDo.";
	public static final String VAL_LINEA_VACIO_ESTADO = "El estado no debe estar vac\u00EDo.";
	public static final String VAL_LINEA_UNICA_CODIGO = "La linea ya existe";
	public static final String VAL_LINEAF_UNICA_FAMILIA = "El c\u00F3digo de familia ya existe en el maestro.";
	public static final String VAL_LINEAF_VACIO_CODIGO = "El c\u00F3digo de familia no debe estar vac\u00EDo.";
	public static final String VAL_LINEAF_VACIO_DESCRIPCION = "La descripci\u00F3n de familia no debe estar vac\u00EDo.";
	public static final String VAL_LINEAF_VACIO_ESTADO = "El estado de la familia no debe estar vac\u00EDo.";
	
	public static final String VAL_LINEAF_CUENTA1_VALID= "La cuenta de inventario no existe.";
	public static final String VAL_LINEAF_CUENTA1_ESTADO= "La cuenta de inventario no est\u00E1 activa.";
	
	public static final String VAL_LINEAF_CUENTA2_VALID= "La cuenta de gastos no existe.";
	public static final String VAL_LINEAF_CUENTA2_ESTADO= "La cuenta de gastos no est\u00E1 activa.";
	
	public static final String VAL_LINEAF_CUENTA3_VALID= "La cuenta de ventas no existe.";
	public static final String VAL_LINEAF_CUENTA3_ESTADO= "La cuenta de ventas no est\u00E1 activa.";
	
	public static final String VAL_LINEAF_CUENTA4_VALID= "La cuenta de tr\u00E1nsito no existe.";
	public static final String VAL_LINEAF_CUENTA4_ESTADO= "La cuenta de tr\u00E1nsito no est\u00E1 activa.";

	//VALIDACIONES APLICACIONMAST
	public static final String VAL_APLI_UNICA_APLICACION_CODIGO = "El c\u00F3digo aplicaci\u00F3n ya existe.";
	public static final String VAL_APLI_RESTRICCION_APLICACION_CODIGO = "El c\u00F3digo aplicaci\u00F3n no debe estar vac\u00EDo.";
	public static final String VAL_APLI_RESTRICCION_TABLA_DESCRIPCION = "La descripci\u00F3n no debe estar vac\u00EDo.";
	public static final String VAL_APLI_RESTRICCION_ESTADO = "El estado no debe estar vac\u00EDo.";
	public static final String VAL_APLI_RESTRICCION_PERIODO = "El periodo contable no debe estar vac\u00EDo.";
	public static final String VAL_APLI_RESTRICCION_PERIODO_LENGTH = "El periodo contable debe tener 6 caracteres.";
	public static final String VAL_APLI_RESTRICCION_PERIODO_NO_VALID = "El periodo contable no es valido.";
	public static final String VAL_APLI_RESTRICCION_DEPARTAMENTO = "Debe seleccionar un departamento.";
	public static final String VAL_APLI_CODIGO_SISTEMA = "Debe seleccionar un c\u00F3digo sistema fuente.";
	
	//VALIDACIONES CORRELATIVOMAST
	public static final String VAL_CORRE_UNICA_APLICACION_CODIGO = "El correlativo ya existe.";
	public static final String VAL_CORRE_RESTRICCION_TIPO = "Debe seleccionar un tipo correlativo.";
	public static final String VAL_CORRE_RESTRICCION_COMPANIA = "Debe seleccionar una compa\u00F1\u00EDa.";
	public static final String VAL_CORRE_RESTRICCION_SERIE = "El c\u00F3digo de serie no debe estar vac\u00EDo.";
	public static final String VAL_CORRE_RESTRICCION_DESCRIPCION = "La descripci\u00F3n no debe estar vac\u00EDo.";
	public static final String VAL_CORRE_RESTRICCION_NUMERO_ACTUAL = "El n\u00FAmero actual no debe estar vac\u00EDo.";
	public static final String VAL_CORRE_RESTRICCION_RANGO_DESDE = "El rango desde no debe estar vac\u00EDo.";
	public static final String VAL_CORRE_RESTRICCION_HASTA = "El hasta no debe estar vac\u00EDo.";
	public static final String VAL_CORRE_RESTRICCION_ESTADO = "El estado no debe estar vac\u00EDo.";
	public static final String VAL_CORRE_VALID_NUMERO_ACTUAL = "El n\u00FAmero actual debe ser mayor o igual a 0.";
	public static final String VAL_CORRE_VALID_RANGO = "El n\u00FAmero actual debe ser mayor o igual a rango desde y menor o igual a hasta.";
	
	
	public static final String URL_PROVEEDOR = "mantenimientoregistro/";
	public static final String JSONARMADO = "/{\"id\":";
	
	
	
	//CONSTANTES PARA ADJUNTOS INGESO DE PRECIO - PROVEEDOR
	public static final String MODULO = "WH";
	public static final String TIPODOCUMENTO = "QH";
	public static final Integer LINEACERO = 0;
	
	public static final String VAL_VOUCHER_NUMBER = "Se ha llegado al m\u00E1ximo # de vouchers permitido para el periodo.";
	public static final String VAL_VOUCHER_VALID_PERIODO = "El periodo no debe estar vac\u00EDo.";
	public static final String VAL_VOUCHER_VALID_COMPANY = "La compa\u00F1\u00EDa socio no debe estar vac\u00EDo.";
	public static final String VAL_VOUCHER_VALID_NUMERO = "El n\u00FAmero de voucher no debe estar vac\u00EDo.";
	public static final String VAL_VOUCHER_VALID_LIBRO = "El libro contable no debe estar vac\u00EDo.";
	public static final String VAL_VOUCHER_VALID_DEPARTAMENTO = "El departamento no debe estar vac\u00EDo.";
	public static final String VAL_VOUCHER_VALID_UNIDAD_NEGOCIO = "La unidad de negocio no debe estar vac\u00EDo.";
	public static final String VAL_VOUCHER_VALID_MONEDA = "La moneda no debe estar vac\u00EDo.";
	public static final String VAL_VOUCHER_VALID_TIPO_VOUCHER = "El tipo de voucher no debe estar vac\u00EDo.";
	public static final String VAL_VOUCHER_VALID_TIPO_CAMBIO_MONEY = "El tipo de cambio no debe estar vac\u00EDo.";
	public static final String VAL_VOUCHER_VALID_TIPO_CAMBIO_FECHA = "El tipo de cambio  no debe estar vac\u00EDo.";
	public static final String VAL_VOUCHER_VALID_FECHA = "La fecha no debe estar vac\u00EDo.";
	public static final String VAL_VOUCHER_VALID_TITULO = "La descripci\u00F3n no debe estar vac\u00EDo.";
	public static final String VAL_VOUCHER_VALID_PERIOD = "La Fecha del voucher no pertenece al per\u00EDodo contable.";

	public static final String VAL_VOUCHER_VALID_REF = "Existen campos obligatorios correspondientes a la cuenta ";

	public static final String VAL_VOUCHER_DUPLICATE = "El Voucher ya existe";
	public static final String VAL_VOUCHER_LEDGER = "El Libro Contable no es valido";
	public static final String VAL_VOUCHER_BUSNIT_UNIT = "La Unidad de Negocio no es valida";
	public static final String VAL_VOUCHER_DEPARTMENT = "El Departamento no existe";
	public static final String VAL_VOUCHER_VOUCHER_SOURCE = "El Origen del Voucher no es valido";
	public static final String VAL_VOUCHER_CURRENCY = "La moneda no es valida";
	public static final String VAL_VOUCHER_VOUCHER_TYPE = "El tipo de conversion no es valido";
}
