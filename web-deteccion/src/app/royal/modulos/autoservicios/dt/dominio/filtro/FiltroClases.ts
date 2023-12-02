import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroClases {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    idClase : number;
    nombre : string;
    fechaHoraInicio : Date;
    fechaHoraFin : Date;
    idDocente : number;
    
}
