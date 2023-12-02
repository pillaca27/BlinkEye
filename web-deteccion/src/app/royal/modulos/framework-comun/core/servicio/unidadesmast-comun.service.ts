import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunUnidadesmast } from '../dominio/dto/DtoComunUnidadesmast';

@Injectable()
export class UnidadesmastComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/unidadesmastcomun/`;
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
    
    public obtenerTabla(unidadcodigo: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=unidadcodigo;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(unidadcodigo: string,descripcioncorta: string,estado: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=unidadcodigo;
        filtro.nombre=descripcioncorta;
        filtro.estadoId=estado;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
 
    public obtenerDto(unidadcodigo: string): Promise<DtoComunUnidadesmast> {
        var filtro = new DtoComunUnidadesmast();
        filtro.unidadcodigo=unidadcodigo;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunUnidadesmast)
            .catch(error => new DtoComunUnidadesmast());
    } 

    public listarDtoFiltros(unidadcodigo: string,descripcioncorta: string,estado: string): Promise<DtoComunUnidadesmast[]> {
        var filtro = new DtoComunUnidadesmast();
        filtro.unidadcodigo=unidadcodigo;
        filtro.descripcioncorta=descripcioncorta;
        filtro.estado=estado;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunUnidadesmast[])
            .catch(error => []);
    }
    
}