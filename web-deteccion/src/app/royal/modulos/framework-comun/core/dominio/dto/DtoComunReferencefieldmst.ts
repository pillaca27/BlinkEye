import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunReferencefieldmst extends DominioTransaccion {
    referencefield: string;
	localname: string;
	englishname: string;
	screentitle: string;
	length: number;
	internalcodeflag: string;
	internalcode: string;
	tablevalidatedflag: string;
	availableflag: string;
	status: string;
	lastuser: string;
	lastdate: Date;
}