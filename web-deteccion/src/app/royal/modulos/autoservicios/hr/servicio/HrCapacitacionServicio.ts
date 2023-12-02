import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '@env/appconfig';
import { DtoHrCapacitacion } from '../dominio/dto/DtoHrCapacitacion';
import { FiltroSolicitudCapacitacionListado } from '../dominio/filtro/FiltroSolicitudCapacitacionListado';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';

@Injectable()
export class HrCapacitacionServicio {

    private rutaServicio = `${this.config.getEnv('spring-autoservicios-api')}/spring/rrhh/hrcapacitacion/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public solicitudListado(filtro: FiltroSolicitudCapacitacionListado): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'solicitudListado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public solicitudNuevo(): Promise<DtoHrCapacitacion> {
        return this.http.get(this.rutaServicio + 'solicitudNuevo')
            .toPromise()
            .then(response => response as DtoHrCapacitacion)
            .catch(error => new DtoHrCapacitacion());
    }

    public solicitudObtenerPorUuid(dto: DtoHrCapacitacion): Promise<DtoHrCapacitacion> {
        return this.http.post(this.rutaServicio + 'solicitudObtenerPorUuid', dto)
            .toPromise()
            .then(response => response as DtoHrCapacitacion)
            .catch(error => new DtoHrCapacitacion());
    }

    public solicitudRegistrar(filtro: DtoHrCapacitacion): Promise<DtoHrCapacitacion> {
        return this.http.post(this.rutaServicio + 'solicitudRegistrar', filtro)
            .toPromise()
            .then(response => response as DtoHrCapacitacion)
            .catch(error => new DtoHrCapacitacion());
    }

    public solicitudActualizar(filtro: DtoHrCapacitacion): Promise<DtoHrCapacitacion> {
        return this.http.put(this.rutaServicio + 'solicitudActualizar', filtro)
            .toPromise()
            .then(response => response as DtoHrCapacitacion)
            .catch(error => new DtoHrCapacitacion());
    }

    public solicitudAnular(filtro: any): Promise<any> {
        return this.http.put(this.rutaServicio + 'solicitudAnular', filtro)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }

    public generarResumenDesdeDetalle(filtro: DtoHrCapacitacion): Promise<DtoHrCapacitacion> {
        return this.http.post(this.rutaServicio + 'generarResumenDesdeDetalle', filtro)
            .toPromise()
            .then(response => response as DtoHrCapacitacion)
            .catch(error => new DtoHrCapacitacion());
    }
}
