import { DominioPaginacion } from './generico/DominioPaginacion';
import { DominioTransaccion } from './generico/DominioTransaccion';

export class MiscelaneosDetalleTransaccion extends DominioTransaccion {
	aplicacioncodigo: string;
	codigotabla: string;
	compania: string;
	codigoelemento: string;
	descripcionlocal: string;
	descripcionextranjera: string;
	valornumerico: number;
	valorfecha: Date;
	valorcodigo1: string;
	valorcodigo2: string;
	valorcodigo3: string;
	valorcodigo4: string;
	valorcodigo5: string;
	valorcodigo6: string;
	valorcodigo7: string;
	valorcodigo8: string;
	valorcodigo9: string;
	valorcodigo10: string;
	estado: string;
	ultimousuario: string;
	ultimafechamodif: Date;
	paginacion: DominioPaginacion;
}