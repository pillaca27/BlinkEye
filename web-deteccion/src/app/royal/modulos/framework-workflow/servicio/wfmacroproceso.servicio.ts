import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { DtoWfMacroProceso } from '../dominio/dto/DtoWfMacroProceso';
import { FiltroWfMacroProceso } from '../dominio/filtro/FiltroWfMacroProceso';

@Injectable()
export class WfMacroProcesoService {

    private url = `${this.config.getEnv('spring-framework-workflow-api')}spring/workflow/wfmacroproceso/`;
    constructor(private config: AppConfig) { }

    public obtenerDtoPorId(macroProcesoId : string, nombre : string, estado : string, creacionUsuario : string, creacionFecha : Date, modificacionUsuario : string, modificacionFecha : Date): Promise<DtoWfMacroProceso> {
        var dto = new DtoWfMacroProceso();
        dto.macroProcesoId = macroProcesoId;
        dto.nombre = nombre;
        dto.estado = estado;
        dto.creacionUsuario = creacionUsuario;
        dto.creacionFecha = creacionFecha;
        dto.modificacionUsuario = modificacionUsuario;
        dto.modificacionFecha = modificacionFecha;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoWfMacroProceso)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoWfMacroProceso): Promise<DtoWfMacroProceso> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoWfMacroProceso)
            .catch(error => null);
    }

    public registrar(dto: DtoWfMacroProceso): Promise<DtoWfMacroProceso> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoWfMacroProceso)
            .catch(error => null);
    }

    public actualizar(dto: DtoWfMacroProceso): Promise<DtoWfMacroProceso> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoWfMacroProceso)
            .catch(error => null);
    }

    public anular(dto: DtoWfMacroProceso): Promise<DtoWfMacroProceso> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoWfMacroProceso)
            .catch(error => null);
    }

    public eliminar(dto: DtoWfMacroProceso): Promise<DtoWfMacroProceso> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoWfMacroProceso)
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

    public listarDtoFiltros(macroProcesoId : string, nombre : string, estado : string, creacionUsuario : string, creacionFecha : Date, modificacionUsuario : string, modificacionFecha : Date): Promise<DtoWfMacroProceso[]> {
        var filtro = new DtoWfMacroProceso();
        filtro.macroProcesoId = macroProcesoId;
        filtro.nombre = nombre;
        filtro.estado = estado;
        filtro.creacionUsuario = creacionUsuario;
        filtro.creacionFecha = creacionFecha;
        filtro.modificacionUsuario = modificacionUsuario;
        filtro.modificacionFecha = modificacionFecha;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoWfMacroProceso[])
            .catch(error => []);
    }

    public listarDtoActivos(macroProcesoId : string, nombre : string, estado : string, creacionUsuario : string, creacionFecha : Date, modificacionUsuario : string, modificacionFecha : Date): Promise<DtoWfMacroProceso[]> {
        var filtro = new DtoWfMacroProceso();
        filtro.macroProcesoId = macroProcesoId;
        filtro.nombre = nombre;
        filtro.estado = estado;
        filtro.creacionUsuario = creacionUsuario;
        filtro.creacionFecha = creacionFecha;
        filtro.modificacionUsuario = modificacionUsuario;
        filtro.modificacionFecha = modificacionFecha;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoWfMacroProceso[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroWfMacroProceso): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

}
