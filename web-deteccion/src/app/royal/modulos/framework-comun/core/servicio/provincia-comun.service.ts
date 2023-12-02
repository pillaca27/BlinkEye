import { DtoComunUbigeo } from './../dominio/dto/DtoComunUbigeo';
import { DtoComunProvincia } from '../dominio/dto/DtoComunProvincia';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';

@Injectable()
export class ProvinciaComunService {

    //dtoProvincia: DtoComunProvincia = new DtoComunProvincia();
    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/provinciacomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public obtenerDto(departamento : string,provincia : string): Promise<DtoComunProvincia> {
        var filtro = new DtoComunProvincia();
        filtro.departamento=departamento;
        filtro.provincia=provincia;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunProvincia)
            .catch(error => new DtoComunProvincia());
    }

    public listarDtoFiltros(departamento:string,provincia:string,descripcioncorta:string,estado:string): Promise<DtoComunProvincia[]> {
        var filtro = new DtoComunProvincia();
        filtro.departamento=departamento;
        filtro.provincia=provincia;
        filtro.descripcioncorta=descripcioncorta;
        filtro.estado=estado;
        return this.config.getHttp().put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunProvincia[])
            .catch(error => []);
    }

    public listarDtoPorDepartamento(departamento:string,descripcioncorta:string): Promise<DtoComunProvincia[]> {
        var filtro = new DtoComunProvincia();
        filtro.departamento=departamento;
        filtro.descripcioncorta=descripcioncorta;
        return this.config.getHttp().put(this.url + 'listardtopordepartamento',filtro)
            .toPromise()
            .then(response => response as DtoComunProvincia[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-PROVINCIA-C0001
    public listarpordepartamentopk(pais: string, departamento: string): Promise<DtoTabla[]> {   
        var dtoProvincia = new DtoComunProvincia();    
        dtoProvincia.pais = pais;
        dtoProvincia.departamento = departamento;
        return this.config.getHttp().put(this.url + 'listarpordepartamentopk', dtoProvincia)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-PROVINCIA-C0002
    public obtenernombreprovincia(pais: string, departamento: string, provincia: string): Promise<DtoTabla> {
        var dtoUbigeo = new DtoComunProvincia();
        dtoUbigeo.pais = pais;   
        dtoUbigeo.departamento = departamento;     
        dtoUbigeo.provincia = provincia;
        return this.config.getHttp().put(this.url + 'obtenernombreprovincia', dtoUbigeo)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }

}