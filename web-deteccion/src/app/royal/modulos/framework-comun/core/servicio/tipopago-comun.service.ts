import { Observable } from 'rxjs';
import { FiltroComunTipopago } from './../dominio/filtro/FiltroComunTipopago';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunTipopago } from '../dominio/dto/DtoComunTipopago';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';

@Injectable()
export class TipopagoComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/tipopagocomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    /*public listar(): Promise<DtoTabla[]> {                
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarActivos(): Promise<DtoTabla[]> {                
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
     
    public obtenerTabla(tipopago: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=tipopago;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(tipopago: string,descripcion: string,estado: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=tipopago;
        filtro.nombre=descripcion;
        filtro.estadoId=estado;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

     public obtenerDto(tipopago: string): Promise<DtoComunTipopago> {
        var filtro = new DtoComunTipopago();
        filtro.tipopago=tipopago;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunTipopago)
            .catch(error => new DtoComunTipopago());
    } 

    public listarDtoFiltros(tipopago: string,descripcion:string,estado: string): Promise<DtoComunTipopago[]> {
        var filtro = new DtoComunTipopago();
        filtro.tipopago=tipopago;
        filtro.descripcion=descripcion;
        filtro.estado=estado;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunTipopago[])
            .catch(error => []);
    }
    */





    /*MANTENIMIENTO LEONARDO*/

    public obtenerDtoPorId(tipopago : string, descripcion : string, restringidoflag : string, cartaflag : string, generarnumeracionflag : string, entregableflag : string, ultimousuario : string, ultimafechamodif : Date, timestamp : object, estado : string, vouchersumarizadoflag : string, disponiblefeederflag : string, descripcioningles : string, codigosiaf : string, codigofiscal : string, tipopagortps : string): Promise<DtoComunTipopago> {
        var dto = new DtoComunTipopago();

        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunTipopago)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunTipopago): Promise<DtoComunTipopago> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunTipopago)
            .catch(error => null);
    }

    public registrar(dto: DtoComunTipopago): Promise<DtoComunTipopago> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunTipopago)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunTipopago): Promise<DtoComunTipopago> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunTipopago)
            .catch(error => null);
    }

    public anular(dto: DtoComunTipopago): Promise<DtoComunTipopago> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunTipopago)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunTipopago): Promise<DtoComunTipopago> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunTipopago)
            .catch(error => null);
    }

    public listar(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarActivos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarDtoFiltros(tipopago : string, descripcion : string, restringidoflag : string, cartaflag : string, generarnumeracionflag : string, entregableflag : string, ultimousuario : string, ultimafechamodif : Date, timestamp : object, estado : string, vouchersumarizadoflag : string, disponiblefeederflag : string, descripcioningles : string, codigosiaf : string, codigofiscal : string, tipopagortps : string): Promise<DtoComunTipopago[]> {
        var filtro = new DtoComunTipopago();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunTipopago[])
            .catch(error => []);
    }

    public listarDtoActivos(tipopago : string, descripcion : string, restringidoflag : string, cartaflag : string, generarnumeracionflag : string, entregableflag : string, ultimousuario : string, ultimafechamodif : Date, timestamp : object, estado : string, vouchersumarizadoflag : string, disponiblefeederflag : string, descripcioningles : string, codigosiaf : string, codigofiscal : string, tipopagortps : string): Promise<DtoComunTipopago[]> {
        var filtro = new DtoComunTipopago();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunTipopago[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunTipopago): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public exportarTipoPago(filtro: FiltroComunTipopago): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarTipoPago`, filtro,
            { responseType: 'blob' as 'json' });
    }
}