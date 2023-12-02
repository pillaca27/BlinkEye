import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';
 

export class FiltroComunCompanyowner {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    companyowner : string;
    description : string;
    englishdescription : string;
    percentage : number;
    company : string;
    owner : string;
    changerate : number;
    lastuser : string;
    lastdate : Date;
    
}
