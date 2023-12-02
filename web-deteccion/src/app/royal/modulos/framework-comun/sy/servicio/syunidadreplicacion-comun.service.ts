import { Observable } from 'rxjs';
 
import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';
 
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DtoComunSyUnidadreplicacion } from '../dominio/dto/DtoComunSyUnidadreplicacion';
import { FiltroComunSyUnidadreplicacion } from '../dominio/filtro/FiltroComunSyUnidadreplicacion';

@Injectable()
export class SyunidadreplicacionComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/sistema/syunidadreplicacioncomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }
 

    //99-UNIREPL-CLIACT
    public listarActivos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    

    public obtenerDtoPorId(unidadreplicacion : string, descripcionlocal : string, descripcionextranjera : string, rangopersonainicio : number, rangopersonafin : number, personaactual : number, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoComunSyUnidadreplicacion> {
        var dto = new DtoComunSyUnidadreplicacion();

        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunSyUnidadreplicacion)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunSyUnidadreplicacion): Promise<DtoComunSyUnidadreplicacion> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunSyUnidadreplicacion)
            .catch(error => null);
    }

    public registrar(dto: DtoComunSyUnidadreplicacion): Promise<DtoComunSyUnidadreplicacion> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunSyUnidadreplicacion)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunSyUnidadreplicacion): Promise<DtoComunSyUnidadreplicacion> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunSyUnidadreplicacion)
            .catch(error => null);
    }

    public anular(dto: DtoComunSyUnidadreplicacion): Promise<DtoComunSyUnidadreplicacion> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunSyUnidadreplicacion)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunSyUnidadreplicacion): Promise<DtoComunSyUnidadreplicacion> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunSyUnidadreplicacion)
            .catch(error => null);
    }

    public listar(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    /* public listarActivos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    } */

    public listarDtoFiltros(unidadreplicacion : string, descripcionlocal : string, descripcionextranjera : string, rangopersonainicio : number, rangopersonafin : number, personaactual : number, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoComunSyUnidadreplicacion[]> {
        var filtro = new DtoComunSyUnidadreplicacion();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunSyUnidadreplicacion[])
            .catch(error => []);
    }

    public listarDtoActivos(unidadreplicacion : string, descripcionlocal : string, descripcionextranjera : string, rangopersonainicio : number, rangopersonafin : number, personaactual : number, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoComunSyUnidadreplicacion[]> {
        var filtro = new DtoComunSyUnidadreplicacion();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunSyUnidadreplicacion[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunSyUnidadreplicacion): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public exportarUnidadReplicacion(filtro: FiltroComunSyUnidadreplicacion): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarUnidadReplicacion`, filtro,
            { responseType: 'blob' as 'json' });
    }
}