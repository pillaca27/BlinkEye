import { DtoWfFlujoConfiguracion } from './DtoWfFlujoConfiguracion';
import { DtoWfFlujoNivel } from './DtoWfFlujoNivel';

export class DtoWfFlujo {

    proceso: string;
    flujo: number;
    descripcion: string;

    configuraciones: DtoWfFlujoConfiguracion[];
    niveles: DtoWfFlujoNivel[];

    constructor() {
        this.configuraciones = [];
        this.niveles = [];
    }
}