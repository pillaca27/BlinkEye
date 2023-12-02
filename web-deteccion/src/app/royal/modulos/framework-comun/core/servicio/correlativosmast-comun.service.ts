import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { Observable } from 'rxjs';
import { DtoComunCorrelativosmast } from '../dominio/dto/DtoComunCorrelativosmast';
import { FiltroComunCorrelativosmast } from '../dominio/filtro/FiltroComunCorrelativosmast';
 

@Injectable()
export class CorrelativosmastComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/correlativosmastcomun/`;
    constructor(private config: AppConfig) { }

    public obtenerDtoPorId(companiacodigo : string, tipocomprobante : string, serie : string, descripcion : string, correlativonumero : number, correlativodesde : number, correlativohasta : number, almacencodigo : string, estado : string, ultimousuario : string, ultimafechamodif : Date, feflag : string, fetipocomprobanteref : string, feenvio : string): Promise<DtoComunCorrelativosmast> {
        var dto = new DtoComunCorrelativosmast();

        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunCorrelativosmast)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunCorrelativosmast): Promise<DtoComunCorrelativosmast> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunCorrelativosmast)
            .catch(error => null);
    }

    public registrar(dto: DtoComunCorrelativosmast): Promise<DtoComunCorrelativosmast> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunCorrelativosmast)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunCorrelativosmast): Promise<DtoComunCorrelativosmast> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunCorrelativosmast)
            .catch(error => null);
    }

    public anular(dto: DtoComunCorrelativosmast): Promise<DtoComunCorrelativosmast> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunCorrelativosmast)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunCorrelativosmast): Promise<DtoComunCorrelativosmast> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunCorrelativosmast)
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

    public listarDtoFiltros(companiacodigo : string, tipocomprobante : string, serie : string, descripcion : string, correlativonumero : number, correlativodesde : number, correlativohasta : number, almacencodigo : string, estado : string, ultimousuario : string, ultimafechamodif : Date, feflag : string, fetipocomprobanteref : string, feenvio : string): Promise<DtoComunCorrelativosmast[]> {
        var filtro = new DtoComunCorrelativosmast();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunCorrelativosmast[])
            .catch(error => []);
    }

    public listarDtoActivos(companiacodigo : string, tipocomprobante : string, serie : string, descripcion : string, correlativonumero : number, correlativodesde : number, correlativohasta : number, almacencodigo : string, estado : string, ultimousuario : string, ultimafechamodif : Date, feflag : string, fetipocomprobanteref : string, feenvio : string): Promise<DtoComunCorrelativosmast[]> {
        var filtro = new DtoComunCorrelativosmast();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunCorrelativosmast[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunCorrelativosmast): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listarTipos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarTipos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    public exportarCorrelativos(filtro: FiltroComunCorrelativosmast): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarCorrelativos`, filtro,
            { responseType: 'blob' as 'json' });
    }
}
