import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroComunDepartamento {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    departamento : string;
    descripcioncorta : string;
    descripcionlarga : string;
    pais : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    tipoexportar:string
}
