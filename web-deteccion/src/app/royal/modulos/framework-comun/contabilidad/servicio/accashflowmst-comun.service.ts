import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunAcCashflowmst } from '../dominio/dto/DtoComunAcCashflowmst';

@Injectable()
export class AcCashflowmstComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/contabilidad/accashflowmstcomun/`;
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
    
    public obtenerTabla(cashflowcode: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=cashflowcode;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(cashflowcode: string,localname: string,status: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=cashflowcode;
        filtro.nombre=localname;
        filtro.estadoId=status;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerDto(cashflowcode: string): Promise<DtoComunAcCashflowmst> {
        var filtro = new DtoComunAcCashflowmst();
        filtro.cashflowcode=cashflowcode;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunAcCashflowmst)
            .catch(error => new DtoComunAcCashflowmst());
    } 

    public listarDtoFiltros(cashflowcode: string,localname: string,status: string): Promise<DtoComunAcCashflowmst[]> {
        var filtro = new DtoComunAcCashflowmst();
        filtro.cashflowcode=cashflowcode;
        filtro.localname=localname;
        filtro.status=status;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunAcCashflowmst[])
            .catch(error => []);
    }
    
}