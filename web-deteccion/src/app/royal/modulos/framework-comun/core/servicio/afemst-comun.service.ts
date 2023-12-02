import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';
import { FiltroComunAfemst } from './../dominio/filtro/FiltroComunAfemst';
import { DtoTabla } from './../../../../framework/modelo/generico/dto/DtoTabla';
import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DtoComunAfemst } from '../dominio/dto/DtoComunAfemst';


@Injectable()
export class AfemstComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/afemstcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }


    //LEONARDO
    //SELECTOR COMUN PROYECTO (afemst)
    public listarproyectoporfiltro(dto: FiltroComunAfemst): Promise<DominioPaginacion> {        
        return this.config.getHttp().post(this.url + 'listarproyectoporfiltro', dto)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
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

    public obtenerTabla(cargo: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=cargo;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public obtenerDto(afe: string): Promise<DtoComunAfemst> {
        var filtro = new DtoComunAfemst();
        filtro.afe=afe;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunAfemst)
            .catch(error => new DtoComunAfemst());
    }

    public listarDtoFiltros(afe:string,companyowner:string,localname:string,status:string): Promise<DtoComunAfemst[]> {
        var filtro = new DtoComunAfemst();
        filtro.afe=afe;
        filtro.companyowner=companyowner;
        filtro.localname=localname;
        filtro.status=status;
        return this.config.getHttp().put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunAfemst[])
            .catch(error => []);
    }

    listarPaginado(filtro: FiltroComunAfemst): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion);
    }  
    
}










