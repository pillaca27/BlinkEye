import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroComunHrCursodescripcion {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    curso: number;
    descripcion: string;
    estado: string;
    area: string;
    tipo: string; 
    tipomaestro: string;
}