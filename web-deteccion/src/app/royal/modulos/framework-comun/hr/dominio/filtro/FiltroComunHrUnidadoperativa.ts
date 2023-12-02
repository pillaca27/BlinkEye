import { DominioPaginacion } from "../../../../../framework/modelo/generico/DominioPaginacion";

export class FiltroComunHrUnidadoperativa{
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    unidadoperativa : string;
    descripcion : string;
    responsable : number;
    unidadoperativaSuperior : string;
    responsablecontratos : number;
    responsablecompania : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
}