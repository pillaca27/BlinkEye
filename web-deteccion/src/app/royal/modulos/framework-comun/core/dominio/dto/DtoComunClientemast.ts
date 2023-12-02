import { DominioTransaccion } from './../../../../../framework/modelo/generico/DominioTransaccion';
 

export class DtoComunClientemast extends DominioTransaccion {
    constructor(){
        super();
    }

    // pk
    cliente : number;
    clasificacion : string;
    ingresomensual : number;
    esgarante : string;
    lineacreditomoneda : string;
    lineacredito : number;
    tipoactividad : string;
    cantidaddependientes : number;
    totalacumulado : number;
    formadepago : string;
    tiposervicio : string;
    numerodiascobranza : number;
    resoluciondirectoral : string;
    licenciafechadesde : Date;
    licenciafechahasta : Date;
    practicotmcflag : string;
    licencianumero : string;
    pagoefectivoflag : string;
    suspensionflag : string;
    tipofacturacion : string;
    tipoventa : string;
    conceptofacturacion : string;
    vendedor : number;
    centrocosto : string;
    formafacturacion : string;
    tipocliente : string;
    rutadespacho : string;
    tipodocumento : string;
    personacontacto : string;
    lineacreditofechavencimiento : Date;
    nacionalidad : string;
    garantedireccion : string;
    garanteclasificacion : string;
    garantedocumento : string;
    garantenombre : string;
    financiamientotasa : number;

    // columnas
    
}
