export class SeguridadMenuItem {
    id: string;
    label: string;
    icon: string;
    routerLink: string;
    action: string;
    orden: number;
    grupo: string;
    tipoDeAcceso: string;
    flgAgregar: boolean;
    flgModificar: boolean;
    flgBorrar: boolean;
    items: any[]; 

    urlAyuda: string;

    auxFechaInicioPerido: Date;
    auxFechaFinPerido: Date;

}