import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class FiltroComunMaMiscelaneosheader{
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    compania: string;
	aplicacioncodigo: string;
	codigotabla: string;
	descripcionlocal: string;
    estado:string;    
    tipoexportar:string	
}