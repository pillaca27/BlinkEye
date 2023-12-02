import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';


export class FiltroComunTipocambiomast{

    constructor() {
        this.paginacion = new DominioPaginacion();
      }
    
	fechacambio: Date;		
	estado: string;	
    
    paginacion: DominioPaginacion;


    monedacodigo : string;
    monedacambiocodigo : string;
 
    fechacambiostring : string;
    factor : number;
    factorcompra : number;
    factorventa : number;
    factorpromedio : number;
    factorcompraafp : number;
    factorventaafp : number;
    factorcomprasbs : number;
    factorventasbs : number;
    valorcuota : number;
  
    ultimafechamodif : Date;
    ultimousuario : string;
    tasatamex : number;
    tasatamn : number;
    tasaanualtamex : number;
    tasaanualtamn : number;
    tipoexportar:string

}