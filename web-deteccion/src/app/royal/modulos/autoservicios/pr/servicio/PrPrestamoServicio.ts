import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '@env/appconfig';
import { DtoPrPrestamo } from '../dominio/dto/DtoPrPrestamo';
import { FiltroSolicitudPrestamoListado } from '../dominio/filtro/FiltroSolicitudPrestamoListado';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';

@Injectable()
export class PrPrestamoServicio {

    private rutaServicio = `${this.config.getEnv('spring-autoservicios-api')}/spring/planilla/prprestamo/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public solicitudListado(filtro: FiltroSolicitudPrestamoListado): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'solicitudListado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public solicitudNuevo(): Promise<DtoPrPrestamo> {
        return this.http.get(this.rutaServicio + 'solicitudNuevo')
            .toPromise()
            .then(response => response as DtoPrPrestamo)
            .catch(error => new DtoPrPrestamo());
    }

    public solicitudObtenerPorUuid(dto: DtoPrPrestamo): Promise<DtoPrPrestamo> {
        return this.http.post(this.rutaServicio + 'solicitudObtenerPorUuid', dto)
            .toPromise()
            .then(response => response as DtoPrPrestamo)
            .catch(error => new DtoPrPrestamo());
    }

    public solicitudRegistrar(filtro: DtoPrPrestamo): Promise<DtoPrPrestamo> {
        return this.http.post(this.rutaServicio + 'solicitudRegistrar', filtro)
            .toPromise()
            .then(response => response as DtoPrPrestamo)
            .catch(error => new DtoPrPrestamo());
    }

    public solicitudActualizar(filtro: DtoPrPrestamo): Promise<DtoPrPrestamo> {
        return this.http.put(this.rutaServicio + 'solicitudActualizar', filtro)
            .toPromise()
            .then(response => response as DtoPrPrestamo)
            .catch(error => new DtoPrPrestamo());
    }

    public solicitudAnular(filtro: DtoPrPrestamo): Promise<DtoPrPrestamo> {
        return this.http.put(this.rutaServicio + 'solicitudAnular', filtro)
            .toPromise()
            .then(response => response as DtoPrPrestamo)
            .catch(error => new DtoPrPrestamo());
    }

    public lstTipoPrestamo(filtro: DtoTabla): Promise<DtoTabla[]> {
        return this.http.post(this.rutaServicio + 'lstTipoPrestamo', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public lstConcepto(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'lstConcepto')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public lstTipoDocumento(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'lstTipoDocumento')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
}
