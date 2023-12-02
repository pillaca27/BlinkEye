import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from 'src/environments/appconfig';
import { DtoDocentes } from '../dominio/dto/DtoDocentes';
import { FiltroDocentes } from '../dominio/filtro/FiltroDocentes';
import { DominioPaginacion } from 'src/app/royal/framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from 'src/app/royal/framework/modelo/generico/dto/DtoTabla';
import { DtoClases } from '../dominio/dto/DtoClases';
import { FiltroClases } from '../dominio/filtro/FiltroClases';

@Injectable()
export class ClasesService {

    private url = `${this.config.getEnv('spring-deteccion-api')}spring/publico/clases/`;
    constructor(private config: AppConfig) { }

    public obtenerDtoPorId(idClase : number, nombre : string, fechaHoraInicio : Date, fechaHoraFin : Date, idDocente : number): Promise<DtoClases> {
        var dto = new DtoClases();
        dto.idClase = idClase;
        dto.nombre = nombre;
        dto.fechaHoraInicio = fechaHoraInicio;
        dto.fechaHoraFin = fechaHoraFin;
        dto.idDocente = idDocente;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoClases)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoClases): Promise<DtoClases> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoClases)
            .catch(error => null);
    }

    public registrar(dto: DtoClases): Promise<DtoClases> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoClases)
            .catch(error => null);
    }

    public actualizar(dto: DtoClases): Promise<DtoClases> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoClases)
            .catch(error => null);
    }

    public anular(dto: DtoClases): Promise<DtoClases> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoClases)
            .catch(error => null);
    }

    public activar(dto: DtoClases): Promise<DtoClases> {
        return this.config.getHttp().put(this.url + 'activar', dto)
            .toPromise()
            .then(response => response as DtoClases)
            .catch(error => null);
    }

    public eliminar(dto: DtoClases): Promise<DtoClases> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoClases)
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

    public listarDtoFiltros(idClase : number, nombre : string, fechaHoraInicio : Date, fechaHoraFin : Date, idDocente : number): Promise<DtoClases[]> {
        var filtro = new DtoClases();
        filtro.idClase = idClase;
        filtro.nombre = nombre;
        filtro.fechaHoraInicio = fechaHoraInicio;
        filtro.fechaHoraFin = fechaHoraFin;
        filtro.idDocente = idDocente;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoClases[])
            .catch(error => []);
    }

    public listarDtoActivos(idClase : number, nombre : string, fechaHoraInicio : Date, fechaHoraFin : Date, idDocente : number): Promise<DtoClases[]> {
        var filtro = new DtoClases();
        filtro.idClase = idClase;
        filtro.nombre = nombre;
        filtro.fechaHoraInicio = fechaHoraInicio;
        filtro.fechaHoraFin = fechaHoraFin;
        filtro.idDocente = idDocente;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoClases[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroClases): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

}
