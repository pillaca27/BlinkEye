import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunAcCostcenterdestvalid } from '../dominio/dto/DtoComunAcCostcenterdestvalid';

@Injectable()
export class AcCostcenterdestvalidComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/contabilidad/accostcenterdestvalidcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }
 
    public obtenerDto(costcenter: string,costcenterdestination: string): Promise<DtoComunAcCostcenterdestvalid> {
        var filtro = new DtoComunAcCostcenterdestvalid();
        filtro.costcenter=costcenter;
        filtro.costcenterdestination=costcenterdestination;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunAcCostcenterdestvalid)
            .catch(error => new DtoComunAcCostcenterdestvalid());
    } 

    public listarDtoFiltros(costcenter: string,costcenterdestination: string,status: string): Promise<DtoComunAcCostcenterdestvalid[]> {
        var filtro = new DtoComunAcCostcenterdestvalid();
        filtro.costcenter=costcenter;
        filtro.costcenterdestination=costcenterdestination;
        filtro.status=status;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunAcCostcenterdestvalid[])
            .catch(error => []);
    }
    
}