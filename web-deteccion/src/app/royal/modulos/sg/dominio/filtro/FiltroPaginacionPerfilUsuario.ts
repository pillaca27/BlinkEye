import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroPaginacionPerfilUsuario {

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;
    usuario : string;
    labeelbutton : string;


    tipoexportar: string;
}