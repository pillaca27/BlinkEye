import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';
export class UsuarioPk {
    usuario: string;
}

export class Usuario extends DominioTransaccion {
    pk: UsuarioPk = new UsuarioPk();
    usuarioperfil: string;
    nombre: string;
    clave: string;
    expirarpasswordflag: string;
    fechaexpiracion: Date;
    ultimologin: Date;
    numeroLoginDisponible: String;
    numeroLoginUsados: string;
    sqllogin: string;
    sqlpassword: string;
    estado: string;
    ultimousuario: string;
    ultimafechamodif: Date;
    usuariored: string;
    horainicio: string;
    horafin: string;
    horainicioap: string;
    horafinap: string;
    fechacreacion: Date;
    usuariocreacion: string;
    forcelogonspringflag: string;
    fechapassword: Date;
    situacion: string;
    usuarioupdate: string;
    personagroid: number;
    usuarioBean: Usuario;
    status: number;
    mensajeuser: string;

    personanumero: number;

    usuario: string;
}