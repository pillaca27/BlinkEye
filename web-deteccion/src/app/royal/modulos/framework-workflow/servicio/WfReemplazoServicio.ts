import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { WfReemplazo, WfReemplazoPk } from './../dominio/dto/WfReemplazo';
import { AppConfig } from 'src/environments/appconfig';
import { WfTransaccionPk } from './../dominio/dto/WfTransaccion';
import { DominioPaginacion } from 'src/app/royal/framework/modelo/generico/DominioPaginacion';

@Injectable()
export class WfReemplazoServicio {

    private rutaServicio = `${this.config.getEnv('spring-framework-workflow-api')}spring/workflow/wfreemplazo/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public listarPaginado(filtro: FiltroReemplazo, paginacion: DominioPaginacion): Promise<DominioPaginacion> {

        return this.http.post(this.rutaServicio + 'listarPaginado', { filtro: filtro, paginacion: paginacion })
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public registrar(bean: WfReemplazo): Promise<WfTransaccionPk> {

        return this.http.post(this.rutaServicio + 'registrar', bean)
            .toPromise()
            .then(response => response as WfTransaccionPk)
            .catch(error => null);
    }

    public actualizar(bean: WfReemplazo): Promise<WfTransaccionPk> {

        return this.http.post(this.rutaServicio + 'actualizar', bean)
            .toPromise()
            .then(response => response as WfTransaccionPk)
            .catch(error => null);
    }

    public obtenerPorId(prestamo: WfReemplazoPk): Promise<WfReemplazo> {

        return this.http.post(this.rutaServicio + 'obtenerPorId', prestamo)
            .toPromise()
            .then(response => response as WfReemplazo)
            .catch(error => null);
    }

    public anular(prestamo: WfReemplazoPk): Promise<WfReemplazoPk> {

        return this.http.post(this.rutaServicio + 'anular', prestamo)
            .toPromise()
            .then(response => response as WfReemplazoPk)
            .catch(error => null);
    }
}

export class FiltroReemplazo {
    id: number;
    nombre: string;
    descripcion: string;
    estado: string;
    reemplazo: number;
}