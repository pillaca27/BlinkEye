import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunAcCostcenterdestination } from '../dominio/dto/DtoComunAcCostcenterdestination';

@Injectable()
export class AcCostcenterdestinationComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/contabilidad/accostcenterdestinationcomun/`;
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
    
    public obtenerTabla(costcenterdestination: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=costcenterdestination;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(costcenterdestination: string,localname: string,status: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=costcenterdestination;
        filtro.nombre=localname;
        filtro.estadoId=status;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerDto(costcenterdestination: string): Promise<DtoComunAcCostcenterdestination> {
        var filtro = new DtoComunAcCostcenterdestination();
        filtro.costcenterdestination=costcenterdestination;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunAcCostcenterdestination)
            .catch(error => new DtoComunAcCostcenterdestination());
    } 

    public listarDtoFiltros(costcenterdestination: string,localname: string,status: string): Promise<DtoComunAcCostcenterdestination[]> {
        var filtro = new DtoComunAcCostcenterdestination();
        filtro.costcenterdestination=costcenterdestination;
        filtro.localname=localname;
        filtro.status=status;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunAcCostcenterdestination[])
            .catch(error => []);
    }
    
}