import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { DtoAsConcepto } from '../dominio/dto/DtoAsConcepto';
import { DominioMensajeUsuario } from '@framework/modelo/generico/DominioMensajeUsuario';
import { DtoAsAutorizacion } from '../dominio/dto/DtoAsAutorizacion';
import { DtoWAsAutorizacionHorasDisponibles } from '../dominio/dto/DtoWAsAutorizacionHorasDisponibles';
import { convertDateStringsToDates } from '@framework/angular/funciones/dateutils';
import { DatePipe } from '@angular/common';
import { Observable } from 'rxjs';
import { FiltroPaginacionEmpleado } from '../dominio/filtros/filtropaginacionempleado';
import { DtoSolicitudPermisoListado } from '../dominio/dto/DtoSolicitudPermisoListado';

@Injectable({ providedIn: 'root' })

export class AsAutorizacionService {

    private url = `${this.config.getEnv('spring-autoservicios-api')}spring/asistencia/asautorizacion/`;
    constructor(
        private datePipe: DatePipe,
        private http: HttpClient,
        private config: AppConfig) { }
        

        public solicitudRegistrar(dto: DtoAsAutorizacion): Promise<DtoAsAutorizacion> {
            return this.config.getHttp().post(this.url + 'solicitudRegistrar', dto)
                .toPromise()
                .then(response => {
                    return response as DtoAsAutorizacion;
                })
                .catch(error => new DtoAsAutorizacion());
        }
        
        public solicitudActualizar(dto: DtoAsAutorizacion): Promise<DtoAsAutorizacion> {
            return this.config.getHttp().put(this.url + 'solicitudActualizar', dto)
                .toPromise()
                .then(response => {
                    return response as DtoAsAutorizacion;
                })
                .catch(error => new DtoAsAutorizacion());
        }

        public solicitudListado(filtro: FiltroPaginacionEmpleado): Promise<DtoSolicitudPermisoListado[]> {
            return this.config.getHttp().post(this.url + 'solicitudListado', filtro)
                .toPromise()
                .then(response => {
                    return response as DtoSolicitudPermisoListado[];
                })
                .catch(error => []);
        }























    public solicitudListadoOld(filtro: FiltroPaginacionEmpleado): Promise<DominioPaginacion> {
        return this.config.getHttp().post(this.url + 'solicitudListadoOld', filtro)
            .toPromise()
            .then(response => {
                return response as DominioPaginacion;
            })
            .catch(error => new DominioPaginacion());
    }

    
   

    obtenerConceptos(isAdmin: string): Promise<DtoAsConcepto[]> {

        let dto: DtoTabla = new DtoTabla();
        dto.codigo = isAdmin;

        return this.config.getHttp().post(this.url + 'obtenerConceptos', dto)
            .toPromise()
            .then(response => response as DtoAsConcepto[])
            .catch(error => []);
    }


    listarEstados(aplicacion: string, codigotabla: string): Promise<DtoTabla[]> {
        console.log('listarEstados');
        const params = new HttpParams()
            .set('aplicacion', aplicacion)
            .set('codigotabla', codigotabla);

        return this.config.getHttp().get(this.url + 'listarEstados', { params })
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    solicitudValidarAccion(accion: string, numeroproceso: number): Promise<DominioMensajeUsuario[]> {

        return this.config.getHttp().get(this.url + 'validarAccion/' + accion + '/' + numeroproceso)
            .toPromise()
            .then(response => response as DominioMensajeUsuario[])
            .catch(error => null);
    }

    public validarAprobacion(listaPermiso: DtoAsAutorizacion[]): Promise<DominioMensajeUsuario[]> {
        return this.config.getHttp().put(`${this.url}validarAprobacion`, listaPermiso)
            .toPromise()
            .then(response => {
                return response as DominioMensajeUsuario[];
            })
            .catch(error => []);
    }

    public procesoAprobacion(listaPermiso: DtoAsAutorizacion[], tieneAccesoSysAdm: string): Promise<DominioMensajeUsuario[]> {
        return this.config.getHttp().put(`${this.url}procesoAprobacion` + `/` + tieneAccesoSysAdm, listaPermiso)
            .toPromise()
            .then(response => {
                return response as DominioMensajeUsuario[];
            })
            .catch(error => []);
    }

    puedeAutorizar(): Promise<DtoTabla> {
        return this.config.getHttp().get(this.url + 'puedeAutorizar')
            .toPromise()
            .then(response => response as DtoTabla);
    }

    obtenerSumaDecimales(sumaHoras: number): Promise<DtoWAsAutorizacionHorasDisponibles> {
        const params = new HttpParams()
            .set('sumaHoras', JSON.stringify(sumaHoras));

        return this.config.getHttp().get(this.url + 'obtenerSumaDecimales', { params })
            .toPromise()
            .then(response => response as DtoWAsAutorizacionHorasDisponibles);
    }


    public cambiarFechaInicio(bean: DtoAsAutorizacion): Promise<DtoAsAutorizacion> {
        bean = this.setearHoras(bean);
        return this.config.getHttp().put(`${this.url}cambiarFechaInicio`, bean)
            .toPromise()
            .then(response => {
                return convertDateStringsToDates(response) as DtoAsAutorizacion;
            })
            .catch(error => null);
    }

    setearHoras(autorizacion: DtoAsAutorizacion): DtoAsAutorizacion {
        // autorizacion.horasDesde = this.datePipe.transform(autorizacion.desde1, 'dd/M/yyyy HH:mm:ss');
        // autorizacion.horasHasta = this.datePipe.transform(autorizacion.hasta1, 'dd/M/yyyy HH:mm:ss');
        return autorizacion;
    }

    public cambiarHoraInicio(bean: DtoAsAutorizacion): Promise<DtoAsAutorizacion> {
        bean = this.setearHoras(bean);
        return this.config.getHttp().put(`${this.url}cambiarHoraInicio`, bean)
            .toPromise()
            .then(response => {
                return convertDateStringsToDates(response) as DtoAsAutorizacion;
            })
            .catch(error => null);
    }

    public cambiarFechaFin(bean: DtoAsAutorizacion): Promise<DtoAsAutorizacion> {
        bean = this.setearHoras(bean);
        return this.config.getHttp().put(`${this.url}cambiarFechaFin`, bean)
            .toPromise()
            .then(response => {
                return convertDateStringsToDates(response) as DtoAsAutorizacion;
            })
            .catch(error => null);
    }

    public cargarConceptos(bean: DtoAsAutorizacion, accion: string, isAdmin: string, concepto: string): Promise<DtoAsAutorizacion> {
        bean = this.setearHoras(bean);
        console.log('cargarConceptos');
        return this.config.getHttp().put(`${this.url}cargarConceptos` + `/` + accion + `/` + isAdmin + `/` + concepto, bean)
            .toPromise()
            .then(response => {
                return convertDateStringsToDates(response) as DtoAsAutorizacion;
            })
            .catch(error => null);
    }

    public obtenerConceptosSeleccionados(bean: DtoAsAutorizacion, accion: string, isAdmin: string, idevento: string): Promise<DtoAsAutorizacion> {
        bean = this.setearHoras(bean);
        return this.config.getHttp().put(`${this.url}obtenerConceptosSeleccionados` + `/` + accion + `/` + isAdmin + `/` + idevento, bean)
            .toPromise()
            .then(response => {
                return convertDateStringsToDates(response) as DtoAsAutorizacion;
            })
            .catch(error => null);
    }

    DddwAsTipodiaasistenciaSelect(): Promise<any[]> {

        return this.config.getHttp().get(this.url + 'DddwAsTipodiaasistenciaSelect')
            .toPromise()
            .then(response => response as any[]);
    }

    nuevoHorario(bean: DtoAsAutorizacion): Promise<DtoAsAutorizacion> {
        return this.config.getHttp().put(this.url + 'nuevoHorario', bean)
            .toPromise()
            .then(response => convertDateStringsToDates(response) as DtoAsAutorizacion);
    }

    valuechangedHorarioTipoDia(horario: DtoAsAutorizacion): Promise<DtoAsAutorizacion> {

        return this.config.getHttp().put(this.url + 'valuechangedHorarioTipoDia', horario)
            .toPromise()
            .then(response => convertDateStringsToDates(response) as DtoAsAutorizacion);
    }

    copiarHorario(bean: DtoAsAutorizacion): Promise<DtoAsAutorizacion> {
        bean = this.setearHoras(bean);
        return this.config.getHttp().put(this.url + 'copiarHorario', bean)
            .toPromise()
            .then(response => convertDateStringsToDates(response) as DtoAsAutorizacion)
            .catch(error => null);
    }

    horasManana(bean: DtoAsAutorizacion): Promise<DtoAsAutorizacion> {
        bean = this.setearHoras(bean);
        return this.config.getHttp().put(this.url + 'horasManana', bean)
            .toPromise()
            .then(response => convertDateStringsToDates(response) as DtoAsAutorizacion)
            .catch(error => null);
    }

    horasTarde(bean: DtoAsAutorizacion): Promise<DtoAsAutorizacion> {
        bean = this.setearHoras(bean);
        return this.config.getHttp().put(this.url + 'horasTarde', bean)
            .toPromise()
            .then(response => convertDateStringsToDates(response) as DtoAsAutorizacion)
            .catch(error => null);
    }
    insertarHorario(bean: DtoAsAutorizacion, accion: string): Promise<DtoAsAutorizacion> {
        bean = this.setearHoras(bean);
        return this.config.getHttp().put(this.url + 'insertarHorario' + `/` + accion, bean)
            .toPromise()
            .then(response => convertDateStringsToDates(response) as DtoAsAutorizacion)
            .catch(error => null);
    }
    public validarAntesGuardar(bean: DtoAsAutorizacion): Promise<DtoAsAutorizacion> {
        bean = this.setearHoras(bean);
        return this.config.getHttp().put(`${this.url}validarAntesGuardar`, bean)
            .toPromise()
            .then(response => {
                return convertDateStringsToDates(response) as DtoAsAutorizacion;
            })
            .catch(error => null);
    }

    obtenerInformacionParaPermisoEditar(bean: DtoAsAutorizacion): Promise<DtoAsAutorizacion> {
        console.log('solicitudObtenerPorUuid');
        return this.config.getHttp().post(this.url + 'solicitudObtenerPorUuid', bean)
            .toPromise()
            .then(response => convertDateStringsToDates(response) as DtoAsAutorizacion)
            .catch(error => null);
    }


    public validar(bean: DtoAsAutorizacion, accion: string, tieneAccesoSysAdm: string): Promise<DtoAsAutorizacion> {
        bean = this.setearHoras(bean);
        return this.config.getHttp().put(`${this.url}validar` + `/` + accion + `/` + tieneAccesoSysAdm, bean)
            .toPromise()
            .then(response => {
                return convertDateStringsToDates(response) as DtoAsAutorizacion;
            })
            .catch(error => null);
    }

    public solicitudNuevo(autorizacion: DtoAsAutorizacion, isAdmin: string): Promise<DtoAsAutorizacion> {
        autorizacion = this.setearHoras(autorizacion);
        autorizacion.isAdmin=isAdmin;
        return this.config.getHttp().post(this.url + 'solicitudNuevo', autorizacion)
            .toPromise()
            .then(response => {
                return convertDateStringsToDates(response) as DtoAsAutorizacion;
            })
            .catch(error => null);
    }

    registrar(bean: DtoAsAutorizacion): Promise<DtoAsAutorizacion> {
        bean = this.setearHoras(bean);
        return this.config.getHttp().post(this.url + 'registrar', bean)
            .toPromise()
            .then(response => response as DtoAsAutorizacion)
            .catch(error => null);
    }


    downloadFile(numeroProceso: number): Observable<Blob> {
        const params = new HttpParams()
            .set('numeroProceso', JSON.stringify(numeroProceso));

        return this.config.getHttp().get<Blob>(`${this.url}download`,
            { params, responseType: 'blob' as 'json' });
    }


    public itemchanged(autorizacion: DtoAsAutorizacion): Promise<DtoAsAutorizacion> {
        return this.config.getHttp().put(this.url + 'itemchanged', autorizacion)
            .toPromise()
            .then(response => {
                return convertDateStringsToDates(response) as DtoAsAutorizacion;
            })
            .catch(error => null);
    }

}