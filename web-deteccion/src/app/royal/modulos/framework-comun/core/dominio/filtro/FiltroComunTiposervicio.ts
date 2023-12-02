import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';
 
export class FiltroComunTiposervicio {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    tiposervicio : string;
    descripcion : string;
    regimenfiscal : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    timestamp : object;
    clasificacionfiscal : string;
    estado : string;
    descripcioningles : string;
    tipoexportar:string
}
