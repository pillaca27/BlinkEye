import { DtoComunDwMaAccount } from './../dominio/dto/DtoComunDwMaAccount';
import { FiltroComunAccountmst } from './../dominio/filtro/FiltroComunAccountmst';
 
import { DtoComunUbigeo } from '../dominio/dto/DtoComunUbigeo';
import { FiltroComunUbigeo } from '../dominio/filtro/FiltroComunUbigeo';
import { DtoComunPlancontable } from '../dominio/dto/DtoComunPlancontable';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';

@Injectable()
export class PlancontableComunService {
    
    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/plancontablecomun/`;
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

    public obtenerTabla(pais: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=pais;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public obtenerDto(plancontable: string): Promise<DtoComunPlancontable> {
        var filtro = new DtoComunPlancontable();
        filtro.plancontable=plancontable;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunPlancontable)
            .catch(error => new DtoComunPlancontable());
    }

    public listarDtoFiltros(plancontable : string,descripcionlocal:string,estado:string): Promise<DtoComunPlancontable[]> {
        var filtro = new DtoComunPlancontable();
        filtro.plancontable=plancontable;
        filtro.descripcionlocal=descripcionlocal;
        filtro.estado=estado;
        return this.config.getHttp().put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunPlancontable[])
            .catch(error => []);
    }


}