import { DtoSyTipodocumentoproceso } from "./DtoSyTipodocumentoproceso";

export class DtoSyTipodocumento {
	tipodocumentoid: string;
	descripcionlocal: string;
	descripcioningles: string;
	comentarios: string;
	estado: string;
	ultimousuario: string;
	ultimafechamodif: Date;
	link:string;
	detalle: DtoSyTipodocumentoproceso[] = [];
}