import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DtoComunPais } from '../dominio/dto/DtoComunPais';
import { FiltroComunPais } from '../dominio/filtro/FiltroComunPais';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';

@Injectable()
export class PaisComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/paiscomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //LEONARDO
    //SELECTOR PAIS
    public listarpaisporfiltro(dto: FiltroComunPais): Promise<DominioPaginacion> {        
        return this.config.getHttp().post(this.url + 'listarpaisporfiltro', dto)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    //QQUECHOD VALIDADO
    //99-PAIS-CLISTA
    public listar(): Promise<DtoTabla[]> {        
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-PAIS-CLIACT
    public listarActivos(): Promise<DtoTabla[]> {        
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerTabla(pais: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=pais;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    /*public obtenerDto(pais: string): Promise<DtoComunPais> {
        var filtro = new DtoComunPais();
        filtro.pais=pais;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunPais)
            .catch(error => new DtoComunPais());
    }

    public listarDtoFiltros(pais : string,descripcioncorta:string,estado:string): Promise<DtoComunPais[]> {
        var filtro = new DtoComunPais();
        filtro.pais=pais;
        filtro.descripcioncorta=descripcioncorta;
        filtro.estado=estado;
        return this.config.getHttp().put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunPais[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunPais): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }
    */









    //MANTENIMIENTO LEONARDO

    
    public obtenerDtoPorId(pais : string, descripcioncorta : string, descripcionlarga : string, descripcionextranjera : string, estado : string, ultimousuario : string, ultimafechamodif : Date, nombre : string, nacionalidad : string, codigortps : string): Promise<DtoComunPais> {
        var dto = new DtoComunPais();
        dto.pais = pais;
        dto.descripcioncorta = descripcioncorta;
        dto.descripcionlarga = descripcionlarga;
        dto.descripcionextranjera = descripcionextranjera;
        dto.estado = estado;
        dto.ultimousuario = ultimousuario;
        dto.ultimafechamodif = ultimafechamodif;
        dto.nombre = nombre;
        dto.nacionalidad = nacionalidad;
        dto.codigortps = codigortps;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunPais)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunPais): Promise<DtoComunPais> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunPais)
            .catch(error => null);
    }

    public registrar(dto: DtoComunPais): Promise<DtoComunPais> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunPais)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunPais): Promise<DtoComunPais> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunPais)
            .catch(error => null);
    }

    public anular(dto: DtoComunPais): Promise<DtoComunPais> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunPais)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunPais): Promise<DtoComunPais> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunPais)
            .catch(error => null);
    }

     

    public listarDtoFiltros(pais : string, descripcioncorta : string, descripcionlarga : string, descripcionextranjera : string, estado : string, ultimousuario : string, ultimafechamodif : Date, nombre : string, nacionalidad : string, codigortps : string): Promise<DtoComunPais[]> {
        var filtro = new DtoComunPais();
        filtro.pais = pais;
        filtro.descripcioncorta = descripcioncorta;
        filtro.descripcionlarga = descripcionlarga;
        filtro.descripcionextranjera = descripcionextranjera;
        filtro.estado = estado;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        filtro.nombre = nombre;
        filtro.nacionalidad = nacionalidad;
        filtro.codigortps = codigortps;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunPais[])
            .catch(error => []);
    }

    public listarDtoActivos(pais : string, descripcioncorta : string, descripcionlarga : string, descripcionextranjera : string, estado : string, ultimousuario : string, ultimafechamodif : Date, nombre : string, nacionalidad : string, codigortps : string): Promise<DtoComunPais[]> {
        var filtro = new DtoComunPais();
        filtro.pais = pais;
        filtro.descripcioncorta = descripcioncorta;
        filtro.descripcionlarga = descripcionlarga;
        filtro.descripcionextranjera = descripcionextranjera;
        filtro.estado = estado;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        filtro.nombre = nombre;
        filtro.nacionalidad = nacionalidad;
        filtro.codigortps = codigortps;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunPais[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunPais): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

}