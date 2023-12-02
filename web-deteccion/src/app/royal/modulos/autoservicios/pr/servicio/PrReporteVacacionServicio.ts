import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '@env/appconfig';
import { FiltroReporteVacacionesPago } from '../dominio/filtro/FiltroReporteVacacionesPago';
import { FiltroReporteVacacionesUtilizacion } from '../dominio/filtro/FiltroReporteVacacionesUtilizacion';
import { FiltroReporteVacacionesResumen } from '../dominio/filtro/FiltroReporteVacacionesResumen';
import { FiltroReporteVacacionesPendientesGoce } from '../dominio/filtro/FiltroReporteVacacionesPendientesGoce';
import { FiltroReporteVacaciones } from '../dominio/filtro/FiltroReporteVacaciones';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';

@Injectable()
export class PrReporteVacacionServicio {

    private rutaServicio = `${this.config.getEnv('spring-autoservicios-api')}/spring/planilla/reportevacacion/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public reportePago(filtro: FiltroReporteVacacionesPago): Promise<FiltroReporteVacacionesPago> {
        return this.http.post(this.rutaServicio + 'reportePago', filtro)
            .toPromise()
            .then(response => response as FiltroReporteVacacionesPago)
            .catch(error => new FiltroReporteVacacionesPago());
    }

    public reporteUtilizacion(filtro: FiltroReporteVacacionesUtilizacion): Promise<FiltroReporteVacacionesUtilizacion> {
        return this.http.post(this.rutaServicio + 'reporteUtilizacion', filtro)
            .toPromise()
            .then(response => response as FiltroReporteVacacionesUtilizacion)
            .catch(error => new FiltroReporteVacacionesUtilizacion());
    }

    public reporteResumen(filtro: FiltroReporteVacacionesResumen): Promise<FiltroReporteVacacionesResumen> {
        return this.http.post(this.rutaServicio + 'reporteResumen', filtro)
            .toPromise()
            .then(response => response as FiltroReporteVacacionesResumen)
            .catch(error => new FiltroReporteVacacionesResumen());
    }

    public reportePendientes(filtro: FiltroReporteVacacionesPendientesGoce): Promise<FiltroReporteVacacionesPendientesGoce> {
        return this.http.post(this.rutaServicio + 'reportePendientes', filtro)
            .toPromise()
            .then(response => response as FiltroReporteVacacionesPendientesGoce)
            .catch(error => new FiltroReporteVacacionesPendientesGoce());
    }

    public obtenerFiltros(): Promise<FiltroReporteVacaciones> {
        return this.http.get(this.rutaServicio + 'obtenerFiltros')
            .toPromise()
            .then(response => response as FiltroReporteVacaciones)
            .catch(error => new FiltroReporteVacaciones());
    }

    public validarPermisos(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'validarPermisos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
}
