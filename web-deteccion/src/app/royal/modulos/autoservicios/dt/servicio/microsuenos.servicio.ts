import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DominioPaginacion } from 'src/app/royal/framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from 'src/app/royal/framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from 'src/environments/appconfig';
import { DtoMicrosuenos } from '../dominio/dto/DtoMicrosuenos';
import { FiltroMicrosuenos } from '../dominio/filtro/FiltroMicrosuenos';
import { map } from 'rxjs/operators';

@Injectable()
export class MicrosuenosService {

    private url = `${this.config.getEnv('spring-deteccion-api')}spring/dt/microsuenos/`;
    constructor(private config: AppConfig) { }

    public obtenerDeteccion(filtro: FiltroMicrosuenos): any {
        return this.config.getHttp().post(`${this.url}obtenerDeteccion`, filtro, { responseType: 'blob' })
            .pipe(
                map((result: any) => {
                    return result;
                })
            );
    }

    // public obtenerDtoPorId(idMicrosueno : number, idAlumno : number, idClase : number, fechaHora : Date, duracion : number): Promise<DtoMicrosuenos> {
    //     var dto = new DtoMicrosuenos();
    //     dto.idMicrosueno = idMicrosueno;
    //     dto.idAlumno = idAlumno;
    //     dto.idClase = idClase;
    //     dto.fechaHora = fechaHora;
    //     dto.duracion = duracion;
    //     return this.config.getHttp().put(this.url + 'obtenerdto', dto)
    //         .toPromise()
    //         .then(response => response as DtoMicrosuenos)
    //         .catch(error => null);
    // }

    // public obtenerDto(dto: DtoMicrosuenos): Promise<DtoMicrosuenos> {
    //     return this.config.getHttp().put(this.url + 'obtenerdto', dto)
    //         .toPromise()
    //         .then(response => response as DtoMicrosuenos)
    //         .catch(error => null);
    // }

    // public registrar(dto: DtoMicrosuenos): Promise<DtoMicrosuenos> {
    //     return this.config.getHttp().post(this.url + 'registrar', dto)
    //         .toPromise()
    //         .then(response => response as DtoMicrosuenos)
    //         .catch(error => null);
    // }

    // public actualizar(dto: DtoMicrosuenos): Promise<DtoMicrosuenos> {
    //     return this.config.getHttp().put(this.url + 'actualizar', dto)
    //         .toPromise()
    //         .then(response => response as DtoMicrosuenos)
    //         .catch(error => null);
    // }

    // public anular(dto: DtoMicrosuenos): Promise<DtoMicrosuenos> {
    //     return this.config.getHttp().put(this.url + 'anular', dto)
    //         .toPromise()
    //         .then(response => response as DtoMicrosuenos)
    //         .catch(error => null);
    // }

    // public activar(dto: DtoMicrosuenos): Promise<DtoMicrosuenos> {
    //     return this.config.getHttp().put(this.url + 'activar', dto)
    //         .toPromise()
    //         .then(response => response as DtoMicrosuenos)
    //         .catch(error => null);
    // }

    // public eliminar(dto: DtoMicrosuenos): Promise<DtoMicrosuenos> {
    //     return this.config.getHttp().put(this.url + 'eliminar', dto)
    //         .toPromise()
    //         .then(response => response as DtoMicrosuenos)
    //         .catch(error => null);
    // }

    // public listar(): Promise<DtoTabla[]> {
    //     return this.config.getHttp().get(this.url + 'listar')
    //         .toPromise()
    //         .then(response => response as DtoTabla[])
    //         .catch(error => []);
    // }

    // public listarActivos(): Promise<DtoTabla[]> {
    //     return this.config.getHttp().get(this.url + 'listaractivos')
    //         .toPromise()
    //         .then(response => response as DtoTabla[])
    //         .catch(error => []);
    // }

    // public listarDtoFiltros(idMicrosueno : number, idAlumno : number, idClase : number, fechaHora : Date, duracion : number): Promise<DtoMicrosuenos[]> {
    //     var filtro = new DtoMicrosuenos();
    //     filtro.idMicrosueno = idMicrosueno;
    //     filtro.idAlumno = idAlumno;
    //     filtro.idClase = idClase;
    //     filtro.fechaHora = fechaHora;
    //     filtro.duracion = duracion;
    //     return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
    //         .toPromise()
    //         .then(response => response as DtoMicrosuenos[])
    //         .catch(error => []);
    // }

    // public listarDtoActivos(idMicrosueno : number, idAlumno : number, idClase : number, fechaHora : Date, duracion : number): Promise<DtoMicrosuenos[]> {
    //     var filtro = new DtoMicrosuenos();
    //     filtro.idMicrosueno = idMicrosueno;
    //     filtro.idAlumno = idAlumno;
    //     filtro.idClase = idClase;
    //     filtro.fechaHora = fechaHora;
    //     filtro.duracion = duracion;
    //     return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
    //         .toPromise()
    //         .then(response => response as DtoMicrosuenos[])
    //         .catch(error => []);
    // }

    // public listarpaginado(filtro: FiltroMicrosuenos): Promise<DominioPaginacion> {
    //     filtro.paginacion.paginacionListaResultado = null;
    //     return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
    //         .toPromise()
    //         .then(response => response as DominioPaginacion)
    //         .catch(error => new DominioPaginacion());
    // }

}
