export class UsuarioPkBean {
    usuario : string;
}

export class UsuarioBean {    
    pk: UsuarioPkBean = new UsuarioPkBean();    
    usuarioperfil : string;
    nombre : string;
    clave : string;
    expirarpasswordflag : string;
    fechaexpiracion : Date;
    ultimologin : Date;
    numeroLoginDisponible : String;
    numeroLoginUsados : string;
    sqllogin : String;
    sqlpassword : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    usuariored : string;
    horainicio : string;
    horafin : string;
    horainicioap : string;
    horafinap : string;
    fechacreacion : Date;
    usuariocreacion : string;
    forcelogonspringflag : string;
    fechapassword : Date;
    situacion : string;
    usuarioupdate: string;
    personagroid : number;
}