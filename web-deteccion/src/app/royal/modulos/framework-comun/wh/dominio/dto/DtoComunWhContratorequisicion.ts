import { DominioTransaccion } from "../../../../../framework/modelo/generico/DominioTransaccion";

 

export class DtoComunWhContratorequisicion extends DominioTransaccion {
    constructor() {
        super();
    }

    // pk
    companiasocio: string;
    numerocontrato: string;
    secuencia: number;
    requisicionnumero: string;
    requisicionsecuencia: number;
    cantidad: number;

    item: string;
    commodity: string;
    descripcion: string;
    cantidadpedida: number;
    licitacionnumeroproceso: string;
    preciounitario: number;
    precioxcantidad: number;
    pulicitacion: number;
    procesonumero: string;
    // columnas

}
