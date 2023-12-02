import { AppConfig } from './../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DtoHrPostulante } from '../dominio/DtoHrPostulante';
import { FiltroPostulantePostular } from '../dominio/filtro/FiltroPostulantePostular';
import { DtoPostulanteRequerimientoPostularListado } from '../dominio/dto/DtoPostulanteRequerimientoPostularListado';
import { FiltroComunUbigeo } from '@framework-comun/core/dominio/filtro/FiltroComunUbigeo';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { FiltroSolicitudRequerimientoListado } from '../../autoservicios/hr/dominio/filtro/FiltroSolicitudRequerimientoListado';

@Injectable()
export class HrPostulanteService {

    private url = `${this.config.getEnv('spring-autoservicios-api')}/spring/rrhh/hrpostulante/`;
    private urlPublico = `${this.config.getEnv('spring-autoservicios-api')}/spring/publico/rrhh/hrpostulante/`;

    constructor(private http: HttpClient, private config: AppConfig) { }

    public datosPersonales(pk: DtoHrPostulante): Promise<DtoHrPostulante> {
        return this.config.getHttp().post(this.url + 'datosPersonales', pk)
            .toPromise()
            .then(response => response as DtoHrPostulante)
            .catch(error => new DtoHrPostulante);
    }

    public registrarDatosPersonales(dto: DtoHrPostulante): Promise<DtoHrPostulante> {
        return this.config.getHttp().post(this.url + 'registrarDatosPersonales', dto)
            .toPromise()
            .then(response => response as DtoHrPostulante)
            .catch(error => new DtoHrPostulante);
    }

    public registrarDatosPersonalesPublico(dto: DtoHrPostulante): Promise<DtoHrPostulante> {
        return this.config.getHttp().post(this.urlPublico + 'registrarDatosPersonales', dto)
            .toPromise()
            .then(response => response as DtoHrPostulante)
            .catch(error => new DtoHrPostulante);
    }

    public actualizarDatosPersonales(dto: DtoHrPostulante): Promise<DtoHrPostulante> {
        return this.config.getHttp().post(this.url + 'actualizarDatosPersonales', dto)
            .toPromise()
            .then(response => response as DtoHrPostulante)
            .catch(error => new DtoHrPostulante());
    }

    public listarubigeoporfiltro(dto: FiltroComunUbigeo): Promise<DominioPaginacion> {
        return this.config.getHttp().post(this.urlPublico + 'listarubigeoporfiltro', dto)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public lstTipoDocumento(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.urlPublico + 'lstTipoDocumento').toPromise().then(response => response as DtoTabla[]).catch(error => []);
    }
    public lstCodigoLDN(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.urlPublico + 'lstCodigoLDN').toPromise().then(response => response as DtoTabla[]).catch(error => []);
    }
    public lstGrupoSanguineo(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.urlPublico + 'lstGrupoSanguineo').toPromise().then(response => response as DtoTabla[]).catch(error => []);
    }
    public lstTipoVivienda(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.urlPublico + 'lstTipoVivienda').toPromise().then(response => response as DtoTabla[]).catch(error => []);
    }
    public lstEstadoCivil(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.urlPublico + 'lstEstadoCivil').toPromise().then(response => response as DtoTabla[]).catch(error => []);
    }
    public lstParentesco(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.urlPublico + 'lstParentesco').toPromise().then(response => response as DtoTabla[]).catch(error => []);
    }
    public lstCategoriaLicencia(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.urlPublico + 'lstCategoriaLicencia').toPromise().then(response => response as DtoTabla[]).catch(error => []);
    }
    public lstTipoVisa(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.urlPublico + 'lstTipoVisa').toPromise().then(response => response as DtoTabla[]).catch(error => []);
    }
    public lstPaisNacionalidad(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.urlPublico + 'lstPaisNacionalidad').toPromise().then(response => response as DtoTabla[]).catch(error => []);
    }
    public informacionAdicionalPostulante(): Promise<any> {
        return this.config.getHttp().get(this.urlPublico + 'informacionAdicionalPostulante').toPromise().then(response => response as any).catch(error => null);
    }

    public direccionesListar(dto: DtoHrPostulante): Promise<any[]> {
        return this.config.getHttp().post(this.url + 'direccionesListar', dto).toPromise().then(response => response as any[]).catch(error => []);
    }
    public instruccionBasicaListar(dto: DtoHrPostulante): Promise<any[]> {
        return this.config.getHttp().post(this.url + 'instruccionBasicaListar', dto).toPromise().then(response => response as any[]).catch(error => []);
    }
    public instruccionSuperiorListar(dto: DtoHrPostulante): Promise<any[]> {
        return this.config.getHttp().post(this.url + 'instruccionSuperiorListar', dto).toPromise().then(response => response as any[]).catch(error => []);
    }
    public cursosListar(dto: DtoHrPostulante): Promise<any[]> {
        return this.config.getHttp().post(this.url + 'cursosListar', dto).toPromise().then(response => response as any[]).catch(error => []);
    }
    public idiomasListar(dto: DtoHrPostulante): Promise<any[]> {
        return this.config.getHttp().post(this.url + 'idiomasListar', dto).toPromise().then(response => response as any[]).catch(error => []);
    }
    public informaticaListar(dto: DtoHrPostulante): Promise<any[]> {
        return this.config.getHttp().post(this.url + 'informaticaListar', dto).toPromise().then(response => response as any[]).catch(error => []);
    }
    public experienciaListar(dto: DtoHrPostulante): Promise<any[]> {
        return this.config.getHttp().post(this.url + 'experienciaListar', dto).toPromise().then(response => response as any[]).catch(error => []);
    }
    public referenciaListar(dto: DtoHrPostulante): Promise<any[]> {
        return this.config.getHttp().post(this.url + 'referenciaListar', dto).toPromise().then(response => response as any[]).catch(error => []);
    }
    public documentosListar(dto: DtoHrPostulante): Promise<any[]> {
        return this.config.getHttp().post(this.url + 'documentosListar', dto).toPromise().then(response => response as any[]).catch(error => []);
    }
    public reqAsocListar(dto: DtoHrPostulante): Promise<any[]> {
        return this.config.getHttp().post(this.url + 'reqAsocListar', dto).toPromise().then(response => response as any[]).catch(error => []);
    }
    public dependientesListar(dto: DtoHrPostulante): Promise<any[]> {
        return this.config.getHttp().post(this.url + 'dependientesListar', dto).toPromise().then(response => response as any[]).catch(error => []);
    }

    public crud(dto: any, accion): Promise<any> {
        return this.config.getHttp().post(this.url + accion, dto).toPromise().then(response => response as any[]).catch(error => []);
    }

    public requerimientosPostular(dto: FiltroPostulantePostular): Promise<DtoPostulanteRequerimientoPostularListado[]> {
        return this.config.getHttp().post(this.url + 'requerimientosPostular', dto).toPromise().then(response => response as DtoPostulanteRequerimientoPostularListado[]).catch(error => []);
    }

    public perfilRequerimiento(filtro: DtoPostulanteRequerimientoPostularListado): Promise<DtoPostulanteRequerimientoPostularListado> {
        return this.http.post(this.url + 'perfilRequerimiento', filtro)
            .toPromise()
            .then(response => response as DtoPostulanteRequerimientoPostularListado)
            .catch(error => new DtoPostulanteRequerimientoPostularListado());
    }

    public registrarPostulacion(dto: DtoPostulanteRequerimientoPostularListado): Promise<DtoPostulanteRequerimientoPostularListado> {
        return this.http.post(this.url + 'registrarPostulacion', dto)
            .toPromise()
            .then(response => response as DtoPostulanteRequerimientoPostularListado)
            .catch(error => new DtoPostulanteRequerimientoPostularListado());
    }

    public listarrequerimientoaprobado(filtro: FiltroSolicitudRequerimientoListado): Promise<any[]> {
        return this.http.post(this.url + 'listarrequerimientoaprobado', filtro)
            .toPromise()
            .then(response => response as any[])
            .catch(error => []);
    }


}