import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroSeguridadconcepto {
    constructor(){
        this.paginacion = new DominioPaginacion();
    }
    
    paginacion: DominioPaginacion;
    
    aplicacioncodigo : string;
    grupo : string;
    concepto : string;
    descripcion : string;
    descripcioningles : string;
    visibleflag : string;
    tipodedetalle : string;
    tipodeobjeto : string;
    objeto : string;
    tipodeacceso : string;
    objetowindow : string;
    workflag : string;
    workagregarflag : string;
    workmodificarflag : string;
    workborrarflag : string;
    workaprobarflag : string;
    webflag : string;
    webpage : string;
    webaction : string;
    webgrupo : string;
    webgruposecuencia : number;
    ultimousuario : string;
    ultimafechamodif : Date;
    tipodeacceso2 : string;
    tipodeacceso3 : string;
    tipodeacceso4 : string;
    workprocesarflag : string;
    workopcion1flag : string;
    workopcion2flag : string;
    workopcion3flag : string;
    workopcion4flag : string;
    workopcion5flag : string;
    orden : number;
    imagen : string;
    jerarquia : string;
    uuid : string;
    
}
