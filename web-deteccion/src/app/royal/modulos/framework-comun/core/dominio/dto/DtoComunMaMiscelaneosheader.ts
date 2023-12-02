import { DtoComunMaMiscelaneosdetalle } from './DtoComunMaMiscelaneosdetalle';
import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';
export class DtoComunMaMiscelaneosheader{

    aplicacioncodigo: string;
	codigotabla: string;
	compania: string;
	descripcionlocal: string;
	descripcionextranjera: string;
	estado: string;
	ultimousuario: string;
	ultimafechamodif: Date;
	detalle: DtoComunMaMiscelaneosdetalle[];
	constructor() {
		this.detalle = [];
      }
}