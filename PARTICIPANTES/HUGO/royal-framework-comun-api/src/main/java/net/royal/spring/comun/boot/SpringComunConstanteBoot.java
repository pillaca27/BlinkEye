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
	public static final String VAL_PARAM_RESTRICCION_APLICACION_CODIGO = "El código aplicación no debe estar vacío.";
	public static final String VAL_PARAM_RESTRICCION_COMPANIA_CODIGO = "La compañía no debe estar vacía";
	public static final String VAL_PARAM_RESTRICCION_TABLA_CODIGO = "El código del parámetro no debe estar vacío.";
	public static final String VAL_PARAM_RESTRICCION_TABLA_DESCRIPCION = "La descripción del parámetro no debe estar vacío.";
	public static final String VAL_PARAM_RESTRICCION_ESTADO = "El estado no debe estar vacío.";
	public static final String VAL_PARAM_RESTRICCION_TIPO = "El tipo de dato no debe estar vacío.";
	public static final String VAL_PARAM_RESTRICCION_TIPO_TEXTO = "El campo Texto no debe estar vacío.";
	public static final String VAL_PARAM_RESTRICCION_TIPO_NUMERO = "El campo Número no debe estar vacío.";
	public static final String VAL_PARAM_RESTRICCION_TIPO_FECHA = "El campo Fecha no debe estar vacío.";
	
	
	
	//VALIDACIONES MISCELANEOS HEADER
	public static final String VAL_MISC_RESTRICCION_UNICA = "El registro ya existe en el maestro.";
	public static final String VAL_MISC_RESTRICCION_APLICACION_CODIGO = "El código aplicación no debe estar vacío.";
	public static final String VAL_MISC_RESTRICCION_COMPANIA_CODIGO = "La compañía no debe estar vacía";
	public static final String VAL_MISC_RESTRICCION_TABLA_CODIGO = "El código de la tabla no debe estar vacío.";
	public static final String VAL_MISC_RESTRICCION_ESTADO = "El estado no debe estar vacío.";
	public static final String VAL_MISC_D_RESTRICCION_CODIGO_PERIODO = "El código del periodo es requerido.";
	public static final String VAL_MISC_D_RESTRICCION_REPORTE = "El reporte es requerido.";
	public static final String VAL_MISC_D_RESTRICCION_TIPO_RECURSO = "El tipo es requerido.";
	
	//VALIDACIONES MISCELANEOS DETALLE		
	public static final String VAL_MISC_D_RESTRICCION_CODIGO_ELEMENTO = "El código del elemento no debe estar vacío.";
	public static final String VAL_MISC_D_RESTRICCION_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_MISC_D_RESTRICCION_ESTADO = "El estado no debe estar vacío.";
	
	
	//VALIDACIONES SY REPORTE
	public static final String VAL_RE_RESTRICCION_UNICA = "El registro ya existe.";
	public static final String VAL_RE_RESTRICCION_APLICACION_CODIGO = "El código aplicación no debe estar vacío.";
	public static final String VAL_RE_RESTRICCION_COMPANIA_CODIGO = "La compañía no debe estar vacía";
	public static final String VAL_RE_RESTRICCION_TABLA_CODIGO = "El código del reporte no debe estar vacío.";
	public static final String VAL_RE_RESTRICCION_NOMBRE = "El nombre no debe estar vacío.";
	public static final String VAL_RE_RESTRICCION_TIPO_REPORTE = "El tipo de reporte no debe estar vacío.";
	public static final String VAL_RE_RESTRICCION_ESTADO = "El estado no debe estar vacío.";
	
	//VALIDACIONES SY REPORTE ARCHIVO
	public static final String VAL_RE_AR_RESTRICCION_UNICA = "El registro ya existe.";
	public static final String VAL_RE_AR_RESTRICCION_COMPANIA_CODIGO = "La compañía no debe estar vacía";
	public static final String VAL_RE_AR_RESTRICCION_PERIODO = "El periodo no debe estar vacía";
	public static final String VAL_RE_AR_RESTRICCION_VERSION = "La versión no debe estar vacía";
	public static final String VAL_RE_AR_RESTRICCION_ARCHIVO = "El archivo está vacío";
	
	//VALIDACIONES COMMUNITYSUB
	public static final String VAL_COMMU_RESTRICCION_CODIGO = "El código community no debe estar vacío.";
	public static final String VAL_COMMU_RESTRICCION_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_COMMU_RESTRICCION_CLASIFICACION = "La clasificación no debe estar vacío.";
	public static final String VAL_COMMU_RESTRICCION_ESTADO = "El estado no debe estar vacío.";
	public static final String VAL_COMMU_UNICA_COMMUNITY = "El código community ya existe";
	public static final String VAL_COMMU_UNICA_COMMUNITYSUB = "El registro de la subclase ya existe en el maestro.";
	public static final String VAL_COMMUSUB_RESTRICCION_CODIGO = "El código sub clase no debe estar vacío.";
	public static final String VAL_COMMUSUB_RESTRICCION_DESCRIPCION = "La descripción sub clase no debe estar vacío.";
	public static final String VAL_COMMUSUB_RESTRICCION_UNIDAD = "La unidad no debe estar vacío.";
	
	//VALIDACIONES LINEA-FAMILIA
	public static final String VAL_LINEA_VACIO_CODIGO = "La linea no debe estar vacío.";
	public static final String VAL_LINEA_VACIO_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_LINEA_VACIO_DESCRIPCION_INGLES = "La descripción ingles no debe estar vacío.";
	public static final String VAL_LINEA_VACIO_ESTADO = "El estado no debe estar vacío.";
	public static final String VAL_LINEA_UNICA_CODIGO = "La linea ya existe";
	public static final String VAL_LINEAF_UNICA_FAMILIA = "El código de familia ya existe en el maestro.";
	public static final String VAL_LINEAF_VACIO_CODIGO = "El código de familia no debe estar vacío.";
	public static final String VAL_LINEAF_VACIO_DESCRIPCION = "La descripción de familia no debe estar vacío.";
	public static final String VAL_LINEAF_VACIO_ESTADO = "El estado de la familia no debe estar vacío.";
	
	public static final String VAL_LINEAF_CUENTA1_VALID= "La cuenta de inventario no existe.";
	public static final String VAL_LINEAF_CUENTA1_ESTADO= "La cuenta de inventario no está activa.";
	
	public static final String VAL_LINEAF_CUENTA2_VALID= "La cuenta de gastos no existe.";
	public static final String VAL_LINEAF_CUENTA2_ESTADO= "La cuenta de gastos no está activa.";
	
	public static final String VAL_LINEAF_CUENTA3_VALID= "La cuenta de ventas no existe.";
	public static final String VAL_LINEAF_CUENTA3_ESTADO= "La cuenta de ventas no está activa.";
	
	public static final String VAL_LINEAF_CUENTA4_VALID= "La cuenta de tránsito no existe.";
	public static final String VAL_LINEAF_CUENTA4_ESTADO= "La cuenta de tránsito no está activa.";

	//VALIDACIONES APLICACIONMAST
	public static final String VAL_APLI_UNICA_APLICACION_CODIGO = "El código aplicación ya existe.";
	public static final String VAL_APLI_RESTRICCION_APLICACION_CODIGO = "El código aplicación no debe estar vacío.";
	public static final String VAL_APLI_RESTRICCION_TABLA_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_APLI_RESTRICCION_ESTADO = "El estado no debe estar vacío.";
	public static final String VAL_APLI_RESTRICCION_PERIODO = "El periodo contable no debe estar vacío.";
	public static final String VAL_APLI_RESTRICCION_PERIODO_LENGTH = "El periodo contable debe tener 6 caracteres.";
	public static final String VAL_APLI_RESTRICCION_PERIODO_NO_VALID = "El periodo contable no es valido.";
	public static final String VAL_APLI_RESTRICCION_DEPARTAMENTO = "Debe seleccionar un departamento.";
	public static final String VAL_APLI_CODIGO_SISTEMA = "Debe seleccionar un código sistema fuente.";
	
	//VALIDACIONES CORRELATIVOMAST
	public static final String VAL_CORRE_UNICA_APLICACION_CODIGO = "El correlativo ya existe.";
	public static final String VAL_CORRE_RESTRICCION_TIPO = "Debe seleccionar un tipo correlativo.";
	public static final String VAL_CORRE_RESTRICCION_COMPANIA = "Debe seleccionar una compañía.";
	public static final String VAL_CORRE_RESTRICCION_SERIE = "El código de serie no debe estar vacío.";
	public static final String VAL_CORRE_RESTRICCION_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_CORRE_RESTRICCION_NUMERO_ACTUAL = "El número actual no debe estar vacío.";
	public static final String VAL_CORRE_RESTRICCION_RANGO_DESDE = "El rango desde no debe estar vacío.";
	public static final String VAL_CORRE_RESTRICCION_HASTA = "El hasta no debe estar vacío.";
	public static final String VAL_CORRE_RESTRICCION_ESTADO = "El estado no debe estar vacío.";
	public static final String VAL_CORRE_VALID_NUMERO_ACTUAL = "El número actual debe ser mayor o igual a 0.";
	public static final String VAL_CORRE_VALID_RANGO = "El número actual debe ser mayor o igual a rango desde y menor o igual a hasta.";
	
	
	public static final String URL_PROVEEDOR = "mantenimientoregistro/";
	public static final String JSONARMADO = "/{\"id\":";
	
	
	
	//CONSTANTES PARA ADJUNTOS INGESO DE PRECIO - PROVEEDOR
	public static final String MODULO = "WH";
	public static final String TIPODOCUMENTO = "QH";
	public static final Integer LINEACERO = 0;
	
	
	
	
	
	
	//WH
	public static String PROCESO_PROVEEDORES_ESTADO_ACTIVO = "AC";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
