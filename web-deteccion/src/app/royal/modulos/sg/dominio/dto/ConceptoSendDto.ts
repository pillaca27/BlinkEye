import { DominioMensajeUsuario } from './../../../../framework/modelo/generico/DominioMensajeUsuario';

export class ConceptoSendDto {    
    codigoaplicacion: string;
    grupo : string;
    descripcion : string;
    descripcionextrangera : string;
    ordenpresentacion : string;
    tipodetalle : string;
    estado : string;
    ultimousuario : string;
    webflag : string;
    orden :number;
 
    conceptostemp: Concepto[];
    lst: DominioMensajeUsuario[];
    creacionfecha:Date;
    creacionusuario:string;
     
}



export class Concepto {    
    
    idlinea : number;
    concepto: string;
    descripcion : string;
    codigointerno : string;
    estado : string;  
    objetoSelect : string;
    objeto : string;
    esmasestro : string;
    objwindow  : string;
    visible : string;
    orden :number;
    ultimousuario : string;
    ultimafecha : string;
    wpage: string;
    tipodeacceso :string;
    rutaayuda:string;
    descripcionlocal: string;
 
    
}