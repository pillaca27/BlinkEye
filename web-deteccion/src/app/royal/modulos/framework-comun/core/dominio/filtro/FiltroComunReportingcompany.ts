import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';
 

export class FiltroComunReportingcompany {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    companiacodigo : string;
    companyowner : string;
    percentage : number;
    
}
