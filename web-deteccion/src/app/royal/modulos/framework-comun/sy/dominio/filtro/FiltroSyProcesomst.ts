import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';


export class FiltroSyProcesomst {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }

    paginacion: DominioPaginacion;

    aplicacioncodigo: string;
    procesocodigo: string;
    descripcion: string;
    estado: string;
    ultimousuario: string;
    ultimafechamodif: Date;


    descripcioncorta: string;
}
