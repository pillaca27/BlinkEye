import { AppConfig } from './../../../../../../environments/appconfig';
import { FiltroPaginacionEmpleado } from '../dominio/filtros/filtropaginacionempleado';
import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { DtoAccesosdiarios } from './../dominio/dto/DtoAccesosdiarios';

@Injectable()
export class AsAccesosdiariosService {

    private url = `${this.config.getEnv('spring-autoservicios-api')}spring/asistencia/asaccesosdiarios/`;

    constructor(private config: AppConfig) { }

    public obtenerMarcas(filtro: FiltroPaginacionEmpleado): any {
        return this.config.getHttp().post(`${this.url}obtenerMarcas`, filtro, { responseType: 'blob' })
            .pipe(
                map((result: any) => {
                    return result;
                })
            );
    }


    public obtenerAsistencia(filtro: FiltroPaginacionEmpleado): any {
        return this.config.getHttp().post(`${this.url}obtenerAsistencia`, filtro, { responseType: 'blob' })
            .pipe(
                map((result: any) => {
                    return result;
                })
            );
    }


    public obtenerAsistenciaDetallada(filtro: FiltroPaginacionEmpleado): any {
        return this.config.getHttp().post(`${this.url}obtenerAsistenciaDetallada`, filtro, { responseType: 'blob' })
            .pipe(
                map((result: any) => {
                    return result;
                })
            );
    }


    obtenerInformacionParaMarcar(): Promise<DtoAccesosdiarios> {
        console.log('obtenerInformacionParaMarcar');
        return this.config.getHttp().get(this.url + 'obtenerInformacionParaMarcar')
            .toPromise()
            .then(response => response as DtoAccesosdiarios)
            .catch(error => null);
    }

    public insertarMarca(bean: DtoAccesosdiarios): Promise<DtoAccesosdiarios> {
        return this.config.getHttp().put(`${this.url}insertarMarca`, bean)
            .toPromise()
            .then(response => {
                return response as DtoAccesosdiarios;
            })
            .catch(error => null);
    }

    public inicializar(): Promise<FiltroPaginacionEmpleado> {
        return this.config.getHttp().put(`${this.url}inicializar`, null)
            .toPromise()
            .then(response => {
                return response as FiltroPaginacionEmpleado;
            })
            .catch(error => null);
    }

}
