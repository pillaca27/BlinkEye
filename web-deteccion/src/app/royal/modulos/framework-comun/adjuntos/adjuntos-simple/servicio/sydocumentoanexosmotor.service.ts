import { Observable } from 'rxjs';
import { DtoComunSyDocumentoanexos } from './../dominio/DtoComunSyDocumentoanexos';
import { DtoComunSyDocumentoCabecera } from './../dominio/DtoComunSyDocumentoCabecera';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '@env/appconfig';

@Injectable()
export class SyDocumentoanexosMotorService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/sistema/sydocumentoanexosmotor/`;
    constructor(private http: HttpClient, private config: AppConfig) { }
    
    
    public listarporcabecera(filtro: DtoComunSyDocumentoCabecera): Promise<DtoComunSyDocumentoanexos[]> {
        return this.http.put(this.url + 'listarporcabecera', filtro)
            .toPromise()
            .then(response => response as DtoComunSyDocumentoanexos[])
            .catch(error => []);
    }

    public listarporcabeceraWh(filtro: DtoComunSyDocumentoCabecera): Promise<DtoComunSyDocumentoanexos[]> {
        return this.http.post(this.url + 'listarporcabeceraWh', filtro)
            .toPromise()
            .then(response => response as DtoComunSyDocumentoanexos[])
            .catch(error => []);
    }

    public registrartemporal(dto: DtoComunSyDocumentoanexos): Promise<DtoComunSyDocumentoanexos> {
        return this.http.post(this.url + 'registrartemporal', dto)
            .toPromise()
            .then(response => response as DtoComunSyDocumentoanexos)
            .catch(error => null);
    }
    
    public actualizarTemporal(dto: DtoComunSyDocumentoanexos): Promise<DtoComunSyDocumentoanexos> {
        return this.http.put(this.url + 'actualizarTemporal', dto)
            .toPromise()
            .then(response => response as DtoComunSyDocumentoanexos)
            .catch(error => null);
    }

    
    public eliminartemporal(dto: DtoComunSyDocumentoanexos): Promise<DtoComunSyDocumentoanexos> {
        return this.http.put(this.url + 'eliminartemporal', dto)
            .toPromise()
            .then(response => response as DtoComunSyDocumentoanexos)
            .catch(error => null);
    }
    
    public eliminartemporalMasivo(dto: DtoComunSyDocumentoanexos[]): Promise<DtoComunSyDocumentoanexos[]> {
        return this.http.put(this.url + 'eliminartemporalMasivo', dto)
            .toPromise()
            .then(response => response as DtoComunSyDocumentoanexos[])
            .catch(error => []);
    }

    descargarPrueba( ): Observable<Blob> {
        return this.config.getHttp().put<Blob>(this.url + 'descargarPrueba', null , {responseType: 'blob' as 'json' });
    }

    descargar(dto: DtoComunSyDocumentoanexos ): Observable<Blob> {
        return this.config.getHttp().put<Blob>(this.url + 'descargar', dto , {responseType: 'blob' as 'json' });
    }

    descargarOrdenCompra(dto: DtoComunSyDocumentoanexos ): Observable<Blob> {
        return this.config.getHttp().put<Blob>(this.url + 'descargarOrdenCompra', dto , {responseType: 'blob' as 'json' });
    }

    descargarplantilla(dto: DtoComunSyDocumentoanexos ): Observable<Blob> {
        return this.config.getHttp().put<Blob>(this.url + 'descargarplantilla', dto , {responseType: 'blob' as 'json' });
    }
    
	
    public procesaradjuntos(dto: DtoComunSyDocumentoCabecera): Promise<DtoComunSyDocumentoCabecera> {
        return this.http.put(this.url + 'procesaradjuntos', dto)
            .toPromise()
            .then(response => response as DtoComunSyDocumentoCabecera)
            .catch(error => null);
    }


    public validarProcesaradjuntos(dto: DtoComunSyDocumentoCabecera): Promise<DtoComunSyDocumentoCabecera> {
        return this.http.put(this.url + 'validarProcesaradjuntos', dto)
            .toPromise()
            .then(response => response as DtoComunSyDocumentoCabecera)
            .catch(error => null);
    }

    
    
    public procesaradjuntosoc(dto: DtoComunSyDocumentoCabecera): Promise<DtoComunSyDocumentoCabecera> {
        return this.http.put(this.url + 'procesaradjuntosoc', dto)
            .toPromise()
            .then(response => response as DtoComunSyDocumentoCabecera)
            .catch(error => null);
    }

    public procesaradjuntonot(dto: DtoComunSyDocumentoCabecera): Promise<DtoComunSyDocumentoCabecera> {
        return this.http.put(this.url + 'procesaradjuntonot', dto)
            .toPromise()
            .then(response => response as DtoComunSyDocumentoCabecera)
            .catch(error => null);
    }
    
    public procesaradjuntoscopy(dto: DtoComunSyDocumentoCabecera): Promise<DtoComunSyDocumentoCabecera> {
        return this.http.put(this.url + 'procesaradjuntoscopy', dto)
            .toPromise()
            .then(response => response as DtoComunSyDocumentoCabecera)
            .catch(error => null);
    }

    public procesarReturnDefault(dto: DtoComunSyDocumentoCabecera): Promise<DtoComunSyDocumentoCabecera> {
        return new Promise((resolve, reject) => {
                return dto 
          });
    }

    public validarAdjunto(dto: DtoComunSyDocumentoanexos): Promise<DtoComunSyDocumentoanexos> {
        return this.http.put(this.url + 'validarArchivo', dto)
            .toPromise()
            .then(response => response as DtoComunSyDocumentoanexos)
            .catch(error => null);
    }
    
}