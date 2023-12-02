import { FiltroComunAcCashflow } from './../dominio/filtro/FiltroComunAcCashflow';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunAcCashflowmst } from '../dominio/dto/DtoComunAcCashflowmst';

@Injectable()
export class AcCashflowComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/contabilidad/accashflowcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }
     

    public listarpaginado(filtro: FiltroComunAcCashflow): Promise<DominioPaginacion> {                
        return this.http.put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    } 

    
}