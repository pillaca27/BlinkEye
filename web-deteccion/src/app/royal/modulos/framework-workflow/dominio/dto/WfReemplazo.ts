export class WfReemplazoPk {
    secuencia: number;
}
export class WfReemplazo {
    constructor() {
        this.pk = new WfReemplazoPk();
    }
    pk: WfReemplazoPk;
    tipo: string;
    empleadoAntiguo: number;
    empleadoNuevo: number;
    desde: Date;
    hasta: Date;
    observacion: string;
    estado: string;
    preparadopor: string;
    fechapreparacion: Date;
    modificadopor: string;
    fechamodificacion: Date;

    nuevoNombre: string;
    antiguoNombre: string;
}