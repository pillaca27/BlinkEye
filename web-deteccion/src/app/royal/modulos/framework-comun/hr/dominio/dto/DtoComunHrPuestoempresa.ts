import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';
export class DtoComunHrPuestoempresa extends DominioTransaccion{
    
    codigopuesto : number;
    gradosalario : string;
    descripcion : string;
    descripcioningles : string;
    comentarios : string;
    estado : string;
    unidadnegocio : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    unidadreplicacion : string;
    tipopuesto : number;
    categoriafuncional : string;
    puestosuperior : number;
    plantillaevaluacion : string;
    plantilladocumento : number;
    codanterior : string;
    codigocap : number;
    descripcioncap : string;
    grupoocupacional : number;
    plantillapotencial : string;
    codigortps : string;
    
    flagpuestopracticante:string;
    tiemporeclutar:number;
    tipotrabajador:string;
}