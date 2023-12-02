import { DtoWfFlujoNivelAprobador } from './DtoWfFlujoNivelAprobador';
import { DtoWfFlujoNivelDocumento } from './DtoWfFlujoNivelDocumento';
import { DtoWfFlujoNivelAccion } from './DtoWfFlujoNivelAccion';

export class DtoWfFlujoNivel {
    proceso: string;
    flujo: number;
    nivel: number;
    origenDatos: number;
    estado: string;
    correojefe: boolean;
    correosolicitante: boolean;
    correootros: string;

    aprobadores: DtoWfFlujoNivelAprobador[];
    documentos: DtoWfFlujoNivelDocumento[];
    acciones: DtoWfFlujoNivelAccion[];

    jefeAux: string;
    soliAux: string;

    spvalidar: string;
    sprechazar: string;
    spaprobar: string;
    spdevolver: string;
    documentoFlgColumnaNuevo: string;
    documentoFlgColumnaGrupo: string;
    api: string;
    componenteweb: string;
    tipoaprobador: string;
    condicionaprobacion: string;

    btnAprobar: boolean;
    btnRechazar: boolean;
    btnDevolver: boolean;
    btnNotificar: boolean;

    constructor() {
        this.aprobadores = [];
        this.documentos = [];
        this.acciones = [];
    }

    nombre: string;
    duraciontipo: string;
    duracioncantidad: number;

    auxFlagPlanificacionVer: boolean;
    auxFlagPlanificacionEditar: boolean;

    planificacionTag: string;
    auxFlgPlanificacionValidar: boolean;

    auxDocumentoFlgColumnaNuevo: boolean;
    auxDocumentoFlgColumnaGrupo: boolean;

    flgComentarioDetalladoAprobar: string;
    flgComentarioDetalladoRechazar: string;
    flgComentarioDetalladoDevolver: string;

    auxFlgComentarioDetalladoAprobar: boolean;
    auxFlgComentarioDetalladoRechazar: boolean;
    auxFlgComentarioDetalladoDevolver: boolean;

    auxFlgCorreoPersonaReferencia: boolean;
    flgCorreoPersonaReferencia: string;

    auxFlgCorreoTransaccion: boolean;
    flgCorreoTransaccion: string;

    auxFlgCorreoPersona: boolean;
    flgCorreoPersona: string;

    auxFlgAprobarComentario: boolean;
    flgAprobarComentario: string;

    auxFlgCorreoAdjuntarDocumentos: boolean;
    flgCorreoAdjuntarDocumentos: string;

    tienePlantillaAprobar:string;
    tienePlantillaRechazar:string;
    tienePlantillaDevolver:string;
}