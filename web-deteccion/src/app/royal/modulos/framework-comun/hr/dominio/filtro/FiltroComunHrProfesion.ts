import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroComunHrProfesion {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    profesion: string;
    descripcion: string;
    estado: string;
}