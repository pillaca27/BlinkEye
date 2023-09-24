package net.royal.spring.comun.boot;

public class SpringWhConstanteBoot {
	public static final String NOMBRE = "erp.wh";

	public static final Integer TOKEN_MINUTES = 600;

	public static final String APLICACION_CODIGO = "WH";
	public static final String ORDEN_COMPRA = "OC";
	public static final String CONTRATO = "CO";
	public static final String DEFAULT_COMPANIA = "999999";

	public static final String PARAMETRO_VALOR_MERCADO = "VALMERCADO";

	public static final String DIRECTORIO_ITEMS_UPLOAD = "items";
	
	public static final String CONTENT_TYPE_APPLICATION_PDF_VALUE = "application/pdf";
	public static final String CONTENT_TYPE_APPLICATION_XML_VALUE = "application/xml";
	public static final String CONTENT_TYPE_APPLICATION_XLS_VALUE = "application/xls";
	public static final String HTTP_HEADER_CONTENT_DISPOSITION = "Content-Disposition";
	public static final String CONTENT_DISPOSITION_INLINE_FORMAT_PDF = "inline; filename=\"%s.pdf\"";
	public static final String CONTENT_DISPOSITION_INLINE_FORMAT_XLS = "inline; filename=\"%s.xls\"";
	public static final String CONTENT_DISPOSITION_INLINE_FORMAT_XML = "inline; filename=\"%s.xml\"";
	
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
	public static final String VAL_CORRE_RESTRICCION_TIPO = "Debe seleccionar un tipo comprobante.";
	public static final String VAL_CORRE_RESTRICCION_COMPANIA = "Debe seleccionar una compañía.";
	public static final String VAL_CORRE_RESTRICCION_SERIE = "El código de serie no debe estar vacío.";
	public static final String VAL_CORRE_RESTRICCION_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_CORRE_RESTRICCION_NUMERO_ACTUAL = "El número actual no debe estar vacío.";
	public static final String VAL_CORRE_RESTRICCION_RANGO_DESDE = "El rango desde no debe estar vacío.";
	public static final String VAL_CORRE_RESTRICCION_HASTA = "El hasta no debe estar vacío.";
	public static final String VAL_CORRE_RESTRICCION_ESTADO = "El estado no debe estar vacío.";
	public static final String VAL_CORRE_VALID_NUMERO_ACTUAL = "El número actual debe ser mayor o igual a 0.";
	public static final String VAL_CORRE_VALID_RANGO = "El número actual debe ser mayor o igual a rango desde y menor o igual a hasta.";

	// VALIDACIONES PERSONAMAST
	public static final String VAL_CORRE_UNICA_APLICACION_CODIGO = "El correlativo ya existe.";
	public static final String VAL_PER_RESTRICCION_TIPO_PERSONA = "Debe seleccionar un tipo de persona.";
	public static final String VAL_PER_RESTRICCION_TIPO_DOC = "Debe seleccionar un tipo de documento.";
	public static final String VAL_PER_RESTRICCION_DOCUMENTO = "El documento no debe estar vacío.";
	public static final String VAL_PER_RESTRICCION_DOCUMENTO_IDENTIDAD = "El documento de identidad no debe estar vacío.";
	public static final String VAL_PER_RESTRICCION_NOMBRES = "El nombre no debe estar vacío.";
	public static final String VAL_PER_RESTRICCION_APE_PATERNO = "El apellido paterno no debe estar vacío.";
	public static final String VAL_PER_RESTRICCION_APE_MATERNO = "El apellido materno no debe estar vacío.";
	public static final String VAL_PER_RESTRICCION_DOCUMENTO_FISCAL = "El documento fiscal no debe estar vació.";
	public static final String VAL_PER_RESTRICCION_DOCUMENTO_FISCAL_LIMIT = "El documento fiscal no es válido.";
	public static final String VAL_PER_RESTRICCION_DOCUMENTO_RUC_VALID = "El RUC no es válido.";
	public static final String VAL_PER_RESTRICCION_DOCUMENTO_RUC = "El RUC ya se encuentra registrado";
	public static final String VAL_PER_VALID_BUSQUEDA = "El campo búsqueda ya se encuentra registrado";
	public static final String VAL_PER_RESTRICCION_DNI = "El DNI ya se encuentra registrado.";
	public static final String VAL_PER_VALID_DOCUMENTO_FISCAL = "El documento fiscal ya se encuentra registrado";
	public static final String VAL_PER_VALID_DOCUMENTO = "El documento ya se encuentra registrado";
	public static final String VAL_PER_VALID_DOCUMENTO_IDENTIDAD = "El documento de identidad ya se encuentra registrado";
	public static final String VAL_PER_RESTRICCION_SEXO = "El sexo no debe estar vacío.";
	public static final String VAL_PER_RESTRICCION_BUSQUEDA = "El campo búsqueda no debe estar vacío.";

	// VALIDACIONES GRUPO PERSONAS
	public static final String VAL_GRPER_RESTRICCION_GRUPO = "El campo grupo no debe estar vacío.";
	public static final String VAL_GRPER_RESTRICCION_DESCRI = "La descripción local no debe estar vacío.";
	public static final String VAL_GRPER_UNICO_GRUPO = "El grupo ya se encuentra registrado.";

	// VALIDACIONES PROVEEDORMAST
	public static final String VAL_PROV_RESTRICCION_TIPO_DOCUMENTO = "El tipo documento no debe estar vacío.";
	public static final String VAL_PROV_TIPO_PAGO = "El tipo de pago no debe estar vacío.";
	public static final String VAL_PROV_TIPO_SERVICIO = "El tipo de servicio no debe estar vacío.";
	public static final String VAL_PROV_MONEDA_PAGO = "La moneda de pago no debe estar vacío.";
	public static final String VAL_PROV_FORMA_PAGO = "La forma de pago no debe estar vacío.";
	public static final String VAL_PROV_FECHA_EXONERADO = "La fecha exonerado de renta no debe estar vacío.";
	public static final String VAL_PROV_DETRACCION_SELECTOR = "El código de detracción no debe estar vació.";
	public static final String VAL_PROV_CUENTA_DETRACCION = "La cuenta detracción no debe estar vació.";

	// VALIDACIONES EMPLEADOMAST
	public static final String VAL_EMP_USUARIO_RED = "El usuario de red no debe estar vacío.";
	public static final String VAL_EMP_UNIDAD_NEGOCIO = "La unidad de negocio no debe estar vacío.";
	public static final String VAL_EMP_RESPONSABLE = "El responsable no debe estar vacío..";
	public static final String VAL_EMP_CENTRO_COSTOS = "El área usuaria no debe estar vacío.";
	public static final String VAL_EMP_DEPARTAMENTO = "El departamento no debe estar vacío.";
	public static final String VAL_EMP_ELIMINAR = "No se puede eliminar el empleado porque tiene registros asociados en el sistema de planillas";

	// VALIDACIONES RESPONSABLEMAST
	public static final String VAL_RES_RESPONSABLE = "El código del responsable no debe estar vacío.";
	public static final String VAL_RES_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_RES_RESPONSABLE_VALID = "El código del responsable ya se encuentra registrado.";

	// VALIDACIONES DEPARTAMENTOMAST
	public static final String VAL_DEP_DEPARTAMENTO = "El código del departamento no debe estar vacío.";
	public static final String VAL_DEP_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_DEP_DEPARTAMENTO_VALID = "El código del departamento ya se encuentra registrado.";

	// VALIDACIONES TIPOPAGO
	public static final String VAL_TIPPAGO_CODIGO = "El código del tipo de pago no debe estar vacío.";
	public static final String VAL_TIPPAGO_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_TIPPAGO_CODIGO_VALID = "El código del tipo de pago ya se encuentra registrado.";

	// VALIDACIONES IMPUESTOS
	public static final String VAL_IMPUESTO_CODIGO = "El código del impuesto no debe estar vacío.";
	public static final String VAL_IMPUESTO_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_IMPUESTO_CODIGO_VALID = "El código del impuesto ya se encuentra registrado.";

	public static final String VAL_IMPUESTO_SOLES = "La cuenta en moneda local no debe estar vacío.";
	public static final String VAL_IMPUESTO_SOLES_VALID = "La cuenta en moneda local no existe.";
	public static final String VAL_IMPUESTO_SOLES_ESTADO = "La cuenta en moneda local no está activa.";

	public static final String VAL_IMPUESTO_DOLARES = "El cuenta en moneda dolares no debe estar vacío.";
	public static final String VAL_IMPUESTO_DOLAR_VALID = "La cuenta en moneda dolares no existe.";
	public static final String VAL_IMPUESTO_DOLAR_ESTADO = "La cuenta en moneda dolares no está activa.";

	public static final String VAL_IMPUESTO_PORCENTAJE = "El porcentaje no debe estar vacío.";
	public static final String VAL_IMPUESTO_PORCENTAJE_100 = "El porcentaje no debe ser mayor a 100.";

	// VALIDACIONES BANCOS
	public static final String VAL_BANCO_CODIGO = "El código de banco no debe estar vacío.";
	public static final String VAL_BANCO_DESCRIPCION_CORTA = "La descripción no debe estar vacío.";
	public static final String VAL_BANCO_DESCRIPCION_LARGA = "La descripción larga no debe estar vacío.";
	public static final String VAL_BANCO_PERSONA = "La persona (# en maestro) no debe estar vacío.";
	public static final String VAL_BANCO_CHEQUE = "El formato de cheque no debe estar vacío.";
	public static final String VAL_BANCO_CONCILIACION = "La conciliación no debe estar vacío.";
	public static final String VAL_BANCO_CODIGO_VALID = "El código de banco ya se encuentra registrado.";

	// VALIDACIONES TIPO DOCUMENTO
	public static final String VAL_TIP_DOC_CODIGO = "El tipo documento no debe estar vacío.";
	public static final String VAL_TIP_DOC_DESCRIPCION_CORTA = "La descripción no debe estar vacío.";
	public static final String VAL_TIP_DOC_CLASIFICACION = "La clasificación no debe estar vacío.";
	public static final String VAL_TIP_DOC_REGIMEN = "El régimen legal no debe estar vacío.";
	public static final String VAL_TIP_DOC_VOUCHER = "El tipo de voucher no debe estar vacío.";
	public static final String VAL_TIP_DOC_PROVICION_LOCAL = "La cuenta contable local no debe estar vacío.";
	public static final String VAL_TIP_DOC_PROVICION_VALID = "La cuenta contable local no existe.";
	public static final String VAL_TIP_DOC_PROVICION_ESTADO = "La cuenta contable local no está activa.";

	public static final String VAL_TIP_DOC_PROVICION_DOLAR = "La cuenta contable dolares no debe estar vacío.";
	public static final String VAL_TIP_DOC_PROVICION_DOLAR_VALID = "La cuenta contable dolares no existe.";
	public static final String VAL_TIP_DOC_PROVICION_DOLAR_ESTADO = "La cuenta contable dolares no está activa.";

	public static final String VAL_TIP_DOC_PROV2_LOCAL = "La cuenta provisional local no debe estar vacío.";
	public static final String VAL_TIP_DOC_PROV2_LOCAL_VALID = "La cuenta provisional local no existe.";
	public static final String VAL_TIP_DOC_PROV2_LOCAL_ESTADO = "La cuenta provisional local no está activa.";

	public static final String VAL_TIP_DOC_PROV2_DOLAR = "La cuenta provisional dolares no debe estar vacío.";
	public static final String VAL_TIP_DOC_PROV2_DOLAR_VALID = "La cuenta provisional dolares no existe.";
	public static final String VAL_TIP_DOC_PROV2_DOLAR_ESTADO = "La cuenta provisional dolares no está activa.";

	public static final String VAL_TIP_DOC_ADELANTO_LOCAL = "La cuenta adelantos local no debe estar vacío.";
	public static final String VAL_TIP_DOC_ADELANTO_VALID = "La cuenta adelantos local no existe.";
	public static final String VAL_TIP_DOC_ADELANTO_ESTADO = "La cuenta adelantos local no está activa.";

	public static final String VAL_TIP_DOC_SUNAT = "El código SUNAT no debe estar vacío.";

	public static final String VAL_TIP_DOC_ADELANTO_DOLAR = "La cuenta adelantos dolares no debe estar vacío.";
	public static final String VAL_TIP_DOC_ADELANTO_DOLAR_VALID = "La cuenta adelantos dolares no existe.";
	public static final String VAL_TIP_DOC_ADELANTO_DOLAR_ESTADO = "La cuenta adelantos dolares no está activa.";

	public static final String VAL_TIP_DOC_CODIGO_VALID = "El tipo documento ya se encuentra registrado.";

	// VALIDACIONES BANCOS
	public static final String VAL_TIP_SERVICIO_CODIGO = "El código de tipo de servicio no debe estar vacío.";
	public static final String VAL_TIP_SERVICIO_DESCRI = "La descripción no debe estar vacío.";
	public static final String VAL_TIP_SERVICIO_REGIMEN = "El régimen legal no debe estar vacío.";
	public static final String VAL_TIP_SERVICIO_CODIGO_VALID = "El código de tipo de servicio ya se encuentra registrado.";

	// VALIDACIONES COMPANIAS
	public static final String VAL_COMPANIA_CODIGO = "El código de compañia no debe estar vacío.";
	public static final String VAL_COMPANIA_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_COMPANIA_DESCRIPCION_COMPLETA = "La descripción completa no debe estar vacío.";
	public static final String VAL_COMPANIA_TIPO = "El tipo compañia no debe estar vacío.";
	public static final String VAL_COMPANIA_PERSONA = "La persona no debe estar vacío.";
	public static final String VAL_COMPANIA_DOCUMENTO = "EL documento fiscal no debe estar vacío.";
	public static final String VAL_COMPANIA_SOCIO = "EL socio principal no debe estar vacío.";
	public static final String VAL_COMPANIA_DIRECCION = "La dirección no debe estar vacío.";
	public static final String VAL_COMPANIA_MONEDA = "La moneda no debe estar vacío.";
	public static final String VAL_COMPANIA_CODIGO_VALID = "El código de compañia ya se encuentra registrado.";

	// VALIDACIONES UNIDAD NEGOCIO
	public static final String VAL_UN_NEGOCIO_CODIGO = "La unidad de negocio no debe estar vacío.";
	public static final String VAL_UN_NEGOCIO_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_UN_NEGOCIO_CODIGO_VALID = "La unidad de negocio ya se encuentra registrado.";

	// VALIDACIONES UNIDAD REPLICACION
	public static final String VAL_UN_REPLICACION_CODIGO = "La unidad replicación no debe estar vacío.";
	public static final String VAL_UN_REPLICACION_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_UN_REPLICACION_INICIO = "El rango persona inicio no debe estar vacío.";
	public static final String VAL_UN_REPLICACION_FIN = "El rango persona fin no debe estar vacío.";
	public static final String VAL_UN_REPLICACION_PERSONA = "El N° persona actual no debe estar vacío.";
	public static final String VAL_UN_REPLICACION_CODIGO_VALID = "La unidad replicación ya se encuentra registrado.";
	public static final String VAL_UN_REPLICACION_CODIGO_VALID_CHAR = "La unidad replicación debe tener 4 dígitos.";

	// VALIDACIONES TIPO CAMBIO
	public static final String VAL_TIPO_CAMBIO = "La fecha de tipo cambio ya se encuentra registrado.";

	// VALIDACIONES DEPARTAMENTOS GEOGRAFICOS
	public static final String VAL_DEP_GEO_CODIGO = "El código de departamento no debe estar vacío.";
	public static final String VAL_DEP_GEO_DESCRI = "La descripción no debe estar vacío.";
	public static final String VAL_DEP_GEO_CODIGO_VALID = "El código de departamento ya se encuentra registrado.";

	// VALIDACIONES ACCOUNTS
	public static final String VAL_ACCOUNT_CODIGO = "El código de cuenta mayor no debe estar vacío.";
	public static final String VAL_ACCOUNT_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_ACCOUNT_MONEDA = "La moneda base no debe estar vacío.";
	public static final String VAL_ACCOUNT_CUENTA1 = "La cuenta A.C.M. no debe estar vacío.";
	public static final String VAL_ACCOUNT_CUENTA1_VALID = "La cuenta A.C.M. no existe.";
	public static final String VAL_ACCOUNT_CUENTA1_ESTADO = "La cuenta A.C.M. no está activa.";
	public static final String VAL_ACCOUNT_ELEMENTO = "El elemento de gasto no debe estar vació";

	public static final String VAL_ACCOUNT_CUENTA2 = "La cuenta REI no debe estar vacío.";
	public static final String VAL_ACCOUNT_CUENTA2_VALID = "La cuenta REI no existe.";
	public static final String VAL_ACCOUNT_CUENTA2_ESTADO = "La cuenta REI no está activa.";

	public static final String VAL_ACCOUNT_CUENTA3 = "La cuenta destino normal no debe estar vacío.";
	public static final String VAL_ACCOUNT_CUENTA3_VALID = "La cuenta destino normal no existe.";
	public static final String VAL_ACCOUNT_CUENTA3_ESTADO = "La cuenta destino normal no está activa.";

	public static final String VAL_ACCOUNT_CUENTA4 = "La cuenta destino consumo no debe estar vacío.";
	public static final String VAL_ACCOUNT_CUENTA4_VALID = "La cuenta destino consumo no existe.";
	public static final String VAL_ACCOUNT_CUENTA4_ESTADO = "La cuenta destino consumo no está activa.";

	public static final String VAL_ACCOUNT_CUENTA5 = "La cuenta offset normal no debe estar vacío.";
	public static final String VAL_ACCOUNT_CUENTA5_VALID = "La cuenta offset normal no existe.";
	public static final String VAL_ACCOUNT_CUENTA5_ESTADO = "La cuenta offset normal no está activa.";

	public static final String VAL_ACCOUNT_CUENTA6 = "La cuenta offset consumo no debe estar vacío.";
	public static final String VAL_ACCOUNT_CUENTA6_VALID = "La cuenta offset consumo no existe.";
	public static final String VAL_ACCOUNT_CUENTA6_ESTADO = "La cuenta offset consumo no está activa.";

	public static final String VAL_ACCOUNT_CODIGO_VALID = "El código de cuenta mayor ya se encuentra registrado.";
	public static final String VAL_ACCOUNT_VOUCHER = "La cuenta no se puede eliminar mientras exista registros en la tabla voucherdetail.";
	public static final String VAL_ACCOUNT_BALANCE = "La cuenta no se puede eliminar mientras exista registros en la tabla accountbalance.";
	public static final String VAL_ACCOUNT_PRIME_VALID = "La cuenta mayor que fue calculado por el sistema, debe existir en la tabla primemst.";

	// VALIDACIONES PRIMETMAST
	public static final String VAL_PRIME_CODIGO = "La cuenta mayor no debe estar vacío.";
	public static final String VAL_PRIME_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_PRIME_BALANCE = "El balance de comprobación no debe estar vacío.";
	public static final String VAL_PRIME_CODIGO_VALID = "La cuenta mayor ya se encuentra registrado.";

	// VALIDACIONES PRIMETYPEMAST
	public static final String VAL_PRIMETYP_CODIGO = "El tipo de cuenta no debe estar vacío.";
	public static final String VAL_PRIMETYP_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_PRIMETYP_CODIGO_VALID = "El tipo de cuenta ya se encuentra registrado.";

	// VALIDACIONES TIPO PROYECTOS
	public static final String VAL_TIPPROY_CODIGO = "El tipo de proyecto no debe estar vacío.";
	public static final String VAL_TIPPROY_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_TIPPROY_CODIGO_VALID = "El tipo de proyecto ya se encuentra registrado.";

	// VALIDACIONES GRUPO PROYECTOS
	public static final String VAL_GRUPPROY_CODIGO = "El código no debe estar vacío.";
	public static final String VAL_GRUPPROY_DIGITO = "El grupo no debe estar vacío.";
	public static final String VAL_GRUPPROY_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_GRUPPROY_CODIGO_VALID = "El código y el grupo ya se encuentra registrado.";

	// VALIDACIONES CLASIFICACION CENTRO COSTOS
	public static final String VAL_CLAS_CENTRO_CODIGO = "La clasificación no debe estar vacío.";
	public static final String VAL_CLAS_CENTRO_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_CLAS_CENTRO_CODIGO_VALID = "La clasificación ya se encuentra registrado.";

	// VALIDACIONES GRUPO CENTRO COSTOS
	public static final String VAL_GRUPCOSTO_CODIGO = "El grupo C. Costo no debe estar vacío.";
	public static final String VAL_GRUPCOSTO_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_GRUPCOSTO_GRUPO = "El grupo mayor de C.C. no debe estar vacío.";
	public static final String VAL_GRUPCOSTO_CODIGO_VALID = "El grupo C. Costo ya se encuentra registrado.";

	// VALIDACIONES GRUPO MAYOR CENTRO COSTOS
	public static final String VAL_GRUPMAYORCOSTO_CODIGO = "El grupo mayor no debe estar vacío.";
	public static final String VAL_GRUPMAYORCOSTO_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_GRUPMAYORCOSTO_CODIGO_VALID = "El grupo mayor ya se encuentra registrado.";

	// VALIDACIONES SUCURSAL
	public static final String VAL_SUCURSAL_CODIGO = "La sucursal no debe estar vacío.";
	public static final String VAL_SUCURSAL_GRUPO = "El grupo no debe estar vacío.";
	public static final String VAL_SUCURSAL_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_SUCURSAL_CODIGO_VALID = "La sucursal ya se encuentra registrado.";

	// VALIDACIONES CENTRO DE COSTOS DESTINO
	public static final String VAL_COSTODESTINO_CODIGO = "El c. costos destino no debe estar vacío.";
	public static final String VAL_COSTODESTINO_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_COSTODESTINO_CODIGO_VALID = "El C. Costos Destino ya se encuentra registrado.";

	// VALIDACIONES PROYECTOS
	public static final String VAL_ROY_CODIGO = "El proyecto no debe estar vacío.";
	public static final String VAL_PROY_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_PROY_COMPANIA = "La compañia no debe estar vacío.";
	public static final String VAL_PROY_FECHAINICIO = "La fecha inicio no debe estar vacío.";
	public static final String VAL_PROY_FECHAFIN = "La fecha fin no debe estar vacío.";
	public static final String VAL_PROY_TIPO = "El tipo de proyecto no debe estar vacío.";
	public static final String VAL_PROY_MONTO = "El monto autorizado no debe estar vacío.";
	public static final String VAL_PROY_MONTOSELECT = "Debe seleccionar la moneda.";
	public static final String VAL_PROY_CODIGO_VALID = "El tipo de proyecto ya se encuentra registrado.";

	// VALIDACIONES CENTRO DE COSTOS DESTINO
	public static final String VAL_CENTROCOSTO_CODIGO = "El centro de costo no debe estar vacío.";
	public static final String VAL_CENTROCOSTO_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_CENTROCOSTO_EMPLEADO = "El empleado supervisor de C.C. no debe estar vacío.";
	public static final String VAL_CENTROCOSTO_NIVEL = "El siguiente nivel de autorización (C.C.) no debe estar vacío.";

	public static final String VAL_CENTROCOSTO_CUENTA_DEFECTO = "La cuenta por defecto no debe estar vacío.";
	public static final String VAL_CENTROCOSTO_CUENTA_DEFECTO_VALID = "La cuenta por defecto no existe.";
	public static final String VAL_CENTROCOSTO_CUENTA_DEFECTO_ESTADO = "La cuenta por defecto no está activa.";

	public static final String VAL_CENTROCOSTO_CUENTA_ESTADISTICA = "La cuenta estadística no debe estar vacío.";
	public static final String VAL_CENTROCOSTO_CUENTA_ESTADISTICA_VALID = "La cuenta estadística no existe.";
	public static final String VAL_CENTROCOSTO_CUENTA_ESTADISTICA_ESTADO = "La cuenta estadística no está activa.";

	public static final String VAL_CENTROCOSTO_SUCURSAL = "La sucursal no debe estar vacío.";
	public static final String VAL_CLASIFICACION = "La clasificación no debe estar vacío.";
	public static final String VAL_GRUPO = "El grupo - subgrupo no debe estar vacío.";
	public static final String VAL_CENTROCOSTO_CUENTA_IGUAL = "El siguiente nivel de autorización (C.C.) no puede ser igual al centro de costo";
	public static final String VAL_CENTROCOSTO_CODIGO_VALID = "El centro de costo ya se encuentra registrado.";

	// VALIDACIONES CONTRATO RESPONSABLE
	public static final String VAL_CONTRATO_CODIGO = "El responsable no debe estar vacío.";
	public static final String VAL_CONTRATO_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_CONTRATO_CODIGO_VALID = "El responsable ya se encuentra registrado.";

	// VALIDACIONES CONTRATO TIPO
	public static final String VAL_CONTRATO_TIP_CODIGO = "El tipo de contrato no debe estar vacío.";
	public static final String VAL_CONTRATO_TIP_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_CONTRATO_TIP_CODIGO_VALID = "El tipo de contrato ya se encuentra registrado.";

	// VALIDACIONES ALMACENES
	public static final String VAL_ALMACEN_CODIGO = "El almacén no debe estar vacío.";
	public static final String VAL_ALMACEN_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_ALMACEN_COMPANIA = "La compañia socio no debe estar vacío.";
	public static final String VAL_ALMACEN_UNIDAD = "La unidad de negocio no debe estar vacío.";
	public static final String VAL_ALMACEN_TIPO = "El tipo de almacén no debe estar vacío.";
	public static final String VAL_ALMACEN_CODIGO_VALID = "El almacén ya se encuentra registrado.";

	// VALIDACIONES TIPO ITEM
	public static final String VAL_TIPOITEM_CODIGO = "El tipo de item no debe estar vacío.";
	public static final String VAL_TIPOITEM_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_TIPOITEM_CODIGO_VALID = "El tipo de item ya se encuentra registrado.";

	// VALIDACIONES CLASIFICACION REQUERIMIENTO
	public static final String VAL_CLAREQ_CODIGO = "El clasificación no debe estar vacío.";
	public static final String VAL_CLAREQ_DESCRIPCION = "La descripción local no debe estar vacío.";
	public static final String VAL_CLAREQ_CODIGO_VALID = "El clasificación ya se encuentra registrado.";

	// VALIDACIONES FORMA DE PAGO
	public static final String VAL_FORMPAGO_CODIGO = "La forma de pago no debe estar vacío.";
	public static final String VAL_FORMPAGO_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_FORMPAGO_CODIGO_VALID = "La forma de pago ya se encuentra registrado.";

	// VALIDACIONES UNIDADES
	public static final String VAL_UNIDADES_CODIGO = "La und. de medida no debe estar vacío.";
	public static final String VAL_UNIDADES_DESCRI = "La descripción local no debe estar vacío.";
	public static final String VAL_UNIDADES_CODIGO_VALID = "La und. de medida ya se encuentra registrado.";

	// VALIDACIONES CONDICION
	public static final String VAL_CONDICION_CODIGO = "La condición no debe estar vacío.";
	public static final String VAL_CONDICION_DESCRI = "La descripción local no debe estar vacío.";
	public static final String VAL_CONDICION_PORCENTAJE = "El porcentaje no debe estar vacío.";
	public static final String VAL_CONDICION_PORCENTAJE_VALID = "El porcentaje debe estar entre 0 y 100.";
	public static final String VAL_CONDICION_CODIGO_VALID = "La condición ya se encuentra registrado.";

	// VALIDACIONES MARCAS
	public static final String VAL_MARCAS_CODIGO = "La marca no debe estar vacío.";
	public static final String VAL_MARCAS_DESCRI = "La descripción local no debe estar vacío.";
	public static final String VAL_MARCAS_CODIGO_VALID = "La marca ya se encuentra registrado.";

	// VALIDACIONES MODELOS
	public static final String VAL_MODELOS_CODIGO = "El modelo no debe estar vacío.";
	public static final String VAL_MODELOS_DESCRI = "La descripción no debe estar vacío.";
	public static final String VAL_MODELOS_CODIGO_VALID = "El modelo ya se encuentra registrado.";

	// VALIDACIONES PROCEDENCIA
	public static final String VAL_PROCEDENCIA_CODIGO = "La procedencia no debe estar vacío.";
	public static final String VAL_PROCEDENCIA_DESCRI = "La descripción no debe estar vacío.";
	public static final String VAL_PROCEDENCIA_CODIGO_VALID = "La procedencia ya se encuentra registrado.";

	// VALIDACIONES CLASIF. ABC
	public static final String VAL_CLASABC_CODIGO = "La clasif. abc no debe estar vacío.";
	public static final String VAL_CLASABC_DESCRI = "La descripción local no debe estar vacío.";
	public static final String VAL_CLASABC_DIAS = "El número inventarios al año no debe estar vacío.";
	public static final String VAL_CLASABC_CODIGO_VALID = "La clasif. abc ya se encuentra registrado.";

	// VALIDACIONES MAESTRO MISCELANEOS
	public static final String VAL_MISCELANEO_CODIGO = "La aplicación no debe estar vacío.";
	public static final String VAL_MISCELANEO_DESCRI = "La descripción no debe estar vacío.";
	public static final String VAL_MISCELANEO_MAESTRO = "El maestro no debe estar vacío.";
	public static final String VAL_MISCELANEO_CODIGO_VALID = "El maestro misceláneo ya se encuentra registrado.";

	// VALIDACIONES MAESTRO ITEMS
	public static final String VAL_ITEMS_DESCRI = "La descripción local no debe estar vacío.";
	public static final String VAL_ITEMS_TIPO = "El tipo de item no debe estar vacío.";
	public static final String VAL_ITEMS_UNIDAD = "La unidad medida no debe estar vacío.";
	public static final String VAL_ITEMS_LINEA = "La linea no debe estar vacío.";
	public static final String VAL_ITEMS_FAMILIA = "La familia no debe estar vacío.";
	public static final String VAL_ITEMS_SUBFAMILIA = "La subfamilia no debe estar vacío.";
	public static final String VAL_ITEMS_UNIDAD2 = "La und de cantidad doble no debe estar vacío.";
	public static final String VAL_ITEMS_FACTOR = "El factor no debe estar vacío.";
	public static final String VAL_ITEMS_ERROR = "La código del item no pudo generarse.";
	public static final String VAL_ITEMS_IMG = "La imagen no es valido.";
	public static final String VAL_CUENTA1 = "La cuenta de inventario no existe.";
	public static final String VAL_CUENTA2 = "La cuenta para gastos no existe.";
	public static final String VAL_CUENTA3 = "La cuenta ventas no existe.";
	public static final String VAL_CUENTA4 = "La cuenta tránsito no existe.";
	public static final String VAL_CUENTA5 = "La cuenta costo ventas no existe.";
	public static final String VAL_CUENTA6 = "La cuenta salida a terc. no existe.";
	public static final String VAL_CUENTA7 = "La cuenta de inversión no existe.";
	public static final String VAL_CUENTAESTADO1 = "La cuenta de inventario no está activa.";
	public static final String VAL_CUENTAESTADO2 = "La cuenta para gastos no está activa.";
	public static final String VAL_CUENTAESTADO3 = "La cuenta ventas no está activa.";
	public static final String VAL_CUENTAESTADO4 = "La cuenta tránsito no está activa.";
	public static final String VAL_CUENTAESTADO5 = "La cuenta costo ventas no está activa.";
	public static final String VAL_CUENTAESTADO6 = "La cuenta salida a terc. no está activa.";
	public static final String VAL_CUENTAESTADO7 = "La cuenta de inversión no está activa.";
	public static final String VAL_COTIZACION_ERROR = "La código cotización no pudo generarse.";
	public static final String VAL_ITEMS_ERROR_KEY = "La código del item no pudo generarse.";

	// VALIDACIONES PROCESO SELECCION

	public static final String VAL_UN_PROCESO_SELEC_COMPANIASOCIO = "Falta ingresar la Compania";

	// VALIDACIONES ORDEN DE COMPRA
	public static final String VAL_OC_CODIGO = "El número de orden de compra no debe estar vacío.";
	public static final String VAL_OC_COMPANIA = "La companía no debe estar vacío.";
	public static final String VAL_OC_CLASIFICACION = "La clasificación no debe estar vacío.";
	public static final String VAL_OC_NEGOCIO = "La unidad de negocio no debe estar vacío.";
	public static final String VAL_OC_FORMAPAGO = "La forma de pago no debe estar vacío.";
	public static final String VAL_OC_PROVEEDOR = "El proveedor no debe estar vacío.";
	public static final String VAL_OC_AREA = "El área supervisora no debe estar vacío.";
	public static final String VAL_OC_TIPOSERVICIO = "El tipo de servicio no debe estar vacío.";
	public static final String VAL_OC_MONEDA = "La moneda no debe estar vacío.";
	public static final String VAL_OC_MONTO_AFECTO = "El monto afecto no debe estar vacío.";
	public static final String VAL_OC_MONTO_NO_AFECTO = "El monto no afecto no debe estar vacío.";
	public static final String VAL_OC_IMPUESTOS = "El impuesto no debe estar vacío.";
	public static final String VAL_OC_MONTO_TOTAL = "El monto total no debe estar vacío.";
	public static final String VAL_OC_ALMACEN = "El almacén entrega no debe estar vacío.";
	public static final String VAL_OC_FECHA_ENTREGA = "La fecha de entrega no debe estar vacío.";
	public static final String VAL_OC_ENVIAR_DOCUMENTO = "El envió de documento no debe estar vacío.";
	public static final String VAL_OC_ENVIAR_PREPARADOPOR = "El usuario ha expirado.";
	public static final String VAL_OC_CODIGO_VALID = "La orden de compra ya se encuentra registrado.";
	public static final String VAL_OC_CODIGO_VALID2 = "No se puede usar Renta de 4ta para una O.Compra. Use O.Servicio.";
	public static final String VAL_OC_CODIGO_OC = "La código de la orden de compra no pudo generarse.";
	public static final String VAL_OC_CODIGO_OC_COMPANIA = "La código de la orden de compra no pudo generarse.";
	public static final String VAL_OC_TIPO_CAMBIO = "Se debe asignar un tipo de cambio.";
	public static final String VAL_OC_CLASIFICACION_VALID = "La clasificación no existe en la tabla WH_ClasificacionRequOrden";
	public static final String VAL_OC_CENTRO_COSTO = "El área usuaria no debe estar vacío.";
	public static final String VAL_OC_ITEM_VALID = "El item no existe.";
	public static final String VAL_OC_COMMODITY_VALID = "El commodity no existe.";
	public static final String VAL_OC_SUCURSAL_VALID = "La sucursal no debe estar vacío.";
	public static final String VAL_OC_ADJUNTO_VALID = "El documento ya existe.";
	public static final String VAL_OC_ITEM = "El item no debe estar vacío.";
	public static final String VAL_OC_PRECIO = "El precio unitario inicial no debe estar vacío.";
	public static final String VAL_OC_COMMODITY = "El commodity no debe estar vacío.";
	public static final String VAL_OC_ITEM_OR_COMMODITY = "El item o commodity no debe estar vacío.";
	public static final String VAL_OC_AVISO_DESDE = "Se han generado Ordenes de Compra desde el número ";
	public static final String VAL_OC_AVISO_HASTA = " hasta el número ";
	public static final String VAL_OC_AVISO = "Se han generado la orden compra con el número ";
	public static final String VAL_OC_GENERAR = "Debe actualizar el área usuaria ";

	// VALIDACIONES COTIZACIONES
	public static final String VAL_COTI_UPLOAD = "Error al importar el documento excel.";
	public static final String VAL_COTI_XLSX = "El documento excel no corresponde al formato valido.";
	public static final String VAL_COTI_AVISO_DESDE = "Se han generado Invitaciones a proveedores desde el número ";
	public static final String VAL_COTI_AVISO_HASTA = " hasta el número ";
	public static final String VAL_COTI_AVISO = "Se han generado la Invitación al proveedor con el número ";

	// VALIDACIONES CONTRATOS
	public static final String VAL_CON_COMPANIA = "La companía no debe estar vacío.";
	public static final String VAL_CON_PROVEEDOR = "El proveedor no debe estar vacío.";
	public static final String VAL_CON_USUARIO = "El usuario no debe estar vacío.";
	public static final String VAL_CON_CLASF_CONTRATO = "La clasificación contrato no debe estar vacío.";
	public static final String VAL_CON_RESPONSABLE_CON = "El responsable contrato no debe estar vacío.";
	public static final String VAL_CON_TIPO_CONTRATO = "El tipo contrato no debe estar vacío.";
	public static final String VAL_CON_AREA_REVISORA = "El área revisora no debe estar vacío.";
	public static final String VAL_CON_FECHA_DOCUMENTO = "La fecha documento no debe estar vacío.";
	public static final String VAL_CON_FECHA_VALIDEZ_DESDE = "La fecha validez desde no debe estar vacío.";
	public static final String VAL_CON_FECHA_VALIDEZ_HASTA = "La fecha validez hasta no debe estar vacío.";
	public static final String VAL_CON_FORMA_PAGO = "La forma de pago no debe estar vacío.";
	public static final String VAL_CON_MONEDA = "La moneda no debe estar vacío.";
	public static final String VAL_CON_MODALIDAD = "La modalidad no debe estar vacío.";
	public static final String VAL_CON_CLASIFICACION = "La clasificación no debe estar vacío.";
	public static final String VAL_CON_UNIDAD_NEGOCIO = "La unidad de negocio no debe estar vacío.";
	public static final String VAL_CON_PERIODICIDAD = "La periodicidad no debe estar vacío.";
	public static final String VAL_CON_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_CON_ESTADO_INTERNO = "El estado interno no debe estar vacío.";
	public static final String VAL_CON_MENSAJE = "Se han generado el contrato con el número ";

	// VALIDACIONES CONTRATO DETALLE
	public static final String VAL_CON_DET_ITEM = "El item o commodity no debe estar vacío.";
	public static final String VAL_CON_DET_DESCRIPCION = "La descripción no debe estar vacío.";
	public static final String VAL_CON_DET_CANTIDAD = "La cantidad no debe estar vacío.";
	public static final String VAL_CON_DET_PRECIO_UNITARIO = "El precio unitario no debe estar vacío.";
	public static final String VAL_CON_DET_CENTRO_COSTO = "El área usuaria no debe estar vacío.";
	public static final String VAL_CON_DET_CUENTA = "La cuenta no debe estar vacío.";
	public static final String VAL_CON_DET_TOTAL_CANTIDAD = "La cantidad total de vencimientos debe ser igual al del detalle";

	// VALIDACIONES CONTRATO MODIFICADOS
	public static final String VAL_CON_MOD_DESCRI = "La descripción del contrato modificado no debe estar vacío.";

	// VALIDACIONES CONTRATO ADENDAS
	public static final String VAL_CON_ADN_MOTIVO = "El motivo de la adenda no debe estar vacío";
	public static final String VAL_CON_ADN_TIPO = "El tipo de adenda no debe estar vacío";

	// VALIDACIONES ADJUNTOS REQ
	public static final String VAL_REQ_ADJ_ADJ = "El tipo adjudicación no debe estar vacío.";
	public static final String VAL_REQ_ADJ_PROC = "El tipo proceso no debe estar vacío.";
	public static final String VAL_REQ_ADJ_DESCRI = "La descripción no debe estar vacío.";
	public static final String VAL_REQ_ADJ_ESTADO = "El estado no debe estar vacío.";
	public static final String VAL_REQ_ADJ_PK = "El adjunto del requerimiento ya existe.";

	// VALIDACIONES CONFIRMAR ORDENES
	public static final String VAL_CF_ERROR = "La confirmación no pudo generarse.";

	// VALIDACIONES GESTORES DE REQUERIMIENTO
	public static final String VAL_GEREQ_TIPO = "El tipo comprador no debe estar vacío.";
	public static final String VAL_GEREQ_COMPRADOR = "El comprador no debe estar vacío.";
	public static final String VAL_GEREQ_VALID = "El tipo comprador ya existe.";
	
	// VALIDACIONES PENALIDAD
	public static final String VAL_PEN_SERIE = "La serie no debe estar vacío.";
	public static final String VAL_PEN_PROVEEDOR = "El proveedor no debe estar vacío.";
	public static final String VAL_PEN_DOCUMENTO = "El número de documento no debe estar vacío.";
	public static final String VAL_PEN_MONTO = "El importe penalidad no debe estar vacío.";
	public static final String VAL_PEN_TIPO = "El tipo penalidad no debe estar vacío.";
	public static final String VAL_PEN_ADJ = "Debe adjuntar el sustento de los días asumidos por CMAC";
	public static final String VAL_PEN_DIA = "La cantidad de días no atribuibles debe ser menor a los días de atraso.";
	public static final String VAL_PEN_ERROR = "Este tipo de penalidad ya existe para esta O.Compra.";
	public static final String VAL_PEN_CALCULO = "La penalidad no puede ser mayor al 10% del monto del documento.";
	
	// VALIDACIONES ANULACIONES
	public static final String VAL_ANULAR_OC_VALID_1 = "Existen Vouchers pendientes para esta Importación.";
	public static final String VAL_ANULAR_OC_VALID_2 = "La Orden de Compra no ha tenido movimiento. No puede ser cerrada. Si desea, puede anularla.";
	public static final String VAL_ANULAR_OC_VALID_3 = "Existen Transferencias Pendientes para esta Importacion.";
	public static final String VAL_ANULAR_OC_VALID_4 = "Existen Vouchers pendientes para esta Importación.";
	
	public static final String VAL_ANULAR_OC_VALID_00 = "La Orden de compra no existe.";
	public static final String VAL_ANULAR_OC_VALID_02 = "La Orden de Compra se encuentra en Estado de Preparación. El Proceso la pasará al Estado Anulado y reabrirá los requerimientos relacionados.";
	public static final String VAL_ANULAR_OC_VALID_03 = "La Orden de Compra se encuentra en Estado de Preparación. El Proceso la pasará al Estado Anulado y reabrirá los requerimientos relacionados.";
	public static final String VAL_ANULAR_OC_VALID_04 = "Error: La Orden de Compra no ha tenido movimiento. No puede ser cerrada. Si desea, puede anularla.";
	public static final String VAL_ANULAR_OC_VALID_05 = "La Orden de Compra se encuentra en Estado Revisada o Rechazada. El Proceso la pasará al Estado de Preparación.";
	public static final String VAL_ANULAR_OC_VALID_06 = "Error: La Orden de Compra no ha tenido movimiento. No puede ser cerrada. Si desea, puede anularla.";
	public static final String VAL_ANULAR_OC_VALID_07 = "Error: La Orden de Compra ha comenzado a ser pagada en CXP. La anulación NO PROCEDE.";
	public static final String VAL_ANULAR_OC_VALID_08 = "Error: Ya se han recibido items de esta Orden de Compra. La Orden de Compra no puede ser anulada. Si desea, puede cerrarla.";
	public static final String VAL_ANULAR_OC_VALID_09 = "El proceso de Anulación pasará la Orden de Compra al Estado de Revisado. Si desea realmente Anularla presione nuevamente Buscar y Ejecutar.";
	public static final String VAL_ANULAR_OC_VALID_10 = "El proceso de Anulación pasará la Orden de Compra al Estado de Preparación. Si desea realmente Anularla presione nuevamente Buscar y Ejecutar.";
	public static final String VAL_ANULAR_OC_VALID_11 = "El Cierre de la Orden de Compra procede. Sin embargo, los requerimientos asociados no se reabrirán.";
	public static final String VAL_ANULAR_OC_VALID_12 = "Error: El Estado actual de la Orden de Compra no permite ninguna acción.";
	
	
	// WEB PROVEEDORES
	public static final String URL_PROVEEDOR = "whquotation-vendor-edit/";
	public static final String JSONARMADO = "/{\"cotizacionnumero\":";

	public static final String RUTA = "/{\"companiasocio\":\"";
	public static final String RUTA2 = "\",\"cotizacionnumero\":\"";

	public static final String INVITADO = "Invitado";
	public static final String REGRECHAZADO = "Registro Rechazado";

	public static final String PROCESO_ESTUDIO_MERCADO = "EM";
	public static String PROCESO_PROVEEDORES_ESTADO_ACTIVO = "AC";
}
