import { DtoComunAcReferenciafiscal } from './DtoComunAcReferenciafiscal';
export class DtoComunAcReferenciafiscalSeleccion{

    actividad: DtoComunAcReferenciafiscal;
    financiamiento: DtoComunAcReferenciafiscal;
    gasto: DtoComunAcReferenciafiscal;


    constructor(){
        this.actividad = new DtoComunAcReferenciafiscal();
        this.financiamiento = new DtoComunAcReferenciafiscal();
        this.gasto = new DtoComunAcReferenciafiscal();
    }

}