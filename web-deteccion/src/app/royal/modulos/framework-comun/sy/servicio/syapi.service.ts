import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AppConfig } from '@env/appconfig';
import { DtoTabla } from 'src/app/royal/framework/modelo/generico/dto/DtoTabla';
import { DtoFiltroApi } from '../dominio/dto/DtoFiltroApi';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';
import { DtoWsApi } from '../dominio/dto/DtoWsApi';
import { DtoWsApipath } from '../dominio/dto/DtoWsApipath';

@Injectable()
export class SyApiServicio {

    private rutaServicio = `${this.config.getEnv('spring-framework-comun-api')}spring/sistema/syapi/`;

    constructor(private http: HttpClient, private config: AppConfig) { }

    public listarApis(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarApis')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarrutaspaginado(filtro: DtoFiltroApi): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'listarrutaspaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public registrar(filtro: DtoWsApi): Promise<DominioTransaccion> {
        return this.http.post(this.rutaServicio + 'registrar', filtro)
            .toPromise()
            .then(response => response as DominioTransaccion)
            .catch(error => new DominioTransaccion());
    }

    public obtenerDetallePath(filtro: DtoWsApipath): Promise<DtoWsApipath> {
        return this.http.post(this.rutaServicio + 'obtenerDetallePath', filtro)
            .toPromise()
            .then(response => response as DtoWsApipath)
            .catch(error => new DtoWsApipath());
    }

    public vistobueno(filtro: DtoWsApipath): Promise<DtoWsApipath> {
        return this.http.post(this.rutaServicio + 'vistobueno', filtro)
            .toPromise()
            .then(response => response as DtoWsApipath)
            .catch(error => new DtoWsApipath());
    }

    public get(url: string): Promise<any> {
        return this.http.get(url)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }
}
