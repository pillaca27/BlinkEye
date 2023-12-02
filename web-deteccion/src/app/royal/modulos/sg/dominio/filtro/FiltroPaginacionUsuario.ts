import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroPaginacionUsuario {

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;
    nombre: string;
    codigoUsuario: string;
    usuarioPerfil: string;
    estado: string;
    labeelbutton: string;
    tipoexportar: string;



}