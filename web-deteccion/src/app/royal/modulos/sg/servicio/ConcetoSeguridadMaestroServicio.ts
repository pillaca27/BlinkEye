import { FiltroPaginacionAutorizacionConcepto } from './../dominio/filtro/FiltroPaginacionAutorizacionConcepto';
import { Seguridadgrupo } from './../dominio/dto/Seguridadgrupo';
import { FiltroPaginacionSeguridadGrupo } from './../dominio/filtro/FiltroPaginacionSeguridadGrupo';
import { FiltroPaginacionAplicacionesMast } from './../dominio/filtro/FiltroPaginacionAplicacionesMast';
import { MensajesBean } from './../dominio/dto/MensajesBean';
import { ConceptoSendDto } from './../dominio/dto/ConceptoSendDto';
import { DtoAutorizacionFuncionListado } from './../dominio/dto/DtoAutorizacionFuncionListado';
import { DtoAutorizacionConceptoListado } from './../dominio/dto/DtoAutorizacionConceptoListado';
import { FiltroPaginacionSeguridadConcepto } from './../dominio/filtro/FiltroPaginacionSeguridadConcepto';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';

@Injectable()
export class ConcetoSeguridadMaestroServicio {
    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/seguridad/seguridadgrupo`;
    private urlConcepto = `${this.config.getEnv('spring-framework-comun-api')}spring/seguridad/seguridadconcepto`;
    private urlAutotizacion = `${this.config.getEnv('spring-erp-sg-api')}spring/seguridad/seguridadautorizaciones`;
    private urlAutotizacionConcepto = `${this.config.getEnv('spring-erp-sg-api')}spring/sg/syseguridadautorizaciones`; 
    private urlalternaseguridad = `${this.config.getEnv('spring-erp-sg-api')}spring/sy/syseguridadgrupo`;
    private urlsyseguridadconcepto = `${this.config.getEnv('spring-erp-sg-api')}spring/sy/syseguridadconcepto`;

    private urlAplicaiones = `${this.config.getEnv('spring-framework-comun-api')}spring/seguridad/aplicacionesmast`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public listarAplicacionesMast(filtro: FiltroPaginacionAplicacionesMast) {
        let urlTemp = this.urlAplicaiones + "/listarcombotemp";
        return this.http.post(urlTemp, filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }
 

    public exportargarosgenerico(filtro: FiltroPaginacionSeguridadGrupo): Observable<Blob> {
        return this.config.getHttp().put<Blob>(`${this.url}/exportarGenerico`, filtro,
            { responseType: 'blob' as 'json' });
    }

    public listarcombodetalletipo(filtro: FiltroPaginacionAplicacionesMast) {
        let urlTemp = this.urlAplicaiones + "/listarcombotipodet";
        return this.http.post(urlTemp, filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public eliminargrupos(aplicacioncodigo: String, grupo: String, concepto: String): Promise<Seguridadgrupo> {
        let urlTemp = this.url + '/eliminar/' + aplicacioncodigo + "/" + grupo + "/" + concepto;
        return this.http.delete(urlTemp)
            .toPromise()
            .then(response => (response as Seguridadgrupo))
            .catch(error => null);
    }

    public eliminargruposSys(aplicacioncodigo: String, grupo: String): Promise<Seguridadgrupo> {
        let urlTemp = this.urlalternaseguridad + '/eliminar/' + aplicacioncodigo + "/" + grupo;
        return this.http.delete(urlTemp)
            .toPromise()
            .then(response => (response as Seguridadgrupo))
            .catch(error => null);
    }

    public listar(filtro: FiltroPaginacionSeguridadGrupo) {
        let urlTemp = this.url + "/listar";
        return this.http.post(urlTemp, filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listarConceptos(filtro: FiltroPaginacionSeguridadConcepto) {
        let urlTemp = this.urlConcepto + "/listarConceptos";
        return this.http.post(urlTemp, filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }




    public exportarconceptosseguridad(filtro: FiltroPaginacionSeguridadConcepto): Observable<Blob> {
        return this.config.getHttp().put<Blob>(`${this.urlConcepto}/exportarconceptosseguridad`, filtro,
            { responseType: 'blob' as 'json' });
    }
 

    public listarFunciones(filtro: FiltroPaginacionAutorizacionConcepto) {
        let urlTemp = this.urlAutotizacion + "/listarFunciones";
        return this.http.post(urlTemp, filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listarreportes(filtro: FiltroPaginacionAutorizacionConcepto) {
        let urlTemp = this.urlAutotizacion + "/listarreportesseguridad";
        return this.http.post(urlTemp, filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }



    public listarAutoConceptos(filtro: FiltroPaginacionAutorizacionConcepto) {
        let urlTemp = this.urlAutotizacion + "/listarConceptos";
        return this.http.post(urlTemp, filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }


    public reqObj(listSend: ConceptoSendDto[]) {
        let urlTemp = this.url + "/registrarList";
        return this.http.post(urlTemp, listSend)
            .toPromise()
            .then(response => response as ConceptoSendDto)
            .catch(error => new ConceptoSendDto());
    }


    public eliminarConceptos(aplicacioncodigo: String, grupo: String, concepto: String): Promise<MensajesBean> {
        let urlTemp = this.urlConcepto + '/eliminar/' + aplicacioncodigo + "/" + grupo + "/" + concepto;
        return this.http.delete(urlTemp)
            .toPromise()
            .then(response => (response as MensajesBean))
            .catch(error => null);
    }

    public eliminarConceptosalternos(aplicacioncodigo: String, grupo: String, concepto: String): Promise<MensajesBean> {
        let urlTemp = this.urlsyseguridadconcepto + '/eliminar/' + aplicacioncodigo + "/" + grupo + "/" + concepto;
        return this.http.delete(urlTemp)
            .toPromise()
            .then(response => (response as MensajesBean))
            .catch(error => null);
    }

    public reqObjUpdate(listSend: ConceptoSendDto[]) {
        let urlTemp = this.url + "/updateList";
        return this.http.post(urlTemp, listSend)
            .toPromise()
            .then(response => response as ConceptoSendDto)
            .catch(error => new ConceptoSendDto());
    }

    public updateListConcepto(listSend: ConceptoSendDto[]) {
       let urlTemp = this.urlsyseguridadconcepto + "/updateListConcepto";
        return this.http.post(urlTemp, listSend)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public registrarListConcepto(listSend: ConceptoSendDto[]) {
        let urlTemp = this.urlsyseguridadconcepto + "/registrarListConcepto";
        return this.http.post(urlTemp, listSend)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public reqObjUpdateFunciones(listSend: DtoAutorizacionFuncionListado) {
        let urlTemp = this.urlAutotizacion + "/updateList";
        return this.http.post(urlTemp, listSend)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }


    public reqObjUpdateAutoConceptos(listSend: DtoAutorizacionConceptoListado) {
        let urlTemp = this.urlAutotizacionConcepto + "/updateListConcepto";
        //let urlTemp = this.urlAutotizacion + "/updateListConcepto";
        return this.http.post(urlTemp, listSend)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public reqObjUpdateAutoReportes(listSend: DtoAutorizacionConceptoListado) {
        let urlTemp = this.urlAutotizacionConcepto + "/updateListReportes";
        return this.http.post(urlTemp, listSend)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }


    public obtenerPorId(aplicacioncodigo: String, grupo: String): Promise<Seguridadgrupo> {
        let urlTemp = this.url + "/obtenerporid/" + aplicacioncodigo + "/" + grupo;
        return this.http.get(urlTemp)
            .toPromise()
            .then(response => response as Seguridadgrupo)
            .catch(error => null);
    }

    // public obtenerPorId(usuario: String): Promise<Usuario> {
    //     let urlTemp = this.url + "/obtenerporid/" + usuario;
    //     return this.http.get(urlTemp)
    //         .toPromise()
    //         .then(response => response as Usuario)
    //         .catch(error => null);
    // }

    // public listarSeguridadPerfilUsuario(filtro: FiltroPaginacionPerfilUsuario) {
    //     let urlTemp = this.urlTempV2 + "/listar";
    //     return this.http.post(urlTemp, filtro)
    //         .toPromise()
    //         .then(response => response as ParametroPaginacionGenerico)
    //         .catch(error => new ParametroPaginacionGenerico());
    // }

    // public registrar(bean: Usuario): Promise<Usuario> {
    //     return this.http.post(this.url + '/registrar', bean)
    //         .toPromise()
    //         .then(response => (response as Usuario))
    //         .catch(error => null);
    // }

    // public actualizar(bean: Usuario): Promise<Usuario> {
    //     return this.http.put(this.url + '/actualizar', bean)
    //         .toPromise()
    //         .then(response => (response as Usuario))
    //         .catch(error => null);
    // }

    // public eliminar(pk: UsuarioPk): Promise<UsuarioPk> {
    //     let urlTemp = this.url + '/eliminar' + pk.usuario; 
    //     return this.http.delete(urlTemp)
    //         .toPromise()
    //         .then(response => (response as UsuarioPk))
    //         .catch(error => null);
    // }

    // public registrarPerfil(bean: Seguridadperfilusuario): Promise<Seguridadperfilusuario> {
    //     return this.http.post(this.url + '/registrar', bean)
    //         .toPromise()
    //         .then(response => (response as Seguridadperfilusuario))
    //         .catch(error => null);
    // }

    // public desactivarPerfil(bean: Seguridadperfilusuario): Promise<Seguridadperfilusuario> {
    //     return this.http.post(this.url + '/modificar', bean)
    //         .toPromise()
    //         .then(response => (response as Seguridadperfilusuario))
    //         .catch(error => null);
    // }

    // public listarAgGePersona(filtro: FiltroPaginacionAgGePersona) {
    //     let urlTemp = this.urlTempV3 + "/listar";
    //     return this.http.post(urlTemp, filtro)
    //         .toPromise()
    //         .then(response => response as ParametroPaginacionGenerico)
    //         .catch(error => new ParametroPaginacionGenerico());
    // }

    // public obtenerParametro() {
    //     return this.http.post(urlTemp, filtro)
    //         .toPromise()
    //         .then(response => response as ParametroPaginacionGenerico)
    //         .catch(error => new ParametroPaginacionGenerico());
    // }


    // SEGURIDAD AUTORIZACIONES EXPORTAR FUNCIONES 
    public exportarfunciones(filtro: FiltroPaginacionAutorizacionConcepto): Observable<Blob> {
        return this.config.getHttp().put<Blob>(`${this.urlAutotizacion}/exportarfunciones`, filtro,
            { responseType: 'blob' as 'json' });
    }

    // SEGURIDAD AUTORIZACIONES EXPORTAR CONCEPTO 
    public exportarconcepto(filtro: FiltroPaginacionAutorizacionConcepto): Observable<Blob> {
        return this.config.getHttp().put<Blob>(`${this.urlAutotizacion}/exportarconcepto`, filtro,
            { responseType: 'blob' as 'json' });
    }

    // SEGURIDAD AUTORIZACIONES EXPORTAR REPORTE 
    public exportarreporte(filtro: FiltroPaginacionAutorizacionConcepto): Observable<Blob> {
        return this.config.getHttp().put<Blob>(`${this.urlAutotizacion}/exportarreporte`, filtro,
            { responseType: 'blob' as 'json' });
    }
}