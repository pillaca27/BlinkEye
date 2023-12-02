import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunTipodocumentocxp extends DominioTransaccion {
    tipodocumento : string;
    descripcion : string;
    cuentacontablelocal : string;
    cuentacontabledolares : string;
    cuentacontableservlocal : string;
    cuentacontableservdolares : string;
    adelantocuentalocal : string;
    adelantocuentadolares : string;
    esadelanto : string;
    esfiscal : string;
    generarprovisionflag : string;
    adelantomultipleflag : string;
    codigofiscal : string;
    regimenfiscal : string;
    transacciondelsistemaflag : string;
    formatoedicion : string;
    vouchertipo : string;
    vouchertipomateriales : string;
    voucherclasificacion : string;
    controlpresupuestalflag : string;
    controlpresupaplicableflag : string;
    negativoflag : string;
    normalflag : string;
    estado : string;
    ultimafechamodif : Date;
    ultimousuario : string;
    interfasebancariaflag : string;
    clasificacion : string;
    retencionigvflag : string;
    codigosiaf : string;
    controlpresupuestalfiltroflag : string;
    descripcioningles : string;
    prepagorevisionflag : string;
}