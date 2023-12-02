import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunAcCashflowmajor } from '../dominio/dto/DtoComunAcCashflowmajor';

@Injectable()
export class AcCashflowmajorComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/contabilidad/accashflowmajorcomun/`;
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
    
    public obtenerTabla(cashflowmajor: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=cashflowmajor;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(cashflowmajor: string,localname: string,status: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=cashflowmajor;
        filtro.nombre=localname;
        filtro.estadoId=status;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerDto(cashflowmajor: string): Promise<DtoComunAcCashflowmajor> {
        var filtro = new DtoComunAcCashflowmajor();
        filtro.cashflowmajor=cashflowmajor;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunAcCashflowmajor)
            .catch(error => new DtoComunAcCashflowmajor());
    } 

    public listarDtoFiltros(cashflowmajor: string,localname: string,status: string): Promise<DtoComunAcCashflowmajor[]> {
        var filtro = new DtoComunAcCashflowmajor();
        filtro.cashflowmajor=cashflowmajor;
        filtro.localname=localname;
        filtro.status=status;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunAcCashflowmajor[])
            .catch(error => []);
    }
    
}