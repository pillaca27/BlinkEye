import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroSySeguridadconcepto {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    aplicacioncodigo : string;
    grupo : string;
    concepto : string;
    ordenpresentacion : number;
    descripcionlocal : string;
    descripcioningles : string;
    codigointerno : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    
}
