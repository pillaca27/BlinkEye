import { DominioPaginacion } from '../../../../framework/modelo/generico/DominioPaginacion';

import { AppConfig } from '../../../../../../environments/appconfig';
import { Component, Inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { DtoComunHrPuestoempresa } from '../dominio/dto/DtoComunHrPuestoempresa';
import { FiltroComunHrPuestoempresa } from '../dominio/filtro/FiltroComunHrPuestoempresa';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { FiltroComunOrganigramaPuestos } from '../dominio/filtro/FiltroComunOrganigramaPuestos';



@Injectable()
export class HrPuestoempresaComunServicio {

    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/rrhh/hrpuestoempresacomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //LEONARDO
    //SELECTOR PUESTO
    public listarpuestoporfiltro(dto: FiltroComunHrPuestoempresa): Promise<DominioPaginacion> {
        return this.config.getHttp().post(this.url + 'listarpuestoporfiltro', dto)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listarPuestosOrganigrama(dto: FiltroComunOrganigramaPuestos): Promise<DominioPaginacion> {
        return this.config.getHttp().post(this.url + 'listarPuestosOrganigrama', dto)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listar(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listaractivos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerTabla(tipoTrabajador: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo = tipoTrabajador;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }

    //QQUECHOD VALIDADO
    //HR-PUESTO-COBTE
    public obtenerDto(codigopuesto: number): Promise<DtoComunHrPuestoempresa> {
        var filtro = new DtoComunHrPuestoempresa();
        filtro.codigopuesto = codigopuesto;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunHrPuestoempresa)
            .catch(error => new DtoComunHrPuestoempresa());
    }

    public validarUbicacionPuestoOrg(filtro: FiltroComunOrganigramaPuestos): Promise<any> {
        return this.http.put(this.url + 'validarUbicacionPuestoOrg', filtro)
            .toPromise()
            .then(response => response)
            .catch(error => null);
    }

    public listarDtoFiltros(codigopuesto: number, descripcion: string, estado: string): Promise<DtoComunHrPuestoempresa[]> {
        var filtro = new DtoComunHrPuestoempresa();
        filtro.codigopuesto = codigopuesto;
        filtro.descripcion = descripcion;
        filtro.estado = estado;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunHrPuestoempresa[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //HR-PUESTO-CLISTA
    public listarDto(): Promise<DtoComunHrPuestoempresa[]> {
        return this.config.getHttp().get(this.url + 'listardto')
            .toPromise()
            .then(response => response as DtoComunHrPuestoempresa[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //HR-PUESTO-CLIACT
    public listarDtoActivos(): Promise<DtoComunHrPuestoempresa[]> {
        return this.config.getHttp().get(this.url + 'listardtoactivos')
            .toPromise()
            .then(response => response as DtoComunHrPuestoempresa[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //HR-PUESTO-CPAGIN
    public listarPuestosPaginacion(filtro: FiltroComunHrPuestoempresa): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarpuestospaginacion', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }


}
