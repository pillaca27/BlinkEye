import { FiltroComunHrReqetapa } from './../dominio/filtro/FiltroComunHrReqetapa';
import { DtoComunHrReqetapa } from './../dominio/dto/DtoComunHrReqetapa';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
 

@Injectable()
export class HrReqetapaComunServicio {

    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/rrhh/hrreqetapa/`;
    constructor(private config: AppConfig) { }

    //LEONARDO
    //SELECTOR PUESTO
    public listaretapaevaluacionporfiltro(dto: FiltroComunHrReqetapa): Promise<DominioPaginacion> {        
        return this.config.getHttp().post(this.url + 'listaretapaevaluacionporfiltro', dto)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }


    public obtenerDtoPorId(companyowner : string, etapa : number, descripcion : string, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoComunHrReqetapa> {
        var dto = new DtoComunHrReqetapa();
        dto.companyowner = companyowner;
        dto.etapa = etapa;
        dto.descripcion = descripcion;
        dto.estado = estado;
        dto.ultimousuario = ultimousuario;
        dto.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunHrReqetapa)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunHrReqetapa): Promise<DtoComunHrReqetapa> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunHrReqetapa)
            .catch(error => null);
    }

    public registrar(dto: DtoComunHrReqetapa): Promise<DtoComunHrReqetapa> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunHrReqetapa)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunHrReqetapa): Promise<DtoComunHrReqetapa> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunHrReqetapa)
            .catch(error => null);
    }

    public anular(dto: DtoComunHrReqetapa): Promise<DtoComunHrReqetapa> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunHrReqetapa)
            .catch(error => null);
    }

    public activar(dto: DtoComunHrReqetapa): Promise<DtoComunHrReqetapa> {
        return this.config.getHttp().put(this.url + 'activar', dto)
            .toPromise()
            .then(response => response as DtoComunHrReqetapa)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunHrReqetapa): Promise<DtoComunHrReqetapa> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunHrReqetapa)
            .catch(error => null);
    }

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

    public listarDtoFiltros(companyowner : string, etapa : number, descripcion : string, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoComunHrReqetapa[]> {
        var filtro = new DtoComunHrReqetapa();
        filtro.companyowner = companyowner;
        filtro.etapa = etapa;
        filtro.descripcion = descripcion;
        filtro.estado = estado;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunHrReqetapa[])
            .catch(error => []);
    }

    public listarDtoActivos(companyowner : string, etapa : number, descripcion : string, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoComunHrReqetapa[]> {
        var filtro = new DtoComunHrReqetapa();
        filtro.companyowner = companyowner;
        filtro.etapa = etapa;
        filtro.descripcion = descripcion;
        filtro.estado = estado;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunHrReqetapa[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunHrReqetapa): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

}
