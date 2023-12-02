import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroWfMacroProcesoDetalle {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    macroProcesoId : string;
    procesoOrigenId : string;
    procesoDestinoId : string;
    orden : number;
    creacionUsuario : string;
    creacionFecha : Date;
    modificacionUsuario : string;
    modificacionFecha : Date;
    
}
