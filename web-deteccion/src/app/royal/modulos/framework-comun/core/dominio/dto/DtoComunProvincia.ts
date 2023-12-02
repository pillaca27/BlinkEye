import { DtoComunZonapostal } from './DtoComunZonapostal';
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';

export class DtoComunProvincia extends DominioTransaccion {
    constructor(){
        super();
        this.zonaPostal=[]
        this.accion=''
    }

    pais : string;
    departamento : string;
    provincia : string;
    descripcioncorta : string;
    descripcionlarga : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;    

 
     zonaPostal:DtoComunZonapostal[]
    accion:string
}