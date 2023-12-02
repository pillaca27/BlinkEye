import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class FiltroComunAcCashflow {

    tiporegistro: string; 
	tipooperacion: string;
	grupoflujo: string;
	descripcion: string;
	codigo: string;

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

}