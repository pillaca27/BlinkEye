import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';


export class FiltroComunSyReportearchivoWf {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    aplicacioncodigo: string;    
    reportecodigo: string;
    estado: string;
}
