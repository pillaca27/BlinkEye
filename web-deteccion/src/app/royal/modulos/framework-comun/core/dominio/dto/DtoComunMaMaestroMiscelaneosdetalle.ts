import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';
export class DtoComunMaMaestroMiscelaneosdetalle extends DominioTransaccion{
	
	aplicacioncodigo: string;
	maestrocodigo: string;
	valorcodigo: string;
	codigo01: string;
	descripcionlocal: string;
	codigo02: string;
	descripcioningles: string;
	codigo03: string;
	numero01: number;
	descripcion01: string;
	estado: string;
	ultimousuario: string;
	ultimafechamodif: Date;
	paginacion: DominioPaginacion;
	codtipo:string;
}