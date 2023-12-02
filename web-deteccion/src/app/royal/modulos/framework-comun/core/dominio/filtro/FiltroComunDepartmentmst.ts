import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroComunDepartmentmst {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    department : string;
    description : string;
    accountavailableflag : string;
    warehouseavailableflag : string;
    application : string;
    status : string;
    lastuser : string;
    lastdate : Date;
    tipoexportar:string
    
}
