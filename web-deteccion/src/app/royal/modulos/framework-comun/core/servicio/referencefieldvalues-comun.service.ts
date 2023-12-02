import { DtoComunReferencefieldmst } from './../dominio/dto/DtoComunReferencefieldmst';
import { DtoComunReferencefieldvalues } from './../dominio/dto/DtoComunReferencefieldvalues';
import { DtoComunUbigeo } from './../dominio/dto/DtoComunUbigeo';
import { DtoComunProvincia } from '../dominio/dto/DtoComunProvincia';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';

@Injectable()
export class ReferencefieldvaluesComunService{

    //dtoProvincia: DtoComunProvincia = new DtoComunProvincia();
    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/referencefieldvaluescomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    
    
    public obtenerreferencefield(filtro:DtoComunReferencefieldvalues): Promise<DtoComunReferencefieldmst> {
        return this.config.getHttp().put(this.url + 'obtenerreferencefield',filtro)
            .toPromise()
            .then(response => response as DtoComunReferencefieldmst)
            .catch(error => new DtoComunReferencefieldmst());
    }

    public listardtofiltros(filtro:DtoComunReferencefieldvalues): Promise<DtoComunReferencefieldvalues[]> { 
        return this.config.getHttp().put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunReferencefieldvalues[])
            .catch(error => []);
    }

}