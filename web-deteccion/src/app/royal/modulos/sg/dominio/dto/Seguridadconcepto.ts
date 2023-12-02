export class SeguridadconceptoPk {
    aplicacioncodigo : string;
    grupo : string;
    concepto : string;
}

export class Seguridadconcepto {    
    pk: SeguridadconceptoPk = new SeguridadconceptoPk();    
    descripcion : string;
    visibleflag : string;
    tipodedetalle : string;
    tipodeobjeto : string;
    objeto : Date;
    tipodeacceso : Date;
    objetowindow : String;
    workflag : string;
    workagregarflag : String;
    workmodificarflag : string;
    workborrarflag : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    workaprobarflag : string;
    xestado : string;
    descripcioningles : string;
    webaction : string;
    webflag : string;
    webgrupo : string;
    webpage : string;
    webgruposecuencia : Number;
    conceptopadre : Date;
    imagen : string;
    
    estilo : string;
    conceptorelacion : string;
    auxFlgPreparado : boolean;

   
}