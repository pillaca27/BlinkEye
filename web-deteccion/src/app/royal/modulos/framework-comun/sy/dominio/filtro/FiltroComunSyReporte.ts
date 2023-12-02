import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';


export class FiltroComunSyReporte {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    aplicacioncodigo: string;
    topico: string;
    reportecodigo: string;
    estado: string;
    tiporeporte: string;
    nombre: string;
    tipoexportar: string
}
