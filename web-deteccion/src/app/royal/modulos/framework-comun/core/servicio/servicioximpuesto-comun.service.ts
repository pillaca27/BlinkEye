import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunCompaniamast } from '../dominio/dto/DtoComunCompaniamast';
import { DtoComunServicioximpuesto } from '../dominio/dto/DtoComunServicioximpuesto';

@Injectable()
export class ServicioximpuestoComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/servicioximpuestocomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

 
    public obtenerDto(tiposervicio: string,impuesto: string): Promise<DtoComunServicioximpuesto> {
        var filtro = new DtoComunServicioximpuesto();
        filtro.tiposervicio=tiposervicio;
        filtro.impuesto=impuesto;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunServicioximpuesto)
            .catch(error => new DtoComunServicioximpuesto());
    } 

    public listarDtoFiltros(tiposervicio: string,impuesto: string): Promise<DtoComunServicioximpuesto[]> {
        var filtro = new DtoComunServicioximpuesto();
        filtro.tiposervicio=tiposervicio;
        filtro.impuesto=impuesto;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunServicioximpuesto[])
            .catch(error => []);
    }
    
    public listarDtoPorTipoServicio(tiposervicio: string): Promise<DtoComunServicioximpuesto[]> {
        var filtro = new DtoComunServicioximpuesto();
        filtro.tiposervicio=tiposervicio;
        return this.http.put(this.url + 'listardtoportiposervicio',filtro)
            .toPromise()
            .then(response => response as DtoComunServicioximpuesto[])
            .catch(error => []);
    }
    public listarDtoPorImpuesto(impuesto: string): Promise<DtoComunServicioximpuesto[]> {
        var filtro = new DtoComunServicioximpuesto();
        filtro.impuesto=impuesto;
        return this.http.put(this.url + 'listardtoporimpuesto',filtro)
            .toPromise()
            .then(response => response as DtoComunServicioximpuesto[])
            .catch(error => []);
    }
}