import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunTipodocumentocxp } from '../dominio/dto/DtoComunTipodocumentocxp';

@Injectable()
export class TipodocumentocxpComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/tipodocumentocxpcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

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
    
    public obtenerTabla(tipodocumento: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=tipodocumento;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(tipodocumento: string,descripcion: string,estado: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=tipodocumento;
        filtro.nombre=descripcion;
        filtro.estadoId=estado;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerDto(tipodocumento: string): Promise<DtoComunTipodocumentocxp> {
        var filtro = new DtoComunTipodocumentocxp();
        filtro.tipodocumento=tipodocumento;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunTipodocumentocxp)
            .catch(error => new DtoComunTipodocumentocxp());
    } 

    public listarDtoFiltros(tipodocumento: string,descripcion: string,estado: string): Promise<DtoComunTipodocumentocxp[]> {
        var filtro = new DtoComunTipodocumentocxp();
        filtro.tipodocumento=tipodocumento;
        filtro.descripcion=descripcion;
        filtro.estado=estado;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunTipodocumentocxp[])
            .catch(error => []);
    }
    
    public listarDtoActivosClasificacionFeOb(): Promise<DtoComunTipodocumentocxp[]> {                
        return this.config.getHttp().get(this.url + 'listardtoactivosclasificacionfeob')
            .toPromise()
            .then(response => response as DtoComunTipodocumentocxp[])
            .catch(error => []);
    }
}