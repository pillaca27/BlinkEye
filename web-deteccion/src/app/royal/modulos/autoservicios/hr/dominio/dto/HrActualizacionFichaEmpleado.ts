import { DominioTransaccion } from "@framework/modelo/generico/DominioTransaccion";
import { BeanEmpleadomast } from "../../../../framework-comun/core/dominio/dto/BeanEmpleadomast";
import { BeanPersonamast } from "../../../../framework-comun/core/dominio/dto/BeanPersonamast";

export class HrActualizacionFichaEmpleado extends DominioTransaccion {

	constructor() {
		super();
		this.personamasttemp = new Personamasttemp();
		this.empleadomasttemp = new Empleadomasttemp();
		this.hrEmpleadotemp = new HrEmpleadotemp();
		this.hrEmpleado = new HrEmpleado();
		this.personamast = new BeanPersonamast();
		this.empleadomast = new BeanEmpleadomast();

		this.listaDependientes = [];
		this.listaInstruccionesBasica = [];
		this.listaInstruccionesSuperior = [];
		this.listaCursos = [];
		this.listaCursosIdioma = [];
		this.listaCursosInformatica = [];
		this.listaExperiencias = [];
		this.listaDirecciones = [];
		this.listaDocumentos = [];
	}

	transaccionid: number;
	empleado: number;
	estado: string;
	fechasolicitud: Date;
	ultimousuario: string;
	ultimafechamodif: Date;
	numeroproceso: number;

	auxFotoString: string;
	auxSegmentos: string;
	auxNivelAprobacion: number;
	axuTransaccionUUID: string;

	auxReadOnlyTipoTrabajadorNombre: string;
	auxReadOnlyPuestoNombre: string;
	auxReadOnlyCargoNombre: string;
	auxReadOnlySituacionepsNombre: string;
	auxReadOnlyBancomonedalocalNombre: string;
	auxReadOnlyCuentaLocalNombre: string;
	auxReadOnlyTipoContratoNombre: string;
	auxReadOnlyCentroCostosNombre: string;
	auxReadOnlyEstadoCivilNombre: string;
	auxReadOnlyPaisExtranjeroNombre: string;
	auxReadOnlySexoNombre: string;
	auxReadOnlyNacionalidadNombre: string;

	auxReadOnlyPaisLocalNombre: string;
	auxReadOnlyDepartamentoLocalNombre: string;
	auxReadOnlyProvinciaLocalNombre: string;
	auxReadOnlyDistritoLocalNombre: string;

	auxReadOnlyPaisNacimientoNombre: string;
	auxReadOnlyDepartamentoNacimientoNombre: string;
	auxReadOnlyProvinciaNacimientoNombre: string;
	auxReadOnlyDistritoNacimientoNombre: string;

	personamasttemp: Personamasttemp;
	empleadomasttemp: Empleadomasttemp;
	hrEmpleadotemp: HrEmpleadotemp;
	hrEmpleado: HrEmpleado;
	personamast: BeanPersonamast;
	empleadomast: BeanEmpleadomast;

	listaDependientes: Dependientetemp[];
	listaInstruccionesBasica: HrEmpleadoinstrucciontemp[];
	listaInstruccionesSuperior: HrEmpleadoinstrucciontemp[];
	listaCursos: HrEmpleadocursotemp[];
	listaCursosIdioma: HrEmpleadocursotemp[];
	listaCursosInformatica: HrEmpleadocursotemp[];
	listaExperiencias: HrEmpleadoexperienciatemp[];
	listaDirecciones: Direcciontemp[];
	listaDocumentos: HrEmpleadodocumentotemp[];
	listaReferencias: HrEmpleadoreferenciatemp[];
}


export class Dependientetemppk {
	transaccionId: number;
	secuencia: number;
}
export class Dependientetemp {
	pk: Dependientetemppk;
	constructor() {
		this.pk = new Dependientetemppk();
	}
	nombrecompleto: string;
	apellidopaterno: string;
	apellidomaterno: string;
	nombres: string;
	tipocarga: string;
	tipodependiente: string;
	fechanacimiento: Date;
	sexo: string;
	tipodocumentoidentidad: string;
	documentoidentidad: string;
	tipodocumentoatencionmedica: string;
	documentoatencionmedica: string;
	tiposangre: string;
	flagbeneficio: string;
	centroeducacion: string;
	tipoescolariadad: string;
	gradoeducacion: string;
	tieneescolaridad: string;
	direccioncompleta: string;
	conseguro: string;
	codigoseguro: string;
	historiamedica: string;
	cirugias: string;
	vacunas: string;
	comentarios: string;
	flagtrabaja: string;
	empresa: string;
	direccionempresa: string;
	departamento: string;
	codigopostal: string;
	telefono: string;
	fax: string;
	tiemposervicio: number;
	sueldo: number;
	carnetasistenciasocial: string;
	tipocarnetasistenciasocial: string;
	centroasistenciasocial: number;
	motivobaja: string;
	documentoincapacidad: string;
	flagdomiciliopropio: string;
	fechabaja: Date;
	enfermedadgraveflag: string;
	estado: string;
	ultimousuario: string;
	ultimafechamodif: Date;
	tipoescolaridad: string;
	flagsmf: string;
	anoseducacion: number;
	flagprorrogaaf: string;
	periodoprorrogaaf: string;
	fechaalta: Date;
	tipodocumentopaternidad: string;
	nrodocumentopaternidad: string;
	nacionalidad: string;
	fechasmf: Date;
	nroresolucion: string;
	municipalidadnacimiento: string;
	nacionalidadrtps: string;
	ocupacion: string;
	paisemisor: string;
	correoelectronico: string;
	flagcentroasistencia: string;
	mesconcepcion: string;
	tipovinculo: string;
	flagtrabajadentro: string;
	flagviveconempleado: string;
	puesto: string;
	flagmodificado: string;
	flageliminado: string;
	flagnuevo: string;


	flagbeneficioB: boolean;
	flagsmfB: boolean;
	flagviveconempleadoB: boolean;

	auxTipodependienteNombre: string;

}
export class HrEmpleadoinstrucciontempPk {
	transaccionId: number;
	secuencia: number;
	tipomaestro: string;
}
export class HrEmpleadoinstrucciontemp {
	pk: HrEmpleadoinstrucciontempPk;

	constructor() {
		this.pk = new HrEmpleadoinstrucciontempPk();
	}

	gradoinstruccion: string;
	flagultimogrado: string;
	fechadesde: Date;
	fechahasta: Date;
	centroestudio: number;
	flagotrocentroestudio: string;
	otrocentroestudio: string;
	area: string;
	profesion: string;
	numerocreditos: number;
	colegioprofesional: string;
	colegioprofesionalpadron: string;
	flagpracticas: string;
	observaciones: string;
	rutadocumento: string;
	ultimousuario: string;
	ultimafechamodif: Date;
	flagmodificado: string;
	flageliminado: string;
	flagnuevo: string;

	auxFlagotrocentroestudio: boolean;
	auxFlagultimogrado: boolean;
	auxFlagpracticas: boolean;
	auxCentroestudiosNombre: string;
	auxGradoinstruccionNombre: string;
	auxDocumentoContenidoBase64: string;
	auxDocumentoNombre: string;

	auxCarrera: string;
	auxArea: string;
}
export class HrEmpleadocursotemppk {
	transaccionId: number;
	secuencia: number;
	tipomaestro: string;
}
export class HrEmpleadocursotemp {
	pk: HrEmpleadocursotemppk;
	constructor() {
		this.pk = new HrEmpleadocursotemppk();
	}
	curso: number;
	tipocurso: string;
	centroestudio: number;
	fechadesde: Date;
	fechahasta: Date;
	dias: number;
	horas: number;
	anosvigencia: number;
	nota: number;
	flagauspicioempresa: string;
	flagcapacitacion: string;
	capacitacion: string;
	capacitacioncompania: string;
	nivelgeneral: string;
	idiomanivellectura: string;
	idiomaniveloral: string;
	idiomanivelescritura: string;
	observaciones: string;
	rutadocumento: string;
	ultimousuario: string;
	ultimafechamodif: Date;
	flagmodificado: string;
	flageliminado: string;
	flagnuevo: string;

	auxCursoNombre: string;
	auxCentroestudioNombre: string;
	auxFlagauspicioempresa: boolean;
	auxDocumentoNombre: string;
	auxDocumentoContenidoBase64: string;
}
export class HrEmpleadoexperienciatemppk {
	transaccionId: number;
	secuencia: number;
}
export class HrEmpleadoexperienciatemp {
	pk: HrEmpleadoexperienciatemppk;
	constructor() {
		this.pk = new HrEmpleadoexperienciatemppk();
	}
	fechadesde: Date;
	fechahasta: Date;
	ano: number;
	tipoexperiencia: string;
	empresa: string;
	giroempresa: string;
	tipocontrato: string;
	motivocese: string;
	sueldo: number;
	moneda: string;
	areaexperiencia: string;
	tipoentidad: string;
	cargo: string;
	funciones: string;
	puestosimilar: number;
	competencias: string;
	puntosmejora: string;
	observaciones: string;
	ultimousuario: string;
	ultimafechamodif: Date;
	flagmodificado: string;
	flageliminado: string;
	flagnuevo: string;
}
export class HrEmpleadoreferenciatemppk {
	transaccionId: number;
	secuencia: number;
}
export class HrEmpleadoreferenciatemp {
	pk: HrEmpleadoreferenciatemppk;
	constructor() {
		this.pk = new HrEmpleadoreferenciatemppk();
	}
	nombre: string;
	direccion: string;
	telefono: string;
	cargo: string;
	empresa: string;
	observaciones: string;
	ultimousuario: string;
	ultimafechamodif: Date;
	flagmodificado: string;
	flageliminado: string;
	flagnuevo: string;
}
export class Direcciontemppk {
	transaccionId: number;
	secuencia: number;
}
export class Direcciontemp {
	pk: Direcciontemppk;
	constructor() {
		this.pk = new Direcciontemppk();
	}
	direccion: string;
	referenciasdireccion: string;
	departamento: string;
	codigopostal: string;
	escobranza: string;
	esdomicilio: string;
	estrabajo: string;
	esremision: string;
	esotros: string;
	empresa: string;
	condiciondireccion: string;
	unidadtiempo: string;
	tiemporesidencia: number;
	telefono: string;
	fax: string;
	montoavaluo: number;
	almacencodigo: string;
	tipocalle: string;
	nombrecalle: string;
	numero: string;
	numdep: string;
	manzana: string;
	lote: string;
	interior: string;
	sector: string;
	urbanizacion: string;
	provinciadomicilio: string;
	provincia: string;
	pais: string;
	distrito: string;
	ubigeo: string;
	vendedor: number;
	estado: string;
	ultimousuario: string;
	ultimafechamodif: Date;
	rutadespacho: string;
	pdttipozona: string;
	pdtzona: string;
	pdttipocalle: string;
	kilometro: string;
	etapa: string;
	flagcentroessalud: string;
	secuenciadependiente: number;
	flagmodificado: string;
	flageliminado: string;
	flagnuevo: string;

	auxUbigeoNombre: string;
	auxPdttipocalleNombre: string;
}
export class HrEmpleadodocumentotemppk {
	transaccionId: number;
	secuencia: number;
}
export class HrEmpleadodocumentotemp {
	pk: HrEmpleadodocumentotemppk;
	constructor() {
		this.pk = new HrEmpleadodocumentotemppk();
	}
	companiasocio: string;
	documento: string;
	descripcion: string;
	fecha: Date;
	fechavencimiento: Date;
	flagpresento: string;
	observaciones: string;
	rutadocumento: string;
	ultimousuario: string;
	ultimafechamodif: Date;
	flagmodificado: string;
	flageliminado: string;
	flagnuevo: string;

	auxTipoDocumentoNombre: string;
	auxFlagpresento: boolean;
	auxDocumentoNombre: string;
	auxDocumentoContenidoBase64: string;
}
export class HrEmpleadotemp {
	transaccionId: number;
	empleado: number;
	postulante: number;
	fechaingresoorganizacion: Date;
	apellidocasada: string;
	paisnacimiento: string;
	departamentonacimiento: string;
	provincianacimiento: string;
	distritonacimiento: string;
	direccionextranjera: string;
	paisextranjero: string;
	telefonoextranjero: string;
	celularextranjero: string;
	talla: number;
	peso: number;
	impedimentos: string;
	gruposanguineo: string;
	flagdonante: string;
	fechaestadocivil: Date;
	situaciondomicilio: string;
	flagtrabajofuera: string;
	observaciones: string;
	nombreemergencia: string;
	direccionemergencia: string;
	telefonoemergencia: string;
	celularemergencia: string;
	parentescoemergencia: string;
	licenciaconducirfecha: Date;
	pretensioneconomica: number;
	ultimousuario: string;
	ultimafechamodif: Date;
}
export class HrEmpleado {
	empleado: number;
	postulante: number;
	fechaingresoorganizacion: Date;
	apellidocasada: string;
	paisnacimiento: string;
	departamentonacimiento: string;
	provincianacimiento: string;
	distritonacimiento: string;
	direccionextranjera: string;
	paisextranjero: string;
	telefonoextranjero: string;
	celularextranjero: string;
	talla: number;
	peso: number;
	impedimentos: string;
	gruposanguineo: string;
	flagdonante: string;
	fechaestadocivil: Date;
	situaciondomicilio: string;
	flagtrabajofuera: string;
	observaciones: string;
	nombreemergencia: string;
	direccionemergencia: string;
	telefonoemergencia: string;
	celularemergencia: string;
	parentescoemergencia: string;
	licenciaconducirfecha: Date;
	pretensioneconomica: number;
	ultimousuario: string;
	ultimafechamodif: Date;
}
export class Empleadomasttemp {
	transaccionId: number;
	empleado: number;
	companiasocio: string;
	tipopago: string;
	tipotrabajador: string;
	tipoplanilla: string;
	raza: string;
	religion: string;
	tipovisa: string;
	visafechainicio: Date;
	visafechaexpiracion: Date;
	serviciomilitardesde: Date;
	serviciomilitarhasta: Date;
	numeroarchivo: string;
	unidadnegocioasignada: string;
	locaciondepago: string;
	codigousuario: string;
	tipoasistenciasocial: string;
	centroasistenciasocial: string;
	tipocarnetasistenciasocial: string;
	carnetasistenciasocial: string;
	tipopension: string;
	fechainiciopension: Date;
	fechaterminopension: Date;
	codigoafp: string;
	numeroafp: string;
	bancocts: string;
	tipocuentacts: string;
	tipomonedacts: string;
	numerocuentacts: string;
	estadoempleado: string;
	fechaingreso: Date;
	fechaultimaplanilla: Date;
	tipocontrato: string;
	fechainiciocontrato: Date;
	fechafincontrato: Date;
	fechacese: Date;
	razoncese: string;
	motivocese: number;
	contratista: number;
	centrocostos: string;
	afe: string;
	deptoorganizacion: string;
	departamentooperacional: string;
	tipohorario: number;
	gradosalario: string;
	cargo: string;
	nivelacceso: string;
	flagipssvida: string;
	flagacctrabajo: string;
	correointerno: string;
	sueldoactuallocal: number;
	sueldoactualdolar: number;
	sueldoanteriorlocal: number;
	sueldoanteriordolar: number;
	monedapago: string;
	perfil: number;
	foto: string;
	fechaliquidacion: Date;
	fechareingreso: Date;
	unidadreplicacion: string;
	codigocargo: number;
	ultimafechapagovacacion: Date;
	estado: string;
	sucursal: string;
	ruccentroasistenciasocial: string;
	tarjetadecredito: string;
	plantillaconcepto: number;
	flagsmf: string;
	fechavacaciones: Date;
	flagtrabajadorpensionista: string;
	flagsctrsalud: string;
	flagsctrpension: string;
	flagdiscapacidad: string;
	flagsujetocontrol: string;
	flagsindicalizado: string;
	flagdomiciliado: string;
	niveleducativortps: string;
	flagregimenalternativo: string;
	flagjornadamaxima: string;
	flaghorarionocturno: string;
	flagotrosquinta: string;
	flagquintaexonerada: string;
	situacionespecial: string;
	flagmadreresponsabilidad: string;
	flagcentroformacion: string;
	tipomodalidadformativa: string;
	prestadorservicio: string;
	flagasegurapension: string;
	categoriaocupacional: string;
	flagconveniodobletrib: string;
	firmadigitalizada: string;
	flagdeconfianza: string;
	fechabajaeps: Date;
	codigounidad: number;
	ultimousuario: string;
	ultimafechamodif: Date;
	division: string;
	oficina: string;
	responsableempleado: string;
	responsablejefe: string;
	tipocomisionafp: string;
	flagtransferido: string;
	locacionasignada: string;
	flageducacioniep: string;
	jeferesponsable: number;
	unidadoperativa: string;
	ordenorganigrama: string;
	empleadorelacionado: number;
	unidadtrabajo: string;
	flageducacioncompletaiep: string;
	tiemposervicio: string;
	tiempocontratototal: string;
	codigodiscapacidad: string;
	jeferesponsablecompania: string;
	numerocuentaintercts: string;
	tiempocontratototalacum: string;
	motivo: string;
	costcenterdestination: string;
	posicion: string;
	grupoocupacional: number;
	estadonivelacion: string;
	aprobadornivelacion: number;
	solicitadornivelacion: number;
	dptoareaoperativa: string;
}
export class Personamasttemp {
	transaccionId: number;
	persona: number;
	origen: string;
	apellidopaterno: string;
	apellidomaterno: string;
	nombres: string;
	nombrecompleto: string;
	busqueda: string;
	tipodocumento: string;
	documento: string;
	codigobarras: string;
	tipopersonausuario: string;
	escliente: string;
	esproveedor: string;
	esempleado: string;
	esotro: string;
	tipopersona: string;
	fechanacimiento: Date;
	ciudadnacimiento: string;
	sexo: string;
	nacionalidad: string;
	estadocivil: string;
	nivelinstruccion: string;
	direccion: string;
	departamento: string;
	provincia: string;
	codigopostal: string;
	telefono: string;
	fax: string;
	documentofiscal: string;
	documentoidentidad: string;
	carnetextranjeria: string;
	documentomilitarfa: string;
	tipobrevete: string;
	brevete: string;
	pasaporte: string;
	nombreemergencia: string;
	direccionemergencia: string;
	telefonoemergencia: string;
	bancomonedalocal: string;
	tipocuentalocal: string;
	cuentamonedalocal: string;
	bancomonedaextranjera: string;
	tipocuentaextranjera: string;
	cuentamonedaextranjera: string;
	personaant: string;
	correoelectronico: string;
	clasepersonacodigo: string;
	enfermedadgraveflag: string;
	ingresofecharegistro: Date;
	ingresoaplicacioncodigo: string;
	ingresousuario: string;
	pymeflag: string;
	grupoempresarial: string;
	personaclasificacion: string;
	tarjetadecredito: string;
	flagactualizacion: string;
	celular: string;
	parentescoemergencia: string;
	celularemergencia: string;
	lugarnacimiento: string;
	estado: string;
	ultimousuario: string;
	ultimafechamodif: Date;
	direccionreferencia: string;
	brevetefecvcto: Date;
	carnetextranjeriafecvcto: Date;
	sunatnacionalidad: string;
	sunatvia: string;
	sunatzona: string;
	sunatubigeo: string;
	sunatdomiciliado: string;
	paisemisor: string;
	codigoldn: string;
	codigointerbancario: string;
	flagsolicitausuario: string;
	pais: string;
	correoelectronicootros: string;
	fechainactivacion: Date;
	estadogestionproveedor: string;
	sunatconvenio: string;
	sunatndconvenio: string;
	sunatndexoneracion: string;
	sunatndservicio: string;
	sunatndtiporenta: string;
}
export class ConstanteFichaEmpleado {
	static readonly APLICACION_CODIGO: string = 'HR';
	static readonly COMPANIA: string = '999999';
	static readonly PARAMETRO_FOTO_TAMANIO: string = 'MAXSIFOTO';
	static readonly PARAMETRO_TAMANIO_FOLIO: string = 'MXSIFOLIO';
	static readonly MISC_DOCUMENTOS_EXTENSIONES_PERMITIDOS = 'EXTADJPER';
	static readonly MISCELANEO_TIPO_PARENTESCO: string = 'TIPOVINC';
	static readonly MISCELANEO_TIPO_DOCUMENTO_PERSONA: string = 'TIPODOCI';
	static readonly MISCELANEO_PAIS_EMISOR_DOCUMENTO: string = 'RTPS_TB_26';
	static readonly MISCELANEO_TIPO_VINCULO_FAMILIAR: string = 'RTPS_TB_19';
	static readonly MISCELANEO_TIPO_DOCUMENTO_ACREDITADO: string = 'RTPS_TB_27';
	static readonly MISCELANEO_CODIGO_POSTAL: string = 'RTPS_TB_29';
	static readonly MISCELANEO_GRUPO_SANGUIENEO: string = 'GRUPOSANGR';
	static readonly MISCELANEO_TIPO_VIAS: string = 'TIPOVIAS';
	static readonly MISCELANEO_TIPO_ZONA: string = 'TIPOZONA';
	static readonly MISCELANEO_AREAS: string = 'AREAS';
	static readonly MISCELANEO_COLEGIO_PROFESIONAL: string = 'COLEGIOSPR';
	static readonly MISCELANEO_TIPO_CURSO: string = 'TIPOCURSO';
	static readonly MISCELANEO_TIPO_MODALIDAD: string = 'CPMODALIDA';
	static readonly MISCELANEO_SISTEMA_PENSION: string = 'REGPENSI';
	static readonly MISCELANEO_MOTIVO_CESE: string = 'MOTIVOCESE';
	static readonly MISCELANEO_AREA_EXPERIENCIA: string = 'AREAEXPER';
	static readonly MISCELANEO_TIPO_EXPERIENCIA: string = 'TIPOEXPER';
	static readonly MISCELANEO_TIPO_ENTIDAD: string = 'TIPOENTID';
	static readonly MISCELANEO_DOCUMENTOS_PRESENTADOS: string = 'DOCUMENTOS';
	static readonly MISCELANEO_ESTADO_CIVIL: string = 'ESTCIVIL';
	static readonly MISCELANEO_TIPO_BREVETE: string = 'TIPOBREV';

}