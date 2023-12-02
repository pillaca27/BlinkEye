import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunResponsiblemst } from '../dominio/dto/DtoComunResponsiblemst';

@Injectable()
export class ResponsiblemstComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/responsiblemstcomun/`;
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
    
    public obtenerTabla(responsible: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=responsible;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(responsible: string,localname: string,status: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=responsible;
        filtro.nombre=localname;
        filtro.estadoId=status;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerDto(responsible: string): Promise<DtoComunResponsiblemst> {
        var filtro = new DtoComunResponsiblemst();
        filtro.responsible=responsible;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunResponsiblemst)
            .catch(error => new DtoComunResponsiblemst());
    } 

    public listarDtoFiltros(responsible: string,localname: string,status: string): Promise<DtoComunResponsiblemst[]> {
        var filtro = new DtoComunResponsiblemst();
        filtro.responsible=responsible;
        filtro.localname=localname;
        filtro.status=status;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunResponsiblemst[])
            .catch(error => []);
    }
    
}