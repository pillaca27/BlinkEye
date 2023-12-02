import { DominioTransaccion } from './generico/DominioTransaccion';
import { PersonamastTransaccion } from './PersonamastTransaccion';

export class EmpleadomastTransaccion extends PersonamastTransaccion{    	
	empleado: number;
	estadoEmpleado: string;
	estadoEmpleadoNombre: string;
	centrocostos: string;
	centrocostosNombre: string;
	unidadnegocioasignada: string;
	unidadnegocioasignadaNombre: string;	
	companiasocio: string;
	companiasocioNombre: string;	
	sucursal: string;
	fechaingreso: Date;
	fechainiciocontrato: Date;
	fechafincontrato: Date;
	tipohorario: number;
	sueldoactuallocal: number;
	codigocargo: number;
	codigocargoNombre: string;
	tipocontrato: string;
	tipocontratoNombre: string;
	tipoplanilla: string;
	tipoplanillaNombre: string;
	cargo: string;
	cargoNombre: string;
	deptoorganizacion: string;
	deptoorganizacionNombre: string;
}