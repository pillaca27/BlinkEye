import { DtoComunUbigeo } from './../dominio/dto/DtoComunUbigeo';
import { DtoComunZonapostal } from '../dominio/dto/DtoComunZonapostal';

import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';

@Injectable()
export class ZonapostalComunService {

    //dtoZonaPostal: DtoComunZonapostal = new DtoComunZonapostal();
    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/zonapostalcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public obtenerDto(departamento : string,provincia : string,codigopostal : string): Promise<DtoComunZonapostal> {
        var filtro = new DtoComunZonapostal();
        filtro.departamento=departamento;
        filtro.provincia=provincia;
        filtro.codigopostal=codigopostal;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunZonapostal)
            .catch(error => new DtoComunZonapostal());
    }

    public listarDtoFiltros(departamento:string,provincia:string,codigopostal:string,descripcioncorta:string,estado:string): Promise<DtoComunZonapostal[]> {
        var filtro = new DtoComunZonapostal();
        filtro.departamento=departamento;
        filtro.provincia=provincia;
        filtro.codigopostal=codigopostal;
        filtro.descripcioncorta=descripcioncorta;
        filtro.estado=estado;
        return this.config.getHttp().put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunZonapostal[])
            .catch(error => []);
    }

    public listarDtoPorProvincia(departamento:string,provincia:string,descripcioncorta:string): Promise<DtoComunZonapostal[]> {
        var filtro = new DtoComunZonapostal();
        filtro.departamento=departamento;
        filtro.provincia=provincia;
        filtro.descripcioncorta=descripcioncorta;
        return this.config.getHttp().put(this.url + 'listardtoporprovincia',filtro)
            .toPromise()
            .then(response => response as DtoComunZonapostal[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-ZONPOS-C0001
    public listarporprovinciapk(pais: string, departamento: string, provincia: string): Promise<DtoTabla[]> {        
        var dtoZonaPostal = new DtoComunZonapostal();
        dtoZonaPostal.pais = pais;
        dtoZonaPostal.departamento = departamento;
        dtoZonaPostal.provincia = provincia;
        return this.config.getHttp().put(this.url + 'listarporprovinciapk', dtoZonaPostal)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    
    //QQUECHOD VALIDADO
    //99-ZONPOS-C0002
    public obtenernombrezonapostal(pais: string, departamento: string, provincia: string, codigopostal: string): Promise<DtoTabla> {
        var dtoUbigeo = new DtoComunZonapostal();
        dtoUbigeo.pais = pais;   
        dtoUbigeo.departamento = departamento;     
        dtoUbigeo.provincia = provincia;
        dtoUbigeo.codigopostal = codigopostal;
        return this.config.getHttp().put(this.url + 'obtenernombrezonapostal', dtoUbigeo)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }

}