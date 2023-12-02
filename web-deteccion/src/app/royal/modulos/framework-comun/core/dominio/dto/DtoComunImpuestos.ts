import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunImpuestos extends DominioTransaccion {
    impuesto : string;
    descripcion : string;
    tipoimpuesto : string;
    factorporcentaje : number;
    cuentacontablelocal : string;
    cuentacontabledolares : string;
    signo : string;
    exportableflag : string;
    exportabletipodecambio : string;
    certificadorentaflag : string;
    estado : string;
    ultimafechamodif : Date;
    ultimousuario : string;
    montonoafectoflag : string;
    cuentacontablerevlocal : string;
    cuentacontablerevdolares : string;
    voucherprovisionflag : string;
    montoimponibleflag : string;
    montoexonerado : number;
}