import { DtoTabla } from './../../../../framework/modelo/generico/dto/DtoTabla';
import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';

export class DtoSelectorDinamico extends DtoTabla {
    paginacion: DominioPaginacion;
    constructor() {
        super();
        this.paginacion = new DominioPaginacion();
    }
}