import { FiltroComunPersonamastListaRapidaWf } from './../dominio/filtro/FiltroComunPersonamastListaRapidaWf';
import { PersonamastTransaccion } from './../../../framework/modelo/PersonamastTransaccion';
import { FiltroComunPersonamastclis001Wf } from './../dominio/filtro/FiltroComunPersonamastclis001Wf';
import { DominioPaginacion } from './../../../framework/modelo/generico/DominioPaginacion';
import { AppConfig } from './../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable()
export class WfPersonamastComunService {

    private url = `${this.config.getEnv('spring-framework-workflow-api')}spring/comun/wfpersonamastcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    obtenerDto(persona: number): Promise<any> {
        var dtoempleado = { persona: persona };
        return this.http.put(this.url + 'obtenerdto', dtoempleado)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //99-PERSON-COBTAPI
    obtenerPersonaPorDtoApi(persona: number): Promise<PersonamastTransaccion> {
        var dtoempleado = new PersonamastTransaccion();
        dtoempleado.persona = persona;
        return this.http.put(this.url + 'obtenerpersonapordtoapi', dtoempleado)
            .toPromise()
            .then(response => response as PersonamastTransaccion)
            .catch(error => new PersonamastTransaccion());
    }

    //QQUECHOD VALIDADO
    //99-PERSON-CLIS001
    clis001(filtro: FiltroComunPersonamastclis001Wf): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'clis001', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    //QQUECHOD VALIDADO
    //99-PERSON-CLISRAP
    listaRapida(busqueda: string, estado: string): Promise<PersonamastTransaccion[]> {
        var filtro = new FiltroComunPersonamastListaRapidaWf();
        filtro.busqueda = busqueda;
        filtro.estado = estado;
        return this.http.put(this.url + 'listarapida', filtro)
            .toPromise()
            .then(response => response as PersonamastTransaccion[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-PERSON-CLISRAP
    listaRapidaActivos(busqueda: string): Promise<PersonamastTransaccion[]> {
        var filtro = new FiltroComunPersonamastListaRapidaWf();
        filtro.busqueda = busqueda;
        return this.http.put(this.url + 'listarapidaactivos', filtro)
            .toPromise()
            .then(response => response as PersonamastTransaccion[])
            .catch(error => []);
    }


    listarclienteproveedorotro(filtro: FiltroComunPersonamastclis001Wf): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarclienteproveedorotro', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    listargestor(filtro: FiltroComunPersonamastclis001Wf): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listargestor', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    listarTiposProveedor(filtro: FiltroComunPersonamastclis001Wf): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarTiposProveedor', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    listarTiposProveedorCotizacion(filtro: FiltroComunPersonamastclis001Wf): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarTiposProveedorCotizacion', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    listarproveedor(filtro: FiltroComunPersonamastclis001Wf): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarproveedor', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }


    obtenerTipoProveedor(codPersona: number): Promise<PersonamastTransaccion> {
        var filtro: FiltroComunPersonamastclis001Wf = new FiltroComunPersonamastclis001Wf()
        filtro.persona = codPersona;
        return this.http.put(this.url + 'obtenerTipoProveedor', filtro)
            .toPromise()
            .then(response => response as PersonamastTransaccion)
            .catch(error => new PersonamastTransaccion());
    }


}