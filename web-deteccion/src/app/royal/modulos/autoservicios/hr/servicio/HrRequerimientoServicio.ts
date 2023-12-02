import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '@env/appconfig';
import { FiltroSolicitudRequerimientoListado } from '../dominio/filtro/FiltroSolicitudRequerimientoListado';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoHrRequerimiento } from '../dominio/dto/DtoHrRequerimiento';
import { FiltroPostulanteSelector } from '../dominio/filtro/FiltroPostulanteSelector';
import { FiltroPersonalReclutamientoSelector } from '../dominio/filtro/FiltroPersonalReclutamientoSelector';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { DominioMensajeUsuario } from '@framework/modelo/generico/DominioMensajeUsuario';

@Injectable()
export class HrRequerimientoServicio {

    private rutaServicio = `${this.config.getEnv('spring-autoservicios-api')}/spring/rrhh/hrrequerimiento/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public solicitudListado(filtro: FiltroSolicitudRequerimientoListado): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'solicitudListado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listarPostulantes(filtro: FiltroPostulanteSelector): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'listarPostulantes', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listarPersonalReclutamiento(filtro: FiltroPersonalReclutamientoSelector): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'listarPersonalReclutamiento', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listarCategorias(filtro: DtoTabla): Promise<DtoTabla[]> {
        return this.http.post(this.rutaServicio + 'listarCategorias', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarMotivoCese(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarMotivoCese')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarTipoTrabajador(filtro: DtoTabla): Promise<DtoTabla[]> {
        return this.http.post(this.rutaServicio + 'listarTipoTrabajador', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarGradoSalario(filtro: DtoTabla): Promise<DtoTabla[]> {
        return this.http.post(this.rutaServicio + 'listarGradoSalario', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarTipoContrato(filtro: DtoTabla): Promise<DtoTabla[]> {
        return this.http.post(this.rutaServicio + 'listarTipoContrato', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public solicitudNuevo(): Promise<DtoHrRequerimiento> {
        return this.http.get(this.rutaServicio + 'solicitudNuevo')
            .toPromise()
            .then(response => response as DtoHrRequerimiento)
            .catch(error => new DtoHrRequerimiento());
    }

    public solicitudObtenerPorUuid(dto: DtoHrRequerimiento): Promise<DtoHrRequerimiento> {
        return this.http.post(this.rutaServicio + 'solicitudObtenerPorUuid', dto)
            .toPromise()
            .then(response => response as DtoHrRequerimiento)
            .catch(error => new DtoHrRequerimiento());
    }

    public solicitudRegistrar(filtro: DtoHrRequerimiento): Promise<DtoHrRequerimiento> {
        return this.http.post(this.rutaServicio + 'solicitudRegistrar', filtro)
            .toPromise()
            .then(response => response as DtoHrRequerimiento)
            .catch(error => new DtoHrRequerimiento());
    }

    public solicitudActualizar(filtro: DtoHrRequerimiento): Promise<DtoHrRequerimiento> {
        return this.http.put(this.rutaServicio + 'solicitudActualizar', filtro)
            .toPromise()
            .then(response => response as DtoHrRequerimiento)
            .catch(error => new DtoHrRequerimiento());
    }

    public solicitudAnular(filtro: DtoHrRequerimiento): Promise<DtoHrRequerimiento> {
        return this.http.put(this.rutaServicio + 'solicitudAnular', filtro)
            .toPromise()
            .then(response => response as DtoHrRequerimiento)
            .catch(error => new DtoHrRequerimiento());
    }

    public validarVacantes(filtro: DtoHrRequerimiento): Promise<DtoHrRequerimiento> {
        return this.http.post(this.rutaServicio + 'validarVacantes', filtro)
            .toPromise()
            .then(response => response as DtoHrRequerimiento)
            .catch(error => new DtoHrRequerimiento());
    }

    public validarOrganigarma(filtro: DtoHrRequerimiento): Promise<DominioMensajeUsuario[]> {
        return this.http.post(this.rutaServicio + 'validarOrganigarma', filtro)
            .toPromise()
            .then(response => response as DominioMensajeUsuario[])
            .catch(error => []);
    }
}
