import { DominioPaginacion } from '../../../../framework/modelo/generico/DominioPaginacion';
import { FiltroComunHrCentroestudios } from '../dominio/filtro/FiltroComunHrCentroestudios';
import { FiltroComunHrCentroestudiosCarrera } from '../dominio/filtro/FiltroComunHrCentroestudiosCarrera';
import { DtoComunHrCentroestudioCarrera } from '../dominio/dto/DtoComunHrCentroestudioCarrera';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HrCentroestudiosComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/rrhh/hrcentroestudioscomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //ARMAS MIGRADO
    //RH-CENESTU-CLISTA
    public listar(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //HR-CENESTU-OBTDTO
    public obtenerTabla(centro: number): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.id = centro;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }

    //QQUECHOD VALIDADO
    //HR-CENESTU-LISFIL
    public listarFiltros(centro: number, descripcion: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.id = centro;
        filtro.nombre = descripcion;
        return this.config.getHttp().put(this.url + 'listarfiltros', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //ARMAS MIGRADO
    //RH-CENESTU-CPAGIN
    public listarpaginacion(filtro: FiltroComunHrCentroestudios): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarpaginacion', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listarPaginacionConCarrera(filtro: FiltroComunHrCentroestudiosCarrera): Promise<DominioPaginacion> {
        return this.http.post(this.url + 'listarPaginacionConCarrera', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listarCarreraPorCentroEstudio(filtro: DtoComunHrCentroestudioCarrera): Promise<DtoComunHrCentroestudioCarrera[]> {
        return this.http.post(this.url + 'listarCarreraPorCentroEstudio', filtro)
            .toPromise()
            .then(response => response as DtoComunHrCentroestudioCarrera[])
            .catch(error => []);
    }

}