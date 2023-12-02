import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroComunCorrelativosmast {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    companiacodigo : string;
    tipocomprobante : string;
    serie : string;
    descripcion : string;
    correlativonumero : number;
    correlativodesde : number;
    correlativohasta : number;
    almacencodigo : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    feflag : string;
    fetipocomprobanteref : string;
    feenvio : string;
    tipoexportar:string
    
}
