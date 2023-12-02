import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroComunBanco {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    banco : string;
    descripcioncorta : string;
    descripcionlarga : string;
    banconumero : number;
    personacontacto : string;
    conciliacionautomaticaflag : string;
    formatopropioflag : string;
    formatodatawindow : string;
    estado : string;
    ultimafechamodif : Date;
    timestamp : object;
    ultimousuario : string;
    conciliacionformatoflag : string;
    codigointerfaseafp : string;
    disponibleedflag : string;
    tasaefectivaanual : number;
    codigofiscal : string;
    tipoexportar:string
}
