 
import { Observable } from 'rxjs';
 
 
import { Injectable } from '@angular/core';
 
import { FiltroPaginacionUsuario } from '../dominio/filtro/FiltroPaginacionUsuario';
import { FiltroPaginacionPerfilUsuario } from '../dominio/filtro/FiltroPaginacionPerfilUsuario';
import { Usuario, UsuarioPk } from '../dominio/dto/Usuario';
import { Seguridadperfilusuario } from '../dominio/dto/Seguridadperfilusuario';
import { SeguridadperfilusuarioBean } from '../dominio/dto/SeguridadperfilusuarioBean';
import { FiltroPaginacionAgGePersona } from '../dominio/filtro/FiltroPaginacionAgGePersona';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '@env/appconfig';
import { DtoComunUsuario } from '@framework-comun/sg/dominio/dto/DtoComunUsuario';

@Injectable()
export class UsuarioMaestroServicio {

    private url = `${this.config.getEnv('spring-erp-sg-api')}spring/seguridad/usuario`;
    //private urlTempV2 = `${this.config.getEnv('spring-framework-comun-api')}spring/seguridad/seguridadperfilusuariocomun`;
    //private urlTempV3 = `${this.config.getEnv('spring-framework-comun-api')}spring/seguridad/aggepersona`;
    private urlseguridad = `${this.config.getEnv('spring-erp-sg-api')}spring/seguridad/seguridadperfilusuario`;

    constructor(private http: HttpClient, private config: AppConfig) { }

    public listar(filtro: FiltroPaginacionUsuario) {
        let urlTemp = this.url + "/listar";
        return this.http.post(urlTemp, filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public obtenerPorId(usuario: String): Promise<Usuario> {
        let urlTemp = this.url + "/obtenerporid/" + usuario;
        return this.http.get(urlTemp)
            .toPromise()
            .then(response => response as Usuario)
            .catch(error => null);
    }




    public listarSeguridadPerfilUsuario(filtro: FiltroPaginacionPerfilUsuario) {
        let urlTemp = this.urlseguridad + "/listar";
        return this.http.post(urlTemp, filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listarUsuarioByPerfil(filtro: FiltroPaginacionPerfilUsuario) {
        let urlTemp = this.urlseguridad + "/listaruserbyperfil";
        return this.http.post(urlTemp, filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }



    public validarUsuarioExistente(bean: Usuario): Promise<Usuario> {
        return this.http.post(this.url + '/validarUsuarioExistente', bean)
            .toPromise()
            .then(response => (response as Usuario))
            .catch(error => null);
    }

    public registrar(bean: Usuario): Promise<Usuario> {
        return this.http.post(this.url + '/registrar', bean)
            .toPromise()
            .then(response => (response as Usuario))
            .catch(error => null);
    }

    public actualizarPerfil(bean: Usuario): Promise<Usuario> {
        return this.http.put(this.url + '/actualizar', bean)
            .toPromise()
            .then(response => (response as Usuario))
            .catch(error => null);
    }

    public actualizar(bean: DtoComunUsuario): Promise<Usuario> {
        return this.http.put(this.url + '/actualizar', bean)
            .toPromise()
            .then(response => (response as Usuario))
            .catch(error => null);
    }

    public actualizarestado(bean: Usuario): Promise<Usuario> {
        return this.http.put(this.url + '/actualizarestado', bean)
            .toPromise()
            .then(response => (response as Usuario))
            .catch(error => null);
    }

    public actualizarestadodesbloquear(bean: Usuario): Promise<Usuario> {
        return this.http.put(this.url + '/actualizarestadodesbloquear', bean)
            .toPromise()
            .then(response => (response as Usuario))
            .catch(error => null);
    }

    public generarclave(bean: Usuario): Promise<Usuario> {
        return this.http.put(this.url + '/generarnuevaclave', bean)
            .toPromise()
            .then(response => (response as Usuario))
            .catch(error => null);
    }



    public eliminar(pk: UsuarioPk): Promise<UsuarioPk> {
        let urlTemp = this.url + '/eliminar' + pk.usuario;
        return this.http.delete(urlTemp)
            .toPromise()
            .then(response => (response as UsuarioPk))
            .catch(error => null);
    }

    public eliminarpefil(usuario: String, perfil: String): Promise<Seguridadperfilusuario> {
        let urlTemp = this.urlseguridad + '/eliminar/' + perfil + "/" + usuario;;
        return this.http.delete(urlTemp)
            .toPromise()
            .then(response => (response as Seguridadperfilusuario))
            .catch(error => null);
    }



    public eliminarpefilUsuario(perfil: String, usuario: String): Promise<Seguridadperfilusuario> {
        let urlTemp = this.urlseguridad + '/eliminar/' + perfil + "/" + usuario;;
        return this.http.delete(urlTemp)
            .toPromise()
            .then(response => (response as Seguridadperfilusuario))
            .catch(error => null);
    }

    public registrarPerfil(bean: Seguridadperfilusuario): Promise<Seguridadperfilusuario> {
        return this.http.post(this.urlseguridad + '/registrar', bean)
            .toPromise()
            .then(response => (response as Seguridadperfilusuario))
            .catch(error => null);
    }

    public desactivarPerfil(bean: Seguridadperfilusuario): Promise<Seguridadperfilusuario> {
        return this.http.post(this.url + '/modificar', bean)
            .toPromise()
            .then(response => (response as Seguridadperfilusuario))
            .catch(error => null);
    }

    /*public listarAgGePersona(filtro: FiltroPaginacionAgGePersona) {
        let urlTemp = this.urlTempV3 + "/listar";
        return this.http.post(urlTemp, filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }*/

    // public obtenerParametro() {
    //     return this.http.post(urlTemp, filtro)
    //         .toPromise()
    //         .then(response => response as DominioPaginacion)
    //         .catch(error => new DominioPaginacion());
    // }

    public exportar(filtro: FiltroPaginacionUsuario): Observable<Blob> {
        return this.config.getHttp().put<Blob>(`${this.url}/exportar`, filtro,
            { responseType: 'blob' as 'json' });
    }

    public exportarperfil(filtro: FiltroPaginacionUsuario): Observable<Blob> {
        return this.config.getHttp().put<Blob>(`${this.url}/exportarperfil`, filtro,
            { responseType: 'blob' as 'json' });
    }

    public exportarusuariosperfil(filtro: FiltroPaginacionPerfilUsuario): Observable<Blob> {
        return this.config.getHttp().put<Blob>(`${this.urlseguridad}/exportarusuarioporperfil`, filtro,
            { responseType: 'blob' as 'json' });
    }


    public exportarperfilgeneral(filtro: FiltroPaginacionPerfilUsuario): Observable<Blob> {
        return this.config.getHttp().put<Blob>(`${this.urlseguridad}/exportarperfilgeneral`, filtro,
            { responseType: 'blob' as 'json' });
    }







}