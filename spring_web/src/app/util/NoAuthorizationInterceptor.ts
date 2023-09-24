import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor,
    HttpErrorResponse,
    HttpResponse
} from '@angular/common/http';
import { Observable, ReplaySubject, throwError } from "rxjs";
import { catchError, tap } from 'rxjs/operators';

import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AppConfig } from 'src/environments/appconfig';

@Injectable()
export class NoAuthorizationInterceptor implements HttpInterceptor {

    constructor(private messageService: MessageService, private route: Router, private config: AppConfig) {

    }

    dataStream: ReplaySubject<any> = new ReplaySubject<any>();
    mensajeAnteiror: string = "";

    dataStream$(): Observable<any> {
        this.dataStream = new ReplaySubject<any>();
        return this.dataStream.asObservable();
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        let accessToken = sessionStorage.getItem("access_token");

        if (request.url != './assets/config/proxy/env.json') {
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${accessToken}`,
                    sid: `${this.config.getEnv('sid')}`,
                }
            });
        }

        return next.handle(request).pipe(
            tap(
                event => this.handleResponse(request, event),
                error => this.handleError(request, error)
            )
        );
    }

    handleResponse(req: HttpRequest<any>, event) {

    }

    handleError(req: HttpRequest<any>, errorResponse: HttpErrorResponse) {

        console.log(errorResponse);

        if (errorResponse.status === 407) {
            //407 Session expirada
            this.mostrarMensajesError(errorResponse);
            this.route.navigate([""]);
        }

        if (errorResponse.status === 504) {
            //504 Servidor de seguridad apagado
            this.mostrarMensajesError(errorResponse);
            this.route.navigate([""]);
        }

        if (errorResponse.status === 0) {
            //0 Api actual apagada
            this.mostrarMensajesError("Los servicios no están activos, reintentar en unos minutos");
            this.route.navigate([""]);
        }

        if (errorResponse.status === 500) {
            //500 Error de aplicación no controlado
            this.mostrarMensajesError(errorResponse);
            // this.route.navigate([""]);
        }

        if (errorResponse.status === 401) {
            //401, lo mandamos al login
            this.mostrarMensajesError('No tiene accesos, debe ingresar sus credenciales');
            this.route.navigate([""]);
        }
    }

    mostrarMensajesError(errorResponse: any): void {
        if (Array.isArray(errorResponse.error)) {
            for (var _i = 0; _i < errorResponse.error.length; _i++) {
                this.mostrarMensajeNoDuplicado(errorResponse.error[_i].mensaje);
            }
            return;
        }
        if (typeof errorResponse === 'string') {
            this.mostrarMensajeNoDuplicado(errorResponse);
        }
        else {
            this.mostrarMensajeNoDuplicado(errorResponse.error.message);
        }
    }

    mostrarMensajeNoDuplicado(mensaje: string) {
        if (this.mensajeAnteiror !== mensaje) {
            this.mensajeAnteiror = mensaje;
            this.mostrarMensaje(mensaje, 'error');
        }
    }

    mostrarMensaje(mensaje: string, tipo: string, callBack?: () => void): void {
        this.messageService.add({ severity: tipo, summary: 'Mensaje', detail: mensaje });
    }
}