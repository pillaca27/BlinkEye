import { DtoSeguridadgrupo } from './../dominio/dto/DtoSeguridadgrupo';

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';

import { FiltroSeguridadgrupo } from './../dominio/filtro/FiltroSeguridadgrupo';

@Injectable()
export class SeguridadgrupoService {

    private url = `${this.config.getEnv('spring-erp-sg-api')}spring/sg/seguridadgrupo/`;
    constructor(private config: AppConfig) { }

    public obtenerDtoPorId(aplicacioncodigo: string, grupo: string, descripcion: string, ultimousuario: string, ultimafechamodif: Date, orden: number, webgrupo: string, webgruposecuencia: string, imagen: string, uuid: string, grupopadre: string): Promise<DtoSeguridadgrupo> {
        var dto = new DtoSeguridadgrupo();
        dto.aplicacioncodigo = aplicacioncodigo;
        dto.grupo = grupo;
        dto.descripcion = descripcion;
        dto.ultimousuario = ultimousuario;
        dto.ultimafechamodif = ultimafechamodif;
        dto.orden = orden;
        dto.webgrupo = webgrupo;
        dto.webgruposecuencia = webgruposecuencia;
        dto.imagen = imagen;
        dto.uuid = uuid;
        dto.grupopadre = grupopadre;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoSeguridadgrupo)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoSeguridadgrupo): Promise<DtoSeguridadgrupo> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoSeguridadgrupo)
            .catch(error => null);
    }

    public registrar(dto: DtoSeguridadgrupo): Promise<DtoSeguridadgrupo> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoSeguridadgrupo)
            .catch(error => null);
    }

    public actualizar(dto: DtoSeguridadgrupo): Promise<DtoSeguridadgrupo> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoSeguridadgrupo)
            .catch(error => null);
    }

    public anular(dto: DtoSeguridadgrupo): Promise<DtoSeguridadgrupo> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoSeguridadgrupo)
            .catch(error => null);
    }

    public activar(dto: DtoSeguridadgrupo): Promise<DtoSeguridadgrupo> {
        return this.config.getHttp().put(this.url + 'activar', dto)
            .toPromise()
            .then(response => response as DtoSeguridadgrupo)
            .catch(error => null);
    }

    public eliminar(dto: DtoSeguridadgrupo): Promise<DtoSeguridadgrupo> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoSeguridadgrupo)
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

    public listarDtoFiltros(filtro: DtoSeguridadgrupo): Promise<DtoSeguridadgrupo[]> {
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoSeguridadgrupo[])
            .catch(error => []);
    }

    public listarDtoActivos(aplicacioncodigo: string, grupo: string, descripcion: string, ultimousuario: string, ultimafechamodif: Date, orden: number, webgrupo: string, webgruposecuencia: string, imagen: string, uuid: string, grupopadre: string): Promise<DtoSeguridadgrupo[]> {
        var filtro = new DtoSeguridadgrupo();
        filtro.aplicacioncodigo = aplicacioncodigo;
        filtro.grupo = grupo;
        filtro.descripcion = descripcion;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        filtro.orden = orden;
        filtro.webgrupo = webgrupo;
        filtro.webgruposecuencia = webgruposecuencia;
        filtro.imagen = imagen;
        filtro.uuid = uuid;
        filtro.grupopadre = grupopadre;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoSeguridadgrupo[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroSeguridadgrupo): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listardtoactivosPorAplicacion(filtro: FiltroSeguridadgrupo): Promise<DtoSeguridadgrupo[]> {
        return this.config.getHttp().put(this.url + 'listardtoactivosPorAplicacion', filtro)
            .toPromise()
            .then(response => response as DtoSeguridadgrupo[])
            .catch(error => []);
    }

}
