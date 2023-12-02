import { WfTransaccionComunicacion } from './../dominio/dto/WfTransaccionComunicacion';
import { AppConfig } from './../../../../../environments/appconfig';
import { DtoTabla } from './../../../framework/modelo/generico/dto/DtoTabla';
import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable()
export class WfTransaccionComunicacionServicio {

    private url = `${this.config.getEnv('spring-framework-workflow-api')}/spring/workflow/wftransaccioncomunicacion/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public obtenerEmisores(transaccion: number): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        var dto=new DtoTabla();
        dto.id=transaccion;
        return this.http.put(this.url + 'obtenerEmisores' , dto)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerConversacionesXEmisor(transaccion: number, emisor: number): Promise<WfTransaccionComunicacion[]> {
        //GET CON CUERPO ACT
        var dto=new DtoTabla();
        dto.id=transaccion;
        dto.codigo=emisor+'';
        return this.http.put(this.url + 'obtenerConversacionesXEmisor' , dto)
            .toPromise()
            .then(response => response as WfTransaccionComunicacion[])
            .catch(error => []);
    }

    public enviarMensaje(bean: WfTransaccionComunicacion): Promise<WfTransaccionComunicacion> {
        bean.origen = 'PORTAL';
        return this.http.post(this.url + 'enviarMensaje', bean)
            .toPromise()
            .then(response => response as WfTransaccionComunicacion)
            .catch(error => new WfTransaccionComunicacion());
    }

    public responderMensaje(bean: WfTransaccionComunicacion): Promise<WfTransaccionComunicacion> {
        return this.http.post(this.url + 'responderMensaje', bean)
            .toPromise()
            .then(response => response as WfTransaccionComunicacion)
            .catch(error => new WfTransaccionComunicacion());
    }

    public verAdjunto(bean: WfTransaccionComunicacion): Promise<DtoTabla> {
        return this.http.post(this.url + 'verAdjunto', bean)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }
}
