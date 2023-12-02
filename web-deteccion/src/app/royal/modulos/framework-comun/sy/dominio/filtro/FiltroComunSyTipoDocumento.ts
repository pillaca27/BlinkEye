import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';


export class FiltroComunSyTipoDocumento {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    codigo: string;    
    estado: string;
    comentarios: string;
    nombre: string;
}
