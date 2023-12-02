import { SeguridadUsuarioActual } from './../../../../../framework/modelo/seguridad/SeguridadUsuarioActual';
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunProveedormast extends DominioTransaccion {
    
    constructor(){
        super();
        this.tipopago=null
        this.usuarioActual=new SeguridadUsuarioActual()
        this.flagNacionalidad=''
    }

    proveedor : number;
    tiposervicio : string;
    tipoproveedor : string;
    pagaranombre : string;
    pagarendireccion : string;
    tipopago : string;
    monedapago : string;
    formadepago : string;
    representantelegal : string;
    responsible : string;
    cuentacontablegasto : string;
    chequenonegociableflag : string;
    suspensionrentaflag : string;
    suspensionfonaviflag : string;
    exoneradodeclaracion : string;
    exoneradofechainicio : Date;
    exoneradofechafinal : Date;
    numerodiaspago : number;
    numerodiasentrega : number;
    tipodocumentodefault : string;
    cobradortipodocumento : string;
    cobradornumerodocumento : string;
    cobradornombre : string;
    comentarioservicio : string;
    comentariofecha : Date;
    comentariousuario : string;
    clasificacionfiscal : string;
    intervencionfiscalflag : string;
    intervencionfiscaldocumento : string;
    registropublico : string;
    licenciamunicipal : string;
    fechaconstitucion : Date;
    personacontacto : string;
    experienciatiempo : string;
    proveedorrelacionado : number;
    exoneradofonavideclaracion : string;
    exoneradofonavifechainicio : Date;
    exoneradofonavifechafinal : Date;
    detraccioncodigo : string;
    detraccioncodigoflag : string;
    detraccioncuentabancaria : string;
    retenerpagosflag : string;
    representantelegal2 : string;
    fechaautorizacionfiscal : Date;
    intervencionfiscalcomentario : string;
    intervencionfiscalfecha : Date;
    exoneradomediopresentacion : string;
    afpretencionflag : string;
    afpcodigo : string;
    afpcuspp : string;
    afptipocomision : string;
    nohabidoflag : string;
    habilitadornpflag : string;
    nohalladoflag : string;
    correoelectronicocre : string;
    
    busqueda : string;
    estado : string;












   
    detraccionnoafectoflag : string;
    usuarioActual:SeguridadUsuarioActual
    accion:string
    flagNacionalidad?:string
}