import { DtoTabla } from './../../../../framework/modelo/generico/dto/DtoTabla';
import { DtoSyTipodocumentoproceso } from './../dominio/dto/DtoSyTipodocumentoproceso';
import { DtoSyTipodocumento } from './../dominio/dto/DtoSyTipodocumento';
import { AppConfig } from './../../../../../../environments/appconfig';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { FiltroComunSyTipoDocumento } from '../dominio/filtro/FiltroComunSyTipoDocumento';
import { DominioMensajeUsuario } from '@framework/modelo/generico/DominioMensajeUsuario';

@Injectable({ providedIn: 'root' })
export class SyTipoDocumentoService {
    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/sistema/sytipodocumento/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public obtenerdtoporid(dto: DtoSyTipodocumento): Promise<DtoSyTipodocumento> {
        return this.http.put(this.url + 'obtenerdtoporid', dto)
            .toPromise()
            .then(response => response as DtoSyTipodocumento)
            .catch(error => null);
    }

    public registrar(dto: DtoSyTipodocumento): Promise<DtoSyTipodocumento> {
        return this.http.post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoSyTipodocumento)
            .catch(error => null);
    }

    public actualizar(dto: DtoSyTipodocumento): Promise<DtoSyTipodocumento> {
        return this.http.put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoSyTipodocumento)
            .catch(error => null);
    }

    public listarpaginado(filtro: FiltroComunSyTipoDocumento): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public anular(dto: DtoSyTipodocumento): Promise<DtoSyTipodocumento> {
        return this.http.put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoSyTipodocumento)
            .catch(error => null);
    }

    public eliminar(dto: DtoSyTipodocumento): Promise<DtoSyTipodocumento> {
        return this.http.put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoSyTipodocumento)
            .catch(error => null);
    }

    public listarDtoPorHeader(tipodocumentoid: string): Promise<DtoSyTipodocumentoproceso[]> {
        var dto = new DtoSyTipodocumentoproceso();
        dto.tipodocumentoid = tipodocumentoid;
        return this.config.getHttp().put(this.url + 'listardtoporheader', dto)
            .toPromise()
            .then(response => response as DtoSyTipodocumentoproceso[])
            .catch(error => []);
    }

    public listarProcesos(aplicacion: string): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = aplicacion;
        return this.http.put(this.url + 'listarProcesos' ,  dto)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public validar(dto: DtoSyTipodocumento, accion: string): Promise<DominioMensajeUsuario[]> {
        return this.config.getHttp().put(this.url + 'validar/' + accion, dto)
            .toPromise()
            .then(response => response as DominioMensajeUsuario[])
            .catch(error => []);
    }


}