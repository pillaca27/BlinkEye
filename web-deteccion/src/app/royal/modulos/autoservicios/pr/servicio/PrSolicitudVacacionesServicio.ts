import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '@env/appconfig';
import { DtoPrSolicitudvacacion } from '../dominio/dto/DtoPrSolicitudvacacion';
import { FiltroSolicitudVacacionListado } from '../dominio/filtro/FiltroSolicitudVacacionListado';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { DtoHistorialVacacionPeriodo, DtoHistorialVacacionPeriodoDetalle } from '../dominio/dto/DtoHistorialVacacionPeriodo';
import { DtoVacacionesDiasInformacion } from '../dominio/dto/DtoVacacionesDiasInformacion';

@Injectable()
export class PrSolicitudVacacionesServicio {

    private rutaServicio = `${this.config.getEnv('spring-autoservicios-api')}/spring/planilla/prsolicitudvacacion/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public solicitudListado(filtro: FiltroSolicitudVacacionListado): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'solicitudListado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public solicitudNuevo(): Promise<DtoPrSolicitudvacacion> {
        return this.http.get(this.rutaServicio + 'solicitudNuevo')
            .toPromise()
            .then(response => response as DtoPrSolicitudvacacion)
            .catch(error => new DtoPrSolicitudvacacion());
    }

    public solicitudObtenerPorUuid(dto: DtoPrSolicitudvacacion): Promise<DtoPrSolicitudvacacion> {
        return this.http.post(this.rutaServicio + 'solicitudObtenerPorUuid', dto)
            .toPromise()
            .then(response => response as DtoPrSolicitudvacacion)
            .catch(error => new DtoPrSolicitudvacacion());
    }

    public solicitudRegistrar(filtro: DtoPrSolicitudvacacion): Promise<DtoPrSolicitudvacacion> {
        return this.http.post(this.rutaServicio + 'solicitudRegistrar', filtro)
            .toPromise()
            .then(response => response as DtoPrSolicitudvacacion)
            .catch(error => new DtoPrSolicitudvacacion());
    }

    public solicitudActualizar(filtro: DtoPrSolicitudvacacion): Promise<DtoPrSolicitudvacacion> {
        return this.http.put(this.rutaServicio + 'solicitudActualizar', filtro)
            .toPromise()
            .then(response => response as DtoPrSolicitudvacacion)
            .catch(error => new DtoPrSolicitudvacacion());
    }

    public solicitudAnular(filtro: DtoPrSolicitudvacacion): Promise<DtoPrSolicitudvacacion> {
        return this.http.put(this.rutaServicio + 'solicitudAnular', filtro)
            .toPromise()
            .then(response => response as DtoPrSolicitudvacacion)
            .catch(error => new DtoPrSolicitudvacacion());
    }

    public listarUtilizacion(filtro: DtoHistorialVacacionPeriodo): Promise<DtoHistorialVacacionPeriodoDetalle[]> {
        return this.http.post(this.rutaServicio + 'listarUtilizacion', filtro)
            .toPromise()
            .then(response => response as DtoHistorialVacacionPeriodoDetalle[])
            .catch(error => []);
    }

    public infoDias(): Promise<DtoVacacionesDiasInformacion> {
        return this.http.get(this.rutaServicio + 'infoDias')
            .toPromise()
            .then(response => response as DtoVacacionesDiasInformacion)
            .catch(error => new DtoVacacionesDiasInformacion());
    }

    public infoPeriodoDias(periodo: DtoHistorialVacacionPeriodo): Promise<DtoVacacionesDiasInformacion> {
        return this.http.post(this.rutaServicio + 'infoPeriodoDias', periodo)
            .toPromise()
            .then(response => response as DtoVacacionesDiasInformacion)
            .catch(error => new DtoVacacionesDiasInformacion());
    }

    public registrarSeguimiento(filtro: DtoPrSolicitudvacacion): Promise<DtoPrSolicitudvacacion> {
        return this.http.post(this.rutaServicio + 'registrarSeguimiento', filtro)
            .toPromise()
            .then(response => response as DtoPrSolicitudvacacion)
            .catch(error => new DtoPrSolicitudvacacion());
    }

    public obtenerFeriados(filtro: DtoPrSolicitudvacacion): Promise<DtoTabla[]> {
        return this.http.post(this.rutaServicio + 'obtenerFeriados', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
}
