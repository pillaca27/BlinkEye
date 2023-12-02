import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { DtoComunSyAplicacionreportetopico } from '../dominio/dto/DtoComunSyAplicacionreportetopico';
import { FiltroComunSyAplicacionreportetopico } from '../dominio/filtro/FiltroComunSyAplicacionreportetopico';
 

@Injectable()
export class SyAplicacionreportetopicoComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/sy/syaplicacionreportetopico/`;
    constructor(private config: AppConfig) { }

    public obtenerDtoPorId(aplicacioncodigo : string, topico : string, descripcionlocal : string, descripcioningles : string, estado : string, ultimousuario : string, ultimafechamodif : Date, uuid : string): Promise<DtoComunSyAplicacionreportetopico> {
        var dto = new DtoComunSyAplicacionreportetopico();
        dto.aplicacioncodigo = aplicacioncodigo;
        dto.topico = topico;
        dto.descripcionlocal = descripcionlocal;
        dto.descripcioningles = descripcioningles;
        dto.estado = estado;
        dto.ultimousuario = ultimousuario;
        dto.ultimafechamodif = ultimafechamodif;
        dto.uuid = uuid;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunSyAplicacionreportetopico)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunSyAplicacionreportetopico): Promise<DtoComunSyAplicacionreportetopico> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunSyAplicacionreportetopico)
            .catch(error => null);
    }

    public registrar(dto: DtoComunSyAplicacionreportetopico): Promise<DtoComunSyAplicacionreportetopico> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunSyAplicacionreportetopico)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunSyAplicacionreportetopico): Promise<DtoComunSyAplicacionreportetopico> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunSyAplicacionreportetopico)
            .catch(error => null);
    }

    public anular(dto: DtoComunSyAplicacionreportetopico): Promise<DtoComunSyAplicacionreportetopico> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunSyAplicacionreportetopico)
            .catch(error => null);
    }

    public activar(dto: DtoComunSyAplicacionreportetopico): Promise<DtoComunSyAplicacionreportetopico> {
        return this.config.getHttp().put(this.url + 'activar', dto)
            .toPromise()
            .then(response => response as DtoComunSyAplicacionreportetopico)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunSyAplicacionreportetopico): Promise<DtoComunSyAplicacionreportetopico> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunSyAplicacionreportetopico)
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

    public listarDtoFiltros(aplicacioncodigo : string, topico : string, descripcionlocal : string, descripcioningles : string, estado : string, ultimousuario : string, ultimafechamodif : Date, uuid : string): Promise<DtoComunSyAplicacionreportetopico[]> {
        var filtro = new DtoComunSyAplicacionreportetopico();
        filtro.aplicacioncodigo = aplicacioncodigo;
        filtro.topico = topico;
        filtro.descripcionlocal = descripcionlocal;
        filtro.descripcioningles = descripcioningles;
        filtro.estado = estado;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        filtro.uuid = uuid;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunSyAplicacionreportetopico[])
            .catch(error => []);
    }

    public listarDtoActivos(aplicacioncodigo : string, topico : string, descripcionlocal : string, descripcioningles : string, estado : string, ultimousuario : string, ultimafechamodif : Date, uuid : string): Promise<DtoComunSyAplicacionreportetopico[]> {
        var filtro = new DtoComunSyAplicacionreportetopico();
        filtro.aplicacioncodigo = aplicacioncodigo;
        filtro.topico = topico;
        filtro.descripcionlocal = descripcionlocal;
        filtro.descripcioningles = descripcioningles;
        filtro.estado = estado;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        filtro.uuid = uuid;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunSyAplicacionreportetopico[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunSyAplicacionreportetopico): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

}
