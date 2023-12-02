import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunAcSucursal extends DominioTransaccion {
    sucursal : string;
    descripcionlocal : string;
    descripcioningles : string;
    sucursalgrupo : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    cuentacontabledefecto : string;
    companiasocio : string;
}