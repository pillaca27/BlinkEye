import { DominioTransaccion } from './generico/DominioTransaccion';

export class ParametroTransaccion extends DominioTransaccion {
    
    companiacodigo:string;
	aplicacioncodigo:string;
	parametroclave:string;
	
	descripcionparametro:string;
	
	explicacion:string;
	texto:string;
	numero:number;
	fecha:Date;
}