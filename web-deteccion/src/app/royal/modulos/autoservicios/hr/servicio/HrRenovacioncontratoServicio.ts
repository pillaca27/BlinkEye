import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '@env/appconfig';
import { DtoHrCapacitacion } from '../dominio/dto/DtoHrCapacitacion';
import { FiltroSolicitudCapacitacionListado } from '../dominio/filtro/FiltroSolicitudCapacitacionListado';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { FiltroSolicitudRenovacioncontratoListado } from '../dominio/filtro/FiltroSolicitudRenovacioncontratoListado';
import { DtoHrContratos } from '../dominio/dto/DtoHrContratos';

@Injectable()
export class HrRenovacioncontratoServicio {

    private rutaServicio = `${this.config.getEnv('spring-autoservicios-api')}/spring/rrhh/hrcontratos/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public solicitudListado(filtro: FiltroSolicitudRenovacioncontratoListado): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'solicitudListado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public validarPedidoSolicitud(dto: DtoHrContratos): Promise<DtoHrContratos> {
        return this.http.post(this.rutaServicio + 'validarPedidoSolicitud', dto)
            .toPromise()
            .then(response => response as DtoHrContratos)
            .catch(error => new DtoHrContratos());
    }

    public obtenerSolicitudContrato(contratos: DtoHrContratos): Promise<DtoHrContratos> {
        return this.http.post(this.rutaServicio + 'obtenerSolicitudContrato', contratos)
            .toPromise()
            .then(response => response as DtoHrContratos)
            .catch(error => null);
    }


    public solicitudContratoEliminar(contratos: DtoHrContratos): Promise<DtoHrContratos> {
        return this.http.post(this.rutaServicio + 'solicitudContratoEliminar', contratos)
            .toPromise()
            .then(response => response as DtoHrContratos)
            .catch(error => null);
    }

    public solicitudContratoRegistrar(contratos: DtoHrContratos): Promise<DtoHrContratos> {
        return this.http.post(this.rutaServicio + 'solicitudContratoRegistrar', contratos)
            .toPromise()
            .then(response => response as DtoHrContratos)
            .catch(error => null);
    }

}
