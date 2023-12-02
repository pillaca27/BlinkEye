import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroComunMaPersonagrupo {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    personagrupo : string;
    descripcionlocal : string;
    descripcioningles : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    codigointerno : string;
    generarnumeracionflag : string;
    tipoexportar:string
}
