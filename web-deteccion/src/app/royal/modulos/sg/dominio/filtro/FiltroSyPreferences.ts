import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroSyPreferences {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }

    paginacion: DominioPaginacion;

    usuario: string;
    preference: string;
    aplicacioncodigo: string;
    tipovalor: string;
    valorstring: string;
    valornumero: number;
    valorfecha: Date;
    ultimousuario: string;
    ultimafechamodif: Date;

    usuariodescripcion: string;
}
