export class SyAdjuntoPk{            
    nombretabla : string;
    clavetabla : string;
    secuencia : number;
}

export class SyAdjunto {

    constructor(){
        this.pk = new SyAdjuntoPk();
    }

    

    pk:SyAdjuntoPk;

    comentario : string;
    archivo : string;
    ultimafechamodif : Date;
    ultimousuario : string;

    archivoadjunto: any;
    archivoadjuntostring: string;

    ruta: string;
    secuenciawh?:number
}
