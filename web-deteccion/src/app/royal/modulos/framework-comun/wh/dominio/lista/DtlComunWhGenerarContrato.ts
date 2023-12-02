import { DominioTransaccion } from "../../../../../framework/modelo/generico/DominioTransaccion";
import { DtoComunWhContrato } from "../dto/DtoComunWhContrato";
import { DtoComunWhContratodetalle } from "../dto/DtoComunWhContratodetalle";
import { DtlComunWhRequisiciondetalle } from "./DtlComunWhRequisiciondetalle";

 

export class DtlComunWhGenerarContrato extends DominioTransaccion {
    constructor(){
        super();
		this.lstRequisionDetalle=[]
		this.dw1=[]
		this.dw_detail=[]
		this.dw_src_header=[]
    }
      companiasocio:string;
	  cotizacionproveedor:number;
	  cotizacionmoneda:string;
	  busqueda:string;
	  tipoproveedor:string;
	  almacencodigo:string;
	  formapago:string;
	  flag01:string;
	  requisicionnumero:string;
	  unidadnegocio:string;
	  responsable:string;
	  comentarios:string;
	  plazoentrega:number

	  //adicionales
	  formapagoproveedor:string
	  personacontacto:string
	  fecharenovacion:Date


	  dw1:DtoComunWhContrato[]
	  dw_detail:DtoComunWhContratodetalle[]
	  dw_src_header:DtlComunWhGenerarContrato[]
	  lstRequisionDetalle:DtlComunWhRequisiciondetalle[]
}