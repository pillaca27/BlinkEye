export class SeguridadgrupoPk {
    aplicacioncodigo : string;
    grupo : string;
     
}

export class Seguridadgrupo {    
    pk: SeguridadgrupoPk = new SeguridadgrupoPk();    
    descripcion : string;
      
    ultimafechamodif : Date;
    imagen : String;
    auxFlgPreparado : boolean;
    sqllogin : String;
    sqlpassword : string;
    estado : string;
    ultimousuario : string;
    mensajeresponse : number;
    
   
    
}