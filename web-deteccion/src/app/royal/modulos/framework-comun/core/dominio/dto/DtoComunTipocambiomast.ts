import { DominioTransaccion } from './../../../../../framework/modelo/generico/DominioTransaccion';
import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';


export class DtoComunTipocambiomast  extends DominioTransaccion{

    constructor() {
		super();
		this.factorcompra=0
        this.factorcomprasbs=0
        this.factorpromedio=0
        this.factorventasbs=0
        this.factorventa=0
      }

    monedacodigo: string;
	monedacambiocodigo: string;
	fechacambio: Date;
	fechacambiostring: string;
	factor: number;
	factorcompra: number;
	factorventa: number;
	factorpromedio: number;
	factorcompraafp: number;
	factorventaafp: number;
	factorcomprasbs: number;
	factorventasbs: number;
	valorcuota: number;
	estado: string;
	ultimafechamodif: Date;
	ultimousuario: string;
	tasatamex: number;
	tasatamn: number;
	tasaanualtamex: number;
	tasaanualtamn: number;
	factorcobranzacompra: number;
    factorcobranzaventa: number;


 

     factorcompra2:string;
     factorventa2:string;
     factorpromedio2:string;
     factorcomprasbs2:string;
     factorventasbs2:string;
    
}