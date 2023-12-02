import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { DtoWfMacroProcesoDetalle } from '../dominio/dto/DtoWfMacroProcesoDetalle';
import { FiltroWfMacroProcesoDetalle } from '../dominio/filtro/FiltroWfMacroProcesoDetalle';

@Injectable()
export class WfMacroProcesoDetalleService {

    private url = `${this.config.getEnv('spring-framework-workflow-api')}spring/workflow/wfmacroprocesodetalle/`;
    constructor(private config: AppConfig) { }

    public obtenerDtoPorId(macroProcesoId : string, procesoOrigenId : string, procesoDestinoId : string, orden : number, creacionUsuario : string, creacionFecha : Date, modificacionUsuario : string, modificacionFecha : Date): Promise<DtoWfMacroProcesoDetalle> {
        var dto = new DtoWfMacroProcesoDetalle();
        dto.macroProcesoId = macroProcesoId;
        dto.procesoOrigenId = procesoOrigenId;
        dto.procesoDestinoId = procesoDestinoId;
        dto.orden = orden;
        dto.creacionUsuario = creacionUsuario;
        dto.creacionFecha = creacionFecha;
        dto.modificacionUsuario = modificacionUsuario;
        dto.modificacionFecha = modificacionFecha;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoWfMacroProcesoDetalle)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoWfMacroProcesoDetalle): Promise<DtoWfMacroProcesoDetalle> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoWfMacroProcesoDetalle)
            .catch(error => null);
    }

    public registrar(dto: DtoWfMacroProcesoDetalle): Promise<DtoWfMacroProcesoDetalle> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoWfMacroProcesoDetalle)
            .catch(error => null);
    }

    public actualizar(dto: DtoWfMacroProcesoDetalle): Promise<DtoWfMacroProcesoDetalle> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoWfMacroProcesoDetalle)
            .catch(error => null);
    }

    public anular(dto: DtoWfMacroProcesoDetalle): Promise<DtoWfMacroProcesoDetalle> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoWfMacroProcesoDetalle)
            .catch(error => null);
    }

    public eliminar(dto: DtoWfMacroProcesoDetalle): Promise<DtoWfMacroProcesoDetalle> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoWfMacroProcesoDetalle)
            .catch(error => null);
    }

    public listar(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarActivos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarDtoFiltros(macroProcesoId : string, procesoOrigenId : string, procesoDestinoId : string, orden : number, creacionUsuario : string, creacionFecha : Date, modificacionUsuario : string, modificacionFecha : Date): Promise<DtoWfMacroProcesoDetalle[]> {
        var filtro = new DtoWfMacroProcesoDetalle();
        filtro.macroProcesoId = macroProcesoId;
        filtro.procesoOrigenId = procesoOrigenId;
        filtro.procesoDestinoId = procesoDestinoId;
        filtro.orden = orden;
        filtro.creacionUsuario = creacionUsuario;
        filtro.creacionFecha = creacionFecha;
        filtro.modificacionUsuario = modificacionUsuario;
        filtro.modificacionFecha = modificacionFecha;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoWfMacroProcesoDetalle[])
            .catch(error => []);
    }

    public listarDtoActivos(macroProcesoId : string, procesoOrigenId : string, procesoDestinoId : string, orden : number, creacionUsuario : string, creacionFecha : Date, modificacionUsuario : string, modificacionFecha : Date): Promise<DtoWfMacroProcesoDetalle[]> {
        var filtro = new DtoWfMacroProcesoDetalle();
        filtro.macroProcesoId = macroProcesoId;
        filtro.procesoOrigenId = procesoOrigenId;
        filtro.procesoDestinoId = procesoDestinoId;
        filtro.orden = orden;
        filtro.creacionUsuario = creacionUsuario;
        filtro.creacionFecha = creacionFecha;
        filtro.modificacionUsuario = modificacionUsuario;
        filtro.modificacionFecha = modificacionFecha;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoWfMacroProcesoDetalle[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroWfMacroProcesoDetalle): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

}
