import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunTipopago extends DominioTransaccion {
    constructor() {
        super();
        this.restringidocheck = false
        this.generanumerocheck = false
        this.generavouchercheck = false
    }
    tipopago: string;
    descripcion: string;
    restringidoflag: string;
    cartaflag: string;
    generarnumeracionflag: string;
    entregableflag: string;
    ultimousuario: string;
    ultimafechamodif: Date;
    estado: string;
    vouchersumarizadoflag: string;
    disponiblefeederflag: string;
    codigosiaf: string;
    descripcioningles: string;
    generarcomisionflag: string;
    cuentacomision: string;
    montocomisionlocal: number;
    montocomisionextranjera: number;
    generarinterfaseflag: string;
    tipopagortps: string;
    codigofiscal: string;





    timestamp: object;
    restringidocheck?: boolean
    generanumerocheck?: boolean
    generavouchercheck?: boolean
}