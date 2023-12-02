import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';


export class FiltroComunSyReportearchivo {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    aplicacioncodigo: string;    
    reportecodigo: string;
    estado: string;
}
