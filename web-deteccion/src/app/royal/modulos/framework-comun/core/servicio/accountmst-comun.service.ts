import { DtoComunDwMaAccount } from './../dominio/dto/DtoComunDwMaAccount';
import { FiltroComunAccountmst } from './../dominio/filtro/FiltroComunAccountmst';
 
import { DtoComunUbigeo } from '../dominio/dto/DtoComunUbigeo';
import { FiltroComunUbigeo } from '../dominio/filtro/FiltroComunUbigeo';
import { DtoComunAccountmst } from '../dominio/dto/DtoComunAccountmst';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';

@Injectable()
export class AccountMstComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/accountmstcomun/`;
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

    public obtenerTabla(cargo: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=cargo;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public obtenerDto(account: string): Promise<DtoComunAccountmst> {
        var filtro = new DtoComunAccountmst();
        filtro.account=account;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunAccountmst)
            .catch(error => new DtoComunAccountmst());
    }

    public listarDtoFiltros(account:string,localname:string,plancontable:string,status:string): Promise<DtoComunAccountmst[]> {
        var filtro = new DtoComunAccountmst();
        filtro.account=account;
        filtro.localname=localname;
        filtro.plancontable=plancontable;
        filtro.status=status;
        return this.config.getHttp().put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunAccountmst[])
            .catch(error => []);
    }

    public listarPaginado(filtro: FiltroComunAccountmst): Promise<DtoComunDwMaAccount[]> {                
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DtoComunDwMaAccount[])
            .catch(error => []);
    }

}