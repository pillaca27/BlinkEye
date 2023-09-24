import { Injectable } from '@angular/core';
import { AppConfig } from 'src/environments/appconfig';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { DtoHrEspecialidad2 } from '../dominio/DtoHrEspecialidad2';
import { FiltroHrEspecialidad2 } from '../dominio/FiltroHrEspecialidad2';
import { DominioPaginacion } from 'src/app/util/DominioPaginacion';

@Injectable({
  providedIn: 'root'
})
export class Especialidad2Service {

  private url = `${this.config.getEnv('spring-rrhh-api')}spring/rrhh/hrespecialidad2/`;

  constructor(private http: HttpClient, private config: AppConfig) { }

  public listarPaginacion(filtros: FiltroHrEspecialidad2): Promise<DominioPaginacion> {
    return this.config.getHttp().put(this.url + 'listarParametros', filtros)
      .toPromise()
      .then(response => response as DominioPaginacion)
      .catch(error => new DominioPaginacion);
  }

  obtenerDto(dto: DtoHrEspecialidad2): Promise<DtoHrEspecialidad2> {
    return this.config.getHttp().put(this.url + 'obtenerDto', dto)
      .toPromise()
      .then(response => response as DtoHrEspecialidad2)
      .catch(error => new DtoHrEspecialidad2());
  }

  registrar(dto: DtoHrEspecialidad2): Promise<DtoHrEspecialidad2> {
    return this.config.getHttp().post(this.url + 'registrar', dto)
      .toPromise()
      .then(response => response as DtoHrEspecialidad2)
      .catch(error => new DtoHrEspecialidad2());
  }

  actualizar(dto: DtoHrEspecialidad2): Promise<DtoHrEspecialidad2> {
    return this.config.getHttp().put(this.url + 'actualizar', dto)
      .toPromise()
      .then(response => response as DtoHrEspecialidad2)
      .catch(error => new DtoHrEspecialidad2());
  }

  eliminar(dto: DtoHrEspecialidad2): Promise<DtoHrEspecialidad2> {
    return this.config.getHttp().put(this.url + 'eliminar', dto)
      .toPromise()
      .then(response => response as DtoHrEspecialidad2)
      .catch(error => new DtoHrEspecialidad2());
  }

  anular(dto: DtoHrEspecialidad2): Promise<DtoHrEspecialidad2> {
    return this.config.getHttp().put(this.url + 'anular', dto)
      .toPromise()
      .then(response => response as DtoHrEspecialidad2)
      .catch(error => new DtoHrEspecialidad2());
  }

}
