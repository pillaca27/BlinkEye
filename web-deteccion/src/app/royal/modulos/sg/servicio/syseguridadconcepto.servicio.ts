import { DtoSeguridadConceptoList } from './../dominio/dto/DtoSeguridadConceptoList';
import { FiltroSySeguridadconcepto } from './../dominio/filtro/FiltroSySeguridadconcepto';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { DtoSySeguridadconcepto } from '../dominio/dto/DtoSySeguridadconcepto';

@Injectable()
export class SySeguridadconceptoService {

    private url = `${this.config.getEnv('spring-erp-sg-api')}spring/sy/syseguridadconcepto/`;
    constructor(private config: AppConfig) { }

    public obtenerDtoPorId(aplicacioncodigo : string, grupo : string, concepto : string, ordenpresentacion : number, descripcionlocal : string, descripcioningles : string, codigointerno : string, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoSySeguridadconcepto> {
        var dto = new DtoSySeguridadconcepto();
        dto.aplicacioncodigo = aplicacioncodigo;
        dto.grupo = grupo;
        dto.concepto = concepto;
        dto.ordenpresentacion = ordenpresentacion;
        dto.descripcionlocal = descripcionlocal;
        dto.descripcioningles = descripcioningles;
        dto.codigointerno = codigointerno;
        dto.estado = estado;
        dto.ultimousuario = ultimousuario;
        dto.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoSySeguridadconcepto)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoSySeguridadconcepto): Promise<DtoSySeguridadconcepto> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoSySeguridadconcepto)
            .catch(error => null);
    }

    public registrar(dto: DtoSySeguridadconcepto): Promise<DtoSySeguridadconcepto> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoSySeguridadconcepto)
            .catch(error => null);
    }

    public actualizar(dto: DtoSySeguridadconcepto): Promise<DtoSySeguridadconcepto> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoSySeguridadconcepto)
            .catch(error => null);
    }

    public anular(dto: DtoSySeguridadconcepto): Promise<DtoSySeguridadconcepto> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoSySeguridadconcepto)
            .catch(error => null);
    }

    public eliminar(dto: DtoSySeguridadconcepto): Promise<DtoSySeguridadconcepto> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoSySeguridadconcepto)
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

    public listarDtoFiltros(aplicacioncodigo : string, grupo : string, concepto : string, ordenpresentacion : number, descripcionlocal : string, descripcioningles : string, codigointerno : string, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoSySeguridadconcepto[]> {
        var filtro = new DtoSySeguridadconcepto();
        filtro.aplicacioncodigo = aplicacioncodigo;
        filtro.grupo = grupo;
        filtro.concepto = concepto;
        filtro.ordenpresentacion = ordenpresentacion;
        filtro.descripcionlocal = descripcionlocal;
        filtro.descripcioningles = descripcioningles;
        filtro.codigointerno = codigointerno;
        filtro.estado = estado;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoSySeguridadconcepto[])
            .catch(error => []);
    }

    public listarDtoActivos(aplicacioncodigo : string, grupo : string, concepto : string, ordenpresentacion : number, descripcionlocal : string, descripcioningles : string, codigointerno : string, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoSySeguridadconcepto[]> {
        var filtro = new DtoSySeguridadconcepto();
        filtro.aplicacioncodigo = aplicacioncodigo;
        filtro.grupo = grupo;
        filtro.concepto = concepto;
        filtro.ordenpresentacion = ordenpresentacion;
        filtro.descripcionlocal = descripcionlocal;
        filtro.descripcioningles = descripcioningles;
        filtro.codigointerno = codigointerno;
        filtro.estado = estado;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoSySeguridadconcepto[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroSySeguridadconcepto): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }


    public listarPaginadoSeguridadConcepto(filtro: FiltroSySeguridadconcepto): Promise<DtoSySeguridadconcepto[]> {        
        return this.config.getHttp().put(this.url + 'listarPaginadoSeguridadConcepto', filtro)
            .toPromise()
            .then(response => response as DtoSySeguridadconcepto[])
            .catch(error => []);
    }


    public actualizarSeguridadConcepto(filtro: DtoSeguridadConceptoList): Promise<DtoSeguridadConceptoList> {
        return this.config.getHttp().put(this.url + 'actualizarSeguridadConcepto', filtro)
            .toPromise()
            .then(response => response as DtoSeguridadConceptoList)
            .catch(error => null);
    }


}
