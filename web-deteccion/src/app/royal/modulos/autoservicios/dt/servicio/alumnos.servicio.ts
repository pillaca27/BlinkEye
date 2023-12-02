import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from 'src/environments/appconfig';
import { DominioPaginacion } from 'src/app/royal/framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from 'src/app/royal/framework/modelo/generico/dto/DtoTabla';
import { DtoAlumnos } from '../dominio/dto/DtoAlumnos';
import { FiltroAlumnos } from '../dominio/filtro/FiltroAlumnos';

@Injectable()
export class AlumnosService {

    private url = `${this.config.getEnv('spring-deteccion-api')}spring/publico/alumnos/`;
    constructor(private config: AppConfig) { }

    public obtenerDtoPorId(idAlumno : number, nombre : string, apellido : string, email : string): Promise<DtoAlumnos> {
        var dto = new DtoAlumnos();
        dto.idAlumno = idAlumno;
        dto.nombre = nombre;
        dto.apellido = apellido;
        dto.email = email;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoAlumnos)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoAlumnos): Promise<DtoAlumnos> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoAlumnos)
            .catch(error => null);
    }

    public registrar(dto: DtoAlumnos): Promise<DtoAlumnos> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoAlumnos)
            .catch(error => null);
    }

    public actualizar(dto: DtoAlumnos): Promise<DtoAlumnos> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoAlumnos)
            .catch(error => null);
    }

    public anular(dto: DtoAlumnos): Promise<DtoAlumnos> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoAlumnos)
            .catch(error => null);
    }

    public activar(dto: DtoAlumnos): Promise<DtoAlumnos> {
        return this.config.getHttp().put(this.url + 'activar', dto)
            .toPromise()
            .then(response => response as DtoAlumnos)
            .catch(error => null);
    }

    public eliminar(dto: DtoAlumnos): Promise<DtoAlumnos> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoAlumnos)
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

    public listarDtoFiltros(idAlumno : number, nombre : string, apellido : string, email : string): Promise<DtoAlumnos[]> {
        var filtro = new DtoAlumnos();
        filtro.idAlumno = idAlumno;
        filtro.nombre = nombre;
        filtro.apellido = apellido;
        filtro.email = email;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoAlumnos[])
            .catch(error => []);
    }

    public listarDtoActivos(idAlumno : number, nombre : string, apellido : string, email : string): Promise<DtoAlumnos[]> {
        var filtro = new DtoAlumnos();
        filtro.idAlumno = idAlumno;
        filtro.nombre = nombre;
        filtro.apellido = apellido;
        filtro.email = email;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoAlumnos[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroAlumnos): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

}
