import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from 'src/environments/appconfig';
import { DtoDocentes } from '../dominio/dto/DtoDocentes';
import { FiltroDocentes } from '../dominio/filtro/FiltroDocentes';
import { DominioPaginacion } from 'src/app/royal/framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from 'src/app/royal/framework/modelo/generico/dto/DtoTabla';

@Injectable()
export class DocentesService {

    private url = `${this.config.getEnv('spring-deteccion-api')}spring/dt/docentes/`;
    constructor(private config: AppConfig) { }

    public obtenerDtoPorId(idDocente : number, nombre : string, apellido : string, email : string): Promise<DtoDocentes> {
        var dto = new DtoDocentes();
        dto.idDocente = idDocente;
        dto.nombre = nombre;
        dto.apellido = apellido;
        dto.email = email;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoDocentes)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoDocentes): Promise<DtoDocentes> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoDocentes)
            .catch(error => null);
    }

    public registrar(dto: DtoDocentes): Promise<DtoDocentes> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoDocentes)
            .catch(error => null);
    }

    public actualizar(dto: DtoDocentes): Promise<DtoDocentes> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoDocentes)
            .catch(error => null);
    }

    public anular(dto: DtoDocentes): Promise<DtoDocentes> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoDocentes)
            .catch(error => null);
    }

    public activar(dto: DtoDocentes): Promise<DtoDocentes> {
        return this.config.getHttp().put(this.url + 'activar', dto)
            .toPromise()
            .then(response => response as DtoDocentes)
            .catch(error => null);
    }

    public eliminar(dto: DtoDocentes): Promise<DtoDocentes> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoDocentes)
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

    public listarDtoFiltros(idDocente : number, nombre : string, apellido : string, email : string): Promise<DtoDocentes[]> {
        var filtro = new DtoDocentes();
        filtro.idDocente = idDocente;
        filtro.nombre = nombre;
        filtro.apellido = apellido;
        filtro.email = email;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoDocentes[])
            .catch(error => []);
    }

    public listarDtoActivos(idDocente : number, nombre : string, apellido : string, email : string): Promise<DtoDocentes[]> {
        var filtro = new DtoDocentes();
        filtro.idDocente = idDocente;
        filtro.nombre = nombre;
        filtro.apellido = apellido;
        filtro.email = email;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoDocentes[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroDocentes): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

}
