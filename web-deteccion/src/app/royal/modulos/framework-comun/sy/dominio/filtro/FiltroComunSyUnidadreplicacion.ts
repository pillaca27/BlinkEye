import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroComunSyUnidadreplicacion {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    unidadreplicacion : string;
    descripcionlocal : string;
    descripcionextranjera : string;
    rangopersonainicio : number;
    rangopersonafin : number;
    personaactual : number;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    tipoexportar:string
}
