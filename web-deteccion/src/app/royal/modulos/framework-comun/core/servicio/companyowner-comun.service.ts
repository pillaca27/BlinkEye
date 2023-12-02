import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DtoComunCompanyowner } from '../dominio/dto/DtoComunCompanyowner';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { FiltroComunCompanyowner } from '../dominio/filtro/FiltroComunCompanyowner';

@Injectable()
export class CompanyownerComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/core/companyownercomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //LEONARDO
    //SELECTOR COMPANIA
    public listarcompanyownerporfiltro(dto: FiltroComunCompanyowner): Promise<DominioPaginacion> {        
        return this.config.getHttp().post(this.url + 'listarcompanyownerporfiltro', dto)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    //QQUECHOD VALIDADO
    //99-COMPW-CLISTA
    public listar(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-COMPW-CLIACT
    public listarActivos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerTabla(companyowner: string): Promise<DtoTabla> {        
        var filtro = new DtoTabla();
        filtro.codigo = companyowner;
        return this.config.getHttp().put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }

    public listarFiltros(companyowner: string,description: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=companyowner;
        filtro.nombre=description;
        return this.config.getHttp().put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerDto(companyowner: string): Promise<DtoComunCompanyowner> {
        var filtro = new DtoComunCompanyowner();
        filtro.companyowner=companyowner;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunCompanyowner)
            .catch(error => new DtoComunCompanyowner());
    }

    public listarDtoFiltros(companyowner:string,description:string): Promise<DtoComunCompanyowner[]> {
        var filtro = new DtoComunCompanyowner();
        filtro.companyowner=companyowner;
        filtro.description=description;
        return this.config.getHttp().put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunCompanyowner[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-COMPW-C0001
    public listarporusuarioactual(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarporusuarioactual')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarActivosP(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaractivosP')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

}