import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { JWT_RESPONSE_HEADER } from './../../../constantes';
import { DtoTabla } from 'src/app/royal/framework/modelo/generico/dto/DtoTabla';
import { SeguridadUsuarioLogin } from 'src/app/royal/framework/modelo/seguridad/SeguridadUsuarioLogin';
import { SeguridadUsuarioActual } from 'src/app/royal/framework/modelo/seguridad/SeguridadUsuarioActual';
import { DominioMensajeUsuario } from '@framework/modelo/generico/DominioMensajeUsuario';

@Injectable()
export class UsuarioService {
    private url = `${this.config.getEnv('spring-framework-seguridad-api')}spring/seguridad/usuario/`;
    private url2 = `${this.config.getEnv('spring-framework-comun-api')}/spring/seguridad/aplicacionesmastcomun/`;
    private url3 = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/tipocambiomastcomun/`;
    private url4 = `${this.config.getEnv('spring-framework-comun-api')}/spring/seguridad/usuario/`
    constructor(private config: AppConfig) {
    }

    public menucore(): Promise<any> {
        return this.config.getHttp().get(`${this.url}menucore`)
            .toPromise()
            .then(response => response as any)
            .catch(error => new Object());
    }

    public obtenerUsuarioActual(): Promise<SeguridadUsuarioActual> {
        return this.config.getHttp().get(`${this.url}obtenerusuarioactual`)
            .toPromise()
            .then(response => response as SeguridadUsuarioActual
            )
            .catch(error => new SeguridadUsuarioActual());
    }

    public cambiarClaveUsuario(usuario: any): Promise<DtoTabla> {

        return this.config.getHttp().put(this.url + 'cambiarClaveUsuario', usuario)        
            .toPromise()
            .then(response => this.saveToken(response as SeguridadUsuarioLogin)
            )
            .catch(error => null);
    }


    public validarClave(usuario: any): Promise<DominioMensajeUsuario[]> {

        return this.config.getHttp().put(this.url + 'validarClave', usuario)        
            .toPromise()
            .then(response => response as DominioMensajeUsuario[])
            .catch(error => []);
    }

    private saveToken(res: SeguridadUsuarioLogin): any {
        const token = res.token;
        if (token) {
            sessionStorage.setItem(JWT_RESPONSE_HEADER, token);
        }
        return res;
    }

    public logOut(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'logOut')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public aplicaciones(): Promise<any[]> {
        return this.config.getHttp().get(`${this.url4}aplicaciones`)
            .toPromise()
            .then(response => response as any[])
            .catch(error => []);
    }

}