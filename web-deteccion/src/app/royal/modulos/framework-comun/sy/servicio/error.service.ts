import { EmailConfiguracion } from './../../../../framework/modelo/correo/EmailConfiguracion';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DtoComunSyLogWeb } from '../dominio/dto/DtoComunSyLogWeb';
import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { FiltroComunSyLogWeb } from '../dominio/filtro/FiltroComunSyLogWeb';
import { ParametroPaginacionGenerico } from './../../../../../utilities/clases/ParametroPaginacionGenerico';
import { Errordto } from '../dominio/dto/Errordto';

@Injectable()
export class ErrorServicio {

  private url = `${this.config.getEnv('spring-framework-comun-api')}spring/core/errorcomun/`;
  constructor(private http: HttpClient, private config: AppConfig) { }

  public listarpaginado(filtro: FiltroComunSyLogWeb): Promise<DominioPaginacion> {
    return this.http.put(this.url + 'listarpaginado', filtro)
      .toPromise()
      .then(response => response as DominioPaginacion)
      .catch(error => new DominioPaginacion());
  }
  
  public actualizarEstadoMasivo(data: Errordto): Promise<Errordto> {
    return this.http.put(this.url + 'actualizarEstadoMasivo', data)
      .toPromise()
      .then(response => response as Errordto)
      .catch(error => error);
  }

  public eliminarRegistros(data: Errordto): Promise<Errordto> {
    return this.http.post(this.url + 'limpiarDatod', data)
      .toPromise()
      .then(response => response as Errordto)
      .catch(error => error);
  }
}
