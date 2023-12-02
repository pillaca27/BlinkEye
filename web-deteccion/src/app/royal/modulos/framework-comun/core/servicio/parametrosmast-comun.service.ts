import { DominioPaginacion } from '../../../../framework/modelo/generico/DominioPaginacion';
import { DtlComunParametrosmast } from '../dominio/lista/DtlComunParametrosmast';
import { FiltroComunParametrosmast } from '../dominio/filtro/FiltroComunParametrosmast';
import { DtoComunParametrosmast } from '../dominio/dto/DtoComunParametrosmast';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Observable } from 'rxjs';
import { ParametroTransaccion } from '@framework/modelo/ParametroTransaccion';

@Injectable()
export class ParametrosmastComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/parametrosmastcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //QQUECHOD VALIDADO
    //99-PARAM-CREGIS
    public registrar(dto: DtoComunParametrosmast): Promise<DtoComunParametrosmast> {
        return this.http.post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunParametrosmast)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //99-PARAM-CACTU
    public actualizar(dto: DtoComunParametrosmast): Promise<DtoComunParametrosmast> {
        return this.http.put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunParametrosmast)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //99-PARAM-CANUL
    public anular(dto: DtoComunParametrosmast): Promise<DtoComunParametrosmast> {
        return this.http.put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunParametrosmast)
            .catch(error => null);
    }
    public anulardtolistado(dto: DtlComunParametrosmast): Promise<DtoComunParametrosmast> {
        var bean=new DtoComunParametrosmast();
        bean.aplicacioncodigo=dto.aplicacioncodigo;
        bean.parametroclave=dto.parametroclave;
        bean.companiacodigo=dto.companiacodigo;
        return this.anular(bean);
    }

    //QQUECHOD VALIDADO
    //99-PARAM-COBTD
    public obtenerDto(dto: DtoComunParametrosmast): Promise<DtoComunParametrosmast> {
        return this.http.put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunParametrosmast)
            .catch(error => null);
    }
    
    //QQUECHOD VALIDADO
    //99-PARAM-CELIMI
    public eliminar(dto: DtoComunParametrosmast): Promise<DtoComunParametrosmast> {
        return this.http.put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunParametrosmast)
            .catch(error => null);
    }
  
    public eliminardtolistado(dto: DtlComunParametrosmast): Promise<DtoComunParametrosmast> {
        var bean=new DtoComunParametrosmast();
        bean.aplicacioncodigo=dto.aplicacioncodigo;
        bean.parametroclave=dto.parametroclave;
        bean.companiacodigo=dto.companiacodigo;
        return this.eliminar(bean);
    }
    
    //QQUECHOD VALIDADO
    //99-PARAM-CPAGIN
    public listarPaginado(filtro: FiltroComunParametrosmast): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public exportarParametros(filtro: FiltroComunParametrosmast): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarParametros`, filtro,
            { responseType: 'blob' as 'json' });
    }

    public parametroobtener(companiaCodigo: string, aplicacionCodigo: string, parametroClave: string): Promise<ParametroTransaccion> {
        var parametrotransaccion: ParametroTransaccion = new ParametroTransaccion();
        parametrotransaccion.companiacodigo = companiaCodigo;
        parametrotransaccion.aplicacioncodigo = aplicacionCodigo;
        parametrotransaccion.parametroclave = parametroClave;      
        return this.config.getHttp().put(this.url + 'parametroobtener', parametrotransaccion)
            .toPromise()
            .then(response => response as ParametroTransaccion)
            .catch(error => null);
    }
}