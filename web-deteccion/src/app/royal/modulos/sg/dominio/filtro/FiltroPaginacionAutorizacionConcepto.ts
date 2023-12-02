import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroPaginacionAutorizacionConcepto {

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;
    codigoAplicacion: string;
    usuario: string;
    grupo: string;

    tipoexportar: string;

    usuariodescripcion: string;
    seleccionar: boolean;
    seleccionar2: boolean;


    flgBorrar: boolean;
    flgAprobar: boolean;
    flgModificar: boolean;
    flgAgregar: boolean;



}