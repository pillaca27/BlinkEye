import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';


export class DtlComunTipocambiomast{

    constructor() {    
      }
	// pk
	monedacodigo: string;
	monedacambiocodigo: string;
	fechacambio: Date;	

	// campos
	factor: number;
	factorcompra: number;
	factorventa: number;	
	estado: string;	
	tasatamex: number;
	tasatamn: number;	


 
    fechacambiostring : string;
 
    factorpromedio : number;
    factorcompraafp : number;
    factorventaafp : number;
    factorcomprasbs : number;
    factorventasbs : number;
    valorcuota : number;
 
    ultimafechamodif : Date;
    ultimousuario : string;
 
    tasaanualtamex : number;
    tasaanualtamn : number;
    
}