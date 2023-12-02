import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunPrTipoplanilla } from '../dominio/DtoComunPrTipoplanilla';

@Injectable()
export class PrTipoplanillaComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/planilla/prtipoplanillacomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //ARMAS MIGRADO
    //PR-TIPPLAN-CLISTA
    public listar(): Promise<DtoTabla[]> {        
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //ARMAS MIGRADO
    //PR-TIPPLAN-CLIACT
    public listarActivos(): Promise<DtoTabla[]> {        
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
 
    public obtenerTabla(tipoplanilla: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=tipoplanilla;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(tipoplanilla: string,descripcion: string,estado: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=tipoplanilla;
        filtro.nombre=descripcion;
        filtro.estadoId=estado;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerDto(tipoplanilla: string): Promise<DtoComunPrTipoplanilla> {
        var filtro = new DtoComunPrTipoplanilla();
        filtro.tipoplanilla=tipoplanilla;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunPrTipoplanilla)
            .catch(error => new DtoComunPrTipoplanilla());
    } 

    public listarDtoFiltros(tipoplanilla: string,descripcion:string,estado: string): Promise<DtoComunPrTipoplanilla[]> {
        var filtro = new DtoComunPrTipoplanilla();
        filtro.tipoplanilla=tipoplanilla;
        filtro.descripcion=descripcion;
        filtro.estado=estado;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunPrTipoplanilla[])
            .catch(error => []);
    }
}