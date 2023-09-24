import { Injectable } from '@angular/core';
import { JWT_RESPONSE_HEADER } from 'src/app/util/constantes';
import { AppConfig } from 'src/environments/appconfig';
import { SeguridadUsuarioLogin } from '../dominio/SeguridadUsuarioLogin';

@Injectable()
export class LoginService {

    private url = `${this.config.getEnv('spring-seguridad-api')}autorizacion/seguridad/login/`;
    
    constructor(private config: AppConfig) {
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