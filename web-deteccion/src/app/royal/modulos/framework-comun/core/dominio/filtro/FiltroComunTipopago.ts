import { DominioPaginacion } from 'src/app/royal/framework/modelo/generico/DominioPaginacion';
 

export class FiltroComunTipopago {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    tipopago : string;
    descripcion : string;
    restringidoflag : string;
    cartaflag : string;
    generarnumeracionflag : string;
    entregableflag : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    timestamp : object;
    estado : string;
    vouchersumarizadoflag : string;
    disponiblefeederflag : string;
    descripcioningles : string;
    codigosiaf : string;
    codigofiscal : string;
    tipopagortps : string;
    tipoexportar:string
}
