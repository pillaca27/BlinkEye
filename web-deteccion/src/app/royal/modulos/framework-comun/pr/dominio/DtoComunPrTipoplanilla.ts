import { DominioTransaccion } from "@framework/modelo/generico/DominioTransaccion";

export class DtoComunPrTipoplanilla extends DominioTransaccion{
    tipoplanilla : string;
    funcion : number;
    descripcion : string;
    titulodeboleta : string;
    descripcionextranjera : string;
    tipodecambio : number;
    clasepago : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    mensaje : string;
    planillaperfil : number;
    periodicidad : string;
    categoriatrabajador : string;
    tipotrabajador : string;
    cuentacontable : string;
    flujocaja : string;
    afectoutilidad : string;
    diasvacaciones : number;
}