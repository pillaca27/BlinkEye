import { EmailConfiguracion } from './../../../../framework/modelo/correo/EmailConfiguracion';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DtoComunSyLogWeb } from '../dominio/dto/DtoComunSyLogWeb';
import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EmailTransaccion } from '@framework/modelo/correo/EmailTransaccion';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DominioTransaccion } from '@framework/modelo/generico/DominioTransaccion';
import { FiltroComunSyLogWeb } from '../dominio/filtro/FiltroComunSyLogWeb';


@Injectable()
export class SyLogWebServicio {

  private rutaServicio = `${this.config.getEnv('spring-framework-comun-api')}spring/sistema/sylogweb/`;
  constructor(private http: HttpClient, private config: AppConfig) { }

  public listarpaginado(filtro: FiltroComunSyLogWeb): Promise<DominioPaginacion> {
    return this.http.post(this.rutaServicio + 'listarpaginado', filtro)
      .toPromise()
      .then(response => response as DominioPaginacion)
      .catch(error => new DominioPaginacion());
  }

  public eliminar(dto: DtoComunSyLogWeb): Promise<DominioTransaccion> {
    return this.http.post(this.rutaServicio + 'eliminar', dto)
      .toPromise()
      .then(response => response as DominioTransaccion)
      .catch(error => null);
  }

  public eliminarPorFecha(dto: FiltroComunSyLogWeb): Promise<DominioTransaccion> {
    return this.http.post(this.rutaServicio + 'eliminarPorFecha', dto)
      .toPromise()
      .then(response => response as DominioTransaccion)
      .catch(error => null);
  }

  public obtenerTrace(dto: DtoComunSyLogWeb): Promise<DtoComunSyLogWeb> {
    return this.http.post(this.rutaServicio + 'obtenerTrace', dto)
      .toPromise()
      .then(response => response as DtoComunSyLogWeb)
      .catch(error => null);
  }
}
