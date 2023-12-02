import { AppConfig } from './../../../../../environments/appconfig';
import { DtoTabla } from './../../../framework/modelo/generico/dto/DtoTabla';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class CompanyownerComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/companyowner/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //99-0001
    public listarporusuarioactual(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarporusuarioactual')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //99-0009
    public listaractivos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

}