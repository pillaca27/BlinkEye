import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '@env/appconfig';
import { DtoEncuestaClimaLaboralListado } from '../dominio/dto/DtoEncuestaClimaLaboralListado';
import { DtoEncuestaEjecucion } from '../dominio/dto/DtoEncuestaEjecucion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
@Injectable()
export class HrEncuestaServicio {

    private rutaServicio = `${this.config.getEnv('spring-autoservicios-api')}/spring/rrhh/hrencuesta/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public listarEncuestasPendientes(): Promise<DtoEncuestaClimaLaboralListado[]> {
        return this.http.get(this.rutaServicio + 'listarEncuestasPendientes')
            .toPromise()
            .then(response => response as DtoEncuestaClimaLaboralListado[])
            .catch(error => []);
    }

    public obtenerEncuesta(dto: DtoEncuestaClimaLaboralListado): Promise<DtoEncuestaEjecucion> {
        return this.http.post(this.rutaServicio + 'obtenerEncuesta', dto)
            .toPromise()
            .then(response => response as DtoEncuestaEjecucion)
            .catch(error => new DtoEncuestaEjecucion());
    }

    public registrarMuestra(dto: DtoEncuestaEjecucion): Promise<DtoEncuestaEjecucion> {
        return this.http.post(this.rutaServicio + 'registrarMuestra', dto)
            .toPromise()
            .then(response => response as DtoEncuestaEjecucion)
            .catch(error => new DtoEncuestaEjecucion());
    }

    public listarPlantillas(dto: DtoTabla): Promise<DtoTabla[]> {
        return this.http.put(this.rutaServicio + 'listarPlantillas', dto)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
}
