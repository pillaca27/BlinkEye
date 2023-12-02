import { ObjetoBoton } from '@framework/angular/dominio/ObjetoBoton';
import { SeguridadMenuItem } from '@framework/modelo/seguridad/SeguridadMenuItem';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';

export class ObjetoTitulo {
    constructor() {
        this.menuSeguridad = new SeguridadMenuItem();
        this.listaOtros = [];
    }

    listaOtros: any[];
    titulo: string;
    tipo: number;
    menuSeguridad: SeguridadMenuItem;
    componente: any;

    //Mantenimiento
    accion: string;
    puedeEditar: boolean;
    puedeEditarPk: boolean;
    codigoAccion: any;
    objetoBoton: ObjetoBoton[];
}

