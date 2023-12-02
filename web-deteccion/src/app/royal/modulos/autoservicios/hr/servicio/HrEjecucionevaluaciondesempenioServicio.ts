import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '@env/appconfig';
import { DtoEvaluacionDisponibleListado } from '../dominio/dto/DtoEvaluacionDisponibleListado';
import { DtoEvaluacionParticipante } from '../dominio/dto/DtoEvaluacionParticipante';
import { DtoHrEvalempevaluadoevaluacion } from '../dominio/dto/DtoHrEvalempevaluadoevaluacion';
import { FiltroEvaluacionDesempenio } from '../dominio/filtro/FiltroEvaluacionDesempenio';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';

@Injectable()
export class HrEjecucionevaluaciondesempenioServicio {

    private rutaServicio = `${this.config.getEnv('spring-autoservicios-api')}/spring/rrhh/hrejecucionevaluaciondesempenio/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public listarEvaluacionesDisponibles(filtro: FiltroEvaluacionDesempenio): Promise<DtoEvaluacionDisponibleListado[]> {
        return this.http.post(this.rutaServicio + 'listarEvaluacionesDisponibles', filtro)
            .toPromise()
            .then(response => response as DtoEvaluacionDisponibleListado[])
            .catch(error => []);
    }

    public listarParticipantes(row: DtoEvaluacionDisponibleListado): Promise<DtoEvaluacionParticipante[]> {
        return this.http.post(this.rutaServicio + 'listarParticipantes', row)
            .toPromise()
            .then(response => response as DtoEvaluacionParticipante[])
            .catch(error => []);
    }

    public obtenerEvaluacion(row: DtoEvaluacionParticipante): Promise<DtoHrEvalempevaluadoevaluacion> {
        return this.http.post(this.rutaServicio + 'obtenerEvaluacion', row)
            .toPromise()
            .then(response => response as DtoHrEvalempevaluadoevaluacion)
            .catch(error => new DtoHrEvalempevaluadoevaluacion());
    }

    public guardarEvaluacion(row: DtoHrEvalempevaluadoevaluacion): Promise<DtoHrEvalempevaluadoevaluacion> {
        return this.http.post(this.rutaServicio + 'guardarEvaluacion', row)
            .toPromise()
            .then(response => response as DtoHrEvalempevaluadoevaluacion)
            .catch(error => new DtoHrEvalempevaluadoevaluacion());
    }

    public terminarEvaluacion(row: DtoHrEvalempevaluadoevaluacion): Promise<DtoHrEvalempevaluadoevaluacion> {
        return this.http.post(this.rutaServicio + 'terminarEvaluacion', row)
            .toPromise()
            .then(response => response as DtoHrEvalempevaluadoevaluacion)
            .catch(error => new DtoHrEvalempevaluadoevaluacion());
    }

    public imprimir(row: DtoEvaluacionParticipante): Promise<DtoTabla> {
        return this.http.post(this.rutaServicio + 'imprimir', row)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }

}
