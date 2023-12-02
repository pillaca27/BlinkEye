import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunAcCostcenterdestvalid extends DominioTransaccion {
    constructor() {
        super();
        this.accion = ''
        this.descripcion = ''
    }
    costcenter: string;
    costcenterdestination: string;
    status: string;
    lastuser: string;
    lastdate: Date;

    accion: string
    descripcion: string


    
}