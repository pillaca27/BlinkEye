import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';
 
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DtoComunUsuario } from '../dominio/dto/DtoComunUsuario';
import { FiltroComunUsuario } from '../dominio/filtro/FiltroComunUsuario';


@Injectable()
export class UsuarioComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/seguridad/usuariocomun/`;
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
    
    public obtenerTabla(usuario: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=usuario;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(usuario: string,nombre: string,estado: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=usuario;
        filtro.nombre=nombre;
        filtro.estadoId=estado;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerDto(usuario: string): Promise<DtoComunUsuario> {
        var filtro = new DtoComunUsuario();
        filtro.usuario=usuario;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunUsuario)
            .catch(error => new DtoComunUsuario());
    } 

    public listarDtoFiltros(usuario: string,usuarioperfil:string,nombre: string,estado: string): Promise<DtoComunUsuario[]> {
        var filtro = new DtoComunUsuario();
        filtro.usuario=usuario;
        filtro.usuarioperfil=usuarioperfil;        
        filtro.nombre=nombre;
        filtro.estado=estado;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunUsuario[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunUsuario): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.http.put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

}