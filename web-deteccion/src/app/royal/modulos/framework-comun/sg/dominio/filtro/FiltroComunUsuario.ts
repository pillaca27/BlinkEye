

import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class FiltroComunUsuario extends DominioTransaccion{

    constructor() {
        super();
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;    
	
	tipo: String;
	usuario: String;
	nombre: String;
	estado: String;
}