import { DtoComunZonapostal } from './DtoComunZonapostal';
import { DtoComunProvincia } from './DtoComunProvincia';
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunDepartamento extends DominioTransaccion {

    constructor() {
        super();
        this.provincias = [];
        this.distritos = [];
    }

    departamento: string;
    descripcioncorta: string;
    descripcionlarga: string;
    pais: string;
    estado: string;
    ultimousuario: string;
    ultimafechamodif: Date;


    provincias: DtoComunProvincia[]
    distritos: DtoComunZonapostal[]
    provincia: string
    accion: string
}