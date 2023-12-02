import { DtoWfFlujo } from './DtoWfFlujo';
import { DtoWfEstado } from './DtoWfEstado';
import { DtoWfVariable } from './DtoWfVariable';
import { DtoWfTipoDocumento } from './DtoWfTipoDocumento';
import { DtoWfRol } from './DtoWfRol';

export class DtoWfProceso {
    auxEsNuevaVersion: string;
    proceso: string;
    descripcion: string;
    estado: string;
    aplicacion: string;
    aplicacionAux: string;
    componenteweb: string;
    version: number;
    api: string;
    spver: string;
    origen: number;
    segmentocodigotabla: string;

    variables: DtoWfVariable[];
    estados: DtoWfEstado[];
    tipodocumentos: DtoWfTipoDocumento[];

    flujos: DtoWfFlujo[];
    roles: DtoWfRol[];

    constructor() {
        this.tipodocumentos = [];
        this.estados = [];
        this.variables = [];
        this.flujos = [];
        this.roles = [];
    }

    flagPlanificacionGenerar: boolean;
    flgPlanificacionGenerarString: string;
    administradorNombre: string;
    administradorId: number;
    nivelestadoidinicial: string;
    flagComunicacionAlerta: boolean;
    flagComunicacionAlertaString: string;

    flagCorreoNiveles: boolean;
    flagCorreoNivelesString: string;

    tienePlantillaAprobar:string;
    tienePlantillaRechazar:string;
    tienePlantillaDevolver:string;
    tienePlantillaGuardar:string;
    tienePlantillaSeguimiento:string;
    tienePlantillaAlerta:string;
}