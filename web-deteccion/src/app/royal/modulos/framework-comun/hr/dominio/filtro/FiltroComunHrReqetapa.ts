import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';


export class FiltroComunHrReqetapa {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    companyowner : string;
    etapa : number;
    descripcion : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    
}
