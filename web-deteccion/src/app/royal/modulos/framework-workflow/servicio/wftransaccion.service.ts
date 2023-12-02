import { DtoNotificacionMasiva } from './../dominio/dto/DtoNotificacionMasiva';
import { WorkFlowTransaccion } from './../dominio/dto/WorkFlowTransaccion';
import { DtoTransaccionCorreoListadoHeader } from './../dominio/dto/DtoTransaccionCorreoListadoHeader';
import { DtoTransaccionCorreoFiltros } from './../dominio/dto/DtoTransaccionCorreoFiltros';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AppConfig } from '@env/appconfig';
import { FiltroSolicitudes } from './../dominio/filtro/FiltroSolicitudes';
import { DtoFlujoEjecutar } from './../dominio/dto/DtoFlujoEjecutar';
import { FiltroTransaccion } from './../dominio/filtro/FiltroTransaccion';
import { DtoAprobacionAcciones } from './../dominio/dto/DtoAprobacionAcciones';
import { DtoSeguimiento } from './../dominio/dto/DtoSeguimiento';
import { DtoFlujoAdjunto, DtoFlujoTransaccionRequest } from './../dominio/dto/DtoFlujoTransaccionRequest';
import { WfTransaccion, WfTransaccionPk } from './../dominio/dto/WfTransaccion';
import { DtoFlujoTransaccionResponse } from './../dominio/dto/DtoFlujoTransaccionResponse';
import { DtoTabla } from 'src/app/royal/framework/modelo/generico/dto/DtoTabla';
import { DominioPaginacion } from 'src/app/royal/framework/modelo/generico/DominioPaginacion';
import { DominioMensajeUsuario } from 'src/app/royal/framework/modelo/generico/DominioMensajeUsuario';
import { DtoTransaccionVistaAvanzada } from './../dominio/dto/DtoTransaccionVistaAvanzada';
import { DtoSeguimientoVistaAvanzada } from './../dominio/dto/DtoSeguimientoVistaAvanzada';
import { DtoWfFlujoNivel } from './../dominio/dto/DtoWfFlujoNivel';
import { EmailTransaccion } from '@framework/modelo/correo/EmailTransaccion';
import { FiltroSyDocumentos } from './../dominio/filtro/FiltroSyDocumentos';
import { Observable } from 'rxjs';
import { DtoJerarquiaMacroProceso } from './../dominio/dto/DtoJerarquiaMacroProceso';

@Injectable()
export class WfTransaccionServicio {

    private rutaServicio = `${this.config.getEnv('spring-framework-workflow-api')}spring/workflow/wftransaccion/`;
    private rutaServicio2 = `${this.config.getEnv('spring-framework-workflow-api')}spring/workflow/wfproceso/`;

    constructor(private http: HttpClient, private config: AppConfig) { }

    public transaccionListarAprobacion(filtro: FiltroSolicitudes): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'transaccionListarAprobacion', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }


    public obtenerCabecera(uuid: string): Promise<DtoFlujoAdjunto[]> {
        var dto = new DtoTabla();
        dto.codigo = uuid;
        return this.http.post(this.rutaServicio + 'obtenerCabecera', dto)
            .toPromise()
            .then(response => response as DtoFlujoAdjunto[])
            .catch(error => []);
    }
    public obtenerPorId(uuid: string): Promise<WfTransaccion> {
        var dto = new DtoTabla();
        dto.codigo = uuid;
        return this.http.post(this.rutaServicio + 'obtenerPorId', dto)
            .toPromise()
            .then(response => response as WfTransaccion)
            .catch(error => null);
    }

    public transaccionIniciar(dto: DtoFlujoTransaccionRequest): Promise<DtoFlujoTransaccionResponse> {
        return this.http.post(this.rutaServicio + 'transaccionIniciar', dto)
            .toPromise()
            .then(response => response as DtoFlujoTransaccionResponse)
            .catch(error => null);
    }

    public transaccionEjecutar(flujos: DtoFlujoEjecutar): Promise<DominioMensajeUsuario[]> {
        return this.http.post(this.rutaServicio + 'transaccionEjecutar', flujos)
            .toPromise()
            .then(response => response as DominioMensajeUsuario[])
            .catch(error => []);
    }

    public transaccionListarSolicitante(filtro: FiltroTransaccion): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'transaccionListarSolicitante', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public obtenerAccionesAprobacion(bean: string): Promise<DtoAprobacionAcciones> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = bean;
        return this.http.put(this.rutaServicio + 'obtenerAccionesAprobacion', dto)
            .toPromise()
            .then(response => response as DtoAprobacionAcciones)
            .catch(error => new DtoAprobacionAcciones());
    }

    public obtenerAdjuntosAprobacion(uuid: string): Promise<DtoFlujoAdjunto[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = uuid;
        return this.http.put(this.rutaServicio + 'obtenerAdjuntosAprobacion', dto)
            .toPromise()
            .then(response => response as DtoFlujoAdjunto[])
            .catch(error => []);
    }

    public listarSeguimientoWF(bean: number): Promise<DtoSeguimiento[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.id = bean;
        return this.http.put(this.rutaServicio + 'listarSeguimientoWF', dto)
            .toPromise()
            .then(response => response as DtoSeguimiento[])
            .catch(error => []);
    }

    public registrarAdjunto(bean: DtoFlujoAdjunto): Promise<DtoFlujoAdjunto> {
        return this.http.post(this.rutaServicio + 'registrarAdjunto', bean)
            .toPromise()
            .then(response => response as DtoFlujoAdjunto)
            .catch(error => null);
    }

    public actualizarAdjunto(bean: DtoFlujoAdjunto): Promise<DtoFlujoAdjunto> {
        return this.http.post(this.rutaServicio + 'actualizarAdjunto', bean)
            .toPromise()
            .then(response => response as DtoFlujoAdjunto)
            .catch(error => null);
    }

    public eliminarAdjunto(pk: DtoFlujoAdjunto): Promise<DtoFlujoAdjunto> {
        return this.http.post(this.rutaServicio + 'eliminarAdjunto', pk)
            .toPromise()
            .then(response => response as DtoFlujoAdjunto)
            .catch(error => new DtoFlujoAdjunto());
    }


    public verAdjunto(pk: DtoFlujoAdjunto): Promise<DtoFlujoAdjunto> {
        return this.http.post(this.rutaServicio + 'verAdjunto', pk)
            .toPromise()
            .then(response => response as DtoFlujoAdjunto)
            .catch(error => new DtoFlujoAdjunto());
    }

    public verPlantilla(pk: DtoFlujoAdjunto): Promise<DtoFlujoAdjunto> {
        return this.http.post(this.rutaServicio + 'verPlantilla', pk)
            .toPromise()
            .then(response => response as DtoFlujoAdjunto)
            .catch(error => new DtoFlujoAdjunto());
    }

    public listarDocumentoRequeridos(bean: string): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = bean;
        return this.http.put(this.rutaServicio + 'listarDocumentoRequeridos', dto)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarDocumentoTodos(bean: number): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.id = bean;
        return this.http.put(this.rutaServicio + 'listarDocumentoTodos', dto)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }



    public escribirImagenPDF(doc: DtoFlujoAdjunto): Promise<DtoTabla> {
        return this.http.post(this.rutaServicio + 'escribirImagenPDF', doc)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => null);
    }

    public consultarFinFirma(idFirma: number, transaccionUUID: string): Promise<any[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = idFirma + '';
        dto.descripcion = transaccionUUID;

        return this.http.put(this.rutaServicio + 'consultarFinFirma', dto)
            .toPromise()
            .then(response => response as any[])
            .catch(error => []);
    }

    public firmar(docs: DtoFlujoAdjunto[]): Promise<DtoTabla> {
        return this.http.post(this.rutaServicio + 'firmar', docs)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => null);
    }

    public listarAplicacionPorUsuario(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarAplicacionPorUsuario')
            .toPromise()
            .then(response => {
                return response as DtoTabla[];
            })
            .catch(error => []);
    }
    public listarMiscelaneosActivos(aplicacionCodigo: string, codigoTabla: string): Promise<DtoTabla[]> {
        const params = new HttpParams().set('aplicacionCodigo', aplicacionCodigo)
            .set('codigoTabla', codigoTabla);
        //GET CON CUERPO ACT

        var dto = new DtoTabla();
        dto.codigo = codigoTabla;
        dto.descripcion = aplicacionCodigo;

        return this.http.put(this.rutaServicio + 'listarMiscelaneosActivos', dto)
            .toPromise()
            .then(response => {
                return response as DtoTabla[];
            })
            .catch(error => []);
    }
    public listarCompaniasActivas(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarCompaniasActivas')
            .toPromise()
            .then(response => {
                return response as DtoTabla[];
            })
            .catch(error => []);
    }
    filtrarEmpleados(busqueda: string): Promise<DtoTabla[]> {
        const params = new HttpParams().set('busqueda', busqueda);
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = busqueda;
        return this.http.put(this.rutaServicio + 'filtrarEmpleados', dto)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public test0001(referencia: string): Promise<any> {
        //METODO DE PRUEBAS
        var dto = new DtoTabla();
        dto.codigo = referencia;
        return this.http.put(this.rutaServicio + 'test0001', dto)
            .toPromise()
            .then(response => response)
            .catch(error => null);
    }

    public listarOrigenes(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarOrigenes')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarSeguimientoGeneralWF(bean: string): Promise<DtoSeguimiento[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = bean;
        return this.http.put(this.rutaServicio + 'listarSeguimientoGeneralWF', dto)
            .toPromise()
            .then(response => response as DtoSeguimiento[])
            .catch(error => []);
    }

    public obtenerLlaveParaSyDocumento(bean: string): Promise<DtoFlujoAdjunto> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = bean;
        return this.http.put(this.rutaServicio + 'obtenerLlaveParaSyDocumento', dto)
            .toPromise()
            .then(response => response as DtoFlujoAdjunto)
            .catch(error => new DtoFlujoAdjunto());
    }

    public listarPlanificacion(transaccionid: number): Promise<any> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.id = transaccionid;
        return this.http.put(this.rutaServicio + 'listarPlanificacion', dto)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }

    public actualizarPlanificacion(dto: any): Promise<any> {
        return this.http.post(this.rutaServicio + 'actualizarPlanificacion', dto)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }

    public obtenerProyeccionPlanificacion(request: WorkFlowTransaccion): Promise<any> {
        return this.http.post(this.rutaServicio + 'obtenerProyeccionPlanificacion', request)
            .toPromise()
            .then(response => response as WorkFlowTransaccion)
            .catch(error => null);
    }

    public obtenerAdjuntosSoloLectura(uuid: string): Promise<DtoFlujoAdjunto[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = uuid;
        return this.http.put(this.rutaServicio + 'obtenerAdjuntosSoloLectura', dto)
            .toPromise()
            .then(response => response as DtoFlujoAdjunto[])
            .catch(error => []);
    }

    public transaccionListarAdministrador(filtro: FiltroTransaccion): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'transaccionListarAdministrador', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public obtenerVistaAvanzada(uuid: string): Promise<DtoTransaccionVistaAvanzada> {
        var dto = new DtoTabla();
        dto.codigo = uuid;
        return this.http.post(this.rutaServicio + 'obtenerVistaAvanzada', dto)
            .toPromise()
            .then(response => response as DtoTransaccionVistaAvanzada)
            .catch(error => null);
    }

    public obtenerSeguimientoVistaAvanzada(uuid: string): Promise<DtoSeguimientoVistaAvanzada[]> {
        var dto = new DtoTabla();
        dto.codigo = uuid;
        return this.http.post(this.rutaServicio + 'obtenerSeguimientoVistaAvanzada', dto)
            .toPromise()
            .then(response => response as DtoSeguimientoVistaAvanzada[])
            .catch(error => []);
    }

    public obtenerConfiguracionObservaciones(uuid: string): Promise<DtoWfFlujoNivel> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = uuid;
        return this.http.put(this.rutaServicio + 'obtenerConfiguracionObservaciones', dto)
            .toPromise()
            .then(response => response as DtoWfFlujoNivel)
            .catch(error => new DtoWfFlujoNivel());
    }

    public obtenerCorreoObservacion(transaccionUUID: string, accionWf: string): Promise<EmailTransaccion> {
        //GET CON CUERPO ACT

        var dto = new DtoTabla();
        dto.descripcion = transaccionUUID;
        dto.codigo = accionWf;

        return this.http.put(this.rutaServicio + 'obtenerCorreoObservacion', dto)
            .toPromise()
            .then(response => response as EmailTransaccion)
            .catch(error => new EmailTransaccion());
    }

    public obtenerMetadatos(transaccionid: number): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.id = transaccionid;
        return this.http.put(this.rutaServicio + 'obtenerMetadatos', dto)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerTitleWF(transaccionid: string): Promise<DtoTabla> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = transaccionid;
        return this.http.put(this.rutaServicio + 'obtenerTitleWF', dto)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }

    public listarSyTipoDocumentos(filtro: FiltroSyDocumentos): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio2 + 'listarSyTipoDocumentos', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }
    public sytipodocumento(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio2 + 'sytipodocumento')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerTransaccionAprobadorVistaAvanzada(uuid: string): Promise<DtoTabla[]> {
        var dto = new DtoTabla();
        dto.codigo = uuid;
        return this.http.post(this.rutaServicio + 'obtenerTransaccionAprobadorVistaAvanzada', dto)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public registrarNotificacionMasiva(dto: DtoNotificacionMasiva): Promise<DtoTabla> {
        return this.http.post(this.rutaServicio + 'registrarNotificacionMasiva', dto)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }

    public obtenerConfiguracionDocumentosVistaAvanzada(uuid: string): Promise<any[]> {
        var dto = new DtoTabla();
        dto.codigo = uuid;
        return this.http.post(this.rutaServicio + 'obtenerConfiguracionDocumentosVistaAvanzada', dto)
            .toPromise()
            .then(response => response as any[])
            .catch(error => []);
    }

    cambiarNivel(transaccion: number, nivel: number): Promise<DtoTabla> {
        var dto = new DtoTabla();
        dto.codigo = transaccion + '';
        dto.id = nivel;
        return this.config.getHttp().put(this.rutaServicio + 'cambiarNivel', dto)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }


    public listarProcesoCorreos(dto: DtoTransaccionCorreoFiltros): Promise<DtoTransaccionCorreoListadoHeader> {
        return this.config.getHttp().put(this.rutaServicio + 'listarProcesoCorreos', dto)
            .toPromise()
            .then(response => response as DtoTransaccionCorreoListadoHeader)
            .catch(error => new DtoTransaccionCorreoListadoHeader());
    }


    public exportarProcesoCorreos(filtro: DtoTransaccionCorreoFiltros): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.rutaServicio}/exportarProcesoCorreos`, filtro,
            { responseType: 'blob' as 'json' });
    }

    public listarJerarquiaMacroProceso(transaccionUUID: string): Promise<DtoJerarquiaMacroProceso[]> {
        var pk = new DtoTabla();
        pk.codigo = transaccionUUID;
        return this.http.post(this.rutaServicio + 'listarJerarquiaMacroProceso', pk)
            .toPromise()
            .then(response => response as DtoJerarquiaMacroProceso[])
            .catch(error => []);
    }
}
