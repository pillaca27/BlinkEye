import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';
export class FiltroPaginacionAgGePersona {

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion
    personaAgroDocumento : string;
    personaAgroBusqueda : string;
}