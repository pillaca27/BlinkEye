import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';


export class FiltroComunProvincia {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    departamento : string;
    provincia : string;
    descripcioncorta : string;
    descripcionlarga : string;
    estado : string;
    ultimafechamodif : Date;
    ultimousuario : string;
    
}
