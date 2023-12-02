import { DtoComunSnAlertas } from './../dominio/dto/DtoComunSnAlertas';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';

@Injectable()
export class SnuAlertasComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/sistema/snalertascomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }
 

    //99-SN-ALERACT
    public listarActivos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    
    
    public actualizarAlerta(dto: DtoComunSnAlertas): Promise<DtoTabla[]> {
            return this.config.getHttp().put(this.url + 'actualizaralerta', dto)
                .toPromise()
                .then(response => response as DtoComunSnAlertas)
                .catch(error => null);
    }

    public registrarAlerta(dto: DtoComunSnAlertas): Promise<DtoTabla[]> {
        return this.config.getHttp().put(this.url + 'registraralerta', dto)
            .toPromise()
            .then(response => response as DtoComunSnAlertas)
            .catch(error => null);
}

}