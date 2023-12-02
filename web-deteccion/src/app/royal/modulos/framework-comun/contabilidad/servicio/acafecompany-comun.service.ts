import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunAcAfecompany } from '../dominio/dto/DtoComunAcAfecompany';

@Injectable()
export class AcAfecompanyComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/contabilidad/acafecompanycomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }
 
    public obtenerDto(afe: string,companyowner: string): Promise<DtoComunAcAfecompany> {
        var filtro = new DtoComunAcAfecompany();
        filtro.afe=afe;
        filtro.companyowner=companyowner;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunAcAfecompany)
            .catch(error => new DtoComunAcAfecompany());
    } 

    public listarDtoFiltros(afe: string,companyowner: string,status: string): Promise<DtoComunAcAfecompany[]> {
        var filtro = new DtoComunAcAfecompany();
        filtro.afe=afe;
        filtro.companyowner=companyowner;
        filtro.status=status;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunAcAfecompany[])
            .catch(error => []);
    }
    
}