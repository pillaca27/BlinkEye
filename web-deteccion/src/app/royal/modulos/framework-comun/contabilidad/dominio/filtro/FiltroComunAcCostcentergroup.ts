import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';
 

export class FiltroComunAcCostcentergroup {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    costcentergroup : string;
    subgrupo:string
    localname : string;
    englishname : string;
    costcentermajorgroup : string;
    status : string;
    lastuser : string;
    lastdate : Date;
    tipoexportar:string
}
