import { DominioTransaccion } from './generico/DominioTransaccion';

export class PersonamastTransaccion extends DominioTransaccion{    	
	persona: number;
	busqueda: string;
	nombrecompleto:string;
	nombres: string;
	apellidopaterno: string;
	apellidomaterno: string;
	sexo: string;
	tipodocumento: string;
	tipopersona: string;
	fechanacimiento: Date;
	codigousuario: string;
	escliente: string;
	esproveedor: string;
	esempleado: string;
	esotro: string;
	documento: string;
	documentofiscal: string;
	documentoidentidad: string;	
	estado: string;
	estadoNombre: string;
	correoelectronico: string;
		
	direccion: string;
	telefono: string;
	pais: string;
	moneda: string;
	formapago: string;
	fax: string;
	tiposervicio: string;
	personaContacto: string;


}