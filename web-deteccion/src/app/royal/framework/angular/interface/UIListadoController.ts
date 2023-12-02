import { LazyLoadEvent } from 'primeng/api';
import { UIBaseController } from './UIBaseController';

export declare interface UIListadoController extends UIBaseController {
    // listado
    coreNuevo(): void;
    coreBusquedaRapida(filtro: string): void;
    coreBuscar(tabla: LazyLoadEvent): void;
    coreFiltro(flag: boolean): void;
    coreAnular(dto: any): void;
    coreEliminar(dto: any): void;
    coreEditar(dto: any): void;
    coreVer(dto: any): void;
}
