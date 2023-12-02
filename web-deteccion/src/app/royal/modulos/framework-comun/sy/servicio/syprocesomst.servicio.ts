 
 
import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from './../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from './../../../../../../environments/appconfig';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
 
import { DtoSyProcesomst } from '../dominio/dto/DtoSyProcesomst';
import { FiltroSyProcesomst } from '../dominio/filtro/FiltroSyProcesomst';
import { FiltroComunAplicacionesmast } from '@framework-comun/sg/dominio/filtro/FiltroComunAplicacionesmast';

@Injectable()
export class SyProcesomstService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/sistema/syprocesomst/`;
    constructor(private config: AppConfig) { }

    public obtenerDtoPorId(aplicacioncodigo : string, procesocodigo : string, descripcion : string, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoSyProcesomst> {
        var dto = new DtoSyProcesomst();
        dto.aplicacioncodigo = aplicacioncodigo;
        dto.procesocodigo = procesocodigo;
        dto.descripcion = descripcion;
        dto.estado = estado;
        dto.ultimousuario = ultimousuario;
        dto.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoSyProcesomst)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoSyProcesomst): Promise<DtoSyProcesomst> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoSyProcesomst)
            .catch(error => null);
    }

    public registrar(dto: DtoSyProcesomst): Promise<DtoSyProcesomst> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoSyProcesomst)
            .catch(error => null);
    }

    public actualizar(dto: DtoSyProcesomst): Promise<DtoSyProcesomst> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoSyProcesomst)
            .catch(error => null);
    }

    public anular(dto: DtoSyProcesomst): Promise<DtoSyProcesomst> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoSyProcesomst)
            .catch(error => null);
    }

    public eliminar(dto: DtoSyProcesomst): Promise<DtoSyProcesomst> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoSyProcesomst)
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

    public listarDtoFiltros(aplicacioncodigo : string, procesocodigo : string, descripcion : string, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoSyProcesomst[]> {
        var filtro = new DtoSyProcesomst();
        filtro.aplicacioncodigo = aplicacioncodigo;
        filtro.procesocodigo = procesocodigo;
        filtro.descripcion = descripcion;
        filtro.estado = estado;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoSyProcesomst[])
            .catch(error => []);
    }

    public listarDtoActivos(aplicacioncodigo : string, procesocodigo : string, descripcion : string, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoSyProcesomst[]> {
        var filtro = new DtoSyProcesomst();
        filtro.aplicacioncodigo = aplicacioncodigo;
        filtro.procesocodigo = procesocodigo;
        filtro.descripcion = descripcion;
        filtro.estado = estado;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoSyProcesomst[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroSyProcesomst): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listaraplicacionesmastporfiltro(filtro: FiltroComunAplicacionesmast): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listaraplicacionesmastporfiltro', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }
    
}
