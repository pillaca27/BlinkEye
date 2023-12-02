import { DominioTransaccion } from "@framework/modelo/generico/DominioTransaccion";
import { DtoHrRequerimientoreemplazo } from "./DtoHrRequerimientoreemplazo";

export class DtoHrRequerimiento extends DominioTransaccion {
    companyowner: string;
    requerimiento: string;
    unidadreplicacion: string;
    solicitantecompania: string;
    solicitante: number;
    solicitantecodigounidad: number;
    solicitantepuesto: number;
    fechasolicitud: Date;
    fechaaprobacion: Date;
    fechacontrato: Date;
    fechatermino: Date;
    numerosolicitado: number;
    numeropendiente: number;
    motivo: string;
    puestodescripcion: string;
    vigenciainicio: Date;
    vigenciafin: Date;
    tiporequerimiento: string;
    etapa: number;
    etapacompania: string;
    responsablecompania: string;
    responsable: number;
    codigounidad: number;
    codigopuesto: number;
    unidadoperativa: string;
    jeferesponsablecompania: string;
    jeferesponsable: number;
    centrocosto: string;
    categoria: string;
    gradosalario: string;
    tipotrabajador: string;
    tipoplanilla: string;
    sucursal: string;
    afe: string;
    unidadnegocio: string;
    tipocontrato: string;
    iniciocontrato: Date;
    fincontrato: Date;
    tipohorario: number;
    observaciones: string;
    rutadocumento: string;
    accesooficina: string;
    accesointernet: string;
    accesolaptop: string;
    accesocorreo: string;
    accesotelefono: string;
    accesocelular: string;
    accesopc: string;
    accesoimpresora: string;
    accesootros: string;
    accesootroscomentario: string;
    accesootrossistemas: string;
    accesootrossistemascomentario: string;
    convocatorianumero: string;
    convocatorianumeroacta: number;
    convocatoriafechapostulacion: Date;
    convocatoriafecharesultados: Date;
    convocatoriafechapublicacion: Date;
    convocatoriafechacartamintra: Date;
    codigoproceso: string;
    numeroproceso: number;
    nivelaprobacion: number;
    estado: string;
    motivoAnulado: string;
    motivoRechazo: string;
    motivoDesierto: string;
    motivoTerminado: string;
    motivoSuspendido: string;
    motivoNosuspendido: string;
    estadoSuspendido: string;
    copiacompanyowner: string;
    copiarequerimiento: string;
    ultimousuario: string;
    ultimafechamodif: Date;
    uuid: string;
    transaccionId: number;
    transaccionUUID: string;

    lstReemplazos: DtoHrRequerimientoreemplazo[];

    //Auxiliares
    auxPuestoDesc: string;
    auxSolicitanteNombre: string;
    auxCompaniaNombre: string;
    auxEstadoNombre: string;
    auxUnidadorganigramaDesc: string;
    auxCentrocostoDesc: string;
    auxUnidadNegocioDesc: string;
    auxSolicitanteUnidadorganigramaDesc: string;
    auxTiempoReclutamiento: number;
    auxTiempoTranscurrido: string;
    auxTipoRequerimientoDesc: string;
    auxEtapaEvaluacionDesc: string;
    auxMotivoDesc: string;
    auxMotivoValorCodigo2: string;
    auxMotivoValorCodigo1: string;
    auxResponsableNombreCompleto: string;
    auxCategoriaFuncionalDesc: string;
    auxUnidadOperativaDesc: string;
    auxNivelSalarialDesc: string;
    auxJefeInmediatoNombreCompleto: string;
    auxSalarioMinimo: number;
    auxTipoTrabajadorDesc: string;
    auxTipoContratoDesc: string;
    auxTipoPlanillaDesc: string;
    auxSucursalDesc: string;
    auxAfeDesc: string;
    auxDocumentoAdjuntoBase64: string;

    //ControlesUI
    auxUIVigenciavencimiento: string;
    auxUIHabilitarPuestoDescripcion: string;
    auxUIHabilitarBtnPuesto: string;
    auxUIHabilitarReemplazo: string;


    constructor() {
        super();
        this.lstReemplazos = [];
    }
}