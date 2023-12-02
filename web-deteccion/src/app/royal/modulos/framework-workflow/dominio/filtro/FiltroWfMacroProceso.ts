import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroWfMacroProceso {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    macroProcesoId : string;
    nombre : string;
    estado : string;
    creacionUsuario : string;
    creacionFecha : Date;
    modificacionUsuario : string;
    modificacionFecha : Date;
    
}
