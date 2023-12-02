import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroSySeguridadgrupo {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    aplicacioncodigo : string;
    grupo : string;
    descripcionlocal : string;
    descripcioningles : string;
    tipodetalle : string;
    ordenpresentacion : number;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    
}
