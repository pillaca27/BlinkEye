import { DtoComunSyReporte } from '../dominio/dto/DtoComunSyReporte';
import { DominioPaginacion } from '../../../../framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { DominioMensajeUsuario } from '../../../../framework/modelo/generico/DominioMensajeUsuario';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FiltroComunSyReporte } from '../dominio/filtro/FiltroComunSyReporte';
import { Observable } from 'rxjs';
import { DtoExportarReportes } from '../dominio/dto/DtoExportarReportes';

@Injectable()
export class SyReporteComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/sistema/syreportecomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //QQUECHOD VALIDADO
    //SY-REPORTE-COBTDTO
    public obtenerDto(bean: DtoComunSyReporte): Promise<DtoComunSyReporte> {
        return this.http.put(this.url + 'obtenerdto', bean)
            .toPromise()
            .then(response => response as DtoComunSyReporte)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //SY-REPORTE-CACTUA
    public actualizar(bean: DtoComunSyReporte): Promise<any> {
        return this.http.put(this.url + 'actualizar', bean)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //SY-REPORTE-CREGIST
    public registrar(bean: DtoComunSyReporte): Promise<any> {
        return this.http.post(this.url + 'registrar', bean)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //SY-REPORTE-CLISPAG
    public listarPaginado(filtro: FiltroComunSyReporte): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listarpaginadoReportes(filtro: FiltroComunSyReporte): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarpaginadoReportes', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public obtenerTopicos(aplicacion: string): Promise<DtoTabla[]> {
        const params = new HttpParams()
            .set('aplicacion', aplicacion);
            //GET CON CUERPO ACT
            var dto = new DtoTabla();
            dto.codigo = aplicacion;
        return this.http.put(this.url + 'obtenerTopicos', dto )
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public exportarReportes(filtro: FiltroComunSyReporte): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarReportes`, filtro,
            { responseType: 'blob' as 'json' });
    }

    public exportarJsonReportes(filtro: FiltroComunSyReporte): Promise<DtoExportarReportes> {
        return this.http.post(this.url + 'exportarJsonReportes', filtro)
            .toPromise()
            .then(response => response as DtoExportarReportes)
            .catch(error => new DtoExportarReportes());
    }

    public importarJsonReportes(filtro: DtoExportarReportes): Promise<DtoExportarReportes> {
        return this.http.post(this.url + 'importarJsonReportes', filtro)
            .toPromise()
            .then(response => response as DtoExportarReportes)
            .catch(error => new DtoExportarReportes());
    }

}