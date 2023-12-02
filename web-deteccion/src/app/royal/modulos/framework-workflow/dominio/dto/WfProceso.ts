export class WfProcesoPk {
    procesoid: number;
}
export class WfProceso {
    pk: WfProcesoPk;
    constructor() {
        this.pk = new WfProcesoPk();
    }
    sigla: string;
    nombre: string;
    estado: string;
    aplicacion: string;
}