import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';
 

export class FiltroComunWhContrato {
    constructor(){
        this.paginacion = new DominioPaginacion();
		this.fechadoc_check=false
		this.fechaval_check=true
		this.tc=0
    }
    
    paginacion: DominioPaginacion;
    
     compania:string;
	 unidadnegocio:string;
	 clasificacioncontrato:string;
	 tipocontrato:string;
	 numerocontrato:string;
	 documentointerno:string;
	 modalidad:string;
	 clasificacion:string;
	 estado:string;
	 responsable:string;
	 fechadoc_desde:string;
	 fechadoc_hasta:string;
	 fechavalidez_desde:string;
	 fechavalidez_hasta:string;
	 proveedor:number;
	 moneda:string;
     busqueda:string
	 centrocostos_descri:string
	 centrocostos:string
	 tipoexportar:string
	 tc:number

	 //DATES
	 fechadoc_desde_date:Date;
	 fechadoc_hasta_date:Date;
	 fechavalidez_desde_date:Date;
	 fechavalidez_hasta_date:Date;
	 fechadoc_check:boolean
	 fechaval_check:boolean
    
}
