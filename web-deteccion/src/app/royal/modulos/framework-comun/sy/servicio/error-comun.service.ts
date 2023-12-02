import { DtoTabla } from './../../../../framework/modelo/generico/dto/DtoTabla';
import { EmailConfiguracion } from './../../../../framework/modelo/correo/EmailConfiguracion';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DtoComunEmailTest } from '../dominio/dto/DtoComunEmailTest';
import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class ErrorComunServicio {

    private rutaServicio = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/errorcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }


    public prueballamadaexterna( emailEnviar: DtoTabla ): Promise<DtoTabla> {                
      return this.http.put(this.rutaServicio + 'prueballamadaexterna', emailEnviar)
          .toPromise()
          .then(response => response as DtoTabla)
          .catch(error => new DtoTabla());
  }
}
