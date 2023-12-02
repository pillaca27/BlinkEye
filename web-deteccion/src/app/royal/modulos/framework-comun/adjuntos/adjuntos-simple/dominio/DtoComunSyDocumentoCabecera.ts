import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';
import { DtoComunSyDocumentoanexos } from './DtoComunSyDocumentoanexos';
export class DtoComunSyDocumentoCabecera extends DominioTransaccion {

    constructor() {
        super();
        this.listaExtensiones = [];
        this.listaDocumentos = [];
        this.flgVer = 'N';
        this.flgVerAuditoria = 'N';
        this.flgFechaEditable = 'N';
        this.flgTituloEditable = 'S';
        this.flgVerColumnaPlantilla = 'N';
        this.flagVerSeleccionar = false;
        this.procesarautomatico = 'N';
        this.accion=null
        this.modulo2 = null;
    }

    companiasocio: string;
    modulo: string;
    tipodocumento: string;
    numerodocumento: string;
    numerocontrato: string;
    linea: number;
    sistemacontratacion: string;
    monedadocumento: string;
    preciototal: number;

    listaDocumentos: DtoComunSyDocumentoanexos[];
    listaExtensiones: DtoTabla[];
    flgVer: string;
    flgVerAuditoria: string;
    flgFechaEditable: string;
    flgTituloEditable: string;
    flgVerColumnaPlantilla: string;
    flagVerSeleccionar: boolean;
    procesarautomatico: string;
    flgCambioModalidad:string;
    flgEnviadoWorflow:string;

    accion:any

    modulo2: string;

}