import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';
import { DominioAdjunto } from './../generico/DominioAdjunto';
import { EmailDestino } from './EmailDestino';
export class EmailTransaccion extends DominioTransaccion{
    remitenteNombre: string;
	remitenteCorreo: string;
	asunto: string;
	cuerpoCorreoBytes: string;
	cuerpoCorreoBase64: string;
	
	listaCorreoDestino: EmailDestino[];
	listaCorreoAdjunto: DominioAdjunto[];

    flgCorreoPrueba: boolean;
    
    constructor() {
        super();
        this.listaCorreoDestino = [];
        this.listaCorreoAdjunto = [];
    }

}