import { AppConfig } from '../../../../../../environments/appconfig';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { DtoComunHrProfesion } from '../dominio/dto/DtoComunHrProfesion';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FiltroComunHrProfesion } from '../dominio/filtro/FiltroComunHrProfesion';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';

@Injectable()
export class HrProfesionComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/rrhh/hrprofesioncomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //QQUECHOD VALIDADO
    //RH-PROFE-CLISTA
    public listar(): Promise<DtoTabla[]> {        
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //RH-PROFE-CLIACT
    public listaractivos(): Promise<DtoTabla[]> {        
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    
    public obtenerTabla(profesion: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=profesion;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public obtenerDto(profesion :string): Promise<DtoComunHrProfesion> {
        var filtro = new DtoComunHrProfesion();
        filtro.profesion=profesion;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunHrProfesion)
            .catch(error => new DtoComunHrProfesion());
    }

    public listarDtoFiltros(profesion : string,descripcion : string,estado : string): Promise<DtoComunHrProfesion[]> {        
        var filtro = new DtoComunHrProfesion();
        filtro.profesion=profesion;
        filtro.descripcion=descripcion;
        filtro.estado=estado;
        return this.config.getHttp().put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunHrProfesion[])
            .catch(error => []);
    }

    //FALTA
    public listarPaginacion(filtro: FiltroComunHrProfesion): Promise<DominioPaginacion> {

        return this.http.post(this.url + 'listarPaginacion', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    } 

}