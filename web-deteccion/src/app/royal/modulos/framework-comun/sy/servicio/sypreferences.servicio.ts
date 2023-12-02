import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { DtoSyPreferences } from '../dominio/dto/DtoSyPreferences';
import { FiltroSyPreferences } from '../dominio/filtro/FiltroSyPreferences';

@Injectable()
export class SyPreferencesService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/sistema/sypreferences/`;
    constructor(private config: AppConfig) { }

    public obtenerDtoPorId(usuario : string, preference : string, aplicacioncodigo : string, tipovalor : string, valorstring : string, valornumero : number, valorfecha : Date, ultimousuario : string, ultimafechamodif : Date): Promise<DtoSyPreferences> {
        var dto = new DtoSyPreferences();
        dto.usuario = usuario;
        dto.preference = preference;
        dto.aplicacioncodigo = aplicacioncodigo;
        dto.tipovalor = tipovalor;
        dto.valorstring = valorstring;
        dto.valornumero = valornumero;
        dto.valorfecha = valorfecha;
        dto.ultimousuario = ultimousuario;
        dto.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoSyPreferences)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoSyPreferences): Promise<DtoSyPreferences> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoSyPreferences)
            .catch(error => null);
    }

    public registrar(dto: DtoSyPreferences): Promise<DtoSyPreferences> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoSyPreferences)
            .catch(error => null);
    }

    public actualizar(dto: DtoSyPreferences): Promise<DtoSyPreferences> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoSyPreferences)
            .catch(error => null);
    }

    public anular(dto: DtoSyPreferences): Promise<DtoSyPreferences> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoSyPreferences)
            .catch(error => null);
    }

    public eliminar(dto: DtoSyPreferences): Promise<DtoSyPreferences> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoSyPreferences)
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

    public listarDtoFiltros(usuario : string, preference : string, aplicacioncodigo : string, tipovalor : string, valorstring : string, valornumero : number, valorfecha : Date, ultimousuario : string, ultimafechamodif : Date): Promise<DtoSyPreferences[]> {
        var filtro = new DtoSyPreferences();
        filtro.usuario = usuario;
        filtro.preference = preference;
        filtro.aplicacioncodigo = aplicacioncodigo;
        filtro.tipovalor = tipovalor;
        filtro.valorstring = valorstring;
        filtro.valornumero = valornumero;
        filtro.valorfecha = valorfecha;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoSyPreferences[])
            .catch(error => []);
    }

    public listarDtoActivos(usuario : string, preference : string, aplicacioncodigo : string, tipovalor : string, valorstring : string, valornumero : number, valorfecha : Date, ultimousuario : string, ultimafechamodif : Date): Promise<DtoSyPreferences[]> {
        var filtro = new DtoSyPreferences();
        filtro.usuario = usuario;
        filtro.preference = preference;
        filtro.aplicacioncodigo = aplicacioncodigo;
        filtro.tipovalor = tipovalor;
        filtro.valorstring = valorstring;
        filtro.valornumero = valornumero;
        filtro.valorfecha = valorfecha;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoSyPreferences[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroSyPreferences): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

}
