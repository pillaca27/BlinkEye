import { DominioTransaccion } from "../../../../../framework/modelo/generico/DominioTransaccion";

 

export class DtoComunWhContratovencimiento extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    companiasocio : string;
    numerocontrato : string;
    secuencia : number;
    linea : number;
    fechavencimiento : Date;
    cantidad : number;
    cantidadreal : number;
    preciounitario : number;
    montoimponible : number;
    montoimpuestos : number;
    montototal : number;
    referenciatipodocumento : string;
    referencianumerodocumento : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    centrocosto : string;
    proyecto : string;
    camporeferencia : string;
    referenciasecuencia : number;
    documentoreferencia : string;
    montocimpuestos:number
    montoimponibleheader:number
    montototalheader:number
    accion:string

    // columnas
    
}
