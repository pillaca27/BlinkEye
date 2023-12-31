import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunCuentabancaria extends DominioTransaccion {
    cuentabancaria : string;
    banco : string;
    descripcion : string;
    companiacodigo : string;
    fechaapertura : Date;
    fechacierrecuenta : Date;
    monedacodigo : string;
    tipocuenta : string;
    cuentacontable : string;
    sobregiroautorizado : number;
    agenciabanco : string;
    agenciadistrito : string;
    sucursalcodigo : string;
    idioma : string;
    ultimoperiodoconciliacion : string;
    vouchertipo : string;
    voucherclasificacion : string;
    atencionpersona : string;
    atencioncargo : string;
    flujodecajaflag : string;
    flujodecajaorden : number;
    codigodiskette : string;
    conciliacionbancariaflag : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    smartcredflag : string;
    numctabanco : string;
    montoapertura : number;
    cuentabancariaconsolidada : string;
    cuentabancooriginal : string;
    cuentacontabledescuento : string;
    archivodiskette : string;
    referenciafiscal03 : string;
    unidadnegocio : string;
    itfflag : string;
    conciliacionapflag : string;
    conciliacionperiodo : string;
    pagosinterfaseflag : string;
}