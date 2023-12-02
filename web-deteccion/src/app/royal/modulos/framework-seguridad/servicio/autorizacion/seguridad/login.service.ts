import { SeguridadUsuarioActual } from './../../../../../framework/modelo/seguridad/SeguridadUsuarioActual';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { JWT_RESPONSE_HEADER } from './../../../constantes';
import { SeguridadUsuarioLogin } from 'src/app/royal/framework/modelo/seguridad/SeguridadUsuarioLogin';
import { DtoTabla } from 'src/app/royal/framework/modelo/generico/dto/DtoTabla';

@Injectable()
export class LoginService {
    private url = `${this.config.getEnv('spring-framework-seguridad-api')}autorizacion/seguridad/login/`;

    constructor(private config: AppConfig) {
    }

    listarcompaniasporusuario(body: SeguridadUsuarioLogin): Promise<DtoTabla[]> {
        return this.config.getHttp().put(`${this.url}listarcompaniasporusuario`, body)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => null);
    }

    listarcompaniaslogin(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(`${this.url}listarcompaniaslogin`)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => null);
    }

    listarTipoDocumento(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(`${this.url}listarTipoDocumento`)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => null);
    }

    ingresar(body: SeguridadUsuarioLogin): Promise<SeguridadUsuarioLogin> {
        return this.config.getHttp().put(`${this.url}ingresar`, body)
            .toPromise()
            .then(response => this.saveToken(response as SeguridadUsuarioLogin)
            )
            .catch(error => null);
    }



    private saveToken(res: SeguridadUsuarioLogin): any {
        const token = res.token;
        if (token) {
            sessionStorage.setItem(JWT_RESPONSE_HEADER, token);
        }
        return res;
    }



}