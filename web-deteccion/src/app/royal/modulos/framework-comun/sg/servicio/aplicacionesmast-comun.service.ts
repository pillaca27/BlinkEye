import { Observable } from 'rxjs';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DtoComunAplicacionesmast } from '../dominio/dto/DtoComunAplicacionesmast';
import { FiltroComunAplicacionesmast } from '../dominio/filtro/FiltroComunAplicacionesmast';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';


@Injectable()
export class AplicacionesmastComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/aplicacionesmastcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }    

    //QQUECHOD VALIDADO
    //SG-APLICA-CLISTA  
    public listar(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //SG-APLICA-CLIACT
    public listarActivos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    
    public obtenerTabla(aplicacioncodigo: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=aplicacioncodigo;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(aplicacioncodigo: string,descripcioncorta: string,estado: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=aplicacioncodigo;
        filtro.nombre=descripcioncorta;
        filtro.estadoId=estado;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    /* public obtenerDto(aplicacioncodigo: string): Promise<DtoComunAplicacionesmast> {
        var filtro = new DtoComunAplicacionesmast();
        filtro.aplicacioncodigo=aplicacioncodigo;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunAplicacionesmast)
            .catch(error => new DtoComunAplicacionesmast());
    } */

    public listarDtoFiltros(aplicacioncodigo: string,descripcioncorta: string,estado: string): Promise<DtoComunAplicacionesmast[]> {
        var filtro = new DtoComunAplicacionesmast();
        filtro.aplicacioncodigo=aplicacioncodigo;
        filtro.descripcioncorta=descripcioncorta;
        filtro.estado=estado;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunAplicacionesmast[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //SG-APLICA-C0001
    public listarAplicacionPorUsuario(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaraplicacionporusuario')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }


    //MANTENIMIENTO

    public listarpaginado(filtro: FiltroComunAplicacionesmast): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public exportarAplicaciones(filtro: FiltroComunAplicacionesmast): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarAplicaciones`, filtro,
            { responseType: 'blob' as 'json' });
    }

    public anular(dto: DtoComunAplicacionesmast): Promise<DtoComunAplicacionesmast> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunAplicacionesmast)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunAplicacionesmast): Promise<DtoComunAplicacionesmast> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunAplicacionesmast)
            .catch(error => null);
    }

    public registrar(dto: DtoComunAplicacionesmast): Promise<DtoComunAplicacionesmast> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunAplicacionesmast)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunAplicacionesmast): Promise<DtoComunAplicacionesmast> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunAplicacionesmast)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunAplicacionesmast): Promise<DtoComunAplicacionesmast> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunAplicacionesmast)
            .catch(error => null);
    }

    public listardepartamentos(): Promise<DtoTabla[]> {                
        return this.config.getHttp().get(this.url + 'listardepartamentos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarsistemafuente(): Promise<DtoTabla[]> {                
        return this.config.getHttp().get(this.url + 'listarsistemafuente')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    //FIN MANTENIMIENTO
}