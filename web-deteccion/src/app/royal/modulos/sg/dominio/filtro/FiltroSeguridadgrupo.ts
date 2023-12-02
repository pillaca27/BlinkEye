import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroSeguridadgrupo {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    aplicacioncodigo : string;
    grupo : string;
    descripcion : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    orden : number;
    webgrupo : string;
    webgruposecuencia : string;
    imagen : string;
    uuid : string;
    grupopadre : string;
    
}
