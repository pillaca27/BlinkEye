import { Observable } from 'rxjs';
import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';
import { FiltroComunDepartmentmst } from './../dominio/filtro/FiltroComunDepartmentmst';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DtoComunDepartmentmst } from '../dominio/dto/DtoComunDepartmentmst';


@Injectable()
export class DepartmentmstComunService {

    //private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/departmentmst/`;
     private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/departmentmstcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    /*public listar(): Promise<DtoTabla[]> {        
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }*/

    /*public listarActivos(): Promise<DtoTabla[]> {        
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }*/

    public obtenerTabla(banco: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=banco;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(department: string,description: string,status: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=department;
        filtro.nombre=description;
        filtro.estadoId=status;
        return this.config.getHttp().put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    } 

    /*public obtenerDto(department: string): Promise<DtoComunDepartmentmst> {
        var filtro = new DtoComunDepartmentmst();
        filtro.department=department;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunDepartmentmst)
            .catch(error => new DtoComunDepartmentmst());
    }*/ 

    /*public listarDtoFiltros(department: string,description: string,status: string): Promise<DtoComunDepartmentmst[]> {
        var filtro = new DtoComunDepartmentmst();
        filtro.department=department;
        filtro.description=description;
        filtro.status=status;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunDepartmentmst[])
            .catch(error => []);
    }*/

    public listarDtoWarehouse(): Promise<DtoComunDepartmentmst[]> {        
        return this.config.getHttp().get(this.url + 'listardtowarehouse')
            .toPromise()
            .then(response => response as DtoComunDepartmentmst[])
            .catch(error => []);
    }
    







    public obtenerDtoPorId(department : string, description : string, accountavailableflag : string, warehouseavailableflag : string, application : string, status : string, lastuser : string, lastdate : Date): Promise<DtoComunDepartmentmst> {
        var dto = new DtoComunDepartmentmst();

        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunDepartmentmst)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunDepartmentmst): Promise<DtoComunDepartmentmst> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunDepartmentmst)
            .catch(error => null);
    }

    public registrar(dto: DtoComunDepartmentmst): Promise<DtoComunDepartmentmst> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunDepartmentmst)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunDepartmentmst): Promise<DtoComunDepartmentmst> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunDepartmentmst)
            .catch(error => null);
    }

    public anular(dto: DtoComunDepartmentmst): Promise<DtoComunDepartmentmst> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunDepartmentmst)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunDepartmentmst): Promise<DtoComunDepartmentmst> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunDepartmentmst)
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

    public listarDtoFiltros(department : string, description : string, accountavailableflag : string, warehouseavailableflag : string, application : string, status : string, lastuser : string, lastdate : Date): Promise<DtoComunDepartmentmst[]> {
        var filtro = new DtoComunDepartmentmst();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunDepartmentmst[])
            .catch(error => []);
    }

    public listarDtoActivos(department : string, description : string, accountavailableflag : string, warehouseavailableflag : string, application : string, status : string, lastuser : string, lastdate : Date): Promise<DtoComunDepartmentmst[]> {
        var filtro = new DtoComunDepartmentmst();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunDepartmentmst[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunDepartmentmst): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public exportarDepartamentosMast(filtro: FiltroComunDepartmentmst): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarDepartamentosMast`, filtro,
            { responseType: 'blob' as 'json' });
    }

}