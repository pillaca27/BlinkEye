export class Errordto {

    constructor(){
        this.lstParaModificar = [];
    }

    idError: number;
    nombre: string;
    idReglaNegocio: number;
    idCorreo: number;
    idLogAlerta: number;
    idAlerta: number;
    proceso: string;
    descripcionError: string;
    estado: string;
    creacionFecha: Date;
    className: string;
    objetoBasedatos: string;
    mensajeUsuario: string;

    lstParaModificar:any[] = []
    ROWNUM_: number;

    flgInactivar: boolean;




}