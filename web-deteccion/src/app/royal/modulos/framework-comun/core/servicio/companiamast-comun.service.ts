import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';
import { FiltroComunCompaniamast } from './../dominio/filtro/FiltroComunCompaniamast';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunCompaniamast } from '../dominio/dto/DtoComunCompaniamast';
import { catchError } from 'rxjs/operators';
import { throwError, Observable } from 'rxjs';

@Injectable()
export class CompaniamastComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/companiamastcomun/`;
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
    
    public obtenerTabla(companiacodigo: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=companiacodigo;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(companiacodigo: string,descripcioncorta: string,estado: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=companiacodigo;
        filtro.nombre=descripcioncorta;
        filtro.estadoId=estado;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
 
    /*public obtenerDto(companiacodigo: string): Promise<DtoComunCompaniamast> {
        var filtro = new DtoComunCompaniamast();
        filtro.companiacodigo=companiacodigo;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunCompaniamast)
            .catch(error => new DtoComunCompaniamast());
    } */

    /*public listarDtoFiltros(companiacodigo: string,descripcioncorta: string,estado: string): Promise<DtoComunCompaniamast[]> {
        var filtro = new DtoComunCompaniamast();
        filtro.companiacodigo=companiacodigo;
        filtro.descripcioncorta=descripcioncorta;
        filtro.estado=estado;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunCompaniamast[])
            .catch(error => []);
    }*/
    









    public obtenerDtoPorId(companiacodigo : string, descripcioncorta : string, descripcionlarga : string, direccioncomun : string, direccionadicional : string, fechafundacion : Date, telefono1 : string, telefono2 : string, telefono3 : string, fax1 : string, fax2 : string, documentofiscal : string, propietariopordefecto : string, descripcionextranjera : string, monedapordefecto : string, tipocompania : string, factorrvalidacion : string, afectoigvflag : string, creditofiscalfactorflag : string, cuentaprovisionsbsflag : string, logofile : string, persona : number, estado : string, ultimousuario : string, ultimafechamodif : Date, representantelegal : string, paginaweb : string, afectoretencionigvflag : string, certificadoinscripcion : string, detraccioncuentabancaria : string, regimenlaboralrtps : string, codigorepresentantelegal : number, representantelegaldocumento : string, codestablesunat : string): Promise<DtoComunCompaniamast> {
        var dto = new DtoComunCompaniamast();

        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunCompaniamast)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunCompaniamast): Promise<DtoComunCompaniamast> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunCompaniamast)
            .catch(error => null);
    }

    public registrar(dto: DtoComunCompaniamast): Promise<DtoComunCompaniamast> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunCompaniamast)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunCompaniamast): Promise<DtoComunCompaniamast> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunCompaniamast)
            .catch(error => null);
    }

    public anular(dto: DtoComunCompaniamast): Promise<DtoComunCompaniamast> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunCompaniamast)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunCompaniamast): Promise<DtoComunCompaniamast> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunCompaniamast)
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

    public listarDtoFiltros(companiacodigo : string, descripcioncorta : string, descripcionlarga : string, direccioncomun : string, direccionadicional : string, fechafundacion : Date, telefono1 : string, telefono2 : string, telefono3 : string, fax1 : string, fax2 : string, documentofiscal : string, propietariopordefecto : string, descripcionextranjera : string, monedapordefecto : string, tipocompania : string, factorrvalidacion : string, afectoigvflag : string, creditofiscalfactorflag : string, cuentaprovisionsbsflag : string, logofile : string, persona : number, estado : string, ultimousuario : string, ultimafechamodif : Date, representantelegal : string, paginaweb : string, afectoretencionigvflag : string, certificadoinscripcion : string, detraccioncuentabancaria : string, regimenlaboralrtps : string, codigorepresentantelegal : number, representantelegaldocumento : string, codestablesunat : string): Promise<DtoComunCompaniamast[]> {
        var filtro = new DtoComunCompaniamast();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunCompaniamast[])
            .catch(error => []);
    }

    public listarDtoActivos(companiacodigo : string, descripcioncorta : string, descripcionlarga : string, direccioncomun : string, direccionadicional : string, fechafundacion : Date, telefono1 : string, telefono2 : string, telefono3 : string, fax1 : string, fax2 : string, documentofiscal : string, propietariopordefecto : string, descripcionextranjera : string, monedapordefecto : string, tipocompania : string, factorrvalidacion : string, afectoigvflag : string, creditofiscalfactorflag : string, cuentaprovisionsbsflag : string, logofile : string, persona : number, estado : string, ultimousuario : string, ultimafechamodif : Date, representantelegal : string, paginaweb : string, afectoretencionigvflag : string, certificadoinscripcion : string, detraccioncuentabancaria : string, regimenlaboralrtps : string, codigorepresentantelegal : number, representantelegaldocumento : string, codestablesunat : string): Promise<DtoComunCompaniamast[]> {
        var filtro = new DtoComunCompaniamast();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunCompaniamast[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunCompaniamast): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public subirLogo(logo:File,logoanterior:string){
        let formData= new FormData()
        formData.append('logoanterior', logoanterior);
        formData.append('logo',logo);
        return this.config.getHttp().post(this.url + 'upload', formData)
        .pipe(   
            catchError(e=>{console.log(e); return throwError(e)}) )

    }

    public delete_upload(logoanterior:string){
        let formData= new FormData()
        formData.append('logoanterior', logoanterior);
        return this.config.getHttp().post(this.url + 'delete_upload', formData)
        .pipe(   
            catchError(e=>{console.log(e); return throwError(e)}) )

    }

    public logo_compania(logo:string): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = logo;
        return this.config.getHttp().get(this.url + 'logo_compania/'+logo)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public exportarCompanias(filtro: FiltroComunCompaniamast): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarCompanias`, filtro,
            { responseType: 'blob' as 'json' });
    }
}