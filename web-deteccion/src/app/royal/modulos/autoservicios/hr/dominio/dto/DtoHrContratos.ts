import { DtoComunMaMiscelaneosdetalle } from "@framework-comun/core/dominio/dto/DtoComunMaMiscelaneosdetalle";
import { DtoTabla } from "@framework/modelo/generico/dto/DtoTabla";

export class DtoHrContratos {
    constructor() {
        this.lstTipoContratos = [];
        this.lstTiempoRenovacion = [];
    }
    adjunto: string;
    cargo: string;
    centroCostos: string;
    codigoProceso: string;
    codigoPuesto: number;
    codigoUnidad: string;
    companiaSocio: string;
    descripcionCategoriaFuncional: string;
    descripcionCentroCostos: string;
    descripcionCompania: string;
    descripcionEmpleado: string;
    descripcionGradoSalarial: string;
    descripcionPuesto: string;
    descripcionSolicitante: string;
    descripcionSucursal: string;
    descripcionTiempoRenovacion: string;
    descripcionTipoContrato: string;
    descripcionTipoPlanilla: string;
    descripcionUnidadOperativa: string;
    descripcionUnidadOrganigrama: string;
    diaVigencia: string;
    diaPeriodo: string;
    empleado: number;
    estado: string;
    estadoEmpleado: number;
    evaluador: number;
    estadoRenovacion: string;
    estadoSolicitud: string;
    estadoSolicitudRenovacion: string;
    fecha: Date;
    fechaDesde: Date;
    fechaelaboracion: Date;
    fechaFinContrato: Date;
    fechaFinPrueba: Date;
    fechaEvaluacion: Date;
    fechaFirma: Date;
    fechaHasta: Date;
    fechaIngreso: Date;
    fechaInicioContrato: Date;
    fechaInicioPrueba: Date;
    fechaSolicitud: Date;
    flagRenovar: string;
    flgEvaluadoPeriodoPrueba: string;
    flgFechaVencimiento: string;
    flgFirma: string;
    formato: string;
    gradoSalarial: string;
    motivoNoRenovacion: string;
    motivoRenovacion: string;
    nivelAprobacion: number;
    nombre: string;
    nombreCompleto: string;
    nombreJefe: string;
    nombreUnidad: string;
    numeroProceso: number;
    numeroSolicitud: number;
    plantilla: string;
    plandeAccion: string;
    puestoNombre: string;
    secuencia: number;
    secuenciaSolicitud: number;
    solicitante: number;
    sucursal: string;
    sueldo: number;
    sustento: string;
    tiempoContrato: string;
    tiempoContratoTotal: string;
    tiempoPermanencia: string;
    tiempoRenovacion: string;
    tiempoServicio: string;
    tiempoVencimiento: string;
    tipoContrato: string;
    tipoPlanilla: string;
    tipoSolicitudRenovacion: string;
    unidadOperativa: string;

    nombreEstado: string;
    tipoSolicitudRenovar: string;
    nombreEstadoSolicitud: string;
    nombreEstadoRenovacion: string;
    nombreEvaluadoPerPrueba: string;
    tipoContratoDescripcion: string;
    idMotivo: string;
    motivo: string;
    tipocontrato: string;
    fechadesde: Date;
    fechahasta: Date;
    fechainicioprueba: Date;
    fechafinprueba: Date;
    unidadoperativa: string;
    codigopuesto: number;
    centrocostos: string;
    evalperiodopruebaEstado: string;
    evalperiodopruebaPlantillaevaluacion: string;
    ultimoUsuario: string;
    ultimaFechaModif: Date;
    nombreEvaluador: string;
    nombreSolicitante: string;
    fechaAprobacion: Date;

    tipoEvento: String;
    solicitud: number;
    flagbloqueartiemporenovacion: string;
    nombreFormato: string;
    solRenovacionTipocontrato: string;
    solRenovacionTiemporenovacion: string;
    solRenovacionSustento: string;
    solRenovacionAprobador: number;
    solRenovacionEstado: string;
    ivFlagfechavencimiento: string;
    cantidadDiasVigencia: number;
    cantidadDiasPeriodo: number;

    aprobador: number;
    nombreAprobador: string;
    tiempoPrueba: string;
    tiempoAcumulado: string;
    tiempoMeses: number;
    anios: number;
    meses: number;
    dias: number;

    lstTipoContratos: DtoTabla[] = [];
    lstTiempoRenovacion: DtoComunMaMiscelaneosdetalle[] = [];
    tiempoAcumuladoStr: string;
    tiempoMaximo: string;

    auxAccion: string;
    visibleEliminar: string;
    auxDwoName: string;
    auxRutaSustento: string;
    auxTipoSustento: string;
    auxArchivo: string;
    auxString: string;

    nroRegistros: number;

    auxDocumento: string;
    auxFechaIngreso: string;
    auxTiempoIndeterminado: string;
    auxAcumuladoContrato: string;
    auxTiempoPendiente1: string;
    auxTiempoMaximo: string;
    auxTiempoAcumulado: string;
    auxTiempoPendiente2: string;

    obs: string;

    transaccionUUID: string;

}