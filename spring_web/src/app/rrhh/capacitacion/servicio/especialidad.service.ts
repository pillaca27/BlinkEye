import { Injectable } from '@angular/core';
import { AppConfig } from 'src/environments/appconfig';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { FiltroHrEspecialidad } from '../dominio/FiltroHrEspecialidad';
import { DtoHrEspecialidad } from '../dominio/DtoHrEspecialidad';

@Injectable({
  providedIn: 'root'
})
export class EspecialidadService {

  private url = `${this.config.getEnv('spring-rrhh-api')}spring/rrhh/hrespecialidad/`;

  constructor(private http: HttpClient, private config: AppConfig) { }

  public listar(filtros: FiltroHrEspecialidad): Promise<DtoHrEspecialidad[]> {
    return this.config.getHttp().post(this.url + 'listarEspecialidades', filtros)
      .toPromise()
      .then(response => response as DtoHrEspecialidad[])
      .catch(error => []);
  }

  obtenerDto(dto: DtoHrEspecialidad): Promise<DtoHrEspecialidad> {
    return this.config.getHttp().put(this.url + 'obtenerDto', dto)
      .toPromise()
      .then(response => response as DtoHrEspecialidad)
      .catch(error => new DtoHrEspecialidad());
  }

  registrar(dto: DtoHrEspecialidad): Promise<DtoHrEspecialidad> {
    return this.config.getHttp().post(this.url + 'registrar', dto)
      .toPromise()
      .then(response => response as DtoHrEspecialidad)
      .catch(error => new DtoHrEspecialidad());
  }

  actualizar(dto: DtoHrEspecialidad): Promise<DtoHrEspecialidad> {
    return this.config.getHttp().put(this.url + 'actualizar', dto)
      .toPromise()
      .then(response => response as DtoHrEspecialidad)
      .catch(error => new DtoHrEspecialidad());
  }

  eliminar(dto: DtoHrEspecialidad): Promise<DtoHrEspecialidad> {
    return this.config.getHttp().put(this.url + 'eliminar', dto)
      .toPromise()
      .then(response => response as DtoHrEspecialidad)
      .catch(error => new DtoHrEspecialidad());
  }

  anular(dto: DtoHrEspecialidad): Promise<DtoHrEspecialidad> {
    return this.config.getHttp().put(this.url + 'anular', dto)
      .toPromise()
      .then(response => response as DtoHrEspecialidad)
      .catch(error => new DtoHrEspecialidad());
  }

}
