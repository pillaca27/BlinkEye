import { FiltroComunTiposervicio } from './../dominio/filtro/FiltroComunTiposervicio';
import { FiltroComunTipopago } from './../dominio/filtro/FiltroComunTipopago';
import { Observable } from 'rxjs';
import { DominioPaginacion } from 'src/app/royal/framework/modelo/generico/DominioPaginacion';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';

import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunTiposervicio } from '../dominio/dto/DtoComunTiposervicio';

@Injectable()
export class TiposervicioComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/tiposerviciocomun/`;
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
    
    public obtenerTabla(tiposervicio: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=tiposervicio;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(tiposervicio: string,descripcion: string,estado: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=tiposervicio;
        filtro.nombre=descripcion;
        filtro.estadoId=estado;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerDto(tiposervicio: string): Promise<DtoComunTiposervicio> {
        var filtro = new DtoComunTiposervicio();
        filtro.tiposervicio=tiposervicio;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunTiposervicio)
            .catch(error => new DtoComunTiposervicio());
    } 

    public listarDtoFiltros(tiposervicio: string,descripcion:string,estado: string): Promise<DtoComunTiposervicio[]> {
        var filtro = new DtoComunTiposervicio();
        filtro.tiposervicio=tiposervicio;
        filtro.descripcion=descripcion;
        filtro.estado=estado;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunTiposervicio[])
            .catch(error => []);
    }

    public listarDtoActivos(): Promise<DtoComunTiposervicio[]> {                
        return this.config.getHttp().get(this.url + 'listardtoactivos')
            .toPromise()
            .then(response => response as DtoComunTiposervicio[])
            .catch(error => []);
    }
    
    public listarDtoActivosPorRegimenfiscal(regimenfiscal: string): Promise<DtoComunTiposervicio[]> {
        var filtro = new DtoComunTiposervicio();
        filtro.regimenfiscal=regimenfiscal;
        return this.http.put(this.url + 'listardtoactivosporregimenfiscal',filtro)
            .toPromise()
            .then(response => response as DtoComunTiposervicio[])
            .catch(error => []);
    }*/


    /*MANTENIMIENTO LEONARDO*/

    public obtenerDtoPorId(tiposervicio: string, descripcion: string, regimenfiscal: string, ultimousuario: string, ultimafechamodif: Date, timestamp: object, clasificacionfiscal: string, estado: string, descripcioningles: string): Promise<DtoComunTiposervicio> {
        var dto = new DtoComunTiposervicio();

        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunTiposervicio)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunTiposervicio): Promise<DtoComunTiposervicio> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunTiposervicio)
            .catch(error => null);
    }

    public registrar(dto: DtoComunTiposervicio): Promise<DtoComunTiposervicio> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunTiposervicio)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunTiposervicio): Promise<DtoComunTiposervicio> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunTiposervicio)
            .catch(error => null);
    }

    public anular(dto: DtoComunTiposervicio): Promise<DtoComunTiposervicio> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunTiposervicio)
            .catch(error => null);
    }


    public eliminar(dto: DtoComunTiposervicio): Promise<DtoComunTiposervicio> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunTiposervicio)
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

    public listarDtoFiltros(tiposervicio: string, descripcion: string, regimenfiscal: string, ultimousuario: string, ultimafechamodif: Date, timestamp: object, clasificacionfiscal: string, estado: string, descripcioningles: string): Promise<DtoComunTiposervicio[]> {
        var filtro = new DtoComunTiposervicio();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunTiposervicio[])
            .catch(error => []);
    }

    public listarDtoActivos(tiposervicio: string, descripcion: string, regimenfiscal: string, ultimousuario: string, ultimafechamodif: Date, timestamp: object, clasificacionfiscal: string, estado: string, descripcioningles: string): Promise<DtoComunTiposervicio[]> {
        var filtro = new DtoComunTiposervicio();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunTiposervicio[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunTiposervicio): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }


    public exportarTipoServicio(filtro: FiltroComunTiposervicio): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarTipoServicio`, filtro,
            { responseType: 'blob' as 'json' });
    }
}