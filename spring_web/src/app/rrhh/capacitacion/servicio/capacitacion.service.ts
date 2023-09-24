import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { DominioPaginacion } from 'src/app/util/DominioPaginacion';
import { DtoTabla } from 'src/app/util/DtoTabla';
import { AppConfig } from 'src/environments/appconfig';
import { DtoHrCapacitacion } from '../dominio/DtoHrCapacitacion';
import { FiltroComunPersonamast } from '../dominio/FiltroComunPersonamast';
import { FiltroHrCapacitacion } from '../dominio/FiltroHrCapacitacion';
import { FiltroReporteUsuarios } from '../dominio/FiltroReporteUsuarios';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CapacitacionService {

  private url = `${this.config.getEnv('spring-rrhh-api')}spring/rrhh/hrcapacitacion/`;
  private url2 = `${this.config.getEnv('spring-rrhh-api')}spring/gth/pruebas/`;

  constructor(private http: HttpClient, private config: AppConfig) { }

  public listarPaginacion(filtros: FiltroHrCapacitacion): Promise<DominioPaginacion> {
    return this.config.getHttp().put(this.url + 'listarPaginacion', filtros)
      .toPromise()
      .then(response => response as DominioPaginacion)
      .catch(error => new DominioPaginacion);
  }

  public listarcompaniasporusuario(): Promise<DtoTabla[]> {
    return this.config.getHttp().get(this.url + 'listarcompaniasporusuario')
      .toPromise()
      .then(response => response as DtoTabla[])
      .catch(error => []);
  }

  listarEmpleados(filtro: FiltroComunPersonamast): Promise<DominioPaginacion> {
    return this.config.getHttp().put(this.url + 'listarEmpleados', filtro)
      .toPromise()
      .then(response => response as DominioPaginacion)
      .catch(error => new DominioPaginacion());
  }


  listarCursos(filtro: FiltroComunPersonamast): Promise<DominioPaginacion> {
    return this.config.getHttp().put(this.url + 'listarCursos', filtro)
      .toPromise()
      .then(response => response as DominioPaginacion)
      .catch(error => new DominioPaginacion());
  }

  registrar(dto: DtoHrCapacitacion): Promise<DtoHrCapacitacion> {
    return this.config.getHttp().post(this.url + 'registrar', dto)
      .toPromise()
      .then(response => response as DtoHrCapacitacion)
      .catch(error => new DtoHrCapacitacion());
  }

  actualizar(dto: DtoHrCapacitacion): Promise<DtoHrCapacitacion> {
    return this.config.getHttp().put(this.url + 'actualizar', dto)
      .toPromise()
      .then(response => response as DtoHrCapacitacion)
      .catch(error => new DtoHrCapacitacion());
  }

  anular(dto: DtoHrCapacitacion): Promise<DtoHrCapacitacion> {
    return this.config.getHttp().put(this.url + 'anular', dto)
      .toPromise()
      .then(response => response as DtoHrCapacitacion)
      .catch(error => new DtoHrCapacitacion());
  }

  obtenerDto(dto: DtoHrCapacitacion): Promise<DtoHrCapacitacion> {
    return this.config.getHttp().put(this.url + 'obtenerDto', dto)
      .toPromise()
      .then(response => response as DtoHrCapacitacion)
      .catch(error => new DtoHrCapacitacion());
  }

  public generarUsuariosExcel(filtro: FiltroReporteUsuarios): Observable<Blob> {
    return this.config.getHttp().post<Blob>(`${this.url2}/generarUsuariosExcel`, filtro,
      { responseType: 'blob' as 'json' });
  }

  generarReporteUsuarios(
    filtro: FiltroReporteUsuarios
  ): Observable<Blob> {
    return this.config.getHttp().post<Blob>(`${this.url2}generarReporteUsuarios`, filtro,
      { responseType: 'blob' as 'json' });
  }

  generarHtmlUsuarios(filtro: FiltroReporteUsuarios) {

    const headers = new HttpHeaders().set('Content-Type', 'application/json');

    return this.config.getHttp().post(`${this.url2}generarHtmlUsuarios`, filtro,
      {
        responseType: 'blob',
        headers: headers
      })
      .pipe(map(res => {
        return {
          filename: 'usuarios.html',
          data: res as Blob
        };
      }))
      .subscribe(res => {
        const link = window.URL.createObjectURL(res.data);
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.href = link;
        a.download = res.filename;
        a.click();
        window.URL.revokeObjectURL(link);
        a.remove();
      }, error => {
        throw error;
      }, () => {
        console.log('Completed file download.');
      });
  }

  generarTramaUsuarios(filtro: FiltroReporteUsuarios) {

    const headers = new HttpHeaders().set('Content-Type', 'application/json');

    return this.config.getHttp().post(`${this.url2}generarTramaUsuarios`, filtro,
      {
        responseType: 'blob',
        headers: headers
      })
      .pipe(map(res => {
        return {
          filename: 'usuarios.txt',
          data: res as Blob
        };
      }))
      .subscribe(res => {
        const link = window.URL.createObjectURL(res.data);
        const a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.href = link;
        a.download = res.filename;
        a.click();
        window.URL.revokeObjectURL(link);
        a.remove();
      }, error => {
        throw error;
      }, () => {
        console.log('Completed file download.');
      });
  }

}
