import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';
 

export class FiltroComunZonapostal {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    departamento : string;
    provincia : string;
    codigopostal : string;
    descripcioncorta : string;
    descripcionlarga : string;
    cobrador : number;
    estado : string;
    ultimafechamodif : Date;
    ultimousuario : string;
    
}
