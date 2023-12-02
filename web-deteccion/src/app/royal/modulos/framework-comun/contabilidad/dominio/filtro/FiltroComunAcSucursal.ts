import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';
export class FiltroComunAcSucursal {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }
    paginacion: DominioPaginacion;

    sucursal: string;
    descripcionlocal: string;
    estado: string;
    companiasocio: string;
    tipoexportar: string;

 
    sucursalgrupo : string;
    descripcioningles : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    cuentacontabledefecto : string;
    almacencodigo : string;
    racionperfil : string;
    departamento : string;
    provincia : string;
    codigopostal : string;
    pais : string;
    direccion : string;
    responsable : number;
   
}