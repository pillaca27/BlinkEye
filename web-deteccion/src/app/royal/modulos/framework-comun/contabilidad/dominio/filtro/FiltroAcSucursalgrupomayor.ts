import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroAcSucursalgrupomayor {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    sucursalgrupomayor : string;
    descripcionlocal : string;
    descripcioningles : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    uuid : string;
    
}
