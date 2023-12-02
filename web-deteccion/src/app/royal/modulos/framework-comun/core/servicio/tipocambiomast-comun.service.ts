import { Observable } from 'rxjs';
import { FiltroComunTipocambiomast } from '../dominio/filtro/FiltroComunTipocambiomast';
import { DtoComunTipocambiomast } from '../dominio/dto/DtoComunTipocambiomast';
import { DominioPaginacion } from '../../../../framework/modelo/generico/DominioPaginacion';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DtlComunTipocambiomast } from '../dominio/lista/DtlComunTipocambiomast';


@Injectable()
export class TipocambiomastComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/tipocambiomastcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //QQUECHOD VALIDADO
    //99-TICAMB-CLISPAG
    public listarpaginado(filtro: FiltroComunTipocambiomast): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.http.put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    //QQUECHOD VALIDADO
    //99-TICAMB-CREGIS
    public registrar(dto: DtoComunTipocambiomast): Promise<DtoComunTipocambiomast> {
        return this.http.post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunTipocambiomast)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //99-TICAMB-CACTUA
    public actualizar(dto: DtoComunTipocambiomast): Promise<DtoComunTipocambiomast> {
        return this.http.put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunTipocambiomast)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //99-TICAMB-CANULA
    public anular(dto: DtoComunTipocambiomast): Promise<DtoComunTipocambiomast> {
        return this.http.put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunTipocambiomast)
            .catch(error => null);
    }

    public anularDtoListado(dto: DtlComunTipocambiomast): Promise<DtoComunTipocambiomast> {
        var bean = new DtoComunTipocambiomast();
        bean.monedacodigo = dto.monedacodigo;
        bean.monedacambiocodigo = dto.monedacambiocodigo;
        bean.fechacambio = dto.fechacambio;
        return this.anular(bean);
    }

    //QQUECHOD VALIDADO
    //99-TICAMB-CELIMIN
    public eliminar(dto: DtoComunTipocambiomast): Promise<DtoComunTipocambiomast> {
        return this.http.put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunTipocambiomast)
            .catch(error => null);
    }

    public eliminarDtoListado(dto: DtlComunTipocambiomast): Promise<DtoComunTipocambiomast> {
        var bean = new DtoComunTipocambiomast();
        bean.monedacodigo = dto.monedacodigo;
        bean.monedacambiocodigo = dto.monedacambiocodigo;
        bean.fechacambio = dto.fechacambio;
        return this.eliminar(bean);
    }

    //QQUECHOD VALIDADO
    //99-TICAMB-COBDTO
    public obtenerDto(dto: DtoComunTipocambiomast): Promise<DtoComunTipocambiomast> {
        return this.http.put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunTipocambiomast)
            .catch(error => null);
    }

    public obtenerTipoCambioCreacionDefault(): Promise<DtoComunTipocambiomast> {
        return this.http.get(this.url + 'obtenerTipoCambioCreacionDefault')
            .toPromise()
            .then(response => response as DtoComunTipocambiomast)
            .catch(error => null);
    }


    public obtenertipoCambioActual():Promise<DtoComunTipocambiomast>{
        return this.http.get(this.url + 'tipoCambioActual' )
        .toPromise()
        .then(response => response as DtoComunTipocambiomast)
        .catch(error => null);
    }


    public exportarTipoCambio(filtro: FiltroComunTipocambiomast): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarTipoCambio`, filtro,
            { responseType: 'blob' as 'json' });
    }

    //URL2
    public auxObtenerTipoCambioCreacionDefault(): Promise<DtoComunTipocambiomast> {
        return this.config.getHttp().get(this.url + 'obtenerTipoCambioCreacionDefault')
            .toPromise()
            .then(response => response as DtoComunTipocambiomast)
            .catch(error => null);
    }
}