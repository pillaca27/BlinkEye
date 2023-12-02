import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class FiltroComunParametrosmast{
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    companiacodigo: string;
	aplicacioncodigo: string;
	parametroclave: string;
	descripcionparametro: string;
    estado: string;
    tipoexportar:string
    
}