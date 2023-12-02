export class DtoAprobacionAcciones {
    nivelActual: number;
    puedeAprobar: string;
    puedeRechazar: string;
    puedeDevolver: string;
    botonesaprobar: DtoAprobacionAccion[];
    botonesrechazar: DtoAprobacionAccion[];
    botonesdevolver: DtoAprobacionAccion[];
    constructor() {
        this.botonesaprobar = [];
        this.botonesdevolver = [];
        this.botonesrechazar = [];
    }
}
export class DtoAprobacionAccion {
    nombreBoton: string;
    subaccion: string;
    icon: string;
}