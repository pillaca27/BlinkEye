import { DominioMensajeUsuario } from './../../../../../framework/modelo/generico/DominioMensajeUsuario';
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunUsuario extends DominioTransaccion{
    usuario : string;
    usuarioperfil : string;
    nombre : string;
    clave : string;
    expirarpasswordflag : string;
    fechaexpiracion : Date;
    ultimologin : Date;
    numerologinsdisponible : number;
    numerologinsusados : number;
    sqllogin : string;
    sqlpassword : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    usuariored : string;
    horainicio : string;
    horafin : string;
    horainicioap : string;
    horafinap : string;
    valuehsh : string;
    fechacreacion : Date;
    usuariocreacion : string;
    forcelogonspringflag : string;
    fechapassword : Date;
    situacion : string;



    















    persona: number;
    permiso: number;
    frmcodigo: number;
	claveOld: string;
	claveNueva: string;
	claveRep: string;
	li_retorno: number;
	il_vigencia: number;
	listaErrores: DominioMensajeUsuario[];
	informacionConexion: string;
	mostrarPantallaCambioClave: string;
	puedePasar: string;
	aplicacioncodigo: string;
	ultimoperiodocontable: string;
	claveOriginal: string;
	ib_cambiarClave: boolean;
    claveDefault: string;
	compania: string;

	//USUARIO BEAN
    numeroLoginDisponible: String;
    numeroLoginUsados: string;
       
    usuarioupdate: string;
    personagroid: number;  
    personanumero  : number;  
    status: number;
    mensajeuser: string;
    transaccionEstado: string = "OK";
    transaccionListaMensajes: any = [];
}