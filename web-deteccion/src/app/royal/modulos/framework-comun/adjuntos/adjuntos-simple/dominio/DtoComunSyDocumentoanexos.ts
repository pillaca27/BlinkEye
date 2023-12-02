import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';

export class DtoComunSyDocumentoanexos extends DominioTransaccion {

	constructor() {
		super();
		this.auxFlgAdjuntoObligatorio = 'S';
	}

	companiasocio: string;
	modulo: string;
	tipodocumento: string;
	numerodocumento: string;
	linea: number;
	secuencia: number;
	fecha: Date;
	descripcion: string;
	comentario: string;
	rutaarchivo: string;
	estado: string;
	ultimousuario: string;
	ultimafechamodif: Date;
	archivo: string;
	archivostring: string;
	procesoTipoDocumentoId: string;
	auxFlgAdjuntoObligatorio: string;
	auxRutaPlantilla: string;
	auxflgTituloEditable: string;
	auxConfirmar: string;
	auxConfirmarMensaje: string;
	validarporrango: string;
	montorango1inicio: number;
	montorango1fin: number;

}