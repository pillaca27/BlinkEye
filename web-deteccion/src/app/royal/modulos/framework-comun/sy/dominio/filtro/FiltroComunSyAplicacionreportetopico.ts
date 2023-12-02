import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroComunSyAplicacionreportetopico {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    aplicacioncodigo : string;
    topico : string;
    descripcionlocal : string;
    descripcioningles : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    uuid : string;
    
}
