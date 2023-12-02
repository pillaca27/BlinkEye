import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';
import { SeguridadUsuarioActual } from '@framework/modelo/seguridad/SeguridadUsuarioActual';

export class DtoComunMaPersonagrupo extends DominioTransaccion {
    constructor(){
        super();
        this.usuarioActual=new SeguridadUsuarioActual()
    }

    // pk
    personagrupo : string;
    descripcionlocal : string;
    descripcioningles : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    codigointerno : string;
    generarnumeracionflag : string;
    usuarioActual:SeguridadUsuarioActual
    // columnas
    
}
