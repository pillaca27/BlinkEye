import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { DtoSySeguridadgrupo } from '../dominio/dto/DtoSySeguridadgrupo';
import { FiltroSySeguridadgrupo } from '../dominio/filtro/FiltroSySeguridadgrupo';

@Injectable()
export class SySeguridadgrupoService {

    private url = `${this.config.getEnv('spring-erp-sg-api')}spring/sy/syseguridadgrupo/`;
    constructor(private config: AppConfig) { }

    public obtenerDtoPorId(aplicacioncodigo: string, grupo: string, descripcionlocal: string, descripcioningles: string, tipodetalle: string, ordenpresentacion: number, estado: string, ultimousuario: string, ultimafechamodif: Date): Promise<DtoSySeguridadgrupo> {
        var dto = new DtoSySeguridadgrupo();
        dto.aplicacioncodigo = aplicacioncodigo;
        dto.grupo = grupo;
        dto.descripcionlocal = descripcionlocal;
        dto.descripcioningles = descripcioningles;
        dto.tipodetalle = tipodetalle;
        dto.ordenpresentacion = ordenpresentacion;
        dto.estado = estado;
        dto.ultimousuario = ultimousuario;
        dto.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoSySeguridadgrupo)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoSySeguridadgrupo): Promise<DtoSySeguridadgrupo> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoSySeguridadgrupo)
            .catch(error => null);
    }

    public registrar(dto: DtoSySeguridadgrupo): Promise<DtoSySeguridadgrupo> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoSySeguridadgrupo)
            .catch(error => null);
    }

    public actualizar(dto: DtoSySeguridadgrupo): Promise<DtoSySeguridadgrupo> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoSySeguridadgrupo)
            .catch(error => null);
    }

    public anular(dto: DtoSySeguridadgrupo): Promise<DtoSySeguridadgrupo> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoSySeguridadgrupo)
            .catch(error => null);
    }

    public eliminar(dto: DtoSySeguridadgrupo): Promise<DtoSySeguridadgrupo> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoSySeguridadgrupo)
            .catch(error => null);
    }

    public listar(filtro: DtoSySeguridadgrupo): Promise<DtoTabla[]> {
        return this.config.getHttp().put(this.url + 'listar', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarseguridadgrupo(filtro: DtoSySeguridadgrupo): Promise<DtoTabla[]> {
        return this.config.getHttp().put(this.url + 'listarseguridadgrupo', filtro)
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

    public listarDtoFiltros(aplicacioncodigo: string, grupo: string, descripcionlocal: string, descripcioningles: string, tipodetalle: string, ordenpresentacion: number, estado: string, ultimousuario: string, ultimafechamodif: Date): Promise<DtoSySeguridadgrupo[]> {
        var filtro = new DtoSySeguridadgrupo();
        filtro.aplicacioncodigo = aplicacioncodigo;
        filtro.grupo = grupo;
        filtro.descripcionlocal = descripcionlocal;
        filtro.descripcioningles = descripcioningles;
        filtro.tipodetalle = tipodetalle;
        filtro.ordenpresentacion = ordenpresentacion;
        filtro.estado = estado;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoSySeguridadgrupo[])
            .catch(error => []);
    }

    public listarDtoActivos(aplicacioncodigo: string, grupo: string, descripcionlocal: string, descripcioningles: string, tipodetalle: string, ordenpresentacion: number, estado: string, ultimousuario: string, ultimafechamodif: Date): Promise<DtoSySeguridadgrupo[]> {
        var filtro = new DtoSySeguridadgrupo();
        filtro.aplicacioncodigo = aplicacioncodigo;
        filtro.grupo = grupo;
        filtro.descripcionlocal = descripcionlocal;
        filtro.descripcioningles = descripcioningles;
        filtro.tipodetalle = tipodetalle;
        filtro.ordenpresentacion = ordenpresentacion;
        filtro.estado = estado;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoSySeguridadgrupo[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroSySeguridadgrupo): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }
    public listarTipoDetalle(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarTipoDetalle')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

}
