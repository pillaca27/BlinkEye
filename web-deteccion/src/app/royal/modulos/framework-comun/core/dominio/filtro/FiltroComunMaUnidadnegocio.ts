import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';
 

export class FiltroComunMaUnidadnegocio {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    unidadnegocio : string;
    zona : string;
    descripcionlocal : string;
    descripcioningles : string;
    direccion : string;
    telefonos : string;
    registropatronalplanilla : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    companiasocio : string;
    ledger : string;
    representantedocumento : string;
    representante : string;
    persona : number;
    tipoexportar:string
}
