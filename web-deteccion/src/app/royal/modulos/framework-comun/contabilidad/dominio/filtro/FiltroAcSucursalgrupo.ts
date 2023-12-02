import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroAcSucursalgrupo {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    sucursalgrupo : string;
    sucursalgrupomayor : string;
    descripcionlocal : string;
    descripcioningles : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    uuid : string;
    
}
