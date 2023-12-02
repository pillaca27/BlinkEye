import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunAcCostcenteraccount } from '../dominio/dto/DtoComunAcCostcenteraccount';

@Injectable()
export class AcCostcenteraccountComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/contabilidad/accostcenteraccountcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }
 
    public obtenerDto(costcenter: string,account: string): Promise<DtoComunAcCostcenteraccount> {
        var filtro = new DtoComunAcCostcenteraccount();
        filtro.costcenter=costcenter;
        filtro.account=account;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunAcCostcenteraccount)
            .catch(error => new DtoComunAcCostcenteraccount());
    } 

    public listarDtoFiltros(costcenter: string,account: string,status: string): Promise<DtoComunAcCostcenteraccount[]> {
        var filtro = new DtoComunAcCostcenteraccount();
        filtro.costcenter=costcenter;
        filtro.account=account;
        filtro.status=status;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunAcCostcenteraccount[])
            .catch(error => []);
    }
    
}