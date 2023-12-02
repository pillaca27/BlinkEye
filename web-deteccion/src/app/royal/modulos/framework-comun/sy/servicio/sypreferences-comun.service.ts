import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunSyPreferences } from '../dominio/dto/DtoComunSyPreferences';

@Injectable()
export class SyPreferencesComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/sistema/sypreferencescomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }
 
    public obtenerDto(usuario: string,preference:string): Promise<DtoComunSyPreferences> {
        var filtro = new DtoComunSyPreferences();
        filtro.usuario=usuario;
        filtro.preference=preference;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunSyPreferences)
            .catch(error => new DtoComunSyPreferences());
    } 

}