import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { HttpClient } from '@angular/common/http';
import { FiltroConsultaBoleta } from '../dominio/filtro/FiltroConsultaBoleta';
import { DtoConsultaBoletaCabecera, DtoConsultaBoletaDetalle } from '../dominio/dto/DtoConsultaBoletaCabecera';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { FiltroConsultaAniversario } from '../dominio/filtro/FiltroConsultaAniversario';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { FiltroConsultaCumpleanio } from '../dominio/filtro/FiltroConsultaCumpleanio';
import { FiltroConsultaLiquidaciones } from '../dominio/filtro/FiltroConsultaLiquidaciones';
import { FiltroConsultaUtilidades } from '../dominio/filtro/FiltroConsultaUtilidades';
import { FiltroConsultaQuintaCategoria } from '../dominio/filtro/FiltroConsultaQuintaCategoria';
import { FiltroConsultaCts } from '../dominio/filtro/FiltroConsultaCts';
import { FiltroAuditoriaProcesos } from '../dominio/filtro/FiltroAuditoriaProcesos';
import { Observable } from 'rxjs';
import { FiltroCertificadoIngresos } from '../dominio/filtro/FiltroCertificadoIngresos';
import { map } from 'rxjs/operators';
import { FiltroCertificadoLaboral } from '../dominio/filtro/FiltroCertificadoLaboral';

@Injectable()
export class PrPlanillaempleadoServicio {

    private rutaServicio = `${this.config.getEnv('spring-autoservicios-api')}spring/planilla/prplanillaempleado/`;

    constructor(private http: HttpClient, private config: AppConfig) { }

    public consultaBoletasDatosInicio(): Promise<FiltroConsultaBoleta> {
        return this.http.get(this.rutaServicio + 'consultaBoletasDatosInicio')
            .toPromise()
            .then(response => response as FiltroConsultaBoleta)
            .catch(error => new FiltroConsultaBoleta());
    }

    public listarPeriodoParaBoleta(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarPeriodoParaBoleta')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => new Array());
    }

    public listarProcesadosPorPeriodo(filtro: FiltroConsultaBoleta): Promise<DtoTabla[]> {
        return this.http.post(this.rutaServicio + 'listarProcesadosPorPeriodo', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => new Array());
    }

    public consultaBoletasCabecera(filtro: FiltroConsultaBoleta): Promise<DtoConsultaBoletaCabecera[]> {
        return this.http.post(this.rutaServicio + 'consultaBoletasCabecera', filtro)
            .toPromise()
            .then(response => response as DtoConsultaBoletaCabecera[])
            .catch(error => []);
    }

    public consultaBoletasDetalle(filtro: DtoConsultaBoletaCabecera): Promise<DtoConsultaBoletaDetalle[]> {
        return this.http.post(this.rutaServicio + 'consultaBoletasDetalle', filtro)
            .toPromise()
            .then(response => response as DtoConsultaBoletaDetalle[])
            .catch(error => []);
    }

    public imprimirBoleta(filtro: DtoConsultaBoletaCabecera): Promise<DtoTabla> {
        return this.http.post(this.rutaServicio + 'imprimirBoleta', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }

    public consultaPrestamos(): Promise<any[]> {
        return this.http.get(this.rutaServicio + 'consultaPrestamos')
            .toPromise()
            .then(response => response as any[])
            .catch(error => []);
    }

    public consultaCuentaCorriente(filtro: DtoConsultaBoletaCabecera): Promise<any[]> {
        return this.http.post(this.rutaServicio + 'consultaCuentaCorriente', filtro)
            .toPromise()
            .then(response => response as any[])
            .catch(error => []);
    }

    public consultaAniversario(filtro: FiltroConsultaAniversario): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'consultaAniversario', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public consultaCumpleanio(filtro: FiltroConsultaCumpleanio): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'consultaCumpleanio', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public consultaLiquidaciones(filtro: FiltroConsultaLiquidaciones): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'consultaLiquidaciones', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public imprimirLiquidacion(filtro: any): Promise<DtoTabla> {
        return this.http.post(this.rutaServicio + 'imprimirLiquidacion', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }

    public listarPeriodosUtilidad(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarPeriodosUtilidad')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public imprimirUtilidades(filtro: FiltroConsultaUtilidades): Promise<DtoTabla> {
        return this.http.post(this.rutaServicio + 'imprimirUtilidades', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }

    public listarPeriodosQuintaCategoria(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarPeriodosQuintaCategoria')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public imprimirQuintaCategoria(filtro: FiltroConsultaQuintaCategoria): Promise<DtoTabla> {
        return this.http.post(this.rutaServicio + 'imprimirQuintaCategoria', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }

    public listarPeriodosCts(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarPeriodosCts')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public consultaCts(filtro: FiltroConsultaCts): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'consultaCts', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public imprimirCts(filtro: any): Promise<DtoTabla> {
        return this.http.post(this.rutaServicio + 'imprimirCts', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }

    public auditoriaProcesos(filtro: FiltroAuditoriaProcesos): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'auditoriaProcesos', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listarAuditoriaPeriodo(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarAuditoriaPeriodo')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public exportarAuditoriaProcesos(filtro: FiltroAuditoriaProcesos): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.rutaServicio}exportarAuditoriaProcesos`, filtro,
            { responseType: 'blob' as 'json' });
    }

    public consultaCertificadoInicio(): Promise<FiltroCertificadoIngresos> {
        return this.http.get(this.rutaServicio + 'consultaCertificadoInicio')
            .toPromise()
            .then(response => response as FiltroCertificadoIngresos)
            .catch(error => new FiltroCertificadoIngresos());
    }

    public imprimirCertificado(filtro: FiltroCertificadoIngresos): any {
        return this.config.getHttp().post(`${this.rutaServicio}imprimirCertificado`, filtro, { responseType: 'blob' })
            .pipe(
                map((result: any) => {
                    return result;
                })
            );
    }

    public existePlanilla(filtro: FiltroCertificadoIngresos): Promise<DtoTabla> {
        return this.http.post(this.rutaServicio + 'existePlanilla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => null);
    }

    public consultaCertificadoLaboral(): Promise<FiltroCertificadoLaboral> {
        return this.http.get(this.rutaServicio + 'consultaCertificadoLaboral')
            .toPromise()
            .then(response => response as FiltroCertificadoLaboral)
            .catch(error => new FiltroCertificadoLaboral());
    }

    public obtenerCertifCorrelativo(filtro: FiltroCertificadoLaboral): Promise<DtoTabla> {
        return this.http.post(this.rutaServicio + 'obtenerCertifCorrelativo', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => null);
    }

    public imprimirCertificadoLaboral(filtro: FiltroCertificadoLaboral): any {
        return this.config.getHttp().post(`${this.rutaServicio}imprimirCertificadoLaboral`, filtro, { responseType: 'blob' })
            .pipe(
                map((result: any) => {
                    return result;
                })
            );
    }

}


