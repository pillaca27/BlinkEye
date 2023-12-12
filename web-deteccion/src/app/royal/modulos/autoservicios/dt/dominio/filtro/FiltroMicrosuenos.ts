import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroMicrosuenos {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    idMicrosueno : number;
    idAlumno : number;
    idClase : number;
    idDocente: number;
    fechaHora : Date;
    duracion : number;
	fechaDesde: Date;
	fechaHasta: Date;
    
}
