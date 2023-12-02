import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';
 

export class FiltroComunAplicacionesmast {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    aplicacioncodigo : string;
    descripcioncorta : string;
    descripcionlarga : string;
    ultimoperiodocontable : string;
    sistemafuente : string;
    estadisponible : string;
    departamentorevisor : string;
    ultimoperiodoprocesado : string;
    aplicacionusuario : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    aplicacionusuario02 : string;
    aplicacionusuario03 : string;
    aplicacionusuario04 : string;
    orden : number;
    tipoexportar:string


     
    
}
