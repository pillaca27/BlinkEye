import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';
export class FiltroWhCiiu  {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    ciiu : string;
    descripcion : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    
}
