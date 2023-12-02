import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroComunHrGradoinstruccion {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    gradoinstruccion: string;
    descripcion: string;
    estado: string;
}