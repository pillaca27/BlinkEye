import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunMaUnidadnegocio extends DominioTransaccion {

    constructor(){
        super();
    }

    
    unidadnegocio : string;
    zona : string;
    descripcionlocal : string;
    descripcioningles : string;
    direccion : string;
    telefonos : string;
    registropatronalplanilla : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    representante : string;
    representantedocumento : string;
    representantecargo : string;
    representante2 : string;
    representantedocumento2 : string;
    representantecargo2 : string;
    representante3 : string;
    representantedocumento3 : string;
    representantecargo3 : string;
    companiasocio : string;


 
    ledger : string;
    persona : number;
}