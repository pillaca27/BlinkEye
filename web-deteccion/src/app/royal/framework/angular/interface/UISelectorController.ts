import { MensajeController } from './../dominio/MensajeController';
import { Table } from 'primeng/table';
import { UIBaseController } from './UIBaseController';

export declare interface UISelectorController extends UIBaseController {
    // selector
    coreBusquedaRapida(filtro: string): void;
    coreBuscar(tabla: Table): void;
    coreFiltro(flag: boolean): void;
    coreSalir(): void;
    //Solo para selectores
    coreSeleccionar(seleccion: any): void;
    coreIniciarComponente(mensaje: MensajeController):void;
}
