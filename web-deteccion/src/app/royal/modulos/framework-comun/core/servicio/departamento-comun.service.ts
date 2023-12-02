import { FiltroComunDepartamento } from './../dominio/filtro/FiltroComunDepartamento';
import { Observable } from 'rxjs';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoComunUbigeo } from '../dominio/dto/DtoComunUbigeo';
import { FiltroComunUbigeo } from '../dominio/filtro/FiltroComunUbigeo';
import { DtoComunDepartamento } from '../dominio/dto/DtoComunDepartamento';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';

@Injectable()
export class DepartamentoComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/departamentocomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

   /*  public obtenerDto(departamento: string): Promise<DtoComunDepartamento> {
        var filtro = new DtoComunDepartamento();
        filtro.departamento=departamento;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunDepartamento)
            .catch(error => new DtoComunDepartamento());
    }

    public listarDtoFiltros(departamento:string,descripcioncorta:string,estado:string): Promise<DtoComunDepartamento[]> {
        var filtro = new DtoComunDepartamento();
        filtro.departamento=departamento;
        filtro.descripcioncorta=descripcioncorta;
        filtro.estado=estado;
        return this.config.getHttp().put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunDepartamento[])
            .catch(error => []);
    }

    public listarDtoPorPais(pais:string,descripcioncorta:string,estado:string): Promise<DtoComunDepartamento[]> {
        var filtro = new DtoComunDepartamento();
        filtro.pais=pais;
        filtro.descripcioncorta=descripcioncorta;
        filtro.estado=estado;
        return this.config.getHttp().put(this.url + 'listardtoporpais',filtro)
            .toPromise()
            .then(response => response as DtoComunDepartamento[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-DEPARTAMENTO-C0001
    public listarporpais(pais: string): Promise<DtoTabla[]> {    
        var DtoComunDepartamento = new DtoComunDepartamento();
        DtoComunDepartamento.pais = pais;        
        return this.config.getHttp().put(this.url + 'listarporpais', DtoComunDepartamento)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    } */

    //QQUECHOD VALIDADO
    //99-DEPARTAMENTO-C0002
    public listarubigeoporfiltro(dto: FiltroComunUbigeo): Promise<DominioPaginacion> {        
        return this.config.getHttp().post(this.url + 'listarubigeoporfiltro', dto)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

/*     //QQUECHOD VALIDADO
    //99-DEPARTAMENTO-C0003
    public obtenerNombreDepartamento(pais: string, departamento: string): Promise<DtoTabla> {
        var dtoUbigeo = new DtoComunUbigeo();
        dtoUbigeo.pais = pais;   
        dtoUbigeo.departamento = departamento;     
        return this.config.getHttp().put(this.url + 'obtenernombredepartamento', dtoUbigeo)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } */





    public obtenerDtoPorId(departamento : string, descripcioncorta : string, descripcionlarga : string, pais : string, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoComunDepartamento> {
        var dto = new DtoComunDepartamento();

        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunDepartamento)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunDepartamento): Promise<DtoComunDepartamento> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunDepartamento)
            .catch(error => null);
    }

    public registrar(dto: DtoComunDepartamento): Promise<DtoComunDepartamento> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunDepartamento)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunDepartamento): Promise<DtoComunDepartamento> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunDepartamento)
            .catch(error => null);
    }

    public anular(dto: DtoComunDepartamento): Promise<DtoComunDepartamento> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunDepartamento)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunDepartamento): Promise<DtoComunDepartamento> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunDepartamento)
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

    public listarDtoFiltros(departamento : string, descripcioncorta : string, descripcionlarga : string, pais : string, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoComunDepartamento[]> {
        var filtro = new DtoComunDepartamento();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunDepartamento[])
            .catch(error => []);
    }

    public listarDtoActivos(departamento : string, descripcioncorta : string, descripcionlarga : string, pais : string, estado : string, ultimousuario : string, ultimafechamodif : Date): Promise<DtoComunDepartamento[]> {
        var filtro = new DtoComunDepartamento();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunDepartamento[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunDepartamento): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public exportarDepartamentosGeograficos(filtro: FiltroComunDepartamento): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarDepartamentosGeograficos`, filtro,
            { responseType: 'blob' as 'json' });
    }
}